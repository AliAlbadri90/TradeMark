package ly.gov.eidc.archive.service;

import java.util.List;
import javax.persistence.criteria.JoinType;
import ly.gov.eidc.archive.domain.*; // for static metamodels
import ly.gov.eidc.archive.domain.Complaint;
import ly.gov.eidc.archive.repository.ComplaintRepository;
import ly.gov.eidc.archive.repository.search.ComplaintSearchRepository;
import ly.gov.eidc.archive.service.criteria.ComplaintCriteria;
import ly.gov.eidc.archive.service.dto.ComplaintDTO;
import ly.gov.eidc.archive.service.mapper.ComplaintMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link Complaint} entities in the database.
 * The main input is a {@link ComplaintCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ComplaintDTO} or a {@link Page} of {@link ComplaintDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ComplaintQueryService extends QueryService<Complaint> {

    private final Logger log = LoggerFactory.getLogger(ComplaintQueryService.class);

    private final ComplaintRepository complaintRepository;

    private final ComplaintMapper complaintMapper;

    private final ComplaintSearchRepository complaintSearchRepository;

    public ComplaintQueryService(
        ComplaintRepository complaintRepository,
        ComplaintMapper complaintMapper,
        ComplaintSearchRepository complaintSearchRepository
    ) {
        this.complaintRepository = complaintRepository;
        this.complaintMapper = complaintMapper;
        this.complaintSearchRepository = complaintSearchRepository;
    }

    /**
     * Return a {@link List} of {@link ComplaintDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ComplaintDTO> findByCriteria(ComplaintCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Complaint> specification = createSpecification(criteria);
        return complaintMapper.toDto(complaintRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ComplaintDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ComplaintDTO> findByCriteria(ComplaintCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Complaint> specification = createSpecification(criteria);
        return complaintRepository.findAll(specification, page).map(complaintMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ComplaintCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Complaint> specification = createSpecification(criteria);
        return complaintRepository.count(specification);
    }

    /**
     * Function to convert {@link ComplaintCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Complaint> createSpecification(ComplaintCriteria criteria) {
        Specification<Complaint> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Complaint_.id));
            }
            if (criteria.getComplaintUUID() != null) {
                specification = specification.and(buildStringSpecification(criteria.getComplaintUUID(), Complaint_.complaintUUID));
            }
            if (criteria.getComplaintNo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getComplaintNo(), Complaint_.complaintNo));
            }
            if (criteria.getTrademarkNo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTrademarkNo(), Complaint_.trademarkNo));
            }
            if (criteria.getComplaintDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getComplaintDate(), Complaint_.complaintDate));
            }
            if (criteria.getComplaintOfficeReceivedDate() != null) {
                specification =
                    specification.and(
                        buildRangeSpecification(criteria.getComplaintOfficeReceivedDate(), Complaint_.complaintOfficeReceivedDate)
                    );
            }
            if (criteria.getComplaintPaymentReceipt() != null) {
                specification =
                    specification.and(buildStringSpecification(criteria.getComplaintPaymentReceipt(), Complaint_.complaintPaymentReceipt));
            }
            if (criteria.getComplaintYear() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getComplaintYear(), Complaint_.complaintYear));
            }
            if (criteria.getComplainerPersonFullName() != null) {
                specification =
                    specification.and(
                        buildStringSpecification(criteria.getComplainerPersonFullName(), Complaint_.complainerPersonFullName)
                    );
            }
            if (criteria.getComplainerPersonJob() != null) {
                specification =
                    specification.and(buildStringSpecification(criteria.getComplainerPersonJob(), Complaint_.complainerPersonJob));
            }
            if (criteria.getComplainerPersonNationality() != null) {
                specification =
                    specification.and(
                        buildStringSpecification(criteria.getComplainerPersonNationality(), Complaint_.complainerPersonNationality)
                    );
            }
            if (criteria.getComplainerPersonAddress() != null) {
                specification =
                    specification.and(buildStringSpecification(criteria.getComplainerPersonAddress(), Complaint_.complainerPersonAddress));
            }
            if (criteria.getComplainerCompanyName() != null) {
                specification =
                    specification.and(buildStringSpecification(criteria.getComplainerCompanyName(), Complaint_.complainerCompanyName));
            }
            if (criteria.getComplainerCompanyAddress() != null) {
                specification =
                    specification.and(
                        buildStringSpecification(criteria.getComplainerCompanyAddress(), Complaint_.complainerCompanyAddress)
                    );
            }
            if (criteria.getComplainerCompanyPurpose() != null) {
                specification =
                    specification.and(
                        buildStringSpecification(criteria.getComplainerCompanyPurpose(), Complaint_.complainerCompanyPurpose)
                    );
            }
            if (criteria.getComplainerCompanyHeadQuarterAddress() != null) {
                specification =
                    specification.and(
                        buildStringSpecification(
                            criteria.getComplainerCompanyHeadQuarterAddress(),
                            Complaint_.complainerCompanyHeadQuarterAddress
                        )
                    );
            }
            if (criteria.getComplainerCompanyLibyaAddress() != null) {
                specification =
                    specification.and(
                        buildStringSpecification(criteria.getComplainerCompanyLibyaAddress(), Complaint_.complainerCompanyLibyaAddress)
                    );
            }
            if (criteria.getPdfFileUrl() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPdfFileUrl(), Complaint_.pdfFileUrl));
            }
            if (criteria.getComplaintStatus() != null) {
                specification = specification.and(buildSpecification(criteria.getComplaintStatus(), Complaint_.complaintStatus));
            }
            if (criteria.getComplaintDetails() != null) {
                specification = specification.and(buildStringSpecification(criteria.getComplaintDetails(), Complaint_.complaintDetails));
            }
            if (criteria.getNotes() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNotes(), Complaint_.notes));
            }
        }
        return specification;
    }
}
