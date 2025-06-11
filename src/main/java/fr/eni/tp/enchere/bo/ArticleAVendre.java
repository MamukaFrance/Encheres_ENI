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

    public ArticleAVendre() {
    }

    public ArticleAVendre(String nom, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres, int statut, int prixInitial, int prixVente, Adresse retrait, Utilisateur vendeur, Categorie categorie) {
        this.nom = nom;
        this.description = description;
        this.dateDebutEncheres = dateDebutEncheres;
        this.dateFinEncheres = dateFinEncheres;
        this.statut = statut;
        this.prixInitial = prixInitial;
        this.prixVente = prixVente;
        this.retrait = retrait;
        this.vendeur = vendeur;
        this.categorie = categorie;
    }

    public ArticleAVendre(long id, String nom, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres, int statut, int prixInitial, int prixVente, Adresse retrait, Utilisateur vendeur, Categorie categorie) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.dateDebutEncheres = dateDebutEncheres;
        this.dateFinEncheres = dateFinEncheres;
        this.statut = statut;
        this.prixInitial = prixInitial;
        this.prixVente = prixVente;
        this.retrait = retrait;
        this.vendeur = vendeur;
        this.categorie = categorie;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateDebutEncheres() {
        return dateDebutEncheres;
    }

    public void setDateDebutEncheres(LocalDate dateDebutEncheres) {
        this.dateDebutEncheres = dateDebutEncheres;
    }

    public LocalDate getDateFinEncheres() {
        return dateFinEncheres;
    }

    public void setDateFinEncheres(LocalDate dateFinEncheres) {
        this.dateFinEncheres = dateFinEncheres;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }

    public int getPrixInitial() {
        return prixInitial;
    }

    public void setPrixInitial(int prixInitial) {
        this.prixInitial = prixInitial;
    }

    public int getPrixVente() {
        return prixVente;
    }

    public void setPrixVente(int prixVente) {
        this.prixVente = prixVente;
    }

    public Adresse getRetrait() {
        return retrait;
    }

    public void setRetrait(Adresse retrait) {
        this.retrait = retrait;
    }

    public Utilisateur getVendeur() {
        return vendeur;
    }

    public void setVendeur(Utilisateur vendeur) {
        this.vendeur = vendeur;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "ArticleAVendre{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", dateDebutEncheres=" + dateDebutEncheres +
                ", dateFinEncheres=" + dateFinEncheres +
                ", statut=" + statut +
                ", prixInitial=" + prixInitial +
                ", prixVente=" + prixVente +
                ", retrait=" + retrait +
                ", vendeur=" + vendeur +
                ", categorie=" + categorie +
                '}';
    }
}
