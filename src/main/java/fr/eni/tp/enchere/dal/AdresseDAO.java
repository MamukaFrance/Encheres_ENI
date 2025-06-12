package fr.eni.tp.enchere.dal;

import fr.eni.tp.enchere.bo.Adresse;

import java.util.List;

public interface AdresseDAO {
    void createAdresse (Adresse adresse);

    List<Adresse> readAll();

    Adresse readAdresseByID (long id);
    void deleteAdresse (long id);
    Adresse updateAdresse (Adresse adresse);

}
