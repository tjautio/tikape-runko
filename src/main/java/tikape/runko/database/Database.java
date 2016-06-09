package tikape.runko.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private String databaseAddress;

    public Database(String databaseAddress) throws ClassNotFoundException {
        this.databaseAddress = databaseAddress;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseAddress);
    }

    public void init() {
        List<String> lauseet = sqliteLauseet();

        // "try with resources" sulkee resurssin automaattisesti lopuksi
        try (Connection conn = getConnection()) {
            Sta
            // suoritetaan komennottement st = conn.createStatement();

            for (String lause : lauseet) {
                System.out.println("Running command >> " + lause);
                st.executeUpdate(lause);
            }

        } catch (Throwable t) {
            // jos tietokantataulu on jo olemassa, ei komentoja suoriteta
            System.out.println("Error >> " + t.getMessage());
        }
    }

    private List<String> sqliteLauseet() {
        ArrayList<String> lista = new ArrayList<>();

        // tietokantataulujen luomiseen tarvittavat komennot suoritusjärjestyksessä
        lista.add("CREATE DATABASE keskustelupalsta;");
        lista.add("CREATE TABLE Aihealueet (aihealue_id integer AUTO_INCREMENT, nimi varchar(100), PRIMARY KEY(aihealue_id));");
        lista.add("CREATE TABLE Ketjut (ketju_id integer AUTO_INCREMENT, aihealue integer, otsikko varchar(100), PRIMARY KEY(ketju_id), FOREIGN KEY(aihealue) REFERENCES Aihealueet(aihealue_id));");
        lista.add("CREATE TABLE Viestit (viesti_id integer AUTO_INCREMENT, ketju integer, lahettaja varchar(50), viestiosa text, paivamaara date, PRIMARY KEY(viesti_id), FOREIGN KEY(ketju) REFERENCES Ketjut(ketju_id));");

        return lista;
    }
}