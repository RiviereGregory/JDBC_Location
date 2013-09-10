package fr.treeptik.jdbclocation.service;

import fr.treeptik.jdbclocation.service.impl.ClientServiceImpl;

public class ServiceFactory {

	private static ClientService clientService = new ClientServiceImpl();

	public static ClientService getClientService() {

		return clientService;

	}

}
