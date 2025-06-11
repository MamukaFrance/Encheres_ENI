package fr.eni.tp.enchere.dal;

import fr.eni.tp.enchere.bo.ArticleAVendre;

import java.time.LocalDate;
import java.util.List;

public interface ArticleAVendreDAO {

    void create(ArticleAVendre articleAVendre);

    ArticleAVendre articleByID(long id);

    List<ArticleAVendre> articlesLst();

    void update( ArticleAVendre articleAVendre);

    void  delete(long id);
}
