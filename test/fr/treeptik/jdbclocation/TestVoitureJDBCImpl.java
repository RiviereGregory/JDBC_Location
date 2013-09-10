package fr.treeptik.jdbclocation;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import fr.treeptik.jdbclocation.dao.DAOFactory;
import fr.treeptik.jdbclocation.dao.TypeVoitureDAO;
import fr.treeptik.jdbclocation.dao.VoitureDAO;
import fr.treeptik.jdbclocation.domain.TypeVoiture;
import fr.treeptik.jdbclocation.domain.Voiture;
import fr.treeptik.jdbclocation.exception.DAOException;

@RunWith(JUnit4.class)
public class TestVoitureJDBCImpl {
	@Test(expected = NullPointerException.class)
	public void TestSaveParamNull() {
		VoitureDAO voitureDAO = DAOFactory.getVoitureDAO();

		try {
			voitureDAO.save(null);

		} catch (DAOException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void TestSaveOK() {
		VoitureDAO voitureDAO = DAOFactory.getVoitureDAO();
		TypeVoitureDAO typeVoitureDAO = DAOFactory.getTypeVoitureDAO();

		Voiture voiture = new Voiture();
		TypeVoiture typeVoiture = new TypeVoiture();

		try {

			voiture.setCouleur("bleu");
			voiture.setCumulReparation(0);
			voiture.setDisponible(true);
			voiture.setMarque("BMW");
			voiture.setModele("X5");

			typeVoiture.setDescriptionType("4x4 luxe");
			typeVoiture.setPlaces(5);
			typeVoiture.setPrixJour(150);

			typeVoiture = typeVoitureDAO.save(typeVoiture);
			voiture.setTypeVoiture(typeVoiture);
			voiture = voitureDAO.save(voiture);

			Assert.assertNotNull(voiture);
			Assert.assertNotNull(voiture.getNoImmatriculation());

		} catch (DAOException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void TestRemoveOK() {
		VoitureDAO voitureDAO = DAOFactory.getVoitureDAO();
		TypeVoitureDAO typeVoitureDAO = DAOFactory.getTypeVoitureDAO();

		Voiture voiture = new Voiture();
		TypeVoiture typeVoiture = new TypeVoiture();

		try {

			voiture.setCouleur("bleu");
			voiture.setCumulReparation(0);
			voiture.setDisponible(true);
			voiture.setMarque("BMW");
			voiture.setModele("X5");

			typeVoiture.setDescriptionType("4x4 luxe");
			typeVoiture.setPlaces(5);
			typeVoiture.setPrixJour(150);

			typeVoiture = typeVoitureDAO.save(typeVoiture);
			voiture.setTypeVoiture(typeVoiture);
			voiture = voitureDAO.save(voiture);

			voitureDAO.remove(voiture);

		} catch (DAOException e) {
			Assert.fail(e.getMessage());
		}

		Assert.assertTrue(true);
	}

	@Test
	public void TestFindAllDisponible() {
		VoitureDAO voitureDAO = DAOFactory.getVoitureDAO();

		try {

			List<Voiture> voitures = voitureDAO.findAllDisponible();

			Assert.assertNotNull(voitures);
			Assert.assertTrue(voitures.size() > 0);

		} catch (DAOException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void TestFindNumberVoiture() {
		VoitureDAO voitureDAO = DAOFactory.getVoitureDAO();
		TypeVoitureDAO typeVoitureDAO = DAOFactory.getTypeVoitureDAO();

		Voiture voiture = new Voiture();
		TypeVoiture typeVoiture = new TypeVoiture();

		try {

			voiture.setCouleur("bleu");
			voiture.setCumulReparation(0);
			voiture.setDisponible(true);
			voiture.setMarque("BMW");
			voiture.setModele("X5");

			typeVoiture.setDescriptionType("4x4 luxe");
			typeVoiture.setPlaces(5);
			typeVoiture.setPrixJour(150);

			typeVoiture = typeVoitureDAO.save(typeVoiture);
			voiture.setTypeVoiture(typeVoiture);
			voiture = voitureDAO.save(voiture);

			Integer numberVoiture = voitureDAO.findNumberVoiture(voiture.getTypeVoiture());

			Assert.assertNotNull(numberVoiture);
			Assert.assertEquals(1l, numberVoiture.longValue());

		} catch (DAOException e) {
			Assert.fail(e.getMessage());
		}

	}

}
