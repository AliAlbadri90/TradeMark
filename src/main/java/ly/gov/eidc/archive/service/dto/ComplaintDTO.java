package ly.gov.eidc.archive.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Lob;
import ly.gov.eidc.archive.domain.enumeration.ComplaintStatus;

/**
 * A DTO for the {@link ly.gov.eidc.archive.domain.Complaint} entity.
 */
public class ComplaintDTO implements Serializable {

    private Long id;

    private String complaintUUID;

    private String complaintNo;

    private String trademarkNo;

    private LocalDate complaintDate;

    private LocalDate complaintOfficeReceivedDate;

    private String complaintPaymentReceipt;

    private Integer complaintYear;

    private String complainerPersonFullName;

    private String complainerPersonJob;

    private String complainerPersonNationality;

    private String complainerPersonAddress;

    private String complainerCompanyName;

    private String complainerCompanyAddress;

    private String complainerCompanyPurpose;

    private String complainerCompanyHeadQuarterAddress;

    private String complainerCompanyLibyaAddress;

    @Lob
    private byte[] pdfFile;

    private String pdfFileContentType;
    private String pdfFileUrl;

    private ComplaintStatus complaintStatus;

    private String complaintDetails;

    private String notes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComplaintUUID() {
        return complaintUUID;
    }

    public void setComplaintUUID(String complaintUUID) {
        this.complaintUUID = complaintUUID;
    }

    public String getComplaintNo() {
        return complaintNo;
    }

    public void setComplaintNo(String complaintNo) {
        this.complaintNo = complaintNo;
    }

    public String getTrademarkNo() {
        return trademarkNo;
    }

    public void setTrademarkNo(String trademarkNo) {
        this.trademarkNo = trademarkNo;
    }

    public LocalDate getComplaintDate() {
        return complaintDate;
    }

    public void setComplaintDate(LocalDate complaintDate) {
        this.complaintDate = complaintDate;
    }

    public LocalDate getComplaintOfficeReceivedDate() {
        return complaintOfficeReceivedDate;
    }

    public void setComplaintOfficeReceivedDate(LocalDate complaintOfficeReceivedDate) {
        this.complaintOfficeReceivedDate = complaintOfficeReceivedDate;
    }

    public String getComplaintPaymentReceipt() {
        return complaintPaymentReceipt;
    }

    public void setComplaintPaymentReceipt(String complaintPaymentReceipt) {
        this.complaintPaymentReceipt = complaintPaymentReceipt;
    }

    public Integer getComplaintYear() {
        return complaintYear;
    }

    public void setComplaintYear(Integer complaintYear) {
        this.complaintYear = complaintYear;
    }

    public String getComplainerPersonFullName() {
        return complainerPersonFullName;
    }

    public void setComplainerPersonFullName(String complainerPersonFullName) {
        this.complainerPersonFullName = complainerPersonFullName;
    }

    public String getComplainerPersonJob() {
        return complainerPersonJob;
    }

    public void setComplainerPersonJob(String complainerPersonJob) {
        this.complainerPersonJob = complainerPersonJob;
    }

    public String getComplainerPersonNationality() {
        return complainerPersonNationality;
    }

    public void setComplainerPersonNationality(String complainerPersonNationality) {
        this.complainerPersonNationality = complainerPersonNationality;
    }

    public String getComplainerPersonAddress() {
        return complainerPersonAddress;
    }

    public void setComplainerPersonAddress(String complainerPersonAddress) {
        this.complainerPersonAddress = complainerPersonAddress;
    }

    public String getComplainerCompanyName() {
        return complainerCompanyName;
    }

    public void setComplainerCompanyName(String complainerCompanyName) {
        this.complainerCompanyName = complainerCompanyName;
    }

    public String getComplainerCompanyAddress() {
        return complainerCompanyAddress;
    }

    public void setComplainerCompanyAddress(String complainerCompanyAddress) {
        this.complainerCompanyAddress = complainerCompanyAddress;
    }

    public String getComplainerCompanyPurpose() {
        return complainerCompanyPurpose;
    }

    public void setComplainerCompanyPurpose(String complainerCompanyPurpose) {
        this.complainerCompanyPurpose = complainerCompanyPurpose;
    }

    public String getComplainerCompanyHeadQuarterAddress() {
        return complainerCompanyHeadQuarterAddress;
    }

    public void setComplainerCompanyHeadQuarterAddress(String complainerCompanyHeadQuarterAddress) {
        this.complainerCompanyHeadQuarterAddress = complainerCompanyHeadQuarterAddress;
    }

    public String getComplainerCompanyLibyaAddress() {
        return complainerCompanyLibyaAddress;
    }

    public void setComplainerCompanyLibyaAddress(String complainerCompanyLibyaAddress) {
        this.complainerCompanyLibyaAddress = complainerCompanyLibyaAddress;
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

    public ComplaintStatus getComplaintStatus() {
        return complaintStatus;
    }

    public void setComplaintStatus(ComplaintStatus complaintStatus) {
        this.complaintStatus = complaintStatus;
    }

    public String getComplaintDetails() {
        return complaintDetails;
    }

    public void setComplaintDetails(String complaintDetails) {
        this.complaintDetails = complaintDetails;
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
        if (!(o instanceof ComplaintDTO)) {
            return false;
        }

        ComplaintDTO complaintDTO = (ComplaintDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, complaintDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ComplaintDTO{" +
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
            ", pdfFileUrl='" + getPdfFileUrl() + "'" +
            ", complaintStatus='" + getComplaintStatus() + "'" +
            ", complaintDetails='" + getComplaintDetails() + "'" +
            ", notes='" + getNotes() + "'" +
            "}";
    }
}
