package G2B2.CodeSource.Stagiaire;

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

public class AddStagiaire extends JFrame {

	/* Déclaration des variables */
	private JPanel panneau;
	private JTextField champPrenom = new JTextField(15);
	private JTextField champNom = new JTextField(15);
	private JTextField champDateNaissance = new JTextField(15);
	private JTextField champAnneeStage = new JTextField(15);
	private JTextField champEntreprise = new JTextField(15);
	public static ArrayList<Stagiaire> arrayStagiaire = new ArrayList<Stagiaire>();
	private Object[] row = new Object[5];

	/**
	 * Constructeur qui permer de créer une fenetre d'ajout
	 */
	public AddStagiaire() {
		super("Ajout d'un stagiaire");
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setPreferredSize(new Dimension(490, 300));
		this.setContentPane(panneauDeContenu());
		this.setVisible(true);
		this.setAlwaysOnTop(true);
		this.setResizable(false);
		this.pack();
	}

	/**
	 * Permet d'afficher le contenu de la fenêtre de l'ajout de Stagiaires
	 * 
	 * @return un JPanel
	 */
	JPanel panneauDeContenu() {
		this.panneau = new JPanel();
		SpringLayout layout = new SpringLayout();
		panneau.setLayout(layout);

		JLabel prenom = new JLabel("Prénom");
		panneau.add(prenom);
		panneau.add(champPrenom);

		JLabel nom = new JLabel("Nom");
		panneau.add(nom);
		panneau.add(champNom);

		JLabel date = new JLabel("Date de naissance");
		panneau.add(date);
		panneau.add(champDateNaissance);

		JLabel anneeStage = new JLabel("Année du stage");
		panneau.add(anneeStage);
		panneau.add(champAnneeStage);

		JLabel entreprise = new JLabel("Entreprise");
		panneau.add(entreprise);
		panneau.add(champEntreprise);

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
		layout.putConstraint(SpringLayout.WEST, prenom, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, prenom, 25, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.NORTH, champPrenom, 25, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, champPrenom, 30, SpringLayout.EAST, prenom);

		layout.putConstraint(SpringLayout.WEST, nom, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, nom, 55, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.NORTH, champNom, 55, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, champNom, 49, SpringLayout.EAST, nom);

		layout.putConstraint(SpringLayout.WEST, date, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, date, 85, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.NORTH, champDateNaissance, 85, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, champDateNaissance, 24, SpringLayout.EAST, date);

		layout.putConstraint(SpringLayout.WEST, anneeStage, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, anneeStage, 115, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.NORTH, champAnneeStage, 115, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, champAnneeStage, 41, SpringLayout.EAST, anneeStage);

		layout.putConstraint(SpringLayout.WEST, entreprise, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, entreprise, 145, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.NORTH, champEntreprise, 145, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, champEntreprise, 30, SpringLayout.EAST, entreprise);

		layout.putConstraint(SpringLayout.WEST, ok, 150, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, ok, 200, SpringLayout.NORTH, this);

		layout.putConstraint(SpringLayout.WEST, annuler, 240, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, annuler, 200, SpringLayout.NORTH, this);

		return panneau;
	}

	/**
	 * Permet de vérifier si les champs sont valides
	 * 
	 * @return un boolean sur la validité des champs
	 */
	private boolean donneesValidesChamps() {
		return (!this.champPrenom.getText().equals("") && !this.champNom.getText().equals("")
				&& !champAnneeStage.getText().equals("") && !this.champDateNaissance.getText().equals("")
				&& !this.champEntreprise.getText().equals(""));
	}

	/**
	 * Permet de vérifier si le champ du stage est valide
	 * 
	 * @return un boolean sur la validité du champ
	 */
	private boolean donneesValidesChampStage() {
		return (isInt(champAnneeStage.getText()) && !champAnneeStage.getText().equals("")
				&& !champAnneeStage.getText().contains("-"));
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
	 * Permet d'effectuer les actions lorsqu'on clique sur le bouton ok
	 */
	private void actionBoutonOk() {
		if (donneesValidesChamps() == false) {
			JOptionPane.showMessageDialog(this, "Veuillez remplir les champs vides", "Attention",
					JOptionPane.WARNING_MESSAGE);
		}
		if (donneesValidesChampStage() == false
				&& (!isInt(champAnneeStage.getText()) && !champAnneeStage.getText().equals(""))) {
			JOptionPane.showMessageDialog(this, "L'année du stage doit être un entier", "Attention",
					JOptionPane.WARNING_MESSAGE);
		}
		if (champAnneeStage.getText().contains("-") && isInt(champAnneeStage.getText())
				&& !champAnneeStage.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "L'année du stage doit être positive", "Attention",
					JOptionPane.WARNING_MESSAGE);
		}
		if (donneesValidesChamps() == true && donneesValidesChampStage() == true) {
			row[0] = champPrenom.getText();
			row[1] = champNom.getText();
			row[2] = champDateNaissance.getText();
			row[3] = champAnneeStage.getText();
			row[4] = champEntreprise.getText();

			/* Ajout des lignes au modèle Stagiaire */
			Interface.modelStagiaire.addRow(row);
			Stagiaire Stagiaire = new Stagiaire(champPrenom.getText(), champNom.getText(), champDateNaissance.getText(),
					champAnneeStage.getText(), champEntreprise.getText());

			/* On ajoute la ligne dans l'arrayList de type Stagiaire */
			arrayStagiaire.add(Stagiaire);
			dispose();
		}
	}

	/**
	 * @return un boolean indiquant s'il s'agit d'un entier ou pas
	 */
	private boolean isInt(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}