package ly.gov.eidc.archive.repository;

import ly.gov.eidc.archive.domain.TrademarkDecree;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TrademarkDecree entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TrademarkDecreeRepository extends JpaRepository<TrademarkDecree, Long>, JpaSpecificationExecutor<TrademarkDecree> {}
