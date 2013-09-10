package fr.treeptik.jdbclocation.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.treeptik.jdbclocation.dao.ContratDAO;
import fr.treeptik.jdbclocation.domain.Client;
import fr.treeptik.jdbclocation.domain.Contrat;
import fr.treeptik.jdbclocation.domain.TypeVoiture;
import fr.treeptik.jdbclocation.domain.Voiture;
import fr.treeptik.jdbclocation.exception.DAOException;
import fr.treeptik.jdbclocation.utils.JDBCUtil;

public class ContratJDBCImpl implements ContratDAO {

	@Override
	public List<Contrat> findAllByDateContrat() throws DAOException {

		Connection connection;
		List<Contrat> contrats = new ArrayList<>();
		try {
			connection = JDBCUtil.getConnection();
			PreparedStatement prepareStatement = connection
					.prepareStatement("SELECT * FROM Contrat co INNER JOIN Client c ON c.codecl=co.codecl "
							+ " INNER JOIN Voiture v ON v.noimmatriculation=co.noimmatriculation "
							+ " INNER JOIN Type_voiture tv ON tv.code_type = v.code_type ORDER BY co.date_contrat");
			ResultSet resultSet = prepareStatement.executeQuery();

			while (resultSet.next()) {
				Contrat contrat = new Contrat();
				contrat.setNoContrat(resultSet.getInt("nocontrat"));
				contrat.setDateContrat(new Date(resultSet.getDate("date_contrat").getTime()));
				contrat.setDateEnlevement(new Date(resultSet.getDate("date_enlevement").getTime()));
				contrat.setDateRetour(new Date(resultSet.getDate("date_retour").getTime()));

				Client client = new Client();
				client.setCodeClient(resultSet.getInt("codecl"));
				client.setNomClient(resultSet.getString("nomcl"));
				client.setAdresse(resultSet.getString("adresse"));
				client.setVille(resultSet.getString("ville"));
				contrat.setClient(client);

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
				contrat.setVoiture(voiture);

				contrats.add(contrat);
			}

		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
				| SQLException e) {
			throw new DAOException(e.getMessage(), e.getCause());
		}

		return contrats;
	}

	@Override
	public Contrat save(Contrat contrat) throws DAOException {

		Connection connection;
		try {
			connection = JDBCUtil.getConnection();
			PreparedStatement prepareStatement = connection
					.prepareStatement(
							"INSERT INTO Contrat (date_contrat,date_enlevement,date_retour,codecl ,noimmatriculation) VALUES (?,?,?,?,?)",
							Statement.RETURN_GENERATED_KEYS);
			prepareStatement.setDate(1, new java.sql.Date(contrat.getDateContrat().getTime()));
			prepareStatement.setDate(2, new java.sql.Date(contrat.getDateEnlevement().getTime()));
			prepareStatement.setDate(3, new java.sql.Date(contrat.getDateRetour().getTime()));
			prepareStatement.setInt(4, contrat.getClient().getCodeClient());
			prepareStatement.setInt(5, contrat.getVoiture().getNoImmatriculation());

			prepareStatement.executeUpdate();

			ResultSet keys = prepareStatement.getGeneratedKeys();
			keys.next();
			contrat.setNoContrat(keys.getInt(1));
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
				| SQLException e) {
			throw new DAOException(e.getMessage(), e.getCause());
		}

		return contrat;
	}

	@Override
	public void remove(Contrat contrat) throws DAOException {

		Connection connection;
		try {
			connection = JDBCUtil.getConnection();
			PreparedStatement prepareStatement = connection
					.prepareStatement("DELETE FROM Contrat WHERE nocontrat=?");
			prepareStatement.setInt(1, contrat.getNoContrat());
			prepareStatement.executeUpdate();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
				| SQLException e) {
			throw new DAOException(e.getMessage(), e.getCause());
		}

	}

