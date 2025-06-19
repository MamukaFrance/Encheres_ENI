package fr.eni.tp.enchere.bll;

import fr.eni.tp.enchere.bo.Enchere;
import fr.eni.tp.enchere.bo.Utilisateur;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface AdminService extends UserDetailsService {

    List<Utilisateur> getAll();

    List<Enchere>  getEncheresByUser(Utilisateur utilisateur);

    Utilisateur get(String pseudo);

    void desactiverCompte(Utilisateur utilisateur);

    void supprimerCompte(Utilisateur utilisateur);
}
