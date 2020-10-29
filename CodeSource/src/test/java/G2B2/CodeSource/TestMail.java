package G2B2.CodeSource;

import G2B2.CodeSource.Email.Mail;
import junit.framework.TestCase;

public class TestMail extends TestCase {

	public void testMail() {
		Mail m2 = new Mail("b.didier745", "bn-31", "Object", "18/10/2017", "bla");
		assertNotNull("non instancie", m2);
	}
	
	@SuppressWarnings("unused")
	public void testGetObjet() {
		Mail m1 = new Mail("b.didier745", "bn-31", null, "18/10/2017", "bla");
		Mail m2 = new Mail("b.didier745", "bn-31", "Object", "18/10/2017", "bla");

		if (m1.getObjet() == null) {
			fail("objet nul");
		}
		assertTrue("objet normal", m2.getObjet().equals("Object"));

	}

	public void testGetTexte() {
		Mail m1 = new Mail("b.didier745", "bn-31", "Object", "18/10/2017", null);
		Mail m2 = new Mail("b.didier745", "bn-31", "Object", "187/120/2017", "bla");
		assertTrue("texte valide", m2.getTexte().equals("bla"));

		if (m1.getTexte() == null) {
			fail("texte vide");
		}
	}
}
