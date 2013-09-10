package fr.treeptik.jdbclocation.dao;

import fr.treeptik.jdbclocation.dao.impl.ClientJDBCImpl;
import fr.treeptik.jdbclocation.dao.impl.ContratJDBCImpl;
import fr.treeptik.jdbclocation.dao.impl.ReparationsJDBCImpl;
import fr.treeptik.jdbclocation.dao.impl.SuplementsJDBCImpl;
import fr.treeptik.jdbclocation.dao.impl.TypeVoitureJDBCImpl;
import fr.treeptik.jdbclocation.dao.impl.VoitureJDBCImpl;

public class DAOFactory {

	// Singleton
	private static ClientDAO clientDAO;
	private static ContratDAO contratDAO;
	private static VoitureDAO voitureDAO;
	private static TypeVoitureDAO typeVoitureDAO;
	private static ReparationsDAO reparationsDAO;
	private static SuplementsDAO suplementsDAO;

	public static ClientDAO getClientDAO() {
		if (clientDAO == null) {
			clientDAO = new ClientJDBCImpl();
		}
		return clientDAO;
	}

	public static ContratDAO getContratDAO() {
		if (contratDAO == null) {
			contratDAO = new ContratJDBCImpl();
		}
		return contratDAO;
	}

	public static VoitureDAO getVoitureDAO() {
		if (voitureDAO == null) {
			voitureDAO = new VoitureJDBCImpl();
		}
		return voitureDAO;
	}

	public static TypeVoitureDAO getTypeVoitureDAO() {
		if (typeVoitureDAO == null) {
			typeVoitureDAO = new TypeVoitureJDBCImpl();
		}
		return typeVoitureDAO;
	}

	public static ReparationsDAO getReparationsDAO() {
		if (reparationsDAO == null) {
			reparationsDAO = new ReparationsJDBCImpl();
		}
		return reparationsDAO;
	}

	public static SuplementsDAO getSuplementsDAO() {
		if (suplementsDAO == null) {
			suplementsDAO = new SuplementsJDBCImpl();
		}
		return suplementsDAO;
	}

	// public static ClientDAO getClientDAO() {
	// return new ClientJDBCImpl();
	// }
	// public static ContratDAO getContratDAO() {
	// return new ContratJDBCImpl();
	// }
	//
	// public static VoitureDAO getVoitureDAO() {
	// return new VoitureJDBCImpl();
	// }
	//
	// public static TypeVoitureDAO getTypeVoitureDAO() {
	// return new TypeVoitureJDBCImpl();
	// }
	//
	// public static ReparationsDAO getReparationsDAO() {
	// return new ReparationsJDBCImpl();
	// }
	//
	// public static SuplementsDAO getSuplementsDAO() {
	// return new SuplementsJDBCImpl();
	// }
}
