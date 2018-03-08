package de.haikMap.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.haikMap.fenster.GUIWegPunkte;

/**Der Listener kümmert sich um die actionen der Buttons
 * Button:
 * - Zeigen
 * - Speichern
 * @author mkenkel
 *
 */
public class ButtonListener implements ActionListener {

	private JTextField addetTxtWegPkt;
	private JPanel wegPunktPanel;
	private GUIWegPunkte tempGuiWegPunkte;

	public ButtonListener(GUIWegPunkte guiWegPunkte) {
		tempGuiWegPunkte = guiWegPunkte;
		addetTxtWegPkt = guiWegPunkte.getTxtNameWegpkt();
		wegPunktPanel = guiWegPunkte.getWegPunktPanel();
	}
	
	/* (nicht-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JLabel addetWegPkt = new JLabel(addetTxtWegPkt.getText());
		tempGuiWegPunkte.nachtragPanel(addetWegPkt, wegPunktPanel);
	}

	
}
