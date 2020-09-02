package org.bankkata.entities;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Agency {

	@NotNull
	@Size(min = 4, max = 4)
	private long agencyId;

	@NotNull
	private String agencyName;

	private String agencyLocation;

	public Agency() {

	}

	public Agency(long agencyId, String agencyName, String agencyLocation) {
		this.agencyId = agencyId;
		this.agencyName = agencyName;
		this.agencyLocation = agencyLocation;
	}

	public long getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(long agencyId) {
		this.agencyId = agencyId;
	}

	public String getAgencyName() {
		return agencyName;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}

	public String getAgencyLocation() {
		return agencyLocation;
	}

	public void setAgencyLocation(String agencyLocation) {
		this.agencyLocation = agencyLocation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (agencyId ^ (agencyId >>> 32));
		result = prime * result + ((agencyLocation == null) ? 0 : agencyLocation.hashCode());
		result = prime * result + ((agencyName == null) ? 0 : agencyName.hashCode());
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
		Agency other = (Agency) obj;
		if (agencyId != other.agencyId)
			return false;
		if (agencyLocation == null) {
			if (other.agencyLocation != null)
				return false;
		} else if (!agencyLocation.equals(other.agencyLocation))
			return false;
		if (agencyName == null) {
			if (other.agencyName != null)
				return false;
		} else if (!agencyName.equals(other.agencyName))
			return false;
		return true;
	}

}
