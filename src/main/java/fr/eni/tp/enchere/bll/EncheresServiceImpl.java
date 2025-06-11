package fr.eni.tp.enchere.bll;

import fr.eni.tp.enchere.dal.AdresseDAO;
import fr.eni.tp.enchere.dal.ArticleAVendreDAO;
import fr.eni.tp.enchere.dal.UtilisateursDAO;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

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
    public String consulterEncheres() {
        return "";
    }

    @Override
    public String voirEnchere() {
        return "";
    }

    @Override
    public String nouvelleVente() {
        return "";
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
