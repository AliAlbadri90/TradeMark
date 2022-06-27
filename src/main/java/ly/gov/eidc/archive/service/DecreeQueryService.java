package ly.gov.eidc.archive.service;

import java.util.List;
import javax.persistence.criteria.JoinType;
import ly.gov.eidc.archive.domain.*; // for static metamodels
import ly.gov.eidc.archive.domain.Decree;
import ly.gov.eidc.archive.repository.DecreeRepository;
import ly.gov.eidc.archive.service.criteria.DecreeCriteria;
import ly.gov.eidc.archive.service.dto.DecreeDTO;
import ly.gov.eidc.archive.service.mapper.DecreeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link Decree} entities in the database.
 * The main input is a {@link DecreeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DecreeDTO} or a {@link Page} of {@link DecreeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DecreeQueryService extends QueryService<Decree> {

    private final Logger log = LoggerFactory.getLogger(DecreeQueryService.class);

    private final DecreeRepository decreeRepository;

    private final DecreeMapper decreeMapper;

    public DecreeQueryService(DecreeRepository decreeRepository, DecreeMapper decreeMapper) {
        this.decreeRepository = decreeRepository;
        this.decreeMapper = decreeMapper;
    }

    /**
     * Return a {@link List} of {@link DecreeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DecreeDTO> findByCriteria(DecreeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Decree> specification = createSpecification(criteria);
        return decreeMapper.toDto(decreeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DecreeDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page     The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DecreeDTO> findByCriteria(DecreeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Decree> specification = createSpecification(criteria);
        return decreeRepository.findAll(specification, page).map(decreeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DecreeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Decree> specification = createSpecification(criteria);
        return decreeRepository.count(specification);
    }

    /**
     * Function to convert {@link DecreeCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Decree> createSpecification(DecreeCriteria criteria) {
        Specification<Decree> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.or(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.or(buildRangeSpecification(criteria.getId(), Decree_.id));
            }
            if (criteria.getDocumentNo() != null) {
                specification = specification.or(buildStringSpecification(criteria.getDocumentNo(), Decree_.documentNo));
            }
            if (criteria.getDecreeNo() != null) {
                specification = specification.or(buildStringSpecification(criteria.getDecreeNo(), Decree_.decreeNo));
            }
            if (criteria.getTitle() != null) {
                specification = specification.or(buildStringSpecification(criteria.getTitle(), Decree_.title));
            }
            if (criteria.getDetails() != null) {
                specification = specification.or(buildStringSpecification(criteria.getDetails(), Decree_.details));
            }
            if (criteria.getKeywords() != null) {
                specification = specification.or(buildStringSpecification(criteria.getKeywords(), Decree_.keywords));
            }
            if (criteria.getPages() != null) {
                specification = specification.or(buildRangeSpecification(criteria.getPages(), Decree_.pages));
            }
            if (criteria.getDecreeDate() != null) {
                specification = specification.or(buildRangeSpecification(criteria.getDecreeDate(), Decree_.decreeDate));
            }
            if (criteria.getYear() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getYear(), Decree_.year));
            }
            if (criteria.getNotes() != null) {
                specification = specification.or(buildStringSpecification(criteria.getNotes(), Decree_.notes));
            }
            if (criteria.getPdfFileUrl() != null) {
                specification = specification.or(buildStringSpecification(criteria.getPdfFileUrl(), Decree_.pdfFileUrl));
            }
            if (criteria.getWordFileUrl() != null) {
                specification = specification.or(buildStringSpecification(criteria.getWordFileUrl(), Decree_.wordFileUrl));
            }
            if (criteria.getDecreeStatus() != null) {
                specification = specification.or(buildSpecification(criteria.getDecreeStatus(), Decree_.decreeStatus));
            }
            if (criteria.getRemarks() != null) {
                specification = specification.or(buildStringSpecification(criteria.getRemarks(), Decree_.remarks));
            }
            if (criteria.getIsHidden() != null) {
                specification = specification.or(buildSpecification(criteria.getIsHidden(), Decree_.isHidden));
            }
            if (criteria.getHideNotes() != null) {
                specification = specification.or(buildStringSpecification(criteria.getHideNotes(), Decree_.hideNotes));
            }
            if (criteria.getHideEndDate() != null) {
                specification = specification.or(buildRangeSpecification(criteria.getHideEndDate(), Decree_.hideEndDate));
            }
            if (criteria.getDecreeTypeId() != null) {
                specification =
                    specification.or(
                        buildSpecification(
                            criteria.getDecreeTypeId(),
                            root -> root.join(Decree_.decreeType, JoinType.LEFT).get(DecreeType_.id)
                        )
                    );
            }
            if (criteria.getDecreeCategoryId() != null) {
                specification =
                    specification.or(
                        buildSpecification(
                            criteria.getDecreeCategoryId(),
                            root -> root.join(Decree_.decreeCategory, JoinType.LEFT).get(DecreeCategory_.id)
                        )
                    );
            }
            if (criteria.getMinisterId() != null) {
                specification =
                    specification.or(
                        buildSpecification(criteria.getMinisterId(), root -> root.join(Decree_.minister, JoinType.LEFT).get(Minister_.id))
                    );
            }
            if (criteria.getGovernmentId() != null) {
                specification =
                    specification.or(
                        buildSpecification(
                            criteria.getGovernmentId(),
                            root -> root.join(Decree_.government, JoinType.LEFT).get(Government_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
