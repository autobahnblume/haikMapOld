package de.haikMap.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.haikMap.steuerung.WegpktControll;

/**Der Listener kümmert sich um die actionen der Buttons
 * Button:
 * - Zeigen
 * - Speichern
 * @author autobahnblume
 *
 */
public class ButtonListener implements ActionListener{

	private WegpktControll wegPktControll;

	/**
	 * @param wegPunkteFenster
	 */
	public ButtonListener(WegpktControll wegPktControll) {
		this.wegPktControll = wegPktControll;
	}
	
	/* (nicht-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Zeigen":
			wegPktControll.zeigWegPkt();
			break;
		case "Speichern":
			wegPktControll.nachtragWegPktLabel();

		default:
			break;
		}
	}
	
}
