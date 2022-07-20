package ly.gov.eidc.archive.service;

import java.util.List;
import javax.persistence.criteria.JoinType;
import ly.gov.eidc.archive.domain.*; // for static metamodels
import ly.gov.eidc.archive.domain.ViewLog;
import ly.gov.eidc.archive.repository.ViewLogRepository;
import ly.gov.eidc.archive.service.criteria.ViewLogCriteria;
import ly.gov.eidc.archive.service.dto.ViewLogDTO;
import ly.gov.eidc.archive.service.mapper.ViewLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link ViewLog} entities in the database.
 * The main input is a {@link ViewLogCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ViewLogDTO} or a {@link Page} of {@link ViewLogDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ViewLogQueryService extends QueryService<ViewLog> {

    private final Logger log = LoggerFactory.getLogger(ViewLogQueryService.class);

    private final ViewLogRepository viewLogRepository;

    private final ViewLogMapper viewLogMapper;

    public ViewLogQueryService(ViewLogRepository viewLogRepository, ViewLogMapper viewLogMapper) {
        this.viewLogRepository = viewLogRepository;
        this.viewLogMapper = viewLogMapper;
    }

    /**
     * Return a {@link List} of {@link ViewLogDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ViewLogDTO> findByCriteria(ViewLogCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ViewLog> specification = createSpecification(criteria);
        return viewLogMapper.toDto(viewLogRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ViewLogDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page     The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ViewLogDTO> findByCriteria(ViewLogCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ViewLog> specification = createSpecification(criteria);
        return viewLogRepository.findAll(specification, page).map(viewLogMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ViewLogCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ViewLog> specification = createSpecification(criteria);
        return viewLogRepository.count(specification);
    }

    /**
     * Function to convert {@link ViewLogCriteria} to a {@link Specification}
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<ViewLog> createSpecification(ViewLogCriteria criteria) {
        Specification<ViewLog> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), ViewLog_.id));
            }
            if (criteria.getActionName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getActionName(), ViewLog_.actionName));
            }
            if (criteria.getEntityName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEntityName(), ViewLog_.entityName));
            }
            if (criteria.getEntityId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEntityId(), ViewLog_.entityId));
            }
        }
        return specification;
    }
}
