package de.haikMap.fenster;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.TileFactory;

import de.haikMap.steuerung.Karte;

/**Dise Klasse erstellt ein JFrame-Fenster-Objekt, id die die OpenStreetMap-Karte
 * geladen wird. Zusätzlich ist oben noch eine Auswahl der verschiedenen ansichten
 * 
 * @author autobahnblume
 *
 */
public class KartenFenster extends JFrame {

	/**
	 * @param map 
	 * @throws HeadlessException
	 */
	public KartenFenster(Karte map) throws HeadlessException {
		super("KarteFenster");
		initFenster();
		createKartenAuswahl(map);
		getContentPane().add(map.getJXMapViewer());
	}

	/**Erzeugt die Auswahl der verschiedenen Karten Typen
	 */
	private void createKartenAuswahl(Karte map) {
		JPanel auswahl = new JPanel();
		JLabel beschriftung = new JLabel("Wähle den Karten Typ:");
		
		ArrayList<DefaultTileFactory> factories = map.getFactories();
		
		String[] tfLabels = new String[factories.size()];
		for(int i = 0; i < factories.size(); i++) {
			tfLabels[i] = factories.get(i).getInfo().getName();
		}
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		final JComboBox auswahlCombo = new JComboBox(tfLabels);
		auswahlCombo.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				TileFactory factory = factories.get(auswahlCombo.getSelectedIndex());
				map.getJXMapViewer().setTileFactory(factory);
			}
		});
		
		auswahl.setLayout(new GridLayout());
		auswahl.add(beschriftung);
		auswahl.add(auswahlCombo);
		
		add(auswahl, BorderLayout.NORTH);
				
	}

	/**Setzt die Größe und die Position des Fensters.
	 * Setzt SetVisible auf true und 
	 * setDefaultCloseOperation so, dass das ganze Programm geschlossen 
	 * wird.
	 */
	private void initFenster() {
		this.setSize(800, 600);
		this.setLocation(10, 10);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

}
