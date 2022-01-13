package ly.gov.eidc.archive.repository;

import ly.gov.eidc.archive.domain.Minister;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Minister entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MinisterRepository extends JpaRepository<Minister, Long>, JpaSpecificationExecutor<Minister> {}
