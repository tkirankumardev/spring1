package com.tkkcode.controller;

import com.tkkcode.controller.repo.PersonRepository;
import com.tkkcode.entity.Person;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
public class MainController {

    private final PersonRepository personRepository;

    public MainController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @ResponseBody
    @GetMapping("/{id}")
    public String home(@PathVariable int id){
        if(id != 1){
            throw new IllegalArgumentException("can't enrich now!");
        }
        return "home";
    }

    @ResponseBody
    @GetMapping("/get/{id}")
    public Optional<Person> person(@PathVariable long id){
        if (!personRepository.existsById(id)) {
            throw new EntityNotFoundException("The entity you requested is not found in database!");

        }
        return personRepository.findById(id);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handler(IllegalArgumentException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> personHandler(EntityNotFoundException e){
        return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.NOT_FOUND);
    }
}
