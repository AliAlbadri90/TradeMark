package ly.gov.eidc.archive.service;

import static org.elasticsearch.index.query.QueryBuilders.*;

import java.util.Optional;
import ly.gov.eidc.archive.domain.Regulation;
import ly.gov.eidc.archive.repository.RegulationRepository;
import ly.gov.eidc.archive.repository.search.RegulationSearchRepository;
import ly.gov.eidc.archive.service.dto.RegulationDTO;
import ly.gov.eidc.archive.service.mapper.RegulationMapper;
import ly.gov.eidc.archive.service.util.FileTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Regulation}.
 */
@Service
@Transactional
public class RegulationService {

    private final Logger log = LoggerFactory.getLogger(RegulationService.class);

    private final RegulationRepository regulationRepository;

    private final RegulationMapper regulationMapper;

    private final RegulationSearchRepository regulationSearchRepository;

    public RegulationService(
        RegulationRepository regulationRepository,
        RegulationMapper regulationMapper,
        RegulationSearchRepository regulationSearchRepository
    ) {
        this.regulationRepository = regulationRepository;
        this.regulationMapper = regulationMapper;
        this.regulationSearchRepository = regulationSearchRepository;
    }

    /**
     * Save a regulation.
     *
     * @param regulationDTO the entity to save.
     * @return the persisted entity.
     */
    public RegulationDTO save(RegulationDTO regulationDTO) {
        log.debug("Request to save Regulation : {}", regulationDTO);
        Regulation regulation = regulationMapper.toEntity(regulationDTO);

        if (regulationDTO.getFilePdf() != null) {
            String filePath = FileTools.upload(
                regulation.getFilePdf(),
                regulation.getFilePdfContentType(),
                regulationDTO.getYear().toString()
            );
            regulation.setFilePdf(null);
            regulation.setFilePdfContentType(regulationDTO.getFilePdfContentType());
            regulation.setFilePdfUrl(filePath);
        }

        if (regulationDTO.getFileWord() != null) {
            String filePath = FileTools.upload(
                regulation.getFileWord(),
                regulation.getFileWordContentType(),
                regulationDTO.getYear().toString()
            );
            regulation.setFileWord(null);
            regulation.setFileWordContentType(regulationDTO.getFileWordContentType());
            regulation.setFileWordUrl(filePath);
        }

        regulation = regulationRepository.save(regulation);
        RegulationDTO result = regulationMapper.toDto(regulation);
        regulationSearchRepository.save(regulation);
        return result;
    }

    /**
     * Partially update a regulation.
     *
     * @param regulationDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<RegulationDTO> partialUpdate(RegulationDTO regulationDTO) {
        log.debug("Request to partially update Regulation : {}", regulationDTO);

        return regulationRepository
            .findById(regulationDTO.getId())
            .map(existingRegulation -> {
                regulationMapper.partialUpdate(existingRegulation, regulationDTO);

                return existingRegulation;
            })
            .map(regulationRepository::save)
            .map(savedRegulation -> {
                regulationSearchRepository.save(savedRegulation);

                return savedRegulation;
            })
            .map(regulationMapper::toDto);
    }

    /**
     * Get all the regulations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<RegulationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Regulations");
        return regulationRepository.findAll(pageable).map(regulationMapper::toDto);
    }

    /**
     * Get one regulation by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<RegulationDTO> findOne(Long id) {
        log.debug("Request to get Regulation : {}", id);
        return regulationRepository.findById(id).map(regulationMapper::toDto);
    }

    /**
     * Delete the regulation by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Regulation : {}", id);
        regulationRepository.deleteById(id);
        regulationSearchRepository.deleteById(id);
    }

    /**
     * Search for the regulation corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<RegulationDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Regulations for query {}", query);
        return regulationSearchRepository.search(query, pageable).map(regulationMapper::toDto);
    }
}
