package ly.gov.eidc.archive.web.rest;

import static org.elasticsearch.index.query.QueryBuilders.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;
import ly.gov.eidc.archive.repository.TrademarkRegisteredRepository;
import ly.gov.eidc.archive.service.TrademarkRegisteredQueryService;
import ly.gov.eidc.archive.service.TrademarkRegisteredService;
import ly.gov.eidc.archive.service.criteria.TrademarkRegisteredCriteria;
import ly.gov.eidc.archive.service.dto.TrademarkRegisteredDTO;
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
 * REST controller for managing {@link ly.gov.eidc.archive.domain.TrademarkRegistered}.
 */
@RestController
@RequestMapping("/api")
public class TrademarkRegisteredResource {

    private final Logger log = LoggerFactory.getLogger(TrademarkRegisteredResource.class);

    private static final String ENTITY_NAME = "trademarkRegistered";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TrademarkRegisteredService trademarkRegisteredService;

    private final TrademarkRegisteredRepository trademarkRegisteredRepository;

    private final TrademarkRegisteredQueryService trademarkRegisteredQueryService;

    public TrademarkRegisteredResource(
        TrademarkRegisteredService trademarkRegisteredService,
        TrademarkRegisteredRepository trademarkRegisteredRepository,
        TrademarkRegisteredQueryService trademarkRegisteredQueryService
    ) {
        this.trademarkRegisteredService = trademarkRegisteredService;
        this.trademarkRegisteredRepository = trademarkRegisteredRepository;
        this.trademarkRegisteredQueryService = trademarkRegisteredQueryService;
    }

