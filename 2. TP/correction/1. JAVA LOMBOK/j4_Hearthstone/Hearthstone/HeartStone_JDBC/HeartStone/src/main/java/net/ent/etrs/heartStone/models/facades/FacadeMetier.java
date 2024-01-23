package net.ent.etrs.heartStone.models.facades;

import net.ent.etrs.heartStone.models.entities.Carte;
import net.ent.etrs.heartStone.models.facades.exception.BusinessException;
import net.ent.etrs.heartStone.models.references.Classe;
import net.ent.etrs.heartStone.models.references.TypeCarte;

import java.time.Month;
import java.util.List;
import java.util.Map;

public interface FacadeMetier {
    Carte sauvegarderCarte(Carte carte) throws BusinessException;

    List<Carte> getLesCarte() throws BusinessException;

    List<Carte> getCarteByType(TypeCarte typeCarte);

    List<Carte> getCarteByClasse(Classe classe);

    Map<Month, List<Carte>> getCarteByMonth();

    void supprimerCarte(Carte carte) throws BusinessException;
}
