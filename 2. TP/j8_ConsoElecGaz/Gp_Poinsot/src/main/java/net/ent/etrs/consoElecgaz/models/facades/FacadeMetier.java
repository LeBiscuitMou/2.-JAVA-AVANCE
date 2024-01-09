package net.ent.etrs.consoElecgaz.models.facades;

import net.ent.etrs.consoElecgaz.models.entities.ActivitePrincipale;
import net.ent.etrs.consoElecgaz.models.entities.Consomation;
import net.ent.etrs.consoElecgaz.models.entities.Operateur;
import net.ent.etrs.consoElecgaz.models.entities.Region;
import net.ent.etrs.consoElecgaz.models.entities.references.Filiere;
import net.ent.etrs.consoElecgaz.models.entities.references.GrandSecteur;
import net.ent.etrs.consoElecgaz.models.facades.exceptions.BusinessException;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface FacadeMetier {

    /**
     * Permet d'initialiser la base de données
     *
     * @throws BusinessException
     */
    void initialisation() throws BusinessException;

    Region sauvegarderRegion(Region region) throws BusinessException;

    List<Region> getAllRegions() throws BusinessException;

    Operateur sauvegarderOperateur(Operateur operateur) throws BusinessException;

    ActivitePrincipale sauvegarderActivitePrincipale(ActivitePrincipale activitePrincipale) throws BusinessException;

    Consomation sauvegarderConsomation(Consomation consomation) throws BusinessException;

    /**
     * Permet d'obtenir les consomation par secteur d'activité
     *
     * @param secteur le secteur choisi
     * @return la liste des consomations du secteur
     * @throws BusinessException
     */
    List<Consomation> consomationsBySecteur(GrandSecteur secteur) throws BusinessException;

    /**
     * Permet d'obtenir toutes les consomations avant la date indiquée
     *
     * @param date la date limite
     * @return la liste des consomation avant la date indiquée
     * @throws BusinessException
     */
    List<Consomation> consomationsBeforeDate(LocalDate date) throws BusinessException;

    /**
     * Permet d'obtenir la liste des opérateurs d'une filière
     *
     * @param filiere la filière a selectionner
     * @return la liste les opérateur de la filière
     * @throws BusinessException
     */
    List<Operateur> operateurByFiliere(Filiere filiere) throws BusinessException;

    /**
     * Permet d'obtenir la liste des consomations pour les Région concernées avant la date saisie
     *
     * @param lesRegions la liste des région concernées
     * @param date       la date limite
     * @return lma liste des consomations pour les régions avant la date saisie
     * @throws BusinessException
     */
    List<Consomation> consomationsByRegionsAndBeforeDate(List<Region> lesRegions, LocalDate date) throws BusinessException;

    /**
     * Permet d'obtenir la liste des consomations fournie par l'opérateur après la date saisie
     *
     * @param operateur l'opérateur choisi
     * @param date      la date minimum
     * @return la liste des consomations pour l'opérateur après la date saisie
     */
    List<Consomation> consomationsByOperateurAndAfterDate(Operateur operateur, LocalDate date) throws BusinessException;

    /**
     * Permet d'obtenir pour une région concernée, la somme des consomations cumulées (Elec + Gaz) avant la date saisie
     *
     * @param region la région choisie
     * @param date   avant la date saisie
     * @return une MAP contenant la somme des consomations cumulées (Elec + Gaz) à la date
     */
    Map<LocalDate, Double> sumConsomationsForRegionAndBeforeDate(Region region, LocalDate date) throws BusinessException;

    /**
     * Permet d'obtenir pour une région concernée, la somme des consomations séparées par Filiere avant la date saisie
     *
     * @param region la région choisie
     * @param date   avant la date saisie
     * @return une MAP contenant la somme des consomations cumulées par filière avant la date
     */
    Map<Filiere, Map<LocalDate, Double>> sumConsomationsByFiliereForRegionAndBeforeDate(Region region, LocalDate date) throws BusinessException;

}
