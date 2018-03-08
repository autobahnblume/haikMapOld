package de.haikMap.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import de.haikMap.wegPkt.WegPkt;

/**
 * @author AutoBahnBlume
 *
 */
public class WegPktListener implements MouseListener {

	private WegPkt wegPkt;
	
	public WegPktListener(WegPkt wegPkt) {
		this.wegPkt = wegPkt;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		JOptionPane.showMessageDialog(null, "WegPkt geklickt", null, 0, wegPkt.getWegPktBild());
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		JOptionPane.showMessageDialog(null, "WegPkt gedrückt");
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		JOptionPane.showMessageDialog(null, "WegPkt losgelassen");
		
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
