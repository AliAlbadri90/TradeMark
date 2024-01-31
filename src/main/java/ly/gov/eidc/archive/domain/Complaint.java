package ly.gov.eidc.archive.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import ly.gov.eidc.archive.domain.enumeration.ComplaintStatus;

/**
 * A Complaint.
 */
@Entity
@Table(name = "complaint")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "complaint")
public class Complaint implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "complaint_uuid")
    private String complaintUUID;

    @Column(name = "complaint_no")
    private String complaintNo;

    @Column(name = "trademark_no")
    private String trademarkNo;

    @Column(name = "complaint_date")
    private LocalDate complaintDate;

    @Column(name = "complaint_office_received_date")
    private LocalDate complaintOfficeReceivedDate;

    @Column(name = "complaint_payment_receipt")
    private String complaintPaymentReceipt;

    @Column(name = "complaint_year")
    private Integer complaintYear;

    @Column(name = "complainer_person_full_name")
    private String complainerPersonFullName;

    @Column(name = "complainer_person_job")
    private String complainerPersonJob;

    @Column(name = "complainer_person_nationality")
    private String complainerPersonNationality;

    @Column(name = "complainer_person_address")
    private String complainerPersonAddress;

    @Column(name = "complainer_company_name")
    private String complainerCompanyName;

    @Column(name = "complainer_company_address")
    private String complainerCompanyAddress;

    @Column(name = "complainer_company_purpose")
    private String complainerCompanyPurpose;

    @Column(name = "complainer_company_head_quarter_address")
    private String complainerCompanyHeadQuarterAddress;

    @Column(name = "complainer_company_libya_address")
    private String complainerCompanyLibyaAddress;

    @Lob
    @Column(name = "pdf_file")
    private byte[] pdfFile;

    @Column(name = "pdf_file_content_type")
    private String pdfFileContentType;

    @Column(name = "pdf_file_url")
    private String pdfFileUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "complaint_status")
    private ComplaintStatus complaintStatus;

    @Column(name = "complaint_details")
    private String complaintDetails;

    @Column(name = "notes")
    private String notes;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Complaint id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComplaintUUID() {
        return this.complaintUUID;
    }

    public Complaint complaintUUID(String complaintUUID) {
        this.setComplaintUUID(complaintUUID);
        return this;
    }

    public void setComplaintUUID(String complaintUUID) {
        this.complaintUUID = complaintUUID;
    }

    public String getComplaintNo() {
        return this.complaintNo;
    }

    public Complaint complaintNo(String complaintNo) {
        this.setComplaintNo(complaintNo);
        return this;
    }

    public void setComplaintNo(String complaintNo) {
        this.complaintNo = complaintNo;
    }

    public String getTrademarkNo() {
        return this.trademarkNo;
    }

    public Complaint trademarkNo(String trademarkNo) {
        this.setTrademarkNo(trademarkNo);
        return this;
    }

    public void setTrademarkNo(String trademarkNo) {
        this.trademarkNo = trademarkNo;
    }

    public LocalDate getComplaintDate() {
        return this.complaintDate;
    }

    public Complaint complaintDate(LocalDate complaintDate) {
        this.setComplaintDate(complaintDate);
        return this;
    }

    public void setComplaintDate(LocalDate complaintDate) {
        this.complaintDate = complaintDate;
    }

    public LocalDate getComplaintOfficeReceivedDate() {
        return this.complaintOfficeReceivedDate;
    }

    public Complaint complaintOfficeReceivedDate(LocalDate complaintOfficeReceivedDate) {
        this.setComplaintOfficeReceivedDate(complaintOfficeReceivedDate);
        return this;
    }

    public void setComplaintOfficeReceivedDate(LocalDate complaintOfficeReceivedDate) {
        this.complaintOfficeReceivedDate = complaintOfficeReceivedDate;
    }

    public String getComplaintPaymentReceipt() {
        return this.complaintPaymentReceipt;
    }

    public Complaint complaintPaymentReceipt(String complaintPaymentReceipt) {
        this.setComplaintPaymentReceipt(complaintPaymentReceipt);
        return this;
    }

    public void setComplaintPaymentReceipt(String complaintPaymentReceipt) {
        this.complaintPaymentReceipt = complaintPaymentReceipt;
    }

    public Integer getComplaintYear() {
        return this.complaintYear;
    }

    public Complaint complaintYear(Integer complaintYear) {
        this.setComplaintYear(complaintYear);
        return this;
    }

    public void setComplaintYear(Integer complaintYear) {
        this.complaintYear = complaintYear;
    }

    public String getComplainerPersonFullName() {
        return this.complainerPersonFullName;
    }

    public Complaint complainerPersonFullName(String complainerPersonFullName) {
        this.setComplainerPersonFullName(complainerPersonFullName);
        return this;
    }

    public void setComplainerPersonFullName(String complainerPersonFullName) {
        this.complainerPersonFullName = complainerPersonFullName;
    }

    public String getComplainerPersonJob() {
        return this.complainerPersonJob;
    }

    public Complaint complainerPersonJob(String complainerPersonJob) {
        this.setComplainerPersonJob(complainerPersonJob);
        return this;
    }

    public void setComplainerPersonJob(String complainerPersonJob) {
        this.complainerPersonJob = complainerPersonJob;
    }

    public String getComplainerPersonNationality() {
        return this.complainerPersonNationality;
    }

    public Complaint complainerPersonNationality(String complainerPersonNationality) {
        this.setComplainerPersonNationality(complainerPersonNationality);
        return this;
    }

    public void setComplainerPersonNationality(String complainerPersonNationality) {
        this.complainerPersonNationality = complainerPersonNationality;
    }

    public String getComplainerPersonAddress() {
        return this.complainerPersonAddress;
    }

    public Complaint complainerPersonAddress(String complainerPersonAddress) {
        this.setComplainerPersonAddress(complainerPersonAddress);
        return this;
    }

    public void setComplainerPersonAddress(String complainerPersonAddress) {
        this.complainerPersonAddress = complainerPersonAddress;
    }

    public String getComplainerCompanyName() {
        return this.complainerCompanyName;
    }

    public Complaint complainerCompanyName(String complainerCompanyName) {
        this.setComplainerCompanyName(complainerCompanyName);
        return this;
    }

    public void setComplainerCompanyName(String complainerCompanyName) {
        this.complainerCompanyName = complainerCompanyName;
    }

    public String getComplainerCompanyAddress() {
        return this.complainerCompanyAddress;
    }

    public Complaint complainerCompanyAddress(String complainerCompanyAddress) {
        this.setComplainerCompanyAddress(complainerCompanyAddress);
        return this;
    }

    public void setComplainerCompanyAddress(String complainerCompanyAddress) {
        this.complainerCompanyAddress = complainerCompanyAddress;
    }

    public String getComplainerCompanyPurpose() {
        return this.complainerCompanyPurpose;
    }

    public Complaint complainerCompanyPurpose(String complainerCompanyPurpose) {
        this.setComplainerCompanyPurpose(complainerCompanyPurpose);
        return this;
    }

    public void setComplainerCompanyPurpose(String complainerCompanyPurpose) {
        this.complainerCompanyPurpose = complainerCompanyPurpose;
    }

    public String getComplainerCompanyHeadQuarterAddress() {
        return this.complainerCompanyHeadQuarterAddress;
    }

    public Complaint complainerCompanyHeadQuarterAddress(String complainerCompanyHeadQuarterAddress) {
        this.setComplainerCompanyHeadQuarterAddress(complainerCompanyHeadQuarterAddress);
        return this;
    }

    public void setComplainerCompanyHeadQuarterAddress(String complainerCompanyHeadQuarterAddress) {
        this.complainerCompanyHeadQuarterAddress = complainerCompanyHeadQuarterAddress;
    }

    public String getComplainerCompanyLibyaAddress() {
        return this.complainerCompanyLibyaAddress;
    }

    public Complaint complainerCompanyLibyaAddress(String complainerCompanyLibyaAddress) {
        this.setComplainerCompanyLibyaAddress(complainerCompanyLibyaAddress);
        return this;
    }

    public void setComplainerCompanyLibyaAddress(String complainerCompanyLibyaAddress) {
        this.complainerCompanyLibyaAddress = complainerCompanyLibyaAddress;
    }

    public byte[] getPdfFile() {
        return this.pdfFile;
    }

    public Complaint pdfFile(byte[] pdfFile) {
        this.setPdfFile(pdfFile);
        return this;
    }

    public void setPdfFile(byte[] pdfFile) {
        this.pdfFile = pdfFile;
    }

    public String getPdfFileContentType() {
        return this.pdfFileContentType;
    }

    public Complaint pdfFileContentType(String pdfFileContentType) {
        this.pdfFileContentType = pdfFileContentType;
        return this;
    }

    public void setPdfFileContentType(String pdfFileContentType) {
        this.pdfFileContentType = pdfFileContentType;
    }

    public String getPdfFileUrl() {
        return this.pdfFileUrl;
    }

    public Complaint pdfFileUrl(String pdfFileUrl) {
        this.setPdfFileUrl(pdfFileUrl);
        return this;
    }

    public void setPdfFileUrl(String pdfFileUrl) {
        this.pdfFileUrl = pdfFileUrl;
    }

    public ComplaintStatus getComplaintStatus() {
        return this.complaintStatus;
    }

    public Complaint complaintStatus(ComplaintStatus complaintStatus) {
        this.setComplaintStatus(complaintStatus);
        return this;
    }

    public void setComplaintStatus(ComplaintStatus complaintStatus) {
        this.complaintStatus = complaintStatus;
    }

    public String getComplaintDetails() {
        return this.complaintDetails;
    }

    public Complaint complaintDetails(String complaintDetails) {
        this.setComplaintDetails(complaintDetails);
        return this;
    }

    public void setComplaintDetails(String complaintDetails) {
        this.complaintDetails = complaintDetails;
    }

    public String getNotes() {
        return this.notes;
    }

    public Complaint notes(String notes) {
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
        if (!(o instanceof Complaint)) {
            return false;
        }
        return id != null && id.equals(((Complaint) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Complaint{" +
            "id=" + getId() +
            ", complaintUUID='" + getComplaintUUID() + "'" +
            ", complaintNo='" + getComplaintNo() + "'" +
            ", trademarkNo='" + getTrademarkNo() + "'" +
            ", complaintDate='" + getComplaintDate() + "'" +
            ", complaintOfficeReceivedDate='" + getComplaintOfficeReceivedDate() + "'" +
            ", complaintPaymentReceipt='" + getComplaintPaymentReceipt() + "'" +
            ", complaintYear=" + getComplaintYear() +
            ", complainerPersonFullName='" + getComplainerPersonFullName() + "'" +
            ", complainerPersonJob='" + getComplainerPersonJob() + "'" +
            ", complainerPersonNationality='" + getComplainerPersonNationality() + "'" +
            ", complainerPersonAddress='" + getComplainerPersonAddress() + "'" +
            ", complainerCompanyName='" + getComplainerCompanyName() + "'" +
            ", complainerCompanyAddress='" + getComplainerCompanyAddress() + "'" +
            ", complainerCompanyPurpose='" + getComplainerCompanyPurpose() + "'" +
            ", complainerCompanyHeadQuarterAddress='" + getComplainerCompanyHeadQuarterAddress() + "'" +
            ", complainerCompanyLibyaAddress='" + getComplainerCompanyLibyaAddress() + "'" +
            ", pdfFile='" + getPdfFile() + "'" +
            ", pdfFileContentType='" + getPdfFileContentType() + "'" +
            ", pdfFileUrl='" + getPdfFileUrl() + "'" +
            ", complaintStatus='" + getComplaintStatus() + "'" +
            ", complaintDetails='" + getComplaintDetails() + "'" +
            ", notes='" + getNotes() + "'" +
            "}";
    }
}
