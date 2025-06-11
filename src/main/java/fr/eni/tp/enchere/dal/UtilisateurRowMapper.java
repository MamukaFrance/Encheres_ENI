package fr.eni.tp.enchere.dal;

import fr.eni.tp.enchere.bo.Adresse;
import fr.eni.tp.enchere.bo.Utilisateur;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


    class UtilisateurRowMapper implements RowMapper<Utilisateur> {

        public Utilisateur mapRow(ResultSet rs, int rowNum) throws SQLException {
            Utilisateur f = new Utilisateur();
            f.setPseudo(rs.getString("pseudo"));
            f.setNom(rs.getString("nom"));
            f.setPrenom(rs.getString("prenom"));
            f.setEmail(rs.getString("email"));
            f.setTelephone(rs.getString("telephone"));
            f.setMotDePasse(rs.getString("mot_de_passe"));
            f.setCredit(rs.getInt("credit"));
            f.setAdmin(rs.getBoolean("administrateur"));

            Adresse adresse = new Adresse();
            adresse.setId(rs.getLong("no_adresse"));
            f.setAdresse(adresse);

            return f;
        }
    }

