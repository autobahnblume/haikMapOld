package de.haikMap.steuerung;



import javax.swing.event.MouseInputListener;

import java.util.ArrayList;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.VirtualEarthTileFactoryInfo;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCursor;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;


/**Die Klasse erzeugt ein OpenStreetMap-Objekt, das eine OSM-Karte zuverf�hgung stellt.
 * Das JXMapViewer-Objekt muss zu einem JFrame-ContentPane geaddet werden.()
 * 
 * @author AutoBahnBlume
 *
 */
public class Karte {
	
	private JXMapViewer mapViewer;
	private GeoPosition kartenStartPkt;
	private ArrayList<DefaultTileFactory> factories;
	
	public Karte() {
		mapViewer = new JXMapViewer();
		kartenStartPkt = new  GeoPosition(57.006163, 16.218952);
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
	 * Array zur�ck 
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
	
	/**Setz alle Listener die f�r interaktion n�tig sind.
	 * Sodass durch gedruckt halten der LinkenMausTaste eine bewegung in der karte m�glich ist
	 * Und das drehen des MausRads das rein und raus zoomen erm�glicht
	 */
	private void addAllListener() {
		MouseInputListener mia = new PanMouseInputListener(mapViewer);
		mapViewer.addMouseListener(mia);
		mapViewer.addMouseMotionListener(mia);
		
		mapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCursor(mapViewer));
	}
	
	/**�bergibt das JXMapViewer-Objekt des Objektes
	 * 
	 * @return mapViewer - JXMapViewer 
	 */
	public JXMapViewer getJXMapViewer() {
		return mapViewer;
	}

	/**�bergib die ArrayList von DefaultTileFactory aus
	 * factories-Eigentschaft des Objektes.
	 * @return factories - ArrayList<DefaultTileFactory>
	 */
	public ArrayList<DefaultTileFactory> getFactories() {
		return factories;
	}
	
	public void gehZu(GeoPosition pos) {
		mapViewer.setAddressLocation(pos);
	}

}
