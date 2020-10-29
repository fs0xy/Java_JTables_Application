package G2B2.CodeSource.Email;


import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import G2B2.CodeSource.Interface;

@SuppressWarnings("serial")
public class UpdateMail extends JFrame {

	/* Déclaration des attributs et récupération du contenu ce chaque champs */
	private JPanel panneau;
	private JTextField champExpediteur = new JTextField(
			AddMail.arrayEmail.get(Interface.tableEmail.getSelectedRow()).getExpediteur(), 15);
	private JTextField champDestinataire = new JTextField(
			AddMail.arrayEmail.get(Interface.tableEmail.getSelectedRow()).getDestinataire(), 15);
	private JTextField champObjet = new JTextField(
			AddMail.arrayEmail.get(Interface.tableEmail.getSelectedRow()).getObjet(), 15);
	private JTextArea champTexte = new JTextArea(
			AddMail.arrayEmail.get(Interface.tableEmail.getSelectedRow()).getTexte(), 4, 20);
	private JTextField champDate = new JTextField(
			AddMail.arrayEmail.get(Interface.tableEmail.getSelectedRow()).getObjet(), 15);

	/**
	 * Constructeur permettant de faire apparaitre une fenêtre de modification d'email
	 */
	public UpdateMail() {
		super("Modofication d'un email");
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setPreferredSize(new Dimension(550, 350));
		this.setContentPane(panneauDeContenu());
		this.setVisible(true);
		this.setAlwaysOnTop(true);
		this.setResizable(false);
		this.pack();
	}

	/**
	 * Permet d'ajouter tous les éléments au Jpanel
	 * 
	 * @return un JPanel
	 */
	JPanel panneauDeContenu() {
		this.panneau = new JPanel();
		SpringLayout layout = new SpringLayout();
		panneau.setLayout(layout);

		JLabel expediteur = new JLabel("Expediteur");
		panneau.add(expediteur);
		panneau.add(champExpediteur);

		JLabel destinataire = new JLabel("Destinataire");
		panneau.add(destinataire);
		panneau.add(champDestinataire);

		JLabel objet = new JLabel("Objet");
		panneau.add(objet);
		panneau.add(champObjet);

		JLabel Date = new JLabel("Date");
		panneau.add(Date);
		panneau.add(champDate);

		JLabel texteMail = new JLabel("Message");
		panneau.add(texteMail);
		champTexte.setBorder(champDestinataire.getBorder());
		champTexte.setLineWrap(true);
		panneau.add(champTexte);

		JScrollPane barre = new JScrollPane(this.champTexte);
		panneau.add(barre);
		barre.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		JButton ok = new JButton("OK");
		ok.setPreferredSize(new Dimension(82, 28));
		panneau.add(ok);
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionBoutonOk();
			}
		});

		JButton annuler = new JButton("Annuler");
		annuler.setPreferredSize(new Dimension(82, 28));
		panneau.add(annuler);
		annuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionBoutonAnnuler();
			}
		});

		/* Placement des champs sur l'interface de la modification */
		layout.putConstraint(SpringLayout.WEST, expediteur, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, expediteur, 25, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.NORTH, champExpediteur, 25, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, champExpediteur, 30, SpringLayout.EAST, destinataire);

		layout.putConstraint(SpringLayout.WEST, destinataire, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, destinataire, 55, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.NORTH, champDestinataire, 55, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, champDestinataire, 30, SpringLayout.EAST, destinataire);

		layout.putConstraint(SpringLayout.WEST, objet, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, objet, 85, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.NORTH, champObjet, 85, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, champObjet, 69, SpringLayout.EAST, objet);

		layout.putConstraint(SpringLayout.WEST, Date, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, Date, 115, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.NORTH, champDate, 115, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, champDate, 73, SpringLayout.EAST, Date);

		layout.putConstraint(SpringLayout.WEST, texteMail, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, texteMail, 148, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.NORTH, barre, 148, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, barre, 46, SpringLayout.EAST, texteMail);

		layout.putConstraint(SpringLayout.WEST, ok, 170, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, ok, 250, SpringLayout.NORTH, this);

		layout.putConstraint(SpringLayout.WEST, annuler, 270, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, annuler, 250, SpringLayout.NORTH, this);

		return panneau;
	}

	/**
	 * Permet d'effectuer les actions lorsqu'on clique sur le bouton annuler
	 */
	private void actionBoutonAnnuler() {
		int confirmation;
		confirmation = JOptionPane.showConfirmDialog(this, "Voulez-vous réellement annuler ?", "Annuler",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (confirmation == JOptionPane.YES_OPTION) {
			dispose();
		}
	}

	/**
	 * Permet de vérifier si les champs sont valides
	 * 
	 * @return un boolean sur la validité des champs
	 */
	private boolean donneesValidesChamps() {
		return (!this.champExpediteur.getText().equals("") && !this.champDestinataire.getText().equals("")
				&& !this.champObjet.getText().equals("") && !this.champDate.getText().equals("")
				&& !this.champTexte.getText().equals(""));
	}

	/**
	 * Permet d'effectuer les actions lorsqu'on clique sur le bouton ok
	 */
	private void actionBoutonOk() {
		if (donneesValidesChamps() == false) {
			JOptionPane.showMessageDialog(this, "Veuillez remplir les champs vides", "Attention",
					JOptionPane.WARNING_MESSAGE);
		}
		if (donneesValidesChamps() == true) {

			Mail mail = new Mail(champExpediteur.getText(), champDestinataire.getText(), champObjet.getText(),
					champDate.getText(), champTexte.getText());
			AddMail.arrayEmail.set(Interface.tableEmail.getSelectedRow(), mail);

			/* On modifie les valeurs dans les cellules */
			Interface.modelEmail.setValueAt(champExpediteur.getText(), Interface.tableEmail.getSelectedRow(), 0);
			Interface.modelEmail.setValueAt(champDestinataire.getText(), Interface.tableEmail.getSelectedRow(), 1);
			Interface.modelEmail.setValueAt(champObjet.getText(), Interface.tableEmail.getSelectedRow(), 2);
			Interface.modelEmail.setValueAt(champDate.getText(), Interface.tableEmail.getSelectedRow(), 3);
			Interface.modelEmail.setValueAt(champTexte.getText(), Interface.tableEmail.getSelectedRow(), 4);

			dispose();
		}
	}
}