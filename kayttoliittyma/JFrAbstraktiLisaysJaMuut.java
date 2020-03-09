package kayttoliittyma;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import tietokanta.Tietovarasto;


public abstract class JFrAbstraktiLisaysJaMuut extends JFrame{
	
    //protected muuttujia voi käyttää samassa pakkauksessa olevat luokat
    protected JPanel paPohja = new JPanel(new BorderLayout());
    protected JPanel paNapit = new JPanel(new FlowLayout(FlowLayout.TRAILING));
    protected JPanel paYla = new JPanel(new GridLayout(6, 2));
    
    protected JLabel lbId = new JLabel("OpiskelijaID");
    protected JTextField tfId = new JTextField();
    protected JLabel lbEtunimi = new JLabel("Etunimi");
    protected JTextField tfEtunimi = new JTextField();
    protected JLabel lbSukunimi = new JLabel("Sukunimi");
    protected JTextField tfSukunimi = new JTextField();
    protected JLabel lbOpintoviikot = new JLabel("Opintoviikot");
    protected JTextField tfOpintoviikot = new JTextField();
    protected JLabel lbSahkoposti = new JLabel("Sähköposti");
    protected JTextField tfSahkoposti = new JTextField();
    protected JLabel lbRyhma = new JLabel("RyhmäID");
    protected JTextField tfRyhma = new JTextField();
    protected JButton btNappi = new JButton();
    protected JButton btPeruuta = new JButton("Tyhjennä");
    
    //Kaikki opiskelijat
    protected TextArea taTiedot = new TextArea("", 20, 1);
    
    //Hae opiskelija, Luo ryhmä, Muuta ryhmä
    protected JLabel lbRyhmannimi = new JLabel("Ryhmän nimi");
    protected JTextField tfRyhmannimi = new JTextField();
    protected JLabel lbToteutus = new JLabel("Toteutusmuoto");
    protected JTextField tfToteutus = new JTextField();
    protected JLabel lbOpettaja = new JLabel("Vastuuopettaja");
    protected JTextField tfOpettaja = new JTextField();
    
    protected Tietovarasto rekisteri;
	
    //konstruktori
    public JFrAbstraktiLisaysJaMuut(Tietovarasto varasto){
    	this.rekisteri = varasto;
    	asetteleKomponentit();
    	this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    	this.setLocation(100, 100);
    	this.setSize(300,240);
    }
    
    private void asetteleKomponentit(){
    	paYla.add(lbId);
    	paYla.add(tfId);
    	paYla.add(lbEtunimi);
    	paYla.add(tfEtunimi);
    	paYla.add(lbSukunimi);
    	paYla.add(tfSukunimi);
    	paYla.add(lbOpintoviikot);
    	paYla.add(tfOpintoviikot);
        paYla.add(lbSahkoposti);
        paYla.add(tfSahkoposti);
        paYla.add(lbRyhma);
        paYla.add(tfRyhma);
    	paNapit.add(btNappi);
    	paNapit.add(btPeruuta);
    	
    	paPohja.add(paYla, BorderLayout.PAGE_START);
    	paPohja.add(paNapit, BorderLayout.PAGE_END);
    	
    	this.add(paPohja);
    	
    	btNappi.addActionListener(new ActionListener() {
                    @Override
    		public void actionPerformed(ActionEvent e) {
    			kasitteleNappi();
    		}
    	});
    	
    	btPeruuta.addActionListener(new ActionListener() {
                    @Override
    		public void actionPerformed(ActionEvent e) {
    			tyhjenna();
    		}
    	});
    }//asetteleKomponentit loppu
    
    protected abstract void kasitteleNappi();
	
    private void tyhjenna(){
    	tfId.setText("");
    	tfSukunimi.setText("");
    	tfEtunimi.setText("");
    	tfOpintoviikot.setText("");
        tfSahkoposti.setText("");
        tfRyhma.setText("");
        tfId.setEditable(true);
        //Muut ikkunat
        taTiedot.setText("");
        tfRyhmannimi.setText("");
    	tfToteutus.setText("");
    	tfOpettaja.setText("");
    }
	

}
