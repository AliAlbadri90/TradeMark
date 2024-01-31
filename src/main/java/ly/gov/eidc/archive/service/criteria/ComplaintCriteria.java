package ly.gov.eidc.archive.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import ly.gov.eidc.archive.domain.enumeration.ComplaintStatus;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.BooleanFilter;
import tech.jhipster.service.filter.DoubleFilter;
import tech.jhipster.service.filter.Filter;
import tech.jhipster.service.filter.FloatFilter;
import tech.jhipster.service.filter.IntegerFilter;
import tech.jhipster.service.filter.LocalDateFilter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link ly.gov.eidc.archive.domain.Complaint} entity. This class is used
 * in {@link ly.gov.eidc.archive.web.rest.ComplaintResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /complaints?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ComplaintCriteria implements Serializable, Criteria {

    /**
     * Class for filtering ComplaintStatus
     */
    public static class ComplaintStatusFilter extends Filter<ComplaintStatus> {

        public ComplaintStatusFilter() {}

        public ComplaintStatusFilter(ComplaintStatusFilter filter) {
            super(filter);
        }

        @Override
        public ComplaintStatusFilter copy() {
            return new ComplaintStatusFilter(this);
        }
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter complaintUUID;

    private StringFilter complaintNo;

    private StringFilter trademarkNo;

    private LocalDateFilter complaintDate;

    private LocalDateFilter complaintOfficeReceivedDate;

    private StringFilter complaintPaymentReceipt;

    private IntegerFilter complaintYear;

    private StringFilter complainerPersonFullName;

    private StringFilter complainerPersonJob;

    private StringFilter complainerPersonNationality;

    private StringFilter complainerPersonAddress;

    private StringFilter complainerCompanyName;

    private StringFilter complainerCompanyAddress;

    private StringFilter complainerCompanyPurpose;

    private StringFilter complainerCompanyHeadQuarterAddress;

    private StringFilter complainerCompanyLibyaAddress;

    private StringFilter pdfFileUrl;

    private ComplaintStatusFilter complaintStatus;

    private StringFilter complaintDetails;

    private StringFilter notes;

    private Boolean distinct;

    public ComplaintCriteria() {}

    public ComplaintCriteria(ComplaintCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.complaintUUID = other.complaintUUID == null ? null : other.complaintUUID.copy();
        this.complaintNo = other.complaintNo == null ? null : other.complaintNo.copy();
        this.trademarkNo = other.trademarkNo == null ? null : other.trademarkNo.copy();
        this.complaintDate = other.complaintDate == null ? null : other.complaintDate.copy();
        this.complaintOfficeReceivedDate = other.complaintOfficeReceivedDate == null ? null : other.complaintOfficeReceivedDate.copy();
        this.complaintPaymentReceipt = other.complaintPaymentReceipt == null ? null : other.complaintPaymentReceipt.copy();
        this.complaintYear = other.complaintYear == null ? null : other.complaintYear.copy();
        this.complainerPersonFullName = other.complainerPersonFullName == null ? null : other.complainerPersonFullName.copy();
        this.complainerPersonJob = other.complainerPersonJob == null ? null : other.complainerPersonJob.copy();
        this.complainerPersonNationality = other.complainerPersonNationality == null ? null : other.complainerPersonNationality.copy();
        this.complainerPersonAddress = other.complainerPersonAddress == null ? null : other.complainerPersonAddress.copy();
        this.complainerCompanyName = other.complainerCompanyName == null ? null : other.complainerCompanyName.copy();
        this.complainerCompanyAddress = other.complainerCompanyAddress == null ? null : other.complainerCompanyAddress.copy();
        this.complainerCompanyPurpose = other.complainerCompanyPurpose == null ? null : other.complainerCompanyPurpose.copy();
        this.complainerCompanyHeadQuarterAddress =
            other.complainerCompanyHeadQuarterAddress == null ? null : other.complainerCompanyHeadQuarterAddress.copy();
        this.complainerCompanyLibyaAddress =
            other.complainerCompanyLibyaAddress == null ? null : other.complainerCompanyLibyaAddress.copy();
        this.pdfFileUrl = other.pdfFileUrl == null ? null : other.pdfFileUrl.copy();
        this.complaintStatus = other.complaintStatus == null ? null : other.complaintStatus.copy();
        this.complaintDetails = other.complaintDetails == null ? null : other.complaintDetails.copy();
        this.notes = other.notes == null ? null : other.notes.copy();
        this.distinct = other.distinct;
    }

    @Override
    public ComplaintCriteria copy() {
        return new ComplaintCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public LongFilter id() {
        if (id == null) {
            id = new LongFilter();
        }
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getComplaintUUID() {
        return complaintUUID;
    }

    public StringFilter complaintUUID() {
        if (complaintUUID == null) {
            complaintUUID = new StringFilter();
        }
        return complaintUUID;
    }

    public void setComplaintUUID(StringFilter complaintUUID) {
        this.complaintUUID = complaintUUID;
    }

    public StringFilter getComplaintNo() {
        return complaintNo;
    }

    public StringFilter complaintNo() {
        if (complaintNo == null) {
            complaintNo = new StringFilter();
        }
        return complaintNo;
    }

    public void setComplaintNo(StringFilter complaintNo) {
        this.complaintNo = complaintNo;
    }

    public StringFilter getTrademarkNo() {
        return trademarkNo;
    }

    public StringFilter trademarkNo() {
        if (trademarkNo == null) {
            trademarkNo = new StringFilter();
        }
        return trademarkNo;
    }

    public void setTrademarkNo(StringFilter trademarkNo) {
        this.trademarkNo = trademarkNo;
    }

    public LocalDateFilter getComplaintDate() {
        return complaintDate;
    }

    public LocalDateFilter complaintDate() {
        if (complaintDate == null) {
            complaintDate = new LocalDateFilter();
        }
        return complaintDate;
    }

    public void setComplaintDate(LocalDateFilter complaintDate) {
        this.complaintDate = complaintDate;
    }

    public LocalDateFilter getComplaintOfficeReceivedDate() {
        return complaintOfficeReceivedDate;
    }

    public LocalDateFilter complaintOfficeReceivedDate() {
        if (complaintOfficeReceivedDate == null) {
            complaintOfficeReceivedDate = new LocalDateFilter();
        }
        return complaintOfficeReceivedDate;
    }

    public void setComplaintOfficeReceivedDate(LocalDateFilter complaintOfficeReceivedDate) {
        this.complaintOfficeReceivedDate = complaintOfficeReceivedDate;
    }

    public StringFilter getComplaintPaymentReceipt() {
        return complaintPaymentReceipt;
    }

    public StringFilter complaintPaymentReceipt() {
        if (complaintPaymentReceipt == null) {
            complaintPaymentReceipt = new StringFilter();
        }
        return complaintPaymentReceipt;
    }

    public void setComplaintPaymentReceipt(StringFilter complaintPaymentReceipt) {
        this.complaintPaymentReceipt = complaintPaymentReceipt;
    }

    public IntegerFilter getComplaintYear() {
        return complaintYear;
    }

    public IntegerFilter complaintYear() {
        if (complaintYear == null) {
            complaintYear = new IntegerFilter();
        }
        return complaintYear;
    }

    public void setComplaintYear(IntegerFilter complaintYear) {
        this.complaintYear = complaintYear;
    }

    public StringFilter getComplainerPersonFullName() {
        return complainerPersonFullName;
    }

    public StringFilter complainerPersonFullName() {
        if (complainerPersonFullName == null) {
            complainerPersonFullName = new StringFilter();
        }
        return complainerPersonFullName;
    }

    public void setComplainerPersonFullName(StringFilter complainerPersonFullName) {
        this.complainerPersonFullName = complainerPersonFullName;
    }

    public StringFilter getComplainerPersonJob() {
        return complainerPersonJob;
    }

    public StringFilter complainerPersonJob() {
        if (complainerPersonJob == null) {
            complainerPersonJob = new StringFilter();
        }
        return complainerPersonJob;
    }

    public void setComplainerPersonJob(StringFilter complainerPersonJob) {
        this.complainerPersonJob = complainerPersonJob;
    }

    public StringFilter getComplainerPersonNationality() {
        return complainerPersonNationality;
    }

    public StringFilter complainerPersonNationality() {
        if (complainerPersonNationality == null) {
            complainerPersonNationality = new StringFilter();
        }
        return complainerPersonNationality;
    }

    public void setComplainerPersonNationality(StringFilter complainerPersonNationality) {
        this.complainerPersonNationality = complainerPersonNationality;
    }

    public StringFilter getComplainerPersonAddress() {
        return complainerPersonAddress;
    }

    public StringFilter complainerPersonAddress() {
        if (complainerPersonAddress == null) {
            complainerPersonAddress = new StringFilter();
        }
        return complainerPersonAddress;
    }

    public void setComplainerPersonAddress(StringFilter complainerPersonAddress) {
        this.complainerPersonAddress = complainerPersonAddress;
    }

    public StringFilter getComplainerCompanyName() {
        return complainerCompanyName;
    }

    public StringFilter complainerCompanyName() {
        if (complainerCompanyName == null) {
            complainerCompanyName = new StringFilter();
        }
        return complainerCompanyName;
    }

    public void setComplainerCompanyName(StringFilter complainerCompanyName) {
        this.complainerCompanyName = complainerCompanyName;
    }

    public StringFilter getComplainerCompanyAddress() {
        return complainerCompanyAddress;
    }

    public StringFilter complainerCompanyAddress() {
        if (complainerCompanyAddress == null) {
            complainerCompanyAddress = new StringFilter();
        }
        return complainerCompanyAddress;
    }

    public void setComplainerCompanyAddress(StringFilter complainerCompanyAddress) {
        this.complainerCompanyAddress = complainerCompanyAddress;
    }

    public StringFilter getComplainerCompanyPurpose() {
        return complainerCompanyPurpose;
    }

    public StringFilter complainerCompanyPurpose() {
        if (complainerCompanyPurpose == null) {
            complainerCompanyPurpose = new StringFilter();
        }
        return complainerCompanyPurpose;
    }

    public void setComplainerCompanyPurpose(StringFilter complainerCompanyPurpose) {
        this.complainerCompanyPurpose = complainerCompanyPurpose;
    }

    public StringFilter getComplainerCompanyHeadQuarterAddress() {
        return complainerCompanyHeadQuarterAddress;
    }

    public StringFilter complainerCompanyHeadQuarterAddress() {
        if (complainerCompanyHeadQuarterAddress == null) {
            complainerCompanyHeadQuarterAddress = new StringFilter();
        }
        return complainerCompanyHeadQuarterAddress;
    }

    public void setComplainerCompanyHeadQuarterAddress(StringFilter complainerCompanyHeadQuarterAddress) {
        this.complainerCompanyHeadQuarterAddress = complainerCompanyHeadQuarterAddress;
    }

    public StringFilter getComplainerCompanyLibyaAddress() {
        return complainerCompanyLibyaAddress;
    }

    public StringFilter complainerCompanyLibyaAddress() {
        if (complainerCompanyLibyaAddress == null) {
            complainerCompanyLibyaAddress = new StringFilter();
        }
        return complainerCompanyLibyaAddress;
    }

    public void setComplainerCompanyLibyaAddress(StringFilter complainerCompanyLibyaAddress) {
        this.complainerCompanyLibyaAddress = complainerCompanyLibyaAddress;
    }

    public StringFilter getPdfFileUrl() {
        return pdfFileUrl;
    }

    public StringFilter pdfFileUrl() {
        if (pdfFileUrl == null) {
            pdfFileUrl = new StringFilter();
        }
        return pdfFileUrl;
    }

    public void setPdfFileUrl(StringFilter pdfFileUrl) {
        this.pdfFileUrl = pdfFileUrl;
    }

    public ComplaintStatusFilter getComplaintStatus() {
        return complaintStatus;
    }

    public ComplaintStatusFilter complaintStatus() {
        if (complaintStatus == null) {
            complaintStatus = new ComplaintStatusFilter();
        }
        return complaintStatus;
    }

    public void setComplaintStatus(ComplaintStatusFilter complaintStatus) {
        this.complaintStatus = complaintStatus;
    }

    public StringFilter getComplaintDetails() {
        return complaintDetails;
    }

    public StringFilter complaintDetails() {
        if (complaintDetails == null) {
            complaintDetails = new StringFilter();
        }
        return complaintDetails;
    }

    public void setComplaintDetails(StringFilter complaintDetails) {
        this.complaintDetails = complaintDetails;
    }

    public StringFilter getNotes() {
        return notes;
    }

    public StringFilter notes() {
        if (notes == null) {
            notes = new StringFilter();
        }
        return notes;
    }

    public void setNotes(StringFilter notes) {
        this.notes = notes;
    }

    public Boolean getDistinct() {
        return distinct;
    }

    public void setDistinct(Boolean distinct) {
        this.distinct = distinct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ComplaintCriteria that = (ComplaintCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(complaintUUID, that.complaintUUID) &&
            Objects.equals(complaintNo, that.complaintNo) &&
            Objects.equals(trademarkNo, that.trademarkNo) &&
            Objects.equals(complaintDate, that.complaintDate) &&
            Objects.equals(complaintOfficeReceivedDate, that.complaintOfficeReceivedDate) &&
            Objects.equals(complaintPaymentReceipt, that.complaintPaymentReceipt) &&
            Objects.equals(complaintYear, that.complaintYear) &&
            Objects.equals(complainerPersonFullName, that.complainerPersonFullName) &&
            Objects.equals(complainerPersonJob, that.complainerPersonJob) &&
            Objects.equals(complainerPersonNationality, that.complainerPersonNationality) &&
            Objects.equals(complainerPersonAddress, that.complainerPersonAddress) &&
            Objects.equals(complainerCompanyName, that.complainerCompanyName) &&
            Objects.equals(complainerCompanyAddress, that.complainerCompanyAddress) &&
            Objects.equals(complainerCompanyPurpose, that.complainerCompanyPurpose) &&
            Objects.equals(complainerCompanyHeadQuarterAddress, that.complainerCompanyHeadQuarterAddress) &&
            Objects.equals(complainerCompanyLibyaAddress, that.complainerCompanyLibyaAddress) &&
            Objects.equals(pdfFileUrl, that.pdfFileUrl) &&
            Objects.equals(complaintStatus, that.complaintStatus) &&
            Objects.equals(complaintDetails, that.complaintDetails) &&
            Objects.equals(notes, that.notes) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            complaintUUID,
            complaintNo,
            trademarkNo,
            complaintDate,
            complaintOfficeReceivedDate,
            complaintPaymentReceipt,
            complaintYear,
            complainerPersonFullName,
            complainerPersonJob,
            complainerPersonNationality,
            complainerPersonAddress,
            complainerCompanyName,
            complainerCompanyAddress,
            complainerCompanyPurpose,
            complainerCompanyHeadQuarterAddress,
            complainerCompanyLibyaAddress,
            pdfFileUrl,
            complaintStatus,
            complaintDetails,
            notes,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ComplaintCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (complaintUUID != null ? "complaintUUID=" + complaintUUID + ", " : "") +
            (complaintNo != null ? "complaintNo=" + complaintNo + ", " : "") +
            (trademarkNo != null ? "trademarkNo=" + trademarkNo + ", " : "") +
            (complaintDate != null ? "complaintDate=" + complaintDate + ", " : "") +
            (complaintOfficeReceivedDate != null ? "complaintOfficeReceivedDate=" + complaintOfficeReceivedDate + ", " : "") +
            (complaintPaymentReceipt != null ? "complaintPaymentReceipt=" + complaintPaymentReceipt + ", " : "") +
            (complaintYear != null ? "complaintYear=" + complaintYear + ", " : "") +
            (complainerPersonFullName != null ? "complainerPersonFullName=" + complainerPersonFullName + ", " : "") +
            (complainerPersonJob != null ? "complainerPersonJob=" + complainerPersonJob + ", " : "") +
            (complainerPersonNationality != null ? "complainerPersonNationality=" + complainerPersonNationality + ", " : "") +
            (complainerPersonAddress != null ? "complainerPersonAddress=" + complainerPersonAddress + ", " : "") +
            (complainerCompanyName != null ? "complainerCompanyName=" + complainerCompanyName + ", " : "") +
            (complainerCompanyAddress != null ? "complainerCompanyAddress=" + complainerCompanyAddress + ", " : "") +
            (complainerCompanyPurpose != null ? "complainerCompanyPurpose=" + complainerCompanyPurpose + ", " : "") +
            (complainerCompanyHeadQuarterAddress != null ? "complainerCompanyHeadQuarterAddress=" + complainerCompanyHeadQuarterAddress + ", " : "") +
            (complainerCompanyLibyaAddress != null ? "complainerCompanyLibyaAddress=" + complainerCompanyLibyaAddress + ", " : "") +
            (pdfFileUrl != null ? "pdfFileUrl=" + pdfFileUrl + ", " : "") +
            (complaintStatus != null ? "complaintStatus=" + complaintStatus + ", " : "") +
            (complaintDetails != null ? "complaintDetails=" + complaintDetails + ", " : "") +
            (notes != null ? "notes=" + notes + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
