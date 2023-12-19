package net.ent.etrs.heartStone.models.dao.jdbc.impl;

import net.ent.etrs.heartStone.models.dao.CarteDao;
import net.ent.etrs.heartStone.models.dao.exceptions.DaoException;
import net.ent.etrs.heartStone.models.dao.jdbc.JDBCBaseDao;
import net.ent.etrs.heartStone.models.entities.Carte;
import net.ent.etrs.heartStone.models.entities.EntitiesFactory;
import net.ent.etrs.heartStone.models.references.Classe;
import net.ent.etrs.heartStone.models.references.TypeCarte;

import java.sql.*;
import java.sql.Date;
import java.time.Month;
import java.util.*;

public class CarteDaoJDBCImpl extends JDBCBaseDao<Carte> implements CarteDao {
    @Override
    public Carte save(Carte entity) {
        try {
            PreparedStatement ps = getCnx().prepareStatement("INSERT INTO CARTE" +
                                                                    " VALUES(?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, entity.getId());
            ps.setString(2, entity.getNom());
            ps.setInt(3, entity.getCout());
            ps.setDate(4, Date.valueOf(entity.getDateSortie()));
            ps.setString(5, String.valueOf(entity.getTypeCarte()));
            ps.setString(6, String.valueOf(entity.getClasse()));

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entity;
    }

    @Override
    public Optional<Carte> find(Long id) {
        PreparedStatement ps = null;
        try {
            ps = getCnx().prepareStatement("SELECT id, nom, cout, date_sortie, type_carte, classe" +
                                                " FROM TABLE(CARTE)" +
                                                " WHERE id=" + id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Carte c = EntitiesFactory.fabriquerCarte(rs.getString("nom"),
                                                            rs.getInt("cout"),
                                                            rs.getDate("date_sortie").toLocalDate(),
                                                            TypeCarte.valueOf(rs.getString("type_carte").toUpperCase()),
                                                            Classe.valueOf(rs.getString("classe").toUpperCase()),
                                                            new HashSet<>());
                return Optional.of(c);
            } else {
                System.err.println("Aucun résultat");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Iterable<Carte> findAll() {
        List<Carte> cartes = new ArrayList<>();

        try {
            PreparedStatement ps = getCnx().prepareStatement("SELECT id, nom, cout, date_sortie, type_carte, classe" +
                                                                    "FROM TABLE(CARTE)");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Carte c = EntitiesFactory.fabriquerCarte(rs.getString("nom"),
                                                            rs.getInt("cout"),
                                                            rs.getDate("date_sortie").toLocalDate(),
                                                            TypeCarte.valueOf(rs.getString("type_carte").toUpperCase()),
                                                            Classe.valueOf(rs.getString("classe").toUpperCase()),
                                                            new HashSet<>());

                cartes.add(c);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return cartes;
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

            if (rs.next()) {
                return rs.getLong(1);
            } else {
                System.err.println("Aucun résultat");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
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