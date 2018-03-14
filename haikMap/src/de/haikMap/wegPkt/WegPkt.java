package de.haikMap.wegPkt;

import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;

/**
 * @author AutoBahnBlume
 *
 */
public class WegPkt extends DefaultWaypoint{
	
	private String name;
	private JPanel wegPktPanel;
	private ImageIcon wegPktBild;
	
	public WegPkt(String name, GeoPosition geoPos) {
		super(geoPos);
		this.setName(name);
		setWegPktBild("/wegPunktBilder/default.png");
		wegPktPanel = new JPanel();
		
	}


	public ImageIcon getWegPktBild() {
		return wegPktBild;
	}

	public void setWegPktBild(String wegPktURL) {
		try {
			Image wegPktImg = ImageIO.read(getClass().getResourceAsStream(wegPktURL));
			wegPktBild = new ImageIcon(wegPktImg);
		} catch (Exception e) {
			System.out.println("Fehler beim laden des Button Icon");
		}
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		if(name.equals("") || name.isEmpty()){
			this.name = "Bitte benennen";
		}else {
			this.name = name;
		}
	}


	public GeoPosition getGeoPos() {
		return getPosition();
	}


	public void setGeoPos(GeoPosition geoPos) {
		setGeoPos(geoPos);
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

}
