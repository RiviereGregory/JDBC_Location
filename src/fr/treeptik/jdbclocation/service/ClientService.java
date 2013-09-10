package fr.treeptik.jdbclocation.service;

import fr.treeptik.jdbclocation.domain.Client;
import fr.treeptik.jdbclocation.exception.ServiceException;

public interface ClientService {

	Client save(Client client) throws ServiceException;

}
