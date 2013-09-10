package fr.treeptik.jdbclocation.dao;

import fr.treeptik.jdbclocation.domain.TypeVoiture;
import fr.treeptik.jdbclocation.exception.DAOException;

public interface TypeVoitureDAO {
	TypeVoiture save(TypeVoiture typeVoiture) throws DAOException;

	void remove(TypeVoiture typeVoiture) throws DAOException;

}
