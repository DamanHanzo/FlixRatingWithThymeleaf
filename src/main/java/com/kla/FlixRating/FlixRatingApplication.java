package com.kla.FlixRating;

import com.kla.FlixRating.model.Comment;
import com.kla.FlixRating.model.Flix;
import com.kla.FlixRating.repository.FlixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class FlixRatingApplication implements CommandLineRunner {

	@Autowired
	private FlixRepository flixRepository;

	public static void main(String[] args) {
		SpringApplication.run(FlixRatingApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... strings) throws Exception {
		Flix flix = new Flix();
		flix.setName("CommentTest1");
		flix.setDescription("TEST");
		flix.setAvgRating(3.0F);
		flix.setGenre("NA");

		Set comments = new HashSet<Comment>(){{
			add(new Comment("asb", "I like this"));
			add(new Comment("sad", "what??!!"));
		}};
		flix.setComments(comments);

		flixRepository.save(flix);
	}
}
