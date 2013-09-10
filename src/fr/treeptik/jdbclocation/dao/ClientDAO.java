package fr.treeptik.jdbclocation.dao;

import java.util.List;

import fr.treeptik.jdbclocation.domain.Client;
import fr.treeptik.jdbclocation.exception.DAOException;

public interface ClientDAO {
	Client save(Client client) throws DAOException;

	void remove(Client client) throws DAOException;

	List<Client> findAllByName() throws DAOException;

	Client findClient(Integer codeClient) throws DAOException;

}
