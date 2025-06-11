package fr.eni.tp.enchere.bll;

import fr.eni.tp.enchere.bo.ArticleAVendre;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface EncheresService {

    public List<ArticleAVendre> consulterEncheres();

    public String voirEnchere();

    public String nouvelleVente();

    public String venteRemportee();

    public String detailVente();

    public String ajouterPhoto();
}
