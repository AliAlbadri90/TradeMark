package ly.gov.eidc.archive.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import ly.gov.eidc.archive.domain.enumeration.DecreeStatus;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

/**
 * A Decree.
 */
@Entity
@Table(name = "decree")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "decree")
@Setting(settingPath = "/decreeAnalyzer.json")
public class Decree extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "document_no")
    private String documentNo;

    @Column(name = "decree_no")
    private String decreeNo;

    @Column(name = "title")
    @Field(type = FieldType.Text, searchAnalyzer = "searchAnalyzer", analyzer = "indexAnalyzer")
    private String title;

    @Column(name = "details")
    private String details;

    @Column(name = "keywords")
    private String keywords;

    @Column(name = "pages")
    private Integer pages;

    @Column(name = "decree_date")
    private LocalDate decreeDate;

    @Column(name = "year")
    private Integer year;

    @Column(name = "notes")
    private String notes;

    @Lob
    @Column(name = "pdf_file")
    private byte[] pdfFile;

    @Column(name = "pdf_file_content_type")
    private String pdfFileContentType;

    @Column(name = "pdf_file_url")
    private String pdfFileUrl;

    @Lob
    @Column(name = "word_file")
    private byte[] wordFile;

    @Column(name = "word_file_content_type")
    private String wordFileContentType;

    @Column(name = "word_file_url")
    private String wordFileUrl;

    @Lob
    @Column(name = "extra_pdf_file")
    private byte[] extraPdfFile;

    @Column(name = "extra_pdf_file_content_type")
    private String extraPdfFileContentType;

    @Column(name = "extra_pdf_file_url")
    private String extraPdfFileUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "decree_status")
    private DecreeStatus decreeStatus;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "is_hidden")
    private Boolean isHidden;

    @Column(name = "hide_notes")
    private String hideNotes;

    @Column(name = "hide_end_date")
    private LocalDate hideEndDate;

    @ManyToOne
    private DecreeType decreeType;

    @ManyToOne
    private DecreeCategory decreeCategory;

    @ManyToOne
    private Minister minister;

    @ManyToOne
    private Government government;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Decree id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumentNo() {
        return this.documentNo;
    }

    public Decree documentNo(String documentNo) {
        this.setDocumentNo(documentNo);
        return this;
    }

    public void setDocumentNo(String documentNo) {
        this.documentNo = documentNo;
    }

    public String getDecreeNo() {
        return this.decreeNo;
    }

    public Decree decreeNo(String decreeNo) {
        this.setDecreeNo(decreeNo);
        return this;
    }

    public void setDecreeNo(String decreeNo) {
        this.decreeNo = decreeNo;
    }

    public String getTitle() {
        return this.title;
    }

    public Decree title(String title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return this.details;
    }

    public Decree details(String details) {
        this.setDetails(details);
        return this;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getKeywords() {
        return this.keywords;
    }

    public Decree keywords(String keywords) {
        this.setKeywords(keywords);
        return this;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Integer getPages() {
        return this.pages;
    }

    public Decree pages(Integer pages) {
        this.setPages(pages);
        return this;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public LocalDate getDecreeDate() {
        return this.decreeDate;
    }

    public Decree decreeDate(LocalDate decreeDate) {
        this.setDecreeDate(decreeDate);
        return this;
    }

    public void setDecreeDate(LocalDate decreeDate) {
        this.decreeDate = decreeDate;
    }

    public Integer getYear() {
        return this.year;
    }

    public Decree year(Integer year) {
        this.setYear(year);
        return this;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getNotes() {
        return this.notes;
    }

    public Decree notes(String notes) {
        this.setNotes(notes);
        return this;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public byte[] getPdfFile() {
        return this.pdfFile;
    }

    public Decree pdfFile(byte[] pdfFile) {
        this.setPdfFile(pdfFile);
        return this;
    }

    public void setPdfFile(byte[] pdfFile) {
        this.pdfFile = pdfFile;
    }

    public String getPdfFileContentType() {
        return this.pdfFileContentType;
    }

    public Decree pdfFileContentType(String pdfFileContentType) {
        this.pdfFileContentType = pdfFileContentType;
        return this;
    }

    public void setPdfFileContentType(String pdfFileContentType) {
        this.pdfFileContentType = pdfFileContentType;
    }

    public String getPdfFileUrl() {
        return this.pdfFileUrl;
    }

    public Decree pdfFileUrl(String pdfFileUrl) {
        this.setPdfFileUrl(pdfFileUrl);
        return this;
    }

    public void setPdfFileUrl(String pdfFileUrl) {
        this.pdfFileUrl = pdfFileUrl;
    }

    public byte[] getWordFile() {
        return this.wordFile;
    }

    public Decree wordFile(byte[] wordFile) {
        this.setWordFile(wordFile);
        return this;
    }

    public void setWordFile(byte[] wordFile) {
        this.wordFile = wordFile;
    }

    public String getWordFileContentType() {
        return this.wordFileContentType;
    }

    public Decree wordFileContentType(String wordFileContentType) {
        this.wordFileContentType = wordFileContentType;
        return this;
    }

    public void setWordFileContentType(String wordFileContentType) {
        this.wordFileContentType = wordFileContentType;
    }

    public String getWordFileUrl() {
        return this.wordFileUrl;
    }

    public Decree wordFileUrl(String wordFileUrl) {
        this.setWordFileUrl(wordFileUrl);
        return this;
    }

    public void setWordFileUrl(String wordFileUrl) {
        this.wordFileUrl = wordFileUrl;
    }

    public byte[] getExtraPdfFile() {
        return this.extraPdfFile;
    }

    public Decree extraPdfFile(byte[] extraPdfFile) {
        this.setExtraPdfFile(extraPdfFile);
        return this;
    }

    public void setExtraPdfFile(byte[] extraPdfFile) {
        this.extraPdfFile = extraPdfFile;
    }

    public String getExtraPdfFileContentType() {
        return this.extraPdfFileContentType;
    }

    public Decree extraPdfFileContentType(String extraPdfFileContentType) {
        this.extraPdfFileContentType = extraPdfFileContentType;
        return this;
    }

    public void setExtraPdfFileContentType(String extraPdfFileContentType) {
        this.extraPdfFileContentType = extraPdfFileContentType;
    }

    public String getExtraPdfFileUrl() {
        return this.extraPdfFileUrl;
    }

    public Decree extraPdfFileUrl(String extraPdfFileUrl) {
        this.setExtraPdfFileUrl(extraPdfFileUrl);
        return this;
    }

    public void setExtraPdfFileUrl(String extraPdfFileUrl) {
        this.extraPdfFileUrl = extraPdfFileUrl;
    }

    public DecreeStatus getDecreeStatus() {
        return this.decreeStatus;
    }

    public Decree decreeStatus(DecreeStatus decreeStatus) {
        this.setDecreeStatus(decreeStatus);
        return this;
    }

    public void setDecreeStatus(DecreeStatus decreeStatus) {
        this.decreeStatus = decreeStatus;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public Decree remarks(String remarks) {
        this.setRemarks(remarks);
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Boolean getIsHidden() {
        return this.isHidden;
    }

    public Decree isHidden(Boolean isHidden) {
        this.setIsHidden(isHidden);
        return this;
    }

    public void setIsHidden(Boolean isHidden) {
        this.isHidden = isHidden;
    }

    public String getHideNotes() {
        return this.hideNotes;
    }

    public Decree hideNotes(String hideNotes) {
        this.setHideNotes(hideNotes);
        return this;
    }

    public void setHideNotes(String hideNotes) {
        this.hideNotes = hideNotes;
    }

    public LocalDate getHideEndDate() {
        return this.hideEndDate;
    }

    public Decree hideEndDate(LocalDate hideEndDate) {
        this.setHideEndDate(hideEndDate);
        return this;
    }

    public void setHideEndDate(LocalDate hideEndDate) {
        this.hideEndDate = hideEndDate;
    }

    public DecreeType getDecreeType() {
        return this.decreeType;
    }

    public void setDecreeType(DecreeType decreeType) {
        this.decreeType = decreeType;
    }

    public Decree decreeType(DecreeType decreeType) {
        this.setDecreeType(decreeType);
        return this;
    }

    public DecreeCategory getDecreeCategory() {
        return this.decreeCategory;
    }

    public void setDecreeCategory(DecreeCategory decreeCategory) {
        this.decreeCategory = decreeCategory;
    }

    public Decree decreeCategory(DecreeCategory decreeCategory) {
        this.setDecreeCategory(decreeCategory);
        return this;
    }

    public Minister getMinister() {
        return this.minister;
    }

    public void setMinister(Minister minister) {
        this.minister = minister;
    }

    public Decree minister(Minister minister) {
        this.setMinister(minister);
        return this;
    }

    public Government getGovernment() {
        return this.government;
    }

    public void setGovernment(Government government) {
        this.government = government;
    }

    public Decree government(Government government) {
        this.setGovernment(government);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Decree)) {
            return false;
        }
        return id != null && id.equals(((Decree) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Decree{" +
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
            ", pdfFileContentType='" + getPdfFileContentType() + "'" +
            ", pdfFileUrl='" + getPdfFileUrl() + "'" +
            ", wordFile='" + getWordFile() + "'" +
            ", wordFileContentType='" + getWordFileContentType() + "'" +
            ", wordFileUrl='" + getWordFileUrl() + "'" +
            ", extraPdfFile='" + getExtraPdfFile() + "'" +
            ", extraPdfFileContentType='" + getExtraPdfFileContentType() + "'" +
            ", extraPdfFileUrl='" + getExtraPdfFileUrl() + "'" +
            ", decreeStatus='" + getDecreeStatus() + "'" +
            ", remarks='" + getRemarks() + "'" +
            ", isHidden='" + getIsHidden() + "'" +
            ", hideNotes='" + getHideNotes() + "'" +
            ", hideEndDate='" + getHideEndDate() + "'" +
            "}";
    }
}
