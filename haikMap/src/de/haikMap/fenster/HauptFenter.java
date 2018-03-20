package de.haikMap.fenster;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import de.haikMap.steuerung.Karte;

/**Diese Klasse erzeugt das HauptFenster des Porgrammes.
 * @author AutoBahnBlume
 *
 */
/**
 * @author AutoBahnBlume
 *
 */
public class HauptFenter extends JFrame {

	private Karte map = null;
	private JRadioButtonMenuItem rbMenuItemKartAuswahl= null;
	private ArrayList<JRadioButtonMenuItem> menuKarteAuswahlItems = new ArrayList<>();
	private JMenu menuKarte = null;
	private JMenuBar menuBar = null;
	private JScrollPane panelBaum = null;
	private ArrayList<JButton> btnArrayList = new ArrayList<>();
	
	private Dimension linkenSizeMenu = new Dimension(250, 250);

	
	public HauptFenter(Karte mapKlein) {
		this.map = mapKlein;
		this.iniFenster();
		this.iniMenuBar();
		this.erstelltLinkesMenu();
		this.iniKartenPanel();
		
		
		this.setVisible(true);
	}

	/**Erstellt die Linke Seite mit JTree, die und Eingabemöglichkeit 
	 * für eine neuen WegPkt und die Buttons
	 */
	private void erstelltLinkesMenu() {
		this.iniBaum();
		this.iniWegPktEingabe(200);
		this.iniButtons(20);
	}
		
	/**Fügt die Eingabemöglichkeit für eine neuen WegPkt
	 * @param hoehe - int - Gibt die Höhe des Panels an
	 */
	private void iniWegPktEingabe(int hoehe) {
		JPanel eingabeWegPktPanel = new JPanel();
		int y = (int) linkenSizeMenu.getHeight() + 5;
		eingabeWegPktPanel.setLayout(null);
		eingabeWegPktPanel.setBounds(5, y , (int) linkenSizeMenu.getWidth(), hoehe);
		
		this.iniTextFelder(eingabeWegPktPanel);
		
		this.add(eingabeWegPktPanel);
		linkenSizeMenu.setSize(linkenSizeMenu.getWidth(), y + hoehe);
		
	}
	/**Erstellt die TextFelder mit passenden Label
	 * @param container - JPanel - JPanel wo die TextFelder und Labels rein sollen.
	 */
	private void iniTextFelder(JPanel container) {
		int abstand =  20;
		JLabel lbName = new JLabel("Beschreibung");
		lbName.setBounds(0, abstand, 100, 20);
		
		JTextField txtName = new JTextField();
		txtName.setBounds(0, lbName.getY() + abstand, 220, 20);
		txtName.setToolTipText("Beschreibung");
		

		JLabel lbBreiteGrad = new JLabel("BreitenGrad");
		lbBreiteGrad.setBounds(0, txtName.getY() + abstand, 80, 20);
		
		java.awt.event.KeyAdapter keyAdap = new KeyAdapter() {
			public void keyTyped(java.awt.event.KeyEvent kEvt) {
				TxtFeldKeyTyped(kEvt);
			}
		};
		
		JTextField txtBreiteGrad = new JTextField();
		txtBreiteGrad.setBounds(0, lbBreiteGrad.getY() + abstand, 100, 20);
		txtBreiteGrad.addKeyListener(keyAdap);
		
		JLabel lbLaengeGrad = new JLabel("LängenGrad");
		lbLaengeGrad.setBounds(txtBreiteGrad.getWidth() + abstand, txtName.getY() + abstand, 100, 20);
		
		JTextField txtLaengeGrad = new JTextField();
		txtLaengeGrad.setBounds(txtBreiteGrad.getWidth() + abstand, lbLaengeGrad.getY() + abstand, 100, 20);
		txtLaengeGrad.addKeyListener(keyAdap);

		
		container.add(lbLaengeGrad);
		container.add(lbBreiteGrad);
		container.add(txtLaengeGrad);
		container.add(txtBreiteGrad);
		container.add(lbName);
		container.add(txtName);
		
	}

	/**TODO -ist nicht ferig
	 * TODO  Komma und Punkt geht nicht 
	 *  Kommentieren
	 */
	/** ist nicht ferig; Komma und Punkt geht nicht 	
	 * @param kEvent - KeyEvent -
	 */
	private void TxtFeldKeyTyped(java.awt.event.KeyEvent kEvent) {
		char key = kEvent.getKeyChar();
		System.out.println(Character.isDigit(key) + " && " + Character.isLetter(key));
		System.out.println(Character.isDigit(key) || Character.isLetter(key));
		if(!Character.isDigit(key) && !Character.isValidCodePoint(key)) {
			kEvent.consume();
		}
	}
	
	/**Fügt die Buttons ein
	 * @param hoehe - int - Gibt die Höhe der Buttons an
	 */
	private void iniButtons(int hoehe) {
		JButton btnNeu = new JButton("Neu");
		int y = (int) linkenSizeMenu.getHeight() + 5;
		
		btnNeu.setBounds(5, y, 100, hoehe);
		btnArrayList.add(btnNeu);
		this.add(btnNeu);
		linkenSizeMenu.setSize(linkenSizeMenu.getWidth(), y + hoehe);
	}

	/**Erstellt den Karten-Panel
	 */
	private void iniKartenPanel() {
		JPanel kartenPanel = this.map.getJXMapViewer();
		kartenPanel.setBounds(200, 0, 500, 400);
		getContentPane().add(kartenPanel);
	}
	
