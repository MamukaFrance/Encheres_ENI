package fr.eni.tp.enchere.bo;

import java.time.LocalDate;

public class ArticleAVendre {
    private long id;
    private String nom;
    private String description;
    private LocalDate dateDebutEncheres;
    private LocalDate dateFinEncheres;
    private int statut;
    private int prixInitial;
    private int prixVente;

    private Adresse retrait;
    private Utilisateur vendeur;
    private Categorie categorie;

}
