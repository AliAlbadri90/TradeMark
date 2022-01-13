package ly.gov.eidc.archive.repository;

import ly.gov.eidc.archive.domain.DecreeType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the DecreeType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DecreeTypeRepository extends JpaRepository<DecreeType, Long>, JpaSpecificationExecutor<DecreeType> {}
