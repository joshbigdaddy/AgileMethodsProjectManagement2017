/*
 * Exam.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package domain;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

@Entity
@Access(AccessType.PROPERTY)
public class Exam extends DomainEntity {

	// Constructors -----------------------------------------------------------

	public Exam() {
		super();

		this.questions = new HashSet<Question>();
	}


	// Attributes -------------------------------------------------------------

	private String	title;
	private double	minimumMark;
	private int		minimumScore;


	@NotBlank
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	@Min(0)
	public double getMinimumMark() {
		return this.minimumMark;
	}

	public void setMinimumMark(final double minimumMark) {
		this.minimumMark = minimumMark;
	}

	@Range(min = 1, max = 99)
	public int getMinimumScore() {
		return this.minimumScore;
	}

	public void setMinimumScore(final int minimumScore) {
		this.minimumScore = minimumScore;
	}


	// Relationships ----------------------------------------------------------

	private Certification			certification;
	private Collection<Question>	questions;


	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Certification getCertification() {
		return this.certification;
	}

	public void setCertification(final Certification certification) {
		this.certification = certification;
	}

	@NotEmpty
	@OneToMany(mappedBy = "exam", cascade = CascadeType.ALL)
	public Collection<Question> getQuestions() {
		return this.questions;
	}

	public void setQuestions(final Collection<Question> questions) {
		this.questions = questions;
	}

	public void addQuestion(final Question question) {
		this.questions.add(question);
		question.setExam(this);
	}

	public void removeQuestion(final Question question) {
		this.questions.remove(question);
		question.setExam(null);
	}

}
