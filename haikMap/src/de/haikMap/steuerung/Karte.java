package de.haikMap.steuerung;



import javax.swing.event.MouseInputListener;

import java.util.ArrayList;
import java.util.Set;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.VirtualEarthTileFactoryInfo;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCursor;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;
import org.jxmapviewer.viewer.WaypointPainter;

import de.haikMap.wegPkt.WegPkt;


/**Die Klasse erzeugt ein OpenStreetMap-Objekt, das eine OSM-Karte zuverfühgung stellt.
 * Das JXMapViewer-Objekt muss zu einem JFrame-ContentPane geaddet werden.()
 * 
 * @author AutoBahnBlume
 *
 */
public class Karte {
	
	private JXMapViewer mapViewer;
	private GeoPosition kartenStartPkt;
	private GeoPosition mausKlickPkt;
	private ArrayList<DefaultTileFactory> factories;
	
	public Karte() {
		mapViewer = new JXMapViewer();
		kartenStartPkt = new  GeoPosition(57.006163, 16.218952);
		mausKlickPkt = null;
		initKarte();
	}
	
	public void initKarte() {
		// Create a TileFactoryInfo for OpenStreetMap
		factories =  erstellFactoryInfo();
		
		//Use 12 threads in parallel to load the tiles
		factories.get(0).setThreadPoolSize(12);
				 
		mapViewer.setTileFactory(factories.get(0));
		
		// Setzt den focus der karte und den Zoom
		mapViewer.setZoom(8);
		mapViewer.setAddressLocation(kartenStartPkt);
		
		addAllListener();
	}

	/**Erstellt die zwei TileFactoryInfo und gibt sie als
	 * Array zurück 
	 * 
	 * @return factories - ArrayList<DefaultTileFactory>
	 */
	private ArrayList<DefaultTileFactory> erstellFactoryInfo() {
		// Create a TileFactoryInfo for OpenStreetMap
		TileFactoryInfo osmInfo = new OSMTileFactoryInfo();
		TileFactoryInfo veInfo = new VirtualEarthTileFactoryInfo(VirtualEarthTileFactoryInfo.MAP);
		
		ArrayList<DefaultTileFactory> factories = new ArrayList<DefaultTileFactory>();
		
		factories.add(new DefaultTileFactory(osmInfo));
		factories.add(new DefaultTileFactory(veInfo));
		
		return factories;
		 
	}
	
	/**Setz alle Listener die für interaktion nötig sind.
	 * Sodass durch gedruckt halten der LinkenMausTaste eine bewegung in der karte möglich ist
	 * Und das drehen des MausRads das rein und raus zoomen ermöglicht
	 */
	private void addAllListener() {
		MouseInputListener mia = new PanMouseInputListener(mapViewer);
		mapViewer.addMouseListener(mia);
		mapViewer.addMouseMotionListener(mia);
		mapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCursor(mapViewer));
	}
	
	/**Übergibt das JXMapViewer-Objekt des Objektes
	 * 
	 * @return mapViewer - JXMapViewer 
	 */
	public JXMapViewer getJXMapViewer() {
		return mapViewer;
	}

	/**Übergib die ArrayList von DefaultTileFactory aus
	 * factories-Eigentschaft des Objektes.
	 * @return factories - ArrayList<DefaultTileFactory>
	 */
	public ArrayList<DefaultTileFactory> getFactories() {
		return factories;
	}
	
	/**Setz eine neue GeoPosition und zentiert dort drauf
	 * @param pos - GeoPosition - neue GeoPosition
	 */
	public void gehZu(GeoPosition pos) {
//		mapViewer.setAddressLocation(pos);
		mapViewer.setCenterPosition(pos);
	}
	
	public void zeichneWegPkt(Set<WegPkt> wegPktSet) {
		// Create a waypoint painter that takes all the waypoints
		WaypointPainter<WegPkt> waypointPainter = new WaypointPainter<WegPkt>();
		waypointPainter.setWaypoints(wegPktSet);
		
		mapViewer.setOverlayPainter(waypointPainter);
	}

	public GeoPosition getMausKlickPkt() {
		return mausKlickPkt;
	}

	public void setMausKlickPkt(GeoPosition mausKlickPkt) {
		this.mausKlickPkt = mausKlickPkt;
	}

}
