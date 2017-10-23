/*
 * AnnouncementService.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AnnouncementRepository;
import domain.Announcement;
import domain.Customer;
import domain.Reviewer;

@Service
@Transactional
public class AnnouncementService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private AnnouncementRepository	announcementRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private CustomerService			customerService;
	@Autowired
	private ReviewerService			reviewerService;
	@Autowired
	private RegistrationService		registrationService;


	// Constructors -----------------------------------------------------------

	public AnnouncementService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Announcement create() {
		Announcement result;

		result = new Announcement();

		return result;
	}

	public Collection<Announcement> findAll() {
		Collection<Announcement> result;

		Assert.notNull(this.announcementRepository);
		result = this.announcementRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Announcement findOne(final int announcementId) {
		Announcement result;

		result = this.announcementRepository.findOne(announcementId);

		return result;
	}

	public Announcement save(final Announcement announcement) {
		assert announcement != null;

		Announcement result;
		Date currentMoment;

		currentMoment = new Date();
		Assert.isTrue(announcement.getCertification().getExtinctionDate().after(currentMoment));
		Assert.isTrue(announcement.getCertification().getExams().contains(announcement.getExam()));

		result = this.announcementRepository.save(announcement);

		return result;
	}

	public void delete(final Announcement announcement) {
		assert announcement != null;
		assert announcement.getId() != 0;

		Assert.isTrue(this.announcementRepository.exists(announcement.getId()));
		Assert.isTrue(!this.registrationService.existsRegistrationForAnnouncement(announcement));

		this.announcementRepository.delete(announcement);
	}

	// Other business methods -------------------------------------------------

	public Collection<Announcement> findAllActive() {
		Collection<Announcement> result;
		Date currentMoment;

		currentMoment = new Date();
		result = this.announcementRepository.findAllActive(currentMoment);
		Assert.notNull(result);

		return result;
	}

	public Collection<Announcement> findRegistered() {
		Collection<Announcement> result;
		Customer customer;

		customer = this.customerService.findByPrincipal();
		Assert.notNull(customer);
		result = this.announcementRepository.findByCustomerId(customer.getId());

		return result;
	}

	public Collection<Announcement> findToReview() {
		Collection<Announcement> result;
		Reviewer reviewer;

		reviewer = this.reviewerService.findByPrincipal();
		Assert.notNull(reviewer);
		result = this.announcementRepository.findByReviewerId(reviewer.getId());

		return result;
	}

}
