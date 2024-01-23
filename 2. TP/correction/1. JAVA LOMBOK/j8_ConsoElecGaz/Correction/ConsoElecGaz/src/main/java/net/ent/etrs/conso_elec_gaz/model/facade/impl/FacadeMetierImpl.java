package net.ent.etrs.conso_elec_gaz.model.facade.impl;

import net.ent.etrs.conso_elec_gaz.model.commons.validator.ValidException;
import net.ent.etrs.conso_elec_gaz.model.daos.*;
import net.ent.etrs.conso_elec_gaz.model.daos.exception.DaoException;
import net.ent.etrs.conso_elec_gaz.model.entities.*;
import net.ent.etrs.conso_elec_gaz.model.entities.references.CategorieConsomateur;
import net.ent.etrs.conso_elec_gaz.model.entities.references.Filiere;
import net.ent.etrs.conso_elec_gaz.model.entities.references.GrandSecteur;
import net.ent.etrs.conso_elec_gaz.model.facade.FacadeMetier;
import net.ent.etrs.conso_elec_gaz.model.facade.exception.BusinessException;
import org.apache.commons.collections4.IterableUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class FacadeMetierImpl implements FacadeMetier {

    private DaoOperateur daoOperateur;

    private DaoRegion daoRegion;

    private DaoConsomation daoConsomation;
    private DaoActivitePrincipale daoActivitePrincipale;

    public FacadeMetierImpl() {
        daoConsomation = DaoFactory.getDaoConsomation();
        daoOperateur = DaoFactory.getDaoOperateur();
        daoRegion = DaoFactory.getDaoRegion();
        daoActivitePrincipale = DaoFactory.getDaoActivitePrincipale();
    }

    @Override
    public void initialisation() throws BusinessException {
        initJPA();
    }

    private void initJPA() throws BusinessException {
        try {
            if (daoConsomation.count() > 0) {
                System.out.println("Initialisation déjà faite");
            } else {
                Path path = Path.of(FacadeMetierImpl.class.getResource("/conso-elec-gaz-annuelle-par-naf-agregee-region.csv").toURI());
                for (String line : Files.readAllLines(path)) {

                    String[] lineDecoupe = line.split(";");

                    Region r = EntitiesFactory.fabriquerRegion(lineDecoupe[11].contains("XX") ? 0 : Integer.parseInt(lineDecoupe[11]), lineDecoupe[12]);
                    Region regionExist = daoRegion.findByCode(r.getCodeRegion());
                    if (null == regionExist) {
                        r = sauvegarderRegion(r);
                    } else {
                        r = regionExist;
                    }

                    Operateur o = EntitiesFactory.fabriquerOperateur(lineDecoupe[0], Filiere.valueOf(lineDecoupe[2].toUpperCase()));
                    Operateur opeExist = daoOperateur.findByNomAndFiliere(o.getNom(), o.getFiliere());
                    if (null == opeExist) {
                        o = sauvegarderOperateur(o);
                    } else {
                        o = opeExist;
                    }
                    ActivitePrincipale activitePrincipale = null;
                    if (!lineDecoupe[7].isEmpty()) {
                        activitePrincipale = EntitiesFactory.fabriquerActivitePrincipale(Integer.parseInt(lineDecoupe[7]), lineDecoupe[8]);
                        ActivitePrincipale activitePrincipaleExist = daoActivitePrincipale.findByCodeNAF(activitePrincipale.getCodeNAF());
                        if (null == activitePrincipaleExist) {
                            activitePrincipale = sauvegarderActivitePrincipale(activitePrincipale);
                        } else {
                            activitePrincipale = activitePrincipaleExist;
                        }
                    }

                    Consomation c = EntitiesFactory.fabriquerConsomation(lineDecoupe[10]
                            , LocalDate.of(Integer.parseInt(lineDecoupe[1]), 01, 01)
                            , o
                            , GrandSecteur.valueOf(lineDecoupe[5])
                            , r
                            , Double.parseDouble(lineDecoupe[9])
                            , CategorieConsomateur.valueOf(lineDecoupe[3])
                            , activitePrincipale);

                    daoConsomation.save(c);
                }
            }
            } catch(URISyntaxException | IOException | ValidException | DaoException e){
                throw new BusinessException(e);
            }
    }


    @Override
    public Region sauvegarderRegion(Region region) throws BusinessException {
        try {
            return daoRegion.save(region);
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }

    @Override
    public List<Region> getAllRegions() throws BusinessException {
        try {
            return IterableUtils.toList(daoRegion.findAll());
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public Operateur sauvegarderOperateur(Operateur operateur) throws BusinessException {
        try {
            return daoOperateur.save(operateur);
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }

    @Override
    public ActivitePrincipale sauvegarderActivitePrincipale(ActivitePrincipale activitePrincipale) throws BusinessException {
        try {
            return daoActivitePrincipale.save(activitePrincipale);
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }

    @Override
    public Consomation sauvegarderConsomation(Consomation consomation) throws BusinessException {
        try {
            return daoConsomation.save(consomation);
        } catch (DaoException e) {
            throw new BusinessException(e);
        }
    }

    /**
     * Permet d'obtenir les consomation par secteur d'activité
     *
     * @param secteur le secteur choisi
     * @return la liste des consomations du secteur
     * @throws BusinessException
     */
    @Override
    public List<Consomation> consomationsBySecteur(GrandSecteur secteur) throws BusinessException {
        try {
            return daoConsomation.consomationsBySecteur(secteur);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    /**
     * Permet d'obtenir toutes les consomations avant la date indiquée
     *
     * @param date la date limite
     * @return la liste des consomation avant la date indiquée
     * @throws BusinessException
     */
    @Override
    public List<Consomation> consomationsBeforeDate(LocalDate date) throws BusinessException {
        try {
            return daoConsomation.consomationsBeforeDate(date);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    /**
     * Permet d'obtenir la liste des opérateurs d'une filière
     *
     * @param filiere la filière a selectionner
     * @return la liste les opérateur de la filière
     * @throws BusinessException
     */
    @Override
    public List<Operateur> operateurByFiliere(Filiere filiere) throws BusinessException {
        try {
            return daoOperateur.getOperateurByFiliere(filiere);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    /**
     * Permet d'obtenir la liste des consomations pour les Région concernées avant la date saisie
     *
     * @param lesRegions la liste des région concernées
     * @param date       la date limite
     * @return lma liste des consomations pour les régions avant la date saisie
     * @throws BusinessException
     */
    @Override
    public List<Consomation> consomationsByRegionsAndBeforeDate(List<Region> lesRegions, LocalDate date) throws BusinessException {
        try {
            return daoConsomation.consomationsByRegionsAndBeforeDate(lesRegions,date);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    /**
     * Permet d'obtenir la liste des consomations fournie par l'opérateur après la date saisie
     *
     * @param operateur l'opérateur choisi
     * @param date      la date minimum
     * @return la liste des consomations pour l'opérateur après la date saisie
     */
    @Override
    public List<Consomation> consomationsByOperateurAndAfterDate(Operateur operateur, LocalDate date) throws BusinessException {
        try {
            return daoConsomation.consomationsByOperateurAndAfterDate(operateur,date);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    /**
     * Permet d'obtenir pour une région concernée, la somme des consomations cumulées (Elec + Gaz) avant la date saisie
     *
     * @param region la région choisie
     * @param date   avant la date saisie
     * @return une MAP contenant la somme des consomations cumulées (Elec + Gaz) à la date
     */
    @Override
    public Map<LocalDate, Double> sumConsomationsForRegionAndBeforeDate(Region region, LocalDate date) throws BusinessException {
        try {
            return daoConsomation.sumConsomationsForRegionAndBeforeDate(region, date);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    /**
     * Permet d'obtenir pour une région concernée, la somme des consomations séparées par Filiere avant la date saisie
     *
     * @param region la région choisie
     * @param date   avant la date saisie
     * @return une MAP contenant la somme des consomations cumulées par filière avant la date
     */
    @Override
    public Map<Filiere, Map<LocalDate, Double>> sumConsomationsByFiliereForRegionAndBeforeDate(Region region, LocalDate date) throws BusinessException {
        try {
            return daoConsomation.sumConsomationsByFiliereForRegionAndBeforeDate(region,date);
        } catch (DaoException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }
}
