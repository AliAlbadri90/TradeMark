package ly.gov.eidc.archive.repository;

import java.util.List;
import ly.gov.eidc.archive.domain.Decree;
import ly.gov.eidc.archive.domain.Minister;
import ly.gov.eidc.archive.service.dto.MinisterDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Query(
        value = "SELECT decree.minister_id as id, minister.name as name FROM decree, minister " +
        "where decree.minister_id = minister.id " +
        "and decree.year = :year " +
        "group by decree.minister_id " +
        "order by minister.name ",
        nativeQuery = true
    )
    List<Object[]> findAllByYearGroupByMinister(@Param("year") Integer year);
}
