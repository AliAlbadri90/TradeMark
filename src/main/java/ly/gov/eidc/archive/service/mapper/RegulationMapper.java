package ly.gov.eidc.archive.service.mapper;

import ly.gov.eidc.archive.domain.Regulation;
import ly.gov.eidc.archive.service.dto.RegulationDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Regulation} and its DTO {@link RegulationDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RegulationMapper extends EntityMapper<RegulationDTO, Regulation> {}
