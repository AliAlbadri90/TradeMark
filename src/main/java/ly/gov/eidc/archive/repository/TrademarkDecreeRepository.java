package ly.gov.eidc.archive.repository;

import java.util.List;
import ly.gov.eidc.archive.domain.Decree;
import ly.gov.eidc.archive.domain.TrademarkDecree;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TrademarkDecree entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TrademarkDecreeRepository extends JpaRepository<TrademarkDecree, Long>, JpaSpecificationExecutor<TrademarkDecree> {
    @Query(value = "SELECT year, " + "COUNT(id) " + "FROM trademark_decree " + "GROUP BY year", nativeQuery = true)
    List<Object[]> getTrademarkDecreeYearLineChart();

    @Query(value = "SELECT created_by, " + "COUNT(id) " + "FROM trademark_decree " + "GROUP BY created_by", nativeQuery = true)
    List<Object[]> getCreatedByCount();

    List<TrademarkDecree> findAllByYearOrderByDecreeNoAsc(Integer year);

    @Query(
        value = "SELECT trademark_decree.year as year FROM trademark_decree " +
        "group by trademark_decree.year " +
        "order by trademark_decree.year desc ",
        nativeQuery = true
    )
    List<String> findAllYears();
}
