package ly.gov.eidc.archive.service;

import java.util.List;
import javax.persistence.criteria.JoinType;
import ly.gov.eidc.archive.domain.*; // for static metamodels
import ly.gov.eidc.archive.domain.Government;
import ly.gov.eidc.archive.repository.GovernmentRepository;
import ly.gov.eidc.archive.service.criteria.GovernmentCriteria;
import ly.gov.eidc.archive.service.dto.GovernmentDTO;
import ly.gov.eidc.archive.service.mapper.GovernmentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link Government} entities in the database.
 * The main input is a {@link GovernmentCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link GovernmentDTO} or a {@link Page} of {@link GovernmentDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class GovernmentQueryService extends QueryService<Government> {

    private final Logger log = LoggerFactory.getLogger(GovernmentQueryService.class);

    private final GovernmentRepository governmentRepository;

    private final GovernmentMapper governmentMapper;

    public GovernmentQueryService(GovernmentRepository governmentRepository, GovernmentMapper governmentMapper) {
        this.governmentRepository = governmentRepository;
        this.governmentMapper = governmentMapper;
    }

    /**
     * Return a {@link List} of {@link GovernmentDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<GovernmentDTO> findByCriteria(GovernmentCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Government> specification = createSpecification(criteria);
        return governmentMapper.toDto(governmentRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link GovernmentDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page     The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<GovernmentDTO> findByCriteria(GovernmentCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Government> specification = createSpecification(criteria);
        return governmentRepository.findAll(specification, page).map(governmentMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(GovernmentCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Government> specification = createSpecification(criteria);
        return governmentRepository.count(specification);
    }

    /**
     * Function to convert {@link GovernmentCriteria} to a {@link Specification}
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Government> createSpecification(GovernmentCriteria criteria) {
        Specification<Government> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Government_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Government_.name));
            }
            if (criteria.getSerialNo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSerialNo(), Government_.serialNo));
            }
        }
        return specification;
    }
}
