package G2B2.CodeSource;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import G2B2.CodeSource.Email.AddMail;
import G2B2.CodeSource.Email.Mail;
import G2B2.CodeSource.Entreprise.AddEntreprise;
import G2B2.CodeSource.Entreprise.Entreprise;
import G2B2.CodeSource.Stagiaire.AddStagiaire;
import G2B2.CodeSource.Stagiaire.Stagiaire;
import G2B2.CodeSource.Versement.AddVersement;
import G2B2.CodeSource.Versement.Versement;

public class Accueil extends JFrame {
	/**
	 * Permet de créer l'interface contenant les onglets Entreprises, Emails,
	 * Stagiaires et Versements
	 */
	public Accueil() {
		super("Application");
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				quitter();
			}
		});
		this.setPreferredSize(new Dimension(400, 300));
		this.setContentPane(panneauDeContenu());
		this.setResizable(false);
		this.setVisible(true);
		this.pack();
	}

	/**
	 * Permet de demander confirmation avant de fermer la fenêtre
	 */
	private void quitter() {
		int confirmation;
		confirmation = JOptionPane.showConfirmDialog(this, "Voulez-vous réellement quitter ?", "Quitter",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (confirmation == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	/**
	 * @return un Jpanel contenant un JTabbedPane
	 */
	JPanel panneauDeContenu() {
		JPanel panneau = new JPanel();
		panneau.setLayout(new BoxLayout(panneau, BoxLayout.Y_AXIS));

		panneau.setBorder(new EmptyBorder(new Insets(50, 0, 0, 0)));

		JLabel label = new JLabel("Choisissez le mode de démarrage : ");
		label.setAlignmentX(panneau.CENTER_ALIGNMENT);
		JLabel label2 = new JLabel("Importer : ");
		label2.setAlignmentX(panneau.CENTER_ALIGNMENT);
		JButton nouveauBouton = new JButton("Nouveau");
		nouveauBouton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Interface inter = new Interface();
				dispose();
			}
		});

		nouveauBouton.setAlignmentX(panneau.CENTER_ALIGNMENT);

		String formats[] = { "Choisir le format", "csv" };
		final JComboBox cb = new JComboBox(formats);
		cb.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (cb.getSelectedItem().equals("csv")) {
					try {
						Interface inter = new Interface();
						;
						dispose();
						importCsv();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});

		cb.setMaximumSize(new Dimension(150, 25));

		panneau.add(label);
		panneau.add(Box.createRigidArea(new Dimension(0, 20)));
		panneau.add(nouveauBouton);
		panneau.add(Box.createRigidArea(new Dimension(0, 20)));
		panneau.add(label2);
		panneau.add(Box.createRigidArea(new Dimension(0, 20)));
		panneau.add(cb);

		return panneau;
	}

	/**
	 * Permet d'importer des données au format csv
	 * 
	 * @throws IOException
	 */
	private void importCsv() throws IOException {
		String champNom = "";
		String champTelephone = "";
		String champEmail = "";
		String champAdresse = "";
		String boutonStage = "";
		String boutonTaxe = "";
		String boutonContact = "";
		String champAnneeStage = "";
		String champAnneeTaxe = "";
		String champMontantTaxe = "";

		String champExpediteur = "";
		String champDestinataire = "";
		String champObjet = "";
		String champDate = "";
		String champTexte = "";

		String champPrenom = "";
		String champNomS = "";
		String champDateNaissance = "";
		String champAnneeStageS = "";
		String champEntrepriseS = "";

		String champAnneeV = "";
		String champMontantV = "";
		String champEntrepriseV = "";

		ArrayList<String[]> lst = new ArrayList<String[]>();
		List<String[]> lstEntreprise = new ArrayList<String[]>();
		int indexEntreprise = 0;
		int indexMail = 0;
		int indexStage = 0;
		int indexVersement = 0;
		int cpt = 0;
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("./"));
		int actionDialog = chooser.showOpenDialog(this);
		if (actionDialog == JFileChooser.APPROVE_OPTION) {
			File fileName = new File(chooser.getSelectedFile() + "");
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "ISO-8859-1"));
			String line = null;
			while ((line = br.readLine()) != null) {
				if (line.trim().contains("Nom") && line.trim().contains("Téléphone") && line.trim().contains("Email")
						&& line.trim().contains("Adresse") && line.trim().contains("Stage")
						&& line.trim().contains("Année stage") && line.trim().contains("Taxe versée")
						&& line.trim().contains("Année versement") && line.trim().contains("Montant taxe")
						&& line.trim().contains("Contact") && !line.trim().equals("")) {
					indexEntreprise = cpt;
				}
				if (line.trim().contains("Expéditeur") && line.trim().contains("Destinataire")
						&& line.trim().contains("Objet") && line.trim().contains("Date")
						&& line.trim().contains("Message") && !line.trim().equals("")) {
					indexMail = cpt;
				}
				if (line.trim().contains("Prénom") && line.trim().contains("Nom")
						&& line.trim().contains("Date de naissance") && line.trim().contains("Année stage")
						&& line.trim().contains("Entreprise") && !line.trim().equals("")) {
					indexStage = cpt;
				}
				if (line.trim().contains("Année versement") && line.trim().contains("Montant versement")
						&& line.trim().contains("Entreprise") && !line.trim().equals("")) {
					indexVersement = cpt;
				}

				if (!line.trim().equals("")) {
					String[] splitted = line.split(";");
					lst.add(splitted);
				}
				cpt++;
			}
			br.close();

			for (int i = indexEntreprise + 1; i < indexMail - 1; i++) {
				Interface.modelEntreprise.addRow(lst.get(i));
				champNom = Interface.tableEntreprise.getValueAt(Interface.tableEntreprise.getRowCount() - 1, 0)
						.toString().trim();
				champTelephone = Interface.tableEntreprise.getValueAt(Interface.tableEntreprise.getRowCount() - 1, 1)
						.toString().trim();
				champEmail = Interface.tableEntreprise.getValueAt(Interface.tableEntreprise.getRowCount() - 1, 2)
						.toString().trim();
				champAdresse = Interface.tableEntreprise.getValueAt(Interface.tableEntreprise.getRowCount() - 1, 3)
						.toString().trim();
				boutonStage = Interface.tableEntreprise.getValueAt(Interface.tableEntreprise.getRowCount() - 1, 4)
						.toString().trim();
				boutonTaxe = Interface.tableEntreprise.getValueAt(Interface.tableEntreprise.getRowCount() - 1, 5)
						.toString().trim();
				boutonContact = Interface.tableEntreprise.getValueAt(Interface.tableEntreprise.getRowCount() - 1, 6)
						.toString().trim();
				champAnneeStage = Interface.tableEntreprise.getValueAt(Interface.tableEntreprise.getRowCount() - 1, 7)
						.toString().trim();
				champAnneeTaxe = Interface.tableEntreprise.getValueAt(Interface.tableEntreprise.getRowCount() - 1, 8)
						.toString().trim();
				champMontantTaxe = Interface.tableEntreprise.getValueAt(Interface.tableEntreprise.getRowCount() - 1, 9)
						.toString().trim();
				Entreprise entre = new Entreprise(champNom, champTelephone, champEmail, champAdresse, boutonStage,
						boutonTaxe, boutonContact, champAnneeStage, champAnneeTaxe, champMontantTaxe);
				AddEntreprise.arrayEntreprise.add(entre);
			}

			for (int i = indexMail; i < indexStage - 2; i++) {
				Interface.modelEmail.addRow(lst.get(i));
				champExpediteur = Interface.tableEmail.getValueAt(Interface.tableEmail.getRowCount() - 1, 0).toString()
						.trim();
				champDestinataire = Interface.tableEmail.getValueAt(Interface.tableEmail.getRowCount() - 1, 1)
						.toString().trim();
				champObjet = Interface.tableEmail.getValueAt(Interface.tableEmail.getRowCount() - 1, 2).toString()
						.trim();
				champDate = Interface.tableEmail.getValueAt(Interface.tableEmail.getRowCount() - 1, 3).toString()
						.trim();
				champTexte = Interface.tableEmail.getValueAt(Interface.tableEmail.getRowCount() - 1, 3).toString()
						.trim();
				Mail mail = new Mail(champExpediteur, champDestinataire, champObjet, champDate, champTexte);
				AddMail.arrayEmail.add(mail);
			}

			for (int i = indexStage - 1; i < indexVersement - 3; i++) {
				Interface.modelStagiaire.addRow(lst.get(i));
				champPrenom = Interface.tableStagiaire.getValueAt(Interface.tableStagiaire.getRowCount() - 1, 0)
						.toString().trim();
				champNomS = Interface.tableStagiaire.getValueAt(Interface.tableStagiaire.getRowCount() - 1, 1)
						.toString().trim();
				champDateNaissance = Interface.tableStagiaire.getValueAt(Interface.tableStagiaire.getRowCount() - 1, 2)
						.toString().trim();
				champAnneeStageS = Interface.tableStagiaire.getValueAt(Interface.tableStagiaire.getRowCount() - 1, 3)
						.toString().trim();
				champEntrepriseS = Interface.tableStagiaire.getValueAt(Interface.tableStagiaire.getRowCount() - 1, 4)
						.toString().trim();
				Stagiaire stage = new Stagiaire(champPrenom, champNomS, champDateNaissance, champAnneeStageS,
						champEntrepriseS);
				AddStagiaire.arrayStagiaire.add(stage);
			}

			for (int i = indexVersement - 2; i < lst.size(); i++) {
				Interface.modelVersement.addRow(lst.get(i));
				champAnneeV = Interface.tableVersement.getValueAt(Interface.tableVersement.getRowCount() - 1, 0)
						.toString().trim();
				champMontantV = Interface.tableVersement.getValueAt(Interface.tableVersement.getRowCount() - 1, 1)
						.toString().trim();
				champEntrepriseV = Interface.tableVersement.getValueAt(Interface.tableVersement.getRowCount() - 1, 2)
						.toString().trim();
				Versement verse = new Versement(champAnneeV, champMontantV, champEntrepriseV);
				AddVersement.arrayVersement.add(verse);
			}

		}
	}

	public static void main(String args[]) {
		Accueil cadre = new Accueil();
	}
}
