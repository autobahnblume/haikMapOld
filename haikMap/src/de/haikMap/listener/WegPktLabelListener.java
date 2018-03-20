package de.haikMap.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import de.haikMap.steuerung.WegpktControll;

/**
 * @author AutoBahnBlume
 *
 */
public class WegPktLabelListener extends MouseAdapter {

	private WegpktControll wegPktControll;
	
	public WegPktLabelListener(WegpktControll wegPktControll) {
		this.wegPktControll = wegPktControll;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		JLabel wegPktLabel = (JLabel) e.getComponent();
		wegPktControll.darstellWegPkt(wegPktLabel);
//		JOptionPane.showMessageDialog(null, "WegPkt geklickt", null, 0, wegPkt.getWegPktBild());
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
//		JOptionPane.showMessageDialog(null, "WegPkt gedrückt");
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
//		JOptionPane.showMessageDialog(null, "WegPkt losgelassen");
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
