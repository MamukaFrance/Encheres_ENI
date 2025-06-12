package fr.eni.tp.enchere.dal;

import fr.eni.tp.enchere.bo.Categorie;

import java.util.List;

public interface CategorieDAO {
    void create (Categorie categorie);
    Categorie read (long id);
    void update (Categorie categorie);
    void delete (long id);
    List<Categorie> readAll();
}
