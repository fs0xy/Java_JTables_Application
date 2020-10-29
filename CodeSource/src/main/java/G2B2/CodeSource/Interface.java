package G2B2.CodeSource;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import G2B2.CodeSource.Email.AddMail;
import G2B2.CodeSource.Email.Mail;
import G2B2.CodeSource.Email.UpdateMail;
import G2B2.CodeSource.Entreprise.AddEntreprise;
import G2B2.CodeSource.Entreprise.Entreprise;
import G2B2.CodeSource.Entreprise.UpdateEntreprise;
import G2B2.CodeSource.Stagiaire.AddStagiaire;
import G2B2.CodeSource.Stagiaire.Stagiaire;
import G2B2.CodeSource.Stagiaire.UpdateStagiaire;
import G2B2.CodeSource.Versement.AddVersement;
import G2B2.CodeSource.Versement.UpdateVersement;
import G2B2.CodeSource.Versement.Versement;
import au.com.bytecode.opencsv.CSVReader;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.io.FileNotFoundException;

import java.io.FileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;

import org.json.simple.parser.ParseException;

@SuppressWarnings("serial")
public class Interface extends JFrame {

	/* Déclaration des variables */
	private JPanel panneau;
	private Object[] columnsEntreprise = { "Nom", "Téléphone", "Email", "Adresse", "Stage", "Année stage",
			"Taxe versée", "Année versement", "Montant taxe", "Contact" };
	private Object[] columnsEmail = { "Expéditeur", "Destinataire", "Objet", "Date", "Message" };
	private Object[] columnsStagiaire = { "Prénom", "Nom", "Date de naissance", "Année stage", "Entreprise" };
	private Object[] columnsVersement = { "Année versement", "Montant versement", "Entreprise" };
	private JTabbedPane pane;
	public static DefaultTableModel modelEntreprise;
	public static DefaultTableModel modelEmail;
	public static DefaultTableModel modelStagiaire;
	public static DefaultTableModel modelVersement;
	public static JTable tableEntreprise = new JTable(modelEntreprise);
	public static JTable tableEmail = new JTable(modelEmail);
	public static JTable tableStagiaire = new JTable(modelStagiaire);
	public static JTable tableVersement = new JTable(modelVersement);

	/* TableRowSorter permet de filtrer les donnéees */
	private TableRowSorter<TableModel> rowSorterEntreprise;
	private TableRowSorter<TableModel> rowSorterEmail;
	private TableRowSorter<TableModel> rowSorterStagiaire;
	private TableRowSorter<TableModel> rowSorterVersement;

	/* Variables intervenant dans le filtrage */
	private JTextField jtfFilter = new JTextField(25);
	private String col = "";

	/*
	 * selectedIndex permet de savoir sur quel onglet de la JTable où se trouve
	 * l'utilsateur
	 */
	private int selectedIndex = 0;
	private FileReader monFichier;
	private BufferedReader tampon;

