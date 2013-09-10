package fr.treeptik.jdbclocation.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Contrat implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer noContrat;
	private Date dateContrat;
	private Date dateEnlevement;
	private Date dateRetour;
	private Client client;
	private Voiture voiture;
	private List<Suplements> suplements;

	public Contrat() {
	}

	public Contrat(Integer noContrat, Date dateContrat, Date dateEnlevement, Date dateRetour,
			Client client, Voiture voiture, List<Suplements> suplements) {
		super();
		this.noContrat = noContrat;
		this.dateContrat = dateContrat;
		this.dateEnlevement = dateEnlevement;
		this.dateRetour = dateRetour;
		this.client = client;
		this.voiture = voiture;
		this.suplements = suplements;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((noContrat == null) ? 0 : noContrat.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contrat other = (Contrat) obj;
		if (noContrat == null) {
			if (other.noContrat != null)
				return false;
		} else if (!noContrat.equals(other.noContrat))
			return false;
		return true;
	}

	public Date getDateContrat() {
		return dateContrat;
	}

	public void setDateContrat(Date dateContrat) {
		this.dateContrat = dateContrat;
	}

	public Date getDateEnlevement() {
		return dateEnlevement;
	}

	public void setDateEnlevement(Date dateEnlevement) {
		this.dateEnlevement = dateEnlevement;
	}

	public Date getDateRetour() {
		return dateRetour;
	}

	public void setDateRetour(Date dateRetour) {
		this.dateRetour = dateRetour;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Voiture getVoiture() {
		return voiture;
	}

	public void setVoiture(Voiture voiture) {
		this.voiture = voiture;
	}

	public Integer getNoContrat() {
		return noContrat;
	}

	public void setNoContrat(Integer noContrat) {
		this.noContrat = noContrat;
	}

	public List<Suplements> getSuplements() {
		return suplements;
	}

	public void setSuplements(List<Suplements> suplements) {
		this.suplements = suplements;
	}

}
