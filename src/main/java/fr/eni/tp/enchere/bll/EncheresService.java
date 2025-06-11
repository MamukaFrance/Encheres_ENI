package fr.eni.tp.enchere.bll;

import org.springframework.web.bind.annotation.GetMapping;

public interface EncheresService {

    public String consulterEncheres();

    public String voirEnchere();

    public String nouvelleVente();

    public String venteRemportee();

    public String detailVente();

    public String ajouterPhoto();
}
