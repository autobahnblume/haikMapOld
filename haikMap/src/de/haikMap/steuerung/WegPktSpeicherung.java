/**
 * 
 */
package de.haikMap.steuerung;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JLabel;

import org.jxmapviewer.viewer.GeoPosition;

import de.haikMap.wegPkt.WegPkt;

/**Dient zum Speicher der WegPunkte
 * 
 * @author AutobahnBlume
 *
 */
public class WegPktSpeicherung {

	private static final String WEG_PKTE_DATEI = "wegPkte.txt";
	private Set<WegPkt> wegPktSet = null;
	private ArrayList<WegPkt> wegPktArrayList = null;
	private ArrayList<WegPkt> saveWegPktArrayList = null;
	

	/**
	 * 
	 */
	public WegPktSpeicherung() {
		setWegPktSet(new HashSet<WegPkt>());
		setWegPktArrayList(new ArrayList<WegPkt>());
	}

	/**Speichert die einzelnen WegPkt in einer Datein ab.
	 * 
	 */
	public void speichernArray() {
		saveWegPktArrayList = wegPktArrayList;
		File datei = new File(WEG_PKTE_DATEI);
		try {
			FileWriter dateiSchreiber = new FileWriter(datei);
			for (WegPkt wegPkt : saveWegPktArrayList) {
				dateiSchreiber.write(wegPkt.toString());
				dateiSchreiber.write(System.getProperty("line.separator"));
			}
			dateiSchreiber.flush();
			dateiSchreiber.close();
		} catch (IOException e) {
			System.out.println("Fehler beim Array Speichern");
			e.printStackTrace();
		}
	}
//TODO weiter machen
	public void delWegPkt(WegPkt delWegPkt) {
		getWegPktArrayList().remove(delWegPkt);
	}
	
	/**Liest eine Line der Datei
	 * 
	 */
	public void loadArrayWegpkt() {
		try {
			FileReader datei = new FileReader(WEG_PKTE_DATEI);
			BufferedReader buffDatei = new BufferedReader(datei);
			

			double laengenGrad = -1;
			double breitenGrad = -1;
			String name = null;
			String geoPos = null;
			
			while (buffDatei.ready()) {
				String dateiLinie = buffDatei.readLine();
				int trenner = dateiLinie.indexOf(":");
				name = dateiLinie.substring(0, trenner);
				geoPos = dateiLinie.substring(trenner+2);
				trenner = geoPos.indexOf(" ");
				laengenGrad = Double.parseDouble(geoPos.substring(0, trenner));
				breitenGrad = Double.parseDouble(geoPos.substring(trenner + 1));
				addWegPkt(new WegPkt(name, new GeoPosition(breitenGrad, laengenGrad)));
			}
			buffDatei.close();
			datei.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("Fehler beim lesen der Datei");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Fehler beim lesen der Datei");
			e.printStackTrace();
		}
	}
	
	/**Addet zum Set<WegPkt> ein neuen WegPkt-Objekt
	 * @param zuZeichnenWegPkt - WegPkt - der neue Wegpunkt
	 */
	public void addToWegPktSet(WegPkt zuZeichnenWegPkt) {
		getWegPktSet().add(zuZeichnenWegPkt);	
	}
	
	public void addToWegPktArrayList(WegPkt zuZeichnenWegPkt) {
		getWegPktArrayList().add(zuZeichnenWegPkt);
	}
	
	/**Addiert den WegPkt zu wegPktSet und wegPktArrayList
	 * indem die passendenen Methoden aufgeruffen werden
	 * @param zuAddWegPkt - WegPkt - Zu addierenten WegPkt
	 */
	public void addWegPkt(WegPkt zuAddWegPkt) {
		if(isWegPktImArray(zuAddWegPkt) == -1) {
			if(!zuAddWegPkt.getName().equals("TempWegPkt")) {
				addToWegPktArrayList(zuAddWegPkt);
			}
			getWegPktSet().add(zuAddWegPkt);
		}
	}

	/**Geht durch die ArrayList wegPktArrayList durch und ruft vergleich()-Methode des WegPkt 
	 * auf und gibt den index wo er in der ArrayList steht.
	 * -1 wird zurück gegeben wenn kein WegPkt in der Arraylist ist.
	 * @param zuVergleichWegPkt - WegPkt - Der WegPkt der vergliechen werden soll.
	 * @return	- Gibt den index des WegPkt in der ArrayList<WegPkt>
	 * 			- Gibt -1 zurück, wenn der WegPkt nicht in dem Arraylist is
	 */
	public int isWegPktImArray(WegPkt zuVergleichWegPkt) {
		int isDa = -1;
		for (WegPkt wegPkt : wegPktArrayList) {
			if (wegPkt.vergleich(zuVergleichWegPkt)) {
				isDa = wegPktArrayList.indexOf(wegPkt);
			}
		}
		return isDa;
	}
	
	/**Geht durch die ArrayList wegPktArrayList durch und ruft vergleichLabel()-Methode des WegPkt 
	 * auf und gibt den index wo er in der ArrayList steht.
	 * -1 wird zurück gegeben wenn kein WegPkt in der Arraylist ist.
	 * @param zuVergleichWegPktLabel - JLabel - Das JLaben das mit dem WegPkt'en verglichen werden soll.
	 * @return 	- Gibt den index des WegPkt in der ArrayList<WegPkt>
	 * 			- Gibt -1 zurück, wenn der WegPkt nicht in dem Arraylist ist
	 */
	public int isWegPktLabelImArray(JLabel zuVergleichWegPktLabel) {
		int isDa = -1;
		for (WegPkt wegPkt : wegPktArrayList) {
			if (wegPkt.vergleichLabel(zuVergleichWegPktLabel)) {
				isDa = wegPktArrayList.indexOf(wegPkt);
			}
		}
		return isDa;
	}
	
	/**
	 * @param zuVergleichWegPktLabel
	 * @return
	 */
	public WegPkt getWegPkt(JLabel zuVergleichWegPktLabel) {
		int wegPktIndex = -1;
		for (WegPkt wegPkt : wegPktArrayList) {
			if (wegPkt.vergleichLabel(zuVergleichWegPktLabel)) {
				wegPktIndex = wegPktArrayList.indexOf(wegPkt);
			}
		}
		return  wegPktArrayList.get(wegPktIndex);
	}
	
	/**
	 * @param index
	 * @return
	 */
	public WegPkt getWegPkt(int index) {
		return wegPktArrayList.get(index);
	}
	
	public Set<WegPkt> getWegPktSet() {
		return wegPktSet;
	}

	private void setWegPktSet(Set<WegPkt> wegPktSet) {
		this.wegPktSet = wegPktSet;
	}

	public ArrayList<WegPkt> getWegPktArrayList() {
		return wegPktArrayList;
	}

	/**
	 * @param wegPktArrayList
	 */
	public void setWegPktArrayList(ArrayList<WegPkt> wegPktArrayList) {
		this.wegPktArrayList = wegPktArrayList;
	}
	
}
