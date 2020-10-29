package G2B2.CodeSource.Stagiaire;

import java.util.Calendar;
import java.util.Date;

public class Stagiaire {
	private String champPrenom;
	private String champNom;
	private String champDateNaissance;
	private String champAnneStage;
	private String champEntreprise;

	/**
	 * Constructeur permettant de stocker les valeurs des champs
	 */
	public Stagiaire(String prenom, String nom, String dateNaissance, String anneeStage, String entreprise) {
		if (prenom == null) {
			prenom = "";
		}
		if (nom == null) {
			nom = "";
		}
		if (entreprise == null) {
			entreprise = "erreur Compagnie INC";
		}
		if (dateNaissance == null) {
			Date d = new Date();
			Calendar c = Calendar.getInstance();
			c.setTime(d);
			dateNaissance += c.get(Calendar.DATE);
			dateNaissance += "/" + (c.get(Calendar.MONTH) + 1);
			dateNaissance += "/" + c.get(Calendar.YEAR);
		}
		if (anneeStage == null) {
			Date d = new Date();
			Calendar c = Calendar.getInstance();
			c.setTime(d);
			dateNaissance += "/" + c.get(Calendar.YEAR);
		}

		this.champPrenom = prenom;
		this.champNom = nom;
		this.champDateNaissance = dateNaissance;
		this.champAnneStage = anneeStage;
		this.champEntreprise = entreprise;
	}

	/**
	 * Permet de récupérer la valeur du champ prenom
	 * 
	 * @return un String étant le prenom
	 */
	public String getPrenom() {
		return this.champPrenom;
	}

	/**
	 * Permet de récupérer la valeur du champ nom
	 * 
	 * @return un String étant le nom
	 */
	public String getNom() {
		return this.champNom;
	}

	/**
	 * Permet de récupérer la valeur du champ date
	 * 
	 * @return un String étant la date de naissance
	 */
	public String getDate() {
		return this.champDateNaissance;
	}

	/**
	 * Permet de récupérer la valeur du champ année stage
	 * 
	 * @return un String étant l'année du stage
	 */
	public String getAnneeStage() {
		return this.champAnneStage;
	}

	/**
	 * Permet de récupérer la valeur du champ entreprise
	 * 
	 * @return un String étant le nom de l'entreprise
	 */
	public String getEntreprise() {
		return this.champEntreprise;
	}
}