	/**Setzt die größe des Karten-Panels
	 * @param breite - int - Beite des Karten-Panels
	 * @param hoehe - int - Höhe des Karten-Panels
	 */
	public void setKartenSize(int breite, int hoehe) {
		map.getJXMapViewer().setBounds(200, 10, breite, hoehe);
		System.out.println(this.getHeight());
		System.out.println(this.getWidth());
	}
	
	/**Setzt die größe des Karten-Panels
	 * @param breite - int - Der Abstand zwischen Karten-Panel und der rechten FensterKante
	 * @param hoehe - int - Der Abstand zwischen Karten-Panel und der untere FensterKante
	 */
	public void setKartenMaxSize(int breite, int hoehe) {
		int sizeBaumBreite = (int) linkenSizeMenu.getWidth();
		int maxBreite = this.getWidth() - (sizeBaumBreite + 40);
		int maxHoehe = this.getHeight() - 75;
		map.getJXMapViewer().setBounds(sizeBaumBreite + 15, 10, maxBreite, maxHoehe - hoehe);
	}
	
	/**Erstellt den Baum teil des Fensters
	 * 
	 */
	private void iniBaum() {
//Knoten erstellen	
		DefaultMutableTreeNode schweden= new DefaultMutableTreeNode("Schweden");
		DefaultMutableTreeNode haiks = new DefaultMutableTreeNode("Haiks");
		DefaultMutableTreeNode kaufhallen = new DefaultMutableTreeNode("kaufhallen");
//Neuen Knoten 1. Stufe erzeugen
		haiks = new DefaultMutableTreeNode("Haiks");
		
//Knoten 2. Stufen erzeugen
		DefaultMutableTreeNode gruppen1 = new DefaultMutableTreeNode("1");
		haiks.add(gruppen1);
		DefaultMutableTreeNode gruppen2 = new DefaultMutableTreeNode("2");
		haiks.add(gruppen2);
		DefaultMutableTreeNode gruppen3 = new DefaultMutableTreeNode("3");
		haiks.add(gruppen3);
		DefaultMutableTreeNode gruppen4 = new DefaultMutableTreeNode("4");
		gruppen3.add(gruppen4);
//Den Knoten 1. Stufe an den Wurzelknoten anhängen
		schweden.add(kaufhallen);
		schweden.add(haiks);
//Den Baum erzeugen und im Panel einfügen
		JTree baum = new JTree(schweden);
		
		this.panelBaum = new JScrollPane();
		this.panelBaum.setViewportView(baum);
		int hoehe = (int) linkenSizeMenu.getHeight();
		int breite = (int) linkenSizeMenu.getWidth();
		this.panelBaum.setBounds(5, 5, hoehe, (int) breite);
		getContentPane().add(this.panelBaum);
		
	}
	
	/**Hier werden die Menü Einstellung festgelegt
	 * 
	 */
	private void iniMenuBar() {
		this.menuBar = new JMenuBar();
		
//Hinzufügen von Menüs		
		JMenu menuFile = new JMenu("Datei");
		this.menuKarte = new JMenu("Karte");
		JMenu menuWegPkt = new JMenu("WegPkt");
		
		menuBar.add(menuFile);
		menuBar.add(menuKarte);
		menuBar.add(menuWegPkt);
		
//Hinzufügen von Untermenüs
		
		JMenu menuTest = new JMenu("Test");

		menuFile.add(menuTest);
		
		JMenuItem menuItemTest = new JMenuItem("test");
		JMenuItem menuItemTest2 = new JMenuItem("test");
		JMenuItem menuItemTest3 = new JMenuItem("test");

		menuTest.add(menuItemTest);
		menuTest.add(menuItemTest2);
		menuTest.add(menuItemTest3);
		
		menuFile.add(menuItemTest);
		
		
		setJMenuBar(menuBar);
	}
	
	/**Erstellt die JMenuItem für die Karten ausWahl mit JRadioButtonMenuItem.
	 */
	public void setKartenMenuItem(ArrayList<String> kartenMenuString) {
		ButtonGroup gruppeKatenAuswahl = new ButtonGroup();	
//ForEachSchleife wird genutzt um die Bezeichnung der JRadioButtonMenuItem zu setzen 		
		for(String bezeichnung: kartenMenuString) {
			rbMenuItemKartAuswahl = new JRadioButtonMenuItem(bezeichnung);
			gruppeKatenAuswahl.add(rbMenuItemKartAuswahl);
			menuKarte.add(rbMenuItemKartAuswahl);
			menuKarteAuswahlItems.add(rbMenuItemKartAuswahl);
		};
	}
	
	
	/**Hier werden Icon, Position und Größe 
	 *  des Fenster festgelegt
	 */
	private void iniFenster() {
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\mkenkel\\OneDrive\\AutoBahnBlume.png"));
		this.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);	//Setz Fester auf VOllbild
		this.setMinimumSize(new Dimension(560, 360));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		this.setBounds(350, 150, 764, 648); // setz die Position

		this.setLayout(null);
		
	}

	/**Übergibt die passende Eigenschaft HauptFenter.java.
	 *
	 * @return menuKarteAuswahlItems - ArrayList<JRadioButtonMenuItem> - 
	 */
	public ArrayList<JRadioButtonMenuItem> getMenuKarteAuswahlItems() {
		return menuKarteAuswahlItems;
	}

	public void addBaum(JTree baum) {
		panelBaum.setViewportView(baum);
	}
	
	public ArrayList<JButton> getButtons() {
		return btnArrayList;
	}

}
