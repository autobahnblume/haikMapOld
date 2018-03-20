package de.haikMap.steuerung;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.Waypoint;

import de.haikMap.fenster.WegPktFenster;
import de.haikMap.listener.ButtonListener;
import de.haikMap.listener.MausKlickListener;
import de.haikMap.listener.WegPktLabelListener;
import de.haikMap.wegPkt.WegPkt;

/**Koordiert im größenteils die komunikation zwischen 
 * Karten-Fenster und WegPunkt-Fenster
 * 
 * @author autobahnblume
 *
 */
public class WegpktControll {

	private final String BREITENGRAD = "BreitenGrad";
	private final String LAENENGRAD = "LängenGrad";
	
	private ButtonListener btnListener = null;
	private WegPktFenster wegPktFenster = null;
	private Karte map = null;
	private int abstandLabel = -15;
	private WegPktLabelListener wegPktListener;
	// Create waypoints from the geo-positions
	private Set<Waypoint> waypoints = null;
	private WegPktSpeicherung wegPktSpeicher;
	
	/**Konstruktor übergibt den Parameter in zu den eigentschaft
	 * und erstellt ein WegPunkteFenster-Objekt. Auch werden die
	 * zwei Listener ButtonListener(WegpktControll) und 
	 * WegPktListener(WegpktControll) erstellt.
	 * Ruft addBtnListener()-Methode auf. Setz setVisible() des
	 * WegPunkteFenster-Objekt auf true
	 * 
	 * @param map - Karte - die anzuzeigende Karte
	 */
 	public WegpktControll(Karte map) {
		super();
		this.wegPktFenster = new WegPktFenster();
		this.btnListener = new ButtonListener(this);
		this.wegPktListener = new WegPktLabelListener(this);
		this.map = map;
		this.waypoints = new HashSet<Waypoint>();
		wegPktSpeicher = new WegPktSpeicherung();
		
		addBtnListener();	
		addKartenListener();
		setVisibleWegPktFenster(true);
	}
 	
 	/**Setz setVisible von WegPktFenster-Objekt auch true oder false
 	 * @param zeigen - boolean - Ob Das WegPkt-Fenster angezeigt werden soll oder nicht
 	 */
 	public void setVisibleWegPktFenster(boolean zeigen) {
 		wegPktFenster.setVisible(zeigen);
 	}
 	
//TODO weiter machen
 	public void delWegPkt() {
 		String name = wegPktFenster.getName();
 		double breitenGrad = Double.parseDouble(wegPktFenster.getTxtBreitengrad().getText());
 		double laengenGrad = Double.parseDouble(wegPktFenster.getTxtLaengengrad().getText());
 		GeoPosition geoPos = new GeoPosition(breitenGrad, laengenGrad);
 		WegPkt delWegPkt = new WegPkt(name, geoPos);
 		wegPktSpeicher.delWegPkt(delWegPkt);
 	}
 	
 	//TODO kommentieren
 	public void loadWegPkte() {
 		wegPktSpeicher.loadArrayWegpkt();
 		lodeWegPktPanel();
		map.zeichneWegPkt(wegPktSpeicher.getWegPktSet());
 	}
 	//TODO Kommentieren
	private void addKartenListener() {
		MausKlickListener mausL = new MausKlickListener(this);
		map.getJXMapViewer().addMouseListener(mausL);
	}

	/**Bindet die Buttons des WegPunkteFenster-Objekt an die
	 * passenden Listener
	 */
	private void addBtnListener() {
		wegPktFenster.getBtnSpeichern().addActionListener(btnListener);
		wegPktFenster.getBtnZeigen().addActionListener(btnListener);
		wegPktFenster.getBtnReset().addActionListener(btnListener);	
		wegPktFenster.getBtnLaden().addActionListener(btnListener);		
		wegPktFenster.setVisible(true);
	}

	public void setAllTxtFieldText(String name, GeoPosition geoPos) {
		String breitengrad = "" + geoPos.getLatitude();
		String laengengrad = "" + geoPos.getLongitude();
		wegPktFenster.setAllTxtFieldText(name, breitengrad, laengengrad);
	}
	
	/**
	 * 
	 * @param mausPos - GeoPosition - Posistion der Maus
	 */
	public void darstellenMausPosImWegPktFenster(GeoPosition mausPos){
		setAllTxtFieldText("", mausPos);
	}
	
