package com.cristiano.estudos.sync.wrapper.demo.camel.controller;

import com.cristiano.estudos.sync.wrapper.demo.camel.controller.representation.ConsumerRepresentation;
import com.cristiano.estudos.sync.wrapper.demo.camel.controller.representation.PersonRepresentation;
import com.cristiano.estudos.sync.wrapper.demo.camel.domain.Consumer;
import com.cristiano.estudos.sync.wrapper.demo.camel.domain.Person;
import com.cristiano.estudos.sync.wrapper.demo.camel.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Api("Consumer API")
@RestController
@RequestMapping("/v1/consumer")
public class ConsumerController {

    private PersonService service;

    @Autowired
    public ConsumerController(PersonService service) {
        this.service = service;
    }

    @ApiOperation(nickname = "createRandomPerson", value = "Creates a random person and convert to consumer")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = ConsumerRepresentation.class),
            @ApiResponse(code = 500, message = "Internal Error")
    })
    @PostMapping("/person/{id}/random")
    public ResponseEntity createRandomPerson(@PathVariable("id") Integer id){
        Person person = new Person(id);
        person.setDateOfBirth(new Date());
        person.setFirstName("Name");
        person.setLastName("Lastname");
        Consumer consumer = service.convertPersonToConsumer(person);
        ConsumerRepresentation representation = new ConsumerRepresentation();
        representation.name = consumer.getFullName();
        representation.id = consumer.getId();
        representation.age = consumer.getYearOld();
        representation.responseId = consumer.getMessageId();

        return ResponseEntity.ok(representation);
    }

    @ApiOperation(nickname = "convert", value = "Creates a random person and convert to consumer")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = ConsumerRepresentation.class),
            @ApiResponse(code = 500, message = "Internal Error")
    })
    @PutMapping("/person/{id}")
    public ResponseEntity convert(@RequestBody PersonRepresentation person, @PathVariable("id") Integer id){

        var domain = new Person(id);
        domain.setLastName(person.lastName);
        domain.setFirstName(person.firstName);
        domain.setDateOfBirth(person.dateOfBirth);

        var consumer = service.convertPersonToConsumer(domain);

        ConsumerRepresentation representation = new ConsumerRepresentation();
        representation.name = consumer.getFullName();
        representation.id = consumer.getId();
        representation.age = consumer.getYearOld();
        representation.responseId = consumer.getMessageId();

        return ResponseEntity.ok(representation);
    }
}
