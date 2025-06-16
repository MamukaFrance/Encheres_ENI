package fr.eni.tp.enchere.bll;

import fr.eni.tp.enchere.bo.Adresse;
import fr.eni.tp.enchere.bo.ArticleAVendre;
import fr.eni.tp.enchere.bo.Categorie;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface EncheresService {

    public List<ArticleAVendre> consulterEncheres();

    public ArticleAVendre voirEnchere(Long id);

    public void nouvelleVente( ArticleAVendre articleAvendre);

    public List<Adresse> getAdresses();
    public List<Categorie> getCategories();

    public String venteRemportee();

    public String detailVente();

    public String ajouterPhoto();

    Adresse getAdresseById(long id);

    Categorie getCategoriesById(long id);
}
