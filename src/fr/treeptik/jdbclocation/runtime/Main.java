package fr.treeptik.jdbclocation.runtime;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.List;

import fr.treeptik.jdbclocation.dao.ClientDAO;
import fr.treeptik.jdbclocation.dao.ContratDAO;
import fr.treeptik.jdbclocation.dao.DAOFactory;
import fr.treeptik.jdbclocation.dao.ReparationsDAO;
import fr.treeptik.jdbclocation.dao.VoitureDAO;
import fr.treeptik.jdbclocation.domain.Client;
import fr.treeptik.jdbclocation.domain.Contrat;
import fr.treeptik.jdbclocation.domain.Reparations;
import fr.treeptik.jdbclocation.domain.Voiture;
import fr.treeptik.jdbclocation.utils.JDBCUtil;

public class Main {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		// Avec les services on aura plus les connection et les commits
		
		// Création de la connection
		Connection connection = JDBCUtil.getConnection();

		// Liste Client
		ClientDAO clientDAO = DAOFactory.getClientDAO();
		List<Client> findAllByName = clientDAO.findAllByName();
		connection.commit();
		// Création client
		Client clientTest = new Client();
		System.out.println("/* Liste Clients");
		for (Client client : findAllByName) {
			System.out.println("code Client : " + client.getCodeClient());
			System.out.println("Nom : " + client.getNomClient());
			System.out.println("Adresse : " + client.getAdresse());
			System.out.println("Ville : " + client.getVille());
			System.out.println("");

			if (client.getCodeClient() == 1) {
				clientTest.setCodeClient(client.getCodeClient());
				clientTest.setAdresse(client.getAdresse());
				clientTest.setNomClient(client.getNomClient());
				clientTest.setVille(client.getVille());
			}
		}

		// // Créaton Voiture
		Voiture voiture = new Voiture();
		// Liste Contrat
		ContratDAO contratDAO = DAOFactory.getContratDAO();
		List<Contrat> findAllByDateContrat = contratDAO.findAllByDateContrat();
		connection.commit();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("/* Liste Contrats");
		for (Contrat contrat : findAllByDateContrat) {
			System.out.println("numéro contrat : " + contrat.getNoContrat());
			System.out.println("date contrat : " + dateFormat.format(contrat.getDateContrat()));
			System.out.println("date enlevement : "
					+ dateFormat.format(contrat.getDateEnlevement()));
			System.out.println("date retour : " + dateFormat.format(contrat.getDateRetour()));
			System.out.println("nom Client : " + contrat.getClient().getNomClient());
			System.out.println("marque voiture : " + contrat.getVoiture().getMarque());
			System.out.println("modele voiture : " + contrat.getVoiture().getModele());
			System.out.println("");

			// Remplissage voiture
			if (contrat.getVoiture().getNoImmatriculation() == 8) {
				voiture.setCouleur(contrat.getVoiture().getCouleur());
				voiture.setNoImmatriculation(contrat.getVoiture().getNoImmatriculation());
				voiture.setCumulReparation(contrat.getVoiture().getCumulReparation());
				voiture.setDisponible(contrat.getVoiture().getDisponible());
				voiture.setMarque(contrat.getVoiture().getMarque());
				voiture.setModele(contrat.getVoiture().getModele());
				voiture.setTypeVoiture(contrat.getVoiture().getTypeVoiture());
			}

		}

		List<Contrat> findByClient = contratDAO.findByClient(clientTest);
		connection.commit();
		System.out.println("/* Liste Contrats du client");
		for (Contrat contrat : findByClient) {
			System.out.println("numéro contrat : " + contrat.getNoContrat());
			System.out.println("date contrat : " + dateFormat.format(contrat.getDateContrat()));
			System.out.println("date enlevement : "
					+ dateFormat.format(contrat.getDateEnlevement()));
			System.out.println("date retour : " + dateFormat.format(contrat.getDateRetour()));
			System.out.println("nom Client : " + contrat.getClient().getNomClient());
			System.out.println("marque voiture : " + contrat.getVoiture().getMarque());
			System.out.println("modele voiture : " + contrat.getVoiture().getModele());
			System.out.println("");

		}

