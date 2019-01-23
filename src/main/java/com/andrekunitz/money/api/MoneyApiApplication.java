package com.andrekunitz.money.api;

import com.andrekunitz.money.api.config.property.MoneyApiProperty;
import com.andrekunitz.money.api.security.util.GeradorSenha;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(MoneyApiProperty.class)
public class MoneyApiApplication {

	public static void main(String[] args) {

		SpringApplication.run(MoneyApiApplication.class, args);

	}
}
