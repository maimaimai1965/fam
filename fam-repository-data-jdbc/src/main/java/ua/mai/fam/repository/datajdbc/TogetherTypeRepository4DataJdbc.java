package ua.mai.fam.repository.datajdbc;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import ua.mai.fam.model.TogetherType;
import ua.mai.fam.repository.TogetherTypeRepository;
import ua.mai.fam.util.HasId;

import java.util.ArrayList;
import java.util.Optional;

@Profile("da-data-jdbc")
public interface TogetherTypeRepository4DataJdbc extends TogetherTypeRepository,
                                                         Repository<TogetherType, String>, WithOperations<TogetherType> {

    @Query("DELETE together_type WHERE code=:code")
    @Override
    public void deleteByCode(@Param("code") String code);

    @Override
    @Query("SELECT 'TRUE' FROM together_type WHERE code=:code")
    public boolean existsByCode(String code);

    @Override
    @Query("SELECT code, name FROM together_type")
    public Iterable<TogetherType> findAllByCode(Iterable<String> codes);

    @Override
    @Query("SELECT code, name FROM together_type WHERE code=:code")
    public Optional<TogetherType> findByCode(@Param("code") String code);

}
