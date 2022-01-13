package ly.gov.eidc.archive.service;

import java.util.Optional;
import ly.gov.eidc.archive.domain.Government;
import ly.gov.eidc.archive.repository.GovernmentRepository;
import ly.gov.eidc.archive.service.dto.GovernmentDTO;
import ly.gov.eidc.archive.service.mapper.GovernmentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Government}.
 */
@Service
@Transactional
public class GovernmentService {

    private final Logger log = LoggerFactory.getLogger(GovernmentService.class);

    private final GovernmentRepository governmentRepository;

    private final GovernmentMapper governmentMapper;

    public GovernmentService(GovernmentRepository governmentRepository, GovernmentMapper governmentMapper) {
        this.governmentRepository = governmentRepository;
        this.governmentMapper = governmentMapper;
    }

    /**
     * Save a government.
     *
     * @param governmentDTO the entity to save.
     * @return the persisted entity.
     */
    public GovernmentDTO save(GovernmentDTO governmentDTO) {
        log.debug("Request to save Government : {}", governmentDTO);
        Government government = governmentMapper.toEntity(governmentDTO);
        government = governmentRepository.save(government);
        return governmentMapper.toDto(government);
    }

    /**
     * Partially update a government.
     *
     * @param governmentDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<GovernmentDTO> partialUpdate(GovernmentDTO governmentDTO) {
        log.debug("Request to partially update Government : {}", governmentDTO);

        return governmentRepository
            .findById(governmentDTO.getId())
            .map(existingGovernment -> {
                governmentMapper.partialUpdate(existingGovernment, governmentDTO);

                return existingGovernment;
            })
            .map(governmentRepository::save)
            .map(governmentMapper::toDto);
    }

    /**
     * Get all the governments.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<GovernmentDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Governments");
        return governmentRepository.findAll(pageable).map(governmentMapper::toDto);
    }

    /**
     * Get one government by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<GovernmentDTO> findOne(Long id) {
        log.debug("Request to get Government : {}", id);
        return governmentRepository.findById(id).map(governmentMapper::toDto);
    }

    /**
     * Delete the government by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Government : {}", id);
        governmentRepository.deleteById(id);
    }
}
