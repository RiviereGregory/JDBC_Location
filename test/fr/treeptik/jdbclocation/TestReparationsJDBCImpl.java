package fr.treeptik.jdbclocation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import fr.treeptik.jdbclocation.dao.DAOFactory;
import fr.treeptik.jdbclocation.dao.ReparationsDAO;
import fr.treeptik.jdbclocation.dao.TypeVoitureDAO;
import fr.treeptik.jdbclocation.dao.VoitureDAO;
import fr.treeptik.jdbclocation.domain.Reparations;
import fr.treeptik.jdbclocation.domain.TypeVoiture;
import fr.treeptik.jdbclocation.domain.Voiture;
import fr.treeptik.jdbclocation.exception.DAOException;

@RunWith(JUnit4.class)
public class TestReparationsJDBCImpl {

	@Test(expected = NullPointerException.class)
	public void TestSaveParamNull() {
		ReparationsDAO reparationsDAO = DAOFactory.getReparationsDAO();
		try {
			reparationsDAO.save(null);
		} catch (DAOException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void TestSaveOK() {
		ReparationsDAO reparationsDAO = DAOFactory.getReparationsDAO();
		VoitureDAO voitureDAO = DAOFactory.getVoitureDAO();
		TypeVoitureDAO typeVoitureDAO = DAOFactory.getTypeVoitureDAO();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Reparations reparations = new Reparations();
		Voiture voiture = new Voiture();
		TypeVoiture typeVoiture = new TypeVoiture();
		Date date = null;

		try {
			try {
				date = dateFormat.parse("25/11/2012");
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			reparations.setDateReparation(date);
			reparations.setDescriptionPanne("description");
			reparations.setMontantReparation(200);

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
			reparations.setVoiture(voiture);

			reparationsDAO.save(reparations);

			Assert.assertNotNull(reparations);
			Assert.assertNotNull(reparations.getNumeroRep());

		} catch (DAOException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void TestRemoveOK() {
		ReparationsDAO reparationsDAO = DAOFactory.getReparationsDAO();
		VoitureDAO voitureDAO = DAOFactory.getVoitureDAO();
		TypeVoitureDAO typeVoitureDAO = DAOFactory.getTypeVoitureDAO();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Reparations reparations = new Reparations();
		Voiture voiture = new Voiture();
		TypeVoiture typeVoiture = new TypeVoiture();
		Date date = null;

		try {
			try {
				date = dateFormat.parse("25/11/2012");
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			reparations.setDateReparation(date);
			reparations.setDescriptionPanne("description");
			reparations.setMontantReparation(200);

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
			reparations.setVoiture(voiture);

			reparationsDAO.save(reparations);

			reparationsDAO.remove(reparations);

		} catch (DAOException e) {
			Assert.fail(e.getMessage());
		}
		Assert.assertTrue(true);
	}

	@Test
	public void TestFindByVoitureOK() {
		ReparationsDAO reparationsDAO = DAOFactory.getReparationsDAO();
		VoitureDAO voitureDAO = DAOFactory.getVoitureDAO();
		TypeVoitureDAO typeVoitureDAO = DAOFactory.getTypeVoitureDAO();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Reparations reparations = new Reparations();
		Voiture voiture = new Voiture();
		TypeVoiture typeVoiture = new TypeVoiture();
		Date date = null;

		try {
			try {
				date = dateFormat.parse("25/11/2012");
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			reparations.setDateReparation(date);
			reparations.setDescriptionPanne("description");
			reparations.setMontantReparation(200);

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
			reparations.setVoiture(voiture);

			reparationsDAO.save(reparations);

			List<Reparations> list = reparationsDAO.findByVoiture(voiture);
			Assert.assertNotNull(list);
			Assert.assertTrue(list.size() > 0);

		} catch (DAOException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void TestFindMaxOK() {
		ReparationsDAO reparationsDAO = DAOFactory.getReparationsDAO();
		Reparations reparations = new Reparations();

		try {

			reparations = reparationsDAO.findMax();
			Assert.assertNotNull(reparations);
			Assert.assertTrue(reparations.getMontantReparation() > 0);

		} catch (DAOException e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void TestFindNumberReparationVoitureOK() {
		ReparationsDAO reparationsDAO = DAOFactory.getReparationsDAO();
		VoitureDAO voitureDAO = DAOFactory.getVoitureDAO();
		TypeVoitureDAO typeVoitureDAO = DAOFactory.getTypeVoitureDAO();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Reparations reparations = new Reparations();
		Voiture voiture = new Voiture();
		TypeVoiture typeVoiture = new TypeVoiture();
		Date date = null;

		try {
			try {
				date = dateFormat.parse("25/11/2012");
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			reparations.setDateReparation(date);
			reparations.setDescriptionPanne("description");
			reparations.setMontantReparation(200);

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
			reparations.setVoiture(voiture);

			reparationsDAO.save(reparations);

			Integer nombreRep = reparationsDAO.findNumberReparation(voiture);
			Assert.assertNotNull(nombreRep);
			Assert.assertTrue(nombreRep > 0);
			
			Long long1 = Long.valueOf(nombreRep);
			Long long2 = 1l;
			Assert.assertEquals(long2, long1);

		} catch (DAOException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void TestFindNumberReparationTypeVoitureOK() {
		ReparationsDAO reparationsDAO = DAOFactory.getReparationsDAO();
		VoitureDAO voitureDAO = DAOFactory.getVoitureDAO();
		TypeVoitureDAO typeVoitureDAO = DAOFactory.getTypeVoitureDAO();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Reparations reparations = new Reparations();
		Voiture voiture = new Voiture();
		TypeVoiture typeVoiture = new TypeVoiture();
		Date date = null;

		try {
			try {
				date = dateFormat.parse("25/11/2012");
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			reparations.setDateReparation(date);
			reparations.setDescriptionPanne("description");
			reparations.setMontantReparation(200);

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
			reparations.setVoiture(voiture);

			reparationsDAO.save(reparations);

			Integer nombreRep = reparationsDAO.findNumberReparation(typeVoiture);
			Assert.assertNotNull(nombreRep);
			Assert.assertTrue(nombreRep > 0);
			
			Long long1 = Long.valueOf(nombreRep);
			Long long2 = 1l;
			Assert.assertEquals(long2, long1);

		} catch (DAOException e) {
			Assert.fail(e.getMessage());
		}
	}


}
