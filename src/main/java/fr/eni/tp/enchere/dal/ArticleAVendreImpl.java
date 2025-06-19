package fr.eni.tp.enchere.dal;

import fr.eni.tp.enchere.bo.Adresse;
import fr.eni.tp.enchere.bo.ArticleAVendre;
import fr.eni.tp.enchere.bo.Categorie;
import fr.eni.tp.enchere.bo.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class ArticleAVendreImpl implements ArticleAVendreDAO {
    private final String FIND_BY_ID = "SELECT * FROM ARTICLES_A_VENDRE WHERE no_article =:id";
    private final String FIND_ALL = "SELECT * FROM ARTICLES_A_VENDRE";

    //enlever l'id pour la création on a un keyholder
    private final String INSERT = "INSERT INTO ARTICLES_A_VENDRE (nom_article, description, date_debut_encheres, date_fin_encheres, statut_enchere, prix_initial, prix_vente, id_utilisateur, no_categorie, no_adresse_retrait)"
            + " VALUES (:nomArticle, :description, :dateDebutEncheres, :dateFinEncheres, :statutEnchere, :prixInitial, :prixVente, :idUtilisateur, :noCategorie, :noAdresseRetrait)";
    // attention faut enlever le * pour le delete
    private final String DELETE = "DELETE FROM ARTICLES_A_VENDRE WHERE no_article =:id";
    private final String UPDATE = "UPDATE ARTICLES_A_VENDRE SET nom_article = :nomArticle, description = :description, date_debut_encheres = :dateDebutEncheres, date_fin_encheres = :dateFinEncheres, statut_enchere = :statutEnchere, prix_initial = :prixInitial, prix_vente = :prixVente, id_utilisateur = :idUtilisateur, no_categorie = :noCategorie, no_adresse_retrait = :noAdresseRetrait where no_article= :noArticle";
    private final String UPDATE_PHOTO = "UPDATE ARTICLES_A_VENDRE SET photo = :photo WHERE no_article = :no_article";


    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;


    @Override
    public ArticleAVendre read(long id) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id", id);
        return jdbcTemplate.queryForObject(FIND_BY_ID, mapSqlParameterSource, new ArticleAVendreRowMapper());
    }

    @Override
    public List<ArticleAVendre> readAll() {
        return jdbcTemplate.query(FIND_ALL, new ArticleAVendreRowMapper());
    }

    @Override
    public void create(ArticleAVendre articleAVendre) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("nomArticle", articleAVendre.getNom());
        parameterSource.addValue("description", articleAVendre.getDescription());
        parameterSource.addValue("dateDebutEncheres", articleAVendre.getDateDebutEncheres());
        parameterSource.addValue("dateFinEncheres", articleAVendre.getDateFinEncheres());
        parameterSource.addValue("statutEnchere", articleAVendre.getStatut());
        parameterSource.addValue("prixInitial", articleAVendre.getPrixInitial());
        parameterSource.addValue("prixVente", articleAVendre.getPrixVente());
        parameterSource.addValue("idUtilisateur", articleAVendre.getVendeur().getPseudo());
        parameterSource.addValue("noCategorie", articleAVendre.getCategorie().getId());
        parameterSource.addValue("noAdresseRetrait", articleAVendre.getRetrait().getId());

        jdbcTemplate.update(INSERT, parameterSource, keyHolder);
        if (keyHolder != null && keyHolder.getKey() != null) {
            articleAVendre.setId(keyHolder.getKey().longValue());
        }


    }


    @Override
    public void update(ArticleAVendre articleAVendre) {

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("noArticle", articleAVendre.getId());
        parameterSource.addValue("nomArticle", articleAVendre.getNom());
        parameterSource.addValue("description", articleAVendre.getDescription());
        parameterSource.addValue("dateDebutEncheres", articleAVendre.getDateDebutEncheres());
        parameterSource.addValue("dateFinEncheres", articleAVendre.getDateFinEncheres());
        parameterSource.addValue("statutEnchere", articleAVendre.getStatut());
        parameterSource.addValue("prixInitial", articleAVendre.getPrixInitial());
        parameterSource.addValue("prixVente", articleAVendre.getPrixVente());

        // Ensure these are the correct types expected by your database
        parameterSource.addValue("idUtilisateur", articleAVendre.getVendeur().getPseudo());
        parameterSource.addValue("noCategorie", articleAVendre.getCategorie().getId());
        parameterSource.addValue("noAdresseRetrait", articleAVendre.getRetrait().getId());

        jdbcTemplate.update(UPDATE, parameterSource);
    }

    @Override
    public void updatePhoto(ArticleAVendre articleAVendre) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("no_article", articleAVendre.getId());
        parameterSource.addValue("photo", articleAVendre.getPhoto());
        jdbcTemplate.update(UPDATE_PHOTO, parameterSource);
    }


    @Override
    public void  delete(long id) {
        MapSqlParameterSource parameterSource =  new MapSqlParameterSource();
        parameterSource.addValue("id", id);
        jdbcTemplate.update(DELETE, parameterSource);
    }



    public class ArticleAVendreRowMapper implements RowMapper<ArticleAVendre> {


        @Override
        public ArticleAVendre mapRow(ResultSet rs, int rowNum) throws SQLException {
            ArticleAVendre a = new ArticleAVendre();
            a.setId(rs.getLong("NO_ARTICLE"));
            a.setNom(rs.getString("NOM_ARTICLE"));
            a.setDescription(rs.getString("DESCRIPTION"));
            a.setPhoto(rs.getString("photo"));
            a.setDateDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate());
            a.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
            a.setStatut(rs.getInt("statut_enchere"));
            a.setPrixInitial(rs.getInt("prix_initial"));
            a.setPrixVente(rs.getInt("prix_vente"));

            Adresse retrait = new Adresse();
            retrait.setId(rs.getLong("no_adresse_retrait"));
            a.setRetrait(retrait);

            Utilisateur vendeur = new Utilisateur();
            vendeur.setPseudo(rs.getString("id_utilisateur"));
            a.setVendeur(vendeur);

            Categorie categorie = new Categorie();
            categorie.setId(rs.getLong("no_categorie"));
            a.setCategorie(categorie);
            return a;
        }
    }
}
