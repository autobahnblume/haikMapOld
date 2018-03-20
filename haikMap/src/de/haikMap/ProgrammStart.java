package de.haikMap;

import java.awt.EventQueue;

import de.haikMap.steuerung.FensterSteuerung;

/**Diese Klasse dient als Programm start.
 * 
 * Es wird Runnable implementiert das mit die Main-Methode übersichtlich
 * bleibt.
 * 
 * @author AutoBahnBlume
 *
 */
public class ProgrammStart implements Runnable{
	
	public static void main(String[] args) {
		System.out.println("Programm start");
		new ProgrammStart();	
	}
	
	public ProgrammStart() {
		EventQueue.invokeLater(this);
		
	}

	@Override
	public void run() {
		try {
			new FensterSteuerung();
//			JOptionPane.showMessageDialog(null, "Willkommen");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
