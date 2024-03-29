package ly.gov.eidc.archive.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import ly.gov.eidc.archive.repository.DecreeTypeRepository;
import ly.gov.eidc.archive.service.DecreeTypeQueryService;
import ly.gov.eidc.archive.service.DecreeTypeService;
import ly.gov.eidc.archive.service.criteria.DecreeTypeCriteria;
import ly.gov.eidc.archive.service.dto.DecreeTypeDTO;
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
 * REST controller for managing {@link ly.gov.eidc.archive.domain.DecreeType}.
 */
@RestController
@RequestMapping("/api")
public class DecreeTypeResource {

    private final Logger log = LoggerFactory.getLogger(DecreeTypeResource.class);

    private static final String ENTITY_NAME = "decreeType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DecreeTypeService decreeTypeService;

    private final DecreeTypeRepository decreeTypeRepository;

    private final DecreeTypeQueryService decreeTypeQueryService;

    public DecreeTypeResource(
        DecreeTypeService decreeTypeService,
        DecreeTypeRepository decreeTypeRepository,
        DecreeTypeQueryService decreeTypeQueryService
    ) {
        this.decreeTypeService = decreeTypeService;
        this.decreeTypeRepository = decreeTypeRepository;
        this.decreeTypeQueryService = decreeTypeQueryService;
    }

    /**
     * {@code POST  /decree-types} : Create a new decreeType.
     *
     * @param decreeTypeDTO the decreeTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new decreeTypeDTO, or with status {@code 400 (Bad Request)} if the decreeType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/decree-types")
    public ResponseEntity<DecreeTypeDTO> createDecreeType(@RequestBody DecreeTypeDTO decreeTypeDTO) throws URISyntaxException {
        log.debug("REST request to save DecreeType : {}", decreeTypeDTO);
        if (decreeTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new decreeType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DecreeTypeDTO result = decreeTypeService.save(decreeTypeDTO);
        return ResponseEntity
            .created(new URI("/api/decree-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /decree-types/:id} : Updates an existing decreeType.
     *
     * @param id            the id of the decreeTypeDTO to save.
     * @param decreeTypeDTO the decreeTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated decreeTypeDTO,
     * or with status {@code 400 (Bad Request)} if the decreeTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the decreeTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/decree-types/{id}")
    public ResponseEntity<DecreeTypeDTO> updateDecreeType(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DecreeTypeDTO decreeTypeDTO
    ) throws URISyntaxException {
        log.debug("REST request to update DecreeType : {}, {}", id, decreeTypeDTO);
        if (decreeTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, decreeTypeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!decreeTypeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        DecreeTypeDTO result = decreeTypeService.save(decreeTypeDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, decreeTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /decree-types/:id} : Partial updates given fields of an existing decreeType, field will ignore if it is null
     *
     * @param id            the id of the decreeTypeDTO to save.
     * @param decreeTypeDTO the decreeTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated decreeTypeDTO,
     * or with status {@code 400 (Bad Request)} if the decreeTypeDTO is not valid,
     * or with status {@code 404 (Not Found)} if the decreeTypeDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the decreeTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/decree-types/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DecreeTypeDTO> partialUpdateDecreeType(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DecreeTypeDTO decreeTypeDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update DecreeType partially : {}, {}", id, decreeTypeDTO);
        if (decreeTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, decreeTypeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!decreeTypeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DecreeTypeDTO> result = decreeTypeService.partialUpdate(decreeTypeDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, decreeTypeDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /decree-types} : get all the decreeTypes.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of decreeTypes in body.
     */
    @GetMapping("/decree-types")
    public ResponseEntity<List<DecreeTypeDTO>> getAllDecreeTypes(
        DecreeTypeCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get DecreeTypes by criteria: {}", criteria);
        Page<DecreeTypeDTO> page = decreeTypeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /decree-types/count} : count all the decreeTypes.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/decree-types/count")
    public ResponseEntity<Long> countDecreeTypes(DecreeTypeCriteria criteria) {
        log.debug("REST request to count DecreeTypes by criteria: {}", criteria);
        return ResponseEntity.ok().body(decreeTypeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /decree-types/:id} : get the "id" decreeType.
     *
     * @param id the id of the decreeTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the decreeTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/decree-types/{id}")
    public ResponseEntity<DecreeTypeDTO> getDecreeType(@PathVariable Long id) {
        log.debug("REST request to get DecreeType : {}", id);
        Optional<DecreeTypeDTO> decreeTypeDTO = decreeTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(decreeTypeDTO);
    }

    /**
     * {@code DELETE  /decree-types/:id} : delete the "id" decreeType.
     *
     * @param id the id of the decreeTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/decree-types/{id}")
    public ResponseEntity<Void> deleteDecreeType(@PathVariable Long id) {
        log.debug("REST request to delete DecreeType : {}", id);
        decreeTypeService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
