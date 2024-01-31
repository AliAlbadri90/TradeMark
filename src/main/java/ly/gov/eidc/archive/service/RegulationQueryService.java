package ly.gov.eidc.archive.service;

import java.util.List;
import javax.persistence.criteria.JoinType;
import ly.gov.eidc.archive.domain.*; // for static metamodels
import ly.gov.eidc.archive.domain.Regulation;
import ly.gov.eidc.archive.repository.RegulationRepository;
import ly.gov.eidc.archive.repository.search.RegulationSearchRepository;
import ly.gov.eidc.archive.service.criteria.RegulationCriteria;
import ly.gov.eidc.archive.service.dto.RegulationDTO;
import ly.gov.eidc.archive.service.mapper.RegulationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link Regulation} entities in the database.
 * The main input is a {@link RegulationCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link RegulationDTO} or a {@link Page} of {@link RegulationDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class RegulationQueryService extends QueryService<Regulation> {

    private final Logger log = LoggerFactory.getLogger(RegulationQueryService.class);

    private final RegulationRepository regulationRepository;

    private final RegulationMapper regulationMapper;

    private final RegulationSearchRepository regulationSearchRepository;

    public RegulationQueryService(
        RegulationRepository regulationRepository,
        RegulationMapper regulationMapper,
        RegulationSearchRepository regulationSearchRepository
    ) {
        this.regulationRepository = regulationRepository;
        this.regulationMapper = regulationMapper;
        this.regulationSearchRepository = regulationSearchRepository;
    }

    /**
     * Return a {@link List} of {@link RegulationDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<RegulationDTO> findByCriteria(RegulationCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Regulation> specification = createSpecification(criteria);
        return regulationMapper.toDto(regulationRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link RegulationDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<RegulationDTO> findByCriteria(RegulationCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Regulation> specification = createSpecification(criteria);
        return regulationRepository.findAll(specification, page).map(regulationMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(RegulationCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Regulation> specification = createSpecification(criteria);
        return regulationRepository.count(specification);
    }

    /**
     * Function to convert {@link RegulationCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Regulation> createSpecification(RegulationCriteria criteria) {
        Specification<Regulation> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Regulation_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), Regulation_.title));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), Regulation_.description));
            }
            if (criteria.getType() != null) {
                specification = specification.and(buildStringSpecification(criteria.getType(), Regulation_.type));
            }
            if (criteria.getYear() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getYear(), Regulation_.year));
            }
            if (criteria.getFilePdfUrl() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFilePdfUrl(), Regulation_.filePdfUrl));
            }
            if (criteria.getFileWordUrl() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFileWordUrl(), Regulation_.fileWordUrl));
            }
            if (criteria.getIsActive() != null) {
                specification = specification.and(buildSpecification(criteria.getIsActive(), Regulation_.isActive));
            }
        }
        return specification;
    }
}
