/*
 * AnnouncementReviewerController.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers.reviewer;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AnnouncementService;
import controllers.AbstractController;
import domain.Announcement;

@Controller
@RequestMapping("/announcement/reviewer")
public class AnnouncementReviewerController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private AnnouncementService	announcementService;


	// Constructors -----------------------------------------------------------

	public AnnouncementReviewerController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list-to-review", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Announcement> announcements;

		announcements = this.announcementService.findToReview();

		result = new ModelAndView("announcement/list");
		result.addObject("requestURI", "announcement/reviewer/list-to-review.do");
		result.addObject("announcements", announcements);

		return result;
	}

}
