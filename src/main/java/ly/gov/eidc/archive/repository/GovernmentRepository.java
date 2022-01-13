package ly.gov.eidc.archive.repository;

import ly.gov.eidc.archive.domain.Government;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Government entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GovernmentRepository extends JpaRepository<Government, Long>, JpaSpecificationExecutor<Government> {}
