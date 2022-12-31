package ly.gov.eidc.archive.web.rest;

import static org.elasticsearch.index.query.QueryBuilders.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;
import ly.gov.eidc.archive.repository.TrademarkDecreeRepository;
import ly.gov.eidc.archive.service.TrademarkDecreeQueryService;
import ly.gov.eidc.archive.service.TrademarkDecreeService;
import ly.gov.eidc.archive.service.criteria.TrademarkDecreeCriteria;
import ly.gov.eidc.archive.service.dto.TrademarkDecreeDTO;
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
 * REST controller for managing {@link ly.gov.eidc.archive.domain.TrademarkDecree}.
 */
@RestController
@RequestMapping("/api")
public class TrademarkDecreeResource {

    private final Logger log = LoggerFactory.getLogger(TrademarkDecreeResource.class);

    private static final String ENTITY_NAME = "trademarkDecree";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TrademarkDecreeService trademarkDecreeService;

    private final TrademarkDecreeRepository trademarkDecreeRepository;

    private final TrademarkDecreeQueryService trademarkDecreeQueryService;

    public TrademarkDecreeResource(
        TrademarkDecreeService trademarkDecreeService,
        TrademarkDecreeRepository trademarkDecreeRepository,
        TrademarkDecreeQueryService trademarkDecreeQueryService
    ) {
        this.trademarkDecreeService = trademarkDecreeService;
        this.trademarkDecreeRepository = trademarkDecreeRepository;
        this.trademarkDecreeQueryService = trademarkDecreeQueryService;
    }

    /**
     * {@code POST  /trademark-decrees} : Create a new trademarkDecree.
     *
     * @param trademarkDecreeDTO the trademarkDecreeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new trademarkDecreeDTO, or with status {@code 400 (Bad Request)} if the trademarkDecree has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/trademark-decrees")
    public ResponseEntity<TrademarkDecreeDTO> createTrademarkDecree(@RequestBody TrademarkDecreeDTO trademarkDecreeDTO)
        throws URISyntaxException {
        log.debug("REST request to save TrademarkDecree : {}", trademarkDecreeDTO);
        if (trademarkDecreeDTO.getId() != null) {
            throw new BadRequestAlertException("A new trademarkDecree cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TrademarkDecreeDTO result = trademarkDecreeService.save(trademarkDecreeDTO);
        return ResponseEntity
            .created(new URI("/api/trademark-decrees/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /trademark-decrees/:id} : Updates an existing trademarkDecree.
     *
     * @param id                 the id of the trademarkDecreeDTO to save.
     * @param trademarkDecreeDTO the trademarkDecreeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated trademarkDecreeDTO,
     * or with status {@code 400 (Bad Request)} if the trademarkDecreeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the trademarkDecreeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/trademark-decrees/{id}")
    public ResponseEntity<TrademarkDecreeDTO> updateTrademarkDecree(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TrademarkDecreeDTO trademarkDecreeDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TrademarkDecree : {}, {}", id, trademarkDecreeDTO);
        if (trademarkDecreeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, trademarkDecreeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!trademarkDecreeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TrademarkDecreeDTO result = trademarkDecreeService.save(trademarkDecreeDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, trademarkDecreeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /trademark-decrees/:id} : Partial updates given fields of an existing trademarkDecree, field will ignore if it is null
     *
     * @param id                 the id of the trademarkDecreeDTO to save.
     * @param trademarkDecreeDTO the trademarkDecreeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated trademarkDecreeDTO,
     * or with status {@code 400 (Bad Request)} if the trademarkDecreeDTO is not valid,
     * or with status {@code 404 (Not Found)} if the trademarkDecreeDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the trademarkDecreeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/trademark-decrees/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TrademarkDecreeDTO> partialUpdateTrademarkDecree(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TrademarkDecreeDTO trademarkDecreeDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TrademarkDecree partially : {}, {}", id, trademarkDecreeDTO);
        if (trademarkDecreeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, trademarkDecreeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!trademarkDecreeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TrademarkDecreeDTO> result = trademarkDecreeService.partialUpdate(trademarkDecreeDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, trademarkDecreeDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /trademark-decrees} : get all the trademarkDecrees.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of trademarkDecrees in body.
     */
    @GetMapping("/trademark-decrees")
    public ResponseEntity<List<TrademarkDecreeDTO>> getAllTrademarkDecrees(
        TrademarkDecreeCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get TrademarkDecrees by criteria: {}", criteria);
        Page<TrademarkDecreeDTO> page = trademarkDecreeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /trademark-decrees/count} : count all the trademarkDecrees.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/trademark-decrees/count")
    public ResponseEntity<Long> countTrademarkDecrees(TrademarkDecreeCriteria criteria) {
        log.debug("REST request to count TrademarkDecrees by criteria: {}", criteria);
        return ResponseEntity.ok().body(trademarkDecreeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /trademark-decrees/:id} : get the "id" trademarkDecree.
     *
     * @param id the id of the trademarkDecreeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the trademarkDecreeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/trademark-decrees/{id}")
    public ResponseEntity<TrademarkDecreeDTO> getTrademarkDecree(@PathVariable Long id) {
        log.debug("REST request to get TrademarkDecree : {}", id);
        Optional<TrademarkDecreeDTO> trademarkDecreeDTO = trademarkDecreeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(trademarkDecreeDTO);
    }

    /**
     * {@code DELETE  /trademark-decrees/:id} : delete the "id" trademarkDecree.
     *
     * @param id the id of the trademarkDecreeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/trademark-decrees/{id}")
    public ResponseEntity<Void> deleteTrademarkDecree(@PathVariable Long id) {
        log.debug("REST request to delete TrademarkDecree : {}", id);
        trademarkDecreeService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code SEARCH  /_search/trademark-decrees?query=:query} : search for the trademarkDecree corresponding
     * to the query.
     *
     * @param query    the query of the trademarkDecree search.
     * @param pageable the pagination information.
     * @return the result of the search.
     */
    @GetMapping("/_search/trademark-decrees")
    public ResponseEntity<List<TrademarkDecreeDTO>> searchTrademarkDecrees(
        @RequestParam String query,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to search for a page of TrademarkDecrees for query {}", query);
        Page<TrademarkDecreeDTO> page = trademarkDecreeService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
