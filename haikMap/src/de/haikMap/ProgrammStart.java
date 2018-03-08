package de.haikMap;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.TileFactory;

import de.haikMap.fenster.GUIWegPunkte;
import de.haikMap.steuerung.Karte;

/**Diese Klasse dient als Programm start. Es wird ein Fenster mit der
 * Karte öffnet und ein Fenster zur eingae der wegPunkte.
 * 
 * Es wird Runnable implementiert das mit die Main-Methode übersichtlich
 * bleibt.
 * 
 * @author AutoBahnBlume
 *
 */
public class ProgrammStart implements Runnable{

	private static Karte map;
	
	public static void main(String[] args) {
		System.out.println("Programm start");
		map = new Karte();
		new ProgrammStart();        
		new GUIWegPunkte();
	}
	
	public ProgrammStart() {
		EventQueue.invokeLater(this);
	}

	@Override
	public void run() {
		try {
			fensterStart().getContentPane().add(map.getJXMapViewer());
			JOptionPane.showMessageDialog(null, "Willkommen");
			GUIWegPunkte frame = new GUIWegPunkte();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	/**Erzeugt das Fenster in der die karte angezeigt wird.
	 * @return - kartenFenster - JFrame
	 */
	public static JFrame fensterStart() {
		JFrame kartenFenster = new JFrame("Karte");
		
		kartenFenster.setSize(800, 600);
		kartenFenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		kartenFenster.setLocation(100, 100);
		kartenFenster.setVisible(true);
		
		kartenFenster.add(createKartenAuswahl(), BorderLayout.NORTH);
		
		return kartenFenster;
	}
	
	/**Erzeugt die Auswahl der verschiedenen Karten Typen
	 * 
	 */
	private static JPanel createKartenAuswahl() {
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Wähle den Karten Typ:");
		
		ArrayList<DefaultTileFactory> factories = map.getFactories();
		
		String[] tfLabels = new String[factories.size()];
		for(int i = 0; i < factories.size(); i++) {
			tfLabels[i] = factories.get(i).getInfo().getName();
		}
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		final JComboBox combo = new JComboBox(tfLabels);
		combo.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				TileFactory factory = factories.get(combo.getSelectedIndex());
				map.getJXMapViewer().setTileFactory(factory);
			}
		});
		
		panel.setLayout(new GridLayout());
		panel.add(label);
		panel.add(combo);
		
		return panel;
		
	}

}
