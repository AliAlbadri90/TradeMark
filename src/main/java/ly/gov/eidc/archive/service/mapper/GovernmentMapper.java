package ly.gov.eidc.archive.service.mapper;

import ly.gov.eidc.archive.domain.Government;
import ly.gov.eidc.archive.service.dto.GovernmentDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Government} and its DTO {@link GovernmentDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GovernmentMapper extends EntityMapper<GovernmentDTO, Government> {
    @Named("name")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    GovernmentDTO toDtoName(Government government);
}
