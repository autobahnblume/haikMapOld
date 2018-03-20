package de.haikMap.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.TileFactory;

import de.haikMap.steuerung.Karte;

public class MenuItemListener implements ActionListener {

	private ArrayList<DefaultTileFactory> factories;
	private Karte map;

	public MenuItemListener(Karte map, ArrayList<DefaultTileFactory> factories) {
		this.map = map;
		this.factories = factories;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		for(TileFactory tileFactory: factories) {
			String nameTileFactory = tileFactory.getInfo().getName();
			if(nameTileFactory.equals(e.getActionCommand())) {
				map.getJXMapViewer().setTileFactory(tileFactory);
			}
		}
	}

}
