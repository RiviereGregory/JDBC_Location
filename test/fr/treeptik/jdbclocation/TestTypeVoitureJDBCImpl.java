package fr.treeptik.jdbclocation;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import fr.treeptik.jdbclocation.dao.DAOFactory;
import fr.treeptik.jdbclocation.dao.TypeVoitureDAO;
import fr.treeptik.jdbclocation.domain.TypeVoiture;
import fr.treeptik.jdbclocation.exception.DAOException;

@RunWith(JUnit4.class)
public class TestTypeVoitureJDBCImpl {
	@Test(expected = NullPointerException.class)
	public void TestSaveParamNull() {
		TypeVoitureDAO typeVoitureDAO = DAOFactory.getTypeVoitureDAO();
		try {
			typeVoitureDAO.save(null);
		} catch (DAOException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void TestSaveOK() {
		TypeVoitureDAO typeVoitureDAO = DAOFactory.getTypeVoitureDAO();
		TypeVoiture typeVoiture = new TypeVoiture();
		try {
			typeVoiture.setDescriptionType("4x4 luxe");
			typeVoiture.setPlaces(5);
			typeVoiture.setPrixJour(150);

			typeVoiture = typeVoitureDAO.save(typeVoiture);

			Assert.assertNotNull(typeVoiture);
			Assert.assertNotNull(typeVoiture.getCodeType());

		} catch (DAOException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void TestRemoveOK() {
		TypeVoitureDAO typeVoitureDAO = DAOFactory.getTypeVoitureDAO();
		TypeVoiture typeVoiture = new TypeVoiture();
		try {
			typeVoiture.setDescriptionType("4x4 luxe");
			typeVoiture.setPlaces(5);
			typeVoiture.setPrixJour(150);

			typeVoiture = typeVoitureDAO.save(typeVoiture);

			typeVoitureDAO.remove(typeVoiture);

		} catch (DAOException e) {
			Assert.fail(e.getMessage());
		}
		Assert.assertTrue(true);
	}

}
