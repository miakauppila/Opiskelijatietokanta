/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.Objects;

/**
 *
 * @author s1900014
 */
public class Ryhma {

    private String ryhmaID;
    private String ryhmannimi;
    private String toteutusmuoto;
    private String vastuuope;

    public Ryhma(String ryhmaID, String ryhmannimi, String toteutusmuoto, String vastuuope) {
        this.ryhmaID = ryhmaID;
        this.ryhmannimi = ryhmannimi;
        this.toteutusmuoto = toteutusmuoto;
        this.vastuuope = vastuuope;
    }

    public String getRyhmaID() {
        return ryhmaID;
    }

    public void setRyhmaID(String ryhmaID) {
        this.ryhmaID = ryhmaID;
    }

    public String getRyhmannimi() {
        return ryhmannimi;
    }

    public void setRyhmannimi(String ryhmannimi) {
        this.ryhmannimi = ryhmannimi;
    }

    public String getToteutusmuoto() {
        return toteutusmuoto;
    }

    public void setToteutusmuoto(String toteutusmuoto) {
        this.toteutusmuoto = toteutusmuoto;
    }

    public String getVastuuope() {
        return vastuuope;
    }

    public void setVastuuope(String vastuuope) {
        this.vastuuope = vastuuope;
    }

    @Override
    public String toString() {
        return ryhmaID + "=" + ryhmaID + ", ryhmannimi=" + ryhmannimi + ", toteutusmuoto=" + toteutusmuoto + ", vastuuope=" + vastuuope;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.ryhmaID);
        hash = 97 * hash + Objects.hashCode(this.ryhmannimi);
        hash = 97 * hash + Objects.hashCode(this.toteutusmuoto);
        hash = 97 * hash + Objects.hashCode(this.vastuuope);
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
        final Ryhma other = (Ryhma) obj;
        if (!Objects.equals(this.ryhmaID, other.ryhmaID)) {
            return false;
        }
        if (!Objects.equals(this.ryhmannimi, other.ryhmannimi)) {
            return false;
        }
        if (!Objects.equals(this.toteutusmuoto, other.toteutusmuoto)) {
            return false;
        }
        if (!Objects.equals(this.vastuuope, other.vastuuope)) {
            return false;
        }
        return true;
    }

}
