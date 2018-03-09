package de.haikMap.steuerung;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import org.jxmapviewer.viewer.GeoPosition;

import de.haikMap.fenster.WegPunkteFenster;
import de.haikMap.listener.ButtonListener;
import de.haikMap.listener.WegPktListener;
import javafx.geometry.Bounds;

/**
 * @author autobahnblume
 *
 */
public class WegpktControll {
	
	private ButtonListener btnListener = null;
	private WegPunkteFenster wegPktFenster = null;
	private Karte map = null;
	private int abstandLabel = -15;
	private WegPktListener wegPktListener;
	
	/**
	 * @param map
	 */
	public WegpktControll(Karte map) {
		super();
		this.wegPktFenster = new WegPunkteFenster();
		this.btnListener = new ButtonListener(this);
		this.wegPktListener = new WegPktListener(this);
		this.map = map;
		
		wegPktFenster.getBtnSpeichern().addActionListener(btnListener);
		wegPktFenster.getBtnZeigen().addActionListener(btnListener);		
		wegPktFenster.setVisible(true);
	}

	/**Trägt ein Neues JLabel in den JPanel ein.
	 * @param addetWegPkt
	 * @param WegPunktPane
	 */
	public void nachtragWegPktLabel() {
		String wegPktName = wegPktFenster.getTxtNameWegpkt().getText() +" ";
		String wegPktBreitengrad = wegPktFenster.getTxtBreitengrad().getText() +" ";
		String wegPktLaengengrad = wegPktFenster.getTxtLaengengrad().getText() +" ";
		JLabel addetWegPkt = new JLabel(wegPktName);

		String toolTipText = null;
		toolTipText = "Breitengrad: "+ wegPktLaengengrad;
		toolTipText +=  "Längengrad: "+ wegPktLaengengrad;
		addetWegPkt.setToolTipText(toolTipText);
		
		abstandLabel += 16;
		addetWegPkt.setBounds(0, abstandLabel, 270, 14);
//		addetWegPkt.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		addetWegPkt.addMouseListener(wegPktListener);
		
		JPanel WegPunktPanel = wegPktFenster.getWegPunktPanel();
		WegPunktPanel.add(addetWegPkt);
		WegPunktPanel.repaint();
		wegPktFenster.validate();
	}

	
	public void zeigWegPkt() {
		double latitude = 57.016163;
		double longitude = 16.218952;
		
		latitude = Double.parseDouble(wegPktFenster.getTxtBreitengrad().getText());
		longitude = Double.parseDouble(wegPktFenster.getTxtLaengengrad().getText());
		
		GeoPosition pos = new GeoPosition(latitude, longitude);
		map.gehZu(pos);
		
	}
}
