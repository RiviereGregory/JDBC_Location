package fr.treeptik.jdbclocation.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.treeptik.jdbclocation.dao.TypeVoitureDAO;
import fr.treeptik.jdbclocation.domain.TypeVoiture;
import fr.treeptik.jdbclocation.exception.DAOException;
import fr.treeptik.jdbclocation.utils.JDBCUtil;

public class TypeVoitureJDBCImpl implements TypeVoitureDAO {

	@Override
	public TypeVoiture save(TypeVoiture typeVoiture) throws DAOException {
		Connection connection;
		try {
			connection = JDBCUtil.getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement(
					"INSERT INTO Type_voiture (description_type,places,prix_jour) VALUES (?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			prepareStatement.setString(1, typeVoiture.getDescriptionType());
			prepareStatement.setInt(2, typeVoiture.getPlaces());
			prepareStatement.setInt(3, typeVoiture.getPrixJour());

			prepareStatement.executeUpdate();

			ResultSet keys = prepareStatement.getGeneratedKeys();
			keys.next();
			typeVoiture.setCodeType(keys.getInt(1));
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
				| SQLException e) {
			throw new DAOException(e.getMessage(), e.getCause());
		}

		return typeVoiture;
	}

	@Override
	public void remove(TypeVoiture typeVoiture) throws DAOException {
		Connection connection;
		try {
			connection = JDBCUtil.getConnection();
			PreparedStatement prepareStatement = connection
					.prepareStatement("DELETE FROM Type_voiture WHERE code_type=?");
			prepareStatement.setInt(1, typeVoiture.getCodeType());
			prepareStatement.executeUpdate();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
				| SQLException e) {
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}

}
