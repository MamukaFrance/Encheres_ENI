package fr.eni.tp.enchere.dal;

import fr.eni.tp.enchere.bo.Adresse;

public interface AdresseDAO {
    void createAdresse (Adresse adresse);
    Adresse readAdresseByID (long id);
    void deleteAdresse (long id);
    Adresse updateAdresse (Adresse adresse);

}
