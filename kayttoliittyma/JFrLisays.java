package kayttoliittyma;

import javax.swing.JOptionPane;

import data.Opiskelija;

import tietokanta.Tietovarasto;

public class JFrLisays extends JFrAbstraktiLisaysJaMuut {


    //konstruktori
    public JFrLisays(Tietovarasto varasto) {
        super(varasto);
        btNappi.setText("Talleta");
        this.setTitle("Lisää opiskelija");
    }

    @Override
    protected void kasitteleNappi() {
        try {

            if (tfEtunimi.getText().isEmpty() || tfSukunimi.getText().isEmpty() || tfId.getText().isEmpty() || tfOpintoviikot.getText().isEmpty() || tfSahkoposti.getText().isEmpty() || tfRyhma.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tiedoissa puutteita. Täytä kaikki kentät.", "Virhe", JOptionPane.INFORMATION_MESSAGE);
            } else {
                int opiskelijaID = Integer.parseInt(tfId.getText());
                String etunimi = tfEtunimi.getText();
                String sukunimi = tfSukunimi.getText();
                int opintoviikot = Integer.parseInt(tfOpintoviikot.getText());
                String sahkoposti = tfSahkoposti.getText();
                String ryhmaID = tfRyhma.getText();
                Opiskelija tyyppi = new Opiskelija(opiskelijaID, etunimi, sukunimi, opintoviikot, sahkoposti, ryhmaID);
                rekisteri.lisaaOpiskelija(tyyppi);
                JOptionPane.showMessageDialog(this, "Tiedot tallennettu", "Tallennus", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e, "Virhe", JOptionPane.ERROR_MESSAGE);
        }

    }

}
