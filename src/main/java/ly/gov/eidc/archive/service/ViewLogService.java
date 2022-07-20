package ly.gov.eidc.archive.service;

import java.util.Optional;
import ly.gov.eidc.archive.domain.ViewLog;
import ly.gov.eidc.archive.repository.ViewLogRepository;
import ly.gov.eidc.archive.service.dto.ViewLogDTO;
import ly.gov.eidc.archive.service.mapper.ViewLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ViewLog}.
 */
@Service
@Transactional
public class ViewLogService {

    private final Logger log = LoggerFactory.getLogger(ViewLogService.class);

    private final ViewLogRepository viewLogRepository;

    private final ViewLogMapper viewLogMapper;

    public ViewLogService(ViewLogRepository viewLogRepository, ViewLogMapper viewLogMapper) {
        this.viewLogRepository = viewLogRepository;
        this.viewLogMapper = viewLogMapper;
    }

    /**
     * Save a viewLog.
     *
     * @param viewLogDTO the entity to save.
     * @return the persisted entity.
     */
    public ViewLogDTO save(ViewLogDTO viewLogDTO) {
        log.debug("Request to save ViewLog : {}", viewLogDTO);
        ViewLog viewLog = viewLogMapper.toEntity(viewLogDTO);
        viewLog = viewLogRepository.save(viewLog);
        return viewLogMapper.toDto(viewLog);
    }

    /**
     * Partially update a viewLog.
     *
     * @param viewLogDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ViewLogDTO> partialUpdate(ViewLogDTO viewLogDTO) {
        log.debug("Request to partially update ViewLog : {}", viewLogDTO);

        return viewLogRepository
            .findById(viewLogDTO.getId())
            .map(existingViewLog -> {
                viewLogMapper.partialUpdate(existingViewLog, viewLogDTO);

                return existingViewLog;
            })
            .map(viewLogRepository::save)
            .map(viewLogMapper::toDto);
    }

    /**
     * Get all the viewLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ViewLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ViewLogs");
        return viewLogRepository.findAll(pageable).map(viewLogMapper::toDto);
    }

    /**
     * Get one viewLog by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ViewLogDTO> findOne(Long id) {
        log.debug("Request to get ViewLog : {}", id);
        return viewLogRepository.findById(id).map(viewLogMapper::toDto);
    }

    /**
     * Delete the viewLog by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ViewLog : {}", id);
        viewLogRepository.deleteById(id);
    }

    public void newLog(String action, String entityId, String entityName) {
        ViewLogDTO viewLogDTO = new ViewLogDTO();
        viewLogDTO.setEntityId(entityId);
        viewLogDTO.setActionName(action);
        viewLogDTO.setEntityName(entityName);
        save(viewLogDTO);
    }
}
