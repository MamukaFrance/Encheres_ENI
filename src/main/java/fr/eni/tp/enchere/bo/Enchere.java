package fr.eni.tp.enchere.bo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Enchere {
    private LocalDateTime date;
    private int montant;

    private Utilisateur acquereur;
    private ArticleAVendre articleAVendre;

    public Enchere() {
    }


    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public Utilisateur getAcquereur() {
        return acquereur;
    }

    public void setAcquereur(Utilisateur acquereur) {
        this.acquereur = acquereur;
    }

    public ArticleAVendre getArticleAVendre() {
        return articleAVendre;
    }

    public void setArticleAVendre(ArticleAVendre articleAVendre) {
        this.articleAVendre = articleAVendre;
    }
}
