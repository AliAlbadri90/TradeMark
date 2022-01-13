package ly.gov.eidc.archive.service;

import java.util.List;
import javax.persistence.criteria.JoinType;
import ly.gov.eidc.archive.domain.*; // for static metamodels
import ly.gov.eidc.archive.domain.Minister;
import ly.gov.eidc.archive.repository.MinisterRepository;
import ly.gov.eidc.archive.service.criteria.MinisterCriteria;
import ly.gov.eidc.archive.service.dto.MinisterDTO;
import ly.gov.eidc.archive.service.mapper.MinisterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link Minister} entities in the database.
 * The main input is a {@link MinisterCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MinisterDTO} or a {@link Page} of {@link MinisterDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MinisterQueryService extends QueryService<Minister> {

    private final Logger log = LoggerFactory.getLogger(MinisterQueryService.class);

    private final MinisterRepository ministerRepository;

    private final MinisterMapper ministerMapper;

    public MinisterQueryService(MinisterRepository ministerRepository, MinisterMapper ministerMapper) {
        this.ministerRepository = ministerRepository;
        this.ministerMapper = ministerMapper;
    }

    /**
     * Return a {@link List} of {@link MinisterDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MinisterDTO> findByCriteria(MinisterCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Minister> specification = createSpecification(criteria);
        return ministerMapper.toDto(ministerRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MinisterDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page     The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MinisterDTO> findByCriteria(MinisterCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Minister> specification = createSpecification(criteria);
        return ministerRepository.findAll(specification, page).map(ministerMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MinisterCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Minister> specification = createSpecification(criteria);
        return ministerRepository.count(specification);
    }

    /**
     * Function to convert {@link MinisterCriteria} to a {@link Specification}
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Minister> createSpecification(MinisterCriteria criteria) {
        Specification<Minister> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Minister_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Minister_.name));
            }
            if (criteria.getSerialNo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSerialNo(), Minister_.serialNo));
            }
            if (criteria.getJobTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getJobTitle(), Minister_.jobTitle));
            }
        }
        return specification;
    }
}
