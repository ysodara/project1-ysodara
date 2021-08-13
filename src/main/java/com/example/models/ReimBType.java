package com.example.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="reimburstment_types")
@JsonIgnoreProperties (value = {"hibernateLazyInitializer", "handler"})
public class ReimBType {
	
	@Id
	@Column(name="reimb_type_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int reimBTypeId;
	
	@Column(name="reimb_type", unique=true, nullable=false)
	private String reimBType;
	
	public ReimBType() {		
	}
	
	public ReimBType(int id,String type) {
		this.reimBType=type;
		this.reimBTypeId =id;
	}
	
	public int getReimBTypeId() {
		return reimBTypeId;
	}
	
	public void setReimBTypeId(int reimBTypeId) {
		this.reimBTypeId = reimBTypeId;
	}
	
	public String getReimBType() {
		return reimBType;
	}
	
	public void setReimBType(String reimBType) {
		this.reimBType = reimBType;
	}	
}
