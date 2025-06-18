package fr.eni.tp.enchere.bll;

import fr.eni.tp.enchere.bo.Adresse;
import fr.eni.tp.enchere.bo.Utilisateur;
import fr.eni.tp.enchere.dal.AdresseDAO;
import fr.eni.tp.enchere.dal.UtilisateursDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@Primary
public class UtilisateurServiceImpl implements UtilisateurService {
@Autowired
    private UtilisateursDAO utilisateursDAO;
@Autowired
    private AdresseDAO adresseDAO;



    public UtilisateurServiceImpl(AdresseDAO adresseDAO, UtilisateursDAO utilisateursDAO) {
        this.adresseDAO = adresseDAO;
        this.utilisateursDAO = utilisateursDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var utilisateur = utilisateursDAO.readUtilisateurByEmail(email);

        if (utilisateur == null)
            throw new UsernameNotFoundException("User not found");

        return utilisateur;
    }



    @Override
    public List<Utilisateur> getAll() {
        return utilisateursDAO.readAll();
    }

    @Override
    public Utilisateur get(String pseudo) {
        return utilisateursDAO.readUtilisateurByPseudo(pseudo);
    }

    @Override
    public void create(Utilisateur utilisateur) {

        adresseDAO.createAdresse(utilisateur.getAdresse());
        var idAdresse = utilisateur.getAdresse().getId();
        utilisateur.getAdresse().setId(idAdresse);
        utilisateursDAO.createUtilisateur(utilisateur);


    }

    @Override
    public void update(Utilisateur utilisateur) {
        utilisateursDAO.updateUtilisateur(utilisateur);
    }


    @Override
    public void delete(String pseudo) {
        utilisateursDAO.deleteUtilisateur(pseudo);
    }

    @Override
    public Utilisateur registerNewUser(Utilisateur utilisateur) {
        return utilisateursDAO.create(utilisateur);
    }

    @Override
    public Adresse create(Adresse adresse) {
       return adresseDAO.createAdresse(adresse);
    }

    @Override
    public Adresse getAdresseById(long id) {
        return adresseDAO.readAdresseByID(id);
    }

    @Override
    public Utilisateur findByPseudo(String pseudo) {
        return  utilisateursDAO.readUtilisateurByPseudo(pseudo);
    }


}
