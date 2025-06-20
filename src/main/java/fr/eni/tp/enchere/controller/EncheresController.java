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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

@Controller

public class EncheresController {

    private static final String UPLOAD_REPERTOIRE = "upload/";

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
    public String nouvelleVente(@Valid @ModelAttribute("articleAvendre") ArticleAVendre articleAvendre, BindingResult result, Model model, Authentication authentication, @RequestParam("action") String action) {
        Utilisateur personneConnecte = null;
        var principal = authentication.getPrincipal();
        personneConnecte = (Utilisateur) principal;
        articleAvendre.setVendeur(personneConnecte);
        articleAvendre.setPrixVente(articleAvendre.getPrixInitial());
        encheresService.nouvelleVente(articleAvendre);
        Long nouvelId = articleAvendre.getId();
        if ("ajoutPhoto".equals(action)) {
            return "redirect:/ajouter_photo?id=" + nouvelId;
        } else if ("enregistrerArticle".equals(action)) {
            return "redirect:/";
        }
        return "redirect:/nouvelle-vente"; // fallback}}
    }

        @GetMapping("/vente-remportee")
        public String venteRemportee () {
            return "view-venteRemportee";
        }

        @GetMapping("/detail-vente")
        public String detailVente ( @RequestParam(name = "id", required = true) long id, Model model){
            ArticleAVendre articleAVendre = this.encheresService.voirEnchere(id);
            var enchere = new Enchere();
            enchere.setMontant(articleAVendre.getPrixVente() + 1);

            model.addAttribute("enchere", enchere);
            model.addAttribute("idArticle", id);
            model.addAttribute("categories", encheresService.getCategoriesById(articleAVendre.getCategorie().getId()));
            model.addAttribute("adresses", encheresService.getAdresseById(articleAVendre.getRetrait().getId()));
            model.addAttribute("dateDebutEncheres", articleAVendre.getDateDebutEncheres());
            model.addAttribute("dateFinEncheres", articleAVendre.getDateFinEncheres());
            model.addAttribute("articleAVendre", articleAVendre);
            model.addAttribute("enchereur", encheresService.voirutilisateurparrapportalidarticle(id));

            String imagePath = null;
            String uploadRepertoire = "src/main/resources/static/images/upload/";
            String[] extensions = {".jpg", ".png", ".jpeg"};

            for (String ext : extensions) {
                File imgFile = new File(uploadRepertoire + id + ext);
                if (imgFile.exists()) {
                    imagePath = "images/upload/" + id + ext;
                    break;
                }
            }

            model.addAttribute("imagePath", imagePath);  // peut être null si pas d'image

            return "view-detailVente";
        }

        @PostMapping("/detail-vente")
        public String detailVente (@Valid @ModelAttribute("enchere") Enchere enchere, BindingResult result, Model
        model, Authentication authentication,@RequestParam(name = "idArticle") long idArticle, RedirectAttributes
        redirectAttributes){
            Utilisateur personneConnecte = null;
            var principal = authentication.getPrincipal();
            personneConnecte = (Utilisateur) principal;

            enchere.setAcquereur(personneConnecte);
            ArticleAVendre articleAVendre = encheresService.voirEnchere(idArticle);
            enchere.setArticleAVendre(articleAVendre);
            enchere.setDate(LocalDateTime.now());

            if (personneConnecte.getCredit() > enchere.getMontant()) {
                encheresService.creerEnchere(enchere);
                redirectAttributes.addFlashAttribute("success", "Enchère placée avec succès!");
            } else {
                redirectAttributes.addFlashAttribute("error", "Crédit insuffisant pour enchérir.");
            }

            return "redirect:/detail-vente?id=" + idArticle;
        }

        @GetMapping("ajouter_photo")
        public String ajouterPhoto ( @RequestParam(name = "id", required = true) long id, Model model){
            ArticleAVendre articleAVendre = this.encheresService.voirEnchere(id);
            model.addAttribute("articleAVendre", articleAVendre);

            return "view-ajouterPhoto";
        }

        @PostMapping("ajouter_photo")
        public String ajouterPhoto (@RequestParam("photo") MultipartFile file,
                @RequestParam("articleId") Long articleId,
                RedirectAttributes redirectAttributes) throws IOException {

            if (file.isEmpty()) {
                redirectAttributes.addFlashAttribute("message", "Veuillez sélectionner une image.");
                return "redirect:/ajouter_photo?id=" + articleId;
            }

            String uploadRepertoire = "src/main/resources/static/images/upload/";
            String nomDuFichier = file.getOriginalFilename();
            String extension = nomDuFichier.substring(nomDuFichier.lastIndexOf("."));
            String nouveauNomFichier = articleId + extension;

            // 1. Enregistrer le fichier sur le disque
            Files.write(Paths.get(uploadRepertoire + nouveauNomFichier), file.getBytes());


            // 2. Enregistrer le chemin en base
            String cheminFichier = "images/upload/" + nouveauNomFichier;
            ArticleAVendre article = encheresService.voirEnchere(articleId);
            article.setPhoto(cheminFichier);
            encheresService.ajouterPhoto(article);

            redirectAttributes.addFlashAttribute("message", "Photo enregistrée avec succès !");
            return "redirect:/detail-vente?id=" + articleId;
        }


        @GetMapping("/change-password")
        public String changePassword () {
            return "view-changerMotDePasse";
        }
    }
