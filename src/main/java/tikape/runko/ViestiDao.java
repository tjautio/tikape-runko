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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tikape.runko.domain.Viesti;


/**
 *
 * @author Daniel
 */
public class ViestiDao implements Dao<Viesti, Integer> {

    private Database database;

    public ViestiDao(Database database) {
        this.database = database;
    }

    @Override
    public Viesti findOne(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Viestit WHERE id = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        int viestiId = rs.getInt("viesti_id");
        String viesti = rs.getString("viesti");
        int ketjuId = rs.getInt("ketju");
        String lahettaja = rs.getString("lahettaja");
        String paivamaara = rs.getString("paivamaara");

        Viesti v = new Viesti(viesti, viestiId, ketjuId, lahettaja, paivamaara);

        rs.close();
        stmt.close();
        connection.close();

        return v;
    }

    public void lahetaViesti(String viesti, String lahettaja, int ketjuId) {
        try (Connection conn = database.getConnection()) {
            Statement st = conn.createStatement();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Viestit (viesti, ketjuId, lahettaja, paivamaara)VALUES(?,?,?, CURRENT_TIMESTAMP)");
            stmt.setObject(1, viesti);
            stmt.setObject(2, ketjuId);
            stmt.setObject(3, lahettaja);
            stmt.executeQuery();
           

        } catch (Throwable t) {
            // jos tietokantataulu on jo olemassa, ei komentoja suoriteta
            System.out.println("Error >> " + t.getMessage());
        }

    }

    @Override
    public List<Viesti> findAll() throws SQLException {

        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Viestit");

        ResultSet rs = stmt.executeQuery();
        List<Viesti> viestit = new ArrayList<>();
        while (rs.next()) {
            int viestiId = rs.getInt("id");
            String viesti = rs.getString("viesti");
            int ketjuId = rs.getInt("ketjuId");
            String lahettaja = rs.getString("lahettaja");
            String paivamaara = rs.getString("paivamaara");

            viestit.add(new Viesti(viesti, viestiId, ketjuId, lahettaja, paivamaara));
        }

        rs.close();
        stmt.close();
        connection.close();

        return viestit;
    }

    public List<Viesti> findAllInKetju(int ketju) throws SQLException {

        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Viestit WHERE ketjuid = ?");
        stmt.setObject(1, ketju);
        ResultSet rs = stmt.executeQuery();
        List<Viesti> viestit = new ArrayList<>();
        while (rs.next()) {
            int viestiId = rs.getInt("id");
            String viesti = rs.getString("viesti");
            int ketjuId = rs.getInt("ketjuId");
            String lahettaja = rs.getString("lahettaja");
            String paivamaara = rs.getString("paivamaara");

            viestit.add(new Viesti(viesti, viestiId, ketjuId, lahettaja, paivamaara));
        }

        rs.close();
        stmt.close();
        connection.close();

        return viestit;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        // ei toteutettu
    }
}

