package fr.treeptik.jdbclocation.dao;

import java.util.List;

import fr.treeptik.jdbclocation.domain.Reparations;
import fr.treeptik.jdbclocation.domain.TypeVoiture;
import fr.treeptik.jdbclocation.domain.Voiture;
import fr.treeptik.jdbclocation.exception.DAOException;

public interface ReparationsDAO {
	Reparations save(Reparations reparations) throws DAOException;

	void remove(Reparations reparations) throws DAOException;

	List<Reparations> findByVoiture(Voiture voiture) throws DAOException;

	Reparations findMax() throws DAOException;

	Integer findNumberReparation(Voiture voiture) throws DAOException;

	Integer findNumberReparation(TypeVoiture typeVoiture) throws DAOException;
}
