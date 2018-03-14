/**
 * 
 */
package de.haikMap.steuerung;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import de.haikMap.wegPkt.WegPkt;

/**Dient zum Speicher der WegPunkte
 * 
 * @author AutobahnBlume
 *
 */
public class WegPktSpeicherung {

	private Set<WegPkt> wegPktSet = null;
	private ArrayList<WegPkt> wegPktArrayList = null;

	/**
	 * 
	 */
	public WegPktSpeicherung() {
		setWegPktSet(new HashSet<WegPkt>());
		setWegPktArrayList(new ArrayList<WegPkt>());
	}

	/**Addet zum Set<WegPkt> ein neuen WegPkt-Objekt
	 * @param zuZeichnenWegPkt - WegPkt - der neue Wegpunkt
	 */
	public void addToWegPktSet(WegPkt zuZeichnenWegPkt) {
		getWegPktSet().add(zuZeichnenWegPkt);	
	}
	
	public void addToWegPktArrayList(WegPkt zuZeichnenWegPkt) {
		getWegPktArrayList().add(zuZeichnenWegPkt);
	}
	
	/**Addiert den WegPkt zu wegPktSet und wegPktArrayList
	 * indem die passendenen Methoden aufgeruffen werden
	 * @param zuAddWegPkt - WegPkt - Zu addierenten WegPkt
	 */
	public void addWegPkt(WegPkt zuAddWegPkt) {
		if(isWegPktImArray(zuAddWegPkt) == -1) {
			if(!zuAddWegPkt.getName().equals("TempWegPkt")) {
				addToWegPktArrayList(zuAddWegPkt);
			}
			getWegPktSet().add(zuAddWegPkt);
		}
	}

	public int isWegPktImArray(WegPkt zuVergleichWegPkt) {
		int isDa = -1;
		for (WegPkt wegPkt : wegPktArrayList) {
			if (wegPkt.vergleich(zuVergleichWegPkt)) {
				isDa = wegPktArrayList.indexOf(wegPkt);
			}
		}
		return isDa;
	}
	
	public WegPkt getWegPkt(int index) {
		return wegPktArrayList.get(index);
	}
	
	public Set<WegPkt> getWegPktSet() {
		return wegPktSet;
	}

	private void setWegPktSet(Set<WegPkt> wegPktSet) {
		this.wegPktSet = wegPktSet;
	}

	public ArrayList<WegPkt> getWegPktArrayList() {
		return wegPktArrayList;
	}

	public void setWegPktArrayList(ArrayList<WegPkt> wegPktArrayList) {
		this.wegPktArrayList = wegPktArrayList;
	}
	
	
	
}
