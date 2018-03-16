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
import java.awt.Toolkit;

/**https://dbs.cs.uni-duesseldorf.de/lehre/docs/java/javabuch/html/k100241.html
 * http://openbook.rheinwerk-verlag.de/javainsel9/javainsel_19_010.htm#mj66f472599b8b53fe93ea7adde5f179c3
 */
public class WegPunkteFenster extends JFrame {

	private final int NORTH = 0;
	private final int EAST = 1;
	private final int SOUTH = 2;
	private final int WEST = 3;
	public int abstandLabel =-15;
	private JTextField txtNameWegpkt;
	private JTextField txtBreitengrad;
	private JTextField txtLaengengrad;
	private JPanel wegPunktPanel;
	private JPanel scrollPanel;

	private JButton btnSpeichern;
	private JButton btnZeigen;
	private JButton btnReset;
	
	/**
	 * Create the frame.
	 */
	public WegPunkteFenster() {
		/**Unnötig???
		setLocation(new Point(1200, 900));*/
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\mkenkel\\OneDrive\\AutoBahnBlume.png"));
		setMinimumSize(new Dimension(560, 360));
		setTitle("WegPunkte");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
	 * @param einZuOrdnenenPanel -JPanel
	 * @param behaelterPanel - JPanel
	 * @param layout - SpringLayout
	 * @return - SpringLayout
	 */
	private void setzPosComponent(JComponent einZuOrdnenenPanel, int[]pos, JPanel behaelterPanel, SpringLayout layout) {
		behaelterPanel.setLayout(layout);
		layout.putConstraint(SpringLayout.NORTH, einZuOrdnenenPanel, pos[NORTH], SpringLayout.NORTH, behaelterPanel);
		layout.putConstraint(SpringLayout.EAST, einZuOrdnenenPanel, pos[EAST], SpringLayout.EAST, behaelterPanel);
		layout.putConstraint(SpringLayout.SOUTH, einZuOrdnenenPanel, pos[SOUTH], SpringLayout.SOUTH, behaelterPanel);
		layout.putConstraint(SpringLayout.WEST, einZuOrdnenenPanel, pos[WEST], SpringLayout.WEST, behaelterPanel);
		behaelterPanel.add(einZuOrdnenenPanel);
	}
	
	
	/**Erstellt das FestePanel in den alle Componenten kömmen,
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
		
		SpringLayout layoutFestPanel = new SpringLayout();
//Create der einzelnen Panels zum einfügen-------------------------		
		createPanalNameWegpkt(festesPanel, layoutFestPanel);
		createPanelBreitengrad(festesPanel, layoutFestPanel);
		createPanelLaengengrad(layoutFestPanel, festesPanel);
		createAllBtn(festesPanel, layoutFestPanel);
//		createBtnZeigen(festesPanel, layoutFestPanel);
//		createBtnSpeichern(festesPanel, layoutFestPanel);
//-----------------------------------------------------------------		
		
	}

	/**
	 * @param festesPanel
	 * @param layoutFestPanel
	 */
	private void createPanelBreitengrad(JPanel festesPanel, SpringLayout layoutFestPanel) {
		JPanel breitengradPanel = new JPanel();
		createBreitengradPanel(layoutFestPanel,festesPanel, breitengradPanel);
	}

	/**
	 * @param festesPanel
	 * @param layoutFestPanel
	 */
	private void createPanalNameWegpkt(JPanel festesPanel, SpringLayout layoutFestPanel) {
		JPanel panelNameWegpkt = new JPanel();
		setzPosComponent(panelNameWegpkt, panelPosision(10, -10, -328, 10), festesPanel, layoutFestPanel );
		panelNameWegpkt.setPreferredSize(new Dimension(100, 150));
//		festesPanel.add(nameWegpktPanel);
		
		SpringLayout sl_nameWegpktPanel_1 = new SpringLayout();
		panelNameWegpkt.setLayout(sl_nameWegpktPanel_1);
		
		JLabel lblNameDesWegpunktes = new JLabel("Name des WegPunktes");
		setzPosComponent(lblNameDesWegpunktes, panelPosision(0, 0, -39, 127), panelNameWegpkt, sl_nameWegpktPanel_1);
		
		greateTxtNameWegpkt(panelNameWegpkt, sl_nameWegpktPanel_1, lblNameDesWegpunktes, layoutFestPanel, festesPanel);
		
//		nameWegpktPanel.add(lblNameDesWegpunktes);	
	}

	/**
	 * @param sl_festesPanel_1 
	 * @param festesPanel 
	 * @param breitengradPanel Jpanel
	 * 
	 */
	private void createBreitengradPanel(SpringLayout sl_festesPanel_1, JPanel festesPanel, JPanel breitengradPanel) {
		setzPosComponent(breitengradPanel, panelPosision(90, -139, -248, 10), festesPanel, sl_festesPanel_1);
// 		festesPanel.add(breitengradPanel);
		SpringLayout sl_breitengradPanel_1 = new SpringLayout();
		breitengradPanel.setLayout(sl_breitengradPanel_1);
		
		JLabel lblNewLabel = new JLabel("Breitengrad");
		setzPosComponent(lblNewLabel, panelPosision(0, 0, -40, 0), breitengradPanel, sl_breitengradPanel_1);
//		breitengradPanel.add(lblNewLabel);
		
		txtBreitengrad = new JTextField();
		setzPosComponent(txtBreitengrad, panelPosision(19, 0, -20, 0), breitengradPanel, sl_breitengradPanel_1);
//		breitengradPanel.add(txtBreitengrad);
		txtBreitengrad.setColumns(10);
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


	/**
	 * @param sl_festesPanel
	 * @param breitengradPanel
	 * @param festesPanel 
	 */
	private void createPanelLaengengrad(SpringLayout sl_festesPanel, JPanel festesPanel) {
		JPanel laengengradPanel = new JPanel();
		setzPosComponent(laengengradPanel, panelPosision(90, -10, -248, 127), festesPanel, sl_festesPanel);
//		festesPanel.add(laengengradPanel);
		
		SpringLayout sl_laengengradPanel = new SpringLayout();
		laengengradPanel.setLayout(sl_laengengradPanel);
		
		createLblLaengengrad(laengengradPanel, sl_laengengradPanel);
		
		createTxtLaengengrad(laengengradPanel, sl_laengengradPanel);
	}

	/**
	 * @param laengengradPanel
	 * @param sl_laengengradPanel
	 * Label posistion rätselhaft
	 */
	private void createLblLaengengrad(JPanel laengengradPanel, SpringLayout sl_laengengradPanel) {
		JLabel lblLngengrad = new JLabel("L\u00E4ngengrad");
		setzPosComponent(lblLngengrad, panelPosision(-19, -5, -20, 0), laengengradPanel, sl_laengengradPanel);
//		laengengradPanel.add(lblLngengrad);
	}

	/**
	 * @param laengengradPanel
	 * @param sl_laengengradPanel
	 */
	private void createTxtLaengengrad(JPanel laengengradPanel, SpringLayout sl_laengengradPanel) {
		txtLaengengrad = new JTextField();
		setzPosComponent(txtLaengengrad, panelPosision(19, -5, -20, 0), laengengradPanel, sl_laengengradPanel);
		txtLaengengrad.setColumns(10);
	}

	private void createAllBtn(JPanel festesPanel, SpringLayout layoutFestPanel) {
		btnZeigen = createBtn("Zeigen", 		panelPosision(170, -10, -186, 10), festesPanel, layoutFestPanel);
		btnSpeichern = createBtn("Speichern", 	panelPosision(235, -10, -121, 10), festesPanel, layoutFestPanel);
		btnReset = createBtn("Rest", 			panelPosision(290, -10, -67, 10), festesPanel, layoutFestPanel);
	}
	private JButton createBtn(String name,int[] panelPosision, JPanel festPanel, SpringLayout sl_festesPanel) {
		JButton tempZeigen = new JButton(name);
		setzPosComponent(tempZeigen, panelPosision, festPanel, sl_festesPanel);
		return tempZeigen;
	}

	

	/**
	 * @param nameWegpktPanel
	 * @param sl_nameWegpktPanel
	 * @param lblNameDesWegpunktes
	 * @param btnSpeichern
	 * @param sl_festesPanel_1 
	 * @param festesPanel 
	 */
	private void greateTxtNameWegpkt(JPanel nameWegpktPanel, SpringLayout sl_nameWegpktPanel,JLabel lblNameDesWegpunktes, SpringLayout sl_festesPanel_1, JPanel festesPanel) {
		txtNameWegpkt = new JTextField();
		setzPosComponent(txtNameWegpkt, panelPosision(20, 184, -20, 0), nameWegpktPanel, sl_festesPanel_1);
//		nameWegpktPanel.add(txtNameWegpkt);
		txtNameWegpkt.setColumns(10);
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

	
}