package ly.gov.eidc.archive.service.mapper;

import ly.gov.eidc.archive.domain.DecreeCategory;
import ly.gov.eidc.archive.service.dto.DecreeCategoryDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DecreeCategory} and its DTO {@link DecreeCategoryDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DecreeCategoryMapper extends EntityMapper<DecreeCategoryDTO, DecreeCategory> {
    @Named("name")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    DecreeCategoryDTO toDtoName(DecreeCategory decreeCategory);
}
