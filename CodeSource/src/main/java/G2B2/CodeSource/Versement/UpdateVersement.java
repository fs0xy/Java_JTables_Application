package G2B2.CodeSource.Versement;

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

public class UpdateVersement extends JFrame {

	/* Déclaration des attributs et récupération du contenu ce chaque champs */
	private JPanel panneau;
	private JTextField champAnneeTaxe = new JTextField(
			AddVersement.arrayVersement.get(Interface.tableVersement.getSelectedRow()).getAnneeTaxe(), 15);
	private JTextField champMontantTaxe = new JTextField(
			AddVersement.arrayVersement.get(Interface.tableVersement.getSelectedRow()).getMontantTaxe(), 15);
	private JTextField champEntreprise = new JTextField(
			AddVersement.arrayVersement.get(Interface.tableVersement.getSelectedRow()).getEntreprise(), 15);

	/**
	 * Constructeur permettant de faire apparaitre une fenêtre de modification de Stagiaire
	 */
	public UpdateVersement() {
		super("Modofication d'un stagiaire");
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setPreferredSize(new Dimension(460, 250));
		this.setContentPane(panneauDeContenu());
		this.setVisible(true);
		this.setAlwaysOnTop(true);
		this.setResizable(false);
		this.pack();
	}

	/**
	 * Permet d'afficher le contenu de la fenêtre de l'ajout d'un Versement
	 * 
	 * @return un JPanel
	 */
	JPanel panneauDeContenu() {
		this.panneau = new JPanel();
		SpringLayout layout = new SpringLayout();
		panneau.setLayout(layout);

		JLabel anneeTaxe = new JLabel("Année du versement");
		panneau.add(anneeTaxe);
		panneau.add(champAnneeTaxe);

		JLabel montantTaxe = new JLabel("Montant du versement");
		panneau.add(montantTaxe);
		panneau.add(champMontantTaxe);

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
		layout.putConstraint(SpringLayout.WEST, anneeTaxe, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, anneeTaxe, 25, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.NORTH, champAnneeTaxe, 25, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, champAnneeTaxe, 32, SpringLayout.EAST, anneeTaxe);

		layout.putConstraint(SpringLayout.WEST, montantTaxe, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, montantTaxe, 55, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.NORTH, champMontantTaxe, 55, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, champMontantTaxe, 22, SpringLayout.EAST, montantTaxe);

		layout.putConstraint(SpringLayout.WEST, entreprise, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, entreprise, 85, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.NORTH, champEntreprise, 85, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, champEntreprise, 30, SpringLayout.EAST, entreprise);

		layout.putConstraint(SpringLayout.WEST, ok, 130, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, ok, 150, SpringLayout.NORTH, this);

		layout.putConstraint(SpringLayout.WEST, annuler, 235, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, annuler, 150, SpringLayout.NORTH, this);
		
		return panneau;
	}

	/**
	 * Permet de vérifier si les champs sont valides
	 * 
	 * @return un boolean sur la validité des champs
	 */
	private boolean donneesValidesChamps() {
		return (!this.champAnneeTaxe.getText().equals("") && !this.champAnneeTaxe.getText().equals("")
				&& !champMontantTaxe.getText().equals("") && !this.champMontantTaxe.getText().equals("")
				&& !champEntreprise.getText().equals("") && !this.champEntreprise.getText().equals(""));
	}

	/**
	 * Permet de vérifier si le champ du versement est valide
	 * 
	 * @return un boolean sur la validité du champ
	 */
	private boolean donneesValidesChampTaxe() {
		return (isInt(champAnneeTaxe.getText()) && !champAnneeTaxe.getText().equals("")
				&& !champAnneeTaxe.getText().contains("-") && isDouble(champMontantTaxe.getText())
				&& !champMontantTaxe.getText().equals("") && !champMontantTaxe.getText().contains("-"));
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
		if (donneesValidesChampTaxe() == false
				&& (!isInt(champAnneeTaxe.getText()) && !champAnneeTaxe.getText().equals(""))) {
			JOptionPane.showMessageDialog(this, "L'année du versement doit être un entier", "Attention",
					JOptionPane.WARNING_MESSAGE);
		}
		if (donneesValidesChampTaxe() == false
				&& (!isDouble(champMontantTaxe.getText()) && !champMontantTaxe.getText().equals(""))) {
			JOptionPane.showMessageDialog(this, "Le montant du versement doit être un réel", "Attention",
					JOptionPane.WARNING_MESSAGE);
		}
		if (champAnneeTaxe.getText().contains("-") && isInt(champAnneeTaxe.getText())
				&& !champAnneeTaxe.getText().equals("")
				|| (champMontantTaxe.getText().contains("-") && isDouble(champMontantTaxe.getText())
						&& !champMontantTaxe.getText().equals(""))) {
			JOptionPane.showMessageDialog(this, "L'année du versement et le montant du versement doit être positifs",
					"Attention", JOptionPane.WARNING_MESSAGE);
		}
		if (donneesValidesChamps() == true && donneesValidesChampTaxe() == true) {

			/* Ajout des lignes au modèle Stagiaire */
			Versement versement = new Versement(champAnneeTaxe.getText(), champMontantTaxe.getText(),
					champEntreprise.getText());
			/* On ajoute la ligne dans l'arrayList de type Versement */
			AddVersement.arrayVersement.add(versement);

			/* On modifie les valeurs dans les cellules */
			Interface.modelVersement.setValueAt(champAnneeTaxe.getText(), Interface.tableVersement.getSelectedRow(), 0);
			Interface.modelVersement.setValueAt(champMontantTaxe.getText(), Interface.tableVersement.getSelectedRow(), 1);
			Interface.modelVersement.setValueAt(champEntreprise.getText(), Interface.tableVersement.getSelectedRow(), 2);
			
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
	
	/**
	 * @return un boolean indiquant s'il s'agit d'un reel ou pas
	 */
	private boolean isDouble(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}