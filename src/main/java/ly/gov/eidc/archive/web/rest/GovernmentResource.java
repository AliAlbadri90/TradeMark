package ly.gov.eidc.archive.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import ly.gov.eidc.archive.repository.GovernmentRepository;
import ly.gov.eidc.archive.service.GovernmentQueryService;
import ly.gov.eidc.archive.service.GovernmentService;
import ly.gov.eidc.archive.service.criteria.GovernmentCriteria;
import ly.gov.eidc.archive.service.dto.GovernmentDTO;
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
 * REST controller for managing {@link ly.gov.eidc.archive.domain.Government}.
 */
@RestController
@RequestMapping("/api")
public class GovernmentResource {

    private final Logger log = LoggerFactory.getLogger(GovernmentResource.class);

    private static final String ENTITY_NAME = "government";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GovernmentService governmentService;

    private final GovernmentRepository governmentRepository;

    private final GovernmentQueryService governmentQueryService;

    public GovernmentResource(
        GovernmentService governmentService,
        GovernmentRepository governmentRepository,
        GovernmentQueryService governmentQueryService
    ) {
        this.governmentService = governmentService;
        this.governmentRepository = governmentRepository;
        this.governmentQueryService = governmentQueryService;
    }

    /**
     * {@code POST  /governments} : Create a new government.
     *
     * @param governmentDTO the governmentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new governmentDTO, or with status {@code 400 (Bad Request)} if the government has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/governments")
    public ResponseEntity<GovernmentDTO> createGovernment(@RequestBody GovernmentDTO governmentDTO) throws URISyntaxException {
        log.debug("REST request to save Government : {}", governmentDTO);
        if (governmentDTO.getId() != null) {
            throw new BadRequestAlertException("A new government cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GovernmentDTO result = governmentService.save(governmentDTO);
        return ResponseEntity
            .created(new URI("/api/governments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /governments/:id} : Updates an existing government.
     *
     * @param id            the id of the governmentDTO to save.
     * @param governmentDTO the governmentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated governmentDTO,
     * or with status {@code 400 (Bad Request)} if the governmentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the governmentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/governments/{id}")
    public ResponseEntity<GovernmentDTO> updateGovernment(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody GovernmentDTO governmentDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Government : {}, {}", id, governmentDTO);
        if (governmentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, governmentDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!governmentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        GovernmentDTO result = governmentService.save(governmentDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, governmentDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /governments/:id} : Partial updates given fields of an existing government, field will ignore if it is null
     *
     * @param id            the id of the governmentDTO to save.
     * @param governmentDTO the governmentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated governmentDTO,
     * or with status {@code 400 (Bad Request)} if the governmentDTO is not valid,
     * or with status {@code 404 (Not Found)} if the governmentDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the governmentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/governments/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<GovernmentDTO> partialUpdateGovernment(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody GovernmentDTO governmentDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Government partially : {}, {}", id, governmentDTO);
        if (governmentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, governmentDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!governmentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<GovernmentDTO> result = governmentService.partialUpdate(governmentDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, governmentDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /governments} : get all the governments.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of governments in body.
     */
    @GetMapping("/governments")
    public ResponseEntity<List<GovernmentDTO>> getAllGovernments(
        GovernmentCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get Governments by criteria: {}", criteria);
        Page<GovernmentDTO> page = governmentQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /governments/count} : count all the governments.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/governments/count")
    public ResponseEntity<Long> countGovernments(GovernmentCriteria criteria) {
        log.debug("REST request to count Governments by criteria: {}", criteria);
        return ResponseEntity.ok().body(governmentQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /governments/:id} : get the "id" government.
     *
     * @param id the id of the governmentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the governmentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/governments/{id}")
    public ResponseEntity<GovernmentDTO> getGovernment(@PathVariable Long id) {
        log.debug("REST request to get Government : {}", id);
        Optional<GovernmentDTO> governmentDTO = governmentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(governmentDTO);
    }

    /**
     * {@code DELETE  /governments/:id} : delete the "id" government.
     *
     * @param id the id of the governmentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/governments/{id}")
    public ResponseEntity<Void> deleteGovernment(@PathVariable Long id) {
        log.debug("REST request to delete Government : {}", id);
        governmentService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
