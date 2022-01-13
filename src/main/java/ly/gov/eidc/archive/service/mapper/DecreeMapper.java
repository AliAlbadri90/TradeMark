package ly.gov.eidc.archive.service.mapper;

import ly.gov.eidc.archive.domain.Decree;
import ly.gov.eidc.archive.service.dto.DecreeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Decree} and its DTO {@link DecreeDTO}.
 */
@Mapper(
    componentModel = "spring",
    uses = { DecreeTypeMapper.class, DecreeCategoryMapper.class, MinisterMapper.class, GovernmentMapper.class }
)
public interface DecreeMapper extends EntityMapper<DecreeDTO, Decree> {
    @Mapping(target = "decreeType", source = "decreeType", qualifiedByName = "name")
    @Mapping(target = "decreeCategory", source = "decreeCategory", qualifiedByName = "name")
    @Mapping(target = "minister", source = "minister", qualifiedByName = "name")
    @Mapping(target = "government", source = "government", qualifiedByName = "name")
    DecreeDTO toDto(Decree s);
}
