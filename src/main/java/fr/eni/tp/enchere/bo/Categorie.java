package fr.eni.tp.enchere.bo;

public class Categorie {
     private long id;
     private String libelle;

     public Categorie() {
     }

     public Categorie(String libelle) {
          this.libelle = libelle;
     }

     public Categorie(long id, String libelle) {
          this.id = id;
          this.libelle = libelle;
     }

     public long getId() {
          return id;
     }

     @Override
     public String toString() {
          return "Categorie{" +
                  "id=" + id +
                  ", libelle='" + libelle + '\'' +
                  '}';
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
