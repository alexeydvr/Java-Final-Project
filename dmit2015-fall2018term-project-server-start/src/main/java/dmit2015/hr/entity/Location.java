package dmit2015.hr.entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import java.util.List;


/**
 * The persistent class for the LOCATIONS database table.
 * 
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name="LOCATIONS")
@NamedQuery(name="Location.findAll", query="SELECT l FROM Location l")
public class Location implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LOCATIONS_LOCATIONID_GENERATOR", sequenceName="LOCATIONS_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LOCATIONS_LOCATIONID_GENERATOR")
	@Column(name="LOCATION_ID")
	private long locationId;

	@NotBlank(message="City value is required.")
	@Size(max=30, message="City must be {max} or less characters.")
	private String city;

	@Column(name="POSTAL_CODE")
	private String postalCode;

	@Size(max=25, message="State/Province must be {max} or less characters.")
	@Column(name="STATE_PROVINCE")
	private String stateProvince;

	@Size(max=40, message="Street Address must be {max} or less characters.")
	@Column(name="STREET_ADDRESS")
	private String streetAddress;

	//bi-directional many-to-one association to Department
	@XmlTransient
	@OneToMany(mappedBy="location")
	private List<Department> departments;

	//bi-directional many-to-one association to Country
	@ManyToOne
	@JoinColumn(name="COUNTRY_ID")
	private Country country;

	public Location() {
	}

	public long getLocationId() {
		return this.locationId;
	}

	public void setLocationId(long locationId) {
		this.locationId = locationId;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getStateProvince() {
		return this.stateProvince;
	}

	public void setStateProvince(String stateProvince) {
		this.stateProvince = stateProvince;
	}

	public String getStreetAddress() {
		return this.streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public List<Department> getDepartments() {
		return this.departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	public Department addDepartment(Department department) {
		getDepartments().add(department);
		department.setLocation(this);

		return department;
	}

	public Department removeDepartment(Department department) {
		getDepartments().remove(department);
		department.setLocation(null);

		return department;
	}

	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

}