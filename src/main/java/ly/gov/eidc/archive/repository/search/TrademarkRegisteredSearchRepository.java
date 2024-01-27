package ly.gov.eidc.archive.repository.search;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

import java.util.List;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import ly.gov.eidc.archive.domain.Decree;
import ly.gov.eidc.archive.domain.TrademarkRegistered;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link TrademarkRegistered} entity.
 */
public interface TrademarkRegisteredSearchRepository
    extends ElasticsearchRepository<TrademarkRegistered, Long>, TrademarkRegisteredSearchRepositoryInternal {}

interface TrademarkRegisteredSearchRepositoryInternal {
    Page<TrademarkRegistered> search(String query, Pageable pageable);
    Page<TrademarkRegistered> search(NativeSearchQuery query, Pageable pageable);
}

class TrademarkRegisteredSearchRepositoryInternalImpl implements TrademarkRegisteredSearchRepositoryInternal {

    private final ElasticsearchRestTemplate elasticsearchTemplate;

    TrademarkRegisteredSearchRepositoryInternalImpl(ElasticsearchRestTemplate elasticsearchTemplate) {
        this.elasticsearchTemplate = elasticsearchTemplate;
    }

    @Override
    public Page<TrademarkRegistered> search(String query, Pageable pageable) {
        NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(queryStringQuery(query));
        nativeSearchQuery.setPageable(pageable);
        List<TrademarkRegistered> hits = elasticsearchTemplate
            .search(nativeSearchQuery, TrademarkRegistered.class)
            .map(SearchHit::getContent)
            .stream()
            .collect(Collectors.toList());

        return new PageImpl<>(hits, pageable, hits.size());
    }

    @Override
    public Page<TrademarkRegistered> search(NativeSearchQuery query, Pageable pageable) {
        query.setPageable(pageable);
        SearchHits<TrademarkRegistered> searchHits = elasticsearchTemplate.search(query, TrademarkRegistered.class);
        List<TrademarkRegistered> hits = searchHits.map(SearchHit::getContent).stream().collect(Collectors.toList());
        return new PageImpl<>(hits, pageable, searchHits.getTotalHits());
    }
}
