package fr.eni.tp.enchere.bo;

public class Categorie {
     private long id;
     private String libelle;

     public Categorie() {
     }

     public long getId() {
          return id;
     }

     public void setId(long id) {
          this.id = id;
     }

     public String getLibelle() {
          return libelle;
     }

     public void setLibelle(String libelle) {
          this.libelle = libelle;
     }
}
