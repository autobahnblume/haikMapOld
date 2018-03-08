package de.haikMap.fenster;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.JScrollPane;
import java.awt.GridLayout;

/**
 * 
 */
public class GUIWegPunkte extends JFrame {

	private JPanel contentPane;
	private int nächste =-15;
	private JTextField txtNameWegpkt;
	private JTextField txtBreitengrad;
	private JTextField txtLaengengrad;
	private JPanel wegPunktPanel;
	private JPanel scrollPanel;

	
	/**
	 * Create the frame.
	 */
	public GUIWegPunkte() {
		setLocation(new Point(1200, 900));
		setMinimumSize(new Dimension(560, 360));
		setTitle("WegPunkte");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 564, 448);
		
		ActionListener aListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nachtragPanel();
			}
		};
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		contentPane.setLayout(new GridLayout(1, 0, 0, 10));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		contentPane.add(scrollPane_1);
		
		createFesterPanel(aListener, scrollPane_1);
		
		createScrollPanel(scrollPane_1);
			
	}

	/**
	 * @param aListener
	 * @param scrollPane_1
	 */
	private void createFesterPanel(ActionListener aListener, JScrollPane scrollPane_1) {
		JPanel festesPanel = new JPanel();
		festesPanel.setPreferredSize(new Dimension(250, 10));
		festesPanel.setBackground(Color.PINK);
		scrollPane_1.setRowHeaderView(festesPanel);
		SpringLayout sl_festesPanel = new SpringLayout();
		festesPanel.setLayout(sl_festesPanel);
		
		JPanel nameWegpktPanel = new JPanel();
		sl_festesPanel.putConstraint(SpringLayout.NORTH, nameWegpktPanel, 10, SpringLayout.NORTH, festesPanel);
		sl_festesPanel.putConstraint(SpringLayout.WEST, nameWegpktPanel, 10, SpringLayout.WEST, festesPanel);
		sl_festesPanel.putConstraint(SpringLayout.SOUTH, nameWegpktPanel, 75, SpringLayout.NORTH, festesPanel);
		sl_festesPanel.putConstraint(SpringLayout.EAST, nameWegpktPanel, -10, SpringLayout.EAST, festesPanel);
		nameWegpktPanel.setPreferredSize(new Dimension(100, 150));
		festesPanel.add(nameWegpktPanel);
		
		JPanel breitengradPanel = new JPanel();
		sl_festesPanel.putConstraint(SpringLayout.NORTH, breitengradPanel, 10, SpringLayout.SOUTH, nameWegpktPanel);
		sl_festesPanel.putConstraint(SpringLayout.WEST, breitengradPanel, 10, SpringLayout.WEST, festesPanel);
		sl_festesPanel.putConstraint(SpringLayout.SOUTH, breitengradPanel, 71, SpringLayout.SOUTH, nameWegpktPanel);
		sl_festesPanel.putConstraint(SpringLayout.EAST, breitengradPanel, -139, SpringLayout.EAST, festesPanel);
		festesPanel.add(breitengradPanel);
		SpringLayout sl_breitengradPanel = new SpringLayout();
		breitengradPanel.setLayout(sl_breitengradPanel);
		
		JLabel lblNewLabel = new JLabel("Breitengrad");
		sl_breitengradPanel.putConstraint(SpringLayout.NORTH, lblNewLabel, 0, SpringLayout.NORTH, breitengradPanel);
		sl_breitengradPanel.putConstraint(SpringLayout.WEST, lblNewLabel, 0, SpringLayout.WEST, breitengradPanel);
		breitengradPanel.add(lblNewLabel);
		
		txtBreitengrad = new JTextField();
		sl_breitengradPanel.putConstraint(SpringLayout.NORTH, txtBreitengrad, 19, SpringLayout.NORTH, breitengradPanel);
		sl_breitengradPanel.putConstraint(SpringLayout.WEST, txtBreitengrad, 0, SpringLayout.WEST, breitengradPanel);
		breitengradPanel.add(txtBreitengrad);
		txtBreitengrad.setColumns(10);
		SpringLayout sl_nameWegpktPanel = new SpringLayout();
		nameWegpktPanel.setLayout(sl_nameWegpktPanel);
		
		JLabel lblNameDesWegpunktes = new JLabel("Name des WegPunktes");
		sl_nameWegpktPanel.putConstraint(SpringLayout.NORTH, lblNameDesWegpunktes, 0, SpringLayout.NORTH, nameWegpktPanel);
		sl_nameWegpktPanel.putConstraint(SpringLayout.WEST, lblNameDesWegpunktes, 0, SpringLayout.WEST, nameWegpktPanel);
		sl_nameWegpktPanel.putConstraint(SpringLayout.SOUTH, lblNameDesWegpunktes, -39, SpringLayout.SOUTH, nameWegpktPanel);
		sl_nameWegpktPanel.putConstraint(SpringLayout.EAST, lblNameDesWegpunktes, 127, SpringLayout.WEST, nameWegpktPanel);
		nameWegpktPanel.add(lblNameDesWegpunktes);
		
		JPanel laengengradPanel = new JPanel();
		sl_festesPanel.putConstraint(SpringLayout.NORTH, laengengradPanel, 10, SpringLayout.SOUTH, nameWegpktPanel);
		sl_festesPanel.putConstraint(SpringLayout.WEST, laengengradPanel, 20, SpringLayout.EAST, breitengradPanel);
		sl_festesPanel.putConstraint(SpringLayout.SOUTH, laengengradPanel, 0, SpringLayout.SOUTH, breitengradPanel);
		sl_festesPanel.putConstraint(SpringLayout.EAST, laengengradPanel, -10, SpringLayout.EAST, festesPanel);
		festesPanel.add(laengengradPanel);
		SpringLayout sl_laengengradPanel = new SpringLayout();
		laengengradPanel.setLayout(sl_laengengradPanel);
		
		JLabel lblLngengrad = new JLabel("L\u00E4ngengrad");
		sl_laengengradPanel.putConstraint(SpringLayout.NORTH, lblLngengrad, 0, SpringLayout.NORTH, laengengradPanel);
		sl_laengengradPanel.putConstraint(SpringLayout.WEST, lblLngengrad, 0, SpringLayout.WEST, laengengradPanel);
		laengengradPanel.add(lblLngengrad);
		
		txtLaengengrad = new JTextField();
		sl_laengengradPanel.putConstraint(SpringLayout.NORTH, txtLaengengrad, 19, SpringLayout.NORTH, laengengradPanel);
		sl_laengengradPanel.putConstraint(SpringLayout.WEST, txtLaengengrad, 0, SpringLayout.WEST, laengengradPanel);
		laengengradPanel.add(txtLaengengrad);
		txtLaengengrad.setColumns(10);
		
		JButton btnZeigen = new JButton("Zeigen");
		btnZeigen.addActionListener(aListener);
		sl_festesPanel.putConstraint(SpringLayout.NORTH, btnZeigen, 24, SpringLayout.SOUTH, breitengradPanel);
		sl_festesPanel.putConstraint(SpringLayout.WEST, btnZeigen, 10, SpringLayout.WEST, festesPanel);
		sl_festesPanel.putConstraint(SpringLayout.SOUTH, btnZeigen, 66, SpringLayout.SOUTH, breitengradPanel);
		sl_festesPanel.putConstraint(SpringLayout.EAST, btnZeigen, 0, SpringLayout.EAST, nameWegpktPanel);
		festesPanel.add(btnZeigen);
		
		JButton btnSpeichern = new JButton("Speichern");
		btnSpeichern.addActionListener(aListener);
		sl_festesPanel.putConstraint(SpringLayout.NORTH, btnSpeichern, 23, SpringLayout.SOUTH, btnZeigen);
		sl_festesPanel.putConstraint(SpringLayout.WEST, btnSpeichern, 10, SpringLayout.WEST, festesPanel);
		sl_festesPanel.putConstraint(SpringLayout.SOUTH, btnSpeichern, 65, SpringLayout.SOUTH, btnZeigen);
		sl_festesPanel.putConstraint(SpringLayout.EAST, btnSpeichern, 0, SpringLayout.EAST, nameWegpktPanel);
		
		txtNameWegpkt = new JTextField();
		sl_nameWegpktPanel.putConstraint(SpringLayout.NORTH, txtNameWegpkt, 6, SpringLayout.SOUTH, lblNameDesWegpunktes);
		sl_nameWegpktPanel.putConstraint(SpringLayout.WEST, txtNameWegpkt, 0, SpringLayout.WEST, lblNameDesWegpunktes);
		sl_nameWegpktPanel.putConstraint(SpringLayout.SOUTH, txtNameWegpkt, 52, SpringLayout.NORTH, nameWegpktPanel);
		sl_nameWegpktPanel.putConstraint(SpringLayout.EAST, txtNameWegpkt, 184, SpringLayout.WEST, nameWegpktPanel);
		nameWegpktPanel.add(txtNameWegpkt);
		txtNameWegpkt.setColumns(10);
		festesPanel.add(btnSpeichern);
	}

	/**
	 * @param scrollPane_1
	 */
	private void createScrollPanel(JScrollPane scrollPane_1) {
		scrollPanel = new JPanel();
		scrollPanel.setPreferredSize(new Dimension(10, 100));
		scrollPanel.setBackground(Color.ORANGE);
		scrollPane_1.setViewportView(scrollPanel);
		SpringLayout sl_scrollPanel = new SpringLayout();
		scrollPanel.setLayout(sl_scrollPanel);
		
		iniWegpktPanel(sl_scrollPanel);
		
		scrollPanel.add(wegPunktPanel);
	}

	/**Erstellt den WegPunkt-Panel und positioniert den Panel
	 * im SpringLayout
	 * @param sl_scrollPanel
	 */
	private void iniWegpktPanel(SpringLayout sl_scrollPanel) {
		wegPunktPanel = new JPanel();
		sl_scrollPanel.putConstraint(SpringLayout.NORTH, wegPunktPanel, 5, SpringLayout.NORTH, scrollPanel);
		sl_scrollPanel.putConstraint(SpringLayout.WEST, wegPunktPanel, 10, SpringLayout.WEST, scrollPanel);
		sl_scrollPanel.putConstraint(SpringLayout.SOUTH, wegPunktPanel, -10, SpringLayout.SOUTH, scrollPanel);
		sl_scrollPanel.putConstraint(SpringLayout.EAST, wegPunktPanel, 276, SpringLayout.WEST, scrollPanel);
		wegPunktPanel.setPreferredSize(new Dimension(50, 50));
		wegPunktPanel.setLayout(null); //nötig
	}
	
	

	/* Trägt ein Neues JLabel in den JPanel ein.
	 * 
	 */
	protected void nachtragPanel() {
		nächste += 15;
		JLabel label = new JLabel("Lang lang Lang lang Lang lang ");
		label.setBounds(0, nächste, 100, 14);
		scrollPanel.setPreferredSize(new Dimension(10, nächste));
		wegPunktPanel.add(label);
		this.validate();
		wegPunktPanel.repaint();
	}
	/**Trägt ein Neues JLabel in den JPanel ein.
	 * @param label
	 * @param panel
	 */
	protected void nachtragPanel(JLabel label, JPanel panel) {
		nächste += 15;
		label.setBounds(0, nächste, 100, 14);
		scrollPanel.setPreferredSize(new Dimension(10, nächste));
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
		nächste += 15;
		label.setBounds(0, nächste, 100, 14);
		scrollPanel.setPreferredSize(new Dimension(10, nächste));
		panel.add(label);
		this.validate();
		panel.repaint();
	}
	
}
