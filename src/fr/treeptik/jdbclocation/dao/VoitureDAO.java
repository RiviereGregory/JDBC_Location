package fr.treeptik.jdbclocation.dao;

import java.util.List;

import fr.treeptik.jdbclocation.domain.TypeVoiture;
import fr.treeptik.jdbclocation.domain.Voiture;
import fr.treeptik.jdbclocation.exception.DAOException;

public interface VoitureDAO {

	Voiture save(Voiture voiture) throws DAOException;

	void remove(Voiture voiture) throws DAOException;

	List<Voiture> findAllDisponible() throws DAOException;

	Integer findNumberVoiture(TypeVoiture typeVoiture) throws DAOException;

}
