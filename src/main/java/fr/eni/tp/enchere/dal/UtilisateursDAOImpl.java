package fr.eni.tp.enchere.dal;

import fr.eni.tp.enchere.bo.Adresse;
import fr.eni.tp.enchere.bo.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UtilisateursDAOImpl implements UtilisateursDAO {

    private final String FIND_BY_PSEUDO = "SELECT pseudo, nom, prenom, email, telephone, mot_de_passe, credit, administrateur, no_adresse, is_activ FROM UTILISATEURS WHERE pseudo = :pseudo";
    private final String FIND_BY_EMAIL = "SELECT pseudo, nom, prenom, email, telephone, mot_de_passe, credit, administrateur, no_adresse, is_activ FROM UTILISATEURS WHERE email = :email";
    private final String INSERT = "INSERT INTO UTILISATEURS(pseudo, nom, prenom, email, telephone, mot_de_passe, credit, administrateur, no_adresse, is_activ)  VALUES (:pseudo, :nom, :prenom, :email, :telephone, :mot_de_passe, :credit, :administrateur, :no_adresse, :is_activ) ";

    private final String DELETE_BY_EMAIL = "DELETE FROM UTILISATEURS WHERE email = :email";
    private final String UPDATE = "UPDATE UTILISATEURS SET pseudo = :pseudo, nom = :nom, prenom = :prenom, telephone = :telephone, mot_de_passe = :mot_de_passe, credit = :credit, administrateur = :administrateur, no_adresse = :no_adresse, is_activ = :is_activ WHERE email = :email";
    private final String UPDATE_CREDIT_BY_PSEUDO = " UPDATE UTILISATEURS SET credit = :credit WHERE pseudo = :pseudo";
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;


    @Override
    public void createUtilisateur(Utilisateur utilisateur) {

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("pseudo", utilisateur.getPseudo());
        namedParameters.addValue("nom", utilisateur.getNom());
        namedParameters.addValue("prenom", utilisateur.getPrenom());
        namedParameters.addValue("email", utilisateur.getEmail());
        namedParameters.addValue("telephone", utilisateur.getTelephone());
        namedParameters.addValue("mot_de_passe", utilisateur.getMotDePasse());
        namedParameters.addValue("credit", utilisateur.getCredit());
        namedParameters.addValue("administrateur", utilisateur.isAdmin());
        namedParameters.addValue("no_adresse", utilisateur.getAdresse().getId());
        namedParameters.addValue("is_activ", utilisateur.getActiv());
        jdbcTemplate.update(INSERT, namedParameters);


    }

    @Override
    public Utilisateur readUtilisateurByEmail(String email) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("email", email);

        return jdbcTemplate.queryForObject(FIND_BY_EMAIL, namedParameters, new UtilisateurRowMapper());
    }

    @Override
    public Utilisateur readUtilisateurByPseudo(String pseudo) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("pseudo", pseudo);

        return jdbcTemplate.queryForObject(FIND_BY_PSEUDO, namedParameters, new UtilisateurRowMapper());
    }

    @Override
    public void deleteUtilisateur(String email) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("email", email);

        jdbcTemplate.update(DELETE_BY_EMAIL, namedParameters);
    }

    @Override
    public void updateUtilisateur(Utilisateur utilisateur) {

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("pseudo", utilisateur.getPseudo());
        namedParameters.addValue("nom", utilisateur.getNom());
        namedParameters.addValue("prenom", utilisateur.getPrenom());
        namedParameters.addValue("email", utilisateur.getEmail());
        namedParameters.addValue("telephone", utilisateur.getTelephone());
        namedParameters.addValue("mot_de_passe", utilisateur.getMotDePasse());
        namedParameters.addValue("credit", utilisateur.getCredit());
        namedParameters.addValue("administrateur", utilisateur.isAdmin());
        namedParameters.addValue("no_adresse", utilisateur.getAdresse().getId());
        namedParameters.addValue("is_activ", utilisateur.getActiv());

        jdbcTemplate.update(UPDATE, namedParameters);
    }

    @Override
    public List<Utilisateur> readAll() {
        return List.of();
    }

    @Override
    public Utilisateur create(Utilisateur utilisateur) {
        createUtilisateur(utilisateur);
        return utilisateur;
    }

    @Override
    public void updateCreditByPseudo(Utilisateur utilisateur, int aRembourser) {

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("pseudo", utilisateur.getPseudo());
        namedParameters.addValue("credit", utilisateur.getCredit() + aRembourser);

        jdbcTemplate.update(UPDATE_CREDIT_BY_PSEUDO, namedParameters);

    }


}



