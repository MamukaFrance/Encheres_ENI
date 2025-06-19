package fr.eni.tp.enchere.dal;

import fr.eni.tp.enchere.bo.Utilisateur;

import java.util.List;

public interface UtilisateursDAO {
 void createUtilisateur (Utilisateur utilisateur);
    Utilisateur readUtilisateurByEmail (String email);

    Utilisateur readUtilisateurByPseudo (String pseudo );
    void deleteUtilisateur (String pseudo);
    void updateUtilisateur (Utilisateur utilisateur);
    List<Utilisateur> readAll();
   Utilisateur create(Utilisateur utilisateur);

    void updateCreditByPseudo(Utilisateur utilisateur ,int aRembourser);
}
