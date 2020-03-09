package tietokanta;

import java.sql.*;

import data.Opiskelija;
import data.Ryhma;
import java.util.ArrayList;

public class Tietovarasto {

    private String ajuri = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localhost/Opiskelijatietokanta";
    private String kayttajatunnus = "root";
    private String salasana = "";

    private String sqlLisaaOpiskelija = "INSERT INTO Opiskelija VALUES (?,?,?,?,?,?)";
    
    private String sqlHaeOpiskelija = "SELECT * FROM Opiskelija WHERE opiskelijaID = ?";

    private String sqlMuutaOpiskelija = "UPDATE Opiskelija SET etunimi = ?, sukunimi = ?, opintoviikot = ?,"
            + "sahkoposti = ?, ryhmaID = ? WHERE opiskelijaID = ?";

    private String sqlPoistaOpiskelija = "DELETE FROM Opiskelija WHERE opiskelijaID = ?";
    
    private String sqlHaeOpiskelijanRyhma = "SELECT opiskelijaID, etunimi, sukunimi, opintoviikot, sahkoposti, Opiskelija.ryhmaID, ryhmannimi, toteutusmuoto, vastuuopettaja\n" +
"	FROM Opiskelija JOIN Ryhma ON Opiskelija.ryhmaID = Ryhma.ryhmaID\n" +
"	WHERE opiskelijaID = ?";

    private String sqlHaeKaikkiOpiskelijat = "SELECT * FROM Opiskelija";
    
    private String sqlHaeRyhmanOpiskelijat = "SELECT * FROM Opiskelija WHERE ryhmaID = ?";
    
    private String sqlHaeKaikkiRyhmat = "SELECT ryhmaID FROM Ryhma";
    
    private String sqlLuoRyhma = "INSERT INTO Ryhma VALUES (?,?,?,?)";
    
    private String sqlHaeRyhmanTiedot = "SELECT * FROM Ryhma WHERE ryhmaID = ?";
    
    private String sqlMuutaRyhma = "UPDATE Ryhma SET ryhmannimi = ?, toteutusmuoto = ?,"
            + "vastuuopettaja = ? WHERE ryhmaID = ?";

    
    public Opiskelija haeOpiskelija(int opiskelijaID) throws Exception {
        Connection yhteys = null;
        try {
            yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttajatunnus, salasana);
        } catch (Exception e) {
            throw new Exception("Tietovarasto ei ole auki.", e);
        }

