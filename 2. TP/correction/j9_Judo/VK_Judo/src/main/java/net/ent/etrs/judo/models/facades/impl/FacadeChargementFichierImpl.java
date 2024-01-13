package net.ent.etrs.judo.models.facades.impl;

import net.ent.etrs.judo.models.dao.IDaoClub;
import net.ent.etrs.judo.models.dao.IDaoJudoka;
import net.ent.etrs.judo.models.dao.exceptions.DaoException;
import net.ent.etrs.judo.models.dao.impl.DaoFactory;
import net.ent.etrs.judo.models.entities.Club;
import net.ent.etrs.judo.models.entities.EntitiesFactory;
import net.ent.etrs.judo.models.entities.Judoka;
import net.ent.etrs.judo.models.entities.references.Grade;
import net.ent.etrs.judo.models.entities.references.Ville;
import net.ent.etrs.judo.models.exceptions.BusinessException;
import net.ent.etrs.judo.models.exceptions.ClubException;
import net.ent.etrs.judo.models.exceptions.EntitiesFactoryException;
import net.ent.etrs.judo.models.exceptions.GradeMinimumAgeException;
import net.ent.etrs.judo.models.facades.interfaces.FacadeChargementFichier;
import net.ent.etrs.judo.models.references.ConstMetier;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class FacadeChargementFichierImpl implements FacadeChargementFichier {
    private final IDaoClub daoClub;
    private final IDaoJudoka daoJudoka;

    protected FacadeChargementFichierImpl() {
        daoClub = DaoFactory.getDaoClub();
        daoJudoka = DaoFactory.getDaoJudoka();
    }

    @Override
    public void initialisation() throws BusinessException {
        // initialisation de l'error log
        StringBuilder ErrorMsg = new StringBuilder();

        try {
            Path fichierInit = Paths.get(Objects.requireNonNull(FacadeChargementFichierImpl.class.getResource("/").toURI()));
            Path errorLogFile = Files.createFile(Paths.get(Objects.requireNonNull(FacadeChargementFichierImpl.class.getResource("/ErrorLog.txt").toURI())));

            for (String line : Files.readAllLines(fichierInit)) {
                try {
                    String[] data = line.split(";");

                    String nom = data[0];
                    String prenom = data[1];
                    LocalDate dateDeNaissance = LocalDate.parse(data[2], ConstMetier.DATE_FORMAT);
                    int taille = Integer.parseInt(data[3]);
                    int poids = Integer.parseInt(data[4]);
                    boolean isFeminin = data[5].equals("F");
                    Grade grade = Grade.valueOf(data[6]);
                    String nomClub = data[7];
                    Ville villeClub = Ville.valueOf(data[8]);

                    Judoka judoka = daoJudoka.save(EntitiesFactory.fabriquerJudoka(nom, prenom, dateDeNaissance, taille, grade, poids, isFeminin));
                    createAndSaveClub(nomClub, villeClub, judoka);
                } catch (EntitiesFactoryException | ClubException | GradeMinimumAgeException | DaoException e) {
                    Files.writeString(errorLogFile, e.getMessage());
                    ErrorMsg.append("Erreur ligne : ").append(line).append('\n');
                }
            }
        } catch (URISyntaxException | IOException e) {
            throw new BusinessException(e);
        }

        if(!ErrorMsg.isEmpty()){
            throw new BusinessException(ErrorMsg.toString());
        }
    }

    private void createAndSaveClub(String nomClub, Ville villeClub, Judoka judoka) throws DaoException, ClubException, EntitiesFactoryException {
        Set<Judoka> judokaSet = new HashSet<>();
        judokaSet.add(judoka);

        // si l'instance existe dans la BDD, on la récupère, sinon on crée le club.
        Club club = daoClub.findClubByNomWithJudokas(nomClub)
                .orElse(EntitiesFactory.fabriquerClub(nomClub, villeClub, judokaSet));

        club.ajouterJudoka(judoka);
        daoClub.save(club);
    }


}
