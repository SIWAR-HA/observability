package com.hammouda.observability;

import com.hammouda.observability.entities.Product;
import com.hammouda.observability.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
public class ObservabilityApplication {

	public static void main(String[] args) {
		SpringApplication.run(ObservabilityApplication.class, args);

	}
	@PostConstruct
	public void displayMachineAddress() {
		try {
			// Obtention de l'adresse IP locale
			InetAddress ip = InetAddress.getLocalHost();
			System.out.println("L'adresse IP de la machine est : " + ip.getHostAddress());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	@Bean
	CommandLineRunner commandLineRunner(ProductRepository productRepository){
		return args -> {
			productRepository.save(Product.builder().name("Computer").price(23000).build());
			productRepository.save(Product.builder().name("Smart Phone").price(1200).build());
			productRepository.save(Product.builder().name("Printer").price(300).build());
			productRepository.findAll().forEach(System.out::println);
		};
	}

}
