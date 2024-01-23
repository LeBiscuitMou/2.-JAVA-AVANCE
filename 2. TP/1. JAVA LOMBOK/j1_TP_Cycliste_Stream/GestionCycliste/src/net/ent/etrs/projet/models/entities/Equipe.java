package net.ent.etrs.projet.models.entities;

import net.ent.etrs.projet.models.entities.exceptions.EquipeException;
import net.ent.etrs.projet.models.entities.references.ConstantesMetier;

import java.util.*;

public class Equipe {
    private String nom;
    private Personne entraineur;
    private List<Cycliste> cyclistes = new ArrayList<>();

    public Equipe(String nom) throws EquipeException {
        this.setNom(nom);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) throws EquipeException {
        if (Objects.isNull(nom)) {
            throw new EquipeException(ConstantesMetier.EQUIPE_NOM_IS_NULL);
        }
        if (nom.isBlank()) {
            throw new EquipeException(ConstantesMetier.EQUIPE_NOM_IS_BLANK);
        }
        this.nom = nom;
    }

    public Personne getEntraineur() {
        return entraineur;
    }

    public void setEntraineur(Personne entraineur) throws EquipeException {
        if (Objects.isNull(entraineur)) {
            throw new EquipeException(ConstantesMetier.EQUIPE_ENTRAINEUR_IS_NULL);
        }
        this.entraineur = entraineur;
    }

    public List<Cycliste> getCyclistes() {
        return Collections.unmodifiableList(cyclistes);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipe equipe = (Equipe) o;
        return Objects.equals(nom, equipe.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom);
    }

    @Override
    public String toString() {
        return "Equipe{" +
                "nom='" + nom + '\'' +
                ", entraineur=" + entraineur +
                '}';
    }

    public void ajouterCycliste(Cycliste cycliste) throws EquipeException {
        if (Objects.isNull(cycliste)) {
            throw new EquipeException(ConstantesMetier.EQUIPE_NOM_IS_NULL);
        }
        this.cyclistes.add(cycliste);
    }
}