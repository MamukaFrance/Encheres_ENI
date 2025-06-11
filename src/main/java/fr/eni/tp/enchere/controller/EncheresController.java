package fr.eni.tp.enchere.controller;

import fr.eni.tp.enchere.bll.EncheresService;
import fr.eni.tp.enchere.bo.ArticleAVendre;
import fr.eni.tp.enchere.bo.Enchere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

        List<ArticleAVendre> listArticleavendre = this.encheresService.consulterEncheres();
        model.addAttribute("articles", listArticleavendre);

        return "view-listeEncheres";
    }

    @GetMapping("/enchere")
    public String voirEnchere() {
        return "view-enchere";
    }

    @GetMapping("/nouvelle-vente")
    public String nouvelleVente() {
        return "view-nouvelleVente";
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
