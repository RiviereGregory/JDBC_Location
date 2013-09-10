package fr.treeptik.jdbclocation.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.treeptik.jdbclocation.dao.ClientDAO;
import fr.treeptik.jdbclocation.domain.Client;
import fr.treeptik.jdbclocation.exception.DAOException;
import fr.treeptik.jdbclocation.utils.JDBCUtil;

public class ClientJDBCImpl implements ClientDAO {

	@Override
	public List<Client> findAllByName() throws DAOException {
		Connection connection;
		List<Client> clients = new ArrayList<>();

		try {
			connection = JDBCUtil.getConnection();
			PreparedStatement prepareStatement = connection
					.prepareStatement("SELECT * FROM Client c ORDER BY c.nomcl");
			ResultSet resultSet = prepareStatement.executeQuery();

			while (resultSet.next()) {
				Client client = new Client();
				client.setCodeClient(resultSet.getInt("codecl"));
				client.setNomClient(resultSet.getString("nomcl"));
				client.setAdresse(resultSet.getString("adresse"));
				client.setVille(resultSet.getString("ville"));
				clients.add(client);
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
				| SQLException e) {
			throw new DAOException(e.getMessage(), e.getCause());
		}
		return clients;
	}

	@Override
	public Client save(Client client) throws DAOException {

		Connection connection;
		try {
			connection = JDBCUtil.getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement(
					"INSERT INTO Client (nomcl,adresse,ville) VALUES (?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			prepareStatement.setString(1, client.getNomClient());
			prepareStatement.setString(2, client.getAdresse());
			prepareStatement.setString(3, client.getVille());
			prepareStatement.executeUpdate();
			ResultSet keys = prepareStatement.getGeneratedKeys();
			keys.next();
			client.setCodeClient(keys.getInt(1));
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
				| SQLException e) {

			throw new DAOException(e.getMessage(), e.getCause());
		}

		return client;
	}

	@Override
	public void remove(Client client) throws DAOException {

		Connection connection;
		try {
			connection = JDBCUtil.getConnection();
			PreparedStatement prepareStatement = connection
					.prepareStatement("DELETE FROM Client WHERE codecl=?");
			prepareStatement.setInt(1, client.getCodeClient());
			prepareStatement.executeUpdate();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
				| SQLException e) {
			throw new DAOException(e.getMessage(), e.getCause());
		}

	}

	@Override
	public Client findClient(Integer codeClient) throws DAOException {
		Connection connection;
		Client client = new Client();

		try {
			connection = JDBCUtil.getConnection();
			PreparedStatement prepareStatement = connection
					.prepareStatement("SELECT * FROM Client c WHERE c.codecl=?");
			prepareStatement.setInt(1, codeClient);
			ResultSet resultSet = prepareStatement.executeQuery();
			resultSet.next();

			client.setCodeClient(resultSet.getInt("codecl"));
			client.setNomClient(resultSet.getString("nomcl"));
			client.setAdresse(resultSet.getString("adresse"));
			client.setVille(resultSet.getString("ville"));

		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
				| SQLException e) {
			throw new DAOException(e.getMessage(), e.getCause());
		}
		return client;
	}

}
