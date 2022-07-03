package ly.gov.eidc.archive.repository;

import java.util.List;
import ly.gov.eidc.archive.domain.Decree;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Decree entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DecreeRepository extends JpaRepository<Decree, Long>, JpaSpecificationExecutor<Decree> {
    @Query(value = "SELECT year, " + "COUNT(id) " + "FROM decree " + "GROUP BY year", nativeQuery = true)
    List<Object[]> getDecreeYearLineChart();

    List<Decree> findAllByYearOrderByMinisterId(Integer year);

    List<Decree> findAllByYearAndMinisterIdOrderByDecreeNoAsc(Integer year, Long ministerId);
}
