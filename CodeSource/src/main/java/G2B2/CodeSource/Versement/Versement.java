package G2B2.CodeSource.Versement;

import java.util.Calendar;
import java.util.Date;

public class Versement {

	private String champAnneeTaxe;
	private String champMontantTaxe;
	private String champEntreprise;

	/**
	 * Constructeur permettant de stocker les valeurs des champs
	 */
	public Versement(String anneeTaxe, String montantTaxe, String entreprise) {
		if (montantTaxe == null) {
			montantTaxe = "0";
		}
		if (montantTaxe.indexOf('-') != -1) {
			montantTaxe = "0";
		}
		if (entreprise == null) {
			entreprise = " ErreurNull Compagnie INC";
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

		this.champAnneeTaxe = anneeTaxe;
		this.champMontantTaxe = montantTaxe;
		this.champEntreprise = entreprise;
	}

	/**
	 * Permet de récupérer la valeur du champ année versement
	 * 
	 * @return un String étant l'année du versement
	 */
	public String getAnneeTaxe() {
		return this.champAnneeTaxe;
	}

	/**
	 * Permet de récupérer la valeur du champ montant versement
	 * 
	 * @return un String étant le montant du versement
	 */
	public String getMontantTaxe() {
		return this.champMontantTaxe;
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
