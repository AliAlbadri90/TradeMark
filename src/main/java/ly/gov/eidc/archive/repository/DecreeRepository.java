package ly.gov.eidc.archive.repository;

import ly.gov.eidc.archive.domain.Decree;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Decree entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DecreeRepository extends JpaRepository<Decree, Long>, JpaSpecificationExecutor<Decree> {}
