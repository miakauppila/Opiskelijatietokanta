/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import data.Opiskelija;
import static data.Opiskelija.listaMerkkijonona;
import data.Ryhma;
import java.util.ArrayList;
import java.util.List;
import tietokanta.Tietovarasto;

/**
 *
 * @author s1900014
 */
public class Testiohjelma {
    public static void main(String[] args) throws Exception {
        
        Tietovarasto testi = new Tietovarasto();
        
        ArrayList<Opiskelija> opiskelijat = testi.haeKaikkiOpiskelijat();
        
        System.out.println(opiskelijat);
        
//        System.out.println(opiskelijat.toString().replace("],", "\n"));
        
        String muotoiltuLista = "";
        for(Opiskelija opiskelija: opiskelijat) {
            muotoiltuLista = muotoiltuLista +"\n"+opiskelija.toString();
        }
        System.out.println(muotoiltuLista);
        
        Ryhma uusi = testi.haeOpiskelijanRyhma(5555);
        System.out.println(uusi);
        
        System.out.println(testi.haeKaikkiRyhmat());
        
        System.out.println(testi.haeRyhmanOpiskelijat("SOKE19A"));
        
        Opiskelija olli = new Opiskelija(6666, "Matti", "Mattila", 0, "matti@", "SOKE19A");
        System.out.println(olli);
        
        System.out.println(listaMerkkijonona(opiskelijat));
       
        
        
    }
    
}
