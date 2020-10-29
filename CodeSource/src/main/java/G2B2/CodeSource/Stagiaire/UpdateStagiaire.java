package G2B2.CodeSource.Stagiaire;

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
import G2B2.CodeSource.Email.AddMail;

public class UpdateStagiaire extends JFrame {

	/* Déclaration des attributs et récupération du contenu ce chaque champs */
	private JPanel panneau;
	private JTextField champPrenom = new JTextField(
			AddStagiaire.arrayStagiaire.get(Interface.tableStagiaire.getSelectedRow()).getPrenom(), 15);
	private JTextField champNom = new JTextField(
			AddStagiaire.arrayStagiaire.get(Interface.tableStagiaire.getSelectedRow()).getNom(), 15);
	private JTextField champDateNaissance = new JTextField(
			AddStagiaire.arrayStagiaire.get(Interface.tableStagiaire.getSelectedRow()).getDate(), 15);
	private JTextField champAnneeStage = new JTextField(
			AddStagiaire.arrayStagiaire.get(Interface.tableStagiaire.getSelectedRow()).getAnneeStage(), 15);
	private JTextField champEntreprise = new JTextField(
			AddStagiaire.arrayStagiaire.get(Interface.tableStagiaire.getSelectedRow()).getEntreprise(), 15);

	/**
	 * Constructeur permettant de faire apparaitre une fenêtre de modification de Stagiaire
	 */
	public UpdateStagiaire() {
		super("Modofication d'un stagiaire");
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setPreferredSize(new Dimension(490, 300));
		this.setContentPane(panneauDeContenuStagiaire());
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
	JPanel panneauDeContenuStagiaire() {
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

			/* Ajout des lignes au modèle Stagiaire */
			Stagiaire stagiaire = new Stagiaire(champPrenom.getText(), champNom.getText(), champDateNaissance.getText(),
					champAnneeStage.getText(), champEntreprise.getText());
			AddStagiaire.arrayStagiaire.set(Interface.tableStagiaire.getSelectedRow(), stagiaire);

			/* On modifie les valeurs dans les cellules */
			Interface.modelStagiaire.setValueAt(champPrenom.getText(), Interface.tableStagiaire.getSelectedRow(), 0);
			Interface.modelStagiaire.setValueAt(champNom.getText(), Interface.tableStagiaire.getSelectedRow(), 1);
			Interface.modelStagiaire.setValueAt(champDateNaissance.getText(), Interface.tableStagiaire.getSelectedRow(), 2);
			Interface.modelStagiaire.setValueAt(champAnneeStage.getText(), Interface.tableStagiaire.getSelectedRow(), 3);
			Interface.modelStagiaire.setValueAt(champEntreprise.getText(), Interface.tableStagiaire.getSelectedRow(), 4);
			
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