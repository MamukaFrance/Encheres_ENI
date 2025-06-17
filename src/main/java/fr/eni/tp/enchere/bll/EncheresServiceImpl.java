package fr.eni.tp.enchere.bll;

import fr.eni.tp.enchere.bo.Adresse;
import fr.eni.tp.enchere.bo.ArticleAVendre;
import fr.eni.tp.enchere.bo.Categorie;
import fr.eni.tp.enchere.bo.Enchere;
import fr.eni.tp.enchere.dal.AdresseDAO;
import fr.eni.tp.enchere.dal.ArticleAVendreDAO;
import fr.eni.tp.enchere.dal.CategorieDAO;
import fr.eni.tp.enchere.dal.UtilisateursDAO;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Primary
public class EncheresServiceImpl implements EncheresService {

    private CategorieDAO categorieDAO;
    private AdresseDAO adresseDAO;
    private ArticleAVendreDAO articleAVendreDAO;
    private UtilisateursDAO utilisateursDAO;

    public EncheresServiceImpl(CategorieDAO categorieDAO, AdresseDAO adresseDAO, ArticleAVendreDAO articleAVendreDAO, UtilisateursDAO utilisateursDAO) {
        this.categorieDAO = categorieDAO;
        this.adresseDAO = adresseDAO;
        this.articleAVendreDAO = articleAVendreDAO;
        this.utilisateursDAO = utilisateursDAO;
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
    public String ajouterPhoto() {
        return "";
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
