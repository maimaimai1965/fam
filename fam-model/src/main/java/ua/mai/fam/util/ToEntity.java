package ua.mai.fam.util;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public interface ToEntity<T_ENTITY> {

    T_ENTITY toEntity();

    static <T extends ToEntity> List<?> toEntities(Collection<T> dtos)  {
        return dtos.stream().map(dto -> dto.toEntity()).collect(Collectors.toList());
    }

}
