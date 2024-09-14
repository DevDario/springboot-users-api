package com.example.demo;

import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication {

	ArrayList<String> users = new ArrayList<>();

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@GetMapping("/get")
	public String users(){
		return users.toString();
	}

	@PostMapping("/new")
	public String newUser(@RequestParam(value="name", defaultValue="dario") String name) throws Exception{
		try {
			users.add(name);
			return String.format("User %s Was Created", name);

		} catch (Exception e) {
			throw new Exception("Something went south ->", e);
		}
	}

	@PutMapping("/update/{id}")
	public String updateUser(@PathVariable(value="id") int id, @RequestParam(value="newName") String newName) throws Exception{
		
		try {

			users.add(id, newName);

		} catch (Exception error) {
			throw new Exception("""
                                            There isn't a user with this id ! 
                                            more info ->""" + error);
		}

		return users.toString();
	}

	@DeleteMapping("delete/{id}")
	public String deleteUser(@PathVariable(value="id") int userID) throws Exception{

		if(!"".equals(users.get(userID))){
			try {
				users.remove(userID);
				
			} catch (Exception error) {
				throw new Exception("""
                                            Something went south ! 
                                            more info ->""" + error + "\n");
			}
		}

		return users.toString();
	}

}
