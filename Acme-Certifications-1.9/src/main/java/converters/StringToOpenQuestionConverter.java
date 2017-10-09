/*
 * StringToOpenQuestionConverter.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.OpenQuestionRepository;
import domain.OpenQuestion;

@Component
@Transactional
public class StringToOpenQuestionConverter implements Converter<String, OpenQuestion> {

	@Autowired
	OpenQuestionRepository	openQuestionRepository;


	@Override
	public OpenQuestion convert(final String text) {
		OpenQuestion result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = this.openQuestionRepository.findOne(id);
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
