package ly.gov.eidc.archive.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import ly.gov.eidc.archive.repository.DecreeCategoryRepository;
import ly.gov.eidc.archive.service.DecreeCategoryQueryService;
import ly.gov.eidc.archive.service.DecreeCategoryService;
import ly.gov.eidc.archive.service.criteria.DecreeCategoryCriteria;
import ly.gov.eidc.archive.service.dto.DecreeCategoryDTO;
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
 * REST controller for managing {@link ly.gov.eidc.archive.domain.DecreeCategory}.
 */
@RestController
@RequestMapping("/api")
public class DecreeCategoryResource {

    private final Logger log = LoggerFactory.getLogger(DecreeCategoryResource.class);

    private static final String ENTITY_NAME = "decreeCategory";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DecreeCategoryService decreeCategoryService;

    private final DecreeCategoryRepository decreeCategoryRepository;

    private final DecreeCategoryQueryService decreeCategoryQueryService;

    public DecreeCategoryResource(
        DecreeCategoryService decreeCategoryService,
        DecreeCategoryRepository decreeCategoryRepository,
        DecreeCategoryQueryService decreeCategoryQueryService
    ) {
        this.decreeCategoryService = decreeCategoryService;
        this.decreeCategoryRepository = decreeCategoryRepository;
        this.decreeCategoryQueryService = decreeCategoryQueryService;
    }

    /**
     * {@code POST  /decree-categories} : Create a new decreeCategory.
     *
     * @param decreeCategoryDTO the decreeCategoryDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new decreeCategoryDTO, or with status {@code 400 (Bad Request)} if the decreeCategory has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/decree-categories")
    public ResponseEntity<DecreeCategoryDTO> createDecreeCategory(@RequestBody DecreeCategoryDTO decreeCategoryDTO)
        throws URISyntaxException {
        log.debug("REST request to save DecreeCategory : {}", decreeCategoryDTO);
        if (decreeCategoryDTO.getId() != null) {
            throw new BadRequestAlertException("A new decreeCategory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DecreeCategoryDTO result = decreeCategoryService.save(decreeCategoryDTO);
        return ResponseEntity
            .created(new URI("/api/decree-categories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /decree-categories/:id} : Updates an existing decreeCategory.
     *
     * @param id                the id of the decreeCategoryDTO to save.
     * @param decreeCategoryDTO the decreeCategoryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated decreeCategoryDTO,
     * or with status {@code 400 (Bad Request)} if the decreeCategoryDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the decreeCategoryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/decree-categories/{id}")
    public ResponseEntity<DecreeCategoryDTO> updateDecreeCategory(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DecreeCategoryDTO decreeCategoryDTO
    ) throws URISyntaxException {
        log.debug("REST request to update DecreeCategory : {}, {}", id, decreeCategoryDTO);
        if (decreeCategoryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, decreeCategoryDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!decreeCategoryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        DecreeCategoryDTO result = decreeCategoryService.save(decreeCategoryDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, decreeCategoryDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /decree-categories/:id} : Partial updates given fields of an existing decreeCategory, field will ignore if it is null
     *
     * @param id                the id of the decreeCategoryDTO to save.
     * @param decreeCategoryDTO the decreeCategoryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated decreeCategoryDTO,
     * or with status {@code 400 (Bad Request)} if the decreeCategoryDTO is not valid,
     * or with status {@code 404 (Not Found)} if the decreeCategoryDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the decreeCategoryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/decree-categories/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DecreeCategoryDTO> partialUpdateDecreeCategory(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DecreeCategoryDTO decreeCategoryDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update DecreeCategory partially : {}, {}", id, decreeCategoryDTO);
        if (decreeCategoryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, decreeCategoryDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!decreeCategoryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DecreeCategoryDTO> result = decreeCategoryService.partialUpdate(decreeCategoryDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, decreeCategoryDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /decree-categories} : get all the decreeCategories.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of decreeCategories in body.
     */
    @GetMapping("/decree-categories")
    public ResponseEntity<List<DecreeCategoryDTO>> getAllDecreeCategories(
        DecreeCategoryCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get DecreeCategories by criteria: {}", criteria);
        Page<DecreeCategoryDTO> page = decreeCategoryQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /decree-categories/count} : count all the decreeCategories.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/decree-categories/count")
    public ResponseEntity<Long> countDecreeCategories(DecreeCategoryCriteria criteria) {
        log.debug("REST request to count DecreeCategories by criteria: {}", criteria);
        return ResponseEntity.ok().body(decreeCategoryQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /decree-categories/:id} : get the "id" decreeCategory.
     *
     * @param id the id of the decreeCategoryDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the decreeCategoryDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/decree-categories/{id}")
    public ResponseEntity<DecreeCategoryDTO> getDecreeCategory(@PathVariable Long id) {
        log.debug("REST request to get DecreeCategory : {}", id);
        Optional<DecreeCategoryDTO> decreeCategoryDTO = decreeCategoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(decreeCategoryDTO);
    }

    /**
     * {@code DELETE  /decree-categories/:id} : delete the "id" decreeCategory.
     *
     * @param id the id of the decreeCategoryDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/decree-categories/{id}")
    public ResponseEntity<Void> deleteDecreeCategory(@PathVariable Long id) {
        log.debug("REST request to delete DecreeCategory : {}", id);
        decreeCategoryService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