		// Création voiture dao
		VoitureDAO voitureDAO = DAOFactory.getVoitureDAO();
		List<Voiture> voitures = voitureDAO.findAllDisponible();
		connection.commit();
		System.out.println("/* Liste des voitures disponible");
		for (Voiture voiture1 : voitures) {
			System.out.println("Numéro Immatriculation : " + voiture1.getNoImmatriculation());
			System.out.println("Modele : " + voiture1.getModele());
			System.out.println("Marque : " + voiture1.getMarque());
			System.out.println("Couleur : " + voiture1.getCouleur());
			System.out.println("Cumul réparation : " + voiture1.getCumulReparation());
			System.out.println("Dispo : " + voiture1.getDisponible());
			System.out.println("Description : " + voiture1.getTypeVoiture().getDescriptionType());
			System.out.println("Nombre de places : " + voiture1.getTypeVoiture().getPlaces());
			System.out.println("Tarif jour : " + voiture1.getTypeVoiture().getPrixJour());
			System.out.println("");
		}

		// List Réparations
		ReparationsDAO reparationsDAO = DAOFactory.getReparationsDAO();
		List<Reparations> reparations = reparationsDAO.findByVoiture(voiture);
		connection.commit();
		System.out.println("/* Liste reparation pour voiture 8");
		for (Reparations reparation : reparations) {
			System.out.println("Date Reparation : "
					+ dateFormat.format(reparation.getDateReparation()));
			System.out.println("Description de la panne : " + reparation.getDescriptionPanne());
			System.out.println("Montant de la réparation : " + reparation.getMontantReparation());
			System.out.println("Numéro immatriculation : "
					+ reparation.getVoiture().getNoImmatriculation());
			System.out.println("Modele : " + reparation.getVoiture().getModele());
			System.out.println("");

		}

		Reparations reparationsMax = reparationsDAO.findMax();
		System.out.println("/* réparation max");
		System.out.println("Date Reparation : "
				+ dateFormat.format(reparationsMax.getDateReparation()));
		System.out.println("Description de la panne : " + reparationsMax.getDescriptionPanne());
		System.out.println("Montant de la réparation : " + reparationsMax.getMontantReparation());
		System.out.println("Numéro immatriculation : "
				+ reparationsMax.getVoiture().getNoImmatriculation());
		System.out.println("Modele : " + reparationsMax.getVoiture().getModele());
		System.out
				.println("Cumul réparation : " + reparationsMax.getVoiture().getCumulReparation());
		System.out.println("");

		System.out.println("Nombre de voiture pour le type 8: "
				+ voitureDAO.findNumberVoiture(voiture.getTypeVoiture()));
		System.out.println("");

		System.out.println("Nombre de réparation par la voiture 8 : "
				+ reparationsDAO.findNumberReparation(voiture));
		System.out.println("");

		System.out.println("Nombre de réparation par le type 8 : "
				+ reparationsDAO.findNumberReparation(voiture.getTypeVoiture()));
		System.out.println("");

		System.out.println("Prix contrat pour le client : "
				+ contratDAO.findPrixContrat(clientTest));
		System.out.println("");

