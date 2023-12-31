package net.ent.etrs.leagueJPA.models.facades;

import net.ent.etrs.leagueJPA.models.entities.Challenge;
import net.ent.etrs.leagueJPA.models.entities.League;
import net.ent.etrs.leagueJPA.models.entities.Personnage;
import net.ent.etrs.leagueJPA.models.entities.references.LabySpecialite;
import net.ent.etrs.leagueJPA.models.facades.exception.BusinessException;

import java.util.Map;
import java.util.Set;

public interface FacadeMetier {

    /**
     * Permet de sauvegarder un personnage
     *
     * @param personnage le personnage à sauvegarder
     */
    void savePersonnage(Personnage personnage) throws BusinessException;

    /**
     * Permet de sauvegarder un challenge
     *
     * @param challenge le challenge à sauvegarder
     */
    void saveChallenge(Challenge challenge) throws BusinessException;

    /**
     * Permet de sauvegarder une League
     *
     * @param league la league à sauvegarder
     */
    void saveLeague(League league) throws BusinessException;

    /**
     * Permet de supprimer un personnage
     *
     * @param personnage le personnage à supprimer
     */
    void deletePersonnage(Personnage personnage) throws BusinessException;

    /**
     * Permet de supprimer un challenge
     *
     * @param challenge le challenge à supprimer
     */
    void deleteChallenge(Challenge challenge) throws BusinessException;

    /**
     * Permet de supprimer une League
     *
     * @param league la league à supprimer
     */
    void deleteLeague(League league) throws BusinessException;

    /**
     * Permet de récupérer tous les personnages
     *
     * @return les personnages de la base
     */
    Set<Personnage> findAllPersonnages() throws BusinessException;

    /**
     * Permet de récupérer tous les challenges
     *
     * @return les challenges de la base
     */
    Set<Challenge> findAllChallenges() throws BusinessException;

    /**
     * Permet de récupérer toutes les leagues
     *
     * @return les leagues de la base
     */
    Set<League> findAllLeagues() throws BusinessException;

    /**
     * Permet de récupérer le challenge qui rapport le plus de points
     *
     * @return le challenge qui possède le plus de points
     */
    Challenge getBestChallenge() throws BusinessException;

    /**
     * Permet de récupérer les rewards points par League
     *
     * @return une Map contenant la league et la somme de ses reward points.
     */
    Map<League, Long> GetRewardPointsByLeague() throws BusinessException;

    /**
     * Permet de récupérer les 3 meilleurs builds de la league
     *
     * @param league league choisie
     * @return un Set des trois meilleurs build
     */
    Set<LabySpecialite> topThreeBestBuild(League league);

    /**
     * Permet de connaître les 3 meilleurs builds pour chaque League
     *
     * @return une Map contenant la league ainsi que les 3 meilleurs builds.
     */
    Map<League, Set<LabySpecialite>> bestBuildByLeague();

}
