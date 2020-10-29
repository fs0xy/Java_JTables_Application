package G2B2.CodeSource.Email;

public class Mail {

	private String champExpediteur;
	private String champDestinataire;
	private String champObjet;
	private String champTexteMail;
	private String champDate;

	/**
	 * Constructeur permettant de stocker les valeurs des champs
	 */
	public Mail(String expe, String destinataire, String objet, String date, String texte) {
		if (expe == null) {
			expe = "";
		}		
		if(destinataire == null){
			destinataire = "";
		}
		if (objet == null) {
			objet = "";
		}
		if (date == null) {
			date = "";
		}

		if (texte == null) {
			texte = "";
		}

		this.champDestinataire = destinataire;
		this.champExpediteur = expe;
		this.champObjet = objet;
		this.champDate = date;
		this.champTexteMail = texte;
	}

	/**
	 * Permet de récupérer la valeur du champ destinataire
	 * 
	 * @return un String étant le destinataire
	 */
	public String getDestinataire() {
		return this.champDestinataire;
	}

	/**
	 * Permet de récupérer la valeur du champ expediteur
	 * 
	 * @return un String étant l'expediteur
	 */
	public String getExpediteur() {
		return this.champExpediteur;
	}

	/**
	 * Permet de récupérer la valeur du champ objet
	 * 
	 * @return un String étant l'obejt
	 */
	public String getObjet() {
		return this.champObjet;
	}

	/**
	 * Permet de récupérer la valeur du champ date
	 * 
	 * @return un String étant la date
	 */
	public String getDate() {
		return this.champDate;
	}

	/**
	 * Permet de récupérer la valeur de la zone de texte représentant le message
	 * 
	 * @return un String étant le mesasge de l'email
	 */
	public String getTexte() {
		return this.champTexteMail;
	}
}
