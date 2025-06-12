package fr.eni.tp.enchere.bll;

import fr.eni.tp.enchere.bo.Adresse;
import fr.eni.tp.enchere.bo.ArticleAVendre;
import fr.eni.tp.enchere.bo.Categorie;
import fr.eni.tp.enchere.bo.Enchere;
import fr.eni.tp.enchere.dal.AdresseDAO;
import fr.eni.tp.enchere.dal.ArticleAVendreDAO;
import fr.eni.tp.enchere.dal.UtilisateursDAO;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Primary
public class EncheresServiceImpl implements EncheresService{

    private AdresseDAO adresseDAO;
private ArticleAVendreDAO articleAVendreDAO;
private UtilisateursDAO utilisateursDAO;

    public EncheresServiceImpl(AdresseDAO adresseDAO, ArticleAVendreDAO articleAVendreDAO, UtilisateursDAO utilisateursDAO) {
        this.adresseDAO = adresseDAO;
        this.articleAVendreDAO = articleAVendreDAO;
        this.utilisateursDAO = utilisateursDAO;
    }

    @Override
    // Sur la page d'accueil on demande la liste des encheres en cours, mais on veut en
    // faite la liste des objets a vendre!!(consulter enchere mais on affiche une liste d'objet a vendre)
    public List<ArticleAVendre>  consulterEncheres() {
        List<ArticleAVendre> articlesAVendres = this.articleAVendreDAO.readAll();

        LocalDate aujourdHui = LocalDate.now();

        List<ArticleAVendre> articlesFiltres = articlesAVendres.stream()
                .filter(article -> article.getDateFinEncheres().isAfter(aujourdHui)).toList();


        return articlesFiltres;

    }

    @Override
    public ArticleAVendre voirEnchere(Long id) {
        return null;
    }

    @Override
    public void nouvelleVente(ArticleAVendre articleAvendre) {

    }

    @Override
    public List<Adresse> getAdresses() {
        return List.of();
    }

    @Override
    public List<Categorie> getCategories() {
        return List.of();
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
}
