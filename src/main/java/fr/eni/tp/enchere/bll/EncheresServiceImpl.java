package fr.eni.tp.enchere.bll;

import fr.eni.tp.enchere.bo.Adresse;
import fr.eni.tp.enchere.bo.ArticleAVendre;
import fr.eni.tp.enchere.bo.Categorie;
import fr.eni.tp.enchere.bo.Enchere;
import fr.eni.tp.enchere.dal.*;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Primary
public class EncheresServiceImpl implements EncheresService {

    private CategorieDAO categorieDAO;
    private AdresseDAO adresseDAO;
    private ArticleAVendreDAO articleAVendreDAO;
    private UtilisateursDAO utilisateursDAO;
    private EnchereDAO enchereDAO;

    public EncheresServiceImpl(CategorieDAO categorieDAO, AdresseDAO adresseDAO, ArticleAVendreDAO articleAVendreDAO, UtilisateursDAO utilisateursDAO, EnchereDAO enchereDAO) {
        this.categorieDAO = categorieDAO;
        this.adresseDAO = adresseDAO;
        this.articleAVendreDAO = articleAVendreDAO;
        this.utilisateursDAO = utilisateursDAO;
        this.enchereDAO = enchereDAO;
    }

    @Override
    // Sur la page d'accueil on demande la liste des encheres en cours, mais on veut en
    // faite la liste des objets a vendre!!(consulter enchere mais on affiche une liste d'objet a vendre)
    public List<ArticleAVendre> consulterEncheres() {
        List<ArticleAVendre> articlesAVendres = this.articleAVendreDAO.readAll();

        LocalDate aujourdHui = LocalDate.now();

        List<ArticleAVendre> articlesFiltres = articlesAVendres.stream()
                .filter(article -> article.getDateFinEncheres().isAfter(aujourdHui)).toList();


        return articlesFiltres;

    }
@Transactional
    @Override
    public Enchere creerEnchere(Enchere enchere) {
        var idAModifier = enchere.getArticleAVendre().getId();
        List<Enchere> ancienEncheres = enchereDAO.readByNo_Article(idAModifier);

        Enchere ancienEnchere= ancienEncheres.stream().max(Comparator.comparingInt(Enchere::getMontant)).orElse(null);
        var aRembourser = ancienEnchere.getMontant();

        Enchere createdEnchere = enchereDAO.create(enchere);

        if (createdEnchere != null) {

//modif sur l'article
            var articleAVendreAModifier = articleAVendreDAO.read(idAModifier);
            articleAVendreAModifier.setPrixVente(enchere.getMontant());
            articleAVendreDAO.update(articleAVendreAModifier);

//remboursement ancienne enchere
            var pseudoAncienEnchere = ancienEnchere.getAcquereur();
            utilisateursDAO.updateCreditByPseudo(pseudoAncienEnchere, aRembourser);

//debit sur nouvelle enchere
            var aDebiter=(enchere.getMontant()-(enchere.getMontant())*2);
            utilisateursDAO.updateCreditByPseudo(enchere.getAcquereur(),aDebiter);


        }

        return enchere;
    }

    @Override
    public ArticleAVendre voirEnchere(Long id) {
        return articleAVendreDAO.read(id);
    }

    @Override
    public void nouvelleVente(ArticleAVendre articleAvendre) {
        articleAVendreDAO.create(articleAvendre);
    }

    @Override
    public List<Adresse> getAdresses() {
        return adresseDAO.readAll();
    }

    @Override
    public List<Categorie> getCategories() {
        return categorieDAO.readAll();
    }

    @Override
    public String venteRemportee() {
        return "";
    }

    @Override
    public String detailVente() {
        return "";
    }

    @Override
    public void ajouterPhoto(ArticleAVendre articleAVendre) {
        articleAVendreDAO.updatePhoto(articleAVendre);
    }

    @Override
    public Adresse getAdresseById(long id) {
        return adresseDAO.readAdresseByID(id);
    }

    @Override
    public Categorie getCategoriesById(long id) {
        return categorieDAO.read(id);
    }
}
