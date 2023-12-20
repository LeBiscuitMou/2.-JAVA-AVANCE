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
    PreparedStatement ps = null;

    @Override
    public Carte save(Carte entity) throws DaoException, SQLException {
        List<String> columnNames = new ArrayList<>();
        columnNames.add("nom");
        columnNames.add("cout");
        columnNames.add("datesortie");
        columnNames.add("typecarte");
        columnNames.add("classe");

        List<Object> columnValues = new ArrayList<>();
        columnValues.add(entity.getNom());
        columnValues.add(entity.getCout());
        columnValues.add(Date.valueOf(entity.getDateSortie()));
        columnValues.add(String.valueOf(entity.getTypeCarte()));
        columnValues.add(String.valueOf(entity.getClasse()));

        PreparedStatement ps = null;

        getCnx().setAutoCommit(false);
        try {

            // exécuter ici des requêtes : statements
            // et preparedStatements

            ps = makeInsert(columnNames, columnValues, "CARTE");

            Statement s = getCnx().createStatement();
            s.executeUpdate(ps.toString(),Statement.RETURN_GENERATED_KEYS);

            // application de la transaction
            getCnx().commit();

            ResultSet key = s.getGeneratedKeys();
            if(key.next()){
                entity.setId(key.getLong(1));
            }
            key.close();
        } catch (SQLException erreur) {
            // retour en arrière car une erreur est survenue
            getCnx().rollback();
        } finally {
            // fermeture des ressources :
            // statement, preparedStatements
            ps.close();
            getCnx().setAutoCommit(true);
        }


        return entity;
    }

    @Override
    public Optional<Carte> find(Long id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public Iterable<Carte> findAll() throws DaoException, SQLException {
        String Query = "SELECT id, Nom, Cout, Datesortie, TypeCarte, Classe FROM CARTE";;
    ps = getCnx().prepareStatement(Query);
    ResultSet rs = ps.executeQuery();
    List<Carte> cartes = new ArrayList<>();
    while (rs.next()){
    Carte c =   EntitiesFactory.fabriquerCarte(
                rs.getString(2),
                rs.getInt(3),
                rs.getDate(4).toLocalDate(),
                TypeCarte.valueOf(rs.getString(5)),
                Classe.valueOf(rs.getString(6)),
                null
        );
    c.setId(rs.getLong(1));

        cartes.add(c);
    }

        return Collections.unmodifiableList(cartes);
    }

    @Override
    public void delete(Long id) throws DaoException {

    }

    @Override
    public boolean exists(Long id) throws DaoException {
        return false;
    }

    @Override
    public long count() throws DaoException {
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

    private PreparedStatement makeInsert(List<String> listOfColumnsNames, List<Object> listOfParameters, String tableName) throws SQLException {
        StringBuilder columns = new StringBuilder();
        StringBuilder parameters = new StringBuilder();
        String query = null;
        PreparedStatement ps = null;

        if (listOfColumnsNames != null && !listOfColumnsNames.isEmpty() && listOfParameters != null && !listOfParameters.isEmpty() && tableName != null) {
            for (String string : listOfColumnsNames) {
                columns.append(string + ",");
            }
            columns.deleteCharAt(columns.length() - 1);

            for (Object object : listOfParameters) {
                parameters.append("?,");
            }
            parameters.deleteCharAt(parameters.length() - 1);

            query = "INSERT " + "INTO " + tableName + " ( " + columns + " ) " + " VALUES " + "( " + parameters + " )";

            ps = getCnx().prepareStatement(query);
            for (int i = 0; i < listOfParameters.size(); i++) {
                if (listOfParameters.get(i).getClass().equals(String.class)) {
                    ps.setString(i + 1, (String) listOfParameters.get(i));
                } else if (listOfParameters.get(i).getClass().equals(Integer.class)) {
                    ps.setInt(i + 1, (Integer) listOfParameters.get(i));
                } else if (listOfParameters.get(i).getClass().equals(Long.class)) {
                    ps.setLong(i + 1, (Long) listOfParameters.get(i));
                } else if (listOfParameters.get(i).getClass().equals(Double.class)) {
                    ps.setDouble(i + 1, (Double) listOfParameters.get(i));
                } else if (listOfParameters.get(i).getClass().equals(Float.class)) {
                    ps.setFloat(i + 1, (Float) listOfParameters.get(i));
                } else if (listOfParameters.get(i).getClass().equals(Date.class)) {
                    ps.setDate(i + 1, (Date) listOfParameters.get(i));
                } else if (listOfParameters.get(i).getClass().equals(Boolean.class)) {
                    ps.setBoolean(i + 1, (Boolean) listOfParameters.get(i));
                } else {
                    ps.setObject(i + 1, listOfParameters.get(i));
                }
            }
        }


        //TODO if you need check for null
        return ps;
    }
}
