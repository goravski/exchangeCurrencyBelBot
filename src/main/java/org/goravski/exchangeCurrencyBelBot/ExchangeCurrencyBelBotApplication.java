package org.goravski.exchangeCurrencyBelBot;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.util.stream.Stream;

@SpringBootApplication
public class ExchangeCurrencyBelBotApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExchangeCurrencyBelBotApplication.class, args);
		File dir = new File("./exchangeCurrencyBelBot-0.0.1-SNAPSHOT.jar");
		Stream.of(dir.listFiles()).forEach(System.out ::println);
	}

}