	public void darstellWegPkt(WegPkt showWegPkt) {
		setAllTxtFieldText("MausPosTemp", showWegPkt.getGeoPos());
		System.out.println("--------> darstellWegPkt");
	}
	
	public void darstellWegPkt(JLabel showWegPktLabel) {
		if(wegPktSpeicher.isWegPktLabelImArray(showWegPktLabel) != -1) {
			WegPkt showWegPkt = wegPktSpeicher.getWegPkt(showWegPktLabel);
			setAllTxtFieldText(showWegPkt.getName(), showWegPkt.getGeoPos());
		}else {
			System.out.println("darstellWegPkt: WegPktLabel nicht im Array");
		}
	}
	
	public WegPkt getWeg(int indexWegPkt) {
		return wegPktSpeicher.getWegPkt(indexWegPkt);
	}
	
	public JXMapViewer getJXMapViewer() {
		return map.getJXMapViewer();
		
	}
	
	/**Trägt ein Neues JLabel in den JPanel ein.
	 * @param addetWegPkt
	 * @param WegPunktPane
	 */
 	public void nachtragWegPktLabel() {
		WegPkt saveWegPkt = createWegPkt();
		
		System.out.println("nachtragWegPktLabel " + wegPktSpeicher.isWegPktImArray(saveWegPkt));
		
		if( wegPktSpeicher.isWegPktImArray(saveWegPkt) == -1) {
			JLabel addetWegPktLabel = saveWegPkt.getWegPktLabel();
			saveWegPkt.setToolTipText();
			
			abstandLabel += 16;
			addetWegPktLabel.setBounds(0, abstandLabel, 270, 14);
//			addetWegPkt.setBorder(new LineBorder(new Color(0, 0, 0)));
			addetWegPktLabel.addMouseListener(wegPktListener);
			
			JPanel wegPktContanerPanel = wegPktFenster.getWegPunktPanel();
			wegPktContanerPanel.add(addetWegPktLabel);
			wegPktContanerPanel.repaint();
			
			wegPktFenster.validate();
		}
			setzWegPkt(saveWegPkt);
	}
 	
 	public void lodeWegPktPanel() {
 		ArrayList<WegPkt> tempWegPktList = wegPktSpeicher.getWegPktArrayList();
 		for (WegPkt tempWegPkt : tempWegPktList) {
			JLabel addetWegPktLabel = tempWegPkt.getWegPktLabel();
			tempWegPkt.setToolTipText();
			abstandLabel += 16;
			addetWegPktLabel.setBounds(0, abstandLabel, 270, 14);
//		addetWegPkt.setBorder(new LineBorder(new Color(0, 0, 0)));
			addetWegPktLabel.addMouseListener(wegPktListener);
		
			JPanel wegPktContanerPanel = wegPktFenster.getWegPunktPanel();
			wegPktContanerPanel.add(addetWegPktLabel);
			wegPktContanerPanel.repaint();
		
			wegPktFenster.validate();
		}
 		
		
		
		
 	}
	
	/**Checkt die eingabe vom Längengrad und Breitengrad
	 * und zentriert die karte auf den Punkt 
	 */
	public void zeigWegPkt() {
		WegPkt tempWegPkt = createTempWegPkt();
		map.gehZu(tempWegPkt.getGeoPos());
		setzWegPkt(tempWegPkt);
	}
	
	/**Erzeugt ein WegPkt-Objekt
	 * @return WegPkt - Neu erzeugter WegPkt
	 */
	public WegPkt createWegPkt() {
		String wegPktName = wegPktFenster.getTxtNameWegpkt().getText();
		double latitude = checkEingabePosition(wegPktFenster.getTxtBreitengrad().getText(), this.BREITENGRAD);
		double longitude = checkEingabePosition(wegPktFenster.getTxtLaengengrad().getText(), this.LAENENGRAD);
		
		WegPkt neuerWegPkt = new WegPkt(wegPktName, new GeoPosition(latitude, longitude));
		return neuerWegPkt;
		
	}
	
