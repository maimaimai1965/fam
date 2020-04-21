package ua.mai.fam.util;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public interface ToDto<T_DTO> {

    T_DTO toDto();

    static <T extends ToDto> List<?> toDtos(Collection<T> entities)  {
        return entities.stream().map(entity -> entity.toDto()).collect(Collectors.toList());
    }

}
