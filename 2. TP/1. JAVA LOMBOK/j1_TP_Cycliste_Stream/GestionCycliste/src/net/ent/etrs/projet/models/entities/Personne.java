package net.ent.etrs.projet.models.entities;

import net.ent.etrs.projet.models.entities.exceptions.PersonneException;
import net.ent.etrs.projet.models.entities.references.ConstantesMetier;

import java.time.LocalDate;
import java.util.Objects;

public class Personne {
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;

    public Personne(String nom, String prenom, LocalDate dateNaissance) throws PersonneException {
        this.setNom(nom.toUpperCase());
        this.setPrenom(prenom);
        this.setDateNaissance(dateNaissance);
    }

    public String getNom() {
        return nom.toUpperCase();
    }

    private void setNom(String nom) throws PersonneException {
        if (Objects.isNull(nom)) {
            throw new PersonneException(ConstantesMetier.PERSONNE_NOM_IS_NULL);
        }
        if (nom.isBlank()) {
            throw new PersonneException(ConstantesMetier.PERSONNE_NOM_IS_BLANK);
        }
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    private void setPrenom(String prenom) throws PersonneException {
        if (Objects.isNull(prenom)) {
            throw new PersonneException(ConstantesMetier.PERSONNE_PRENOM_IS_NULL);
        }
        if (prenom.isBlank()) {
            throw new PersonneException(ConstantesMetier.PERSONNE_PRENOM_IS_BLANK);
        }
        this.prenom = prenom;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) throws PersonneException {
        if (Objects.isNull(dateNaissance)) {
            throw new PersonneException(ConstantesMetier.PERSONNE_DATE_NAISSANCE_IS_NULL);
        }
        if (dateNaissance.isAfter(LocalDate.now())) {
            throw new PersonneException(ConstantesMetier.PERSONNE_DATE_NAISSANCE_FUTUR);
        }
        if (dateNaissance.getYear() >= ConstantesMetier.ANNEE_MINIMUM) {
            throw new PersonneException(ConstantesMetier.PERSONNE_DATE_NAISSANCE_NON_MAJEUR);
        }
        this.dateNaissance = dateNaissance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Personne personne = (Personne) o;
        return Objects.equals(nom, personne.nom) && Objects.equals(prenom, personne.prenom) && Objects.equals(dateNaissance, personne.dateNaissance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, prenom, dateNaissance);
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