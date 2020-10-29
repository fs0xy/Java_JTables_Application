package G2B2.CodeSource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import G2B2.CodeSource.Entreprise.Entreprise;
import junit.framework.TestCase;

public class TestEntreprise extends TestCase {

	@SuppressWarnings("unused")
	public void testEntreprise() {
		Entreprise e1 = new Entreprise("MacDo", "0665340714", "b.didier745@laposte.net", "1 rue sauniere",
				"bouton stage", "bouton taxe", "bouton contact", "anneeStage", "anneeTaxe", "montantTaxe");
		assertNotNull("non instancier entreprise", e1);
	}

	public void testGetnomE() {

		Entreprise e2 = new Entreprise("MacDo", "0665340714", "b.didier745@laposte.net", "1 rue sauniere",
				"bouton stage", "bouton taxe", "bouton contact", "anneeStage", "anneeTaxe", "montantTaxe");
		Entreprise e3 = new Entreprise("Mac Do", "0665340714", "b.didier745@laposte.net", "1 rue sauniere",
				"bouton stage", "bouton taxe", "bouton contact", "anneeStage", "anneeTaxe", "montantTaxe");
		Entreprise e4 = new Entreprise(null, "0665340714", "b.didier745@laposte.net", "1 rue sauniere", "bouton stage",
				"bouton taxe", "bouton contact", "anneeStage", "anneeTaxe", "montantTaxe");
		assertNotNull("nom null", e4.getnomE());
		assertTrue("nom Normal", e2.getnomE().equals("MacDo"));
		assertTrue("Nom avec espace", e3.getnomE().equals("Mac Do"));

	}

	public void testGetTelephoneE() {
		Entreprise e1 = new Entreprise("MacDo", null, "b.didier745@laposte.net", "1 rue sauniere", "bouton stage",
				"bouton taxe", "bouton contact", "anneeStage", "anneeTaxe", "montantTaxe");
		Entreprise e2 = new Entreprise("MacDo", "0665340714", "b.didier745@laposte.net", "1 rue sauniere",
				"bouton stage", "bouton taxe", "bouton contact", "anneeStage", "anneeTaxe", "montantTaxe");
		Entreprise e3 = new Entreprise("MacDo", "06653407144587", "b.didier745@laposte.net", "1 rue sauniere",
				"bouton stage", "bouton taxe", "bouton contact", "anneeStage", "anneeTaxe", "montantTaxe");
		assertNotNull("numero null", e1.getTelephoneE());
		assertTrue("numero normal", e2.getTelephoneE().equals("0665340714"));
		if (e3.getTelephoneE().length() != 10) {
			fail("numero avec plus ou moins de 10 chiffre");
		}

	}

	public void testGetEmailE() {
		Entreprise e1 = new Entreprise("MacDo", "0665340714", "b.didier745laposte.net", "1 rue sauniere",
				"bouton stage", "bouton taxe", "bouton contact", "anneeStage", "anneeTaxe", "montantTaxe");
		Entreprise e2 = new Entreprise("MacDo", "0665340714", null, "1 rue sauniere", "bouton stage", "bouton taxe",
				"bouton contact", "anneeStage", "anneeTaxe", "montantTaxe");
		Entreprise e3 = new Entreprise("MacDo", "0665340714", "b.didier745@laposte.net", "1 rue sauniere",
				"bouton stage", "bouton taxe", "bouton contact", "anneeStage", "anneeTaxe", "montantTaxe");

		if (e1.getEmailE().indexOf("@") == -1) {
			fail("adresse Email sans @ accepte");
		}
		if (e2.getEmailE() == null) {
			fail("adresse email vide accepte erreur");
		}

		assertTrue("email basic", e3.getEmailE().equals("b.didier745@laposte.net"));
	}

	public void testGetAdresseE() {
		Entreprise e1 = new Entreprise("MacDo", "0665340714", "b.didier745@laposte.net", null, "bouton stage",
				"bouton taxe", "bouton contact", "anneeStage", "anneeTaxe", "montantTaxe");
		Entreprise e2 = new Entreprise("MacDo", "0665340714", "b.didier745@laposte.net", "1 rue sauniere",
				"bouton stage", "bouton taxe", "bouton contact", "anneeStage", "anneeTaxe", "montantTaxe");
		if (e1.getAdresseE() == null) {
			fail("adresse null accepte");
		}
		assertTrue("adresse normal", e2.getAdresseE().equals( "1 rue sauniere"));
	}

	public void testGetMontantTaxeE() {
		Entreprise e1 = new Entreprise("MacDo", "0665340714", "b.didier745@laposte.net", "1 rue sauniere",
				"bouton stage", "bouton taxe", "bouton contact", "anneeStage", "-54", "lk");
		Entreprise e2 = new Entreprise("MacDo", "0665340714", "b.didier745@laposte.net", "1 rue sauniere",
				"bouton stage", "bouton taxe", "bouton contact", "anneeStage", null, null);
		Entreprise e3 = new Entreprise("MacDo", "0665340714", "b.didier745@laposte.net", "1 rue sauniere",
				"bouton stage", "bouton taxe", "bouton contact", "anneeStage", "2541", "2541");

		assertTrue("nombre normal", e3.getMontantTaxeE().equals("2541"));
		if (e1.getMontantTaxeE().indexOf("-") != -1) {
			fail("montant de la taxe negatif");
		}
		if (e2.getMontantTaxeE() == null) {
			fail("montant nul");
		}

	}

	public void testGetBoutonStage() {
		Entreprise e1 = new Entreprise("MacDo", "0665340714", "b.didier745@laposte.net", "1 rue sauniere", null,
				"bouton taxe", "bouton contact", "anneeStage", "anneeTaxe", "montantTaxe");
		Entreprise e2 = new Entreprise("MacDo", "0665340714", "b.didier745@laposte.net", "1 rue sauniere",
				"bouton stage", "bouton taxe", "bouton contact", "anneeStage", "anneeTaxe", "montantTaxe");
		assertTrue("bouton stage normal", e2.getBoutonStage().equals("bouton stage"));

		if (e1.getBoutonStage() == null) {
			fail("bouton stage vide");
		}
	}

	public void testGetBoutonTaxe() {
		Entreprise e1 = new Entreprise("MacDo", "0665340714", "b.didier745@laposte.net", "1 rue sauniere",
				"bouton stage", null, "bouton contact", "anneeStage", "anneeTaxe", "montantTaxe");
		Entreprise e2 = new Entreprise("MacDo", "0665340714", "b.didier745@laposte.net", "1 rue sauniere",
				"bouton stage", "bouton taxe", "bouton contact", "anneeStage", "anneeTaxe", "montantTaxe");

		assertTrue(e2.getBoutonTaxe().equals("bouton contact"));

		if (e1.getBoutonTaxe() == null) {
			fail("bouton taxe vide");
		}
	}

	public void testGetBoutonContact() {
		Entreprise e1 = new Entreprise("MacDo", "0665340714", "b.didier745@laposte.net", "1 rue sauniere",
				"bouton stage", "bouton taxe", null, "anneeStage", "anneeTaxe", "montantTaxe");
		Entreprise e2 = new Entreprise("MacDo", "0665340714", "b.didier745@laposte.net", "1 rue sauniere",
				"bouton stage", "bouton taxe", "bouton contact", "anneeStage", "anneeTaxe", "montantTaxe");
		assertTrue("bouton contact normal", e2.getBoutonContact().equals("montantTaxe"));

		if (e1.getBoutonContact() == null)
			fail("bouton contact vide");
	}

}
