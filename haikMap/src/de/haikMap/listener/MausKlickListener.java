package de.haikMap.listener;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.GeoPosition;

import de.haikMap.steuerung.WegpktControll;

public class MausKlickListener extends MouseAdapter {

	private JXMapViewer viewer = null;
	private MouseEvent evt = null;
	private WegpktControll wegpktControll;
	
	
	public MausKlickListener(WegpktControll wegpktControll) {
		this.wegpktControll = wegpktControll;
		this.viewer = wegpktControll.getJXMapViewer();
	}
	/**
     * Gets called on mouseClicked events, calculates the GeoPosition and fires
     * the mapClicked method that the extending class needs to implement.
     * 
     * @param evt the mouse event
     */
    @Override
    public void mouseClicked(MouseEvent evt) {
    	this.evt = evt;
        final boolean left = SwingUtilities.isLeftMouseButton(evt);
        final boolean rechts = SwingUtilities.isRightMouseButton(evt);
        boolean singleClick = (evt.getClickCount() == 1);
        final boolean doppleClick = (evt.getClickCount() == 2);

        
        if ((left && doppleClick)) {
        	speicherMausPosition();
        }
        if((rechts && singleClick)) {
        	darstellenMausPosistion();
        }
    }
	
    public void speicherMausPosition() {
//		GeoPosition mausGeoPos = getMausGeoPos();
		wegpktControll.speicherMausPosition(getMausGeoPos());
	}
    
    public void darstellenMausPosistion() {
		wegpktControll.darstellenMausPosImWegPktFenster(getMausGeoPos());
	}

	/**Gibt die GeoPosistion des Mauszeigers
	 * 
	 * @return - GeoPosition - 
	 */
	private GeoPosition getMausGeoPos() {
		Rectangle bounds = viewer.getViewportBounds();
		int x = bounds.x + evt.getX();
		int y = bounds.y + evt.getY();
		Point pixelCoordinates = new Point(x, y);
		GeoPosition mausGeoPos = viewer.getTileFactory().pixelToGeo(pixelCoordinates, viewer.getZoom());
		return mausGeoPos;
	}

}
