package ly.gov.eidc.archive.service;

import static org.elasticsearch.index.query.QueryBuilders.*;

import java.util.Optional;
import ly.gov.eidc.archive.domain.TrademarkDecree;
import ly.gov.eidc.archive.repository.TrademarkDecreeRepository;
import ly.gov.eidc.archive.repository.search.TrademarkDecreeSearchRepository;
import ly.gov.eidc.archive.service.dto.TrademarkDecreeDTO;
import ly.gov.eidc.archive.service.mapper.TrademarkDecreeMapper;
import ly.gov.eidc.archive.service.util.FileTools;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TrademarkDecree}.
 */
@Service
@Transactional
public class TrademarkDecreeService {

    private final Logger log = LoggerFactory.getLogger(TrademarkDecreeService.class);

    private final TrademarkDecreeRepository trademarkDecreeRepository;

    private final TrademarkDecreeMapper trademarkDecreeMapper;

    private final TrademarkDecreeSearchRepository trademarkDecreeSearchRepository;

    public TrademarkDecreeService(
        TrademarkDecreeRepository trademarkDecreeRepository,
        TrademarkDecreeMapper trademarkDecreeMapper,
        TrademarkDecreeSearchRepository trademarkDecreeSearchRepository
    ) {
        this.trademarkDecreeRepository = trademarkDecreeRepository;
        this.trademarkDecreeMapper = trademarkDecreeMapper;
        this.trademarkDecreeSearchRepository = trademarkDecreeSearchRepository;
    }

    /**
     * Save a trademarkDecree.
     *
     * @param trademarkDecreeDTO the entity to save.
     * @return the persisted entity.
     */
    public TrademarkDecreeDTO save(TrademarkDecreeDTO trademarkDecreeDTO) {
        log.debug("Request to save TrademarkDecree : {}", trademarkDecreeDTO);
        TrademarkDecree trademarkDecree = trademarkDecreeMapper.toEntity(trademarkDecreeDTO);

        if (trademarkDecreeDTO.getPdfFile() != null) {
            String filePath = FileTools.upload(
                trademarkDecree.getPdfFile(),
                trademarkDecree.getPdfFileContentType(),
                "TM_" + trademarkDecreeDTO.getYear() + "_" + trademarkDecreeDTO.getDecreeNo()
            );
            trademarkDecree.setPdfFile(null);
            trademarkDecree.setPdfFileContentType(trademarkDecreeDTO.getPdfFileContentType());
            trademarkDecree.setPdfFileUrl(filePath);
        }

        if (trademarkDecreeDTO.getExtraPdfFile() != null) {
            String filePath = FileTools.upload(
                trademarkDecree.getExtraPdfFile(),
                trademarkDecree.getExtraPdfFileContentType(),
                "TM_" + trademarkDecreeDTO.getYear() + "_" + trademarkDecreeDTO.getDecreeNo() + "_AT"
            );
            trademarkDecree.setExtraPdfFile(null);
            trademarkDecree.setExtraPdfFileContentType(trademarkDecreeDTO.getExtraPdfFileContentType());
            trademarkDecree.setExtraPdfFileUrl(filePath);
        }

        trademarkDecree = trademarkDecreeRepository.save(trademarkDecree);
        TrademarkDecreeDTO result = trademarkDecreeMapper.toDto(trademarkDecree);
        trademarkDecreeSearchRepository.save(trademarkDecree);
        return result;
    }

    /**
     * Partially update a trademarkDecree.
     *
     * @param trademarkDecreeDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<TrademarkDecreeDTO> partialUpdate(TrademarkDecreeDTO trademarkDecreeDTO) {
        log.debug("Request to partially update TrademarkDecree : {}", trademarkDecreeDTO);

        return trademarkDecreeRepository
            .findById(trademarkDecreeDTO.getId())
            .map(existingTrademarkDecree -> {
                trademarkDecreeMapper.partialUpdate(existingTrademarkDecree, trademarkDecreeDTO);

                return existingTrademarkDecree;
            })
            .map(trademarkDecreeRepository::save)
            .map(savedTrademarkDecree -> {
                trademarkDecreeSearchRepository.save(savedTrademarkDecree);

                return savedTrademarkDecree;
            })
            .map(trademarkDecreeMapper::toDto);
    }

    /**
     * Get all the trademarkDecrees.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<TrademarkDecreeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TrademarkDecrees");
        return trademarkDecreeRepository.findAll(pageable).map(trademarkDecreeMapper::toDto);
    }

    /**
     * Get one trademarkDecree by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TrademarkDecreeDTO> findOne(Long id) {
        log.debug("Request to get TrademarkDecree : {}", id);
        return trademarkDecreeRepository.findById(id).map(trademarkDecreeMapper::toDto);
    }

    /**
     * Delete the trademarkDecree by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TrademarkDecree : {}", id);
        trademarkDecreeRepository.deleteById(id);
        trademarkDecreeSearchRepository.deleteById(id);
    }

    /**
     * Search for the trademarkDecree corresponding to the query.
     *
     * @param query    the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<TrademarkDecreeDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of TrademarkDecrees for query {}", query);
        var builder = new BoolQueryBuilder()
            .should(QueryBuilders.multiMatchQuery(query, "tradeMarkOwner", "applicantName", "trademarkArabic", "country"));

        var Nquery = new NativeSearchQueryBuilder().withQuery(builder).withPageable(pageable).build();

        return trademarkDecreeSearchRepository.search(Nquery, pageable).map(trademarkDecreeMapper::toDto);
    }

    public void reindex() {
        trademarkDecreeSearchRepository.deleteAll();
        trademarkDecreeSearchRepository.saveAll(trademarkDecreeRepository.findAll());
    }
}
