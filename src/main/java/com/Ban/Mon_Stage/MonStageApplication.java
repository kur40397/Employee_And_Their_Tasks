package com.Ban.Mon_Stage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// had springboot does all the behind-the-scenes work
// for you blast mat codiha nta
//* Autoconfiguration katraj3 l'application dialna tdir chi 7aja par rapport les annotation
// katconfiguri lik chaque class parrapport l'annotation dialha
// katdir lik la connection avec la bd
//  kayraja3 l'application thandli http request
//  kayrja3 l'application 3andha basic security


public class MonStageApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonStageApplication.class, args);
	}

}