		System.out.println("voiture ayant la même date de contrat que la voiture 1 : ");
		List<Contrat> list = contratDAO.findContratMemeDate(1);
		connection.commit();
		for (Contrat contrat1 : list) {
			System.out.println("Numéro Immatriculation : " +  contrat1.getVoiture().getNoImmatriculation());
			System.out.println("Modele : " + contrat1.getVoiture().getModele());
			System.out.println("Marque : " + contrat1.getVoiture().getMarque());
			System.out.println("Couleur : " + contrat1.getVoiture().getCouleur());
			System.out.println("numero contrat : " + contrat1.getNoContrat());
			System.out.println("date contrat : " + contrat1.getDateContrat());
			System.out.println("date enlevement : " + contrat1.getDateEnlevement());
			System.out.println("date retour : " + contrat1.getDateRetour());
			System.out.println("");
		}
		// // Ajout Voiture et type_voiture
		// Voiture voiture = new Voiture();
		// voiture.setCouleur("bleu");
		// voiture.setCumulReparation(0);
		// voiture.setDisponible(true);
		// voiture.setMarque("BMW");
		// voiture.setModele("X5");
		// TypeVoiture typeVoiture = new TypeVoiture();
		// typeVoiture.setDescriptionType("4x4 luxe");
		// typeVoiture.setPlaces(5);
		// typeVoiture.setPrixJour(150);
		// TypeVoitureDAO typeVoitureDAO = DAOFactory.getTypeVoitureDAO();
		// typeVoiture = typeVoitureDAO.save(typeVoiture);
		//
		// voiture.setTypeVoiture(typeVoiture);
		// voiture = voitureDAO.save(voiture);
		// connection.commit();
		// voitures = voitureDAO.findAllDisponible();
		// connection.commit();
		// System.out.println("/* Liste des voitures disponible");
		// for (Voiture voiture1 : voitures) {
		// System.out.println("Numéro Immatriculation : " + voiture1.getNoImmatriculation());
		// System.out.println("Modele : " + voiture1.getModele());
		// System.out.println("Marque : " + voiture1.getMarque());
		// System.out.println("Couleur : " + voiture1.getCouleur());
		// System.out.println("Cumul réparation : " + voiture1.getCumulReparation());
		// System.out.println("Dispo : " + voiture1.getDisponible());
		// System.out.println("Description : " + voiture1.getTypeVoiture().getDescriptionType());
		// System.out.println("Nombre de places : " + voiture1.getTypeVoiture().getPlaces());
		// System.out.println("Tarif jour : " + voiture1.getTypeVoiture().getPrixJour());
		// System.out.println("");
		// }
		//
		// voitureDAO.remove(voiture);
		// typeVoitureDAO.remove(typeVoiture);
		// connection.commit();
		//
		// // Ajout Client
		// Client client = new Client();
		// client.setNomClient("Libra");
		// client.setAdresse("rue vel");
		// client.setVille("Marseille");
		// client = clientDAO.save(client);
		// List<Client> findAllByNameAjout = clientDAO.findAllByName();
		// connection.commit();
		// System.out.println("Ajout Client");
		// System.out.println("/* Liste Clients");
		// for (Client client1 : findAllByNameAjout) {
		// System.out.println("code Client : " + client1.getCodeClient());
		// System.out.println("Nom : " + client1.getNomClient());
		// System.out.println("Adresse : " + client1.getAdresse());
		// System.out.println("Ville : " + client1.getVille());
		// System.out.println("");
		// }
		//
		//
		//
		// Contrat contrat = new Contrat();
		// contrat.setClient(client);
		// Date date = null;
		// date = dateFormat.parse("25/11/2012");
		// contrat.setDateContrat(date);
		// date = dateFormat.parse("27/11/2012");
		// contrat.setDateEnlevement(date);
		// date = dateFormat.parse("30/11/2012");
		// contrat.setDateRetour(date);
		// contrat.setVoiture(voiture);
		//
		// contrat = contratDAO.save(contrat);
		// connection.commit();
		//
		// List<Contrat> findAllByDateContratAjout = contratDAO.findAllByDateContrat();
		// connection.commit();
		// dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		// System.out.println("");
		// System.out.println("/* Liste Contrats");
		// for (Contrat contrat1 : findAllByDateContratAjout) {
		// System.out.println("numéro contrat : " + contrat1.getNoContrat());
		// System.out.println("date contrat : " + dateFormat.format(contrat1.getDateContrat()));
		// System.out.println("date enlevement : " +
		// dateFormat.format(contrat1.getDateEnlevement()));
		// System.out.println("date retour : " + dateFormat.format(contrat1.getDateRetour()));
		// System.out.println("nom Client : " + contrat1.getClient().getNomClient());
		// System.out.println("marque voiture : " + contrat1.getVoiture().getMarque());
		// System.out.println("modele voiture : " + contrat1.getVoiture().getModele());
		// System.out.println("");
		// }
		//
		// contratDAO.remove(contrat);
		// connection.commit();
		//
		// List<Contrat> findAllByDateContratSupp = contratDAO.findAllByDateContrat();
		// connection.commit();
		// System.out.println("");
		// System.out.println("/* Liste Contrats supprimer");
		// for (Contrat contrat1 : findAllByDateContratSupp) {
		// System.out.println("numéro contrat : " + contrat1.getNoContrat());
		// System.out.println("date contrat : " + dateFormat.format(contrat1.getDateContrat()));
		// System.out.println("date enlevement : " +
		// dateFormat.format(contrat1.getDateEnlevement()));
		// System.out.println("date retour : " + dateFormat.format(contrat1.getDateRetour()));
		// System.out.println("nom Client : " + contrat1.getClient().getNomClient());
		// System.out.println("marque voiture : " + contrat1.getVoiture().getMarque());
		// System.out.println("modele voiture : " + contrat1.getVoiture().getModele());
		// System.out.println("");
		// }
		//
		// //Suppression Client
		// clientDAO.remove(client);
		// connection.commit();
		// List<Client> findAllByNameSupp = clientDAO.findAllByName();
		// System.out.println("Supp Client");
		// System.out.println("/* Liste Clients");
		// for (Client client1 : findAllByNameSupp) {
		// System.out.println("code Client : " + client1.getCodeClient());
		// System.out.println("Nom : " + client1.getNomClient());
		// System.out.println("Adresse : " + client1.getAdresse());
		// System.out.println("Ville : " + client1.getVille());
		// System.out.println("");
		// }

	}

}
