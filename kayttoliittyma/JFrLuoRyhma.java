/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import data.Ryhma;
import javax.swing.JOptionPane;
import tietokanta.Tietovarasto;

/**
 *
 * @author s1900014
 */
public class JFrLuoRyhma extends JFrAbstraktiLisaysJaMuut{
        
    //konstruktori
    public JFrLuoRyhma(Tietovarasto varasto) {
        super(varasto);
        btNappi.setText("Talleta");
        this.setTitle("Luo uusi ryhmä");
        asetteleKomponentit();
    }
    
    private void asetteleKomponentit(){
        paYla.remove(lbId);
        paYla.remove(tfId);
        paYla.remove(lbEtunimi);
        paYla.remove(tfEtunimi);
        paYla.remove(lbSukunimi);
        paYla.remove(tfSukunimi);
        paYla.remove(lbOpintoviikot);
        paYla.remove(tfOpintoviikot);
        paYla.remove(lbSahkoposti);
        paYla.remove(tfSahkoposti);
        paYla.add(lbRyhmannimi);
        paYla.add(tfRyhmannimi);
        paYla.add(lbToteutus);
        paYla.add(tfToteutus);
        paYla.add(lbOpettaja);
        paYla.add(tfOpettaja);  
    }
    
    @Override
    protected void kasitteleNappi() {
        try {

            if (tfRyhma.getText().isEmpty() || tfRyhmannimi.getText().isEmpty() || tfToteutus.getText().isEmpty() || tfOpettaja.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "Tiedoissa puutteita. Täytä kaikki kentät.", "Virhe", JOptionPane.INFORMATION_MESSAGE);
            } else {
                int valinta = JOptionPane.showConfirmDialog(this, "RyhmäID-arvoa ei voi muuttaa/poistaa. Haluatko varmasti luoda uuden ryhmän?", "Tallennus", JOptionPane.OK_CANCEL_OPTION);
                if(valinta == 0){
                String ryhmaID = tfRyhma.getText();
                String nimi = tfRyhmannimi.getText();
                String toteutus = tfToteutus.getText();
                String opettaja = tfOpettaja.getText();
                Ryhma uusi = new Ryhma(ryhmaID, nimi, toteutus, opettaja);
                rekisteri.luoRyhma(uusi);
                JOptionPane.showMessageDialog(this, "Uuden ryhmän tiedot on tallennettu", "Tallennus", JOptionPane.INFORMATION_MESSAGE);
                }
                
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e, "Virhe", JOptionPane.ERROR_MESSAGE);
        }
    }
    
   
    
}
