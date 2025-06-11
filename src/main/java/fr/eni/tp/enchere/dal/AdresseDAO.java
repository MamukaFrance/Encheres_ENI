package fr.eni.tp.enchere.dal;

import fr.eni.tp.enchere.bo.Adresse;

public interface AdresseDAO {
    void createAdresse (Adresse adresse);
    Adresse readUtilisateurByID (long id);
    void deleteUtilisateur (long id);
    Adresse updateUtilisateur (Adresse adresse);

}
