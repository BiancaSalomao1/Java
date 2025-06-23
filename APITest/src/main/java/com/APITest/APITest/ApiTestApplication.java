package com.APITest.APITest;

import com.APITest.APITest.model.Endereco;
import com.APITest.APITest.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.Scanner;

@EnableFeignClients
@SpringBootApplication
public class ApiTestApplication implements CommandLineRunner {

	@Autowired
	private ViaCepService viaCepService;

	public static void main(String[] args) {
		SpringApplication.run(ApiTestApplication.class, args);
	}

	@Override
	public void run(String... args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Digite seu nome: ");
		String nome = scanner.nextLine();

		System.out.print("Digite seu CEP (apenas números): ");
		String cep = scanner.nextLine();

		Endereco endereco = viaCepService.consultarCep(cep);

		System.out.println("\n========= Resultado =========");
		System.out.println("Nome: " + nome);
		if (endereco != null && endereco.getCep() != null) {
			System.out.println("CEP: " + endereco.getCep());
			System.out.println("Logradouro: " + endereco.getLocalidade());
			System.out.println("Bairro: " + endereco.getBairro());
			System.out.println("Localidade: " + endereco.getLocalidade());
			System.out.println("UF: " + endereco.getUf());
		} else {
			System.out.println("CEP não encontrado.");
		}
		System.out.println("=============================");
	}
}
