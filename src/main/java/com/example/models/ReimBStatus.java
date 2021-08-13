package com.example.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="reimburstment_statuses")
@JsonIgnoreProperties (value = {"hibernateLazyInitializer", "handler"})
public class ReimBStatus {
	
	@Id
	@Column(name="reimb_status_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int reimBStatusId;
	
	@Column(name="reimb_status", unique=true, nullable=false)
	private String reimBStatus;
	
	public ReimBStatus () {	
	}
	
	public ReimBStatus (int id,String status) {
		this.reimBStatus=status;
		this.reimBStatusId=id;
	}
	
	public int getReimBStatusId() {
		return reimBStatusId;
	}
	
	public void setReimBStatusId(int reimBStatusId) {
		this.reimBStatusId = reimBStatusId;
	}
	
	public String getReimBStatus() {
		return reimBStatus;
	}
	
	public void setReimBStatus(String reimBStatus) {
		this.reimBStatus = reimBStatus;
	}
	
	
	
	
}
