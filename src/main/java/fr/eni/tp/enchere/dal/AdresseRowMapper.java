package fr.eni.tp.enchere.dal;

import fr.eni.tp.enchere.bo.Adresse;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

class AdresseRowMapper implements RowMapper<Adresse> {

    public Adresse mapRow(ResultSet rs, int rowNum) throws SQLException {
        Adresse adresse = new Adresse();
        adresse.setId(rs.getLong("id"));
        adresse.setRue(rs.getString("rue"));
        adresse.setCodePostal(rs.getString("code_postal"));
        adresse.setVille(rs.getString("ville"));
        return adresse;
    }
};