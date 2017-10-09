/*
 * Registration.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Registration extends DomainEntity {

	// Constructors -----------------------------------------------------------

	public Registration() {
		super();
	}


	// Attributes -------------------------------------------------------------

	private Date	moment;


	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getMoment() {
		return this.moment;
	}

	public void setMoment(final Date moment) {
		this.moment = moment;
	}


	// Relationships ----------------------------------------------------------

	private Announcement	announcement;
	private ExamPaper		examPaper;
	private Customer		owner;


	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Announcement getAnnouncement() {
		return this.announcement;
	}

	public void setAnnouncement(final Announcement announcement) {
		this.announcement = announcement;
	}

	@Valid
	@OneToOne(cascade = CascadeType.ALL, optional = true)
	public ExamPaper getExamPaper() {
		return this.examPaper;
	}

	public void setExamPaper(final ExamPaper examPaper) {
		this.examPaper = examPaper;
	}

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Customer getOwner() {
		return this.owner;
	}

	public void setOwner(final Customer owner) {
		this.owner = owner;
	}

}
