package fr.treeptik.jdbclocation.domain;

import java.io.Serializable;

public class Suplements implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer codeSuplement;
	private String libelleSupplement;
	private Integer tarifJour;

	public Suplements() {
	}

	public Suplements(Integer codeSuplement, String libelleSupplement, Integer tarifJour) {
		super();
		this.codeSuplement = codeSuplement;
		this.libelleSupplement = libelleSupplement;
		this.tarifJour = tarifJour;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codeSuplement == null) ? 0 : codeSuplement.hashCode());
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
		Suplements other = (Suplements) obj;
		if (codeSuplement == null) {
			if (other.codeSuplement != null)
				return false;
		} else if (!codeSuplement.equals(other.codeSuplement))
			return false;
		return true;
	}

	public Integer getCodeSuplement() {
		return codeSuplement;
	}

	public void setCodeSuplement(Integer codeSuplement) {
		this.codeSuplement = codeSuplement;
	}

	public String getLibelleSupplement() {
		return libelleSupplement;
	}

	public void setLibelleSupplement(String libelleSupplement) {
		this.libelleSupplement = libelleSupplement;
	}

	public Integer getTarifJour() {
		return tarifJour;
	}

	public void setTarifJour(Integer tarifJour) {
		this.tarifJour = tarifJour;
	}

}
