package ly.gov.eidc.archive.web.rest;

import static org.elasticsearch.index.query.QueryBuilders.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;
import ly.gov.eidc.archive.repository.RegulationRepository;
import ly.gov.eidc.archive.service.RegulationQueryService;
import ly.gov.eidc.archive.service.RegulationService;
import ly.gov.eidc.archive.service.criteria.RegulationCriteria;
import ly.gov.eidc.archive.service.dto.RegulationDTO;
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
 * REST controller for managing {@link ly.gov.eidc.archive.domain.Regulation}.
 */
@RestController
@RequestMapping("/api")
public class RegulationResource {

    private final Logger log = LoggerFactory.getLogger(RegulationResource.class);

    private static final String ENTITY_NAME = "regulation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RegulationService regulationService;

    private final RegulationRepository regulationRepository;

    private final RegulationQueryService regulationQueryService;

    public RegulationResource(
        RegulationService regulationService,
        RegulationRepository regulationRepository,
        RegulationQueryService regulationQueryService
    ) {
        this.regulationService = regulationService;
        this.regulationRepository = regulationRepository;
        this.regulationQueryService = regulationQueryService;
    }

    /**
     * {@code POST  /regulations} : Create a new regulation.
     *
     * @param regulationDTO the regulationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new regulationDTO, or with status {@code 400 (Bad Request)} if the regulation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/regulations")
    public ResponseEntity<RegulationDTO> createRegulation(@RequestBody RegulationDTO regulationDTO) throws URISyntaxException {
        log.debug("REST request to save Regulation : {}", regulationDTO);
        if (regulationDTO.getId() != null) {
            throw new BadRequestAlertException("A new regulation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RegulationDTO result = regulationService.save(regulationDTO);
        return ResponseEntity
            .created(new URI("/api/regulations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /regulations/:id} : Updates an existing regulation.
     *
     * @param id the id of the regulationDTO to save.
     * @param regulationDTO the regulationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated regulationDTO,
     * or with status {@code 400 (Bad Request)} if the regulationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the regulationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/regulations/{id}")
    public ResponseEntity<RegulationDTO> updateRegulation(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RegulationDTO regulationDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Regulation : {}, {}", id, regulationDTO);
        if (regulationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, regulationDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!regulationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        RegulationDTO result = regulationService.save(regulationDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, regulationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /regulations/:id} : Partial updates given fields of an existing regulation, field will ignore if it is null
     *
     * @param id the id of the regulationDTO to save.
     * @param regulationDTO the regulationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated regulationDTO,
     * or with status {@code 400 (Bad Request)} if the regulationDTO is not valid,
     * or with status {@code 404 (Not Found)} if the regulationDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the regulationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/regulations/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<RegulationDTO> partialUpdateRegulation(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RegulationDTO regulationDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Regulation partially : {}, {}", id, regulationDTO);
        if (regulationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, regulationDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!regulationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RegulationDTO> result = regulationService.partialUpdate(regulationDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, regulationDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /regulations} : get all the regulations.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of regulations in body.
     */
    @GetMapping("/regulations")
    public ResponseEntity<List<RegulationDTO>> getAllRegulations(
        RegulationCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get Regulations by criteria: {}", criteria);
        Page<RegulationDTO> page = regulationQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /regulations/count} : count all the regulations.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/regulations/count")
    public ResponseEntity<Long> countRegulations(RegulationCriteria criteria) {
        log.debug("REST request to count Regulations by criteria: {}", criteria);
        return ResponseEntity.ok().body(regulationQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /regulations/:id} : get the "id" regulation.
     *
     * @param id the id of the regulationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the regulationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/regulations/{id}")
    public ResponseEntity<RegulationDTO> getRegulation(@PathVariable Long id) {
        log.debug("REST request to get Regulation : {}", id);
        Optional<RegulationDTO> regulationDTO = regulationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(regulationDTO);
    }

    /**
     * {@code DELETE  /regulations/:id} : delete the "id" regulation.
     *
     * @param id the id of the regulationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/regulations/{id}")
    public ResponseEntity<Void> deleteRegulation(@PathVariable Long id) {
        log.debug("REST request to delete Regulation : {}", id);
        regulationService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code SEARCH  /_search/regulations?query=:query} : search for the regulation corresponding
     * to the query.
     *
     * @param query the query of the regulation search.
     * @param pageable the pagination information.
     * @return the result of the search.
     */
    @GetMapping("/_search/regulations")
    public ResponseEntity<List<RegulationDTO>> searchRegulations(
        @RequestParam String query,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to search for a page of Regulations for query {}", query);
        Page<RegulationDTO> page = regulationService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
