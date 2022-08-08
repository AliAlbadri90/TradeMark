package ly.gov.eidc.archive.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import ly.gov.eidc.archive.repository.MinisterRepository;
import ly.gov.eidc.archive.service.DecreeQueryService;
import ly.gov.eidc.archive.service.MinisterQueryService;
import ly.gov.eidc.archive.service.MinisterService;
import ly.gov.eidc.archive.service.criteria.DecreeCriteria;
import ly.gov.eidc.archive.service.criteria.MinisterCriteria;
import ly.gov.eidc.archive.service.dto.MinisterDTO;
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
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link ly.gov.eidc.archive.domain.Minister}.
 */
@RestController
@RequestMapping("/api")
public class MinisterResource {

    private final Logger log = LoggerFactory.getLogger(MinisterResource.class);

    private static final String ENTITY_NAME = "minister";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MinisterService ministerService;

    private final MinisterRepository ministerRepository;

    private final MinisterQueryService ministerQueryService;

    private final DecreeQueryService decreeQueryService;

    public MinisterResource(
        MinisterService ministerService,
        MinisterRepository ministerRepository,
        MinisterQueryService ministerQueryService,
        DecreeQueryService decreeQueryService
    ) {
        this.ministerService = ministerService;
        this.ministerRepository = ministerRepository;
        this.ministerQueryService = ministerQueryService;
        this.decreeQueryService = decreeQueryService;
    }

    /**
     * {@code POST  /ministers} : Create a new minister.
     *
     * @param ministerDTO the ministerDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ministerDTO, or with status {@code 400 (Bad Request)} if the minister has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ministers")
    public ResponseEntity<MinisterDTO> createMinister(@RequestBody MinisterDTO ministerDTO) throws URISyntaxException {
        log.debug("REST request to save Minister : {}", ministerDTO);
        if (ministerDTO.getId() != null) {
            throw new BadRequestAlertException("A new minister cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MinisterDTO result = ministerService.save(ministerDTO);
        return ResponseEntity
            .created(new URI("/api/ministers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ministers/:id} : Updates an existing minister.
     *
     * @param id          the id of the ministerDTO to save.
     * @param ministerDTO the ministerDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ministerDTO,
     * or with status {@code 400 (Bad Request)} if the ministerDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ministerDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ministers/{id}")
    public ResponseEntity<MinisterDTO> updateMinister(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody MinisterDTO ministerDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Minister : {}, {}", id, ministerDTO);
        if (ministerDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, ministerDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!ministerRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        MinisterDTO result = ministerService.save(ministerDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, ministerDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /ministers/:id} : Partial updates given fields of an existing minister, field will ignore if it is null
     *
     * @param id          the id of the ministerDTO to save.
     * @param ministerDTO the ministerDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ministerDTO,
     * or with status {@code 400 (Bad Request)} if the ministerDTO is not valid,
     * or with status {@code 404 (Not Found)} if the ministerDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the ministerDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/ministers/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<MinisterDTO> partialUpdateMinister(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody MinisterDTO ministerDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Minister partially : {}, {}", id, ministerDTO);
        if (ministerDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, ministerDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!ministerRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<MinisterDTO> result = ministerService.partialUpdate(ministerDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, ministerDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /ministers} : get all the ministers.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ministers in body.
     */
    @GetMapping("/ministers")
    public ResponseEntity<List<MinisterDTO>> getAllMinisters(
        MinisterCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get Ministers by criteria: {}", criteria);
        Page<MinisterDTO> page = ministerQueryService.findByCriteria(criteria, pageable);
        DecreeCriteria decreeCriteria = new DecreeCriteria();
        LongFilter longFilter = new LongFilter();
        page.forEach(ministerDTO -> {
            longFilter.setEquals(ministerDTO.getId());
            decreeCriteria.setMinisterId(longFilter);
            ministerDTO.setDecreeCount(decreeQueryService.countByCriteria(decreeCriteria));
        });
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /ministers/count} : count all the ministers.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/ministers/count")
    public ResponseEntity<Long> countMinisters(MinisterCriteria criteria) {
        log.debug("REST request to count Ministers by criteria: {}", criteria);
        return ResponseEntity.ok().body(ministerQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /ministers/:id} : get the "id" minister.
     *
     * @param id the id of the ministerDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ministerDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ministers/{id}")
    public ResponseEntity<MinisterDTO> getMinister(@PathVariable Long id) {
        log.debug("REST request to get Minister : {}", id);
        Optional<MinisterDTO> ministerDTO = ministerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ministerDTO);
    }

    /**
     * {@code DELETE  /ministers/:id} : delete the "id" minister.
     *
     * @param id the id of the ministerDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ministers/{id}")
    public ResponseEntity<Void> deleteMinister(@PathVariable Long id) {
        log.debug("REST request to delete Minister : {}", id);
        ministerService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }

    @GetMapping("/public/ministers")
    public ResponseEntity<List<MinisterDTO>> getAllMinistersPublic(
        MinisterCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        Page<MinisterDTO> page = ministerQueryService.findByCriteria(criteria, pageable);
        DecreeCriteria decreeCriteria = new DecreeCriteria();
        LongFilter longFilter = new LongFilter();
        page.forEach(ministerDTO -> {
            longFilter.setEquals(ministerDTO.getId());
            decreeCriteria.setMinisterId(longFilter);
            ministerDTO.setDecreeCount(decreeQueryService.countByCriteria(decreeCriteria));
        });
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
