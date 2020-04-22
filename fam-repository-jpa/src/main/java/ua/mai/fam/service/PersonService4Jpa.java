package ua.mai.fam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ua.mai.fam.model.Person;
import ua.mai.fam.repository.PersonRepository;
import ua.mai.fam.util.SpringUtil;

import java.util.Optional;

@Profile("da-jpa")
@Service
public class PersonService4Jpa implements PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonService4Jpa(PersonRepository personRepository) {
        this.personRepository = personRepository;
        System.out.println("** Use " + SpringUtil.unproxyBean(personRepository).getClass().getSimpleName());
    }

    @Override
    public Person save(Person entity) {
        return personRepository.save(entity);
    }

    @Override
    public Iterable<Person> saveAll(Iterable<Person> entities) {
        return personRepository.saveAll(entities);
    }

    @Override
    public Person insert(Person entity) {
        return personRepository.insert(entity);
    }

    @Override
    public void deleteById(Long id) {
        personRepository.deleteById(id);
    }

    @Override
    public void delete(Person entity) {
        personRepository.insert(entity);
    }

    @Override
    public void deleteAll(Iterable<Person> entities) {
        personRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        personRepository.deleteAll();
    }

    @Override
    public Optional<Person> findById(Long id) {
        return personRepository.findById(id);
    }

    @Override
    public Iterable<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public Iterable<Person> findAllById(Iterable<Long> ids) {
        return personRepository.findAllById(ids);
    }

    @Override
    public long count() {
        return personRepository.count();
    }

    @Override
    public boolean existsById(Long id) {
        return personRepository.existsById(id);
    }

}
