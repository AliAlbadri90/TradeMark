package ly.gov.eidc.archive.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import ly.gov.eidc.archive.repository.ViewLogRepository;
import ly.gov.eidc.archive.service.ViewLogQueryService;
import ly.gov.eidc.archive.service.ViewLogService;
import ly.gov.eidc.archive.service.criteria.ViewLogCriteria;
import ly.gov.eidc.archive.service.dto.ViewLogDTO;
import ly.gov.eidc.archive.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link ly.gov.eidc.archive.domain.ViewLog}.
 */
@RestController
@RequestMapping("/api")
public class ViewLogResource {

    private final Logger log = LoggerFactory.getLogger(ViewLogResource.class);

    private static final String ENTITY_NAME = "viewLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ViewLogService viewLogService;

    private final ViewLogRepository viewLogRepository;

    private final ViewLogQueryService viewLogQueryService;

    public ViewLogResource(ViewLogService viewLogService, ViewLogRepository viewLogRepository, ViewLogQueryService viewLogQueryService) {
        this.viewLogService = viewLogService;
        this.viewLogRepository = viewLogRepository;
        this.viewLogQueryService = viewLogQueryService;
    }

    /**
     * {@code POST  /view-logs} : Create a new viewLog.
     *
     * @param viewLogDTO the viewLogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new viewLogDTO, or with status {@code 400 (Bad Request)} if the viewLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/view-logs")
    public ResponseEntity<ViewLogDTO> createViewLog(@RequestBody ViewLogDTO viewLogDTO) throws URISyntaxException {
        log.debug("REST request to save ViewLog : {}", viewLogDTO);
        if (viewLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new viewLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ViewLogDTO result = viewLogService.save(viewLogDTO);
        return ResponseEntity
            .created(new URI("/api/view-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /view-logs/:id} : Updates an existing viewLog.
     *
     * @param id         the id of the viewLogDTO to save.
     * @param viewLogDTO the viewLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated viewLogDTO,
     * or with status {@code 400 (Bad Request)} if the viewLogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the viewLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/view-logs/{id}")
    public ResponseEntity<ViewLogDTO> updateViewLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ViewLogDTO viewLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ViewLog : {}, {}", id, viewLogDTO);
        if (viewLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, viewLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!viewLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ViewLogDTO result = viewLogService.save(viewLogDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, viewLogDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /view-logs/:id} : Partial updates given fields of an existing viewLog, field will ignore if it is null
     *
     * @param id         the id of the viewLogDTO to save.
     * @param viewLogDTO the viewLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated viewLogDTO,
     * or with status {@code 400 (Bad Request)} if the viewLogDTO is not valid,
     * or with status {@code 404 (Not Found)} if the viewLogDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the viewLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/view-logs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ViewLogDTO> partialUpdateViewLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ViewLogDTO viewLogDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ViewLog partially : {}, {}", id, viewLogDTO);
        if (viewLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, viewLogDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!viewLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ViewLogDTO> result = viewLogService.partialUpdate(viewLogDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, viewLogDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /view-logs} : get all the viewLogs.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of viewLogs in body.
     */
    @GetMapping("/view-logs")
    public ResponseEntity<List<ViewLogDTO>> getAllViewLogs(
        ViewLogCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get ViewLogs by criteria: {}", criteria);
        Page<ViewLogDTO> page = viewLogQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /view-logs/count} : count all the viewLogs.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/view-logs/count")
    public ResponseEntity<Long> countViewLogs(ViewLogCriteria criteria) {
        log.debug("REST request to count ViewLogs by criteria: {}", criteria);
        return ResponseEntity.ok().body(viewLogQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /view-logs/:id} : get the "id" viewLog.
     *
     * @param id the id of the viewLogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the viewLogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/view-logs/{id}")
    public ResponseEntity<ViewLogDTO> getViewLog(@PathVariable Long id) {
        log.debug("REST request to get ViewLog : {}", id);
        Optional<ViewLogDTO> viewLogDTO = viewLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(viewLogDTO);
    }

    /**
     * {@code DELETE  /view-logs/:id} : delete the "id" viewLog.
     *
     * @param id the id of the viewLogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/view-logs/{id}")
    public ResponseEntity<Void> deleteViewLog(@PathVariable Long id) {
        log.debug("REST request to delete ViewLog : {}", id);
        viewLogService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
