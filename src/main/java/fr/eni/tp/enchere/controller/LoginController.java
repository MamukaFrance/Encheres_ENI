package fr.eni.tp.enchere.controller;

import fr.eni.tp.enchere.bll.UtilisateurService;
import fr.eni.tp.enchere.bll.UtilisateurServiceImpl;
import fr.eni.tp.enchere.bo.Adresse;
import fr.eni.tp.enchere.bo.Utilisateur;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    protected final Log logger = LogFactory.getLog(getClass());
    private final UtilisateurService utilisateurService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public LoginController(UtilisateurService utilisateurService, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, UtilisateurServiceImpl utilisateurServiceImpl) {
        this.utilisateurService = utilisateurService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    String login() {
        logger.info("Affichage du formulaire de login");
        return "login";
    }

    @GetMapping("/register")
    public String registerGet(Model model) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setAdresse(new Adresse());
        model.addAttribute("profil", utilisateur);
        return "view-nouveauProfil";
    }

    // Traiter la soumission du formulaire
    @PostMapping("/register")
    public String registerPost(@ModelAttribute Utilisateur profil, Model model, HttpServletRequest request) {
        var password = profil.getPassword(); // je save le mdp en clair car besoin plus bas
        profil.setMotDePasse(passwordEncoder.encode(password));

        // Étape 1 : sauvegarder l'adresse et récupérer l'objet avec ID
        Adresse adresseEnregistree = utilisateurService.create(profil.getAdresse());
        profil.setAdresse(adresseEnregistree); // très important !


        utilisateurService.registerNewUser(profil);


        // 3. Authentifier via HttpServletRequest
        try {
            request.login(profil.getUsername(), password);
        } catch (ServletException e) {
            // Gérer l'erreur (par exemple, mot de passe incorrect)
            return "redirect:/register?error";
        }

        // 2. Authentifier automatiquement l'utilisateur
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        profil.getUsername(),
                        password // la c'est le mdp en clair qu'il nous faut
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "redirect:/";
    }



}