	@Override
	public List<Contrat> findByClient(Client client) throws DAOException {

		Connection connection;
		List<Contrat> contrats = new ArrayList<>();
		try {
			connection = JDBCUtil.getConnection();
			PreparedStatement prepareStatement = connection
					.prepareStatement("SELECT * FROM Contrat co INNER JOIN Client c ON c.codecl=co.codecl "
							+ " INNER JOIN Voiture v ON v.noimmatriculation=co.noimmatriculation "
							+ " INNER JOIN Type_voiture tv ON tv.code_type = v.code_type WHERE c.codecl=?");
			prepareStatement.setInt(1, client.getCodeClient());
			ResultSet resultSet = prepareStatement.executeQuery();

			while (resultSet.next()) {
				Contrat contrat = new Contrat();
				contrat.setNoContrat(resultSet.getInt("nocontrat"));
				contrat.setDateContrat(new Date(resultSet.getDate("date_contrat").getTime()));
				contrat.setDateEnlevement(new Date(resultSet.getDate("date_enlevement").getTime()));
				contrat.setDateRetour(new Date(resultSet.getDate("date_retour").getTime()));

				Client client1 = new Client();
				client1.setCodeClient(resultSet.getInt("codecl"));
				client1.setNomClient(resultSet.getString("nomcl"));
				client1.setAdresse(resultSet.getString("adresse"));
				client1.setVille(resultSet.getString("ville"));
				contrat.setClient(client1);

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
				contrat.setVoiture(voiture);

				contrats.add(contrat);
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
				| SQLException e) {
			throw new DAOException(e.getMessage(), e.getCause());
		}

		return contrats;
	}

	@Override
	public Integer findPrixContrat(Client client) throws DAOException {

		Connection connection;
		Integer PrixContrat = 0;
		try {
			connection = JDBCUtil.getConnection();

			PreparedStatement prepareStatement = connection
					.prepareStatement("SELECT c.codecl, co.nocontrat,tv.prix_jour,co.date_retour - co.date_enlevement +1 AS nb_jour "
							+ " FROM Client c INNER JOIN Contrat co ON c.codecl=co.codecl INNER JOIN Voiture v ON v.noimmatriculation=co.noimmatriculation "
							+ " INNER JOIN Type_voiture tv ON tv.code_type=v.code_type GROUP BY co.nocontrat,tv.prix_jour HAVING c.codecl=?");
			prepareStatement.setInt(1, client.getCodeClient());
			ResultSet resultSet = prepareStatement.executeQuery();

			while (resultSet.next()) {
				Integer PrixJour = resultSet.getInt("tv.prix_jour");
				Integer nbJour = resultSet.getInt("nb_jour");
				PrixContrat += PrixJour * nbJour;
			}

			prepareStatement = connection
					.prepareStatement("SELECT c.codecl, co.nocontrat, s.tarif_jour,co.date_retour - co.date_enlevement +1 AS nb_jour "
							+ " FROM Client c INNER JOIN Contrat co ON c.codecl=co.codecl INNER JOIN Prevoir p ON p.nocontrat=co.nocontrat "
							+ " INNER JOIN Suplements s ON s.codesuplement=p.codesuplement GROUP BY co.nocontrat,s.tarif_jour HAVING c.codecl=?");

			prepareStatement.setInt(1, client.getCodeClient());
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				Integer PrixJour = resultSet.getInt("s.tarif_jour");
				Integer nbJour = resultSet.getInt("nb_jour");
				PrixContrat += PrixJour * nbJour;
			}

		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
				| SQLException e) {
			throw new DAOException(e.getMessage(), e.getCause());
		}

		return PrixContrat;
	}

	@Override
	public Integer findPrixContrat(Contrat contrat) throws DAOException {
		Connection connection;
		Integer PrixContrat = 0;
		try {
			connection = JDBCUtil.getConnection();

			PreparedStatement prepareStatement = connection
					.prepareStatement("SELECT c.codecl, co.nocontrat,tv.prix_jour,co.date_retour - co.date_enlevement +1 AS nb_jour "
							+ " FROM Client c INNER JOIN Contrat co ON c.codecl=co.codecl INNER JOIN Voiture v ON v.noimmatriculation=co.noimmatriculation "
							+ " INNER JOIN Type_voiture tv ON tv.code_type=v.code_type GROUP BY co.nocontrat,tv.prix_jour HAVING co.nocontrat=?");
			prepareStatement.setInt(1, contrat.getNoContrat());
			ResultSet resultSet = prepareStatement.executeQuery();

			while (resultSet.next()) {
				Integer PrixJour = resultSet.getInt("tv.prix_jour");
				Integer nbJour = resultSet.getInt("nb_jour");
				PrixContrat += PrixJour * nbJour;
			}

			prepareStatement = connection
					.prepareStatement("SELECT c.codecl, co.nocontrat, s.tarif_jour,co.date_retour - co.date_enlevement +1 AS nb_jour "
							+ " FROM Client c INNER JOIN Contrat co ON c.codecl=co.codecl INNER JOIN Prevoir p ON p.nocontrat=co.nocontrat "
							+ " INNER JOIN Suplements s ON s.codesuplement=p.codesuplement GROUP BY co.nocontrat,s.tarif_jour HAVING co.nocontrat=?");

			prepareStatement.setInt(1, contrat.getNoContrat());
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				Integer PrixJour = resultSet.getInt("s.tarif_jour");
				Integer nbJour = resultSet.getInt("nb_jour");
				PrixContrat += PrixJour * nbJour;
			}

		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
				| SQLException e) {
			throw new DAOException(e.getMessage(), e.getCause());
		}

