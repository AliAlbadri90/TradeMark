package ly.gov.eidc.archive.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;

/**
 * A TrademarkDecree.
 */
@Entity
@Table(name = "trademark_decree")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "trademarkdecree")
public class TrademarkDecree implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "year")
    private Integer year;

    @Column(name = "decree_no")
    private String decreeNo;

    @Column(name = "is_accepted")
    private Boolean isAccepted;

    @Column(name = "decree_date")
    private LocalDate decreeDate;

    @Column(name = "applicant_name")
    private String applicantName;

    @Column(name = "trade_mark_owner")
    private String tradeMarkOwner;

    @Column(name = "country")
    private String country;

    @Column(name = "apply_date")
    private LocalDate applyDate;

    @Column(name = "serial_no")
    private String serialNo;

    @Column(name = "trademark_english")
    private String trademarkEnglish;

    @Column(name = "trademark_arabic")
    private String trademarkArabic;

    @Column(name = "category")
    private String category;

    @Lob
    @Column(name = "pdf_file")
    private byte[] pdfFile;

    @Column(name = "pdf_file_content_type")
    private String pdfFileContentType;

    @Column(name = "pdf_file_url")
    private String pdfFileUrl;

    @Lob
    @Column(name = "extra_pdf_file")
    private byte[] extraPdfFile;

    @Column(name = "extra_pdf_file_content_type")
    private String extraPdfFileContentType;

    @Column(name = "extra_pdf_file_url")
    private String extraPdfFileUrl;

    @Column(name = "is_withdrawal")
    private Boolean isWithdrawal;

    @Column(name = "withdrawal_decree_no")
    private String withdrawalDecreeNo;

    @Column(name = "notes")
    private String notes;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public TrademarkDecree id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getYear() {
        return this.year;
    }

    public TrademarkDecree year(Integer year) {
        this.setYear(year);
        return this;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getDecreeNo() {
        return this.decreeNo;
    }

    public TrademarkDecree decreeNo(String decreeNo) {
        this.setDecreeNo(decreeNo);
        return this;
    }

    public void setDecreeNo(String decreeNo) {
        this.decreeNo = decreeNo;
    }

    public Boolean getIsAccepted() {
        return this.isAccepted;
    }

    public TrademarkDecree isAccepted(Boolean isAccepted) {
        this.setIsAccepted(isAccepted);
        return this;
    }

    public void setIsAccepted(Boolean isAccepted) {
        this.isAccepted = isAccepted;
    }

    public LocalDate getDecreeDate() {
        return this.decreeDate;
    }

    public TrademarkDecree decreeDate(LocalDate decreeDate) {
        this.setDecreeDate(decreeDate);
        return this;
    }

    public void setDecreeDate(LocalDate decreeDate) {
        this.decreeDate = decreeDate;
    }

    public String getApplicantName() {
        return this.applicantName;
    }

    public TrademarkDecree applicantName(String applicantName) {
        this.setApplicantName(applicantName);
        return this;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getTradeMarkOwner() {
        return this.tradeMarkOwner;
    }

    public TrademarkDecree tradeMarkOwner(String tradeMarkOwner) {
        this.setTradeMarkOwner(tradeMarkOwner);
        return this;
    }

    public void setTradeMarkOwner(String tradeMarkOwner) {
        this.tradeMarkOwner = tradeMarkOwner;
    }

    public String getCountry() {
        return this.country;
    }

    public TrademarkDecree country(String country) {
        this.setCountry(country);
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDate getApplyDate() {
        return this.applyDate;
    }

    public TrademarkDecree applyDate(LocalDate applyDate) {
        this.setApplyDate(applyDate);
        return this;
    }

    public void setApplyDate(LocalDate applyDate) {
        this.applyDate = applyDate;
    }

    public String getSerialNo() {
        return this.serialNo;
    }

    public TrademarkDecree serialNo(String serialNo) {
        this.setSerialNo(serialNo);
        return this;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getTrademarkEnglish() {
        return this.trademarkEnglish;
    }

    public TrademarkDecree trademarkEnglish(String trademarkEnglish) {
        this.setTrademarkEnglish(trademarkEnglish);
        return this;
    }

    public void setTrademarkEnglish(String trademarkEnglish) {
        this.trademarkEnglish = trademarkEnglish;
    }

    public String getTrademarkArabic() {
        return this.trademarkArabic;
    }

    public TrademarkDecree trademarkArabic(String trademarkArabic) {
        this.setTrademarkArabic(trademarkArabic);
        return this;
    }

    public void setTrademarkArabic(String trademarkArabic) {
        this.trademarkArabic = trademarkArabic;
    }

    public String getCategory() {
        return this.category;
    }

    public TrademarkDecree category(String category) {
        this.setCategory(category);
        return this;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public byte[] getPdfFile() {
        return this.pdfFile;
    }

    public TrademarkDecree pdfFile(byte[] pdfFile) {
        this.setPdfFile(pdfFile);
        return this;
    }

    public void setPdfFile(byte[] pdfFile) {
        this.pdfFile = pdfFile;
    }

    public String getPdfFileContentType() {
        return this.pdfFileContentType;
    }

    public TrademarkDecree pdfFileContentType(String pdfFileContentType) {
        this.pdfFileContentType = pdfFileContentType;
        return this;
    }

    public void setPdfFileContentType(String pdfFileContentType) {
        this.pdfFileContentType = pdfFileContentType;
    }

    public String getPdfFileUrl() {
        return this.pdfFileUrl;
    }

    public TrademarkDecree pdfFileUrl(String pdfFileUrl) {
        this.setPdfFileUrl(pdfFileUrl);
        return this;
    }

    public void setPdfFileUrl(String pdfFileUrl) {
        this.pdfFileUrl = pdfFileUrl;
    }

    public byte[] getExtraPdfFile() {
        return this.extraPdfFile;
    }

    public TrademarkDecree extraPdfFile(byte[] extraPdfFile) {
        this.setExtraPdfFile(extraPdfFile);
        return this;
    }

    public void setExtraPdfFile(byte[] extraPdfFile) {
        this.extraPdfFile = extraPdfFile;
    }

    public String getExtraPdfFileContentType() {
        return this.extraPdfFileContentType;
    }

    public TrademarkDecree extraPdfFileContentType(String extraPdfFileContentType) {
        this.extraPdfFileContentType = extraPdfFileContentType;
        return this;
    }

    public void setExtraPdfFileContentType(String extraPdfFileContentType) {
        this.extraPdfFileContentType = extraPdfFileContentType;
    }

    public String getExtraPdfFileUrl() {
        return this.extraPdfFileUrl;
    }

    public TrademarkDecree extraPdfFileUrl(String extraPdfFileUrl) {
        this.setExtraPdfFileUrl(extraPdfFileUrl);
        return this;
    }

    public void setExtraPdfFileUrl(String extraPdfFileUrl) {
        this.extraPdfFileUrl = extraPdfFileUrl;
    }

    public Boolean getIsWithdrawal() {
        return this.isWithdrawal;
    }

    public TrademarkDecree isWithdrawal(Boolean isWithdrawal) {
        this.setIsWithdrawal(isWithdrawal);
        return this;
    }

    public void setIsWithdrawal(Boolean isWithdrawal) {
        this.isWithdrawal = isWithdrawal;
    }

    public String getWithdrawalDecreeNo() {
        return this.withdrawalDecreeNo;
    }

    public TrademarkDecree withdrawalDecreeNo(String withdrawalDecreeNo) {
        this.setWithdrawalDecreeNo(withdrawalDecreeNo);
        return this;
    }

    public void setWithdrawalDecreeNo(String withdrawalDecreeNo) {
        this.withdrawalDecreeNo = withdrawalDecreeNo;
    }

    public String getNotes() {
        return this.notes;
    }

    public TrademarkDecree notes(String notes) {
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
        if (!(o instanceof TrademarkDecree)) {
            return false;
        }
        return id != null && id.equals(((TrademarkDecree) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TrademarkDecree{" +
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
            ", pdfFileContentType='" + getPdfFileContentType() + "'" +
            ", pdfFileUrl='" + getPdfFileUrl() + "'" +
            ", extraPdfFile='" + getExtraPdfFile() + "'" +
            ", extraPdfFileContentType='" + getExtraPdfFileContentType() + "'" +
            ", extraPdfFileUrl='" + getExtraPdfFileUrl() + "'" +
            ", isWithdrawal='" + getIsWithdrawal() + "'" +
            ", withdrawalDecreeNo='" + getWithdrawalDecreeNo() + "'" +
            ", notes='" + getNotes() + "'" +
            "}";
    }
}
