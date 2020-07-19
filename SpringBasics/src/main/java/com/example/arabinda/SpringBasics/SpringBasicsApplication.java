package com.example.arabinda.SpringBasics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBasicsApplication {

    public static void main(String[] args) {
		SpringApplication.run(SpringBasicsApplication.class, args);
	}

	@Bean
    public String message(){
        System.out.println("Message bean was created");
        return "Hello, Spring!";
    }

    @Bean
    public String upperCase(MessageService messageService){
        System.out.println("Uppercase message bean was created");
        return messageService.upperCase();
    }

    @Bean
    public String lowerCase(MessageService messageService){
        System.out.println("Lowercase message bean was created");
        return messageService.lowerCase();
    }


}
