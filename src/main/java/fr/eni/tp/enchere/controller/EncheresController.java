package fr.eni.tp.enchere.controller;

import fr.eni.tp.enchere.bll.EncheresService;
import fr.eni.tp.enchere.bo.ArticleAVendre;
import fr.eni.tp.enchere.bo.Enchere;
import fr.eni.tp.enchere.dal.ArticleAVendreDAO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String nouvelleVente(@ModelAttribute("articleAvendre") ArticleAVendre articleAvendre) {

               // @ModelAttribute("membreEnSession") Membre membreEnSession)

        encheresService.nouvelleVente(articleAvendre);

        return "redirect:/detail-vente";
    }

    @GetMapping("/vente-remportee")
    public String venteRemportee() {
        return "view-venteRemportee";
    }

    @GetMapping("detail-vente")
    public String detailVente() {
        return "view-detailVente";
    }

    @GetMapping("ajouter-photo")
    public String ajouterPhoto() {
        return "view-ajouterPhoto";
    }
}
