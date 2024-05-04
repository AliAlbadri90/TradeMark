package ly.gov.eidc.archive.repository.search;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

import java.util.List;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import ly.gov.eidc.archive.domain.Complaint;
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
 * Spring Data Elasticsearch repository for the {@link Complaint} entity.
 */
public interface ComplaintSearchRepository extends ElasticsearchRepository<Complaint, Long>, ComplaintSearchRepositoryInternal {}

interface ComplaintSearchRepositoryInternal {
    Page<Complaint> search(String query, Pageable pageable);
}

class ComplaintSearchRepositoryInternalImpl implements ComplaintSearchRepositoryInternal {

    private final ElasticsearchRestTemplate elasticsearchTemplate;

    ComplaintSearchRepositoryInternalImpl(ElasticsearchRestTemplate elasticsearchTemplate) {
        this.elasticsearchTemplate = elasticsearchTemplate;
    }

    @Override
    public Page<Complaint> search(String query, Pageable pageable) {
        NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(queryStringQuery(query));
        nativeSearchQuery.setPageable(pageable);
        List<Complaint> hits = elasticsearchTemplate
            .search(nativeSearchQuery, Complaint.class)
            .map(SearchHit::getContent)
            .stream()
            .collect(Collectors.toList());

        return new PageImpl<>(hits, pageable, hits.size());
    }
}