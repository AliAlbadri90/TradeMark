package ly.gov.eidc.archive.service.mapper;

import ly.gov.eidc.archive.domain.ViewLog;
import ly.gov.eidc.archive.service.dto.ViewLogDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ViewLog} and its DTO {@link ViewLogDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ViewLogMapper extends EntityMapper<ViewLogDTO, ViewLog> {}
