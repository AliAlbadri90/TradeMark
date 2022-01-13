package ly.gov.eidc.archive.repository;

import ly.gov.eidc.archive.domain.DecreeCategory;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the DecreeCategory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DecreeCategoryRepository extends JpaRepository<DecreeCategory, Long>, JpaSpecificationExecutor<DecreeCategory> {}
