package net.ent.etrs.projetjavamaier.models.facade;

import net.ent.etrs.projetjavamaier.models.entities.Console;
import net.ent.etrs.projetjavamaier.models.entities.JeuVideo;
import net.ent.etrs.projetjavamaier.models.entities.references.Pays;
import net.ent.etrs.projetjavamaier.models.facade.base.FacadeMetier;
import net.ent.etrs.projetjavamaier.models.facade.exceptions.BusinessException;

import java.util.List;
import java.util.Map;

public interface ConsoleFacade extends FacadeMetier<Console> {
    Console recupererLaConsoleDontLaSortieEstlaPlusAnciennePourUnPays(Pays pays) throws BusinessException;

    /**
     * Permet de récuperer un dictionnaire contenant toutes les consoles avec leur jeux video
     * @return une map contenant toutes les consoles avec leur jeux video
     */
    Map<Console, List<JeuVideo>> recupererConsoleAvecLeurJeuxVideo() throws BusinessException;
    String recupererDureeMoyenneSortieEntreJeuVideoPourUneConsole(Console console) throws BusinessException;
}