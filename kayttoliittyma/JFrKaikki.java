/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import data.Opiskelija;
import static data.Opiskelija.listaMerkkijonona;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import tietokanta.Tietovarasto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;

/**
 *
 * @author s1900014
 */
public class JFrKaikki extends JFrAbstraktiLisaysJaMuut {

    private JPanel paYla = new JPanel(new GridLayout(1, 2));

    JComboBox cbValinta = new JComboBox();
    private JButton btNappiHaeRyhma = new JButton();

    public JFrKaikki(Tietovarasto varasto) {
        super(varasto);
        this.setTitle("Kaikki opiskelijat");
        asetteleKomponentit();
        this.setSize(750, 400);
    }

    private void asetteleKomponentit() {
        paYla.add(taTiedot);
        taTiedot.setEditable(false);
        paNapit.add(lbRyhma);
        paNapit.add(cbValinta);
        cbValinta.addItem("");
        paNapit.add(btNappiHaeRyhma);
        btNappiHaeRyhma.setText("Hae ryhmä");
        paNapit.add(btNappi);
        paNapit.add(btPeruuta);
        btNappi.setText("Hae kaikki");

        paPohja.add(paYla, BorderLayout.PAGE_START);
        paPohja.add(paNapit, BorderLayout.PAGE_END);

        this.add(paPohja);

        btNappiHaeRyhma.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                kasitteleNappi2();
            }
        });

        try {
            ArrayList<String> lista = rekisteri.haeKaikkiRyhmat();

            if (lista.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ryhmien tietoja ei löytynyt", "Virhe", JOptionPane.INFORMATION_MESSAGE);
            } else {
                for (String ryhmaID : lista) {
                    cbValinta.addItem(ryhmaID);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e, "Virhe", JOptionPane.ERROR_MESSAGE);
        }

    }

    @Override
    protected void kasitteleNappi() {
        try {
            ArrayList<Opiskelija> opiskelijat = rekisteri.haeKaikkiOpiskelijat();
            if (opiskelijat.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tietoja ei löytynyt", "Virhe", JOptionPane.INFORMATION_MESSAGE);
            } else {
//                taTiedot.setText(opiskelijat.toString().replace("],", "\n"));
                  taTiedot.setText(listaMerkkijonona(opiskelijat));
                
            }
           
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e, "Virhe", JOptionPane.ERROR_MESSAGE);
        }
    }

    protected void kasitteleNappi2() {
        try {
            ArrayList<String> lista = rekisteri.haeKaikkiRyhmat();
            String ryhmaID = (String) cbValinta.getSelectedItem();
            ArrayList<Opiskelija> opiskelijat = rekisteri.haeRyhmanOpiskelijat(ryhmaID);
            if (opiskelijat.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Opiskelijoiden tietoja ei löytynyt", "Virhe", JOptionPane.INFORMATION_MESSAGE);
            } else {
//                String muotoiltuLista = "";
//                for(Opiskelija opiskelija: opiskelijat) {
//                muotoiltuLista = muotoiltuLista +"\n"+opiskelija.toString();
//                taTiedot.setText(muotoiltuLista);
                  taTiedot.setText(listaMerkkijonona(opiskelijat));
                }
                   
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e, "Virhe", JOptionPane.ERROR_MESSAGE);
        }
    }

}
