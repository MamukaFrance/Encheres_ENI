package fr.eni.tp.enchere.controller;

import fr.eni.tp.enchere.bll.AdminService;
import fr.eni.tp.enchere.bll.EncheresService;
import fr.eni.tp.enchere.bll.UtilisateurService;
import fr.eni.tp.enchere.bo.Utilisateur;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {
    private UtilisateurService utilisateurService;
    private EncheresService encheresService;
   private AdminService adminService;

    public AdminController(UtilisateurService utilisateurService, EncheresService encheresService, AdminService adminService) {
       this.utilisateurService = utilisateurService;
        this.encheresService = encheresService;
        this.adminService = adminService;
    }


    @GetMapping("/admin-profil")
    public String afficherProfil(@RequestParam String pseudo, Model model) {

        Utilisateur utilisateur = utilisateurService.get(pseudo);

        model.addAttribute("profil", utilisateur);
        model.addAttribute("adresse", utilisateur.getAdresse());

        return "view-profil";
    }

    @PostMapping("/disable")
    public String desactiverUtilisateur(@ModelAttribute("profil") Utilisateur utilisateur) {
        adminService.desactiverCompte(utilisateur);
        return "redirect: /admin/profil";
    }

}