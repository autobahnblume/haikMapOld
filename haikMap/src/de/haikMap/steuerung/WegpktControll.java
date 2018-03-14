package de.haikMap.steuerung;

import java.util.HashSet;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.Waypoint;

import de.haikMap.fenster.WegPunkteFenster;
import de.haikMap.listener.ButtonListener;
import de.haikMap.listener.WegPktListener;
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
	private WegPunkteFenster wegPktFenster = null;
	private Karte map = null;
	private int abstandLabel = -15;
	private WegPktListener wegPktListener;
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
		this.wegPktFenster = new WegPunkteFenster();
		this.btnListener = new ButtonListener(this);
		this.wegPktListener = new WegPktListener(this);
		this.map = map;
		this.waypoints = new HashSet<Waypoint>();
		wegPktSpeicher = new WegPktSpeicherung();
		
		addBtnListener();	
		wegPktFenster.setVisible(true);
	}

	/**Bindet die Buttons des WegPunkteFenster-Objekt an die
	 * passenden Listener
	 */
	private void addBtnListener() {
		wegPktFenster.getBtnSpeichern().addActionListener(btnListener);
		wegPktFenster.getBtnZeigen().addActionListener(btnListener);		
		wegPktFenster.setVisible(true);
	}

	/**Trägt ein Neues JLabel in den JPanel ein.
	 * @param addetWegPkt
	 * @param WegPunktPane
	 */
	public void nachtragWegPktLabel() {
//		String wegPktName = wegPktFenster.getTxtNameWegpkt().getText() +" ";
//		double wegPktBreitengrad = checkEingabePosition(wegPktFenster.getTxtBreitengrad().getText(),this.BREITENGRAD);
//		double wegPktLaengengrad = checkEingabePosition(wegPktFenster.getTxtLaengengrad().getText(),this.BREITENGRAD);
//		JLabel addetWegPkt = new JLabel(wegPktName);
		WegPkt saveWegPkt = createWegPkt();
		System.out.println("nachtragWegPktLabel " + wegPktSpeicher.isWegPktImArray(saveWegPkt));
		if( wegPktSpeicher.isWegPktImArray(saveWegPkt) == -1) {
			JLabel addetWegPkt = new JLabel(saveWegPkt.getName());
			
			String toolTipText = null;
			toolTipText = "Breitengrad: "+ saveWegPkt.getGeoPos().getLatitude();
			toolTipText +=  " Längengrad: "+ saveWegPkt.getGeoPos().getLongitude();
			addetWegPkt.setToolTipText(toolTipText);
			
			abstandLabel += 16;
			addetWegPkt.setBounds(0, abstandLabel, 270, 14);
//			addetWegPkt.setBorder(new LineBorder(new Color(0, 0, 0)));
			addetWegPkt.addMouseListener(wegPktListener);
			
			JPanel wegPktContanerPanel = wegPktFenster.getWegPunktPanel();
			wegPktContanerPanel.add(addetWegPkt);
			wegPktContanerPanel.repaint();
			wegPktFenster.validate();
		}
			setzWegPkt(saveWegPkt);
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
			System.out.println(zuCheckenStr);
			return 0;
		}catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, msg + " Ist Leer und wird  deshalb auf 0 gesetzt");
			return 0;
		}
		
	}

	private WegPkt getWegPkt(int index) {
		WegPkt wegPkt = wegPktSpeicher.getWegPkt(index);
		return wegPkt;
	}
	
	
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
	
}
