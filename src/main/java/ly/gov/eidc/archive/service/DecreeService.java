package ly.gov.eidc.archive.service;

import java.util.Optional;
import ly.gov.eidc.archive.domain.Decree;
import ly.gov.eidc.archive.repository.DecreeRepository;
import ly.gov.eidc.archive.service.dto.DecreeDTO;
import ly.gov.eidc.archive.service.mapper.DecreeMapper;
import ly.gov.eidc.archive.service.util.FileTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Decree}.
 */
@Service
@Transactional
public class DecreeService {

    private final Logger log = LoggerFactory.getLogger(DecreeService.class);

    private final DecreeRepository decreeRepository;

    private final DecreeMapper decreeMapper;

    public DecreeService(DecreeRepository decreeRepository, DecreeMapper decreeMapper) {
        this.decreeRepository = decreeRepository;
        this.decreeMapper = decreeMapper;
    }

    /**
     * Save a decree.
     *
     * @param decreeDTO the entity to save.
     * @return the persisted entity.
     */
    public DecreeDTO save(DecreeDTO decreeDTO) {
        log.debug("Request to save Decree : {}", decreeDTO);
        Decree decree = decreeMapper.toEntity(decreeDTO);

        if (decreeDTO.getPdfFile() != null) {
            String filePath = FileTools.upload(
                decree.getPdfFile(),
                decree.getPdfFileContentType(),
                decreeDTO.getDecreeNo() + "_" + decreeDTO.getYear()
            );
            decree.setPdfFile(null);
            decree.setPdfFileContentType(decreeDTO.getPdfFileContentType());
            decree.setPdfFileUrl(filePath);
        }

        decree = decreeRepository.save(decree);
        return decreeMapper.toDto(decree);
    }

    /**
     * Partially update a decree.
     *
     * @param decreeDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DecreeDTO> partialUpdate(DecreeDTO decreeDTO) {
        log.debug("Request to partially update Decree : {}", decreeDTO);

        return decreeRepository
            .findById(decreeDTO.getId())
            .map(existingDecree -> {
                decreeMapper.partialUpdate(existingDecree, decreeDTO);

                return existingDecree;
            })
            .map(decreeRepository::save)
            .map(decreeMapper::toDto);
    }

    /**
     * Get all the decrees.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<DecreeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Decrees");
        return decreeRepository.findAll(pageable).map(decreeMapper::toDto);
    }

    /**
     * Get one decree by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DecreeDTO> findOne(Long id) {
        log.debug("Request to get Decree : {}", id);
        return decreeRepository.findById(id).map(decreeMapper::toDto);
    }

    /**
     * Delete the decree by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Decree : {}", id);
        decreeRepository.deleteById(id);
    }
}
