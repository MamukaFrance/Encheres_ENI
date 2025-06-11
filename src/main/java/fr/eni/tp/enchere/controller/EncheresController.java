package fr.eni.tp.enchere.controller;

import fr.eni.tp.enchere.bll.EncheresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class EncheresController {

   private final EncheresService encheresService;

  @Autowired
  public EncheresController(EncheresService encheresService) {
      this.encheresService = encheresService;
   }

    @GetMapping
    public String consulterEncheres() {
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
