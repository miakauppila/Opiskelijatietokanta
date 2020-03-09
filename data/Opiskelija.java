package data;

import java.text.Collator;
import java.util.List;
import java.util.Objects;

public class Opiskelija implements Comparable<Opiskelija> {

    private int opiskelijaID;
    private String etunimi;
    private String sukunimi;
    private int opintoviikot;
    private String sahkoposti;
    private String ryhmaID;

    //konstruktori 1 lähetetään kaikki tiedot
    public Opiskelija(int opiskelijaID, String etunimi, String sukunimi, int opintoviikot, String sahkoposti, String ryhmaID) {
        this.opiskelijaID = opiskelijaID;
        this.etunimi = etunimi;
        this.sukunimi = sukunimi;
        this.opintoviikot = opintoviikot;
        this.sahkoposti = sahkoposti;
        this.ryhmaID = ryhmaID;
    }

    //konstruktori 2 opintoviikot asetetaan 0:ksi.
    public Opiskelija(int opiskelijaID, String etunimi, String sukunimi, String sahkoposti) {
        this.opiskelijaID = opiskelijaID;
        this.etunimi = etunimi;
        this.sukunimi = sukunimi;
        this.opintoviikot = 0;
        this.sahkoposti = sahkoposti;
    }

    //kaikille ominaisuuksille get- ja set-metodit
    public String getEtunimi() {
        return etunimi;
    }

    public void setEtunimi(String etunimi) {
        this.etunimi = etunimi;
    }

    public String getSukunimi() {
        return sukunimi;
    }

    public void setSukunimi(String sukunimi) {
        this.sukunimi = sukunimi;
    }

    public int getOpintoviikot() {
        return opintoviikot;
    }

    public void setOpintoviikot(int opintoviikot) {
        this.opintoviikot = opintoviikot;
    }

    public int getOpiskelijaID() {
        return opiskelijaID;
    }

    public void setOpiskelijaID(int opiskelijaID) {
        this.opiskelijaID = opiskelijaID;
    }

    public String getSahkoposti() {
        return sahkoposti;
    }

    public void setSahkoposti(String sahkoposti) {
        this.sahkoposti = sahkoposti;
    }

    public String getRyhmaID() {
        return ryhmaID;
    }

    public void setRyhmaID(String ryhmaID) {
        this.ryhmaID = ryhmaID;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.opiskelijaID;
        hash = 29 * hash + Objects.hashCode(this.etunimi);
        hash = 29 * hash + Objects.hashCode(this.sukunimi);
        hash = 29 * hash + this.opintoviikot;
        hash = 29 * hash + Objects.hashCode(this.sahkoposti);
        hash = 29 * hash + Objects.hashCode(this.ryhmaID);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Opiskelija other = (Opiskelija) obj;
        if (this.opiskelijaID != other.opiskelijaID) {
            return false;
        }
        if (this.opintoviikot != other.opintoviikot) {
            return false;
        }
        if (!Objects.equals(this.etunimi, other.etunimi)) {
            return false;
        }
        if (!Objects.equals(this.sukunimi, other.sukunimi)) {
            return false;
        }
        if (!Objects.equals(this.sahkoposti, other.sahkoposti)) {
            return false;
        }
        if (!Objects.equals(this.ryhmaID, other.ryhmaID)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "opiskelijaID=" + opiskelijaID + ", etunimi="
                + etunimi + ", sukunimi=" + sukunimi + ", opintoviikot="
                + opintoviikot + ", sähköposti=" + sahkoposti + ", ryhmaID=" + ryhmaID;
    }

    @Override
    public int compareTo(Opiskelija olio) {
        Collator apu = Collator.getInstance();
        apu.setStrength(Collator.PRIMARY);
        int tulos = apu.compare(this.sukunimi, olio.sukunimi);
        if (tulos != 0) {
            return tulos;
        } else {
            return apu.compare(this.etunimi, olio.etunimi);
        }
    }

    public static String listaMerkkijonona(List<Opiskelija> lista) {
        String merkit = "";
        for (Opiskelija uusi : lista) {
            merkit += uusi + "\n";
        }
        return merkit;
    }

}
