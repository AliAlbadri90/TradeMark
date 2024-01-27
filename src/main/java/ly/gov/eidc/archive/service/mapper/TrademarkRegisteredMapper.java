package ly.gov.eidc.archive.service.mapper;

import ly.gov.eidc.archive.domain.TrademarkRegistered;
import ly.gov.eidc.archive.service.dto.TrademarkRegisteredDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TrademarkRegistered} and its DTO {@link TrademarkRegisteredDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TrademarkRegisteredMapper extends EntityMapper<TrademarkRegisteredDTO, TrademarkRegistered> {}
