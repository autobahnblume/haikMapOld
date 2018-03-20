package de.haikMap.wegPkt;

import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;

/**Das Objekt dieser Klasse bildet einen WegPunkt ab.
 * Es wird von DefaultWaypoint geerbt um die grundeigenschaften
 * eines WegPunktes zu bekommen. Zusätzlich werden noch weitere
 * Eigenschaften, die für das Programm nötig sein, eingepflegt.
 * 
 * @author AutoBahnBlume
 *
 */
public class WegPkt extends DefaultWaypoint{
	
	private String name;
//	private JPanel wegPktPanel;
	private ImageIcon wegPktBild;
	private String toolTipText = null;
	private JLabel wegPktLabel = null;
	
	/**Der Konstruktor übergibt den Parameter "geoPos" (GeoPosition) an den
	 * Super-konstruktor weiter. Auch wird der Parameter "name" (String)
	 * der passenden Objekt-Eigenschaft übergeben und das default ImageIcon
	 * gesetzt.
	 * 
	 * @param name - String - Name des WegPkt
	 * @param geoPos - GeoPosition - GeoPosition des WegPkt
	 */
	public WegPkt(String name, GeoPosition geoPos) {
		super(geoPos);
		this.wegPktLabel = new JLabel(name);
		this.setName(name);
		setWegPktBild("/wegPunktBilder/default.png");
//		wegPktPanel = new JPanel();
	}
	
	public String toString() {
		String laengenGrad = " " + getGeoPos().getLongitude();
		String breitenGrad = " " + getGeoPos().getLatitude();
		return getName() + ":" + laengenGrad + breitenGrad;
	}

	/**Erstellt den ToolTip-String und addet den gespeicherten
	 * String als ToolTipText dem Jlabel
	 * 
	 * @param addetWegPkt - JLabel - JLabel des ToolTips
	 */
	public void setToolTipText() {
		toolTipText = "Breitengrad: "+ getGeoPos().getLatitude();
		toolTipText +=  " Längengrad: "+ getGeoPos().getLongitude();
		wegPktLabel.setToolTipText(toolTipText);
	}

	/**Übergibt die passende Eigenschaft.
	 * @return - ImageIcon
	 */
	public ImageIcon getWegPktBild() {
		return wegPktBild;
	}

	/**Setz das Image was als WegPktIcon genutzt werden soll-
	 * 
	 * @param wegPktURL - String - Die URL des Image
	 */
	public void setWegPktBild(String wegPktURL) {
		try {
			Image wegPktImg = ImageIO.read(getClass().getResourceAsStream(wegPktURL));
			wegPktBild = new ImageIcon(wegPktImg);
		} catch (Exception e) {
			System.out.println("Fehler beim laden des Button Icon");
		}
	}


	/**Übergibt die passende Eigenschaft.
	 * @return
	 */
	public String getName() {
		return name;
	}


	/**Setz den Parameter-Wert als Name des WegPktes, wenn der Werte
	 * zeichen enthalt. Sollte diese nicht der Fall seinen wird als
	 * WegPkt name "Bitte benennen" gesetzt.
	 * 
	 * @param name - String - Der Name des WegPktes
	 */
	public void setName(String name) {
		if(name.equals("") || name.isEmpty()){
			this.name = "Bitte benennen";
			this.wegPktLabel.setText("Bitte benennen");
		}else {
			this.name = name;
		}
	}


	/**Übergibt die passende Eigenschaft.
	 * @return
	 */
	public GeoPosition getGeoPos() {
		return getPosition();
	}


	/**Setz die passende Eigenschaft.
	 * @param geoPos - GeoPosition - Die GeoPosition des WegPkt
	 */
	public void setGeoPos(GeoPosition geoPos) {
		setGeoPos(geoPos);
	}
	
	/**Übergibt die passende Eigenschaft.
	 * @return - String - 
	 */
	public String getToolTipText() {
		return toolTipText;
	}

	/**Übergibt die passende Eigenschaft.
	 * @return wegPktLabel
	 */
	public JLabel getWegPktLabel() {
		return wegPktLabel;
	}

	/**Übergibt des Parameter-Wert die passende Eigenschaft.
	 * @param wegPktLabel - JLabel - das zu setzende Objekt wegPktLabel
	 */
	public void setWegPktLabel(JLabel wegPktLabel) {
		this.wegPktLabel = wegPktLabel;
	}
	
	/**Vergleicht ein WegPkt-Objekt mit diesen WegPkt
	 * @param zuVergleichen - WegPkt - Der zu vergleichende WegPkt
	 * @return -boolean - true wenn es der gleiche wegPkt ist
	 */
	public boolean vergleich(WegPkt zuVergleichen) {
		if(this.getName().equals(zuVergleichen.getName())) {
			if(this.getPosition().equals(zuVergleichen.getPosition())) {
				return true;
			}else {
				return false;				
			}
		}else {
			return false;
		}
	}


	public boolean vergleichLabel(JLabel zuVergleichLabel) {
		if(wegPktLabel.hashCode() == zuVergleichLabel.hashCode()) {
			return true;
		}
		return false;
	}

}
