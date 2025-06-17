package fr.eni.tp.enchere.bll;

import fr.eni.tp.enchere.bo.Adresse;
import fr.eni.tp.enchere.bo.Utilisateur;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UtilisateurService extends UserDetailsService {
    List<Utilisateur> getAll();

    Utilisateur get(String pseudo);

    void create(Utilisateur utilisateur);

    void update(Utilisateur utilisateur);

    void delete(String pseudo);

    Utilisateur registerNewUser(Utilisateur utilisateur);

    Adresse create(Adresse adresse);

    Adresse getAdresseById(long id);
}
