package ua.mai.fam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ua.mai.fam.model.TogetherType;
import ua.mai.fam.repository.TogetherTypeRepository;
import ua.mai.fam.util.SpringUtil;

import java.util.Optional;

@Profile("da-jpa")
@Service
public class TogetherTypeService4Jpa implements TogetherTypeService {

    private TogetherTypeRepository togetherTypeRepository;

    @Autowired
    public TogetherTypeService4Jpa(TogetherTypeRepository personRepository) {
        this.togetherTypeRepository = personRepository;
        System.out.println("** Use " + SpringUtil.unproxyBean(personRepository).getClass().getSimpleName());
    }

    @Override
    public TogetherType save(TogetherType entity) {
        return togetherTypeRepository.save(entity);
    }

    @Override
    public Iterable<TogetherType> saveAll(Iterable<TogetherType> entities) {
        return togetherTypeRepository.saveAll(entities);
    }

    @Override
    public TogetherType insert(TogetherType entity) {
        return togetherTypeRepository.insert(entity);
    }

    @Override
    public void deleteByCode(String code) {
        togetherTypeRepository.deleteByCode(code);
    }

    @Override
    public void delete(TogetherType entity) {
        togetherTypeRepository.insert(entity);
    }

    @Override
    public void deleteAll(Iterable<TogetherType> entities) {
        togetherTypeRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        togetherTypeRepository.deleteAll();
    }

    @Override
    public Optional<TogetherType> findByCode(String code) {
        return togetherTypeRepository.findByCode(code);
    }

    @Override
    public Iterable<TogetherType> findAll() {
        return togetherTypeRepository.findAll();
    }

    @Override
    public Iterable<TogetherType> findAllByCode(Iterable<String> codes) {
        return togetherTypeRepository.findAllByCode(codes);
    }

    @Override
    public long count() {
        return togetherTypeRepository.count();
    }

    @Override
    public boolean existsByCode(String code) {
        return togetherTypeRepository.existsByCode(code);
    }

}
