package ly.gov.eidc.archive.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import ly.gov.eidc.archive.repository.DecreeRepository;
import ly.gov.eidc.archive.service.DecreeQueryService;
import ly.gov.eidc.archive.service.DecreeService;
import ly.gov.eidc.archive.service.criteria.DecreeCriteria;
import ly.gov.eidc.archive.service.criteria.MinisterCriteria;
import ly.gov.eidc.archive.service.dto.DecreeDTO;
import ly.gov.eidc.archive.service.dto.DecreeReport;
import ly.gov.eidc.archive.service.dto.MinisterDTO;
import ly.gov.eidc.archive.service.util.FileTools;
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
 * REST controller for managing {@link ly.gov.eidc.archive.domain.Decree}.
 */
@RestController
@RequestMapping("/api")
public class DecreeResource {

    private final Logger log = LoggerFactory.getLogger(DecreeResource.class);

    private static final String ENTITY_NAME = "decree";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DecreeService decreeService;

    private final DecreeRepository decreeRepository;

    private final DecreeQueryService decreeQueryService;

    public DecreeResource(DecreeService decreeService, DecreeRepository decreeRepository, DecreeQueryService decreeQueryService) {
        this.decreeService = decreeService;
        this.decreeRepository = decreeRepository;
        this.decreeQueryService = decreeQueryService;
    }

    /**
     * {@code POST  /decrees} : Create a new decree.
     *
     * @param decreeDTO the decreeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new decreeDTO, or with status {@code 400 (Bad Request)} if the decree has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/decrees")
    public ResponseEntity<DecreeDTO> createDecree(@RequestBody DecreeDTO decreeDTO) throws URISyntaxException {
        log.debug("REST request to save Decree : {}", decreeDTO);
        if (decreeDTO.getId() != null) {
            throw new BadRequestAlertException("A new decree cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DecreeDTO result = decreeService.save(decreeDTO);
        return ResponseEntity
            .created(new URI("/api/decrees/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /decrees/:id} : Updates an existing decree.
     *
     * @param id        the id of the decreeDTO to save.
     * @param decreeDTO the decreeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated decreeDTO,
     * or with status {@code 400 (Bad Request)} if the decreeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the decreeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/decrees/{id}")
    public ResponseEntity<DecreeDTO> updateDecree(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DecreeDTO decreeDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Decree : {}, {}", id, decreeDTO);
        if (decreeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, decreeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!decreeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        DecreeDTO result = decreeService.save(decreeDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, decreeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /decrees/:id} : Partial updates given fields of an existing decree, field will ignore if it is null
     *
     * @param id        the id of the decreeDTO to save.
     * @param decreeDTO the decreeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated decreeDTO,
     * or with status {@code 400 (Bad Request)} if the decreeDTO is not valid,
     * or with status {@code 404 (Not Found)} if the decreeDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the decreeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/decrees/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DecreeDTO> partialUpdateDecree(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DecreeDTO decreeDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Decree partially : {}, {}", id, decreeDTO);
        if (decreeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, decreeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!decreeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DecreeDTO> result = decreeService.partialUpdate(decreeDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, decreeDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /decrees} : get all the decrees.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of decrees in body.
     */
    @GetMapping("/decrees")
    public ResponseEntity<List<DecreeDTO>> getAllDecrees(
        DecreeCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get Decrees by criteria: {}", criteria);

        Page<DecreeDTO> page = decreeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /decrees/count} : count all the decrees.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/decrees/count")
    public ResponseEntity<Long> countDecrees(DecreeCriteria criteria) {
        log.debug("REST request to count Decrees by criteria: {}", criteria);
        return ResponseEntity.ok().body(decreeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /decrees/:id} : get the "id" decree.
     *
     * @param id the id of the decreeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the decreeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/decrees/{id}")
    public ResponseEntity<DecreeDTO> getDecree(@PathVariable Long id) {
        log.debug("REST request to get Decree : {}", id);
        Optional<DecreeDTO> decreeDTO = decreeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(decreeDTO);
    }

    /**
     * {@code DELETE  /decrees/:id} : delete the "id" decree.
     *
     * @param id the id of the decreeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/decrees/{id}")
    public ResponseEntity<Void> deleteDecree(@PathVariable Long id) {
        log.debug("REST request to delete Decree : {}", id);
        decreeService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }

    @GetMapping("/decrees/decree-line-chart")
    public ResponseEntity<List<Object[]>> getDecreeYearLineChart() {
        return ResponseEntity.ok().body(decreeRepository.getDecreeYearLineChart());
    }

    @GetMapping("/decrees/report/{year}/{ministerId}")
    public ResponseEntity<DecreeReport> getDecreeReport(@PathVariable Integer year, @PathVariable Long ministerId) {
        return ResponseEntity.ok().body(decreeService.getReportByYearAndMinisterId(year, ministerId));
    }

    @GetMapping("/decrees/recheck-files")
    public void recheckFiles() {
        decreeService
            .findAll()
            .forEach(decreeDTO -> {
                if (FileTools.download(decreeDTO.getPdfFileUrl()) == null) decreeDTO.setPdfFileUrl(null);
                decreeService.save(decreeDTO);
            });
    }

    @GetMapping("/decrees/ministers/{year}")
    public ResponseEntity<List<MinisterDTO>> getAllMinisters(@PathVariable Integer year) {
        List<MinisterDTO> list = decreeService.findMinistersByDecreeYear(year);
        return ResponseEntity.ok().body(list);
    }
}
