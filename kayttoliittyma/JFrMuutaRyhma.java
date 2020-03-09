/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import data.Ryhma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import tietokanta.Tietovarasto;

/**
 *
 * @author s1900014
 */
public class JFrMuutaRyhma extends JFrAbstraktiLisaysJaMuut {

    private JButton btMuutos = new JButton("Talleta uudet");
    
    //konstruktori
    public JFrMuutaRyhma(Tietovarasto varasto) {
        super(varasto);
        btNappi.setText("Hae");
        this.setTitle("Hae/Muokkaa ryhmää");
        asetteleKomponentit();
    }

    private void asetteleKomponentit() {
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
        tfRyhmannimi.setEditable(false);
        tfToteutus.setEditable(false);
        tfOpettaja.setEditable(false);
        paNapit.add(btMuutos);
        
        btMuutos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                kasitteleNappi2();
            }
        });
        
        
        btPeruuta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tyhjenna2();
            }
        });
    }

    @Override
    protected void kasitteleNappi() {
        try {
            String ryhmaID = tfRyhma.getText();
            Ryhma haettu = rekisteri.haeRyhmanTiedot(ryhmaID);
            if (haettu == null) {
                JOptionPane.showMessageDialog(this, "Ryhmää ei löytynyt", "Virhe", JOptionPane.INFORMATION_MESSAGE);
            } else {
                tfRyhmannimi.setText(haettu.getRyhmannimi());
                tfToteutus.setText(haettu.getToteutusmuoto());
                tfToteutus.setText(haettu.getToteutusmuoto());
                tfOpettaja.setText(haettu.getVastuuope());
                tfRyhmannimi.setEditable(true);
                tfToteutus.setEditable(true);
                tfOpettaja.setEditable(true);
                tfRyhma.setEditable(false);

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e, "Virhe", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void kasitteleNappi2() {
        try {

            String ryhmaId = tfRyhma.getText();
            String nimi = tfRyhmannimi.getText();
            String toteutus = tfToteutus.getText();
            String opettaja = tfOpettaja.getText();

            if (ryhmaId.isEmpty() || nimi.isEmpty() || toteutus.isEmpty() || opettaja.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tiedoissa puutteita. Hae ja täytä kaikki kentät.", "Virhe", JOptionPane.INFORMATION_MESSAGE);
            } else {
                Ryhma muutettu = new Ryhma(ryhmaId, nimi, toteutus, opettaja);
                rekisteri.muutaRyhma(muutettu);
                JOptionPane.showMessageDialog(this, "Uudet tiedot tallennettu.", "Tallennus", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e, "Virhe", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    private void tyhjenna2() {
        tfRyhma.setText("");
        tfRyhma.setEditable(true);
        tfRyhmannimi.setText("");
        tfToteutus.setText("");
        tfOpettaja.setText("");
        tfRyhmannimi.setEditable(false);
        tfToteutus.setEditable(false);
        tfOpettaja.setEditable(false);   
    }
    
    

}