		return PrixContrat;
	}

	@Override
	public Contrat findContrat(Integer noContrat) throws DAOException {
		Connection connection;
		Contrat contrat = new Contrat();

		try {
			connection = JDBCUtil.getConnection();
			PreparedStatement prepareStatement = connection
					.prepareStatement("SELECT * FROM Contrat co INNER JOIN Client c ON c.codecl=co.codecl "
							+ " INNER JOIN Voiture v ON v.noimmatriculation=co.noimmatriculation "
							+ " INNER JOIN Type_voiture tv ON tv.code_type = v.code_type WHERE co.nocontrat=?");
			prepareStatement.setInt(1, noContrat);
			ResultSet resultSet = prepareStatement.executeQuery();

			resultSet.next();

			contrat.setNoContrat(resultSet.getInt("nocontrat"));
			contrat.setDateContrat(new Date(resultSet.getDate("date_contrat").getTime()));
			contrat.setDateEnlevement(new Date(resultSet.getDate("date_enlevement").getTime()));
			contrat.setDateRetour(new Date(resultSet.getDate("date_retour").getTime()));

			Client client = new Client();
			client.setCodeClient(resultSet.getInt("codecl"));
			client.setNomClient(resultSet.getString("nomcl"));
			client.setAdresse(resultSet.getString("adresse"));
			client.setVille(resultSet.getString("ville"));
			contrat.setClient(client);

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
			contrat.setVoiture(voiture);

		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
				| SQLException e) {
			throw new DAOException(e.getMessage(), e.getCause());
		}
		return contrat;
	}

	@Override
	public List<Contrat> findContratMemeDate(Integer noImmatriculation) throws DAOException {

		List<Contrat> contrats = new ArrayList<>();
		Connection connection;

		try {
			connection = JDBCUtil.getConnection();

			PreparedStatement prepareStatement = connection
					.prepareStatement("SELECT co.nocontrat,co.date_retour, co.date_enlevement, co.date_contrat, co.noimmatriculation"
							+ " FROM Voiture v INNER JOIN Contrat co ON v.noimmatriculation=co.noimmatriculation "
							+ " GROUP BY co.nocontrat,co.noimmatriculation HAVING co.noimmatriculation=?");
			prepareStatement.setInt(1, noImmatriculation);
			ResultSet resultSet = prepareStatement.executeQuery();

			Date dateRetour = null;
			Date dateEnlevement = null;
			Date dateContrat = null;
			while (resultSet.next()) {
				dateRetour = new Date(resultSet.getDate("date_contrat").getTime());
				dateEnlevement = new Date(resultSet.getDate("co.date_enlevement").getTime());
				dateContrat = new Date(resultSet.getDate("co.date_enlevement").getTime());
			}

			prepareStatement = connection
					.prepareStatement("SELECT co.nocontrat,co.date_retour, co.date_enlevement, co.date_contrat,co.codecl,co.noimmatriculation"
							+ " FROM Voiture v INNER JOIN Contrat co ON v.noimmatriculation=co.noimmatriculation "
							+ " GROUP BY co.nocontrat");

			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {

				Date dateRetourT = new Date(resultSet.getDate("co.date_retour").getTime());
				Date dateEnlevementT = new Date(resultSet.getDate("co.date_enlevement").getTime());
				Date dateContratT = new Date(resultSet.getDate("co.date_enlevement").getTime());

				if (!noImmatriculation.equals(resultSet.getInt("co.noimmatriculation")) && (dateRetour.equals(dateRetourT) || dateEnlevement.equals(dateEnlevementT)
						|| dateContrat.equals(dateContratT))) {
					Contrat contrat = new Contrat();
					contrat = findContrat(resultSet.getInt("co.nocontrat"));
					contrats.add(contrat);
				}
			}

		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
				| SQLException e) {
			throw new DAOException(e.getMessage(), e.getCause());
		}

		return contrats;
	}
}
