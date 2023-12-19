package net.ent.etrs.heartStone.models.dao.jdbc.impl;

import net.ent.etrs.heartStone.models.dao.CarteDao;
import net.ent.etrs.heartStone.models.dao.exceptions.DaoException;
import net.ent.etrs.heartStone.models.dao.jdbc.JDBCBaseDao;
import net.ent.etrs.heartStone.models.entities.Carte;
import net.ent.etrs.heartStone.models.references.Classe;
import net.ent.etrs.heartStone.models.references.TypeCarte;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CarteDaoJDBCImpl extends JDBCBaseDao<Carte> implements CarteDao {
    @Override
    public Carte save(Carte entity) throws DaoException {
        PreparedStatement ps = getCnx().prepareStatement("INSERT INTO");
    }

    @Override
    public Optional<Carte> find(Long id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public Iterable<Carte> findAll() throws DaoException {
        return null;
    }

    @Override
    public void delete(Long id) throws DaoException {

    }

    @Override
    public boolean exists(Long id) throws DaoException {
        return false;
    }

    @Override
    public long count() {
        try {
            PreparedStatement ps = getCnx().prepareStatement("SELECT COUNT(id)" +
                                                                    "FROM TABLE(CARTE)");
            ResultSet rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Carte> getCarteByType(TypeCarte typeCarte) {
        return null;
    }

    @Override
    public List<Carte> getCarteByClasse(Classe classe) {
        return null;
    }

    @Override
    public Map<Month, List<Carte>> getCarteByMonth() {
        return null;
    }
}