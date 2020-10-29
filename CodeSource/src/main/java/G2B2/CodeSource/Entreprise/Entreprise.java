package G2B2.CodeSource.Entreprise;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

@SuppressWarnings("serial")
public class Entreprise implements Serializable {

	/* Déclaration des variables */
	private String champNom;
	private String champTelephone;
	private String champEmail;
	private String champNotes;
	private String champAdresse;
	private String boutonStage;
	private String boutonTaxe;
	private String boutonContact;
	private String champAnneeStage;
	private String champAnneeTaxe;
	private String champMontantTaxe;

	/**
	 * Permet de stocker les valeurs des champs d'une Entreprise
	 * 
	 * @param nom
	 * @param telephone
	 * @param email
	 * @param adresse
	 * @param notes
	 * @param boutonStage
	 * @param anneeStage
	 * @param boutonTaxe
	 * @param anneeTaxe
	 * @param montantTaxe
	 * @param boutonContact
	 */
	public Entreprise(String nom, String telephone, String email, String adresse, String boutonStage, String anneeStage,
			String boutonTaxe, String anneeTaxe, String montantTaxe, String boutonContact) {
		if (nom == null) {
			nom = "";
		}
		if (telephone == null) {
			telephone = "";
		}
		if (email == null) {
			email = "";
		}
		if (adresse == null) {
			adresse = "";
		}
		if (boutonStage == null) {
			boutonStage = "";
		}
		if (anneeStage == null) {
			anneeStage = "";
		}
		if (boutonTaxe == null) {
			boutonTaxe = "";
		}
		if (anneeTaxe == null) {
			anneeTaxe = "";
		}
		if (montantTaxe == null) {
			montantTaxe = "0";
		}
		if (montantTaxe.indexOf('-') != -1) {
			montantTaxe = "?";
		}
		if (boutonContact == null) {
			boutonContact = "";
		}

		if (email.indexOf("@") == -1) {
			email = "erreur@mailfaux.fr";
		}
		if (telephone.length() != 10) {
			if (telephone.length() > 10) {
				char[] tab = new char[telephone.length()];
				for (int i = 0; i < telephone.length(); i++) {
					tab[i] = telephone.charAt(i);
				}
				telephone = "";
				for (int i = 0; i < 10; i++) {
					telephone = telephone + tab[i];
				}
			}
			if (telephone.length() < 10) {
				telephone = "tel Errone";
			}
		}
		if (anneeStage == null) {
			anneeStage = "";
			Date Datejour = new Date();
			Calendar c = Calendar.getInstance();
			c.setTime(Datejour);
			anneeStage += c.get(Calendar.DATE);
			anneeStage += "/" + (c.get(Calendar.MONTH) + 1);
			anneeStage += "/" + c.get(Calendar.YEAR);
		}
		if (anneeTaxe == null) {
			anneeTaxe = "";
			Date Datejour = new Date();
			Calendar c = Calendar.getInstance();
			c.setTime(Datejour);
			anneeTaxe += c.get(Calendar.DATE);
			anneeTaxe += "/" + (c.get(Calendar.MONTH) + 1);
			anneeTaxe += "/" + c.get(Calendar.YEAR);
		}
		this.champNom = nom;
		this.champTelephone = telephone;
		this.champEmail = email;
		this.champAdresse = adresse;
		this.boutonStage = boutonStage;
		this.champAnneeStage = anneeStage;
		this.boutonTaxe = boutonTaxe;
		this.champAnneeTaxe = anneeTaxe;
		this.champMontantTaxe = montantTaxe;
		this.boutonContact = boutonContact;
	}

	/**
	 * Permet de récupérer le contenu du champ nom
	 * 
	 * @return un String
	 */
	public String getnomE() {
		return this.champNom;
	}

	/**
	 * Permet de récupérer le contenu du champ téléphone
	 * 
	 * @return un String
	 */
	public String getTelephoneE() {
		return this.champTelephone;
	}

	/**
	 * Permet de récupérer le contenu du champ Email
	 * 
	 * @return un String
	 */
	public String getEmailE() {
		return this.champEmail;
	}

	/**
	 * Permet de récupérer le contenu du champ adresse
	 * 
	 * @return un String
	 */
	public String getAdresseE() {
		return this.champAdresse;
	}

	/**
	 * Permet de récupérer le contenu du champ année stage
	 * 
	 * @return un String
	 */
	public String getAnneeStageE() {
		return this.champAnneeStage;
	}

	/**
	 * Permet de récupérer le contenu du champ année taxe
	 * 
	 * @return un String
	 */
	public String getAnneeTaxeE() {
		return this.champAnneeTaxe;
	}

	/**
	 * Permet de récupérer le contenu du champ montant taxe
	 * 
	 * @return un String
	 */
	public String getMontantTaxeE() {
		return this.champMontantTaxe;
	}

	/**
	 * Permet de récupérer le contenu du valeur du bouton stage sélectionné
	 * 
	 * @return un String
	 */
	public String getBoutonStage() {
		return this.boutonStage;
	}

	/**
	 * Permet de récupérer le contenu du valeur du bouton taxe sélectionné
	 * 
	 * @return un String
	 */
	public String getBoutonTaxe() {
		return this.boutonTaxe;
	}

	/**
	 * Permet de récupérer le contenu du valeur du bouton contact sélectionné
	 * 
	 * @return un String
	 */
	public String getBoutonContact() {
		return this.boutonContact;
	}

}
