package fr.treeptik.jdbclocation.dao;

import java.util.List;

import fr.treeptik.jdbclocation.domain.Client;
import fr.treeptik.jdbclocation.domain.Contrat;
import fr.treeptik.jdbclocation.exception.DAOException;

public interface ContratDAO {
	Contrat save(Contrat contrat) throws DAOException;

	void remove(Contrat contrat) throws DAOException;

	List<Contrat> findAllByDateContrat() throws DAOException;

	List<Contrat> findByClient(Client client) throws DAOException;

	Integer findPrixContrat(Client client) throws DAOException;

	Integer findPrixContrat(Contrat contrat) throws DAOException;

	Contrat findContrat(Integer noContrat) throws DAOException;

	List<Contrat> findContratMemeDate(Integer noImmatriculation) throws DAOException;

}