    /**
     * {@code POST  /trademark-registereds} : Create a new trademarkRegistered.
     *
     * @param trademarkRegisteredDTO the trademarkRegisteredDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new trademarkRegisteredDTO, or with status {@code 400 (Bad Request)} if the trademarkRegistered has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/trademark-registereds")
    public ResponseEntity<TrademarkRegisteredDTO> createTrademarkRegistered(@RequestBody TrademarkRegisteredDTO trademarkRegisteredDTO)
        throws URISyntaxException {
        log.debug("REST request to save TrademarkRegistered : {}", trademarkRegisteredDTO);
        if (trademarkRegisteredDTO.getId() != null) {
            throw new BadRequestAlertException("A new trademarkRegistered cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TrademarkRegisteredDTO result = trademarkRegisteredService.save(trademarkRegisteredDTO);
        return ResponseEntity
            .created(new URI("/api/trademark-registereds/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /trademark-registereds/:id} : Updates an existing trademarkRegistered.
     *
     * @param id the id of the trademarkRegisteredDTO to save.
     * @param trademarkRegisteredDTO the trademarkRegisteredDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated trademarkRegisteredDTO,
     * or with status {@code 400 (Bad Request)} if the trademarkRegisteredDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the trademarkRegisteredDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/trademark-registereds/{id}")
    public ResponseEntity<TrademarkRegisteredDTO> updateTrademarkRegistered(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TrademarkRegisteredDTO trademarkRegisteredDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TrademarkRegistered : {}, {}", id, trademarkRegisteredDTO);
        if (trademarkRegisteredDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, trademarkRegisteredDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!trademarkRegisteredRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TrademarkRegisteredDTO result = trademarkRegisteredService.save(trademarkRegisteredDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, trademarkRegisteredDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /trademark-registereds/:id} : Partial updates given fields of an existing trademarkRegistered, field will ignore if it is null
     *
     * @param id the id of the trademarkRegisteredDTO to save.
     * @param trademarkRegisteredDTO the trademarkRegisteredDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated trademarkRegisteredDTO,
     * or with status {@code 400 (Bad Request)} if the trademarkRegisteredDTO is not valid,
     * or with status {@code 404 (Not Found)} if the trademarkRegisteredDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the trademarkRegisteredDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/trademark-registereds/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TrademarkRegisteredDTO> partialUpdateTrademarkRegistered(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TrademarkRegisteredDTO trademarkRegisteredDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TrademarkRegistered partially : {}, {}", id, trademarkRegisteredDTO);
        if (trademarkRegisteredDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, trademarkRegisteredDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!trademarkRegisteredRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TrademarkRegisteredDTO> result = trademarkRegisteredService.partialUpdate(trademarkRegisteredDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, trademarkRegisteredDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /trademark-registereds} : get all the trademarkRegistereds.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of trademarkRegistereds in body.
     */
    @GetMapping("/trademark-registereds")
    public ResponseEntity<List<TrademarkRegisteredDTO>> getAllTrademarkRegistereds(
        TrademarkRegisteredCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get TrademarkRegistereds by criteria: {}", criteria);
        Page<TrademarkRegisteredDTO> page = trademarkRegisteredQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/public/trademark-registereds")
    public ResponseEntity<List<TrademarkRegisteredDTO>> getAllTrademarkRegisteredsPublic(
        TrademarkRegisteredCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get TrademarkRegistereds by criteria: {}", criteria);

        Page<TrademarkRegisteredDTO> page;
        //        if (criteria.getTrademarkArabic() != null) page =
        //            trademarkRegisteredService.search(criteria.getTrademarkArabic().getContains(), pageable); else
        page = trademarkRegisteredService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /trademark-registereds/count} : count all the trademarkRegistereds.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/trademark-registereds/count")
    public ResponseEntity<Long> countTrademarkRegistereds(TrademarkRegisteredCriteria criteria) {
        log.debug("REST request to count TrademarkRegistereds by criteria: {}", criteria);
        return ResponseEntity.ok().body(trademarkRegisteredQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /trademark-registereds/:id} : get the "id" trademarkRegistered.
     *
     * @param id the id of the trademarkRegisteredDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the trademarkRegisteredDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/trademark-registereds/{id}")
    public ResponseEntity<TrademarkRegisteredDTO> getTrademarkRegistered(@PathVariable Long id) {
        log.debug("REST request to get TrademarkRegistered : {}", id);
        Optional<TrademarkRegisteredDTO> trademarkRegisteredDTO = trademarkRegisteredService.findOne(id);
        return ResponseUtil.wrapOrNotFound(trademarkRegisteredDTO);
    }

    @GetMapping("/public/trademark-registereds/{id}")
    public ResponseEntity<TrademarkRegisteredDTO> getTrademarkRegisteredPublic(@PathVariable Long id) {
        log.debug("REST request to get TrademarkRegistered : {}", id);
        Optional<TrademarkRegisteredDTO> trademarkRegisteredDTO = trademarkRegisteredService.findOne(id);
        return ResponseUtil.wrapOrNotFound(trademarkRegisteredDTO);
    }

    /**
     * {@code DELETE  /trademark-registereds/:id} : delete the "id" trademarkRegistered.
     *
     * @param id the id of the trademarkRegisteredDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/trademark-registereds/{id}")
    public ResponseEntity<Void> deleteTrademarkRegistered(@PathVariable Long id) {
        log.debug("REST request to delete TrademarkRegistered : {}", id);
        trademarkRegisteredService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code SEARCH  /_search/trademark-registereds?query=:query} : search for the trademarkRegistered corresponding
     * to the query.
     *
     * @param query the query of the trademarkRegistered search.
     * @param pageable the pagination information.
     * @return the result of the search.
     */
    @GetMapping("/_search/trademark-registereds")
    public ResponseEntity<List<TrademarkRegisteredDTO>> searchTrademarkRegistereds(
        @RequestParam String query,
        @RequestParam(required = false) String searchType,
        @RequestParam(required = false) String selectedColumn,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to search for a page of TrademarkRegistereds for query {}", query);
        Page<TrademarkRegisteredDTO> page = trademarkRegisteredService.search(query, searchType, selectedColumn, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/public/_search/trademark-registereds")
    public ResponseEntity<List<TrademarkRegisteredDTO>> searchTrademarkRegisteredsPublic(
        @RequestParam String query,
        @RequestParam(required = false) String searchType,
        @RequestParam(required = false) String selectedColumn,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to search for a page of TrademarkRegistereds for query {}", query);
        Page<TrademarkRegisteredDTO> page = trademarkRegisteredService.search(query, searchType, selectedColumn, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/public/trademark-registereds/reindex")
    public void reindex() {
        trademarkRegisteredService.reindex();
    }
}
