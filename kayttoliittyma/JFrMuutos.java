/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import data.Opiskelija;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import tietokanta.Tietovarasto;

/**
 *
 * @author s1900014
 */
public class JFrMuutos extends JFrAbstraktiLisaysJaMuut {
    
    private JButton btMuutos = new JButton("Talleta uudet");

    //konstruktori
    public JFrMuutos(Tietovarasto varasto) {
        super(varasto);
        btNappi.setText("Hae");
        this.setTitle("Muuta opiskelijan tietoja");
        paNapit.add(btMuutos);
        tfEtunimi.setEditable(false);
        tfSukunimi.setEditable(false);
        tfOpintoviikot.setEditable(false);
        tfSahkoposti.setEditable(false);
        tfRyhma.setEditable(false);
        

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
            int opiskelijaID = Integer.parseInt(tfId.getText());
            Opiskelija haettu = rekisteri.haeOpiskelija(opiskelijaID);
            if (haettu == null) {
                JOptionPane.showMessageDialog(this, "Opiskelijaa ei löytynyt", "Virhe", JOptionPane.INFORMATION_MESSAGE);
            } else {
                tfEtunimi.setText(haettu.getEtunimi());
                tfSukunimi.setText(haettu.getSukunimi());
                tfOpintoviikot.setText("" + haettu.getOpintoviikot());
                tfSahkoposti.setText(haettu.getSahkoposti());
                tfRyhma.setText("" + haettu.getRyhmaID());
                tfEtunimi.setEditable(true);
                tfSukunimi.setEditable(true);
                tfOpintoviikot.setEditable(true);
                tfSahkoposti.setEditable(true);
                tfRyhma.setEditable(true);
                tfId.setEditable(false);
               
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e, "Virhe", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void kasitteleNappi2() {
        try {

            String etunimi = tfEtunimi.getText();
            String sukunimi = tfSukunimi.getText();
            String sahkoposti = tfSahkoposti.getText();
            String ryhmaID = tfRyhma.getText();

            if (tfId.getText().isEmpty() || etunimi.isEmpty() || sukunimi.isEmpty() || tfOpintoviikot.getText().isEmpty() || sahkoposti.isEmpty() || ryhmaID.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tiedoissa puutteita. Hae opiskelija ja täytä kaikki kentät.", "Virhe", JOptionPane.INFORMATION_MESSAGE);
            } else {
                int opiskelijaID = Integer.parseInt(tfId.getText());
                int opintoviikot = Integer.parseInt(tfOpintoviikot.getText());
                Opiskelija tyyppi = new Opiskelija(opiskelijaID, etunimi, sukunimi, opintoviikot, sahkoposti, ryhmaID);
                rekisteri.muutaOpiskelija(tyyppi);
                JOptionPane.showMessageDialog(this, "Uudet tiedot tallennettu.", "Tallennus", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e, "Virhe", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void tyhjenna2() {
        tfId.setText("");
        tfSukunimi.setText("");
        tfEtunimi.setText("");
        tfOpintoviikot.setText("");
        tfSahkoposti.setText("");
        tfEtunimi.setEditable(false);
        tfSukunimi.setEditable(false);
        tfOpintoviikot.setEditable(false);
        tfSahkoposti.setEditable(false);
        tfRyhma.setEditable(false);
    }
}
