package de.haikMap;

import java.awt.EventQueue;

import de.haikMap.fenster.KartenFenster;
import de.haikMap.steuerung.Karte;
import de.haikMap.steuerung.WegpktControll;

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
		
		
	}
	
	public ProgrammStart() {
		EventQueue.invokeLater(this);
		greateGUIWegpkt();
	}

	/**Erstellt das GUIWegPkt
	 */
	private void greateGUIWegpkt() {
		new WegpktControll(map);
		
	}

	@Override
	public void run() {
		try {
			kartenFensterStart();
//			JOptionPane.showMessageDialog(null, "Willkommen");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**Erzeugt das Fenster in der die karte angezeigt wird.
	 * @return - kartenFenster - JFrame
	 */
	public static void kartenFensterStart() {
		new KartenFenster(map);
	}
	
}
