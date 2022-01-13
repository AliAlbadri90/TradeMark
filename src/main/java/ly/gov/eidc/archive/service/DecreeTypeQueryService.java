package ly.gov.eidc.archive.service;

import java.util.List;
import javax.persistence.criteria.JoinType;
import ly.gov.eidc.archive.domain.*; // for static metamodels
import ly.gov.eidc.archive.domain.DecreeType;
import ly.gov.eidc.archive.repository.DecreeTypeRepository;
import ly.gov.eidc.archive.service.criteria.DecreeTypeCriteria;
import ly.gov.eidc.archive.service.dto.DecreeTypeDTO;
import ly.gov.eidc.archive.service.mapper.DecreeTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link DecreeType} entities in the database.
 * The main input is a {@link DecreeTypeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DecreeTypeDTO} or a {@link Page} of {@link DecreeTypeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DecreeTypeQueryService extends QueryService<DecreeType> {

    private final Logger log = LoggerFactory.getLogger(DecreeTypeQueryService.class);

    private final DecreeTypeRepository decreeTypeRepository;

    private final DecreeTypeMapper decreeTypeMapper;

    public DecreeTypeQueryService(DecreeTypeRepository decreeTypeRepository, DecreeTypeMapper decreeTypeMapper) {
        this.decreeTypeRepository = decreeTypeRepository;
        this.decreeTypeMapper = decreeTypeMapper;
    }

    /**
     * Return a {@link List} of {@link DecreeTypeDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DecreeTypeDTO> findByCriteria(DecreeTypeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<DecreeType> specification = createSpecification(criteria);
        return decreeTypeMapper.toDto(decreeTypeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DecreeTypeDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page     The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DecreeTypeDTO> findByCriteria(DecreeTypeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DecreeType> specification = createSpecification(criteria);
        return decreeTypeRepository.findAll(specification, page).map(decreeTypeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DecreeTypeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<DecreeType> specification = createSpecification(criteria);
        return decreeTypeRepository.count(specification);
    }

    /**
     * Function to convert {@link DecreeTypeCriteria} to a {@link Specification}
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DecreeType> createSpecification(DecreeTypeCriteria criteria) {
        Specification<DecreeType> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), DecreeType_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), DecreeType_.name));
            }
            if (criteria.getSerialNo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSerialNo(), DecreeType_.serialNo));
            }
        }
        return specification;
    }
}
