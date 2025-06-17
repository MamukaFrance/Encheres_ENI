package fr.eni.tp.enchere.controller;

import fr.eni.tp.enchere.bll.UtilisateurService;
import fr.eni.tp.enchere.bo.Adresse;
import fr.eni.tp.enchere.bo.Utilisateur;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/profil")
public class UtilisateurController {
    private UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping
    public String afficherProfil(Model model, Authentication authentication) {
        Utilisateur utilisateurConnecte = null;
        if (authentication != null) {
            var principal = authentication.getPrincipal();

            if (principal != null && principal instanceof Utilisateur) {
                utilisateurConnecte = (Utilisateur) principal;
            }
        }
        model.addAttribute("profil", utilisateurConnecte);
        model.addAttribute("adresse", utilisateurConnecte.getAdresse());

        return "view-profil";
    }

    @PostMapping("/sauvegarder")
    public String sauvegarderProfil(@ModelAttribute("profil")Utilisateur utilisateur) {
        System.out.println(utilisateur);
        utilisateurService.create(utilisateur);
        return "redirect:/profil";
    }







}

