package ly.gov.eidc.archive.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Lob;
import ly.gov.eidc.archive.domain.enumeration.TrademarkRegisteredStatus;

/**
 * A DTO for the {@link ly.gov.eidc.archive.domain.TrademarkRegistered} entity.
 */
public class TrademarkRegisteredDTO implements Serializable {

    private Long id;

    private String trademarkUUID;

    private String trademarkNo;

    private Integer year;

    private String decreeNo;

    private String applicantName;

    private String tradeMarkOwner;

    private String country;

    private String nationality;

    private String address;

    private LocalDate applyDate;

    private String trademarkEnglish;

    private String trademarkArabic;

    private String category;

    @Lob
    private byte[] imageFile;

    private String imageFileContentType;
    private String imageFileUrl;

    @Lob
    private byte[] file;

    private String fileContentType;
    private String fileUrl;

    @Lob
    private byte[] extraFile;

    private String extraFileContentType;
    private String extraFileUrl;

    private LocalDate publicationDate;

    private Integer publicationNo;

    private TrademarkRegisteredStatus trademarkRegisteredStatus;

    private Boolean isHidden;

    private String notes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTrademarkUUID() {
        return trademarkUUID;
    }

    public void setTrademarkUUID(String trademarkUUID) {
        this.trademarkUUID = trademarkUUID;
    }

    public String getTrademarkNo() {
        return trademarkNo;
    }

    public void setTrademarkNo(String trademarkNo) {
        this.trademarkNo = trademarkNo;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getDecreeNo() {
        return decreeNo;
    }

    public void setDecreeNo(String decreeNo) {
        this.decreeNo = decreeNo;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getTradeMarkOwner() {
        return tradeMarkOwner;
    }

    public void setTradeMarkOwner(String tradeMarkOwner) {
        this.tradeMarkOwner = tradeMarkOwner;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(LocalDate applyDate) {
        this.applyDate = applyDate;
    }

    public String getTrademarkEnglish() {
        return trademarkEnglish;
    }

    public void setTrademarkEnglish(String trademarkEnglish) {
        this.trademarkEnglish = trademarkEnglish;
    }

    public String getTrademarkArabic() {
        return trademarkArabic;
    }

    public void setTrademarkArabic(String trademarkArabic) {
        this.trademarkArabic = trademarkArabic;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public byte[] getImageFile() {
        return imageFile;
    }

    public void setImageFile(byte[] imageFile) {
        this.imageFile = imageFile;
    }

    public String getImageFileContentType() {
        return imageFileContentType;
    }

    public void setImageFileContentType(String imageFileContentType) {
        this.imageFileContentType = imageFileContentType;
    }

    public String getImageFileUrl() {
        return imageFileUrl;
    }

    public void setImageFileUrl(String imageFileUrl) {
        this.imageFileUrl = imageFileUrl;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public byte[] getExtraFile() {
        return extraFile;
    }

    public void setExtraFile(byte[] extraFile) {
        this.extraFile = extraFile;
    }

    public String getExtraFileContentType() {
        return extraFileContentType;
    }

    public void setExtraFileContentType(String extraFileContentType) {
        this.extraFileContentType = extraFileContentType;
    }

    public String getExtraFileUrl() {
        return extraFileUrl;
    }

    public void setExtraFileUrl(String extraFileUrl) {
        this.extraFileUrl = extraFileUrl;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Integer getPublicationNo() {
        return publicationNo;
    }

    public void setPublicationNo(Integer publicationNo) {
        this.publicationNo = publicationNo;
    }

    public TrademarkRegisteredStatus getTrademarkRegisteredStatus() {
        return trademarkRegisteredStatus;
    }

    public void setTrademarkRegisteredStatus(TrademarkRegisteredStatus trademarkRegisteredStatus) {
        this.trademarkRegisteredStatus = trademarkRegisteredStatus;
    }

    public Boolean getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(Boolean isHidden) {
        this.isHidden = isHidden;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TrademarkRegisteredDTO)) {
            return false;
        }

        TrademarkRegisteredDTO trademarkRegisteredDTO = (TrademarkRegisteredDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, trademarkRegisteredDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TrademarkRegisteredDTO{" +
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
            ", imageFileUrl='" + getImageFileUrl() + "'" +
            ", file='" + getFile() + "'" +
            ", fileUrl='" + getFileUrl() + "'" +
            ", extraFile='" + getExtraFile() + "'" +
            ", extraFileUrl='" + getExtraFileUrl() + "'" +
            ", publicationDate='" + getPublicationDate() + "'" +
            ", publicationNo=" + getPublicationNo() +
            ", trademarkRegisteredStatus='" + getTrademarkRegisteredStatus() + "'" +
            ", isHidden='" + getIsHidden() + "'" +
            ", notes='" + getNotes() + "'" +
            "}";
    }
}
