package fr.treeptik.jdbclocation.service.impl;

import java.sql.SQLException;

import fr.treeptik.jdbclocation.dao.ClientDAO;
import fr.treeptik.jdbclocation.dao.DAOFactory;
import fr.treeptik.jdbclocation.domain.Client;
import fr.treeptik.jdbclocation.exception.DAOException;
import fr.treeptik.jdbclocation.exception.ServiceException;
import fr.treeptik.jdbclocation.service.ClientService;
import fr.treeptik.jdbclocation.utils.JDBCUtil;

public class ClientServiceImpl implements ClientService {

	private ClientDAO clientDAO = DAOFactory.getClientDAO();

	@Override
	public Client save(Client client) throws ServiceException {

		try {
			client = clientDAO.save(client);
			JDBCUtil.getConnection().commit();
		} catch (DAOException | InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}

		return client;
	}

}
