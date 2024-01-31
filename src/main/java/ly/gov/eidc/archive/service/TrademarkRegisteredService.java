package ly.gov.eidc.archive.service;

import static org.elasticsearch.index.query.QueryBuilders.*;

import com.google.common.primitives.Ints;
import java.util.Optional;
import ly.gov.eidc.archive.domain.TrademarkRegistered;
import ly.gov.eidc.archive.repository.TrademarkRegisteredRepository;
import ly.gov.eidc.archive.repository.search.TrademarkRegisteredSearchRepository;
import ly.gov.eidc.archive.service.criteria.TrademarkRegisteredCriteria;
import ly.gov.eidc.archive.service.dto.TrademarkRegisteredDTO;
import ly.gov.eidc.archive.service.mapper.TrademarkRegisteredMapper;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TrademarkRegistered}.
 */
@Service
@Transactional
public class TrademarkRegisteredService {

    private final Logger log = LoggerFactory.getLogger(TrademarkRegisteredService.class);

    private final TrademarkRegisteredRepository trademarkRegisteredRepository;

    private final TrademarkRegisteredMapper trademarkRegisteredMapper;

    private final TrademarkRegisteredSearchRepository trademarkRegisteredSearchRepository;
    private final TrademarkRegisteredQueryService trademarkRegisteredQueryService;

    public TrademarkRegisteredService(
        TrademarkRegisteredRepository trademarkRegisteredRepository,
        TrademarkRegisteredMapper trademarkRegisteredMapper,
        TrademarkRegisteredSearchRepository trademarkRegisteredSearchRepository,
        TrademarkRegisteredQueryService trademarkRegisteredQueryService
    ) {
        this.trademarkRegisteredRepository = trademarkRegisteredRepository;
        this.trademarkRegisteredMapper = trademarkRegisteredMapper;
        this.trademarkRegisteredSearchRepository = trademarkRegisteredSearchRepository;
        this.trademarkRegisteredQueryService = trademarkRegisteredQueryService;
    }

    /**
     * Save a trademarkRegistered.
     *
     * @param trademarkRegisteredDTO the entity to save.
     * @return the persisted entity.
     */
    public TrademarkRegisteredDTO save(TrademarkRegisteredDTO trademarkRegisteredDTO) {
        log.debug("Request to save TrademarkRegistered : {}", trademarkRegisteredDTO);
        TrademarkRegistered trademarkRegistered = trademarkRegisteredMapper.toEntity(trademarkRegisteredDTO);
        trademarkRegistered = trademarkRegisteredRepository.save(trademarkRegistered);
        TrademarkRegisteredDTO result = trademarkRegisteredMapper.toDto(trademarkRegistered);
        trademarkRegisteredSearchRepository.save(trademarkRegistered);
        return result;
    }

    /**
     * Partially update a trademarkRegistered.
     *
     * @param trademarkRegisteredDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<TrademarkRegisteredDTO> partialUpdate(TrademarkRegisteredDTO trademarkRegisteredDTO) {
        log.debug("Request to partially update TrademarkRegistered : {}", trademarkRegisteredDTO);

        return trademarkRegisteredRepository
            .findById(trademarkRegisteredDTO.getId())
            .map(existingTrademarkRegistered -> {
                trademarkRegisteredMapper.partialUpdate(existingTrademarkRegistered, trademarkRegisteredDTO);

                return existingTrademarkRegistered;
            })
            .map(trademarkRegisteredRepository::save)
            .map(savedTrademarkRegistered -> {
                trademarkRegisteredSearchRepository.save(savedTrademarkRegistered);

                return savedTrademarkRegistered;
            })
            .map(trademarkRegisteredMapper::toDto);
    }

    /**
     * Get all the trademarkRegistereds.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<TrademarkRegisteredDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TrademarkRegistereds");
        return trademarkRegisteredRepository.findAll(pageable).map(trademarkRegisteredMapper::toDto);
    }

    /**
     * Get one trademarkRegistered by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TrademarkRegisteredDTO> findOne(Long id) {
        log.debug("Request to get TrademarkRegistered : {}", id);
        return trademarkRegisteredRepository.findById(id).map(trademarkRegisteredMapper::toDto);
    }

    /**
     * Delete the trademarkRegistered by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TrademarkRegistered : {}", id);
        trademarkRegisteredRepository.deleteById(id);
        trademarkRegisteredSearchRepository.deleteById(id);
    }

    /**
     * Search for the trademarkRegistered corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<TrademarkRegisteredDTO> search(String query, String searchType, String selectedColumn, Pageable pageable) {
        //TODO IMPLEMENT searchType (match or similar) selectedColumn (ALL, applicantName, ......)
        log.debug("Request to search for a page of Trademark Registereds for query {}", query);
        if (searchType.contains("matching")) {
            TrademarkRegisteredCriteria criteria = new TrademarkRegisteredCriteria();
            if (selectedColumn.contains("all")) {
                criteria.applicantName().setContains(query);
                criteria.trademarkArabic().setContains(query);
                criteria.trademarkEnglish().setContains(query);
                criteria.country().setContains(query);
                criteria.nationality().setContains(query);
                if (Ints.tryParse(query) != null) criteria.year().setEquals(Ints.tryParse(query));
                criteria.decreeNo().setEquals(query);
            } else {
                switch (selectedColumn) {
                    case "applicantName":
                        criteria.applicantName().setContains(query);
                        break;
                    case "country":
                        criteria.country().setContains(query);
                        break;
                    case "nationality":
                        criteria.nationality().setContains(query);
                    case "trademarkArabic":
                        criteria.trademarkArabic().setContains(query);
                    case "trademarkEnglish":
                        criteria.trademarkEnglish().setContains(query);
                        break;
                    case "year":
                        if (Ints.tryParse(query) != null) criteria.year().setEquals(Ints.tryParse(query));
                        break;
                    case "decreeNo":
                        criteria.decreeNo().setEquals(query);
                        break;
                }
            }
            return trademarkRegisteredQueryService.findByCriteria(criteria, pageable);
        }

        BoolQueryBuilder builder;
        NativeSearchQuery Nquery = null;
        if (selectedColumn.contains("all")) {
            builder =
                new BoolQueryBuilder()
                    .should(QueryBuilders.matchQuery("applicantName", query).operator(Operator.AND))
                    .should(QueryBuilders.matchQuery("country", query))
                    .should(QueryBuilders.matchQuery("nationality", query))
                    .should(QueryBuilders.matchQuery("trademarkArabic", query))
                    .should(QueryBuilders.matchQuery("trademarkEnglish", query))
                    .should(QueryBuilders.matchQuery("year", query))
                    .should(QueryBuilders.matchQuery("decreeNo", query));

            Nquery = new NativeSearchQueryBuilder().withQuery(builder).build();
        } else {
            builder = new BoolQueryBuilder().should(QueryBuilders.matchQuery(selectedColumn, query).operator(Operator.AND));

            Nquery = new NativeSearchQueryBuilder().withQuery(builder).build();
        }
        return trademarkRegisteredSearchRepository.search(Nquery, pageable).map(trademarkRegisteredMapper::toDto);
    }

    public void reindex() {
        trademarkRegisteredSearchRepository.deleteAll();
        trademarkRegisteredSearchRepository.saveAll(trademarkRegisteredRepository.findAll());
    }
}
