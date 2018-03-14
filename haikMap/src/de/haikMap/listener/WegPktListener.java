package de.haikMap.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import de.haikMap.steuerung.WegpktControll;
import de.haikMap.wegPkt.WegPkt;

/**
 * @author AutoBahnBlume
 *
 */
public class WegPktListener extends MouseAdapter {

	private WegPkt wegPkt;
	
	public WegPktListener(WegpktControll wegPktControll) {
		this.wegPkt = wegPkt;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		JLabel wegPkt = (JLabel) e.getComponent();
		System.out.println(wegPkt.getText());
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
