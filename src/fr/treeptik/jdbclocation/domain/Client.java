package fr.treeptik.jdbclocation.domain;

import java.io.Serializable;

public class Client implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer codeClient;
	private String nomClient;
	private String adresse;
	private String ville;

	public Client() {
	}

	public Client(Integer codeClient, String nomClient, String adresse, String ville) {
		super();
		this.codeClient = codeClient;
		this.nomClient = nomClient;
		this.adresse = adresse;
		this.ville = ville;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codeClient == null) ? 0 : codeClient.hashCode());
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
		Client other = (Client) obj;
		if (codeClient == null) {
			if (other.codeClient != null)
				return false;
		} else if (!codeClient.equals(other.codeClient))
			return false;
		return true;
	}

	public Integer getCodeClient() {
		return codeClient;
	}

	public void setCodeClient(Integer codeClient) {
		this.codeClient = codeClient;
	}

	public String getNomClient() {
		return nomClient;
	}

	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

}
