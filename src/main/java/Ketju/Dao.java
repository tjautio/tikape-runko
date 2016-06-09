/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tikape.runko.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tikape.runko.domain.Ketju;

/**
 *
 * @author Daniel
 */
public class KetjuDao implements Dao<Ketju, Integer> {

    private Database database;

    public KetjuDao(Database database) {
        this.database = database;
    }

    @Override
    public Ketju findOne(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Ketjut WHERE id = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }
        int ketjuId = rs.getInt("ketju_id");
        String otsikko = rs.getString("otsikko");
        int alueId = rs.getInt("alue");

        Ketju k = new Ketju(ketjuId, otsikko, alueId);

        rs.close();
        stmt.close();
        connection.close();

        return k;
    }

    public List<Ketju> findAll() throws SQLException {

        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Ketjut");

        ResultSet rs = stmt.executeQuery();
        List<Ketju> ketjut = new ArrayList<>();
        while (rs.next()) {
            int ketjuId = rs.getInt("ketju_id");
            String otsikko = rs.getString("otsikko");
            int alueId = rs.getInt("alue");

            ketjut.add(new Ketju(ketjuId, otsikko, alueId));
        }

        rs.close();
        stmt.close();
        connection.close();

        return ketjut;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

