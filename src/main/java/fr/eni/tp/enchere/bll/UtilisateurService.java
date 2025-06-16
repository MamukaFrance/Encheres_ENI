package fr.eni.tp.enchere.bll;

import fr.eni.tp.enchere.bo.Utilisateur;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UtilisateurService {
    List<Utilisateur> getAll();

    Utilisateur get(String pseudo);

    void create(Utilisateur utilisateur);

    void update(Utilisateur utilisateur);

    void delete(String pseudo);

    Utilisateur registerNewUser(Utilisateur personne);
}
