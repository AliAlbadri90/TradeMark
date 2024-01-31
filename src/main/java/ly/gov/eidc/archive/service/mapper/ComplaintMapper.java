package ly.gov.eidc.archive.service.mapper;

import ly.gov.eidc.archive.domain.Complaint;
import ly.gov.eidc.archive.service.dto.ComplaintDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Complaint} and its DTO {@link ComplaintDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ComplaintMapper extends EntityMapper<ComplaintDTO, Complaint> {}
