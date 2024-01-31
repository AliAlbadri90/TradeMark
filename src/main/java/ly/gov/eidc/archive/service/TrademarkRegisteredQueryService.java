package ly.gov.eidc.archive.service;

import java.util.List;
import javax.persistence.criteria.JoinType;
import ly.gov.eidc.archive.domain.*; // for static metamodels
import ly.gov.eidc.archive.domain.TrademarkRegistered;
import ly.gov.eidc.archive.repository.TrademarkRegisteredRepository;
import ly.gov.eidc.archive.repository.search.TrademarkRegisteredSearchRepository;
import ly.gov.eidc.archive.service.criteria.TrademarkRegisteredCriteria;
import ly.gov.eidc.archive.service.dto.TrademarkRegisteredDTO;
import ly.gov.eidc.archive.service.mapper.TrademarkRegisteredMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link TrademarkRegistered} entities in the database.
 * The main input is a {@link TrademarkRegisteredCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link TrademarkRegisteredDTO} or a {@link Page} of {@link TrademarkRegisteredDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class TrademarkRegisteredQueryService extends QueryService<TrademarkRegistered> {

    private final Logger log = LoggerFactory.getLogger(TrademarkRegisteredQueryService.class);

    private final TrademarkRegisteredRepository trademarkRegisteredRepository;

    private final TrademarkRegisteredMapper trademarkRegisteredMapper;

    private final TrademarkRegisteredSearchRepository trademarkRegisteredSearchRepository;

    public TrademarkRegisteredQueryService(
        TrademarkRegisteredRepository trademarkRegisteredRepository,
        TrademarkRegisteredMapper trademarkRegisteredMapper,
        TrademarkRegisteredSearchRepository trademarkRegisteredSearchRepository
    ) {
        this.trademarkRegisteredRepository = trademarkRegisteredRepository;
        this.trademarkRegisteredMapper = trademarkRegisteredMapper;
        this.trademarkRegisteredSearchRepository = trademarkRegisteredSearchRepository;
    }

    /**
     * Return a {@link List} of {@link TrademarkRegisteredDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<TrademarkRegisteredDTO> findByCriteria(TrademarkRegisteredCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<TrademarkRegistered> specification = createSpecification(criteria);
        return trademarkRegisteredMapper.toDto(trademarkRegisteredRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link TrademarkRegisteredDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<TrademarkRegisteredDTO> findByCriteria(TrademarkRegisteredCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<TrademarkRegistered> specification = createSpecification(criteria);
        return trademarkRegisteredRepository.findAll(specification, page).map(trademarkRegisteredMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(TrademarkRegisteredCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<TrademarkRegistered> specification = createSpecification(criteria);
        return trademarkRegisteredRepository.count(specification);
    }

    /**
     * Function to convert {@link TrademarkRegisteredCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<TrademarkRegistered> createSpecification(TrademarkRegisteredCriteria criteria) {
        Specification<TrademarkRegistered> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), TrademarkRegistered_.id));
            }
            if (criteria.getTrademarkNo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTrademarkNo(), TrademarkRegistered_.trademarkNo));
            }
            if (criteria.getYear() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getYear(), TrademarkRegistered_.year));
            }
            if (criteria.getDecreeNo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDecreeNo(), TrademarkRegistered_.decreeNo));
            }
            if (criteria.getApplicantName() != null) {
                specification =
                    specification.and(buildStringSpecification(criteria.getApplicantName(), TrademarkRegistered_.applicantName));
            }
            if (criteria.getTradeMarkOwner() != null) {
                specification =
                    specification.and(buildStringSpecification(criteria.getTradeMarkOwner(), TrademarkRegistered_.tradeMarkOwner));
            }
            if (criteria.getCountry() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCountry(), TrademarkRegistered_.country));
            }
            if (criteria.getNationality() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNationality(), TrademarkRegistered_.nationality));
            }
            if (criteria.getAddress() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAddress(), TrademarkRegistered_.address));
            }
            if (criteria.getApplyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getApplyDate(), TrademarkRegistered_.applyDate));
            }
            if (criteria.getTrademarkEnglish() != null) {
                specification =
                    specification.and(buildStringSpecification(criteria.getTrademarkEnglish(), TrademarkRegistered_.trademarkEnglish));
            }
            if (criteria.getTrademarkArabic() != null) {
                specification =
                    specification.and(buildStringSpecification(criteria.getTrademarkArabic(), TrademarkRegistered_.trademarkArabic));
            }
            if (criteria.getCategory() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCategory(), TrademarkRegistered_.category));
            }
            if (criteria.getImageFileUrl() != null) {
                specification = specification.and(buildStringSpecification(criteria.getImageFileUrl(), TrademarkRegistered_.imageFileUrl));
            }
            if (criteria.getFileUrl() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFileUrl(), TrademarkRegistered_.fileUrl));
            }
            if (criteria.getExtraFileUrl() != null) {
                specification = specification.and(buildStringSpecification(criteria.getExtraFileUrl(), TrademarkRegistered_.extraFileUrl));
            }
            if (criteria.getPublicationDate() != null) {
                specification =
                    specification.and(buildRangeSpecification(criteria.getPublicationDate(), TrademarkRegistered_.publicationDate));
            }
            if (criteria.getPublicationNo() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPublicationNo(), TrademarkRegistered_.publicationNo));
            }
            if (criteria.getTrademarkRegisteredStatus() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getTrademarkRegisteredStatus(), TrademarkRegistered_.trademarkRegisteredStatus)
                    );
            }
            if (criteria.getIsHidden() != null) {
                specification = specification.and(buildSpecification(criteria.getIsHidden(), TrademarkRegistered_.isHidden));
            }
            if (criteria.getNotes() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNotes(), TrademarkRegistered_.notes));
            }
        }
        return specification;
    }
}
