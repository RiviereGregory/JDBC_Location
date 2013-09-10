package fr.treeptik.jdbclocation.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.treeptik.jdbclocation.dao.VoitureDAO;
import fr.treeptik.jdbclocation.domain.TypeVoiture;
import fr.treeptik.jdbclocation.domain.Voiture;
import fr.treeptik.jdbclocation.exception.DAOException;
import fr.treeptik.jdbclocation.utils.JDBCUtil;

public class VoitureJDBCImpl implements VoitureDAO {

	@Override
	public Voiture save(Voiture voiture) throws DAOException {
		Connection connection;
		try {
			connection = JDBCUtil.getConnection();
			PreparedStatement prepareStatement = connection
					.prepareStatement(
							"INSERT INTO Voiture (marque,modele,couleur,cumul_reparation ,disponible,code_type) VALUES (?,?,?,?,?,?)",
							Statement.RETURN_GENERATED_KEYS);
			prepareStatement.setString(1, voiture.getMarque());
			prepareStatement.setString(2, voiture.getModele());
			prepareStatement.setString(3, voiture.getCouleur());
			prepareStatement.setInt(4, voiture.getCumulReparation());
			prepareStatement.setBoolean(5, voiture.getDisponible());
			prepareStatement.setInt(6, voiture.getTypeVoiture().getCodeType());

			prepareStatement.executeUpdate();

			ResultSet keys = prepareStatement.getGeneratedKeys();
			keys.next();
			voiture.setNoImmatriculation(keys.getInt(1));
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
				| SQLException e) {
			throw new DAOException(e.getMessage(), e.getCause());
		}
		return voiture;
	}

	@Override
	public void remove(Voiture voiture) throws DAOException {
		Connection connection;
		try {
			connection = JDBCUtil.getConnection();
			PreparedStatement prepareStatement = connection
					.prepareStatement("DELETE FROM Voiture WHERE noimmatriculation=?");
			prepareStatement.setInt(1, voiture.getNoImmatriculation());
			prepareStatement.executeUpdate();

		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
				| SQLException e) {
			throw new DAOException(e.getMessage(), e.getCause());
		}

	}

	@Override
	public List<Voiture> findAllDisponible() throws DAOException {
		Connection connection;
		List<Voiture> voitures = new ArrayList<>();

		try {
			connection = JDBCUtil.getConnection();
			PreparedStatement prepareStatement = connection
					.prepareStatement("SELECT * FROM Voiture v INNER JOIN Type_voiture tv ON tv.code_type = v.code_type WHERE v.disponible=?");
			prepareStatement.setBoolean(1, true);
			ResultSet resultSet = prepareStatement.executeQuery();

			while (resultSet.next()) {
				Voiture voiture = new Voiture();
				voiture.setNoImmatriculation(resultSet.getInt("noimmatriculation"));
				voiture.setMarque(resultSet.getString("marque"));
				voiture.setModele(resultSet.getString("modele"));
				voiture.setCouleur(resultSet.getString("couleur"));
				voiture.setCumulReparation(resultSet.getInt("cumul_reparation"));
				voiture.setDisponible(resultSet.getBoolean("disponible"));

				TypeVoiture typeVoiture = new TypeVoiture();
				typeVoiture.setCodeType(resultSet.getInt("code_type"));
				typeVoiture.setDescriptionType(resultSet.getString("description_type"));
				typeVoiture.setPlaces(resultSet.getInt("places"));
				typeVoiture.setPrixJour(resultSet.getInt("prix_jour"));

				voiture.setTypeVoiture(typeVoiture);

				voitures.add(voiture);
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
				| SQLException e) {
			throw new DAOException(e.getMessage(), e.getCause());
		}

		return voitures;
	}

	@Override
	public Integer findNumberVoiture(TypeVoiture typeVoiture) throws DAOException {
		Connection connection;
		Integer nombreReparation;
		try {
			connection = JDBCUtil.getConnection();
			PreparedStatement prepareStatement = connection
					.prepareStatement("SELECT COUNT(v.code_type) FROM Voiture v INNER JOIN Type_voiture tv ON tv.code_type = v.code_type WHERE v.code_type=?");
			prepareStatement.setInt(1, typeVoiture.getCodeType());
			ResultSet resultSet = prepareStatement.executeQuery();
			resultSet.next();
			nombreReparation = resultSet.getInt("COUNT(v.code_type)");
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
				| SQLException e) {
			throw new DAOException(e.getMessage(), e.getCause());
		}

		return nombreReparation;
	}

}
