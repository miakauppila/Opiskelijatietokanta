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
public class JFrPoisto extends JFrAbstraktiLisaysJaMuut {

    private JButton btPoisto = new JButton("Poista tiedot");

//konstruktori
    public JFrPoisto(Tietovarasto varasto) {
        super(varasto);
        btNappi.setText("Hae");
        this.setTitle("Poista opiskelija");
        paNapit.add(btPoisto);
        tfEtunimi.setEditable(false);
        tfSukunimi.setEditable(false);
        tfOpintoviikot.setEditable(false);
        tfSahkoposti.setEditable(false);
        tfRyhma.setEditable(false);

        btPoisto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                kasitteleNappi2();
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
                tfId.setEditable(false);

            }
            //
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
                JOptionPane.showMessageDialog(this, "Hae ensin opiskelija.", "Virhe", JOptionPane.INFORMATION_MESSAGE);
            } else {
                int opiskelijaID = Integer.parseInt(tfId.getText());
                int opintoviikot = Integer.parseInt(tfOpintoviikot.getText());
                int valinta = JOptionPane.showConfirmDialog(this, "Haluatko varmasti poistaa opiskelijan tiedot?", "Pysyvä poisto", JOptionPane.OK_CANCEL_OPTION);
                if(valinta == 0){
                rekisteri.poistaOpiskelija(opiskelijaID);
                JOptionPane.showMessageDialog(this, "Opiskelijan kaikki tiedot poistettu.", "Pysyvä poisto", JOptionPane.INFORMATION_MESSAGE);
                }
               
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e, "Virhe", JOptionPane.ERROR_MESSAGE);
        }
    }

}
