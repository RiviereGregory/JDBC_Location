package fr.treeptik.jdbclocation;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import fr.treeptik.jdbclocation.dao.DAOFactory;
import fr.treeptik.jdbclocation.dao.SuplementsDAO;
import fr.treeptik.jdbclocation.domain.Suplements;
import fr.treeptik.jdbclocation.exception.DAOException;

@RunWith(JUnit4.class)
public class TestSuplementsJDBCImpl {

	@Test(expected = NullPointerException.class)
	public void TestSaveParamNull() {
		SuplementsDAO suplementsDAO = DAOFactory.getSuplementsDAO();
		try {
			suplementsDAO.save(null);
		} catch (DAOException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void TestSaveOK() {
		SuplementsDAO suplementsDAO = DAOFactory.getSuplementsDAO();
		Suplements suplements = new Suplements();

		try {
			suplements.setLibelleSupplement("supplement essaie");
			suplements.setTarifJour(15);
			suplements = suplementsDAO.save(suplements);

			Assert.assertNotNull(suplements);
			Assert.assertNotNull(suplements.getCodeSuplement());

		} catch (DAOException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void TestRemoveOK() {
		SuplementsDAO suplementsDAO = DAOFactory.getSuplementsDAO();
		Suplements suplements = new Suplements();

		try {
			suplements.setLibelleSupplement("supplement essaie");
			suplements.setTarifJour(15);
			suplements = suplementsDAO.save(suplements);

			suplementsDAO.remove(suplements);

		} catch (DAOException e) {
			Assert.fail(e.getMessage());
		}
		Assert.assertTrue(true);
	}

}
