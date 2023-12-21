package net.ent.etrs.leagueJPA.models.facades.impl;

import net.ent.etrs.leagueJPA.models.entities.Challenge;
import net.ent.etrs.leagueJPA.models.entities.League;
import net.ent.etrs.leagueJPA.models.entities.Personnage;
import net.ent.etrs.leagueJPA.models.entities.references.LabySpecialite;
import net.ent.etrs.leagueJPA.models.facades.FacadeMetier;

import java.util.Map;
import java.util.Set;

public class FacadeMetierImpl implements FacadeMetier {

    /**
     * Permet de sauvegarder un personnage
     *
     * @param personnage le personnage à sauvegarder
     */
    @Override
    public void savePersonnage(Personnage personnage) {

    }

    /**
     * Permet de sauvegarder un challenge
     *
     * @param challenge le challenge à sauvegarder
     */
    @Override
    public void saveChallenge(Challenge challenge) {

    }

    /**
     * Permet de sauvegarder une League
     *
     * @param league la league à sauvegarder
     */
    @Override
    public void saveLeague(League league) {

    }

    /**
     * Permet de supprimer un personnage
     *
     * @param personnage le personnage à supprimer
     */
    @Override
    public void deletePersonnage(Personnage personnage) {

    }

    /**
     * Permet de supprimer un challenge
     *
     * @param challenge le challenge à supprimer
     */
    @Override
    public void deleteChallenge(Challenge challenge) {

    }

    /**
     * Permet de supprimer une League
     *
     * @param league la league à supprimer
     */
    @Override
    public void deleteLeague(League league) {

    }

    /**
     * Permet de récupérer tous les personnages
     *
     * @return les personnages de la base
     */
    @Override
    public Set<Personnage> findAllPersonnages() {
        return null;
    }

    /**
     * Permet de récupérer tous les challenges
     *
     * @return les challenges de la base
     */
    @Override
    public Set<Challenge> findAllChallenges() {
        return null;
    }

    /**
     * Permet de récupérer toutes les leagues
     *
     * @return les leagues de la base
     */
    @Override
    public Set<League> findAllLeagues() {
        return null;
    }

    /**
     * Permet de récupérer le challenge qui rapport le plus de points
     *
     * @return le challenge qui possède le plus de points
     */
    @Override
    public Challenge getBestChallenge() {
        return null;
    }

    /**
     * Permet de récupérer les rewards points par League
     *
     * @return une Map contenant la league et la somme de ses reward points.
     */
    @Override
    public Map<League, Integer> GetRewardPointsByLeague() {
        return null;
    }

    /**
     * Permet de récupérer les 3 meilleurs builds de la league
     *
     * @param league league choisie
     * @return un Set des trois meilleurs build
     */
    @Override
    public Set<LabySpecialite> topThreeBestBuild(League league) {
        return null;
    }

    /**
     * Permet de connaître les 3 meilleurs builds pour chaque League
     *
     * @return une Map contenant la league ainsi que les 3 meilleurs builds.
     */
    @Override
    public Map<League, Set<LabySpecialite>> bestBuildByLeague() {
        return null;
    }
}