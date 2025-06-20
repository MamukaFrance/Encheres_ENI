package fr.eni.tp.enchere.bo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Utilisateur implements UserDetails {
    private String pseudo;
    private String nom;
    private String prenom;
    private Collection<? extends GrantedAuthority> authorities = Collections.emptyList();
    private String email;
    private String telephone;
    private String motDePasse;
    private int credit = 10;
    private boolean admin;
    private Adresse adresse;
    private Boolean isActiv = true;

    public Utilisateur(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.email = username;
        this.motDePasse = password;
        this.authorities = authorities;
    }

    public Utilisateur() {
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public Boolean getActiv() {
        return isActiv;
    }

    public void setActiv(Boolean activ) {
        isActiv = activ;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (!this.isActiv) {
            // Utilisateur inactif, pas de rôle
            return Collections.emptyList();
        }
        if (this.admin) {
            return Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public boolean isEnabled() {
        return this.isActiv;
    }


    @Override
    public String getPassword() {
        return motDePasse;
    }

    @Override
    public String getUsername() {
        return email;
    }

}
