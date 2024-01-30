package net.ent.etrs.gestion_jeuvideo.models.facades.impl;

import net.ent.etrs.gestion_jeuvideo.models.commons.CoUtils;
import net.ent.etrs.gestion_jeuvideo.models.dao.IDaoJeuVideo;
import net.ent.etrs.gestion_jeuvideo.models.dao.exceptions.DaoException;
import net.ent.etrs.gestion_jeuvideo.models.dao.impl.DaoFactory;
import net.ent.etrs.gestion_jeuvideo.models.entities.Console;
import net.ent.etrs.gestion_jeuvideo.models.entities.JeuVideo;
import net.ent.etrs.gestion_jeuvideo.models.entities.references.Genre;
import net.ent.etrs.gestion_jeuvideo.models.entities.references.Pays;
import net.ent.etrs.gestion_jeuvideo.models.exceptions.BusinessException;
import net.ent.etrs.gestion_jeuvideo.models.facades.interfaces.IFacadeJeuVideo;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Locale;

public class FacadeJeuVideoImpl extends GenericFacadeImpl<JeuVideo> implements IFacadeJeuVideo {
    private final IDaoJeuVideo daoJeuVideo;

    protected FacadeJeuVideoImpl() {
        super(DaoFactory.getDaoJeuVideo());
        daoJeuVideo = DaoFactory.getDaoJeuVideo();
    }

    /**
     * Permet de sauvegarder un jeu video.
     *
     * @param jv le jeu video à sauvegarder
     * @return le jeu video sauvegarder
     * @throws BusinessException l'exception lancée
     */
    @Override
    public JeuVideo sauvergarderJeuVideo(JeuVideo jv) throws BusinessException {
        return super.save(jv);
    }

    /**
     * Permet de récupérer tous les jeux video.
     *
     * @return la liste de tous les jeux video
     * @throws BusinessException l'exception lancée
     */
    @Override
    public List<JeuVideo> recupererJeuxVideo() throws BusinessException {
        return CoUtils.iterableToList(super.findAll());
    }

    /**
     * Permet de récupérer tous les jeux video du genre choisi.
     *
     * @param genre le genre choisi
     * @return la liste des jeux video de ce genre
     * @throws BusinessException l'exception lancée
     */
    @Override
    public List<JeuVideo> recupererJeuxVideoParGenre(Genre genre) throws BusinessException {
        try {
            return daoJeuVideo.recupererJeuxVideoParGenre(genre);
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }

    /**
     * Permet de récupérer tous les jeux video dont le nom commence par : et dont le fabricant est du pays sélectionné.
     *
     * @param debutNom       début du nom du jeu video
     * @param paysFabriquant pays du fabricant
     * @return la liste des jeux video répondant aux filtres
     * @throws BusinessException l'exception lancée
     */
    @Override
    public List<JeuVideo> recupererJeuxVideoDontLeNomCommenceParEtLeFabriquantEstDeTelPays(String debutNom, Pays paysFabriquant) throws BusinessException {
        try {
            return daoJeuVideo.recupererJeuxVideoDontLeNomCommenceParEtLeFabriquantEstDeTelPays(debutNom, paysFabriquant);
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }

    /**
     * Permet de renvoyer une chaîne de caractère au format suivant : X années, Y mois, Z jours arrondi au jour près supérieur
     * donnant la durée moyenne entre 2 sortie de jeux vidéo pour une console.
     *
     * @param console la console choisie
     * @return une chaine de caractère qui donne la durée moyenne entre 2 sortie de jeux vidéo pour une console
     * @throws BusinessException l'exception lancée
     */
    @Override
    public String recupererDureeMoyenneSortieEntreJeuVideoPourUneConsole(Console console) throws BusinessException {
        try {
            List<JeuVideo> jeuVideoList = daoJeuVideo.recupererJeuVideoSortieSurConsole(console);

            // si liste vide, pas de jeux sorti, impossible de calculer la moyenne
            if (jeuVideoList.isEmpty()){
                return "Impossible de calculer la durée moyenne entre deux sorties car aucun jeu n'est sortie sur cette console";
            }
            // si seulement 1 élément dans la liste, juste 1 jeu sorti, impossible de calculer la moyenne
            if(jeuVideoList.size() == 1){
                return "Impossible de calculer la durée moyenne entre deux sorties car seulement un jeu est sortie sur cette console";
            }

            // initialisation
            Period result = getPeriodEntreDeuxSortiesDeJeux(jeuVideoList);
            return String.format("%d années, %d mois, %d jours", result.getYears(), result.getMonths(), result.getDays());
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }

    private static Period getPeriodEntreDeuxSortiesDeJeux(List<JeuVideo> jeuVideoList) {
        LocalDate premierSortie = LocalDate.of(2030, 12, 30);
        LocalDate dernierSortie = LocalDate.of(1980, 1, 1);

        for(JeuVideo jeu : jeuVideoList){
            // on cherche la date du premier jeu sortie.
            if (jeu.getDateSortie().isBefore(premierSortie)){
                premierSortie = jeu.getDateSortie();
            }

            // on cherche la date du dernier jeu sortie.
            if (jeu.getDateSortie().isAfter(dernierSortie)){
                dernierSortie = jeu.getDateSortie();
            }
        }

        // nombre de jours entre la 1ère et la dernière sortie.
        long daysBetween = ChronoUnit.DAYS.between(premierSortie, dernierSortie);
        long moyenne = (daysBetween / (jeuVideoList.size() - 1));

        return LocalDate.now().until(LocalDate.now().plusDays(moyenne));
    }
}