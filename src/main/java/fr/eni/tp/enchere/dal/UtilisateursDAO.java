package fr.eni.tp.enchere.dal;

import fr.eni.tp.enchere.bo.Utilisateur;

public interface UtilisateursDAO {
 void createUtilisateur (Utilisateur utilisateur);
    Utilisateur readUtilisateurByEmail (String pseudo);

    Utilisateur readUtilisateurByPseudo (String email);
    void deleteUtilisateur (String email);
    void updateUtilisateur (Utilisateur utilisateur);

}
