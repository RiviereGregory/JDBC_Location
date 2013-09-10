package fr.treeptik.jdbclocation.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.treeptik.jdbclocation.dao.SuplementsDAO;
import fr.treeptik.jdbclocation.domain.Suplements;
import fr.treeptik.jdbclocation.exception.DAOException;
import fr.treeptik.jdbclocation.utils.JDBCUtil;

public class SuplementsJDBCImpl implements SuplementsDAO {

	@Override
	public Suplements save(Suplements suplements) throws DAOException {
		Connection connection;
		try {
			connection = JDBCUtil.getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement(
					"INSERT INTO Suplements (libelle_supp , tarif_jour) VALUES (?,?)",
					Statement.RETURN_GENERATED_KEYS);

			prepareStatement.setString(1, suplements.getLibelleSupplement());
			prepareStatement.setInt(2, suplements.getTarifJour());

			prepareStatement.executeUpdate();

			ResultSet keys = prepareStatement.getGeneratedKeys();
			keys.next();
			suplements.setCodeSuplement(keys.getInt(1));
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
				| SQLException e) {
			throw new DAOException(e.getMessage(), e.getCause());
		}

		return suplements;
	}

	@Override
	public void remove(Suplements suplements) throws DAOException {
		Connection connection;
		try {
			connection = JDBCUtil.getConnection();
			PreparedStatement prepareStatement = connection
					.prepareStatement("DELETE FROM Suplements WHERE codesuplement=?");
			prepareStatement.setInt(1, suplements.getCodeSuplement());
			prepareStatement.executeUpdate();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
				| SQLException e) {
			throw new DAOException(e.getMessage(), e.getCause());
		}

	}

}
