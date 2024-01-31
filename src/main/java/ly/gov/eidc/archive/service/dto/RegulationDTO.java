package ly.gov.eidc.archive.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link ly.gov.eidc.archive.domain.Regulation} entity.
 */
public class RegulationDTO implements Serializable {

    private Long id;

    private String title;

    private String description;

    private String type;

    private Integer year;

    @Lob
    private byte[] filePdf;

    private String filePdfContentType;
    private String filePdfUrl;

    @Lob
    private byte[] fileWord;

    private String fileWordContentType;
    private String fileWordUrl;

    private Boolean isActive;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public byte[] getFilePdf() {
        return filePdf;
    }

    public void setFilePdf(byte[] filePdf) {
        this.filePdf = filePdf;
    }

    public String getFilePdfContentType() {
        return filePdfContentType;
    }

    public void setFilePdfContentType(String filePdfContentType) {
        this.filePdfContentType = filePdfContentType;
    }

    public String getFilePdfUrl() {
        return filePdfUrl;
    }

    public void setFilePdfUrl(String filePdfUrl) {
        this.filePdfUrl = filePdfUrl;
    }

    public byte[] getFileWord() {
        return fileWord;
    }

    public void setFileWord(byte[] fileWord) {
        this.fileWord = fileWord;
    }

    public String getFileWordContentType() {
        return fileWordContentType;
    }

    public void setFileWordContentType(String fileWordContentType) {
        this.fileWordContentType = fileWordContentType;
    }

    public String getFileWordUrl() {
        return fileWordUrl;
    }

    public void setFileWordUrl(String fileWordUrl) {
        this.fileWordUrl = fileWordUrl;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RegulationDTO)) {
            return false;
        }

        RegulationDTO regulationDTO = (RegulationDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, regulationDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RegulationDTO{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", type='" + getType() + "'" +
            ", year=" + getYear() +
            ", filePdf='" + getFilePdf() + "'" +
            ", filePdfUrl='" + getFilePdfUrl() + "'" +
            ", fileWord='" + getFileWord() + "'" +
            ", fileWordUrl='" + getFileWordUrl() + "'" +
            ", isActive='" + getIsActive() + "'" +
            "}";
    }
}
