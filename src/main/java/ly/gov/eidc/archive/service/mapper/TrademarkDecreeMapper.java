package ly.gov.eidc.archive.service.mapper;

import ly.gov.eidc.archive.domain.TrademarkDecree;
import ly.gov.eidc.archive.service.dto.TrademarkDecreeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TrademarkDecree} and its DTO {@link TrademarkDecreeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TrademarkDecreeMapper extends EntityMapper<TrademarkDecreeDTO, TrademarkDecree> {}
