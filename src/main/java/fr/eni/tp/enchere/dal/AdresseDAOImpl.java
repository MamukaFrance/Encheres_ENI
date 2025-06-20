package fr.eni.tp.enchere.dal;

import fr.eni.tp.enchere.bo.Adresse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.swing.plaf.basic.BasicTreeUI;
import java.util.List;

@Repository
public class AdresseDAOImpl implements AdresseDAO{


    private  final String INSERT = "INSERT INTO ADRESSES (rue, code_postal, ville) VALUES (:rue, :codePostal, :ville)";
    private  final String SELECT_BY_ID = "SELECT no_adresse, rue, code_postal, ville FROM ADRESSES WHERE no_adresse = :id";
    private  final String UPDATE = "UPDATE ADRESSES SET rue = :rue, code_postal = :codePostal, ville = :ville WHERE no_adresse = :id";
    private  final String DELETE = "DELETE FROM ADRESSES WHERE no_adresse = :id";
    private final String READ_ALL = "SELECT * FROM ADRESSES";


    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;


    public Adresse createAdresse(Adresse adresse) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("rue", adresse.getRue());
        params.addValue("codePostal", adresse.getCodePostal());
        params.addValue("ville", adresse.getVille());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(INSERT, params, keyHolder);

        adresse.setId(keyHolder.getKey().longValue());
        return adresse;
    }



    @Override
    public List<Adresse> readAll() {

        return jdbcTemplate.query(READ_ALL, new AdresseRowMapper());
    }


    @Override
    public Adresse readAdresseByID(long id) {
        return jdbcTemplate.queryForObject(SELECT_BY_ID, new MapSqlParameterSource("id", id), new AdresseRowMapper());
    }

    @Override
    public void deleteAdresse(long id) {
        jdbcTemplate.update(DELETE, new MapSqlParameterSource("id", id));
    }

    @Override
    public Adresse updateAdresse(Adresse adresse) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", adresse.getId());
        params.addValue("rue", adresse.getRue());
        params.addValue("codePostal", adresse.getCodePostal());
        params.addValue("ville", adresse.getVille());

        jdbcTemplate.update(UPDATE, params);
        return adresse;
    }

    }

