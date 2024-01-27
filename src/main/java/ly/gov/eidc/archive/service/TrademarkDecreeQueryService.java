package ly.gov.eidc.archive.service;

import java.util.List;
import javax.persistence.criteria.JoinType;
import ly.gov.eidc.archive.domain.*; // for static metamodels
import ly.gov.eidc.archive.domain.TrademarkDecree;
import ly.gov.eidc.archive.repository.TrademarkDecreeRepository;
import ly.gov.eidc.archive.repository.search.TrademarkDecreeSearchRepository;
import ly.gov.eidc.archive.service.criteria.TrademarkDecreeCriteria;
import ly.gov.eidc.archive.service.dto.TrademarkDecreeDTO;
import ly.gov.eidc.archive.service.mapper.TrademarkDecreeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link TrademarkDecree} entities in the database.
 * The main input is a {@link TrademarkDecreeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link TrademarkDecreeDTO} or a {@link Page} of {@link TrademarkDecreeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class TrademarkDecreeQueryService extends QueryService<TrademarkDecree> {

    private final Logger log = LoggerFactory.getLogger(TrademarkDecreeQueryService.class);

    private final TrademarkDecreeRepository trademarkDecreeRepository;

    private final TrademarkDecreeMapper trademarkDecreeMapper;

    private final TrademarkDecreeSearchRepository trademarkDecreeSearchRepository;

    public TrademarkDecreeQueryService(
        TrademarkDecreeRepository trademarkDecreeRepository,
        TrademarkDecreeMapper trademarkDecreeMapper,
        TrademarkDecreeSearchRepository trademarkDecreeSearchRepository
    ) {
        this.trademarkDecreeRepository = trademarkDecreeRepository;
        this.trademarkDecreeMapper = trademarkDecreeMapper;
        this.trademarkDecreeSearchRepository = trademarkDecreeSearchRepository;
    }

    /**
     * Return a {@link List} of {@link TrademarkDecreeDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<TrademarkDecreeDTO> findByCriteria(TrademarkDecreeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<TrademarkDecree> specification = createSpecification(criteria);
        return trademarkDecreeMapper.toDto(trademarkDecreeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link TrademarkDecreeDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page     The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<TrademarkDecreeDTO> findByCriteria(TrademarkDecreeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<TrademarkDecree> specification = createSpecification(criteria);
        return trademarkDecreeRepository.findAll(specification, page).map(trademarkDecreeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(TrademarkDecreeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<TrademarkDecree> specification = createSpecification(criteria);
        return trademarkDecreeRepository.count(specification);
    }

    /**
     * Function to convert {@link TrademarkDecreeCriteria} to a {@link Specification}
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<TrademarkDecree> createSpecification(TrademarkDecreeCriteria criteria) {
        Specification<TrademarkDecree> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.or(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.or(buildRangeSpecification(criteria.getId(), TrademarkDecree_.id));
            }

            if (criteria.getDecreeNo() != null) {
                specification = specification.or(buildStringSpecification(criteria.getDecreeNo(), TrademarkDecree_.decreeNo));
            }
            if (criteria.getIsAccepted() != null) {
                specification = specification.or(buildSpecification(criteria.getIsAccepted(), TrademarkDecree_.isAccepted));
            }
            if (criteria.getDecreeDate() != null) {
                specification = specification.or(buildRangeSpecification(criteria.getDecreeDate(), TrademarkDecree_.decreeDate));
            }
            if (criteria.getApplicantName() != null) {
                specification = specification.or(buildStringSpecification(criteria.getApplicantName(), TrademarkDecree_.applicantName));
            }
            if (criteria.getTradeMarkOwner() != null) {
                specification = specification.or(buildStringSpecification(criteria.getTradeMarkOwner(), TrademarkDecree_.tradeMarkOwner));
            }
            if (criteria.getCountry() != null) {
                specification = specification.or(buildStringSpecification(criteria.getCountry(), TrademarkDecree_.country));
            }
            if (criteria.getApplyDate() != null) {
                specification = specification.or(buildRangeSpecification(criteria.getApplyDate(), TrademarkDecree_.applyDate));
            }
            if (criteria.getSerialNo() != null) {
                specification = specification.or(buildStringSpecification(criteria.getSerialNo(), TrademarkDecree_.serialNo));
            }
            if (criteria.getTrademarkEnglish() != null) {
                specification =
                    specification.or(buildStringSpecification(criteria.getTrademarkEnglish(), TrademarkDecree_.trademarkEnglish));
            }
            if (criteria.getTrademarkArabic() != null) {
                specification = specification.or(buildStringSpecification(criteria.getTrademarkArabic(), TrademarkDecree_.trademarkArabic));
            }
            if (criteria.getCategory() != null) {
                specification = specification.or(buildStringSpecification(criteria.getCategory(), TrademarkDecree_.category));
            }
            if (criteria.getPdfFileUrl() != null) {
                specification = specification.or(buildStringSpecification(criteria.getPdfFileUrl(), TrademarkDecree_.pdfFileUrl));
            }
            if (criteria.getExtraPdfFileUrl() != null) {
                specification = specification.or(buildStringSpecification(criteria.getExtraPdfFileUrl(), TrademarkDecree_.extraPdfFileUrl));
            }
            if (criteria.getIsWithdrawal() != null) {
                specification = specification.or(buildSpecification(criteria.getIsWithdrawal(), TrademarkDecree_.isWithdrawal));
            }
            if (criteria.getWithdrawalDecreeNo() != null) {
                specification =
                    specification.or(buildStringSpecification(criteria.getWithdrawalDecreeNo(), TrademarkDecree_.withdrawalDecreeNo));
            }
            if (criteria.getNotes() != null) {
                specification = specification.or(buildStringSpecification(criteria.getNotes(), TrademarkDecree_.notes));
            }
            if (criteria.getYear() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getYear(), TrademarkDecree_.year));
            }
        }
        return specification;
    }
}
