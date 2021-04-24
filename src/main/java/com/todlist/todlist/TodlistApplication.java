package com.todlist.todlist;

import com.todlist.todlist.model.Role;
import com.todlist.todlist.services.RoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication

public class TodlistApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodlistApplication.class, args);
	}

}
