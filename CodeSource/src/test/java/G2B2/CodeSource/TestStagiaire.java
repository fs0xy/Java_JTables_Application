package G2B2.CodeSource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import G2B2.CodeSource.Stagiaire.Stagiaire;
import junit.framework.TestCase;

public class TestStagiaire extends TestCase {

	public void testStagiaire() {
		
		Stagiaire s1=new Stagiaire("Bastien","Didier","25/02/1998","2017","Mc Do");
		assertNotNull("Stagiaire non instancie",s1);
		
	}

	public void testGetPrenom() {
		Stagiaire s1=new Stagiaire(null,"Didier","25/02/1998","2017","Mc Do");
		Stagiaire s2=new Stagiaire("Bastien","Didier","25/02/1998","2017","Mc Do");
		Stagiaire s3=new Stagiaire("Bast ien","Didier","25/02/1998","2017","Mc Do");
		if(s1.getPrenom()==null)
			fail("prenom stagiaire null");
		assertTrue("prenom normal",s2.getPrenom().equals("Bastien"));
		assertTrue("prenom avec espace",s3.getPrenom().equals("Bast ien"));
		
	}

	public void testGetNom() {
		Stagiaire s1=new Stagiaire("Bastien",null,"25/02/1998","2017","Mc Do");
		Stagiaire s2=new Stagiaire("Bastien","Didier","25/02/1998","2017","Mc Do");
		Stagiaire s3=new Stagiaire("Bastien","Did ier","25/02/1998","2017","Mc Do");
		assertTrue("nom normal",s2.getNom().equals("Didier"));
		assertTrue("nom avec espace",s3.getNom().equals("Did ier"));

		if(s1.getNom()==null)
			fail("nom stagiaire null");
			}

	@SuppressWarnings({ "unused", "deprecation" })
	public void testGetDate() {
		Stagiaire s1=new Stagiaire("Bastien","Didier",null,"2017","Mc Do");
		Stagiaire s2=new Stagiaire("Bastien","Didier","252/02/1998","2017","Mc Do");
		Stagiaire s3=new Stagiaire("Bastien","Didier","25/02/2105","2017","Mc Do");
		Stagiaire s4=new Stagiaire("Bastien","Didier","25/02/1510","2017","Mc Do");
		Stagiaire s5=new Stagiaire("Bastien","Didier","25/02/1998","2017","Mc Do");
		assertTrue("date normale",s5.getDate().equals("25/02/1998"));

		if(s1.getDate()==null) 
			fail("date nulle");
	}

	@SuppressWarnings({ "deprecation", "unused", "unchecked" })
	public void testGetAnneeStage() {
		Stagiaire s1=new Stagiaire("Bastien","Didier",null,"2017","Mc Do");
		Stagiaire s2=new Stagiaire("Bastien","Didier","252/02/1998","2017","Mc Do");
		Stagiaire s3=new Stagiaire("Bastien","Didier","25/02/2105","2017","Mc Do");
		Stagiaire s4=new Stagiaire("Bastien","Didier","25/02/1510","2017","Mc Do");
		Stagiaire s5=new Stagiaire("Bastien","Didier","25/02/1998","2017","Mc Do");
		Stagiaire s6=new Stagiaire("Bastien","Didier","27/02/1998","2017","Mc Do");

		assertTrue("date normale",s5.getAnneeStage().equals("2017"));

		if(s1.getAnneeStage()==null) 
			fail("date nulle");
	
		
	}

	public void testGetEntreprise() {
		Stagiaire s1=new Stagiaire("Bastien","Didier","25/02/1998","2017",null);
		Stagiaire s2=new Stagiaire("Bastien","Didier","25/02/1998","2017","McDo");
		
		if(s1.getEntreprise()==null)
			fail("entreprise stagiaire null");
			Stagiaire s3=new Stagiaire("Bastien","Didier","25/02/1998","2017","Mc Do");
		assertTrue("entreprise normale stagiaire",s2.getEntreprise().equals("McDo"));
		assertTrue("entreprise avec espace stagiaire",s3.getEntreprise().equals("Mc Do"));

	
	
	}
}
