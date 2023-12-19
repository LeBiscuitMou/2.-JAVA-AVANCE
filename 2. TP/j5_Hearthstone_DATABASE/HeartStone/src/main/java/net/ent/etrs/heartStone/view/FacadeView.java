package net.ent.etrs.heartStone.view;

import net.ent.etrs.heartStone.models.entities.Carte;
import net.ent.etrs.heartStone.models.references.Classe;
import net.ent.etrs.heartStone.models.references.TypeCarte;

import java.util.List;

public interface FacadeView {
    void afficherMessageErreur(String message);

    void afficherMessage(String message);

    List<String> afficherMenuPrincipal();

    int lectureChoix(int size);

    boolean lectureChoixBoolean();

    Carte saisirCarte();

    Classe choisirClasses();

    TypeCarte choisirTypeCarte();

    Carte choisirCarte(List<Carte> carteList);
}
