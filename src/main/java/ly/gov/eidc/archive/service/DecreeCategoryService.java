package ly.gov.eidc.archive.service;

import java.util.Optional;
import ly.gov.eidc.archive.domain.DecreeCategory;
import ly.gov.eidc.archive.repository.DecreeCategoryRepository;
import ly.gov.eidc.archive.service.dto.DecreeCategoryDTO;
import ly.gov.eidc.archive.service.mapper.DecreeCategoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link DecreeCategory}.
 */
@Service
@Transactional
public class DecreeCategoryService {

    private final Logger log = LoggerFactory.getLogger(DecreeCategoryService.class);

    private final DecreeCategoryRepository decreeCategoryRepository;

    private final DecreeCategoryMapper decreeCategoryMapper;

    public DecreeCategoryService(DecreeCategoryRepository decreeCategoryRepository, DecreeCategoryMapper decreeCategoryMapper) {
        this.decreeCategoryRepository = decreeCategoryRepository;
        this.decreeCategoryMapper = decreeCategoryMapper;
    }

    /**
     * Save a decreeCategory.
     *
     * @param decreeCategoryDTO the entity to save.
     * @return the persisted entity.
     */
    public DecreeCategoryDTO save(DecreeCategoryDTO decreeCategoryDTO) {
        log.debug("Request to save DecreeCategory : {}", decreeCategoryDTO);
        DecreeCategory decreeCategory = decreeCategoryMapper.toEntity(decreeCategoryDTO);
        decreeCategory = decreeCategoryRepository.save(decreeCategory);
        return decreeCategoryMapper.toDto(decreeCategory);
    }

    /**
     * Partially update a decreeCategory.
     *
     * @param decreeCategoryDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DecreeCategoryDTO> partialUpdate(DecreeCategoryDTO decreeCategoryDTO) {
        log.debug("Request to partially update DecreeCategory : {}", decreeCategoryDTO);

        return decreeCategoryRepository
            .findById(decreeCategoryDTO.getId())
            .map(existingDecreeCategory -> {
                decreeCategoryMapper.partialUpdate(existingDecreeCategory, decreeCategoryDTO);

                return existingDecreeCategory;
            })
            .map(decreeCategoryRepository::save)
            .map(decreeCategoryMapper::toDto);
    }

    /**
     * Get all the decreeCategories.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<DecreeCategoryDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DecreeCategories");
        return decreeCategoryRepository.findAll(pageable).map(decreeCategoryMapper::toDto);
    }

    /**
     * Get one decreeCategory by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DecreeCategoryDTO> findOne(Long id) {
        log.debug("Request to get DecreeCategory : {}", id);
        return decreeCategoryRepository.findById(id).map(decreeCategoryMapper::toDto);
    }

    /**
     * Delete the decreeCategory by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete DecreeCategory : {}", id);
        decreeCategoryRepository.deleteById(id);
    }
}
