package it.jac.javadb.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity //Questo dice che è una classe che serve per la creazione di una tabella nel database
@Table(name = "item") //Serve per specificare il nome della tabella
public class Item {

	@Id //Indica che questa colonna è la primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Dice che questo id è auto-generato
	@Column(name = "id")//Descrive le caratteristiche della colonna
	private int id;

	@Column(name = "code", length = 10)//Traduce le tipologie java in tipologia conosciuta dal database
	private String code;

	@Column(name = "name", length = 30)
	private String name;

	@Column(name = "description", length = 50)
	private String description;

	@Column(name = "long_description", length = 255)
	private String longDescription;

	@Column(name = "valid_from")
	private LocalDate validFrom;

	@Column(name = "valid_to")
	private LocalDate validTo;

	@Column(name = "creation_user", length = 20)
	private String creationUser;

	@Column(name = "creation_time")
	private LocalDateTime creationTime;

	@Column(name = "update_user", length = 20)
	private String updateUser;

	@Column(name = "update_time")
	private LocalDateTime updateTime;

//	@OneToMany(fetch = FetchType.LAZY) //Serve per le relazioni tra gli oggetti
//	da usare in associazioni bi-direzionali
	
//	da usare in associazioni uni-direzionali
//	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
//	@JoinColumn(name = "id_item")	

//	@OneToMany(mappedBy = "item", fetch = FetchType.EAGER)
//	private Set<ItemFeature> featureList = new HashSet<>();
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public LocalDate getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(LocalDate validFrom) {
		this.validFrom = validFrom;
	}

	public LocalDate getValidTo() {
		return validTo;
	}

	public void setValidTo(LocalDate validTo) {
		this.validTo = validTo;
	}

	public String getCreationUser() {
		return creationUser;
	}

	public void setCreationUser(String creationUser) {
		this.creationUser = creationUser;
	}

	public LocalDateTime getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(LocalDateTime creationTime) {
		this.creationTime = creationTime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public LocalDateTime getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}

//	public Set<ItemFeature> getFeatureList() {
//		return featureList;
//	}
//
//	public void setFeatureList(Set<ItemFeature> featureList) {
//		this.featureList = featureList;
//	}
	
}
