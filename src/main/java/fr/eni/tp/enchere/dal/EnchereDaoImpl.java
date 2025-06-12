package fr.eni.tp.enchere.dal;

import fr.eni.tp.enchere.bo.ArticleAVendre;
import fr.eni.tp.enchere.bo.Enchere;
import fr.eni.tp.enchere.bo.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class EnchereDaoImpl implements EnchereDAO{
    private final String READ = "SELECT * FROM ENCHERES WHERE id_utilisateur =:idUtilisateur AND no_article = :noArticle AND montant_enchere =:montantEnchere";
    private final String READ_ALL = "SELECT * FROM ENCHERES";
    private final String CREATE = "INSERT INTO ENCHERES (id_utilisateur, no_article, montant_enchere, date_enchere)"
            +" VALUES(:idUtilisateur, :noArticle, :montantEnchere, :dateEnchere)";
    private final String UPDATE = "UPDATE ENCHERES SET date_enchere = : dateEnchere WHERE id_utilisateur =:idUtilisateur AND no_article = :noArticle AND montant_enchere =:montantEnchere";
    private final String DELETE = "DELETE FROM ENCHERES WHERE id_utilisateur =:idUtilisateur AND no_article = :noArticle AND montant_enchere =:montantEnchere";


    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public void create(Enchere enchere) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("idUtilisateur",enchere.getAcquereur());
        parameterSource.addValue("noArticle", enchere.getArticleAVendre());
        parameterSource.addValue("montantEnchere", enchere.getMontant());
        parameterSource.addValue("dateEnchere", enchere.getDate());
        jdbcTemplate.update(CREATE, parameterSource);
    }

    @Override
    public Enchere read(String idUtilisateur, long noArticle, int montantEnchere) {
            MapSqlParameterSource parameterSource = new MapSqlParameterSource();
            parameterSource.addValue("idUtilisateur", idUtilisateur);
            parameterSource.addValue("noArticle", noArticle);
            parameterSource.addValue("montantEnchere", montantEnchere);
            return jdbcTemplate.queryForObject(READ, parameterSource, new EnchereRowMapper());
    }

    @Override
    public List<Enchere> readAll() {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        return jdbcTemplate.query(READ_ALL, new EnchereRowMapper());
    }

    @Override
    public void update(Enchere enchere) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("idUtilisateur",enchere.getAcquereur());
        parameterSource.addValue("noArticle", enchere.getArticleAVendre());
        parameterSource.addValue("montantEnchere", enchere.getMontant());
        parameterSource.addValue("dateEnchere", enchere.getDate());
        jdbcTemplate.update(UPDATE, parameterSource);
    }

    @Override
    public void delete(String pseudo, Long id, int montant) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("idUtilisateur", pseudo);
        mapSqlParameterSource.addValue("noArticle", id);
        mapSqlParameterSource.addValue("montantEnchere", montant);
        jdbcTemplate.update(DELETE, mapSqlParameterSource);
    }

    public class EnchereRowMapper implements RowMapper<Enchere> {

        @Override
        public Enchere mapRow(ResultSet rs, int rowNum) throws SQLException {
            Enchere enchere = new Enchere();

            Utilisateur acquereur = new Utilisateur();
            acquereur.setPseudo(rs.getString("id_utilisateur"));
            enchere.setAcquereur(acquereur);

            ArticleAVendre articleAVendre = new ArticleAVendre();
            articleAVendre.setId(rs.getLong("no_article"));
            enchere.setArticleAVendre(articleAVendre);

            enchere.setDate(rs.getTimestamp("date_enchere").toLocalDateTime());
            enchere.setMontant(rs.getInt("montant_enchere"));
            return enchere;
        }
    }
}

