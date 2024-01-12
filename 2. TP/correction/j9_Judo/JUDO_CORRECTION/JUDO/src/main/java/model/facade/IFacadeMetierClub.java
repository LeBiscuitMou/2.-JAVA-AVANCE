package model.facade;

import model.entities.Club;
import model.entities.references.Ville;
import model.facade.exceptions.ClubException;

import java.util.List;
import java.util.Optional;

public interface IFacadeMetierClub {

    Club saveClub(final Club pClub) throws ClubException;

    Optional<Club> findClub(final Long pId) throws ClubException;

    List<Club> findAllClub() throws ClubException;

    void deleteClub(final Long pId) throws ClubException;

    Optional<Club> findClubByNomWithJudokas(final String pName) throws ClubException;

    List<Club> findAllClubByVille(final Ville pVille) throws ClubException;

}
