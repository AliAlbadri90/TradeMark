package ly.gov.eidc.archive.service;

import static org.elasticsearch.index.query.QueryBuilders.*;

import java.time.LocalDate;
import java.util.Optional;
import ly.gov.eidc.archive.domain.Complaint;
import ly.gov.eidc.archive.domain.enumeration.ComplaintStatus;
import ly.gov.eidc.archive.repository.ComplaintRepository;
import ly.gov.eidc.archive.repository.search.ComplaintSearchRepository;
import ly.gov.eidc.archive.service.dto.ComplaintDTO;
import ly.gov.eidc.archive.service.mapper.ComplaintMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Complaint}.
 */
@Service
@Transactional
public class ComplaintService {

    private final Logger log = LoggerFactory.getLogger(ComplaintService.class);

    private final ComplaintRepository complaintRepository;

    private final ComplaintMapper complaintMapper;

    private final ComplaintSearchRepository complaintSearchRepository;

    public ComplaintService(
        ComplaintRepository complaintRepository,
        ComplaintMapper complaintMapper,
        ComplaintSearchRepository complaintSearchRepository
    ) {
        this.complaintRepository = complaintRepository;
        this.complaintMapper = complaintMapper;
        this.complaintSearchRepository = complaintSearchRepository;
    }

    /**
     * Save a complaint.
     *
     * @param complaintDTO the entity to save.
     * @return the persisted entity.
     */
    public ComplaintDTO save(ComplaintDTO complaintDTO) {
        log.debug("Request to save Complaint : {}", complaintDTO);
        Complaint complaint = complaintMapper.toEntity(complaintDTO);
        complaint = complaintRepository.save(complaint);
        ComplaintDTO result = complaintMapper.toDto(complaint);
        complaintSearchRepository.save(complaint);
        return result;
    }

    /**
     * Partially update a complaint.
     *
     * @param complaintDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ComplaintDTO> partialUpdate(ComplaintDTO complaintDTO) {
        log.debug("Request to partially update Complaint : {}", complaintDTO);

        return complaintRepository
            .findById(complaintDTO.getId())
            .map(existingComplaint -> {
                complaintMapper.partialUpdate(existingComplaint, complaintDTO);

                return existingComplaint;
            })
            .map(complaintRepository::save)
            .map(savedComplaint -> {
                complaintSearchRepository.save(savedComplaint);

                return savedComplaint;
            })
            .map(complaintMapper::toDto);
    }

    /**
     * Get all the complaints.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ComplaintDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Complaints");
        return complaintRepository.findAll(pageable).map(complaintMapper::toDto);
    }

    /**
     * Get one complaint by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ComplaintDTO> findOne(Long id) {
        log.debug("Request to get Complaint : {}", id);
        return complaintRepository.findById(id).map(complaintMapper::toDto);
    }

    /**
     * Delete the complaint by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Complaint : {}", id);
        complaintRepository.deleteById(id);
        complaintSearchRepository.deleteById(id);
    }

    /**
     * Search for the complaint corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ComplaintDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Complaints for query {}", query);
        return complaintSearchRepository.search(query, pageable).map(complaintMapper::toDto);
    }

    public ComplaintDTO create(ComplaintDTO complaintDTO) {
        complaintDTO.setComplaintStatus(ComplaintStatus.PENDING);
        complaintDTO.setComplaintDate(LocalDate.now());
        complaintDTO.setComplaintYear(LocalDate.now().getYear());
        ComplaintDTO result = save(complaintDTO);
        result.setComplaintNo(complaintDTO.getTrademarkNo() + result.getId());
        return save(result);
    }
}
