package net.ent.etrs.projetjavamaier.models.facade;




import net.ent.etrs.projetjavamaier.models.entities.Console;
import net.ent.etrs.projetjavamaier.models.entities.Fabriquant;
import net.ent.etrs.projetjavamaier.models.entities.JeuVideo;
import net.ent.etrs.projetjavamaier.models.entities.references.Genre;
import net.ent.etrs.projetjavamaier.models.entities.references.Pays;
import net.ent.etrs.projetjavamaier.models.facade.exceptions.BusinessException;

import java.util.List;
import java.util.Map;

public interface IFacade {

    /**
     * Permet de sauvegarder un jeu video
     * @param jv le jeu vidfeo à sauvegarder
     * @return le jeu video sauvegarder
     */
    JeuVideo sauvergarderJeuVideo(JeuVideo jv) throws BusinessException;

    /**
     * Permet de sauvegarder un fabriquant
     * @param fabriquant le fabriquant à sauvegarder
     * @return le fabriquant sauvegarder
     */
    Fabriquant sauvegarderFabriquant(Fabriquant fabriquant) throws BusinessException;

    /**
     * Permet de sauvegarder une console
     * @param console la console à sauvegarder
     * @return la console sauvegarder
     */
    Console sauvegarderConsole(Console console) throws BusinessException;

    /**
     * Permet de récupérer tous les fabriquant
     * @return la liste de tous les fabriquant
     */
    List<Fabriquant> recupererlesFabriquants() throws BusinessException;

    /**
     * Permet de récupérer toutes les consoles
     * @return la liste de toutes les console
     */
    List<Console> recupererlesConsoles() throws BusinessException;

    /**
     * Permet de récuperer tous les jeux video
     * @return la liste de tous les jeux video
     */
    List<JeuVideo> recupererJeuxVideo() throws BusinessException;

    /**
     * Permet de récuperer tous les jeux video du genre choisi
     * @param genre le genre choisi
     * @return la liste des jeux video de ce genre
     */
    List<JeuVideo> recupererJeuxVideoParGenre(Genre genre) throws BusinessException;

    /**
     * Permet de récuperer tous les jeux video dont le nom commence par : et dont le fabriquant est du pays selectionner
     * @param debutNom début du nom du jeu video
     * @param paysFabriquant pays du fabriquant
     * @return la liste des jeux video répondant aux filtres
     */
    List<JeuVideo> recupererJeuxVideoDontLeNomCommenceParEtLeFabriquantEstDeTelPays(String debutNom, Pays paysFabriquant) throws BusinessException;

    /**
     * Permet de récuperer la console qui est la plus vieille pour un pays
     * @param pays le pays choisi
     * @return la console la plus vieille pour le pays
     */
    Console recupererLaConsoleDontLaSortieEstlaPlusAnciennePourUnPays(Pays pays) throws BusinessException;

    /**
     * Permet de récuperer un dictionnaire contenant toutes les consoles avec leur jeux video
     * @return une map contenant toutes les consoles avec leur jeux video
     */
    Map<Console,List<JeuVideo>> recupererConsoleAvecLeurJeuxVideo() throws BusinessException;

    /**
     * Permet d'initialisaer les données du logiciel
     */
    void initialisation() throws BusinessException;


    /**
     * Permet de renvoyer une chaîne de caractère au format suivant : X années, Y mois, Z jours arrondi au jours près supérieur
     * donnant la durée moyenne entre 2 sortie de jeux vidéo pour une console
     * @param console la console choisie
     * @return une chaine de caractère qui donne la durée moyenne entre 2 sortie de jeux vidéo pour une console
     */
    String recupererDureeMoyenneSortieEntreJeuVideoPourUneConsole(Console console) throws BusinessException;


}