        PreparedStatement haeOpiskelija = null;
        ResultSet tulos = null;
        try {
            haeOpiskelija = yhteys.prepareStatement(sqlHaeOpiskelija);
            haeOpiskelija.setInt(1, opiskelijaID);
            tulos = haeOpiskelija.executeQuery();
            if (tulos.next()) {
                return new Opiskelija(tulos.getInt(1), tulos.getString(2), tulos.getString(3), tulos.getInt(4), tulos.getString(5), tulos.getString(6));
            } else {
                throw new Exception("Opiskelijaa ei löydy");
            }

        } catch (SQLException sqle) {
            throw new Exception("Haku ei onnistunut", sqle);
        } finally {
            YhteydenHallinta.suljeYhteys(yhteys);
        }

    }
    
     public Ryhma haeOpiskelijanRyhma(int opiskelijaID) throws Exception {
        Connection yhteys = null;
        try {
            yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttajatunnus, salasana);
        } catch (Exception e) {
            throw new Exception("Tietovarasto ei ole auki.", e);
        }

        PreparedStatement haeRyhma = null;
        ResultSet tulos = null;
        try {
            haeRyhma = yhteys.prepareStatement(sqlHaeOpiskelijanRyhma);
            haeRyhma.setInt(1, opiskelijaID);
            tulos = haeRyhma.executeQuery();
            if (tulos.next()) {
                return new Ryhma(tulos.getString(6), tulos.getString(7), tulos.getString(8), tulos.getString(9));
            } else {
                throw new Exception("Opiskelijaa ei löydy");
            }

        } catch (SQLException sqle) {
            throw new Exception("Haku ei onnistunut", sqle);
        } finally {
            YhteydenHallinta.suljeYhteys(yhteys);
        }

    }

    public void lisaaOpiskelija(Opiskelija uusiOpiskelija) throws Exception {

        Connection yhteys = null;
        try {
            yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttajatunnus, salasana);
        } catch (Exception e) {
            throw new Exception("Tietovarasto ei ole auki.", e);
        }
        PreparedStatement opiskelijanLisays = null;
        try {
            opiskelijanLisays = yhteys.prepareStatement(sqlLisaaOpiskelija);
            opiskelijanLisays.setInt(1, uusiOpiskelija.getOpiskelijaID());// ensimmainen kysymysmerkki
            opiskelijanLisays.setString(2, uusiOpiskelija.getEtunimi());// toinen kysymysmerkki
            opiskelijanLisays.setString(3, uusiOpiskelija.getSukunimi());// jne
            opiskelijanLisays.setInt(4, uusiOpiskelija.getOpintoviikot());// jne
            opiskelijanLisays.setString(5, uusiOpiskelija.getSahkoposti());
            opiskelijanLisays.setString(6, uusiOpiskelija.getRyhmaID()); 
            opiskelijanLisays.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new Exception("Opiskelijan lisäys ei onnistu.", sqle);
        }
    }

    public void muutaOpiskelija(Opiskelija uusiOpiskelija) throws Exception {

        Connection yhteys = null;
        try {
            yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttajatunnus, salasana);
        } catch (Exception e) {
            throw new Exception("Tietovarasto ei ole auki.", e);
        }
        PreparedStatement opiskelijanMuutos = null;
        try {
            opiskelijanMuutos = yhteys.prepareStatement(sqlMuutaOpiskelija);
            opiskelijanMuutos.setString(1, uusiOpiskelija.getEtunimi());// 1. kysymysmerkki
            opiskelijanMuutos.setString(2, uusiOpiskelija.getSukunimi());// jne
            opiskelijanMuutos.setInt(3, uusiOpiskelija.getOpintoviikot());// jne
            opiskelijanMuutos.setString(4, uusiOpiskelija.getSahkoposti());
            opiskelijanMuutos.setString(5, uusiOpiskelija.getRyhmaID());
            opiskelijanMuutos.setInt(6, uusiOpiskelija.getOpiskelijaID());
            opiskelijanMuutos.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new Exception("Opiskelijan muutos ei onnistu.", sqle);
        }
    }

    public void poistaOpiskelija(int opiskelijaID) throws Exception {
        //int opiskelijaID = uusiOpiskelija.getOpiskelijaID();

        Connection yhteys = null;
        try {
            yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttajatunnus, salasana);
        } catch (Exception e) {
            throw new Exception("Tietovarasto ei ole auki.", e);
        }
        PreparedStatement opiskelijanPoisto = null;
        try {
            opiskelijanPoisto = yhteys.prepareStatement(sqlPoistaOpiskelija);
            opiskelijanPoisto.setInt(1, opiskelijaID);
            opiskelijanPoisto.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new Exception("Opiskelijan poisto ei onnistu.", sqle);
        }
    }
    
    public void luoRyhma(Ryhma uusiRyhma) throws Exception {

        Connection yhteys = null;
        try {
            yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttajatunnus, salasana);
        } catch (Exception e) {
            throw new Exception("Tietovarasto ei ole auki.", e);
        }
        PreparedStatement ryhmanLuonti = null;
        try {
            ryhmanLuonti = yhteys.prepareStatement(sqlLuoRyhma);
            ryhmanLuonti.setString(1, uusiRyhma.getRyhmaID());// 1. kysymysmerkki
            ryhmanLuonti.setString(2, uusiRyhma.getRyhmannimi()); // jne
            ryhmanLuonti.setString(3, uusiRyhma.getToteutusmuoto());
            ryhmanLuonti.setString(4, uusiRyhma.getVastuuope());
            ryhmanLuonti.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new Exception("Uuden ryhmän luominen ei onnistu.", sqle);
        }
    }
    
   public Ryhma haeRyhmanTiedot(String ryhmaID) throws Exception {
        Connection yhteys = null;
        try {
            yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttajatunnus, salasana);
        } catch (Exception e) {
            throw new Exception("Tietovarasto ei ole auki.", e);
        }

        PreparedStatement haeRyhmanTiedot = null;
        ResultSet tulos = null;
        try {
            haeRyhmanTiedot = yhteys.prepareStatement(sqlHaeRyhmanTiedot);
            haeRyhmanTiedot.setString(1, ryhmaID);
            tulos = haeRyhmanTiedot.executeQuery();
            if (tulos.next()) {
                return new Ryhma(tulos.getString(1), tulos.getString(2), tulos.getString(3), tulos.getString(4));
            } else {
                throw new Exception("Ryhmää ei löydy");
            }

        } catch (SQLException sqle) {
            throw new Exception("Haku ei onnistunut", sqle);
        } finally {
            YhteydenHallinta.suljeYhteys(yhteys);
        }
    }
   
    public void muutaRyhma(Ryhma uusiRyhma) throws Exception {

        Connection yhteys = null;
        try {
            yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttajatunnus, salasana);
        } catch (Exception e) {
            throw new Exception("Tietovarasto ei ole auki.", e);
        }
        PreparedStatement ryhmanMuutos = null;
        try {
            ryhmanMuutos = yhteys.prepareStatement(sqlMuutaRyhma);
            ryhmanMuutos.setString(1, uusiRyhma.getRyhmannimi());// 1. kysymysmerkki
            ryhmanMuutos.setString(2, uusiRyhma.getToteutusmuoto());// jne.
            ryhmanMuutos.setString(3, uusiRyhma.getVastuuope());
            ryhmanMuutos.setString(4, uusiRyhma.getRyhmaID());
            ryhmanMuutos.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new Exception("Ryhman muutos ei onnistu.", sqle);
        }
    }

    public ArrayList haeKaikkiOpiskelijat() throws Exception {

        Connection yhteys = null;
        ArrayList<Opiskelija> opiskelijalista = new ArrayList<>();
        try {
            yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttajatunnus, salasana);
        } catch (Exception e) {
            throw new Exception("Tietovarasto ei ole auki.", e);
        }
        PreparedStatement kaikkienHaku = null;
        ResultSet hakutulos = null;
        try {
            kaikkienHaku = yhteys.prepareStatement(sqlHaeKaikkiOpiskelijat);
            hakutulos = kaikkienHaku.executeQuery();
            while (hakutulos.next()) { //ottaa taulukosta rivin kerrallaan, numerot alla viittaavat taulukon sarakkeeseen(monesko)
                //tehdään joka rivistä uusi opiskelija, joka lisätään listaan
                opiskelijalista.add(new Opiskelija(hakutulos.getInt(1), hakutulos.getString(2), hakutulos.getString(3), hakutulos.getInt(4), hakutulos.getString(5), hakutulos.getString(6)));
            }
        }
            catch(SQLException sqle){
			sqle.printStackTrace();
			throw new Exception("Haku ei onnistunut.", sqle);
		}
            return opiskelijalista;
        }
    
    public ArrayList haeRyhmanOpiskelijat(String ryhmaID) throws Exception {

        Connection yhteys = null;
        ArrayList<Opiskelija> opiskelijalista = new ArrayList<>();
        try {
            yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttajatunnus, salasana);
        } catch (Exception e) {
            throw new Exception("Tietovarasto ei ole auki.", e);
        }
        PreparedStatement ryhmanHaku = null;
        ResultSet hakutulos = null;
        try {
            ryhmanHaku = yhteys.prepareStatement(sqlHaeRyhmanOpiskelijat);
            ryhmanHaku.setString(1, ryhmaID);
            hakutulos = ryhmanHaku.executeQuery();
            while (hakutulos.next()) { //ottaa taulukosta rivin kerrallaan, numerot alla viittaavat taulukon sarakkeeseen(monesko)
                //tehdään joka rivistä uusi opiskelija, joka lisätään listaan
                opiskelijalista.add(new Opiskelija(hakutulos.getInt(1), hakutulos.getString(2), hakutulos.getString(3), hakutulos.getInt(4), hakutulos.getString(5), hakutulos.getString(6)));
            }
        }
            catch(SQLException sqle){
			sqle.printStackTrace();
			throw new Exception("Haku ei onnistunut.", sqle);
		}
            return opiskelijalista;
        }
    
    public ArrayList haeKaikkiRyhmat() throws Exception {

        Connection yhteys = null;
        ArrayList<String> ryhmalista = new ArrayList<>();
        try {
            yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttajatunnus, salasana);
        } catch (Exception e) {
            throw new Exception("Tietovarasto ei ole auki.", e);
        }
        PreparedStatement kaikkienRyhmienHaku = null;
        ResultSet hakutulos = null;
        try {
            kaikkienRyhmienHaku = yhteys.prepareStatement(sqlHaeKaikkiRyhmat);
            hakutulos = kaikkienRyhmienHaku.executeQuery();
            while (hakutulos.next()) { //ottaa taulukosta rivin kerrallaan, numerot alla viittaavat taulukon sarakkeeseen(monesko)
                //tehdään joka rivistä uusi opiskelija, joka lisätään listaan
                ryhmalista.add(hakutulos.getString(1));
            }
        }
            catch(SQLException sqle){
			sqle.printStackTrace();
			throw new Exception("Haku ei onnistunut.", sqle);
		}
            return ryhmalista;
        }
    

    }
