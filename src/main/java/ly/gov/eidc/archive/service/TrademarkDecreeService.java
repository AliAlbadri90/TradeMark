package ly.gov.eidc.archive.service;

import static org.elasticsearch.index.query.QueryBuilders.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import ly.gov.eidc.archive.domain.Decree;
import ly.gov.eidc.archive.domain.TrademarkDecree;
import ly.gov.eidc.archive.repository.TrademarkDecreeRepository;
import ly.gov.eidc.archive.repository.search.TrademarkDecreeSearchRepository;
import ly.gov.eidc.archive.service.dto.DecreeReport;
import ly.gov.eidc.archive.service.dto.TrademarkDecreeDTO;
import ly.gov.eidc.archive.service.mapper.TrademarkDecreeMapper;
import ly.gov.eidc.archive.service.util.FileTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Transactional(readOnly = true)
    public List<TrademarkDecreeDTO> findAll() {
        log.debug("Request to get all TrademarkDecrees");
        return trademarkDecreeMapper.toDto(trademarkDecreeRepository.findAll());
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
        return trademarkDecreeSearchRepository.search(query, pageable).map(trademarkDecreeMapper::toDto);
    }

    public List<Object[]> getTrademarkDecreeYearLineChart() {
        return trademarkDecreeRepository.getTrademarkDecreeYearLineChart();
    }

    public List<Object[]> getCreatedByCount() {
        return trademarkDecreeRepository.getCreatedByCount();
    }

    public DecreeReport getReportByYear(Integer year) {
        List<Integer> decreeIntsList = new ArrayList<>();
        List<TrademarkDecree> trademarkDecrees = trademarkDecreeRepository.findAllByYearOrderByDecreeNoAsc(year);
        System.out.println("Size " + trademarkDecrees.size());
        String duplicateNumbers = "";
        int duplicate = 0;
        int noFile = 0;

        int text = 0;
        int noText = 0;

        String noFileNumbers = "";
        for (TrademarkDecree trademarkDecree : trademarkDecrees) {
            try {
                if (trademarkDecree.getApplicantName() == null) {
                    noText++;
                } else {
                    text++;
                }
                decreeIntsList.add(Integer.parseInt(trademarkDecree.getDecreeNo()));
                if (trademarkDecree.getPdfFileUrl() == null) {
                    noFile++;
                    noFileNumbers += trademarkDecree.getDecreeNo() + ",";
                }
            } catch (Exception ignored) {
                duplicateNumbers += trademarkDecree.getDecreeNo() + ",";
                duplicate++;
            }
        }

        int[] decreeInts = decreeIntsList.stream().mapToInt(i -> i).toArray();
        Arrays.sort(decreeInts);

        String missingNumbers = "";
        int count = 0;
        int missingCount = 0;
        for (int i = decreeInts[0]; i <= decreeInts[decreeInts.length - 1]; i++) {
            if (decreeInts[count] == i) {
                count++;
            } else {
                missingCount++;
                missingNumbers += i + ",";
            }
        }

        int max = Arrays.stream(decreeInts).max().getAsInt();

        int min = Arrays.stream(decreeInts).min().getAsInt();

        DecreeReport decreeReport = new DecreeReport();
        decreeReport.setYear(year);
        decreeReport.setMissingCount(missingCount);
        decreeReport.setMissingNumbers(missingNumbers);
        decreeReport.setTotalCount(decreeInts.length);
        decreeReport.setFirstDecree(min);
        decreeReport.setLastDecree(max);
        decreeReport.setDuplicate(duplicate);
        decreeReport.setDuplicateNumbers(duplicateNumbers);
        decreeReport.setNoFileCount(noFile);
        decreeReport.setNoFileNumbers(noFileNumbers);
        decreeReport.setTextCount(text);
        decreeReport.setNoTextCount(noText);

        trademarkDecrees.forEach(decree -> {
            try {
                if (Integer.parseInt(decree.getDecreeNo()) == min) {
                    decreeReport.setFirstDecreeDate(decree.getDecreeDate() != null ? decree.getDecreeDate().toString() : "");
                }
                if (Integer.parseInt(decree.getDecreeNo()) == max) {
                    decreeReport.setLastDecreeDate(decree.getDecreeDate() != null ? decree.getDecreeDate().toString() : "");
                }
            } catch (Exception ignored) {}
        });

        return decreeReport;
    }

    public List<String> findAllYears() {
        return trademarkDecreeRepository.findAllYears();
    }
}
