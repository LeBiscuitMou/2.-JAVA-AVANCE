package net.ent.etrs.gestion_jeuvideo.models.facades;



import net.ent.etrs.gestion_jeuvideo.models.entities.Console;
import net.ent.etrs.gestion_jeuvideo.models.entities.Fabriquant;
import net.ent.etrs.gestion_jeuvideo.models.entities.JeuVideo;
import net.ent.etrs.gestion_jeuvideo.models.entities.references.Genre;
import net.ent.etrs.gestion_jeuvideo.models.entities.references.Pays;
import net.ent.etrs.gestion_jeuvideo.models.exceptions.BusinessException;

import java.util.List;
import java.util.Map;

public interface FacadeMetier {

    /**
     * Permet de sauvegarder un jeu video.
     *
     * @param jv le jeu video à sauvegarder
     * @return le jeu video sauvegarder
     * @throws BusinessException l'exception lancée
     */
    JeuVideo sauvergarderJeuVideo(JeuVideo jv) throws BusinessException;

    /**
     * Permet de sauvegarder un fabriquant.
     *
     * @param fabriquant le fabricant à sauvegarder
     * @return le fabricant sauvegarder
     * @throws BusinessException l'exception lancée
     */
    Fabriquant sauvegarderFabriquant(Fabriquant fabriquant) throws BusinessException;

    /**
     * Permet de sauvegarder une console.
     *
     * @param console la console à sauvegarder
     * @return la console sauvegarder
     * @throws BusinessException l'exception lancée
     */
    Console sauvegarderConsole(Console console) throws BusinessException;

    /**
     * Permet de récupérer tous les fabricants.
     *
     * @return la liste de tous les fabriquant
     * @throws BusinessException l'exception lancée
     */
    List<Fabriquant> recupererlesFabriquants() throws BusinessException;

    /**
     * Permet de récupérer toutes les consoles.
     *
     * @return la liste de toutes les console
     * @throws BusinessException l'exception lancée
     */
    List<Console> recupererlesConsoles() throws BusinessException;

    /**
     * Permet de récupérer tous les jeux video.
     *
     * @return la liste de tous les jeux video
     * @throws BusinessException l'exception lancée
     */
    List<JeuVideo> recupererJeuxVideo() throws BusinessException;

    /**
     * Permet de récupérer tous les jeux video du genre choisi.
     *
     * @param genre le genre choisi
     * @return la liste des jeux video de ce genre
     * @throws BusinessException l'exception lancée
     */
    List<JeuVideo> recupererJeuxVideoParGenre(Genre genre) throws BusinessException;

    /**
     * Permet de récupérer tous les jeux video dont le nom commence par : et dont le fabricant est du pays sélectionné.
     *
     * @param debutNom début du nom du jeu video
     * @param paysFabriquant pays du fabricant
     * @return la liste des jeux video répondant aux filtres
     * @throws BusinessException l'exception lancée
     */
    List<JeuVideo> recupererJeuxVideoDontLeNomCommenceParEtLeFabriquantEstDeTelPays(String debutNom, Pays paysFabriquant) throws BusinessException;

    /**
     * Permet de récupérer la console qui est la plus vieille pour un pays.
     *
     * @param pays le pays choisi
     * @return la console la plus vieille pour le pays
     * @throws BusinessException l'exception lancée
     */
    Console recupererLaConsoleDontLaSortieEstlaPlusAnciennePourUnPays(Pays pays) throws BusinessException;

    /**
     * Permet de récupérer un dictionnaire contenant toutes les consoles avec leurs jeux video.
     *
     * @return une map contenant toutes les consoles avec leurs jeux video
     * @throws BusinessException l'exception lancée
     */
    Map<Console,List<JeuVideo>> recupererConsoleAvecLeurJeuxVideo() throws BusinessException;

    /**
     * Permet d'initialiser les données du logiciel.
     *
     * @throws BusinessException l'exception lancée
     */
    void initialisation() throws BusinessException;


    /**
     * Permet de renvoyer une chaîne de caractère au format suivant : X années, Y mois, Z jours arrondi au jour près supérieur
     * donnant la durée moyenne entre 2 sortie de jeux vidéo pour une console.
     *
     * @param console la console choisie
     * @return une chaine de caractère qui donne la durée moyenne entre 2 sortie de jeux vidéo pour une console
     * @throws BusinessException l'exception lancée
     */
    String recupererDureeMoyenneSortieEntreJeuVideoPourUneConsole(Console console) throws BusinessException;


}
