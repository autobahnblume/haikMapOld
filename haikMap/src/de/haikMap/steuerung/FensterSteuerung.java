package de.haikMap.steuerung;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactory;

import com.sun.glass.events.KeyEvent;

import de.haikMap.fenster.HauptFenter;
import de.haikMap.listener.MenuItemListener;
import de.haikMap.wegPkt.WegPkt;

/**
 * @author AutoBahnBlume
 *
 */
public class FensterSteuerung {
	
	private HauptFenter hauptFenster;
	private Karte mapKlein = null;
	private WegPktSpeicherung wegPktSpeicher;
//	private WegpktControll wegPktControll;

	public FensterSteuerung() {
//		Karte mapGross = new Karte();
		this.mapKlein = new Karte();
//		wegPktControll = new WegpktControll(mapKlein);
		
		hauptFenster = new HauptFenter(mapKlein);
		
		this.erstellKartenAuswahl();
		hauptFenster.setKartenMaxSize(0, 0);
		
		this.loadWegPkt();
		
		this.addButtonListener();
		
//		new WegpktControll(mapKlein);

//		new KartenFenster(mapGross);
	}
	
	/**Addet die Button zu eine ButtonListener
	 */
	private void addButtonListener() {
		ActionListener btnAl = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Automatisch generierter Methodenstub
				
			}
		};
		for(JButton btn: hauptFenster.getButtons()) {
			btn.addActionListener(btnAl);
		}
	}

	/**Erstellt die Menüeinträge für die Kartenauswahl
	 * Und fügt die MenuItem in ein MenuItemListener hinzu
	 */
	private void erstellKartenAuswahl() {
		ArrayList<DefaultTileFactory> factories = mapKlein.getFactories();	
		
		ArrayList<String> kartenMenuString = new ArrayList<>();
		for(TileFactory tileFactory: factories) {
			kartenMenuString.add(tileFactory.getInfo().getName());
		}
		ActionListener katenAuswahlListener = new MenuItemListener(mapKlein, factories);
		hauptFenster.setKartenMenuItem(kartenMenuString);	
		
		this.addMenuItemListener(katenAuswahlListener);
	}
	
	/**Lädt die gespeicherten WegPkt in den JTree
	 * 
	 */
	private void loadWegPkt() {
		this.wegPktSpeicher = new WegPktSpeicherung();
		wegPktSpeicher.loadArrayWegpkt();
		
		this.loadBaumEintraege(wegPktSpeicher.getWegPktSet());
		
	}

	/**Lädt die WegPkt'e in den JTree und erstellt drei DefaultMutableTreeNode.
	 * Die DefaultMutableTreeNode mussen noch angepasst werden
	 * @param wegPktSet - Set<WegPkt> - Die WegPkt'e die in den Baum geladen werden soll.
	 */
	private void loadBaumEintraege(Set<WegPkt> wegPktSet) {
		DefaultMutableTreeNode schweden= new DefaultMutableTreeNode("Schweden");
		DefaultMutableTreeNode haiks = new DefaultMutableTreeNode("Haiks");
		DefaultMutableTreeNode kaufhallen = new DefaultMutableTreeNode("Shopping");
		
		for(WegPkt wegPkt: wegPktSet) {
			String nameWegPkt = wegPkt.getName();
			GeoPosition pos = wegPkt.getGeoPos();
			DefaultMutableTreeNode name = new DefaultMutableTreeNode(nameWegPkt);
			name.add(new DefaultMutableTreeNode(pos));
			haiks.add(name);
			
		}
		schweden.add(haiks);
		
//Den Knoten 1. Stufe an den Wurzelknoten anhängen
		schweden.add(kaufhallen);
		schweden.add(haiks);
//Den Baum erzeugen und im Panel einfügen
		JTree baum = new JTree(schweden);
//Listener an den Baum anhängen	
		baum.addTreeSelectionListener(new TreeSelectionListener() {
			
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				TreePath path =  e.getPath();
				zeigWegPkt(path);
			}
		});
//Anzeige des ausgewählten Zweiges	
//		JLabel anzeige = new JLabel("ausgewählt");
//		add(baum);
		hauptFenster.addBaum(baum);
	}

	/**Zentriert die Karte und setz ein Temporen WegPkt, wenn ein TreePath
	 * mit GeoPosition Informationen angeklickt wurde
	 * @param path - Der Angeklickte TreePath
	 */
	public void zeigWegPkt(TreePath path){
		try {
			GeoPosition zeigen = treePathToGeoPos(path);
			mapKlein.gehZu(zeigen);
			HashSet<WegPkt> wegPktSet = new HashSet<WegPkt>();
			wegPktSet.add(new WegPkt("", zeigen));
			mapKlein.zeichneWegPkt(wegPktSet);
		}catch (NullPointerException npe) {
//			System.out.println("Keine GeoPosition informationen vorhanden.");
		} catch (Exception e) {
			System.out.println(e.toString());
		}
//		System.out.println( path.getPathCount() + " <<<-");
	}

	/**Dient zum extrahieren der GeoPosition aus dem TreePath.
	 * 
	 * @param path - TreePath - Der Angeklickte TreePath, der zu einer GeoPosition gemacht werden soll
	 * @return GeoPosition 	- Die GeoPosition die aus TreePath extrahiert werden kann
	 * 						- null-Objekt wenn keine GeoPosition informationen vohanden sind
	 */
	private GeoPosition treePathToGeoPos(TreePath path){
		double laenge = 0;
		double breite = 0;
		String pos = path.getLastPathComponent().toString();
		pos  = pos.replace('[', ' ');
		pos  = pos.replace(']', ' ');
		try {
			String[] geoPos  = pos.split(",");
			laenge = Double.parseDouble(geoPos[1]);
			breite = Double.parseDouble(geoPos[0]);
			
		} catch (Exception e) {
			return null;
		}
		return  new GeoPosition(breite, laenge);
		
	}

	/**Addet MenuItemListener und setzt für die MenuItem ein TastenKürzel.
	 * TastelKürzel für:
	 * - OpenStreetMap-MenuItem o
	 * - Virtaul Earth-MenuItem v
	 * @param katenAuswahlListener
	 */
	private void addMenuItemListener(ActionListener katenAuswahlListener) {
		ArrayList<JRadioButtonMenuItem> MenuKarteAuswahlItems = hauptFenster.getMenuKarteAuswahlItems();
		for(JRadioButtonMenuItem radioMenu: MenuKarteAuswahlItems) {
			if(radioMenu.getText().equals("OpenStreetMap")) {
				radioMenu.setSelected(true);
				radioMenu.setMnemonic(KeyEvent.VK_O);
//				radioMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.ALT_MASK));
				radioMenu.setAccelerator(KeyStroke.getKeyStroke('o'));
			}else {
				radioMenu.setMnemonic(KeyEvent.VK_V);
//				radioMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.ALT_MASK));
				radioMenu.setAccelerator(KeyStroke.getKeyStroke('v'));
			}
			radioMenu.addActionListener(katenAuswahlListener);
		}
	}
	
	

}
