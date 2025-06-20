package fr.eni.tp.enchere.controller;

import fr.eni.tp.enchere.bll.AdminService;import fr.eni.tp.enchere.bll.UtilisateurService;
import fr.eni.tp.enchere.bo.Utilisateur;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/profil")
public class UtilisateurController {
    private UtilisateurService utilisateurService;
    private AdminService adminService;

   public UtilisateurController(UtilisateurService utilisateurService, AdminService adminService) {
    this.utilisateurService = utilisateurService;
    this.adminService = adminService;
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
        var idAdresse =utilisateurConnecte.getAdresse().getId();
        var adresse = utilisateurService.getAdresseById(idAdresse);

        model.addAttribute("profil", utilisateurConnecte);
        model.addAttribute("adresse", adresse);

        return "view-profil";
    }

    @PostMapping("/sauvegarder")
    public String sauvegarderProfil(@ModelAttribute("profil") Utilisateur utilisateur) {
        System.out.println(utilisateur);
        utilisateurService.create(utilisateur);
        return "redirect:/profil";
    }

    @GetMapping("/change-password")
    public String changePassword() {
        return "view-changerMotDePasse";
    }

    @GetMapping("/vendeur")
    public String profilVendeur(Model model, @RequestParam(name = "vendeur") String pseudoVendeur) {
        // Récupération du vendeur via son pseudo
        Utilisateur vendeur = utilisateurService.findByPseudo(pseudoVendeur);
        if (vendeur == null) {
            // gérer le cas où le vendeur n'existe pas, par ex. redirection ou message d'erreur
            return "vendeur-not-found";
        }
        model.addAttribute("vendeur", vendeur);
        return "view-profilVendeur";
    }


    @PostMapping("/vendeur")
    public String gererCompte(
            @RequestParam("pseudo") String pseudoVendeur,
            @RequestParam("action") String action) {

        Utilisateur vendeur = utilisateurService.findByPseudo(pseudoVendeur);

        switch(action) {
            case "desactiver":
                vendeur.setActiv(false);
                adminService.desactiverCompte(vendeur);
                break;
            case "activer":
                vendeur.setActiv(true);
                adminService.desactiverCompte(vendeur);
                break;
            case "supprimer":
                adminService.supprimerCompte(vendeur);
                break;
            default:
                // gérer le cas inattendu
                break;
        }

        return "redirect:/";
    }


}

