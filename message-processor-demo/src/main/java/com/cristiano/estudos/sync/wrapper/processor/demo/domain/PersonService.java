package com.cristiano.estudos.sync.wrapper.processor.demo.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;

public class PersonService {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public void executeBusinessLogic(Person person, String responseId) {
        try {
            Thread.sleep(200);
        }catch (Exception e){
            log.error("Error", e);
        }
        var consumer = new Consumer();
        consumer.setFullName(String.format("%s %s", person.getFirstName(), person.getLastName()));
        consumer.setId(person.getId());
        consumer.setMessageId(responseId);
        consumer.setYearOld(calculateAge(person.getDateOfBirth()));
        repository.sendResponse(consumer, responseId);
    }

    private Integer calculateAge(Date dateOfBirth) {
        var cDateOfBirth = Calendar.getInstance();
        cDateOfBirth.setTime(dateOfBirth);
        return Period.between(
                LocalDate.of(
                        cDateOfBirth.get(Calendar.YEAR),
                        cDateOfBirth.get(Calendar.MONTH),
                        cDateOfBirth.get(Calendar.DAY_OF_MONTH)),
                LocalDate.now()
        ).getYears();
    }
}
