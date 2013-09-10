package fr.treeptik.jdbclocation.dao;

import fr.treeptik.jdbclocation.domain.Suplements;
import fr.treeptik.jdbclocation.exception.DAOException;

public interface SuplementsDAO {
	Suplements save(Suplements suplements) throws DAOException;

	void remove(Suplements suplements) throws DAOException;
}
