package fr.eni.tp.enchere.dal;

import fr.eni.tp.enchere.bo.Enchere;
import fr.eni.tp.enchere.bo.Utilisateur;

import java.time.LocalDateTime;
import java.util.*;

public interface EnchereDAO {

    Enchere create(Enchere enchere);
    Enchere read(String idUtilisateur, long noArticle, int montantEnchere);
    List<Enchere> readAll();
    List<Enchere> readByNo_Article(Long id);
    void update(Enchere enchere);
    void delete(String pseudo, Long id, int montant);

    public void deleteByNoArticle(Long noArticle);
}
