package net.ent.etrs.cycliste.model.entities;

import net.ent.etrs.cycliste.model.entities.exceptions.CyclisteRuntimeException;
import net.ent.etrs.cycliste.model.entities.references.TypeSpecialite;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Cycliste extends Personne {

    private TypeSpecialite specialite;
    
    private Map<Epreuve, Integer> palmares = new HashMap<>();
    
    protected Cycliste() {}
    
    public Cycliste(String nom, String prenom, LocalDate dateNaissance) throws Exception {
        super(nom, prenom, dateNaissance);
    }
    
    public TypeSpecialite getSpecialite() {
        return specialite;
    }
    
    public void setSpecialite(TypeSpecialite specialite) throws Exception {
        if (Objects.isNull(specialite)) throw new Exception("specialite is null");
        this.specialite = specialite;
    }
    
    public Map<Epreuve, Integer> getPalmares() {
        return Collections.unmodifiableMap(this.palmares);
    }
    
    public void modifierPalmares(Epreuve epreuve, int classement) {
        if (Objects.isNull(epreuve)) throw new CyclisteRuntimeException("epreuve is null");
        if (classement < 1) throw new CyclisteRuntimeException("classement mut be positive");
        this.palmares.put(epreuve, classement);
    }
    
    public int calculerAge() {
        return Period.between(this.getDateNaissance(), LocalDate.now()).getYears();
    }
    
    public int calculerFrequenceCardiaqueMax() {
        return 220 - this.calculerAge();
    }
    
    @Override
    public String toString() {
        return "Cycliste{" +
                "specialite=" + specialite +
                "} " + super.toString();
    }
}
