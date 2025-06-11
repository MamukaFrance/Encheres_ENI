package fr.eni.tp.enchere.dal;

import fr.eni.tp.enchere.bo.Adresse;
import fr.eni.tp.enchere.bo.Utilisateur;

public interface AdresseDAO {
    void createAdresse (Adresse adresse);
    Utilisateur readUtilisateurByEmail (String pseudo);

    Utilisateur readUtilisateurByPseudo (String email);
    void deleteUtilisateur (Utilisateur utilisateur);
    void updateUtilisateur (Utilisateur utilisateur);

}
