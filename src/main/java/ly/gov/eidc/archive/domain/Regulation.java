package ly.gov.eidc.archive.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * A Regulation.
 */
@Entity
@Table(name = "regulation")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "regulation")
public class Regulation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "type")
    private String type;

    @Column(name = "year")
    private Integer year;

    @Lob
    @Column(name = "file_pdf")
    private byte[] filePdf;

    @Column(name = "file_pdf_content_type")
    private String filePdfContentType;

    @Column(name = "file_pdf_url")
    private String filePdfUrl;

    @Lob
    @Column(name = "file_word")
    private byte[] fileWord;

    @Column(name = "file_word_content_type")
    private String fileWordContentType;

    @Column(name = "file_word_url")
    private String fileWordUrl;

    @Column(name = "is_active")
    private Boolean isActive;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Regulation id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public Regulation title(String title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public Regulation description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return this.type;
    }

    public Regulation type(String type) {
        this.setType(type);
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getYear() {
        return this.year;
    }

    public Regulation year(Integer year) {
        this.setYear(year);
        return this;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public byte[] getFilePdf() {
        return this.filePdf;
    }

    public Regulation filePdf(byte[] filePdf) {
        this.setFilePdf(filePdf);
        return this;
    }

    public void setFilePdf(byte[] filePdf) {
        this.filePdf = filePdf;
    }

    public String getFilePdfContentType() {
        return this.filePdfContentType;
    }

    public Regulation filePdfContentType(String filePdfContentType) {
        this.filePdfContentType = filePdfContentType;
        return this;
    }

    public void setFilePdfContentType(String filePdfContentType) {
        this.filePdfContentType = filePdfContentType;
    }

    public String getFilePdfUrl() {
        return this.filePdfUrl;
    }

    public Regulation filePdfUrl(String filePdfUrl) {
        this.setFilePdfUrl(filePdfUrl);
        return this;
    }

    public void setFilePdfUrl(String filePdfUrl) {
        this.filePdfUrl = filePdfUrl;
    }

    public byte[] getFileWord() {
        return this.fileWord;
    }

    public Regulation fileWord(byte[] fileWord) {
        this.setFileWord(fileWord);
        return this;
    }

    public void setFileWord(byte[] fileWord) {
        this.fileWord = fileWord;
    }

    public String getFileWordContentType() {
        return this.fileWordContentType;
    }

    public Regulation fileWordContentType(String fileWordContentType) {
        this.fileWordContentType = fileWordContentType;
        return this;
    }

    public void setFileWordContentType(String fileWordContentType) {
        this.fileWordContentType = fileWordContentType;
    }

    public String getFileWordUrl() {
        return this.fileWordUrl;
    }

    public Regulation fileWordUrl(String fileWordUrl) {
        this.setFileWordUrl(fileWordUrl);
        return this;
    }

    public void setFileWordUrl(String fileWordUrl) {
        this.fileWordUrl = fileWordUrl;
    }

    public Boolean getIsActive() {
        return this.isActive;
    }

    public Regulation isActive(Boolean isActive) {
        this.setIsActive(isActive);
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Regulation)) {
            return false;
        }
        return id != null && id.equals(((Regulation) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Regulation{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", type='" + getType() + "'" +
            ", year=" + getYear() +
            ", filePdf='" + getFilePdf() + "'" +
            ", filePdfContentType='" + getFilePdfContentType() + "'" +
            ", filePdfUrl='" + getFilePdfUrl() + "'" +
            ", fileWord='" + getFileWord() + "'" +
            ", fileWordContentType='" + getFileWordContentType() + "'" +
            ", fileWordUrl='" + getFileWordUrl() + "'" +
            ", isActive='" + getIsActive() + "'" +
            "}";
    }
}
