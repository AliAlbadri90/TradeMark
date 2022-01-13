package ly.gov.eidc.archive.service.mapper;

import ly.gov.eidc.archive.domain.Minister;
import ly.gov.eidc.archive.service.dto.MinisterDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Minister} and its DTO {@link MinisterDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MinisterMapper extends EntityMapper<MinisterDTO, Minister> {
    @Named("name")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    MinisterDTO toDtoName(Minister minister);
}
