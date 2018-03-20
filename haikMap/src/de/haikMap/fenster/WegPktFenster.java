package de.haikMap.fenster;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JLabel;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Toolkit;

/**https://dbs.cs.uni-duesseldorf.de/lehre/docs/java/javabuch/html/k100241.html
 * http://openbook.rheinwerk-verlag.de/javainsel9/javainsel_19_010.htm#mj66f472599b8b53fe93ea7adde5f179c3
 */
public class WegPktFenster extends JFrame {

	private final int NORTH = 0;
	private final int EAST = 1;
	private final int SOUTH = 2;
	private final int WEST = 3;
	
	private final int X = 0;
	private final int Y = 1;
	private final int BREITE = 2;
	private final int HOEHE = 3;
	
	public int abstandLabel =-15;
	private JTextField txtNameWegpkt;
	private JTextField txtBreitengrad;
	private JTextField txtLaengengrad;
	private JPanel wegPunktPanel;
	private JPanel scrollPanel;

	private JButton btnSpeichern;
	private JButton btnZeigen;
	private JButton btnReset;
	private JButton btnLaden;
	
	/**
	 * Create the frame.
	 */
	public WegPktFenster() {
		/**Unnötig???
		setLocation(new Point(1200, 900));*/
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\mkenkel\\OneDrive\\AutoBahnBlume.png"));
		setMinimumSize(new Dimension(560, 360));
		setTitle("WegPunkte");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(850, 10, 564, 448); // setz die Position

		greateGrundPanel();	
	}

	/**Erstellt das GrundPanel in das JScrollPane kommt.
	 */
	private void greateGrundPanel() {
//Erstellung des GrundPanel-----------------------------------
		JPanel contentPane = new JPanel();
		contentPane.setBackground(Color.YELLOW);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 10));
//------------------------------------------------------------	
		
//Erstellung des JScrollPane-----------------------------------	
		
		JScrollPane scrollPane = new JScrollPane();
//Erstellung des festen Teil
		createFesterPanel(scrollPane);
//Erstellung des scrollbaren Teil
		createScrollbarPanel(scrollPane);
