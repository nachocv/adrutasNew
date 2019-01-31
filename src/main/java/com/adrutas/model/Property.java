package com.adrutas.model;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the properties database table.
 * 
 */
@Entity
@Table(name="properties")
@NamedQuery(name="Property.findAll", query="SELECT p FROM Property p")
@NamedQuery(name="Property.find", query="SELECT p FROM Property p WHERE key=:key")
public class Property implements Serializable {
	private static final long serialVersionUID = -3716216969984033797L;
	private static final Logger log = Logger.getLogger(Property.class.getName());

	@Id
	private String key;

	@Lob
	private String value;

	public Property() {
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}