package ly.gov.eidc.archive.service;

import java.util.List;
import javax.persistence.criteria.JoinType;
import ly.gov.eidc.archive.domain.*; // for static metamodels
import ly.gov.eidc.archive.domain.DecreeCategory;
import ly.gov.eidc.archive.repository.DecreeCategoryRepository;
import ly.gov.eidc.archive.service.criteria.DecreeCategoryCriteria;
import ly.gov.eidc.archive.service.dto.DecreeCategoryDTO;
import ly.gov.eidc.archive.service.mapper.DecreeCategoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link DecreeCategory} entities in the database.
 * The main input is a {@link DecreeCategoryCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DecreeCategoryDTO} or a {@link Page} of {@link DecreeCategoryDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DecreeCategoryQueryService extends QueryService<DecreeCategory> {

    private final Logger log = LoggerFactory.getLogger(DecreeCategoryQueryService.class);

    private final DecreeCategoryRepository decreeCategoryRepository;

    private final DecreeCategoryMapper decreeCategoryMapper;

    public DecreeCategoryQueryService(DecreeCategoryRepository decreeCategoryRepository, DecreeCategoryMapper decreeCategoryMapper) {
        this.decreeCategoryRepository = decreeCategoryRepository;
        this.decreeCategoryMapper = decreeCategoryMapper;
    }

    /**
     * Return a {@link List} of {@link DecreeCategoryDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DecreeCategoryDTO> findByCriteria(DecreeCategoryCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<DecreeCategory> specification = createSpecification(criteria);
        return decreeCategoryMapper.toDto(decreeCategoryRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DecreeCategoryDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page     The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DecreeCategoryDTO> findByCriteria(DecreeCategoryCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DecreeCategory> specification = createSpecification(criteria);
        return decreeCategoryRepository.findAll(specification, page).map(decreeCategoryMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DecreeCategoryCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<DecreeCategory> specification = createSpecification(criteria);
        return decreeCategoryRepository.count(specification);
    }

    /**
     * Function to convert {@link DecreeCategoryCriteria} to a {@link Specification}
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DecreeCategory> createSpecification(DecreeCategoryCriteria criteria) {
        Specification<DecreeCategory> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), DecreeCategory_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), DecreeCategory_.name));
            }
            if (criteria.getSerialNo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSerialNo(), DecreeCategory_.serialNo));
            }
        }
        return specification;
    }
}
