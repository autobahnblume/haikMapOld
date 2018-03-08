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
		this.name = name;
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

}
