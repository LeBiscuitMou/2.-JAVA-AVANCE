package net.ent.etrs.cycliste.model.entities;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class Personne {
    
    private String nom;
    
    private String prenom;
    
    private LocalDate dateNaissance;
    
    protected Personne() {}
    
    public Personne(String nom, String prenom, LocalDate dateNaissance) throws Exception {
        this.setNom(nom);
        this.setPrenom(prenom);
        this.setDateNaissance(dateNaissance);
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) throws Exception {
        if (Objects.isNull(nom)) throw new Exception("nom is null");
        this.nom = nom.toUpperCase();
    }
    
    public String getPrenom() {
        return prenom;
    }
    
    public void setPrenom(String prenom) throws Exception {
        if (Objects.isNull(prenom)) throw new Exception("prenom is null");
        this.prenom = prenom.substring(0, 1).toUpperCase() + prenom.substring(1).toLowerCase();
    }
    
    public LocalDate getDateNaissance() {
        return dateNaissance;
    }
    
    public void setDateNaissance(LocalDate dateNaissance) throws Exception {
        if (Objects.isNull(dateNaissance)) throw new Exception("dateNaissance is null");
        if (Period.between(dateNaissance, LocalDate.now()).getYears() < 18) throw new Exception("Doit être majeur");
        this.dateNaissance = dateNaissance;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Personne personne)) return false;
        return Objects.equals(getNom(), personne.getNom()) && Objects.equals(getPrenom(), personne.getPrenom()) && Objects.equals(getDateNaissance(), personne.getDateNaissance());
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(getNom(), getPrenom(), getDateNaissance());
    }
    
    @Override
    public String toString() {
        return "Personne{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", dateNaissance=" + dateNaissance +
                '}';
    }
}
