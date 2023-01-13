package com.app.financetracker;

import com.app.financetracker.persistence.Category;
import com.app.financetracker.repository.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FinancetrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinancetrackerApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(CategoryRepository categoryRepository){
		return args -> {
			Category education = new Category("Education");
				categoryRepository.save(education);
			Category groceries = new Category("Groceries");
				categoryRepository.save(groceries);
			Category diningOut = new Category("Dining out");
				categoryRepository.save(diningOut);
			Category entertainment = new Category("Entertainment");
				categoryRepository.save(entertainment);
			Category housing = new Category("Housing");
				categoryRepository.save(housing);
			Category services = new Category("Services");
				categoryRepository.save(services);
			Category books = new Category("Books");
				categoryRepository.save(books);
			Category alcohol = new Category("Alcohol");
				categoryRepository.save(alcohol);
			Category fuel = new Category("Fuel");
				categoryRepository.save(fuel);
			Category subscriptions = new Category("Subscriptions");
				categoryRepository.save(subscriptions);
			Category clothing = new Category("Clothing");
				categoryRepository.save(clothing);
			Category publicTransportation = new Category("Public transportation");
				categoryRepository.save(publicTransportation);
			Category bills = new Category("Bills");
				categoryRepository.save(bills);

		};
	}
}
