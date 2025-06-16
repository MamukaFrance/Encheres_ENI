package fr.eni.tp.enchere.bll;

import fr.eni.tp.enchere.bo.Utilisateur;
import fr.eni.tp.enchere.dal.UtilisateursDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {
    private UtilisateursDAO utilisateursDAO;

    public UtilisateurServiceImpl(UtilisateursDAO utilisateursDAO) {
        this.utilisateursDAO = utilisateursDAO;
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
        utilisateursDAO.create(utilisateur);

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
        return null;
    }

}
