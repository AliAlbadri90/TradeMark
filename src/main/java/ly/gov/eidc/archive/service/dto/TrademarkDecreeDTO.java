package ly.gov.eidc.archive.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link ly.gov.eidc.archive.domain.TrademarkDecree} entity.
 */
public class TrademarkDecreeDTO implements Serializable {

    private Long id;

    private Integer year;

    private String decreeNo;

    private Boolean isAccepted;

    private LocalDate decreeDate;

    private String applicantName;

    private String tradeMarkOwner;

    private String country;

    private LocalDate applyDate;

    private String serialNo;

    private String trademarkEnglish;

    private String trademarkArabic;

    private String category;

    @Lob
    private byte[] pdfFile;

    private String pdfFileContentType;
    private String pdfFileUrl;

    @Lob
    private byte[] extraPdfFile;

    private String extraPdfFileContentType;
    private String extraPdfFileUrl;

    private Boolean isWithdrawal;

    private String withdrawalDecreeNo;

    private String notes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Boolean getIsAccepted() {
        return isAccepted;
    }

    public void setIsAccepted(Boolean isAccepted) {
        this.isAccepted = isAccepted;
    }

    public LocalDate getDecreeDate() {
        return decreeDate;
    }

    public void setDecreeDate(LocalDate decreeDate) {
        this.decreeDate = decreeDate;
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

    public LocalDate getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(LocalDate applyDate) {
        this.applyDate = applyDate;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
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

    public Boolean getIsWithdrawal() {
        return isWithdrawal;
    }

    public void setIsWithdrawal(Boolean isWithdrawal) {
        this.isWithdrawal = isWithdrawal;
    }

    public String getWithdrawalDecreeNo() {
        return withdrawalDecreeNo;
    }

    public void setWithdrawalDecreeNo(String withdrawalDecreeNo) {
        this.withdrawalDecreeNo = withdrawalDecreeNo;
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
        if (!(o instanceof TrademarkDecreeDTO)) {
            return false;
        }

        TrademarkDecreeDTO trademarkDecreeDTO = (TrademarkDecreeDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, trademarkDecreeDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TrademarkDecreeDTO{" +
            "id=" + getId() +
            ", year=" + getYear() +
            ", decreeNo='" + getDecreeNo() + "'" +
            ", isAccepted='" + getIsAccepted() + "'" +
            ", decreeDate='" + getDecreeDate() + "'" +
            ", applicantName='" + getApplicantName() + "'" +
            ", tradeMarkOwner='" + getTradeMarkOwner() + "'" +
            ", country='" + getCountry() + "'" +
            ", applyDate='" + getApplyDate() + "'" +
            ", serialNo='" + getSerialNo() + "'" +
            ", trademarkEnglish='" + getTrademarkEnglish() + "'" +
            ", trademarkArabic='" + getTrademarkArabic() + "'" +
            ", category='" + getCategory() + "'" +
            ", pdfFile='" + getPdfFile() + "'" +
            ", pdfFileUrl='" + getPdfFileUrl() + "'" +
            ", extraPdfFile='" + getExtraPdfFile() + "'" +
            ", extraPdfFileUrl='" + getExtraPdfFileUrl() + "'" +
            ", isWithdrawal='" + getIsWithdrawal() + "'" +
            ", withdrawalDecreeNo='" + getWithdrawalDecreeNo() + "'" +
            ", notes='" + getNotes() + "'" +
            "}";
    }
}
