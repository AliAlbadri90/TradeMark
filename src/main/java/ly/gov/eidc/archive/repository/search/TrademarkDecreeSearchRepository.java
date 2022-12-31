package ly.gov.eidc.archive.repository.search;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

import java.util.List;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import ly.gov.eidc.archive.domain.TrademarkDecree;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link TrademarkDecree} entity.
 */
public interface TrademarkDecreeSearchRepository
    extends ElasticsearchRepository<TrademarkDecree, Long>, TrademarkDecreeSearchRepositoryInternal {}

interface TrademarkDecreeSearchRepositoryInternal {
    Page<TrademarkDecree> search(String query, Pageable pageable);
}

class TrademarkDecreeSearchRepositoryInternalImpl implements TrademarkDecreeSearchRepositoryInternal {

    private final ElasticsearchRestTemplate elasticsearchTemplate;

    TrademarkDecreeSearchRepositoryInternalImpl(ElasticsearchRestTemplate elasticsearchTemplate) {
        this.elasticsearchTemplate = elasticsearchTemplate;
    }

    @Override
    public Page<TrademarkDecree> search(String query, Pageable pageable) {
        NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(queryStringQuery(query));
        nativeSearchQuery.setPageable(pageable);
        List<TrademarkDecree> hits = elasticsearchTemplate
            .search(nativeSearchQuery, TrademarkDecree.class)
            .map(SearchHit::getContent)
            .stream()
            .collect(Collectors.toList());

        return new PageImpl<>(hits, pageable, hits.size());
    }
}
