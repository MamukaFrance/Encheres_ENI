package fr.eni.tp.enchere.dal;

import fr.eni.tp.enchere.bo.Categorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategorieDAOImpl implements CategorieDAO{

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    private final String READ = "SELECT * FROM CATEGORIES WHERE no_categorie =:idCategorie ";
    private final String READ_ALL = "SELECT * FROM CATEGORIES";
    private final String CREATE = "INSERT INTO CATEGORIES (no_categorie, libelle)"
            +" VALUES(:idCategorie, :libelle)";
    private final String UPDATE = "UPDATE CATEGORIES SET libelle = : libelle WHERE no_categorie =:idCategorie";
    private final String DELETE = "DELETE FROM CATEGORIES WHERE no_categorie =:idCategorie ";




    @Override
    public void create(Categorie categorie) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("idCategorie", categorie.getId());
        params.addValue("libelle", categorie.getLibelle());

        jdbcTemplate.update(CREATE, params);

    }

    @Override
    public Categorie read(long id) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("idCategorie", id);

        return jdbcTemplate.queryForObject(READ, namedParameters,
                new CategorieRowMapper());
    }

    @Override
    public void update(Categorie categorie) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("idCategorie", categorie.getId());
        params.addValue("libelle", categorie.getLibelle());

        jdbcTemplate.update(UPDATE, params);

    }

    @Override
    public void delete(long id) {
        jdbcTemplate.update(DELETE, new MapSqlParameterSource("idCategorie", id));
    }

    @Override
    public List<Categorie> readAll() {

        return jdbcTemplate.query(READ_ALL, new CategorieRowMapper());
    }
}
