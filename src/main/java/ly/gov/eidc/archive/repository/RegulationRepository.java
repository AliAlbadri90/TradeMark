package ly.gov.eidc.archive.repository;

import ly.gov.eidc.archive.domain.Regulation;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Regulation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RegulationRepository extends JpaRepository<Regulation, Long>, JpaSpecificationExecutor<Regulation> {}
