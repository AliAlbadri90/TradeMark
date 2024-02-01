package ly.gov.eidc.archive.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import ly.gov.eidc.archive.domain.enumeration.TrademarkRegisteredStatus;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

/**
 * A TrademarkRegistered.
 */
@Entity
@Table(name = "trademark_registered")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "trademarkregistered")
@Setting(settingPath = "da.json")
public class TrademarkRegistered extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Field
    private Long id;

    @Column(name = "trademark_uuid")
    private String trademarkUUID;

    @Column(name = "trademark_no")
    @Field(type = FieldType.Text, searchAnalyzer = "searchAnalyzer", analyzer = "indexAnalyzer")
    private String trademarkNo;

    @Column(name = "year")
    @Field(type = FieldType.Text, searchAnalyzer = "searchAnalyzer", analyzer = "indexAnalyzer")
    private Integer year;

    @Column(name = "decree_no")
    @Field(type = FieldType.Text, searchAnalyzer = "searchAnalyzer", analyzer = "indexAnalyzer")
    private String decreeNo;

    @Column(name = "applicant_name")
    @Field(type = FieldType.Text, searchAnalyzer = "searchAnalyzer", analyzer = "indexAnalyzer")
    private String applicantName;

    @Column(name = "trade_mark_owner")
    @Field(type = FieldType.Text, searchAnalyzer = "searchAnalyzer", analyzer = "indexAnalyzer")
    private String tradeMarkOwner;

    @Column(name = "country")
    @Field(type = FieldType.Text, searchAnalyzer = "countrySearchAnalyzer", analyzer = "countryIndexAnalyzer")
    private String country;

    @Column(name = "nationality")
    @Field(type = FieldType.Text, searchAnalyzer = "countrySearchAnalyzer", analyzer = "countryIndexAnalyzer")
    private String nationality;

    @Column(name = "address")
    @Field(type = FieldType.Text, searchAnalyzer = "searchAnalyzer", analyzer = "indexAnalyzer")
    private String address;

    @Column(name = "apply_date")
    @Field(type = FieldType.Date, format = DateFormat.basic_date)
    private LocalDate applyDate;

    @Column(name = "trademark_english")
    @Field(type = FieldType.Text, searchAnalyzer = "searchAnalyzer", analyzer = "indexAnalyzer")
    private String trademarkEnglish;

    @Column(name = "trademark_arabic")
    @Field(type = FieldType.Text, searchAnalyzer = "searchAnalyzer", analyzer = "indexAnalyzer")
    private String trademarkArabic;

    @Column(name = "category")
    private String category;

    @Lob
    @Column(name = "image_file")
    private byte[] imageFile;

    @Column(name = "image_file_content_type")
    private String imageFileContentType;

    @Column(name = "image_file_url")
    private String imageFileUrl;

    @Lob
    @Column(name = "file")
    private byte[] file;

    @Column(name = "file_content_type")
    private String fileContentType;

    @Column(name = "file_url")
    private String fileUrl;

    @Lob
    @Column(name = "extra_file")
    private byte[] extraFile;

    @Column(name = "extra_file_content_type")
    private String extraFileContentType;

    @Column(name = "extra_file_url")
    private String extraFileUrl;

    @Column(name = "publication_date")
    private LocalDate publicationDate;

    @Column(name = "publication_no")
    private Integer publicationNo;

    @Enumerated(EnumType.STRING)
    @Column(name = "trademark_registered_status")
    private TrademarkRegisteredStatus trademarkRegisteredStatus;

    @Column(name = "is_hidden")
    private Boolean isHidden;

    @Column(name = "notes")
    private String notes;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public TrademarkRegistered id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTrademarkUUID() {
        return this.trademarkUUID;
    }

    public TrademarkRegistered trademarkUUID(String trademarkUUID) {
        this.setTrademarkUUID(trademarkUUID);
        return this;
    }

    public void setTrademarkUUID(String trademarkUUID) {
        this.trademarkUUID = trademarkUUID;
    }

    public String getTrademarkNo() {
        return this.trademarkNo;
    }

    public TrademarkRegistered trademarkNo(String trademarkNo) {
        this.setTrademarkNo(trademarkNo);
        return this;
    }

    public void setTrademarkNo(String trademarkNo) {
        this.trademarkNo = trademarkNo;
    }

    public Integer getYear() {
        return this.year;
    }

    public TrademarkRegistered year(Integer year) {
        this.setYear(year);
        return this;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getDecreeNo() {
        return this.decreeNo;
    }

    public TrademarkRegistered decreeNo(String decreeNo) {
        this.setDecreeNo(decreeNo);
        return this;
    }

    public void setDecreeNo(String decreeNo) {
        this.decreeNo = decreeNo;
    }

    public String getApplicantName() {
        return this.applicantName;
    }

    public TrademarkRegistered applicantName(String applicantName) {
        this.setApplicantName(applicantName);
        return this;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getTradeMarkOwner() {
        return this.tradeMarkOwner;
    }

    public TrademarkRegistered tradeMarkOwner(String tradeMarkOwner) {
        this.setTradeMarkOwner(tradeMarkOwner);
        return this;
    }

    public void setTradeMarkOwner(String tradeMarkOwner) {
        this.tradeMarkOwner = tradeMarkOwner;
    }

    public String getCountry() {
        return this.country;
    }

    public TrademarkRegistered country(String country) {
        this.setCountry(country);
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getNationality() {
        return this.nationality;
    }

    public TrademarkRegistered nationality(String nationality) {
        this.setNationality(nationality);
        return this;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getAddress() {
        return this.address;
    }

    public TrademarkRegistered address(String address) {
        this.setAddress(address);
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getApplyDate() {
        return this.applyDate;
    }

    public TrademarkRegistered applyDate(LocalDate applyDate) {
        this.setApplyDate(applyDate);
        return this;
    }

    public void setApplyDate(LocalDate applyDate) {
        this.applyDate = applyDate;
    }

    public String getTrademarkEnglish() {
        return this.trademarkEnglish;
    }

    public TrademarkRegistered trademarkEnglish(String trademarkEnglish) {
        this.setTrademarkEnglish(trademarkEnglish);
        return this;
    }

    public void setTrademarkEnglish(String trademarkEnglish) {
        this.trademarkEnglish = trademarkEnglish;
    }

    public String getTrademarkArabic() {
        return this.trademarkArabic;
    }

    public TrademarkRegistered trademarkArabic(String trademarkArabic) {
        this.setTrademarkArabic(trademarkArabic);
        return this;
    }

    public void setTrademarkArabic(String trademarkArabic) {
        this.trademarkArabic = trademarkArabic;
    }

    public String getCategory() {
        return this.category;
    }

    public TrademarkRegistered category(String category) {
        this.setCategory(category);
        return this;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public byte[] getImageFile() {
        return this.imageFile;
    }

    public TrademarkRegistered imageFile(byte[] imageFile) {
        this.setImageFile(imageFile);
        return this;
    }

    public void setImageFile(byte[] imageFile) {
        this.imageFile = imageFile;
    }

    public String getImageFileContentType() {
        return this.imageFileContentType;
    }

    public TrademarkRegistered imageFileContentType(String imageFileContentType) {
        this.imageFileContentType = imageFileContentType;
        return this;
    }

    public void setImageFileContentType(String imageFileContentType) {
        this.imageFileContentType = imageFileContentType;
    }

    public String getImageFileUrl() {
        return this.imageFileUrl;
    }

    public TrademarkRegistered imageFileUrl(String imageFileUrl) {
        this.setImageFileUrl(imageFileUrl);
        return this;
    }

    public void setImageFileUrl(String imageFileUrl) {
        this.imageFileUrl = imageFileUrl;
    }

    public byte[] getFile() {
        return this.file;
    }

    public TrademarkRegistered file(byte[] file) {
        this.setFile(file);
        return this;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getFileContentType() {
        return this.fileContentType;
    }

    public TrademarkRegistered fileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
        return this;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public String getFileUrl() {
        return this.fileUrl;
    }

    public TrademarkRegistered fileUrl(String fileUrl) {
        this.setFileUrl(fileUrl);
        return this;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public byte[] getExtraFile() {
        return this.extraFile;
    }

    public TrademarkRegistered extraFile(byte[] extraFile) {
        this.setExtraFile(extraFile);
        return this;
    }

    public void setExtraFile(byte[] extraFile) {
        this.extraFile = extraFile;
    }

    public String getExtraFileContentType() {
        return this.extraFileContentType;
    }

    public TrademarkRegistered extraFileContentType(String extraFileContentType) {
        this.extraFileContentType = extraFileContentType;
        return this;
    }

    public void setExtraFileContentType(String extraFileContentType) {
        this.extraFileContentType = extraFileContentType;
    }

    public String getExtraFileUrl() {
        return this.extraFileUrl;
    }

    public TrademarkRegistered extraFileUrl(String extraFileUrl) {
        this.setExtraFileUrl(extraFileUrl);
        return this;
    }

    public void setExtraFileUrl(String extraFileUrl) {
        this.extraFileUrl = extraFileUrl;
    }

    public LocalDate getPublicationDate() {
        return this.publicationDate;
    }

    public TrademarkRegistered publicationDate(LocalDate publicationDate) {
        this.setPublicationDate(publicationDate);
        return this;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Integer getPublicationNo() {
        return this.publicationNo;
    }

    public TrademarkRegistered publicationNo(Integer publicationNo) {
        this.setPublicationNo(publicationNo);
        return this;
    }

    public void setPublicationNo(Integer publicationNo) {
        this.publicationNo = publicationNo;
    }

    public TrademarkRegisteredStatus getTrademarkRegisteredStatus() {
        return this.trademarkRegisteredStatus;
    }

    public TrademarkRegistered trademarkRegisteredStatus(TrademarkRegisteredStatus trademarkRegisteredStatus) {
        this.setTrademarkRegisteredStatus(trademarkRegisteredStatus);
        return this;
    }

    public void setTrademarkRegisteredStatus(TrademarkRegisteredStatus trademarkRegisteredStatus) {
        this.trademarkRegisteredStatus = trademarkRegisteredStatus;
    }

    public Boolean getIsHidden() {
        return this.isHidden;
    }

    public TrademarkRegistered isHidden(Boolean isHidden) {
        this.setIsHidden(isHidden);
        return this;
    }

    public void setIsHidden(Boolean isHidden) {
        this.isHidden = isHidden;
    }

    public String getNotes() {
        return this.notes;
    }

    public TrademarkRegistered notes(String notes) {
        this.setNotes(notes);
        return this;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TrademarkRegistered)) {
            return false;
        }
        return id != null && id.equals(((TrademarkRegistered) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TrademarkRegistered{" +
            "id=" + getId() +
            ", trademarkUUID='" + getTrademarkUUID() + "'" +
            ", trademarkNo='" + getTrademarkNo() + "'" +
            ", year=" + getYear() +
            ", decreeNo='" + getDecreeNo() + "'" +
            ", applicantName='" + getApplicantName() + "'" +
            ", tradeMarkOwner='" + getTradeMarkOwner() + "'" +
            ", country='" + getCountry() + "'" +
            ", nationality='" + getNationality() + "'" +
            ", address='" + getAddress() + "'" +
            ", applyDate='" + getApplyDate() + "'" +
            ", trademarkEnglish='" + getTrademarkEnglish() + "'" +
            ", trademarkArabic='" + getTrademarkArabic() + "'" +
            ", category='" + getCategory() + "'" +
            ", imageFile='" + getImageFile() + "'" +
            ", imageFileContentType='" + getImageFileContentType() + "'" +
            ", imageFileUrl='" + getImageFileUrl() + "'" +
            ", file='" + getFile() + "'" +
            ", fileContentType='" + getFileContentType() + "'" +
            ", fileUrl='" + getFileUrl() + "'" +
            ", extraFile='" + getExtraFile() + "'" +
            ", extraFileContentType='" + getExtraFileContentType() + "'" +
            ", extraFileUrl='" + getExtraFileUrl() + "'" +
            ", publicationDate='" + getPublicationDate() + "'" +
            ", publicationNo=" + getPublicationNo() +
            ", trademarkRegisteredStatus='" + getTrademarkRegisteredStatus() + "'" +
            ", isHidden='" + getIsHidden() + "'" +
            ", notes='" + getNotes() + "'" +
            "}";
    }
}
