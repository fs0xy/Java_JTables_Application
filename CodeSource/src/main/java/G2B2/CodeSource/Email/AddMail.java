package G2B2.CodeSource.Email;


import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
public class AddMail extends JFrame {

	/* Déclaration des variables */
	private JPanel panneau;
	private JTextField champExpediteur = new JTextField(15);
	private JTextField champDestinataire = new JTextField(15);
	private JTextField champObjet = new JTextField(15);
	private JTextField champDate = new JTextField(15);
	private JTextArea champTexte = new JTextArea(4, 20);
	public static ArrayList<Mail> arrayEmail = new ArrayList<Mail>();
	private Object[] row = new Object[5];

	/**
	 * Constructeur qui permer de créer une fenetre d'ajout
	 * 
	 * @return un JPanel
	 */
	public AddMail() {
		super("Ajout d'un Email");
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setPreferredSize(new Dimension(550, 350));
		this.setContentPane(panneauDeContenuEmail());
		this.setVisible(true);
		this.setAlwaysOnTop(true);
		this.setResizable(false);
		this.pack();
	}

	/**
	 * Permet d'afficher le contenu da fenêtre de l'ajout de mails
	 * 
	 * @return un JPanel
	 */
	JPanel panneauDeContenuEmail() {
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

		/* Mise en place des différents composant dans la fenêtre */
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
	private void actionBoutonAnnuler() {
		int confirmation;
		confirmation = JOptionPane.showConfirmDialog(this, "Voulez-vous réellement annuler ?", "Annuler",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (confirmation == JOptionPane.YES_OPTION) {
			dispose();
		}
	}
	
	/**
	 * Permet d'effectuer les actions lorsqu'on clique sur le bouton annuler
	 */
	private void actionBoutonOk() {
		if (donneesValidesChamps() == false) {
			JOptionPane.showMessageDialog(this, "Veuillez remplir les champs vides", "Attention",
					JOptionPane.WARNING_MESSAGE);
		}
		if (donneesValidesChamps() == true) {
			row[0] = champExpediteur.getText();
			row[1] = champDestinataire.getText();
			row[2] = champObjet.getText();
			row[3] = champDate.getText();
			row[4] = champTexte.getText();

			/* Ajout des lignes au modèle email */
			Interface.modelEmail.addRow(row);
			Mail mail = new Mail(champExpediteur.getText(), champDestinataire.getText(), champObjet.getText(),
					champDate.getText(), champTexte.getText());

			/* On ajoute la ligne dans l'arrayList de type mail */
			arrayEmail.add(mail);
			dispose();
		}
	}
}
