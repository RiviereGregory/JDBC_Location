package fr.treeptik.jdbclocation.domain;

import java.io.Serializable;
import java.util.Date;

public class Reparations implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer numeroRep;
	private Date dateReparation;
	private String descriptionPanne;
	private Integer montantReparation;
	private Voiture voiture;

	public Reparations() {
	}

	public Reparations(Integer numeroRep, Date dateReparation, String descriptionPanne,
			Integer montantReparation, Voiture voiture) {
		super();
		this.numeroRep = numeroRep;
		this.dateReparation = dateReparation;
		this.descriptionPanne = descriptionPanne;
		this.montantReparation = montantReparation;
		this.voiture = voiture;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numeroRep == null) ? 0 : numeroRep.hashCode());
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
		Reparations other = (Reparations) obj;
		if (numeroRep == null) {
			if (other.numeroRep != null)
				return false;
		} else if (!numeroRep.equals(other.numeroRep))
			return false;
		return true;
	}

	public Integer getNumeroRep() {
		return numeroRep;
	}

	public void setNumeroRep(Integer numeroRep) {
		this.numeroRep = numeroRep;
	}

	public Date getDateReparation() {
		return dateReparation;
	}

	public void setDateReparation(Date dateReparation) {
		this.dateReparation = dateReparation;
	}

	public String getDescriptionPanne() {
		return descriptionPanne;
	}

	public void setDescriptionPanne(String descriptionPanne) {
		this.descriptionPanne = descriptionPanne;
	}

	public Integer getMontantReparation() {
		return montantReparation;
	}

	public void setMontantReparation(Integer montantReparation) {
		this.montantReparation = montantReparation;
	}

	public Voiture getVoiture() {
		return voiture;
	}

	public void setVoiture(Voiture voiture) {
		this.voiture = voiture;
	}

}
