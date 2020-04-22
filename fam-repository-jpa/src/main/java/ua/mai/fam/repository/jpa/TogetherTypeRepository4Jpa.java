package ua.mai.fam.repository.jpa;

import org.springframework.context.annotation.Profile;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.mai.fam.model.TogetherType;
import ua.mai.fam.repository.TogetherTypeRepository;
import ua.mai.fam.repository.TogetherTypeRepository;
import ua.mai.fam.util.exception.FoundException;
import ua.mai.fam.util.exception.NotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@Profile("da-jpa")
@Repository
public class TogetherTypeRepository4Jpa implements TogetherTypeRepository {

    @PersistenceContext
    private EntityManager entityManager;


    @Transactional
    @Override
    public TogetherType save(TogetherType entity) {
        if (entity == null) {
            throw new IllegalArgumentException("TogetherType can't be null.");
        }
        if (entity.isNew()) {
            //Если новый person
            entityManager.persist(entity);
            return entity;
        } else {
            TogetherType managedTogetherType = entityManager.merge(entity);
            if (managedTogetherType.getId().equals(entity.getId())) {
                return managedTogetherType;
            }
            else {
                entityManager.detach(managedTogetherType);
                throw new NotFoundException("person", "id", entity.getId().toString());
            }
        }
    }

    @Override
    @Transactional
    public TogetherType insert(TogetherType entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Object can't be null.");
        }
        if (entity.getId() != null) {
            throw new FoundException("Exists code=" + entity.getCode() + " in new inserted object.");
        }
        entityManager.persist(entity);
        return entity;
    }

    @Transactional
    @Override
    public <S extends TogetherType> Iterable<S> saveAll(Iterable<S> entities) {
        if (entities == null) {
            throw new IllegalArgumentException();
        }
        List<TogetherType> list = new ArrayList<TogetherType>((entities != null) ? ((Collection) entities).size() : 0);
        entities.forEach(entity -> { if (entity != null) {
            TogetherType savedEntity = save(entity);
            list.add(savedEntity);
        }});
        return (Iterable<S>) list;
    }

    @Transactional
    @Override
    public void deleteByCode(String code) {
        if (code == null) {
            throw new IllegalArgumentException("code can't be null.");
        }
        Query query = entityManager.createQuery("DELETE FROM TogetherType t WHERE t.code=:code");
        query.setParameter("code", code).executeUpdate();
//        TogetherType ref = entityManager.getReference(TogetherType.class, id);
//        entityManager.remove(ref);
    }

    @Transactional
    @Override
    public void delete(TogetherType entity) {
        if (entity == null) {
            throw new IllegalArgumentException("TogetherType can't be null.");
        }
        if (!entity.isNew()) {
            deleteByCode(entity.getCode());
        }
    }

    @Transactional
    @Override
    public void deleteAll(Iterable<? extends TogetherType> entities) {
        entities.forEach(entity -> delete(entity));
    }

    @Transactional
    @Override
    public void deleteAll() {
        throw new RuntimeException("TogetherTypeRepository4Jdbc.deleteAll() not realised!");
    }

    @Override
    public Optional<TogetherType> findByCode(String code) {
        if (code == null) {
            throw new IllegalArgumentException("code can't be null.");
        }
        return Optional.ofNullable(entityManager.find(TogetherType.class, code));
    }

    @Override
    public Iterable<TogetherType> findAll() {
        List<TogetherType> list = entityManager.createQuery("SELECT t FROM TogetherType AS t").getResultList();
        return list;
    }

    @Override
    public Iterable<TogetherType> findAllByCode(Iterable<String> codes) {
        //TODO переписать
        if (codes == null) {
            throw new IllegalArgumentException();
        }
        List<TogetherType> list = new ArrayList<TogetherType>((codes != null) ? ((Collection) codes).size() : 0);
        codes.forEach(id -> { Optional<TogetherType> entity = findById(id);
            if (entity.isPresent()) {
                list.add(entity.get());
            }
        });
        return list;
    }

    @Override
    public long count() {
        long count = entityManager.createQuery("SELECT COUNT(*) FROM TogetherType", Long.class).getSingleResult();
        return count;
    }

    @Override
    public boolean existsByCode(String code) {
        if (code == null) {
            throw new IllegalArgumentException();
        }
        try {
            Optional<TogetherType> togetherType = findByCode(code);
            return togetherType.isPresent();
//TODO переписать на native query
//            jdbcTemplate.queryForObject("SELECT id FROM person WHERE id = ?", Long.class, id);
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

}
