package net.ent.etrs.cycliste.model.entities;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Epreuve {
    
    private UUID id = UUID.randomUUID();
    
    private String nom;
    
    private LocalDate dateDebut;
    
    private LocalDate dateFin;
    
    protected Epreuve() {
    }
    
    public Epreuve(String nom, LocalDate dateDebut, LocalDate dateFin) throws Exception {
        this.setNom(nom);
        this.setDateDebut(dateDebut);
        this.setDateFin(dateFin);
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) throws Exception {
        if (Objects.isNull(nom)) throw new Exception("nom is null");
        this.nom = nom;
    }
    
    public LocalDate getDateDebut() {
        return dateDebut;
    }
    
    public void setDateDebut(LocalDate dateDebut) throws Exception {
        if (Objects.isNull(dateDebut)) throw new Exception("dateDebut is null");
        this.dateDebut = dateDebut;
    }
    
    public LocalDate getDateFin() {
        return dateFin;
    }
    
    public void setDateFin(LocalDate dateFin) throws Exception {
        if (Objects.isNull(dateFin)) throw new Exception("dateFin is null");
        if ( dateFin.isBefore(this.dateDebut)) throw new Exception("dateFin is before to dateDebut");
        this.dateFin = dateFin;
    }
}
