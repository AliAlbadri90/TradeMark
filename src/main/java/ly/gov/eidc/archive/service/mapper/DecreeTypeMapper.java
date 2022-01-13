package ly.gov.eidc.archive.service.mapper;

import ly.gov.eidc.archive.domain.DecreeType;
import ly.gov.eidc.archive.service.dto.DecreeTypeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DecreeType} and its DTO {@link DecreeTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DecreeTypeMapper extends EntityMapper<DecreeTypeDTO, DecreeType> {
    @Named("name")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    DecreeTypeDTO toDtoName(DecreeType decreeType);
}
