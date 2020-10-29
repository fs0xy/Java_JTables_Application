package G2B2.CodeSource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import G2B2.CodeSource.Versement.Versement;
import junit.framework.TestCase;

public class TestVersement extends TestCase {

	public void testVersement() {
		Versement v2=new Versement("18/10/2016","10","McDo");
		assertNotNull("non instancie Versment",v2);
		assertTrue("non instancier",v2!=null);
	}

	@SuppressWarnings({ "deprecation", "unused" })
	public void testGetAnneeTaxe() {
		Versement v1=new Versement(null,"10","McDo");
		Versement v2=new Versement("185/10/2016","10","McDo");
		Versement v3=new Versement("18/10/2316","10","McDo");
		Versement v4=new Versement("18/10/1560","10","McDo");
		Versement v5=new Versement("18/10/2016","10","McDo");
		assertTrue("date normale",v5.getAnneeTaxe().equals("18/10/2016"));

		if(v1.getAnneeTaxe()==null) 
			fail("date Versement nulle");
	
	
	}

	public void testGetMontantTaxe() {
		Versement v1=new Versement("18/10/2016",null,"McDo");
		Versement v2=new Versement("18/10/2016","-10","McDo");
		Versement v3=new Versement("18/10/2016","10","McDo");
		if(v1.getMontantTaxe()==null) {
			fail("montant versement null");
		}
		if(v2.getMontantTaxe().indexOf("-")!=-1) {
			fail("montant negatif versement");
		}
		assertTrue("montant versement normal",v3.getMontantTaxe().equals("10"));
		
	}

	public void testGetEntreprise() {
		Versement v1=new Versement("18/10/2016","10",null);
		Versement v2=new Versement("18/10/2016","-0","McDo");
	if(v1.getEntreprise()==null) {
		fail("nom entreprise null");
	}
	assertTrue("nom entreprise normal",v2.getEntreprise().equals("McDo"));
	}

}
