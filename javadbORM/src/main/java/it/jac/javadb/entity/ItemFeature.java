package it.jac.javadb.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "feature")
public class ItemFeature {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "feature_key", length = 30)
	private String featureKey;

	@Column(name = "feature_value", length = 30)
	private String featureValue;

	@Column(name = "creation_user", length = 20)
	private String creationUser;

	@Column(name = "creation_time")
	private LocalDateTime creationTime;

	@Column(name = "update_user", length = 20)
	private String updateUser;

	@Column(name = "update_time")
	private LocalDateTime updateTime;

//	da usare in associazioni bi-direzionali
//	@ManyToOne
//	@JoinColumn(name = "id_item")
	private Item item;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFeatureKey() {
		return featureKey;
	}

	public void setFeatureKey(String key) {
		this.featureKey = key;
	}

	public String getFeatureValue() {
		return featureValue;
	}

	public void setFeatureValue(String featureValue) {
		this.featureValue = featureValue;
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

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

}
