package com.example.models;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="reimburstments")
@JsonIgnoreProperties (value = {"hibernateLazyInitializer", "handler"})
public class Reimburstment {
	
	@Id
	@Column(name="reimb_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int reimbId;
	
	@Column(name="amount", nullable =false)
	private double amount;
	
	@Column(name="description", nullable =false)
	private String description;
	
	@Column(name="reciept", nullable =true)
	private String reciept;
	
	@Column(name="submitted", nullable =true)
	private LocalDateTime submitted;
	
	@Column(name="resovled", nullable =true)
	private LocalDateTime resovled;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="employee_submit_FK")
	private User submittedBy;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="manager_resovle_FK")
	private User resovledBy;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="type_FK")
	private ReimBType type;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="status_FK")
	private ReimBStatus status;
	
	public Reimburstment () {
		
	}
	//For setting for Reimburstment to database by Employee
	public Reimburstment (double amount, String description, String reciept, User submittedBy, ReimBType type, LocalDateTime submitted,  ReimBStatus status) {
		this.amount = amount;
		this.submittedBy = submittedBy;
		this.description = description;
		this.reciept = reciept;
		this.type = type;
		this.status = status;
		this.submitted = submitted;
	}
	
	public Reimburstment (int reimId, double amount, String description, String reciept, User submittedBy, ReimBType type, LocalDateTime submitted,  ReimBStatus status) {
		this.reimbId = reimId;
		this.amount = amount;
		this.submittedBy = submittedBy;
		this.description = description;
		this.reciept = reciept;
		this.type = type;
		this.status = status;
		this.resovledBy = new User();
		this.submitted = submitted;
	}
	
	public Reimburstment (int reimId, double amount, String description, String reciept, User submittedBy,User resovledBy, ReimBType type, LocalDateTime submitted,  ReimBStatus status) {
		this.reimbId = reimId;
		this.amount = amount;
		this.submittedBy = submittedBy;
		this.description = description;
		this.reciept = reciept;
		this.type = type;
		this.status = status;
		this.resovledBy = resovledBy;
		this.submitted = submitted;
	}
	
	
	public void setReiemburstmentEmployee (int reimId, double amount, User submittedBy, String description, String reciept, ReimBType type, LocalDateTime submitted,  ReimBStatus status) {
		this.reimbId = reimId;
		this.amount = amount;
		this.submittedBy = submittedBy;
		this.description = description;
		this.reciept = reciept;
	}
	
	public void setReiemburstmentManager (int reimId, double amount, User resovledBy, String description, String reciept, ReimBType type, LocalDateTime submitted,  ReimBStatus status) {
		this.reimbId = reimId;
		this.amount = amount;
		this.resovledBy = resovledBy;
		this.description = description;
		this.reciept = reciept;
	}
	public int getReimbId() {
		return reimbId;
	}
	public void setReimbId(int reimbId) {
		this.reimbId = reimbId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getReciept() {
		return reciept;
	}
	public void setReciept(String reciept) {
		this.reciept = reciept;
	}
	public LocalDateTime getSubmitted() {
		return submitted;
	}
	public void setSubmitted(LocalDateTime submitted) {
		this.submitted = submitted;
	}
	public LocalDateTime getResovled() {
		return resovled;
	}
	public void setResovled(LocalDateTime resovled) {
		this.resovled = resovled;
	}
	public User getSubmittedBy() {
		return submittedBy;
	}
	public void setSubmittedBy(User submittedBy) {
		this.submittedBy = submittedBy;
	}
	public User getResovledBy() {
		return resovledBy;
	}
	public void setResovledBy(User resovledBy) {
		this.resovledBy = resovledBy;
	}
	public ReimBType getType() {
		return type;
	}
	public void setType(ReimBType type) {
		this.type = type;
	}
	public ReimBStatus getStatus() {
		return status;
	}
	public void setStatus(ReimBStatus status) {
		this.status = status;
	}
	
	
	
	@Override
	public String toString() {
		return "Reimburstment [reimbId=" + reimbId + ", amount=" + amount + ", description=" + description
				+ ", submittedBy="+ submittedBy.getUsername() + ", type=" + type.getReimBType() + ", status=" + status.getReimBStatusId() + "]";
	}
	
	
}
