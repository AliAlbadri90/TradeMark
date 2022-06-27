package ly.gov.eidc.archive.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Lob;
import ly.gov.eidc.archive.domain.enumeration.DecreeStatus;

/**
 * A DTO for the {@link ly.gov.eidc.archive.domain.Decree} entity.
 */
public class DecreeDTO implements Serializable {

    private Long id;

    private String documentNo;

    private String decreeNo;

    private String title;

    private String details;

    private String keywords;

    private Integer pages;

    private LocalDate decreeDate;

    private Integer year;

    private String notes;

    @Lob
    private byte[] pdfFile;

    private String pdfFileContentType;
    private String pdfFileUrl;

    @Lob
    private byte[] wordFile;

    private String wordFileContentType;
    private String wordFileUrl;

    @Lob
    private byte[] extraPdfFile;

    private String extraPdfFileContentType;
    private String extraPdfFileUrl;

    private DecreeStatus decreeStatus;

    private String remarks;

    private Boolean isHidden;

    private String hideNotes;

    private LocalDate hideEndDate;

    private DecreeTypeDTO decreeType;

    private DecreeCategoryDTO decreeCategory;

    private MinisterDTO minister;

    private GovernmentDTO government;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumentNo() {
        return documentNo;
    }

    public void setDocumentNo(String documentNo) {
        this.documentNo = documentNo;
    }

    public String getDecreeNo() {
        return decreeNo;
    }

    public void setDecreeNo(String decreeNo) {
        this.decreeNo = decreeNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public LocalDate getDecreeDate() {
        return decreeDate;
    }

    public void setDecreeDate(LocalDate decreeDate) {
        this.decreeDate = decreeDate;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public byte[] getPdfFile() {
        return pdfFile;
    }

    public void setPdfFile(byte[] pdfFile) {
        this.pdfFile = pdfFile;
    }

    public String getPdfFileContentType() {
        return pdfFileContentType;
    }

    public void setPdfFileContentType(String pdfFileContentType) {
        this.pdfFileContentType = pdfFileContentType;
    }

    public String getPdfFileUrl() {
        return pdfFileUrl;
    }

    public void setPdfFileUrl(String pdfFileUrl) {
        this.pdfFileUrl = pdfFileUrl;
    }

    public byte[] getWordFile() {
        return wordFile;
    }

    public void setWordFile(byte[] wordFile) {
        this.wordFile = wordFile;
    }

    public String getWordFileContentType() {
        return wordFileContentType;
    }

    public void setWordFileContentType(String wordFileContentType) {
        this.wordFileContentType = wordFileContentType;
    }

    public String getWordFileUrl() {
        return wordFileUrl;
    }

    public void setWordFileUrl(String wordFileUrl) {
        this.wordFileUrl = wordFileUrl;
    }

    public byte[] getExtraPdfFile() {
        return extraPdfFile;
    }

    public void setExtraPdfFile(byte[] extraPdfFile) {
        this.extraPdfFile = extraPdfFile;
    }

    public String getExtraPdfFileContentType() {
        return extraPdfFileContentType;
    }

    public void setExtraPdfFileContentType(String extraPdfFileContentType) {
        this.extraPdfFileContentType = extraPdfFileContentType;
    }

    public String getExtraPdfFileUrl() {
        return extraPdfFileUrl;
    }

    public void setExtraPdfFileUrl(String extraPdfFileUrl) {
        this.extraPdfFileUrl = extraPdfFileUrl;
    }

    public DecreeStatus getDecreeStatus() {
        return decreeStatus;
    }

    public void setDecreeStatus(DecreeStatus decreeStatus) {
        this.decreeStatus = decreeStatus;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Boolean getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(Boolean isHidden) {
        this.isHidden = isHidden;
    }

    public String getHideNotes() {
        return hideNotes;
    }

    public void setHideNotes(String hideNotes) {
        this.hideNotes = hideNotes;
    }

    public LocalDate getHideEndDate() {
        return hideEndDate;
    }

    public void setHideEndDate(LocalDate hideEndDate) {
        this.hideEndDate = hideEndDate;
    }

    public DecreeTypeDTO getDecreeType() {
        return decreeType;
    }

    public void setDecreeType(DecreeTypeDTO decreeType) {
        this.decreeType = decreeType;
    }

    public DecreeCategoryDTO getDecreeCategory() {
        return decreeCategory;
    }

    public void setDecreeCategory(DecreeCategoryDTO decreeCategory) {
        this.decreeCategory = decreeCategory;
    }

    public MinisterDTO getMinister() {
        return minister;
    }

    public void setMinister(MinisterDTO minister) {
        this.minister = minister;
    }

    public GovernmentDTO getGovernment() {
        return government;
    }

    public void setGovernment(GovernmentDTO government) {
        this.government = government;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DecreeDTO)) {
            return false;
        }

        DecreeDTO decreeDTO = (DecreeDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, decreeDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DecreeDTO{" +
            "id=" + getId() +
            ", documentNo='" + getDocumentNo() + "'" +
            ", decreeNo='" + getDecreeNo() + "'" +
            ", title='" + getTitle() + "'" +
            ", details='" + getDetails() + "'" +
            ", keywords='" + getKeywords() + "'" +
            ", pages=" + getPages() +
            ", decreeDate='" + getDecreeDate() + "'" +
            ", year=" + getYear() +
            ", notes='" + getNotes() + "'" +
            ", pdfFile='" + getPdfFile() + "'" +
            ", pdfFileUrl='" + getPdfFileUrl() + "'" +
            ", wordFile='" + getWordFile() + "'" +
            ", wordFileUrl='" + getWordFileUrl() + "'" +
            ", extraPdfFile='" + getExtraPdfFile() + "'" +
            ", extraPdfFileUrl='" + getExtraPdfFileUrl() + "'" +
            ", decreeStatus='" + getDecreeStatus() + "'" +
            ", remarks='" + getRemarks() + "'" +
            ", isHidden='" + getIsHidden() + "'" +
            ", hideNotes='" + getHideNotes() + "'" +
            ", hideEndDate='" + getHideEndDate() + "'" +
            ", decreeType=" + getDecreeType() +
            ", decreeCategory=" + getDecreeCategory() +
            ", minister=" + getMinister() +
            ", government=" + getGovernment() +
            "}";
    }
}
