package ly.gov.eidc.archive.repository;

import ly.gov.eidc.archive.domain.TrademarkRegistered;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TrademarkRegistered entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TrademarkRegisteredRepository
    extends JpaRepository<TrademarkRegistered, Long>, JpaSpecificationExecutor<TrademarkRegistered> {}
