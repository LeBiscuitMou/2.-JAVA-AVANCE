package net.ent.etrs.heartStone.models.dao.jdbc;

import lombok.Getter;
import net.ent.etrs.heartStone.models.dao.BaseDao;
import net.ent.etrs.heartStone.models.dao.exceptions.DaoException;
import net.ent.etrs.heartStone.models.entities.AbstractEntity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public abstract class JDBCBaseDao<T extends AbstractEntity>  implements BaseDao<T> {

    @Getter
    private Connection cnx;

    public JDBCBaseDao() {
        try {
            this.cnx = DriverManager.getConnection("jdbc:postgresql://localhost:5432/heartstone","postgres","postgres");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
