package fr.treeptik.jdbclocation.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.treeptik.jdbclocation.dao.ReparationsDAO;
import fr.treeptik.jdbclocation.domain.Reparations;
import fr.treeptik.jdbclocation.domain.TypeVoiture;
import fr.treeptik.jdbclocation.domain.Voiture;
import fr.treeptik.jdbclocation.exception.DAOException;
import fr.treeptik.jdbclocation.utils.JDBCUtil;

public class ReparationsJDBCImpl implements ReparationsDAO {

	@Override
	public Reparations save(Reparations reparations) throws DAOException {
		Connection connection;
		try {
			connection = JDBCUtil.getConnection();
			PreparedStatement prepareStatement = connection
					.prepareStatement(
							"INSERT INTO Reparations (date_rep, description_panne, montant_repa, noimmatriculation) VALUES (?,?,?,?)",
							Statement.RETURN_GENERATED_KEYS);
			prepareStatement.setDate(1,
					new java.sql.Date(reparations.getDateReparation().getTime()));
			prepareStatement.setString(2, reparations.getDescriptionPanne());
			prepareStatement.setInt(3, reparations.getMontantReparation());
			prepareStatement.setInt(4, reparations.getVoiture().getNoImmatriculation());

			prepareStatement.executeUpdate();

			ResultSet keys = prepareStatement.getGeneratedKeys();
			keys.next();
			reparations.setNumeroRep(keys.getInt(1));
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
				| SQLException e) {
			throw new DAOException(e.getMessage(), e.getCause());
		}

		return reparations;
	}

	@Override
	public void remove(Reparations reparations) throws DAOException {
		Connection connection;
		try {
			connection = JDBCUtil.getConnection();
			PreparedStatement prepareStatement = connection
					.prepareStatement("DELETE FROM Reparations WHERE num_rep=?");
			prepareStatement.setInt(1, reparations.getNumeroRep());
			prepareStatement.executeUpdate();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
				| SQLException e) {
			throw new DAOException(e.getMessage(), e.getCause());
		}

	}

	@Override
	public List<Reparations> findByVoiture(Voiture voiture) throws DAOException {
		Connection connection;
		List<Reparations> reparations = new ArrayList<>();
		try {
			connection = JDBCUtil.getConnection();
			PreparedStatement prepareStatement = connection
					.prepareStatement("SELECT * FROM Reparations r INNER JOIN Voiture v ON v.noimmatriculation = r.noimmatriculation WHERE v.noimmatriculation=?");
			prepareStatement.setInt(1, voiture.getNoImmatriculation());
			ResultSet resultSet = prepareStatement.executeQuery();

			while (resultSet.next()) {
				Reparations reparation = new Reparations();
				reparation.setDateReparation(new Date(resultSet.getDate("date_rep").getTime()));
				reparation.setDescriptionPanne(resultSet.getString("description_panne"));
				reparation.setMontantReparation(resultSet.getInt("montant_repa"));
				reparation.setVoiture(voiture);

				reparations.add(reparation);
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
				| SQLException e) {
			throw new DAOException(e.getMessage(), e.getCause());
		}

		return reparations;
	}

	@Override
	public Reparations findMax() throws DAOException {
		Connection connection;
		Reparations reparation;
		try {
			connection = JDBCUtil.getConnection();
			PreparedStatement prepareStatement = connection
					.prepareStatement("SELECT r.date_rep, r.description_panne, r.montant_repa, r.noimmatriculation, "
							+ " v.marque, v.modele, v.couleur, v.cumul_reparation, v.disponible, "
							+ " Max(r.montant_repa)FROM Reparations r INNER JOIN Voiture v ON v.noimmatriculation=r.noimmatriculation "
							+ " GROUP BY r.noimmatriculation HAVING MAX(r.montant_repa)=(SELECT MAX(montant_repa) FROM Reparations)");

			ResultSet resultSet = prepareStatement.executeQuery();
			resultSet.next();
			reparation = new Reparations();
			reparation.setDateReparation(new Date(resultSet.getDate("date_rep").getTime()));
			reparation.setDescriptionPanne(resultSet.getString("description_panne"));
			reparation.setMontantReparation(resultSet.getInt("montant_repa"));
			Voiture voiture = new Voiture();
			voiture.setNoImmatriculation(resultSet.getInt("noimmatriculation"));
			voiture.setMarque(resultSet.getString("marque"));
			voiture.setModele(resultSet.getString("modele"));
			voiture.setCouleur(resultSet.getString("couleur"));
			voiture.setCumulReparation(resultSet.getInt("cumul_reparation"));
			voiture.setDisponible(resultSet.getBoolean("disponible"));
			reparation.setVoiture(voiture);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
				| SQLException e) {
			throw new DAOException(e.getMessage(), e.getCause());
		}

		return reparation;
	}

	@Override
	public Integer findNumberReparation(Voiture voiture) throws DAOException {
		Connection connection;
		ResultSet resultSet;
		Integer nombreReparation;
		try {
			connection = JDBCUtil.getConnection();
			PreparedStatement prepareStatement = connection
					.prepareStatement("SELECT COUNT(r.noimmatriculation) FROM Reparations r INNER JOIN Voiture v "
							+ " ON v.noimmatriculation = r.noimmatriculation WHERE r.noimmatriculation=?");
			prepareStatement.setInt(1, voiture.getNoImmatriculation());
			resultSet = prepareStatement.executeQuery();
			resultSet.next();
			nombreReparation = resultSet.getInt("COUNT(r.noimmatriculation)");
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
				| SQLException e) {
			throw new DAOException(e.getMessage(), e.getCause());
		}

		return nombreReparation;
	}

	@Override
	public Integer findNumberReparation(TypeVoiture typeVoiture) throws DAOException {
		Connection connection;
		ResultSet resultSet;
		Integer nombreReparation;
		try {
			connection = JDBCUtil.getConnection();
			PreparedStatement prepareStatement = connection
					.prepareStatement("SELECT COUNT(tv.code_type) FROM Reparations r INNER JOIN Voiture v "
							+ " ON v.noimmatriculation = r.noimmatriculation INNER JOIN Type_voiture tv ON v.code_type=tv.code_type WHERE tv.code_type=?");
			prepareStatement.setInt(1, typeVoiture.getCodeType());
			resultSet = prepareStatement.executeQuery();
			resultSet.next();
			nombreReparation = resultSet.getInt("COUNT(tv.code_type)");
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
				| SQLException e) {
			throw new DAOException(e.getMessage(), e.getCause());
		}

		return nombreReparation;
	}
}
