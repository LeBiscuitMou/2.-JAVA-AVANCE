package net.ent.etrs.projet.models.entities;

import net.ent.etrs.projet.models.entities.exceptions.CyclisteException;
import net.ent.etrs.projet.models.entities.exceptions.PersonneException;
import net.ent.etrs.projet.models.entities.references.ConstantesMetier;
import net.ent.etrs.projet.models.entities.references.TypeSpecialite;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Cycliste extends Personne {
    private TypeSpecialite specialite;
    private Map<Epreuve, Integer> palmares = new HashMap<>();

    public Cycliste(String nom, String prenom, LocalDate dateNaissance) throws PersonneException {
        super(nom, prenom, dateNaissance);
    }

    public TypeSpecialite getSpecialite() {
        return specialite;
    }

    public void setSpecialite(TypeSpecialite specialite) throws CyclisteException {
        if (Objects.isNull(specialite)) {
            throw new CyclisteException(ConstantesMetier.CYCLISTE_SPECIALITE_IS_NULL);
        }
        this.specialite = specialite;
    }

    @Override
    public String toString() {
        return "Cycliste{" +
                "specialite=" + specialite +
                '}';
    }

    public int calculerAge() {
        return LocalDate.now().getYear() - super.getDateNaissance().getYear();
    }

    public int calculerFrequenceCardiaqueMax() {
        return 220 - calculerAge();
    }

    public void modifierPalmares(Epreuve e, int classement) throws CyclisteException {
        if (Objects.isNull(e)) {
            throw new CyclisteException(ConstantesMetier.CYCLISTE_EPREUVE_IS_NULL);
        }
        if (classement < 0) {
            throw new CyclisteException(ConstantesMetier.CYCLISTE_CLASSEMENT_IS_NEGATIVE);
        }
        palmares.put(e, classement);
    }
}