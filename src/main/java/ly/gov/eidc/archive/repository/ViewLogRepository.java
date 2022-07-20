package ly.gov.eidc.archive.repository;

import ly.gov.eidc.archive.domain.ViewLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ViewLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ViewLogRepository extends JpaRepository<ViewLog, Long>, JpaSpecificationExecutor<ViewLog> {}