	/**Erzeugt ein WegPkt-Objekt
	 * @return WegPkt - Neu erzeugter WegPkt
	 */
	public WegPkt createTempWegPkt() {
		WegPkt tempWegPkt = createWegPkt();
		tempWegPkt.setName("TempWegPkt");
		return tempWegPkt;
	}
	
	
	/**Setzt einen WegPunktMarkierung
	 * @param zuZeichnenWegPkt - GeoPosition - position der zu zeichnenden markierung
	 */
	public void setzWegPkt(WegPkt zuZeichnenWegPkt) {
		wegPktSpeicher.addWegPkt(zuZeichnenWegPkt);
		map.zeichneWegPkt(wegPktSpeicher.getWegPktSet());
	}
	
	/**Dient zum umwandeln des BreitenGrad oder LängenGrad String-Wertes in ein gültigen double-Wert
	 * gibt 0 zurück bei ungültigen werten
	 * 
	 * @param zuCheckenStr - String - der zu checkende BreitenGrad- oder LängenGrad-Wert
	 * @param msg - String - Dieht zu erkennung von BreitenGrad oder LängenGrad
	 * @return - double - ein double-wert zurück, der aufgültigkeit geprüft ist
	 */
	private double checkEingabePosition(String zuCheckenStr, String msg) {
		zuCheckenStr = zuCheckenStr.replaceFirst(",", ".").replace(",", "");
		int ersterPkt = zuCheckenStr.indexOf(".");
		int letzterPkt = zuCheckenStr.lastIndexOf(".");
		if(ersterPkt != letzterPkt) {
			String temStr = zuCheckenStr.substring(ersterPkt).replace(".", "");
			zuCheckenStr = zuCheckenStr.substring(0, ersterPkt+1) + temStr;
		}

		ersterPkt = zuCheckenStr.indexOf(".");
		try {
			double zuCheckenDouble = Double.parseDouble(zuCheckenStr);
			if(zuCheckenDouble > 85 && msg.equals(this.BREITENGRAD)) {
				JOptionPane.showMessageDialog(null, msg + " Ist größe oder gleich als 85 und wird deshalb mit Modulor 85");
				return zuCheckenDouble % 85.0;
			}if (zuCheckenDouble < -85 && msg.equals(this.BREITENGRAD)) {
				JOptionPane.showMessageDialog(null, msg + " Ist kleiner oder gleich als -85 und wird deshalb mit Modular 85");
				return zuCheckenDouble % 85.0;
			}if(zuCheckenDouble >= 180 && msg.equals(this.LAENENGRAD)) {
				JOptionPane.showMessageDialog(null, msg + " Ist größe oder gleich als 180 und wird deshalb mit Modulor 180");
				return zuCheckenDouble % 180.0;
			}if (zuCheckenDouble <= -180 && msg.equals(this.LAENENGRAD)) {
				JOptionPane.showMessageDialog(null, msg + " Ist kleiner oder gleich als -180 und wird deshalb mit Modular 180");
				return zuCheckenDouble % 180.0;
			} else {
				return zuCheckenDouble;
			}
			
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, msg + " Hat kein gültigenWert und wird deshalb auf 0 gesetzt");
			
			return 0;
		}catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, msg + " Ist Leer und wird  deshalb auf 0 gesetzt");
			return 0;
		}
		
	}

//	private WegPkt getWegPkt(int index) {
//		WegPkt wegPkt = wegPktSpeicher.getWegPkt(index);
//		return wegPkt;
//	}
	
	
	/**Übergibt den Wert der Eigenschaft
	 * @return - Set<Waypoint>
	 */
	public Set<Waypoint> getWegPktSet() {
		return waypoints;
	}

	
	/**Übergibt den Parameter der passenende Eigenschaft
	 * @param waypoints - Set<Waypoint> - Ein Set<> von Waypoints
	 */
	public void setWaypoints(Set<Waypoint> waypoints) {
		this.waypoints = waypoints;
	}

	/**Gibt die GeoPosition der Maus an die 
	 * darstellenMausPosImWegPktFenster(GeoPosition)-Methode weiter
	 * 
	 * @param mausGeoPos - GeoPosition - Die GeoPosition der Maus
	 */
	public void speicherMausPosition(GeoPosition mausGeoPos) {
		darstellenMausPosImWegPktFenster(mausGeoPos);
	}

	public void speichernArray() {
		wegPktSpeicher.speichernArray();
	}
}