	/**
	 * Permet de créer l'interface contenant les onglets Entreprises, Emails,
	 * Stagiaires et Versements
	 */
	public Interface() {
		super("Application");
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				quitter();
			}
		});
		this.setPreferredSize(new Dimension(1150, 600));
		this.setJMenuBar(barreDeMenus());
		this.setContentPane(panneauDeContenu());

		/* Mise en relation du filtre avec la JTable correspondaNte */
		tableEntreprise.setRowSorter(rowSorterEntreprise);
		tableEmail.setRowSorter(rowSorterEmail);
		tableStagiaire.setRowSorter(rowSorterStagiaire);
		tableVersement.setRowSorter(rowSorterVersement);

		/*
		 * Récupère le texte entré dans le JTextField et effectue une opération
		 * de filtrage
		 */
		jtfFilter.getDocument().addDocumentListener(new DocumentListener() {

			public void insertUpdate(DocumentEvent e) {
				String text = jtfFilter.getText();
				rowSorterEntreprise.setRowFilter(RowFilter.regexFilter("(?i)" + text));
			}

			public void removeUpdate(DocumentEvent e) {
				String text = jtfFilter.getText();
				rowSorterEntreprise.setRowFilter(RowFilter.regexFilter("(?i)" + text));
			}

			public void changedUpdate(DocumentEvent e) {
				throw new UnsupportedOperationException();
			}
		});

		/*
		 * Permet de détecter le changement de JTable et de filtrer sur celle-ci
		 */
		pane.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent e) {
				selectedIndex = pane.getSelectedIndex();

				/* Si l'index est égal à 0 - on recherche dans entreprise */
				if (selectedIndex == 0) {
					jtfFilter.getDocument().addDocumentListener(new DocumentListener() {

						public void insertUpdate(DocumentEvent e) {
							String text = jtfFilter.getText();
							rowSorterEntreprise.setRowFilter(RowFilter.regexFilter("(?i)" + text));
						}

						public void removeUpdate(DocumentEvent e) {
							String text = jtfFilter.getText();
							rowSorterEntreprise.setRowFilter(RowFilter.regexFilter("(?i)" + text));
						}

						public void changedUpdate(DocumentEvent e) {
							throw new UnsupportedOperationException();
						}
					});
				}

				/* Si l'index est égal à 1 - on recherche dans emails */
				if (selectedIndex == 1) {
					jtfFilter.getDocument().addDocumentListener(new DocumentListener() {

						public void insertUpdate(DocumentEvent e) {
							String text = jtfFilter.getText();
							rowSorterEmail.setRowFilter(RowFilter.regexFilter("(?i)" + text));
						}

						public void removeUpdate(DocumentEvent e) {
							String text = jtfFilter.getText();
							rowSorterEmail.setRowFilter(RowFilter.regexFilter("(?i)" + text));
						}

						public void changedUpdate(DocumentEvent e) {
							throw new UnsupportedOperationException();
						}
					});
				}

				/* Si l'index est égal à 2 - on recherche dans stagiaires */
				if (selectedIndex == 2)

				{
					jtfFilter.getDocument().addDocumentListener(new DocumentListener() {

						public void insertUpdate(DocumentEvent e) {
							String text = jtfFilter.getText();
							rowSorterStagiaire.setRowFilter(RowFilter.regexFilter("(?i)" + text));
						}

						public void removeUpdate(DocumentEvent e) {
							String text = jtfFilter.getText();
							rowSorterStagiaire.setRowFilter(RowFilter.regexFilter("(?i)" + text));
						}

						public void changedUpdate(DocumentEvent e) {
							throw new UnsupportedOperationException();
						}
					});
				}

				/* Si l'index est égal à 3 - on recherche dans versements */
				if (selectedIndex == 3) {
					jtfFilter.getDocument().addDocumentListener(new DocumentListener() {

						public void insertUpdate(DocumentEvent e) {
							String text = jtfFilter.getText();
							rowSorterVersement.setRowFilter(RowFilter.regexFilter("(?i)" + text));
						}

						public void removeUpdate(DocumentEvent e) {
							String text = jtfFilter.getText();
							rowSorterVersement.setRowFilter(RowFilter.regexFilter("(?i)" + text));
						}

						public void changedUpdate(DocumentEvent e) {
							throw new UnsupportedOperationException();
						}
					});
				}

				/* Si l'index est égal à 4 - on recherche dans versements */
				if (selectedIndex == 4) {
					jtfFilter.getDocument().addDocumentListener(new DocumentListener() {

						public void insertUpdate(DocumentEvent e) {
							String text = jtfFilter.getText();
							rowSorterVersement.setRowFilter(RowFilter.regexFilter("(?i)" + text));
						}

						public void removeUpdate(DocumentEvent e) {
							String text = jtfFilter.getText();
							rowSorterVersement.setRowFilter(RowFilter.regexFilter("(?i)" + text));
						}

						public void changedUpdate(DocumentEvent e) {
							throw new UnsupportedOperationException();
						}
					});
				}

			}
		});

		this.setResizable(false);
		this.setVisible(true);
		this.pack();
	}

	/**
	 * @return un Jpanel contenant un JTabbedPane
	 */
	JPanel panneauDeContenu() {
		this.panneau = new JPanel();
		panneau.setLayout(new BorderLayout());
		panneau.add(panneauCentral());
		return this.panneau;
	}

	/**
	 * @return un JTabbedPane contenant l'ensemble des éléments de l'application
	 */
	JTabbedPane panneauCentral() {
		pane = new JTabbedPane();

		/*
		 * Permet de rendre les cellules non éditables manuellement, le bouton
		 * modifier est prévu à cet effet
		 */
		Interface.modelEntreprise = new DefaultTableModel() {
			public boolean isCellEditable(int row, int col) {
				return false;
			};
		};
		Interface.modelEmail = new DefaultTableModel() {
			public boolean isCellEditable(int row, int col) {
				return false;
			};
		};
		Interface.modelStagiaire = new DefaultTableModel() {
			public boolean isCellEditable(int row, int col) {
				return false;
			};
		};
		Interface.modelVersement = new DefaultTableModel() {
			public boolean isCellEditable(int row, int col) {
				return false;
			};
		};

		modelEntreprise.setColumnIdentifiers(columnsEntreprise);
		modelEmail.setColumnIdentifiers(columnsEmail);
		modelStagiaire.setColumnIdentifiers(columnsStagiaire);
		modelVersement.setColumnIdentifiers(columnsVersement);

		/* Nous relions le model à sa JTable */
		tableEntreprise.setModel(modelEntreprise);
		tableEmail.setModel(modelEmail);
		tableStagiaire.setModel(modelStagiaire);
		tableVersement.setModel(modelVersement);

		/* Ajout des JTable à la JTabbedPane */
		pane.addTab("Entreprises", new JScrollPane(tableEntreprise));
		pane.addTab("Emails", new JScrollPane(tableEmail));
		pane.addTab("Stagiaires", new JScrollPane(tableStagiaire));
		pane.addTab("Versements", new JScrollPane(tableVersement));

		/* Création de variables de filtrage sur les différentes JTables */
		this.rowSorterEntreprise = new TableRowSorter<TableModel>(tableEntreprise.getModel());
		this.rowSorterEmail = new TableRowSorter<TableModel>(tableEmail.getModel());
		this.rowSorterStagiaire = new TableRowSorter<TableModel>(tableStagiaire.getModel());
		this.rowSorterVersement = new TableRowSorter<TableModel>(tableVersement.getModel());

		this.getContentPane().add(pane);
		return pane;
	}

	/**
	 * 
	 * @return un JMenuBar contenant JMenus et JMenuItems
	 */
	JMenuBar barreDeMenus() {
		JMenuBar barreDeMenus = new JMenuBar();
		JMenu menuFichier = new JMenu("Fichier");
		JMenu menuAjouter = new JMenu("Ajouter");
		JMenu menuSupprimer = new JMenu("Supprimer");
		JMenu menuEffacer = new JMenu("Tout effacer");
		JMenu menuModifier = new JMenu("Modifier");
		JMenu menuRechercher = new JMenu("Rechercher");
		JMenu menuImporter = new JMenu("Importer");
		JMenu menuEnregistrer = new JMenu("Enregistrer sous");
		JMenuItem quitter = new JMenuItem("Quitter");
		quitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				quitter();
			}
		});

		JMenuItem itemNouveau = new JMenuItem("Nouveau");

		itemNouveau.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				erreurNouveau();
			}
		});
		
		menuSupprimer.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {
				// Si l'index correspond à Entreprises on supprime les lignes de
				// Entreprises
				if (selectedIndex == 0) {
					int i = tableEntreprise.getSelectedRow();
					if (i >= 0) {
						modelEntreprise.removeRow(i);
						AddEntreprise.arrayEntreprise.remove(i);
					} else {
						erreurLignes();
					}
				}

				// Si l'index correspond à Emails on supprime les lignes de
				// Emails
				if (selectedIndex == 1) {
					int i = tableEmail.getSelectedRow();
					if (i >= 0) {
						// remove a row from jtable
						modelEmail.removeRow(i);
						AddMail.arrayEmail.remove(i);
					} else {
						erreurLignes();
					}
				}

				// Si l'index correspond à Stagiaires on supprime les lignes de
				// Stagiaires
				if (selectedIndex == 2) {
					int i = tableStagiaire.getSelectedRow();
					if (i >= 0) {
						// remove a row from jtable
						modelStagiaire.removeRow(i);
						AddStagiaire.arrayStagiaire.remove(i);
					} else {
						erreurLignes();
					}
				}

				// Si l'index correspond à Versements on supprime les lignes de
				// Versements
				if (selectedIndex == 3) {
					int i = tableVersement.getSelectedRow();
					if (i >= 0) {
						// remove a row from jtable
						modelVersement.removeRow(i);
						AddVersement.arrayVersement.remove(i);
					} else {
						erreurLignes();
					}
				}
			}

			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
			}
		});

		menuEffacer.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {
				attentionEffacer();
			}

			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		menuModifier.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {
				// Si l'index correspond à Entreprises on modifie la ligne
				// sélectionné dans entreprises
				if (selectedIndex == 0) {
					if (tableEntreprise.getSelectedRow() < 0) {
						erreurLignes();
					} else {
						UpdateEntreprise majEntr = new UpdateEntreprise();
					}
				}

				// Si l'index correspond à Emails on modifie la ligne
				// sélectionné dans Emails
				if (selectedIndex == 1) {
					if (tableEmail.getSelectedRow() < 0) {
						erreurLignes();
					} else {
						UpdateMail majMail = new UpdateMail();
					}
				}

				// Si l'index correspond à Emails on modifie la ligne
				// sélectionné dans Emails
				if (selectedIndex == 2) {
					if (tableStagiaire.getSelectedRow() < 0) {
						erreurLignes();
					} else {
						UpdateStagiaire majStagiaire = new UpdateStagiaire();
					}
				}

				// Si l'index correspond à Versements on modifie la ligne
				// sélectionné dans Versements
				if (selectedIndex == 3) {
					if (tableVersement.getSelectedRow() < 0) {
						erreurLignes();
					} else {
						UpdateVersement majVersement = new UpdateVersement();
					}
				}
			}

			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		JMenuItem itemEntreprise = new JMenuItem("Entreprise");
		menuAjouter.add(itemEntreprise);
		itemEntreprise.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pane.setSelectedIndex(0);
				AddEntreprise add = new AddEntreprise();
			}
		});

		JMenuItem itemEmail = new JMenuItem("Email");
		menuAjouter.add(itemEmail);
		itemEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pane.setSelectedIndex(1);
				AddMail mail = new AddMail();
			}
		});

		JMenuItem itemStagiaire = new JMenuItem("Stagiaire");
		menuAjouter.add(itemStagiaire);
		itemStagiaire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pane.setSelectedIndex(2);
				AddStagiaire stagiaire = new AddStagiaire();
			}
		});
		JMenuItem itemVersement = new JMenuItem("Versement");
		menuAjouter.add(itemVersement);
		itemVersement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pane.setSelectedIndex(3);
				AddVersement stagiaire = new AddVersement();
			}
		});

		JMenuItem itemCsv = new JMenuItem("csv");
		menuEnregistrer.add(itemCsv);
		itemCsv.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					exportCSV();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		JMenuItem itemJson = new JMenuItem("json");
		menuEnregistrer.add(itemJson);
		itemJson.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					exportJSON();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		JMenuItem itemCsvImport = new JMenuItem("csv");
		menuImporter.add(itemCsvImport);
		itemCsvImport.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					importCsv();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		menuFichier.add(itemNouveau);
		menuFichier.add(menuImporter);
		menuFichier.add(menuEnregistrer);

		menuFichier.addSeparator();

		menuFichier.add(quitter);

		barreDeMenus.add(menuFichier);
		barreDeMenus.add(menuAjouter);
		barreDeMenus.add(menuModifier);
		barreDeMenus.add(menuSupprimer);
		barreDeMenus.add(menuEffacer);
		barreDeMenus.add(menuRechercher);
		jtfFilter.setMaximumSize(jtfFilter.getPreferredSize());
		barreDeMenus.add(jtfFilter);

		return barreDeMenus;
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
	 * Permet d'afficher une erreur si l'utilisateur veut supprimer/modifier une
	 * ligne sans l'avoir sélectionné
	 */
	private void erreurLignes() {
		JOptionPane.showMessageDialog(this, "Aucune ligne sélectionnée", "Attention", JOptionPane.WARNING_MESSAGE);
	}

	/**
	 * Permet d'afficher une erreur si l'utilisateur clique sur nouveau
	 * 
	 * @throws IOException
	 */
	private void erreurNouveau() {
		int confirmation;
		String[] choices = { "Csv", "Json" };
		confirmation = JOptionPane.showConfirmDialog(this, "Voulez-vous enregistrer ?", "Attention",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (confirmation == JOptionPane.YES_OPTION) {
			int reponse = JOptionPane.showOptionDialog(this, "Veuillez choisir le format", "Faites votre choix",
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
			if (reponse == 0) {
				try {
					exportCSV();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (reponse == 1) {
				try {
					exportJSON();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			dispose();
			Interface inter = new Interface();
		}
	}

	/**
	 * Permet d'afficher un avertissement si l'utilisateur souhaite tout effacer
	 */
	private void attentionEffacer() {
		int confirmation;
		confirmation = JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment tout effacer ?", "Attention",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (confirmation == JOptionPane.YES_OPTION) {
			((DefaultTableModel) tableEntreprise.getModel()).setRowCount(0);
			for (int j = 0; j < AddEntreprise.arrayEntreprise.size(); j++) {
				AddEntreprise.arrayEntreprise.clear();
			}

			((DefaultTableModel) tableEmail.getModel()).setRowCount(0);
			for (int j = 0; j < AddMail.arrayEmail.size(); j++) {
				AddMail.arrayEmail.clear();
			}

			((DefaultTableModel) tableStagiaire.getModel()).setRowCount(0);
			for (int j = 0; j < AddStagiaire.arrayStagiaire.size(); j++) {
				AddStagiaire.arrayStagiaire.clear();
			}

			((DefaultTableModel) tableVersement.getModel()).setRowCount(0);
			for (int j = 0; j < AddVersement.arrayVersement.size(); j++) {
				AddVersement.arrayVersement.clear();
			}
		}
	}

	/**
	 * Permet d'enregistrer et d'exporter les données au format csv
	 */
	private void exportCSV() throws IOException {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("./"));
		int actionDialog = chooser.showSaveDialog(this);
		if (actionDialog == JFileChooser.APPROVE_OPTION) {
			File fileName = new File(chooser.getSelectedFile() + ".csv");
			if (fileName == null)
				return;
			if (fileName.exists()) {
				actionDialog = JOptionPane.showConfirmDialog(this, "Remplacer le fichier existant ?");
				// Si la réponse est non alors on arrête l'enregistrement
				if (actionDialog == JOptionPane.NO_OPTION)
					return;
			}
			// Ecriture du fichier
			BufferedWriter outFile = new BufferedWriter(new FileWriter(fileName));
			outFile.flush(); // redundant, done by close()
			outFile.close();

			Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "ISO-8859-1"));

			for (int i = 0; i < Interface.tableEntreprise.getColumnCount(); i++) {
				out.write(Interface.tableEntreprise.getColumnName(i) + " ; " + "\t");
			}
			out.write("\n");
			for (int i = 0; i < Interface.tableEntreprise.getRowCount(); i++) {
				for (int j = 0; j < Interface.tableEntreprise.getColumnCount(); j++) {
					out.write(Interface.tableEntreprise.getValueAt(i, j) + " ; " + "\t");
				}
				out.write("\n");
			}
			out.write("\n");

			for (int i = 0; i < Interface.tableEmail.getColumnCount(); i++) {
				out.write(Interface.tableEmail.getColumnName(i) + " ; " + "\t");
			}
			out.write("\n");
			for (int i = 0; i < Interface.tableEmail.getRowCount(); i++) {
				for (int j = 0; j < Interface.tableEmail.getColumnCount(); j++) {
					out.write(Interface.tableEmail.getValueAt(i, j) + " ; " + "\t");
				}
				out.write("\n");
			}
			out.write("\n");

			for (int i = 0; i < Interface.tableStagiaire.getColumnCount(); i++) {
				out.write(Interface.tableStagiaire.getColumnName(i) + " ; " + "\t");
			}
			out.write("\n");
			for (int i = 0; i < Interface.tableStagiaire.getRowCount(); i++) {
				for (int j = 0; j < Interface.tableStagiaire.getColumnCount(); j++) {
					out.write(Interface.tableStagiaire.getValueAt(i, j) + " ; " + "\t");
				}
				out.write("\n");
			}

			out.write("\n");

			for (int i = 0; i < Interface.tableVersement.getColumnCount(); i++) {
				out.write(Interface.tableVersement.getColumnName(i) + " ; " + "\t");
			}
			out.write("\n");
			for (int i = 0; i < Interface.tableVersement.getRowCount(); i++) {
				for (int j = 0; j < Interface.tableVersement.getColumnCount(); j++) {
					out.write(Interface.tableVersement.getValueAt(i, j) + " ; " + "\t");
				}
				out.write("\n");
			}

			out.write("\n");
			out.close();
		}
	}

	/**
	 * Permet d'enregistrer et d'exporter les donnÃ©es au format json
	 */
	private void exportJSON() throws IOException {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("./"));
		int actionDialog = chooser.showSaveDialog(this);
		if (actionDialog == JFileChooser.APPROVE_OPTION) {
			File fileName = new File(chooser.getSelectedFile() + ".json");
			if (fileName == null)
				return;
			if (fileName.exists()) {
				actionDialog = JOptionPane.showConfirmDialog(this, "Remplacer le fichier existant ?");
				// Si la ré©ponse est non alors on arrête l'enregistrement
				if (actionDialog == JOptionPane.NO_OPTION)
					return;
			}
			// Ecriture du fichier
			Writer outFile = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "ISO-8859-1"));
			outFile.flush(); // redundant, done by close()

			for (int i = 0; i < Interface.tableEntreprise.getRowCount(); i++) {

				outFile.write("{");
				for (int j = 0; j < Interface.tableEntreprise.getColumnCount(); j++) {

					outFile.write("\"" + Interface.tableEntreprise.getColumnName(j) + "\"" + ":" + "\""
							+ Interface.tableEntreprise.getValueAt(i, j).toString() + "\"");
					if (j < Interface.tableEntreprise.getColumnCount() - 1)
						outFile.write(",");
				}
				outFile.write("}");
				outFile.write("\r\n");

			}
			for (int i = 0; i < Interface.tableEmail.getRowCount(); i++) {

				outFile.write("{");
				for (int j = 0; j < Interface.tableEmail.getColumnCount(); j++) {
					outFile.write("\"" + Interface.tableEmail.getColumnName(j) + "\"" + ":" + "\""
							+ Interface.tableEmail.getValueAt(i, j).toString() + "\"");
					if (j < Interface.tableEmail.getColumnCount() - 1)
						outFile.write(",");
				}
				outFile.write("}");
				outFile.write("\r\n");

			}
			for (int i = 0; i < Interface.tableStagiaire.getRowCount(); i++) {

				outFile.write("{");
				for (int j = 0; j < Interface.tableStagiaire.getColumnCount(); j++) {
					outFile.write("\"" + Interface.tableStagiaire.getColumnName(j) + "\"" + ":" + "\""
							+ Interface.tableStagiaire.getValueAt(i, j).toString() + "\"");
					if (j < Interface.tableStagiaire.getColumnCount() - 1)
						outFile.write(",");
				}
				outFile.write("}");
				outFile.write("\r\n");
			}

			for (int i = 0; i < Interface.tableVersement.getRowCount(); i++) {

				outFile.write("{");
				for (int j = 0; j < Interface.tableVersement.getColumnCount(); j++) {

					outFile.write("\"" + Interface.tableVersement.getColumnName(j) + "\"" + ":" + "\""
							+ Interface.tableVersement.getValueAt(i, j).toString() + "\"");
					if (j < Interface.tableVersement.getColumnCount() - 1)
						outFile.write(",");
				}
				outFile.write("}");
				outFile.write("\r\n");

			}
			outFile.close();
		}
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
				modelEntreprise.addRow(lst.get(i));
				champNom = tableEntreprise.getValueAt(tableEntreprise.getRowCount() - 1, 0).toString().trim();
				champTelephone = tableEntreprise.getValueAt(tableEntreprise.getRowCount() - 1, 1).toString().trim();
				champEmail = tableEntreprise.getValueAt(tableEntreprise.getRowCount() - 1, 2).toString().trim();
				champAdresse = tableEntreprise.getValueAt(tableEntreprise.getRowCount() - 1, 3).toString().trim();
				boutonStage = tableEntreprise.getValueAt(tableEntreprise.getRowCount() - 1, 4).toString().trim();
				boutonTaxe = tableEntreprise.getValueAt(tableEntreprise.getRowCount() - 1, 5).toString().trim();
				boutonContact = tableEntreprise.getValueAt(tableEntreprise.getRowCount() - 1, 6).toString().trim();
				champAnneeStage = tableEntreprise.getValueAt(tableEntreprise.getRowCount() - 1, 7).toString().trim();
				champAnneeTaxe = tableEntreprise.getValueAt(tableEntreprise.getRowCount() - 1, 8).toString().trim();
				champMontantTaxe = tableEntreprise.getValueAt(tableEntreprise.getRowCount() - 1, 9).toString().trim();
				Entreprise entre = new Entreprise(champNom, champTelephone, champEmail, champAdresse, boutonStage,
						boutonTaxe, boutonContact, champAnneeStage, champAnneeTaxe, champMontantTaxe);
				AddEntreprise.arrayEntreprise.add(entre);
			}

			for (int i = indexMail; i < indexStage - 2; i++) {
				modelEmail.addRow(lst.get(i));
				champExpediteur = tableEmail.getValueAt(tableEmail.getRowCount() - 1, 0).toString().trim();
				champDestinataire = tableEmail.getValueAt(tableEmail.getRowCount() - 1, 1).toString().trim();
				champObjet = tableEmail.getValueAt(tableEmail.getRowCount() - 1, 2).toString().trim();
				champDate = tableEmail.getValueAt(tableEmail.getRowCount() - 1, 3).toString().trim();
				champTexte = tableEmail.getValueAt(tableEmail.getRowCount() - 1, 3).toString().trim();
				Mail mail = new Mail(champExpediteur, champDestinataire, champObjet, champDate, champTexte);
				AddMail.arrayEmail.add(mail);
			}

			for (int i = indexStage - 1; i < indexVersement - 3; i++) {
				modelStagiaire.addRow(lst.get(i));
				champPrenom = tableStagiaire.getValueAt(tableStagiaire.getRowCount() - 1, 0).toString().trim();
				champNomS = tableStagiaire.getValueAt(tableStagiaire.getRowCount() - 1, 1).toString().trim();
				champDateNaissance = tableStagiaire.getValueAt(tableStagiaire.getRowCount() - 1, 2).toString().trim();
				champAnneeStageS = tableStagiaire.getValueAt(tableStagiaire.getRowCount() - 1, 3).toString().trim();
				champEntrepriseS = tableStagiaire.getValueAt(tableStagiaire.getRowCount() - 1, 4).toString().trim();
				Stagiaire stage = new Stagiaire(champPrenom, champNomS, champDateNaissance, champAnneeStageS,
						champEntrepriseS);
				AddStagiaire.arrayStagiaire.add(stage);
			}

			for (int i = indexVersement - 2; i < lst.size(); i++) {
				modelVersement.addRow(lst.get(i));
				champAnneeV = tableVersement.getValueAt(tableVersement.getRowCount() - 1, 0).toString().trim();
				champMontantV = tableVersement.getValueAt(tableVersement.getRowCount() - 1, 1).toString().trim();
				champEntrepriseV = tableVersement.getValueAt(tableVersement.getRowCount() - 1, 2).toString().trim();
				Versement verse = new Versement(champAnneeV, champMontantV, champEntrepriseV);
				AddVersement.arrayVersement.add(verse);
			}

		}
	}
}