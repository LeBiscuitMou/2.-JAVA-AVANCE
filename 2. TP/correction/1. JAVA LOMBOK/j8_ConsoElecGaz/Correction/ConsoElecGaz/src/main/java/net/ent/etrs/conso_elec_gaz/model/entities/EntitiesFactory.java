package net.ent.etrs.conso_elec_gaz.model.entities;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.conso_elec_gaz.model.commons.validator.ValidException;
import net.ent.etrs.conso_elec_gaz.model.commons.validator.ValidatorUtils;
import net.ent.etrs.conso_elec_gaz.model.entities.references.CategorieConsomateur;
import net.ent.etrs.conso_elec_gaz.model.entities.references.Filiere;
import net.ent.etrs.conso_elec_gaz.model.entities.references.GrandSecteur;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EntitiesFactory {

    public static Region fabriquerRegion(Integer code, String libelle) throws ValidException {
        Region region = new Region();
        region.setCodeRegion(code);
        region.setLibelleRegion(libelle);
        ValidatorUtils.validate(region);
        return region;
    }

    public static ActivitePrincipale fabriquerActivitePrincipale(Integer codeNaf, String libelleActivite) throws ValidException {
        ActivitePrincipale activitePrincipale = new ActivitePrincipale();
        activitePrincipale.setCodeNAF(codeNaf);
        activitePrincipale.setLibelleActivite(libelleActivite);
        ValidatorUtils.validate(activitePrincipale);
        return activitePrincipale;
    }

    public static Operateur fabriquerOperateur(String nom, Filiere filiere) throws ValidException {
        Operateur operateur = new Operateur();
        operateur.setFiliere(filiere);
        operateur.setNom(nom);
        ValidatorUtils.validate(operateur);
        return operateur;

    }

    public static Consomation fabriquerConsomation(String numPDL, LocalDate date, Operateur operateur, GrandSecteur grandSecteur, Region region, Double consomation, CategorieConsomateur categorieConsomateur, ActivitePrincipale activitePrincipale) throws ValidException {
        Consomation conso = new Consomation();
        conso.setConsomation(consomation);
        conso.setDateAnnuelle(date);
        conso.setOperateur(operateur);
        conso.setSecteurActivite(grandSecteur);
        conso.setRegion(region);
        conso.setCategorieConsomateur(categorieConsomateur);
        conso.setNumPDL(numPDL);
        conso.setActivitePrincipale(activitePrincipale);
        ValidatorUtils.validate(conso);
        return conso;
    }
}
