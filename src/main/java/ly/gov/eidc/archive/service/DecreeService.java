package ly.gov.eidc.archive.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.validation.constraints.Min;
import ly.gov.eidc.archive.domain.Decree;
import ly.gov.eidc.archive.repository.DecreeRepository;
import ly.gov.eidc.archive.service.dto.DecreeDTO;
import ly.gov.eidc.archive.service.dto.DecreeReport;
import ly.gov.eidc.archive.service.dto.MinisterDTO;
import ly.gov.eidc.archive.service.mapper.DecreeMapper;
import ly.gov.eidc.archive.service.util.FileTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Decree}.
 */
@Service
@Transactional
public class DecreeService {

    private final Logger log = LoggerFactory.getLogger(DecreeService.class);

    private final DecreeRepository decreeRepository;

    private final DecreeMapper decreeMapper;

    public DecreeService(DecreeRepository decreeRepository, DecreeMapper decreeMapper) {
        this.decreeRepository = decreeRepository;
        this.decreeMapper = decreeMapper;
    }

    /**
     * Save a decree.
     *
     * @param decreeDTO the entity to save.
     * @return the persisted entity.
     */
    public DecreeDTO save(DecreeDTO decreeDTO) {
        log.debug("Request to save Decree : {}", decreeDTO);
        Decree decree = decreeMapper.toEntity(decreeDTO);

        if (decreeDTO.getPdfFile() != null) {
            String filePath = FileTools.upload(
                decree.getPdfFile(),
                decree.getPdfFileContentType(),
                decreeDTO.getYear() + "_" + decreeDTO.getMinister().getId() + "_" + decreeDTO.getDecreeNo()
            );
            decree.setPdfFile(null);
            decree.setPdfFileContentType(decreeDTO.getPdfFileContentType());
            decree.setPdfFileUrl(filePath);
        }

        if (decreeDTO.getExtraPdfFile() != null) {
            String filePath = FileTools.upload(
                decree.getExtraPdfFile(),
                decree.getExtraPdfFileContentType(),
                decreeDTO.getYear() + "_" + decreeDTO.getMinister().getId() + "_" + decreeDTO.getDecreeNo() + "_AT"
            );
            decree.setExtraPdfFile(null);
            decree.setExtraPdfFileContentType(decreeDTO.getExtraPdfFileContentType());
            decree.setExtraPdfFileUrl(filePath);
        }

        decree = decreeRepository.save(decree);
        return decreeMapper.toDto(decree);
    }

    /**
     * Partially update a decree.
     *
     * @param decreeDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DecreeDTO> partialUpdate(DecreeDTO decreeDTO) {
        log.debug("Request to partially update Decree : {}", decreeDTO);

        return decreeRepository
            .findById(decreeDTO.getId())
            .map(existingDecree -> {
                decreeMapper.partialUpdate(existingDecree, decreeDTO);

                return existingDecree;
            })
            .map(decreeRepository::save)
            .map(decreeMapper::toDto);
    }

    /**
     * Get all the decrees.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<DecreeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Decrees");
        return decreeRepository.findAll(pageable).map(decreeMapper::toDto);
    }

    @Transactional(readOnly = true)
    public List<DecreeDTO> findAll() {
        return decreeMapper.toDto(decreeRepository.findAll());
    }

    /**
     * Get one decree by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DecreeDTO> findOne(Long id) {
        log.debug("Request to get Decree : {}", id);
        return decreeRepository.findById(id).map(decreeMapper::toDto);
    }

    /**
     * Delete the decree by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Decree : {}", id);
        decreeRepository.deleteById(id);
    }

    public List<Object[]> getDecreeYearLineChart() {
        return decreeRepository.getDecreeYearLineChart();
    }

    public DecreeReport getReportByYearAndMinisterId(Integer year, Long ministerId) {
        System.out.println(year + " " + ministerId);
        List<Integer> decreeIntsList = new ArrayList<>();
        List<Decree> decrees = decreeRepository.findAllByYearAndMinisterIdOrderByDecreeNoAsc(year, ministerId);
        System.out.println("Size " + decrees.size());

        for (Decree decree : decrees) {
            try {
                decreeIntsList.add(Integer.parseInt(decree.getDecreeNo()));
            } catch (Exception ignored) {}
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
        System.out.println("missingNumbers " + missingNumbers);
        System.out.println("missingCount " + missingCount);

        int max = Arrays.stream(decreeInts).max().getAsInt();

        int min = Arrays.stream(decreeInts).min().getAsInt();

        DecreeReport decreeReport = new DecreeReport();
        decreeReport.setYear(year);
        decreeReport.setMissingCount(missingCount);
        decreeReport.setMissingNumbers(missingNumbers);
        decreeReport.setTotalCount(decreeInts.length);
        decreeReport.setFirstDecree(min);
        decreeReport.setLastDecree(max);

        return decreeReport;
    }

    public List<MinisterDTO> findMinistersByDecreeYear(Integer year) {
        List<MinisterDTO> ministerDTOS = new ArrayList<>();
        decreeRepository
            .findAllByYearGroupByMinister(year)
            .forEach(objects -> {
                MinisterDTO ministerDTO = new MinisterDTO();
                ministerDTO.setId(((BigInteger) objects[0]).longValue());
                ministerDTO.setName((String) objects[1]);
                ministerDTOS.add(ministerDTO);
            });
        return ministerDTOS;
    }
}
