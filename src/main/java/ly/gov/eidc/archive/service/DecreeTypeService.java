package ly.gov.eidc.archive.service;

import java.util.Optional;
import ly.gov.eidc.archive.domain.DecreeType;
import ly.gov.eidc.archive.repository.DecreeTypeRepository;
import ly.gov.eidc.archive.service.dto.DecreeTypeDTO;
import ly.gov.eidc.archive.service.mapper.DecreeTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link DecreeType}.
 */
@Service
@Transactional
public class DecreeTypeService {

    private final Logger log = LoggerFactory.getLogger(DecreeTypeService.class);

    private final DecreeTypeRepository decreeTypeRepository;

    private final DecreeTypeMapper decreeTypeMapper;

    public DecreeTypeService(DecreeTypeRepository decreeTypeRepository, DecreeTypeMapper decreeTypeMapper) {
        this.decreeTypeRepository = decreeTypeRepository;
        this.decreeTypeMapper = decreeTypeMapper;
    }

    /**
     * Save a decreeType.
     *
     * @param decreeTypeDTO the entity to save.
     * @return the persisted entity.
     */
    public DecreeTypeDTO save(DecreeTypeDTO decreeTypeDTO) {
        log.debug("Request to save DecreeType : {}", decreeTypeDTO);
        DecreeType decreeType = decreeTypeMapper.toEntity(decreeTypeDTO);
        decreeType = decreeTypeRepository.save(decreeType);
        return decreeTypeMapper.toDto(decreeType);
    }

    /**
     * Partially update a decreeType.
     *
     * @param decreeTypeDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DecreeTypeDTO> partialUpdate(DecreeTypeDTO decreeTypeDTO) {
        log.debug("Request to partially update DecreeType : {}", decreeTypeDTO);

        return decreeTypeRepository
            .findById(decreeTypeDTO.getId())
            .map(existingDecreeType -> {
                decreeTypeMapper.partialUpdate(existingDecreeType, decreeTypeDTO);

                return existingDecreeType;
            })
            .map(decreeTypeRepository::save)
            .map(decreeTypeMapper::toDto);
    }

    /**
     * Get all the decreeTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<DecreeTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DecreeTypes");
        return decreeTypeRepository.findAll(pageable).map(decreeTypeMapper::toDto);
    }

    /**
     * Get one decreeType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DecreeTypeDTO> findOne(Long id) {
        log.debug("Request to get DecreeType : {}", id);
        return decreeTypeRepository.findById(id).map(decreeTypeMapper::toDto);
    }

    /**
     * Delete the decreeType by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete DecreeType : {}", id);
        decreeTypeRepository.deleteById(id);
    }
}
