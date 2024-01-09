package net.ent.etrs.championnathockey.start;

import net.ent.etrs.championnathockey.commons.validator.ValidException;
import net.ent.etrs.championnathockey.models.daos.DaoFactory;
import net.ent.etrs.championnathockey.models.daos.DaoJoueur;
import net.ent.etrs.championnathockey.models.entities.EntitiesFactory;
import net.ent.etrs.championnathockey.models.entities.Equipe;
import net.ent.etrs.championnathockey.models.entities.Joueur;
import net.ent.etrs.championnathockey.models.entities.references.Ville;
import net.ent.etrs.championnathockey.models.facades.exceptions.BusinessException;
import net.ent.etrs.championnathockey.models.facades.impl.FacadeChampionnatImpl;
import net.ent.etrs.championnathockey.models.facades.impl.FacadeEquipeImpl;
import net.ent.etrs.championnathockey.models.facades.impl.FacadeJoueurImpl;
import net.ent.etrs.championnathockey.presenteur.Presenteur;

import java.util.Map;

public class Lanceur {
    public static void main(String[] args) {


        try {
            Presenteur p = new Presenteur();

        } catch (Exception ex) {
            System.out.println("ERROR");
        }
    }
}
