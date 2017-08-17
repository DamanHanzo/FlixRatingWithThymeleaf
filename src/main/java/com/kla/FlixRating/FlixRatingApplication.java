package com.kla.FlixRating;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class FlixRatingApplication {

//	@Autowired
//	private FlixRepository flixRepository;

	public static void main(String[] args) {
		SpringApplication.run(FlixRatingApplication.class, args);
	}

//	@Override
//	@Transactional
//	public void run(String... strings) throws Exception {
//		Flix flix = new Flix();
//		flix.setName("CommentTest1");
//		flix.setDescription("TEST");
//		flix.setAvgRating(3.0F);
//		flix.setGenre("NA");
//		flix.addComment(new Comment("asb", "I like this"));
//		flix.addComment(new Comment("sad", "what??!!"));
//		this.flixRepository.save(flix);
//
//	}
}
