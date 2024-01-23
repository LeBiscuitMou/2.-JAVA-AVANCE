package net.ent.etrs.cycliste.model.entities;

import java.util.*;

public class Equipe {
    
    private String nom;
    
    private Personne entraineur;
    
    private Set<Cycliste> cyclistes = new HashSet<>();
    
    protected Equipe() {}
    
    public Equipe(String nom) throws Exception {
        this.setNom(nom);
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) throws Exception {
        if (Objects.isNull(nom)) throw new Exception("nom is null");
        this.nom = nom.toUpperCase();
    }
    
    public Personne getEntraineur() {
        return entraineur;
    }
    
    public void setEntraineur(Personne entraineur) throws Exception {
        if (Objects.isNull(entraineur)) throw new Exception("entraineur is null");
        this.entraineur = entraineur;
    }
    
    public Collection<Cycliste> getCyclistes() {
        return Collections.unmodifiableCollection(this.cyclistes);
    }
    
    public void ajouterCycliste(final Cycliste cycliste) throws Exception {
        if (Objects.isNull(cycliste)) throw new Exception("cycliste is null");
        if (this.cyclistes.contains(cycliste)) throw new Exception("cycliste already in");;
        this.cyclistes.add(cycliste);
    }
    
}
