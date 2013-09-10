package fr.treeptik.jdbclocation.domain;

import java.io.Serializable;

public class Voiture implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer noImmatriculation;
	private String marque;
	private String modele;
	private String couleur;
	private Integer cumulReparation;
	private Boolean disponible;
	private TypeVoiture typeVoiture;

	public Voiture() {
	}

	public Voiture(Integer noImmatriculation, String marque, String modele, String couleur,
			Integer cumulReparation, Boolean disponible, TypeVoiture typeVoiture) {
		super();
		this.noImmatriculation = noImmatriculation;
		this.marque = marque;
		this.modele = modele;
		this.couleur = couleur;
		this.cumulReparation = cumulReparation;
		this.disponible = disponible;
		this.typeVoiture = typeVoiture;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((noImmatriculation == null) ? 0 : noImmatriculation.hashCode());
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
		Voiture other = (Voiture) obj;
		if (noImmatriculation == null) {
			if (other.noImmatriculation != null)
				return false;
		} else if (!noImmatriculation.equals(other.noImmatriculation))
			return false;
		return true;
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public String getModele() {
		return modele;
	}

	public void setModele(String modele) {
		this.modele = modele;
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	public Integer getCumulReparation() {
		return cumulReparation;
	}

	public void setCumulReparation(Integer cumulReparation) {
		this.cumulReparation = cumulReparation;
	}

	public Boolean getDisponible() {
		return disponible;
	}

	public void setDisponible(Boolean disponible) {
		this.disponible = disponible;
	}

	public Integer getNoImmatriculation() {
		return noImmatriculation;
	}

	public void setNoImmatriculation(Integer noImmatriculation) {
		this.noImmatriculation = noImmatriculation;
	}

	public TypeVoiture getTypeVoiture() {
		return typeVoiture;
	}

	public void setTypeVoiture(TypeVoiture typeVoiture) {
		this.typeVoiture = typeVoiture;
	}

}