//------------------------------------------------------------	
		getContentPane().add(scrollPane);
	}

	/**Erstellt das FestePanel in den alle Komponenten kommen,
	 * die nicht Scrollbar seien sollen
	 * 
	 * @param aListener - listener für die Buttons
	 * @param scrollPane - Container Panel für das hier erstellte Panel
	 */
	private void createFesterPanel(JScrollPane scrollPane) {
	
		JPanel festesPanel = new JPanel();
		festesPanel.setPreferredSize(new Dimension(250, 10));
		festesPanel.setBackground(Color.PINK);
		festesPanel.setPreferredSize(new Dimension(260, 100));
		scrollPane.setRowHeaderView(festesPanel);
		
		festesPanel.setLayout(new SpringLayout());
	
	//		SpringLayout layoutFestPanel = new SpringLayout();
		SpringLayout layoutFestPanel = (SpringLayout) festesPanel.getLayout();
	//Create der einzelnen Panels zum einfügen-------------------------		
		createPanalNameWegpkt(festesPanel);
		createPanelBreitengrad(festesPanel);
		createPanelLaengengrad(festesPanel);
		createAllBtn(festesPanel);
	//-----------------------------------------------------------------		
			
	}

	/**
	 * @param scrollPane
	 */
	private void createScrollbarPanel(JScrollPane scrollPane) {
		wegPunktPanel = new JPanel();
		wegPunktPanel.setBackground(Color.CYAN);
		wegPunktPanel.setLayout(null);
		
//		scrollPane = new JScrollPane();
		scrollPane.setViewportView(wegPunktPanel);
		
	}
	
	/**
	 * @param festesPanel
	 */
	private void createPanalNameWegpkt(JPanel festesPanel) {
		JPanel panelNameWegpkt = new JPanel();
//		panelNameWegpkt.setLayout(null);
		setPosToSpringLayout(panelNameWegpkt, panelPosision(10, -10, -328, 10), festesPanel);
		
		JLabel lblNameDesWegpunktes = new JLabel("Name des WegPunktes");
		setPosToNullLayout(lblNameDesWegpunktes, panelPosision(0, 0, 200, 20), panelNameWegpkt);
		
		txtNameWegpkt = new JTextField();
		setPosToNullLayout(txtNameWegpkt, panelPosision(0, 20, 200, 20), panelNameWegpkt);
		
	}

	/**
	 * @param festesPanel
	 * @param layoutFestPanel
	 */
	private void createPanelBreitengrad(JPanel festesPanel) {
		JPanel breitengradPanel = new JPanel();
		setPosToSpringLayout(breitengradPanel, panelPosision(90, -139, -248, 10), festesPanel);
		
		breitengradPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Breitengrad");
		lblNewLabel.setBounds(0, 0, 100, 20);
		breitengradPanel.add(lblNewLabel);
		
		txtBreitengrad = new JTextField();
		txtBreitengrad.setBounds(0, 20, 100, 20);
		
		breitengradPanel.add(txtBreitengrad);
	}

	/**Erstellt das LängengradPanel
	 * @param festesPanel - JPanel - JPanel in das JPanel geaddet werden soll
	 */
	private void createPanelLaengengrad(JPanel festesPanel) {
		JPanel laengengradPanel = new JPanel();
		
		setPosToSpringLayout(laengengradPanel, panelPosision(90, -10, -248, 127), festesPanel);
//		laengengradPanel.setBounds(0, 0, 100, 20);
//		festesPanel.add(laengengradPanel);
		
		
		laengengradPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("L\u00E4ngengrad");
		lblNewLabel.setBounds(0, 0, 100, 20);
		laengengradPanel.add(lblNewLabel);
		
		txtLaengengrad = new JTextField();
		txtLaengengrad.setBounds(0, 20, 100, 20);
		
		laengengradPanel.add(txtLaengengrad);
	}
	
	/**
	 * @param einZuOrdnenenPanel -JPanel
	 * @param behaelterPanel - JPanel
	 * @param layout - SpringLayout
	 */
	private void setPosToSpringLayout(JComponent einZuOrdnenenPanel, int[]pos, JPanel behaelterPanel) {
		SpringLayout layout = (SpringLayout) behaelterPanel.getLayout();
		behaelterPanel.setLayout(layout);
		layout.putConstraint(SpringLayout.NORTH, einZuOrdnenenPanel, pos[NORTH], SpringLayout.NORTH, behaelterPanel);
		layout.putConstraint(SpringLayout.EAST, einZuOrdnenenPanel, pos[EAST], SpringLayout.EAST, behaelterPanel);
		layout.putConstraint(SpringLayout.SOUTH, einZuOrdnenenPanel, pos[SOUTH], SpringLayout.SOUTH, behaelterPanel);
		layout.putConstraint(SpringLayout.WEST, einZuOrdnenenPanel, pos[WEST], SpringLayout.WEST, behaelterPanel);
		behaelterPanel.add(einZuOrdnenenPanel);
	}
	
	private void setPosToNullLayout(JComponent einZuOrdnenenPanel, int[]pos, JPanel behaelterPanel){
		behaelterPanel.setLayout(null);
		einZuOrdnenenPanel.setBounds(pos[X], pos[Y], pos[BREITE], pos[HOEHE]);
		behaelterPanel.add(einZuOrdnenenPanel);
	}
	
	/**Füllt das Array mit den Parametern und gibt es zurück
	 * @param noth - int - Nördliche ausrichtung
	 * @param east - int - Östliche ausrichtung
	 * @param south - int - Südliche ausrichtung
	 * @param west - int - Westliche ausrichtung
	 * @return position - int-Array - Mit den ausrichtungen
	 */
	private int[] panelPosision(int noth, int east, int south, int west) {
		int[] position = {0, 0, 0, 0};
		position[NORTH] = noth;
		position[EAST] = east;
		position[SOUTH] = south;
		position[WEST] = west;
		return position;
	}
	private int[] panelPosisionNull(int x, int y, int breite, int hoehe) {
		int[] position = {0, 0, 0, 0};
		position[X] = x;
		position[Y] = y;
		position[BREITE] = breite;
		position[HOEHE] = hoehe;
		return position;
	}


	/**
	 * @param nameWegpktPanel
	 * @param sl_nameWegpktPanel
	 * @param lblNameDesWegpunktes
	 * @param btnSpeichern
	 * @param festesPanel 
	 */
	private void createTxtNameWegpkt(JPanel nameWegpktPanel, JLabel lblNameDesWegpunktes, JPanel festesPanel) {
			txtNameWegpkt = new JTextField();
			
			setPosToSpringLayout(txtNameWegpkt, panelPosision(19, -5, -20, 0), nameWegpktPanel);
			txtNameWegpkt.setColumns(10);
	}

	/**Erstellt das LängengradLabel
	 * @param behaelterPanel - JPanel - JPanel in das Jlabel geaddet werden soll
	 * Label posistion rätselhaft
	 */
	private void createLblLaengengrad(JPanel behaelterPanel) {
		JLabel lblLngengrad = new JLabel("L\u00E4ngengrad");
		setPosToSpringLayout(lblLngengrad, panelPosision(-19, -5, -20, 0), behaelterPanel);
	}

	/**Erstellt das LängengradJ-TextField
	 * @param laengengradPanel - JPanel - JPanel in das JTextField geaddet werden soll
	 */
	private void createTxtLaengengrad(JPanel laengengradPanel) {
		txtLaengengrad = new JTextField();
		setPosToSpringLayout(txtLaengengrad, panelPosision(19, -5, -20, 0), laengengradPanel);
		txtLaengengrad.setColumns(10);
	}

	/**Erstellt die benötigten Buttons. Zudem wir ein Button als DefaultButton gesetzt.<br>
	 * <B>Erstellte Buttons:</B><Br>
	 * -> btnZeigen - Zeigen <br>-> btnSpeichern - Speichern<br>-> btnLaden - Laden 
	 * <br>-> btnReset - Rest
	 * <br><B>DefaultButton:</B> btnZeigen - Zeigen 
	 * @param behaelterPanel - JPanel - JPanel in den die Buttons integriert werden sollen
	 * @param layoutFestPanel - layoutFestPanel
	 */
	private void createAllBtn(JPanel behaelterPanel) {
		btnZeigen = createBtn("Zeigen", 		panelPosision(170, -10, -180, 10), behaelterPanel);
		btnSpeichern = createBtn("Speichern", 	panelPosision(230, -10, -120, 10), behaelterPanel);
		btnLaden = createBtn("Laden", 			panelPosision(290, -10, -60, 10), behaelterPanel);
		btnReset = createBtn("Reset", 			panelPosision(350, -10, 0, 10), behaelterPanel);
		
		getRootPane().setDefaultButton(btnZeigen);
	}
	
	private JButton createBtn(String name, int[] panelPosision, JPanel behaelterPanel) {
		JButton tempZeigen = new JButton(name);
		setPosToSpringLayout(tempZeigen, panelPosision, behaelterPanel);
		
		return tempZeigen;
	}

	
	/**Erstellt den WegPunkt-Panel und positioniert den Panel
	 * im SpringLayout
	 * @param sl_scrollPanel
	 */
	private void iniWegpktPanel(SpringLayout sl_scrollPanel) {
		wegPunktPanel = new JPanel();
//		setzPosComponent(wegPunktPanel, panelPosision(5, -5, -10, 10), scrollPanel, sl_scrollPanel);
		wegPunktPanel.setPreferredSize(new Dimension(250, 300));
		wegPunktPanel.setLayout(null); //nötig
	}
	

	/** Trägt ein Neues JLabel in den JPanel ein.
	 * 
	 */
	protected void nachtragPanel() {
		abstandLabel += 15;
		JLabel label = new JLabel("Lang lang Lang lang Lang lang ");
		label.setBounds(0, abstandLabel, 100, 14);
		scrollPanel.setPreferredSize(new Dimension(10, abstandLabel));
		wegPunktPanel.add(label);
		this.validate();
		wegPunktPanel.repaint();
	}
	
	/**Trägt ein Neues JLabel in den JPanel ein.
	 * @param label - Das JLabel was in den JPanel soll 
	 * @param panel - Das JPanel wo die JLabel eingefügt wird
	 */
	public void nachtragPanel(JLabel label, JPanel panel) {
		abstandLabel += 15;
		label.setBounds(0, abstandLabel, 100, 14);
//		scrollPanel.setPreferredSize(new Dimension(10, nächste));
		panel.add(label);
		this.validate();
		panel.repaint();
	}
	
	/**Trägt ein Neues JLabel in den JPanel ein.
	 * @param label
	 * @param panel
	 * @param scrollPanel
	 */
	protected void nachtragPanel(JLabel label, JPanel panel, JPanel scrollPanel ) {
		abstandLabel += 15;
		label.setBounds(0, abstandLabel, 100, 14);
		scrollPanel.setPreferredSize(new Dimension(10, abstandLabel));
		panel.add(label);
		this.validate();
		panel.repaint();
	}
	
	public void setAllTxtFieldText(String name, String breitengrad, String laengengrad) {
		getTxtNameWegpkt().setText(name);
		getTxtBreitengrad().setText(breitengrad);
		getTxtLaengengrad().setText(laengengrad);
	}

	public JTextField getTxtNameWegpkt() {
		return txtNameWegpkt;
	}

	public JPanel getWegPunktPanel() {
		return wegPunktPanel;
	}

	public JButton getBtnSpeichern() {
		return btnSpeichern;
	}

	public JButton getBtnZeigen() {
		return btnZeigen;
	}

	public JTextField getTxtBreitengrad() {
		return txtBreitengrad;
	}

	public void setTxtBreitengrad(JTextField txtBreitengrad) {
		this.txtBreitengrad = txtBreitengrad;
	}

	public JTextField getTxtLaengengrad() {
		return txtLaengengrad;
	}

	public void setTxtLaengengrad(JTextField txtLaengengrad) {
		this.txtLaengengrad = txtLaengengrad;
	}

	public void setTxtNameWegpkt(JTextField txtNameWegpkt) {
		this.txtNameWegpkt = txtNameWegpkt;
	}

	/**Übergibt die passende Eigenschaft WegPktFenster.java.
	 *
	 * @return btnReset - JButton - 
	 */
	public JButton getBtnReset() {
		return btnReset;
	}

	/**Übergibt des Parameter-Wert die passende Eigenschaft.
	 * @param btnReset - JButton -  zu setzende Objekt-Eigenschaft btnReset
	 */
	public void setBtnReset(JButton btnReset) {
		this.btnReset = btnReset;
	}

	/**Übergibt die passende Eigenschaft WegPktFenster.java.
	 *
	 * @return btnLaden - JButton - 
	 */
	public JButton getBtnLaden() {
		return btnLaden;
	}

	/**Übergibt des Parameter-Wert die passende Eigenschaft.
	 * @param btnLaden - JButton -  zu setzende Objekt-Eigenschaft btnLaden
	 */
	public void setBtnLaden(JButton btnLaden) {
		this.btnLaden = btnLaden;
	}

	
	
}