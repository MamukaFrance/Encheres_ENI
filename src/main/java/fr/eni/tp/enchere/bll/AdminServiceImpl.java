package fr.eni.tp.enchere.bll;

import fr.eni.tp.enchere.bo.Enchere;
import fr.eni.tp.enchere.bo.Utilisateur;
import fr.eni.tp.enchere.dal.AdresseDAO;
import fr.eni.tp.enchere.dal.EnchereDAO;
import fr.eni.tp.enchere.dal.UtilisateursDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Primary
@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    private UtilisateursDAO utilisateursDAO;
    @Autowired
    private EnchereDAO enchereDAO;

        public AdminServiceImpl(UtilisateursDAO utilisateursDAO, EnchereDAO enchereDAO) {
            this.utilisateursDAO = utilisateursDAO;
            this.enchereDAO = enchereDAO;
        }

    @Override
    public List<Utilisateur> getAll() {
        return utilisateursDAO.readAll();
    }

    @Override
    public List<Enchere> getEncheresByUser(Utilisateur utilisateur) {
        return enchereDAO.readAll();
    }

    @Override
    public Utilisateur get(String pseudo) {
        return null;
    }

    @Override
    public void desactiverCompte(Utilisateur utilisateur) {

    }

    @Override
    public void supprimerCompte(Utilisateur utilisateur) {

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
