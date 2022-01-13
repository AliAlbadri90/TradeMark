package ly.gov.eidc.archive.service;

import java.util.Optional;
import ly.gov.eidc.archive.domain.Minister;
import ly.gov.eidc.archive.repository.MinisterRepository;
import ly.gov.eidc.archive.service.dto.MinisterDTO;
import ly.gov.eidc.archive.service.mapper.MinisterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Minister}.
 */
@Service
@Transactional
public class MinisterService {

    private final Logger log = LoggerFactory.getLogger(MinisterService.class);

    private final MinisterRepository ministerRepository;

    private final MinisterMapper ministerMapper;

    public MinisterService(MinisterRepository ministerRepository, MinisterMapper ministerMapper) {
        this.ministerRepository = ministerRepository;
        this.ministerMapper = ministerMapper;
    }

    /**
     * Save a minister.
     *
     * @param ministerDTO the entity to save.
     * @return the persisted entity.
     */
    public MinisterDTO save(MinisterDTO ministerDTO) {
        log.debug("Request to save Minister : {}", ministerDTO);
        Minister minister = ministerMapper.toEntity(ministerDTO);
        minister = ministerRepository.save(minister);
        return ministerMapper.toDto(minister);
    }

    /**
     * Partially update a minister.
     *
     * @param ministerDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<MinisterDTO> partialUpdate(MinisterDTO ministerDTO) {
        log.debug("Request to partially update Minister : {}", ministerDTO);

        return ministerRepository
            .findById(ministerDTO.getId())
            .map(existingMinister -> {
                ministerMapper.partialUpdate(existingMinister, ministerDTO);

                return existingMinister;
            })
            .map(ministerRepository::save)
            .map(ministerMapper::toDto);
    }

    /**
     * Get all the ministers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MinisterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Ministers");
        return ministerRepository.findAll(pageable).map(ministerMapper::toDto);
    }

    /**
     * Get one minister by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MinisterDTO> findOne(Long id) {
        log.debug("Request to get Minister : {}", id);
        return ministerRepository.findById(id).map(ministerMapper::toDto);
    }

    /**
     * Delete the minister by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Minister : {}", id);
        ministerRepository.deleteById(id);
    }
}
