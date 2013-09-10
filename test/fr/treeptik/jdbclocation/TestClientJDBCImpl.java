package fr.treeptik.jdbclocation;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import fr.treeptik.jdbclocation.dao.ClientDAO;
import fr.treeptik.jdbclocation.dao.DAOFactory;
import fr.treeptik.jdbclocation.domain.Client;
import fr.treeptik.jdbclocation.exception.DAOException;

@RunWith(JUnit4.class)
public class TestClientJDBCImpl {

	@Test(expected = NullPointerException.class)
	public void testSaveParamNull() {

		try {
			ClientDAO clientDAO = DAOFactory.getClientDAO();
			clientDAO.save(null);
		} catch (DAOException e) {
			Assert.fail(e.getMessage());
		}

	}

	@Test
	public void testSaveOK() {
		try {
			ClientDAO clientDAO = DAOFactory.getClientDAO();
			Client client = new Client();
			client.setAdresse("rue sushi");
			client.setNomClient("Bernard");
			client.setVille("Gardanne");
			client = clientDAO.save(client);

			Assert.assertNotNull(client);
			Assert.assertNotNull(client.getCodeClient());

		} catch (DAOException e) {
			Assert.fail(e.getMessage());
		}

	}

	@Test
	public void testRemoveOK() {
		try {
			ClientDAO clientDAO = DAOFactory.getClientDAO();
			Client client = new Client();
			client.setAdresse("rue sushi");
			client.setNomClient("Bernard");
			client.setVille("Gardanne");
			client = clientDAO.save(client);

			clientDAO.remove(client);

		} catch (DAOException e) {
			Assert.fail(e.getMessage());
		}

		Assert.assertTrue(true);

	}

	@Test(expected = NullPointerException.class)
	public void testRemoveNull() {
		try {
			ClientDAO clientDAO = DAOFactory.getClientDAO();
			clientDAO.remove(null);

		} catch (DAOException e) {
			Assert.fail(e.getMessage());
		}

	}

	@Test
	public void testFindAllByName() {
		ClientDAO clientDAO = DAOFactory.getClientDAO();
		try {
			List<Client> findAllByName = clientDAO.findAllByName();
			Assert.assertNotNull(findAllByName);
			Assert.assertTrue(findAllByName.size() > 0);

		} catch (DAOException e) {
			Assert.fail(e.getMessage());
		}

	}

	@Test
	public void testFindClient() {
		ClientDAO clientDAO = DAOFactory.getClientDAO();
		try {
			Client findClient = clientDAO.findClient(1);
			Assert.assertNotNull(findClient);
			Assert.assertTrue(findClient.getCodeClient() == 1);

		} catch (DAOException e) {
			Assert.fail(e.getMessage());
		}
	}

}
