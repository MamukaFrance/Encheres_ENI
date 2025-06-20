package fr.eni.tp.enchere.bll;

import fr.eni.tp.enchere.bo.*;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface EncheresService {

    public List<ArticleAVendre> consulterEncheres();
    public Enchere creerEnchere (Enchere enchere);
public void suppprimerArticleAvendreETRemboursementEnchere (ArticleAVendre articleAVendre);
    public ArticleAVendre voirEnchere(Long id);
public String  voirutilisateurparrapportalidarticle(Long id);
    public void nouvelleVente( ArticleAVendre articleAvendre);

    public List<Adresse> getAdresses();
    public List<Categorie> getCategories();

    public String venteRemportee();

    public String detailVente();

    void ajouterPhoto(ArticleAVendre articleAVendre);

    Adresse getAdresseById(long id);

    Categorie getCategoriesById(long id);
}
