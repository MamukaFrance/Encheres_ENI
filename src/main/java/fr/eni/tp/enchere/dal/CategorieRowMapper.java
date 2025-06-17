package fr.eni.tp.enchere.dal;

import fr.eni.tp.enchere.bo.Adresse;
import fr.eni.tp.enchere.bo.Categorie;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

class CategorieRowMapper implements RowMapper<Categorie> {

    public Categorie mapRow(ResultSet rs, int rowNum) throws SQLException {
        Categorie categorie = new Categorie();
        categorie.setId(rs.getLong("no_categorie"));
        categorie.setLibelle(rs.getString("libelle"));

        return categorie;
    }
};