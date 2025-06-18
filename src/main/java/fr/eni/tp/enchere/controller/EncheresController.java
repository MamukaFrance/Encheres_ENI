package fr.eni.tp.enchere.controller;

import fr.eni.tp.enchere.bll.EncheresService;
import fr.eni.tp.enchere.bll.UtilisateurServiceImpl;
import fr.eni.tp.enchere.bo.ArticleAVendre;
import fr.eni.tp.enchere.bo.Enchere;
import fr.eni.tp.enchere.bo.Utilisateur;
import fr.eni.tp.enchere.dal.ArticleAVendreDAO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller

public class EncheresController {


    private final EncheresService encheresService;

    @Autowired
    public EncheresController(EncheresService encheresService) {
        this.encheresService = encheresService;
    }

    // Sur la page d'accueil on demande la liste des encheres en cours, mais on veut en
    // faite la liste des objets a vendre!!(consulter enchere mais on affiche une liste d'objet a vendre)
    @GetMapping
    public String consulterEncheres(Model model) {
        List<ArticleAVendre> articleAVendres = this.encheresService.consulterEncheres();
        model.addAttribute("articles", articleAVendres);
        model.addAttribute("categories", encheresService.getCategories());
        return "view-listeEncheres";
    }

    @GetMapping("/enchere")
    public String voirEnchere() {
        return "view-enchere";
    }

    @GetMapping("/nouvelle-vente")
    public String nouvelleVente(Model model) {
        model.addAttribute("articleAvendre", new ArticleAVendre());
        model.addAttribute("categories", encheresService.getCategories());
        model.addAttribute("adresses", encheresService.getAdresses());
        return "view-nouvelleVente";
    }

    @PostMapping("/nouvelle-vente")
    public String nouvelleVente(@Valid @ModelAttribute("articleAvendre") ArticleAVendre articleAvendre, BindingResult result, Model model, Authentication authentication) {
        Utilisateur personneConnecte = null;
        var principal = authentication.getPrincipal();
        personneConnecte = (Utilisateur) principal;

        articleAvendre.setVendeur(personneConnecte);
        encheresService.nouvelleVente(articleAvendre);
// on doit rajouter le vendeur avec le pseudo
 var nouvelleid=articleAvendre.getId();

        return "redirect:/detail-vente?id="+nouvelleid;
    }

    @GetMapping("/vente-remportee")
    public String venteRemportee() {
        return "view-venteRemportee";
    }

    @GetMapping("/detail-vente")
    public String detailVente(@RequestParam(name = "id", required = true) long id, Model model) {


        ArticleAVendre articleAVendre = this.encheresService.voirEnchere(id);
        model.addAttribute("categories", encheresService.getCategoriesById(articleAVendre.getCategorie().getId()));
        model.addAttribute("adresses", encheresService.getAdresseById(articleAVendre.getRetrait().getId()));
        model.addAttribute("dateDebutEncheres", articleAVendre.getDateDebutEncheres());
        model.addAttribute("dateFinEncheres", articleAVendre.getDateFinEncheres());

        model.addAttribute("articleAVendre", articleAVendre);
        return "view-detailVente";

    }


    @GetMapping("ajouter_photo")
    public String ajouterPhoto() {
        return "view-ajouterPhoto";
    }

    @GetMapping("/change-password")
    public String changePassword() {
        return "view-changerMotDePasse";
    }
}
