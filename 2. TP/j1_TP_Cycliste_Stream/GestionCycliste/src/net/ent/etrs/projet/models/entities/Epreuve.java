package net.ent.etrs.projet.models.entities;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

public class Epreuve {
    private UUID id;
    private String nom;
    private LocalDate dateDebut;
    private LocalDate dateFin;

    public Epreuve(String nom, LocalDate dateDebut, LocalDate dateFin) {
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public UUID getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Epreuve epreuve = (Epreuve) o;
        return Objects.equals(id, epreuve.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Epreuve{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                '}';
    }
}