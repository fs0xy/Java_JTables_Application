package G2B2.CodeSource.Entreprise;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import G2B2.CodeSource.Interface;

@SuppressWarnings("serial")
public class AddEntreprise extends JFrame {

	/* Déclaration des variables */
	private JPanel panneau;
	private JTextField champNom = new JTextField(15);
	private JTextField champTelephone = new JTextField(15);
	private JTextField champEmail = new JTextField(15);
	private JTextField champAdresse = new JTextField(15);
	private JTextField champAnneeStage = new JTextField(5);
	private JTextField champAnneeTaxe = new JTextField(5);
	private JTextField champMontantTaxe = new JTextField(15);
	private JRadioButton boutonContactOui;
	private JRadioButton boutonContactNon;
	private JRadioButton boutonStageOui;
	private JRadioButton boutonStageNon;
	private JRadioButton boutonTaxeOui;
	private JRadioButton boutonTaxeNon;
	private ButtonGroup groupeStage;
	private ButtonGroup groupeContact;
	private ButtonGroup groupeTaxe;
	private Object[] row = new Object[10];
	public static ArrayList<Entreprise> arrayEntreprise = new ArrayList<Entreprise>();

	/**
	 * Constructeur qui permer de créer une fenetre d'ajout
	 * 
	 */
	public AddEntreprise() {
		super("Ajout d'une entreprise");
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setPreferredSize(new Dimension(780, 330));
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

		JLabel nom = new JLabel("Nom");
		panneau.add(nom);
		panneau.add(champNom);

		JLabel telephone = new JLabel("Telephone");
		panneau.add(telephone);
		panneau.add(champTelephone);

		JLabel email = new JLabel("Email");
		panneau.add(email);
		panneau.add(champEmail);

		/* Ajout des boutons */
		boutonContactOui = new JRadioButton("Oui");
		boutonContactOui.setActionCommand("Oui");
		boutonContactNon = new JRadioButton("Non");
		boutonContactNon.setActionCommand("Non");

		boutonContactNon.setSelected(true);

		boutonStageOui = new JRadioButton("Oui");
		boutonStageOui.setActionCommand("Oui");
		boutonStageOui.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionBoutonStage();
				donneesValidesChampStage();
			}
		});
		boutonStageNon = new JRadioButton("Non");
		boutonStageNon.setActionCommand("Non");
		boutonStageNon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionBoutonStage();
				donneesValidesChampStage();
			}
		});

		boutonStageNon.setSelected(true);

		boutonTaxeOui = new JRadioButton("Oui");
		boutonTaxeOui.setActionCommand("Oui");
		boutonTaxeOui.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionBoutonTaxe();
				donneesValidesChampTaxe();
			}
		});
		boutonTaxeNon = new JRadioButton("Non");
		boutonTaxeNon.setActionCommand("Non");
		boutonTaxeNon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionBoutonTaxe();
				donneesValidesChampTaxe();
			}
		});

		boutonTaxeNon.setSelected(true);

		/* Si le boutonStage est à non alors le champ devient non éditable */
		if (boutonStageNon.isSelected()) {
			champAnneeStage.setEnabled(false);
		}

		/* Si le boutonTaxe est à non alors les champs deviennent non éditables */
		if (boutonTaxeNon.isSelected()) {
			champAnneeTaxe.setEnabled(false);
			champMontantTaxe.setEnabled(false);
		}

		/*
		 * Cela permet de faire en sorte qu'un seul bouton soit sélectionnable
		 */
		groupeContact = new ButtonGroup();
		groupeContact.add(boutonContactOui);
		groupeContact.add(boutonContactNon);

		groupeStage = new ButtonGroup();
		groupeStage.add(boutonStageOui);
		groupeStage.add(boutonStageNon);

		groupeTaxe = new ButtonGroup();
		groupeTaxe.add(boutonTaxeOui);
		groupeTaxe.add(boutonTaxeNon);

		JLabel contact = new JLabel("Prise de contact");
		panneau.add(contact);
		panneau.add(boutonContactOui);
		panneau.add(boutonContactNon);

		JLabel stage = new JLabel("Proposition de stage");
		panneau.add(stage);
		panneau.add(boutonStageOui);
		panneau.add(boutonStageNon);

		JLabel adresse = new JLabel("Adresse");
		panneau.add(adresse);
		panneau.add(champAdresse);

		JLabel anneeStage = new JLabel("Annee");
		panneau.add(anneeStage);
		panneau.add(champAnneeStage);

		JLabel taxe = new JLabel("Taxe versee");
		panneau.add(taxe);
		panneau.add(boutonTaxeOui);
		panneau.add(boutonTaxeNon);

		JLabel anneeTaxe = new JLabel("Annee");
		panneau.add(anneeTaxe);
		panneau.add(champAnneeTaxe);

		JLabel montantTaxe = new JLabel("Montant");
		panneau.add(montantTaxe);
		panneau.add(champMontantTaxe);

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

		/* Placement des champs sur l'interface de l'ajout */
		layout.putConstraint(SpringLayout.WEST, nom, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, nom, 25, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.NORTH, champNom, 25, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, champNom, 53, SpringLayout.EAST, nom);

		layout.putConstraint(SpringLayout.WEST, telephone, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, telephone, 55, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.NORTH, champTelephone, 55, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, champTelephone, 20, SpringLayout.EAST, telephone);

		layout.putConstraint(SpringLayout.WEST, email, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, email, 85, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.NORTH, champEmail, 85, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, champEmail, 48, SpringLayout.EAST, email);

		layout.putConstraint(SpringLayout.WEST, adresse, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, adresse, 115, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.NORTH, champAdresse, 115, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, champAdresse, 30, SpringLayout.EAST, adresse);

		layout.putConstraint(SpringLayout.WEST, stage, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, stage, 148, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, boutonStageOui, 150, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, boutonStageOui, 145, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, boutonStageNon, 200, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, boutonStageNon, 145, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, anneeStage, 280, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, anneeStage, 148, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.NORTH, champAnneeStage, 145, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, champAnneeStage, 20, SpringLayout.EAST, anneeStage);

		layout.putConstraint(SpringLayout.WEST, taxe, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, taxe, 178, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, boutonTaxeOui, 150, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, boutonTaxeOui, 175, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, boutonTaxeNon, 200, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, boutonTaxeNon, 175, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, anneeTaxe, 280, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, anneeTaxe, 179, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.NORTH, champAnneeTaxe, 179, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, champAnneeTaxe, 20, SpringLayout.EAST, anneeTaxe);
		layout.putConstraint(SpringLayout.WEST, montantTaxe, 410, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, montantTaxe, 179, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.NORTH, champMontantTaxe, 179, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, champMontantTaxe, 20, SpringLayout.EAST, montantTaxe);

		layout.putConstraint(SpringLayout.WEST, contact, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, contact, 208, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, boutonContactOui, 150, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, boutonContactOui, 205, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, boutonContactNon, 200, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, boutonContactNon, 205, SpringLayout.NORTH, this);

		layout.putConstraint(SpringLayout.WEST, ok, 305, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, ok, 250, SpringLayout.NORTH, this);

		layout.putConstraint(SpringLayout.WEST, annuler, 390, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, annuler, 250, SpringLayout.NORTH, this);

		return panneau;
	}

	/**
	 * Permet de vérifier si les champs sont valides
	 * 
	 * @return un boolean sur la validité des champs
	 */
	private boolean donneesValidesChamps() {
		return (!this.champAdresse.getText().equals("") && !this.champEmail.getText().equals("")
				&& !this.champNom.getText().equals("") && !this.champTelephone.getText().equals(""));
	}

	/**
	 * Permet de vérifier si le champ du stage est valide
	 * 
	 * @return un boolean sur la validité du champ
	 */
	private boolean donneesValidesChampStage() {
		return (boutonStageOui.isSelected() && isInt(champAnneeStage.getText()) && !champAnneeStage.getText().equals("")
				&& !champAnneeStage.getText().contains("-") || boutonStageNon.isSelected());
	}

	/**
	 * Permet de vérifier si les champs de la taxe sont valide
	 * 
	 * @return un boolean sur la validité des champs
	 */
	private boolean donneesValidesChampTaxe() {
		return (boutonTaxeOui.isSelected() && isInt(champAnneeTaxe.getText()) && isDouble(champMontantTaxe.getText())
				&& !champAnneeTaxe.getText().equals("") && !champMontantTaxe.getText().equals("")
				&& !champAnneeTaxe.getText().contains("-") && !champMontantTaxe.getText().contains("-")
				|| boutonTaxeNon.isSelected());
	}

	/**
	 * Permet de vérifier si l'email est valide.
	 * 
	 * @return un boolean
	 */
	private boolean donneesValidesEmail() {
		return (champEmail.getText().indexOf("@") != -1);
	}

	/**
	 * Permet de vérifier si le numéro de téléphone est valide.
	 * 
	 * @return un boolean
	 */
	private boolean donneesValidesTelephone() {
		return (isInt(champTelephone.getText()) && champTelephone.getText().length() == 10
				&& !champTelephone.getText().contains("-"));
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
		if ((champAnneeStage.getText().contains("-") && isInt(champAnneeStage.getText())
				&& !champAnneeStage.getText().equals(""))
				|| (champAnneeTaxe.getText().contains("-") && isInt(champAnneeTaxe.getText())
						&& !champAnneeTaxe.getText().equals(""))
				|| (champMontantTaxe.getText().contains("-") && isDouble(champMontantTaxe.getText())
						&& !champMontantTaxe.getText().equals(""))
				|| (champTelephone.getText().contains("-") && isInt(champTelephone.getText())
						&& !champTelephone.getText().equals(""))) {
			JOptionPane.showMessageDialog(this, "Veuillez entrer des entiers ou nombres positifs", "Attention",
					JOptionPane.WARNING_MESSAGE);
		}
		if (donneesValidesEmail() == false) {
			JOptionPane.showMessageDialog(this, "Veuillez entrer une adresse email valide", "Attention",
					JOptionPane.WARNING_MESSAGE);
		}
		if (champTelephone.getText().length() < 10 || champTelephone.getText().length() > 10) {
			JOptionPane.showMessageDialog(this, "Veuillez entrer 10 chiffres pour le numéro", "Attention",
					JOptionPane.WARNING_MESSAGE);
		}
		if (donneesValidesChamps() == false) {
			JOptionPane.showMessageDialog(this, "Veuillez remplir les champs vides", "Attention",
					JOptionPane.WARNING_MESSAGE);
		}
		if ((donneesValidesChampStage() == false
				&& (!isInt(champAnneeStage.getText()) && !champAnneeStage.getText().equals("")))
				|| (donneesValidesChampTaxe() == false
						&& (!isInt(champAnneeTaxe.getText()) || !isDouble(champMontantTaxe.getText())
								&& ((!champMontantTaxe.getText().equals("") || !champAnneeTaxe.getText().equals("")))))
				|| (donneesValidesTelephone() == false && !champTelephone.getText().equals("")
						&& !isInt(champTelephone.getText()))) {
			JOptionPane.showMessageDialog(this,
					"Veuillez entrer des entiers pour les années et le téléphone ainsi que des nombres pour les montants",
					"Attention", JOptionPane.WARNING_MESSAGE);
		}
		if (donneesValidesChampStage() == false && champAnneeStage.getText().equals("")
				|| donneesValidesChampTaxe() == false
						&& (champMontantTaxe.getText().equals("") || champAnneeTaxe.getText().equals(""))) {
			JOptionPane.showMessageDialog(this, "Veuillez remplir les champs vides des boutons", "Attention",
					JOptionPane.WARNING_MESSAGE);
		}
		if (donneesValidesChamps() == true && donneesValidesChampStage() == true && donneesValidesChampTaxe() == true
				&& donneesValidesEmail() == true && donneesValidesTelephone() == true) {
			/*
			 * On récupère le contenu des JTextFields dans les colonnes correspondantes
			 */
			row[0] = champNom.getText();
			row[1] = champTelephone.getText();
			row[2] = champEmail.getText();
			row[3] = champAdresse.getText();
			row[4] = groupeStage.getSelection().getActionCommand();
			row[5] = champAnneeStage.getText();
			row[6] = groupeTaxe.getSelection().getActionCommand();
			row[7] = champAnneeTaxe.getText();
			row[8] = champMontantTaxe.getText();
			row[9] = groupeContact.getSelection().getActionCommand();

			/* On ajoute les lignes au modèle */
			Interface.modelEntreprise.addRow(row);
			Entreprise entre = new Entreprise(champNom.getText(), champTelephone.getText(), champEmail.getText(),
					champAdresse.getText(), groupeStage.getSelection().getActionCommand(), champAnneeStage.getText(),
					groupeTaxe.getSelection().getActionCommand(), champAnneeTaxe.getText(), champMontantTaxe.getText(),
					groupeContact.getSelection().getActionCommand());

			/* On ajoute la ligne dans l'arrayList de type entreprise */
			arrayEntreprise.add(entre);
			dispose();
		}
	}

	/**
	 * Permet de modifier le status du champ Année stage si le bouton stage est
	 * coché à oui ou non
	 */
	private void actionBoutonStage() {
		if (boutonStageOui.isSelected()) {
			champAnneeStage.setEnabled(true);
		}
		if (boutonStageNon.isSelected()) {
			champAnneeStage.setText("");
			champAnneeStage.setEnabled(false);
		}
	}

	/**
	 * Permet de modifier le status des champs si le bouton taxe est coché à oui ou
	 * non
	 */
	private void actionBoutonTaxe() {
		if (boutonTaxeOui.isSelected()) {
			champAnneeTaxe.setEnabled(true);
			champMontantTaxe.setEnabled(true);
		}
		if (boutonTaxeNon.isSelected()) {
			champAnneeTaxe.setText("");
			champMontantTaxe.setText("");
			champAnneeTaxe.setEnabled(false);
			champMontantTaxe.setEnabled(false);
		}
	}

	/**
	 * @return un boolean indiquant s'il s'agit d'un réel ou pas
	 */
	private boolean isDouble(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
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
