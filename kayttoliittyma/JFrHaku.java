package kayttoliittyma;

import javax.swing.JOptionPane;

import data.Opiskelija;
import data.Ryhma;
import java.awt.GridLayout;

import tietokanta.Tietovarasto;


public class JFrHaku extends JFrAbstraktiLisaysJaMuut{

	//konstruktori
		public JFrHaku(Tietovarasto varasto){
			super(varasto);
			btNappi.setText("Hae");
			this.setTitle("Hae opiskelija");
                        paYla.setLayout(new GridLayout(10, 2));
                        this.setSize(320,270);
                        paYla.add(lbRyhmannimi);
                        paYla.add(tfRyhmannimi);
                        paYla.add(lbToteutus);
                        paYla.add(tfToteutus);
                        paYla.add(lbOpettaja);
                        paYla.add(tfOpettaja);
			tfEtunimi.setEditable(false);
			tfSukunimi.setEditable(false);
			tfOpintoviikot.setEditable(false);
                        tfSahkoposti.setEditable(false);
                        tfRyhma.setEditable(false);
                        tfRyhmannimi.setEditable(false);
                        tfToteutus.setEditable(false);
                        tfOpettaja.setEditable(false);
		}

	@Override
	protected void kasitteleNappi() {
		try{
			int opiskelijaID = Integer.parseInt(tfId.getText());
			Opiskelija haettu = rekisteri.haeOpiskelija(opiskelijaID);
                        Ryhma oma = rekisteri.haeOpiskelijanRyhma(opiskelijaID);
			if(haettu == null){
				JOptionPane.showMessageDialog(this, "Opiskelijaa ei löytynyt", "Virhe",JOptionPane.INFORMATION_MESSAGE);
			}else{
				tfEtunimi.setText(haettu.getEtunimi());
				tfSukunimi.setText(haettu.getSukunimi());
				tfOpintoviikot.setText(""+haettu.getOpintoviikot());
                                tfSahkoposti.setText(haettu.getSahkoposti());
                                tfRyhma.setText(haettu.getRyhmaID());
                                tfRyhmannimi.setText(oma.getRyhmannimi());
                                tfToteutus.setText(oma.getToteutusmuoto());
                                tfOpettaja.setText(oma.getVastuuope());
                                
			}
		}catch(Exception e){
			JOptionPane.showMessageDialog(this, e, "Virhe",JOptionPane.ERROR_MESSAGE);
		}
	}

}
