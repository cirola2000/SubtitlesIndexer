package status.subtitles.API;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import status.mongodb.IndexesCreator;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		new IndexesCreator().createIndexes();
		SpringApplication.run(Application.class, args);
	}
}
