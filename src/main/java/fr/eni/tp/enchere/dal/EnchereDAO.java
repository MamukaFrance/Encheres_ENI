package fr.eni.tp.enchere.dal;

import fr.eni.tp.enchere.bo.Enchere;

import java.time.LocalDateTime;
import java.util.*;

public interface EnchereDAO {

    void create(Enchere enchere);
    Enchere read(String idUtilisateur, long noArticle, int montantEnchere);
    List<Enchere> readAll();
    void update(Enchere enchere);
    void delete(String pseudo, Long id, int montant);
}
