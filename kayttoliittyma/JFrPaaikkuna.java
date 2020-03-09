package kayttoliittyma;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import tietokanta.Tietovarasto;


public class JFrPaaikkuna extends JFrame {

	private JPanel paPohja = new JPanel(new GridLayout(7,1));
	
	private JButton btLisaa = new JButton("Lisää opiskelija");
	private JButton btHaeOpiskelija = new JButton("Hae opiskelijan tiedot");
	private JButton btPoista = new JButton("Poista opiskelija");
	private JButton btMuuta = new JButton("Muuta opiskelijan tietoja");
	private JButton btHaeKaikki = new JButton("Hae kaikki opiskelijat");
        private JButton btLisaaRyhma = new JButton("Luo uusi ryhmä");
        private JButton btMuutaPoistaRyhma = new JButton("Hae/Muokkaa ryhmää");
        
	private Tietovarasto rekisteri = new Tietovarasto();
	
	public JFrPaaikkuna(){
	    this.setTitle("Pääikkuna");
	    this.setLocation(100,100);
	    this.setSize(240,300);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    asetteleKomponentit();
	    this.setVisible(true);
	  }

	  private void asetteleKomponentit(){
	    paPohja.add(btLisaa);
	    paPohja.add(btHaeOpiskelija);
	    paPohja.add(btPoista);
	    paPohja.add(btMuuta);
            paPohja.add(btHaeKaikki);
            paPohja.add(btLisaaRyhma);
            paPohja.add(btMuutaPoistaRyhma);
	    
	    this.add(paPohja);
	    
	    btLisaa.addActionListener(new ActionListener(){
                    @Override
	    	public void actionPerformed(ActionEvent e){
	    		lisaaOpiskelija();
	    	}
	    });
	    
	    btHaeOpiskelija.addActionListener(new ActionListener(){
                    @Override
	    	public void actionPerformed(ActionEvent e){
	    		haeOpiskelija();
	    	}
	    });
            
            btPoista.addActionListener(new ActionListener(){
                    @Override
	    	public void actionPerformed(ActionEvent e){
	    		poistaOpiskelija();
	    	}
	    });
            
            btMuuta.addActionListener(new ActionListener(){
                    @Override
	    	public void actionPerformed(ActionEvent e){
	    		muutaOpiskelija();
	    	}
	    });
            
            btHaeKaikki.addActionListener(new ActionListener(){
                    @Override
	    	public void actionPerformed(ActionEvent e){
	    		haeKaikkiOpiskelijat();
	    	}
	    });
            
            btLisaaRyhma.addActionListener(new ActionListener(){
                    @Override
	    	public void actionPerformed(ActionEvent e){
	    		lisaaRyhma();
	    	}
	    });
            
            btMuutaPoistaRyhma.addActionListener(new ActionListener(){
                    @Override
	    	public void actionPerformed(ActionEvent e){
	    		muutaPoistaRyhma();
	    	}
	    });
            
	  }//asetteleKomponentit lopetus
	  
	 private void lisaaOpiskelija(){
		 new JFrLisays(rekisteri).setVisible(true);
	 }
	 
	 private void haeOpiskelija(){
		 new JFrHaku(rekisteri).setVisible(true);
	 }
         
         private void poistaOpiskelija(){
		 new JFrPoisto(rekisteri).setVisible(true);
	 }
         
         private void muutaOpiskelija(){
                new JFrMuutos(rekisteri).setVisible(true);
         }
         
         private void haeKaikkiOpiskelijat(){
             new JFrKaikki(rekisteri).setVisible(true);
         }
         
         private void lisaaRyhma(){
		 new JFrLuoRyhma(rekisteri).setVisible(true);
	 }
         
         private void muutaPoistaRyhma(){
             new JFrMuutaRyhma(rekisteri).setVisible(true);
         }
         
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrPaaikkuna ikkuna = new JFrPaaikkuna();

	}

}
