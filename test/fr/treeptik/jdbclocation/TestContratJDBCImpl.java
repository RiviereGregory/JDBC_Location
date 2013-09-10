package fr.treeptik.jdbclocation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import fr.treeptik.jdbclocation.dao.ClientDAO;
import fr.treeptik.jdbclocation.dao.ContratDAO;
import fr.treeptik.jdbclocation.dao.DAOFactory;
import fr.treeptik.jdbclocation.dao.TypeVoitureDAO;
import fr.treeptik.jdbclocation.dao.VoitureDAO;
import fr.treeptik.jdbclocation.domain.Client;
import fr.treeptik.jdbclocation.domain.Contrat;
import fr.treeptik.jdbclocation.domain.TypeVoiture;
import fr.treeptik.jdbclocation.domain.Voiture;
import fr.treeptik.jdbclocation.exception.DAOException;

@RunWith(JUnit4.class)
public class TestContratJDBCImpl {

	@Test(expected = NullPointerException.class)
	public void testSaveParamNull() {

		try {
			ContratDAO contratDAO = DAOFactory.getContratDAO();
			contratDAO.save(null);
		} catch (DAOException e) {
			Assert.fail(e.getMessage());
		}

	}

	@Test
	public void testSaveOK() {
		try {
			ContratDAO contratDAO = DAOFactory.getContratDAO();
			ClientDAO clientDAO = DAOFactory.getClientDAO();
			VoitureDAO voitureDAO = DAOFactory.getVoitureDAO();
			TypeVoitureDAO typeVoitureDAO = DAOFactory.getTypeVoitureDAO();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Contrat contrat = new Contrat();
			Client client = new Client();
			Voiture voiture = new Voiture();
			TypeVoiture typeVoiture = new TypeVoiture();

			client.setNomClient("Libra");
			client.setAdresse("rue vel");
			client.setVille("Marseille");
			client = clientDAO.save(client);
			contrat.setClient(client);

			Date date = null;
			date = dateFormat.parse("25/11/2012");
			contrat.setDateContrat(date);
			date = dateFormat.parse("27/11/2012");
			contrat.setDateEnlevement(date);
			date = dateFormat.parse("30/11/2012");
			contrat.setDateRetour(date);

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
			contrat.setVoiture(voiture);

			contrat = contratDAO.save(contrat);

			Assert.assertNotNull(contrat);
			Assert.assertNotNull(contrat.getNoContrat());

		} catch (DAOException | ParseException e) {
			Assert.fail(e.getMessage());
		}

	}

	@Test
	public void testRemoveOK() {
		try {
			ContratDAO contratDAO = DAOFactory.getContratDAO();
			ClientDAO clientDAO = DAOFactory.getClientDAO();
			VoitureDAO voitureDAO = DAOFactory.getVoitureDAO();
			TypeVoitureDAO typeVoitureDAO = DAOFactory.getTypeVoitureDAO();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Contrat contrat = new Contrat();
			Client client = new Client();
			Voiture voiture = new Voiture();
			TypeVoiture typeVoiture = new TypeVoiture();

			client.setNomClient("Libra");
			client.setAdresse("rue vel");
			client.setVille("Marseille");
			client = clientDAO.save(client);
			contrat.setClient(client);

			Date date = null;
			date = dateFormat.parse("25/11/2012");
			contrat.setDateContrat(date);
			date = dateFormat.parse("27/11/2012");
			contrat.setDateEnlevement(date);
			date = dateFormat.parse("30/11/2012");
			contrat.setDateRetour(date);

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
			contrat.setVoiture(voiture);

			contrat = contratDAO.save(contrat);

			contratDAO.remove(contrat);

		} catch (DAOException | ParseException e) {
			Assert.fail(e.getMessage());
		}

		Assert.assertTrue(true);
	}

	@Test
	public void testFindByClientOK() {
		try {
			ContratDAO contratDAO = DAOFactory.getContratDAO();
			ClientDAO clientDAO = DAOFactory.getClientDAO();
			VoitureDAO voitureDAO = DAOFactory.getVoitureDAO();
			TypeVoitureDAO typeVoitureDAO = DAOFactory.getTypeVoitureDAO();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Contrat contrat = new Contrat();
			Client client = new Client();
			Voiture voiture = new Voiture();
			TypeVoiture typeVoiture = new TypeVoiture();

			client.setNomClient("Libra");
			client.setAdresse("rue vel");
			client.setVille("Marseille");
			client = clientDAO.save(client);
			contrat.setClient(client);

			Date date = null;
			date = dateFormat.parse("25/11/2012");
			contrat.setDateContrat(date);
			date = dateFormat.parse("27/11/2012");
			contrat.setDateEnlevement(date);
			date = dateFormat.parse("30/11/2012");
			contrat.setDateRetour(date);

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
			contrat.setVoiture(voiture);

			contrat = contratDAO.save(contrat);

			List<Contrat> findByClient = contratDAO.findByClient(client);

			Assert.assertNotNull(findByClient);
			Assert.assertTrue(findByClient.size() > 0);

		} catch (DAOException | ParseException e) {
			Assert.fail(e.getMessage());
		}

	}

	@Test
	public void testFindAllByDateContratOK() {
		try {
			ContratDAO contratDAO = DAOFactory.getContratDAO();

			List<Contrat> findAllByDateContrat = contratDAO.findAllByDateContrat();

			Assert.assertNotNull(findAllByDateContrat);
			Assert.assertTrue(findAllByDateContrat.size() > 0);

		} catch (DAOException e) {
			Assert.fail(e.getMessage());
		}

	}

	@Test
	public void testFindPrixContrat() {
		try {
			ContratDAO contratDAO = DAOFactory.getContratDAO();
			
			Integer montantContrat = contratDAO.findPrixContrat(contratDAO.findContrat(1));

			Assert.assertNotNull(montantContrat);
			Assert.assertTrue(montantContrat > 0);

		} catch (DAOException e) {
			Assert.fail(e.getMessage());
		}

	}

	@Test
	public void testFindContrat() {
		try {
			ContratDAO contratDAO = DAOFactory.getContratDAO();

			Contrat findContrat = contratDAO.findContrat(1);

			Assert.assertNotNull(findContrat);
			Assert.assertTrue(findContrat.getNoContrat() == 1);

		} catch (DAOException e) {
			Assert.fail(e.getMessage());
		}
	}

}
