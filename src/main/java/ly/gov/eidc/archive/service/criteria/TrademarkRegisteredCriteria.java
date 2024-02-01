package ly.gov.eidc.archive.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import ly.gov.eidc.archive.domain.enumeration.TrademarkRegisteredStatus;
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
 * Criteria class for the {@link ly.gov.eidc.archive.domain.TrademarkRegistered} entity. This class is used
 * in {@link ly.gov.eidc.archive.web.rest.TrademarkRegisteredResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /trademark-registereds?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class TrademarkRegisteredCriteria implements Serializable, Criteria {

    public Boolean getOr() {
        return isOr;
    }

    public void setOr(Boolean or) {
        isOr = or;
    }

    /**
     * Class for filtering TrademarkRegisteredStatus
     */
    public static class TrademarkRegisteredStatusFilter extends Filter<TrademarkRegisteredStatus> {

        public TrademarkRegisteredStatusFilter() {}

        public TrademarkRegisteredStatusFilter(TrademarkRegisteredStatusFilter filter) {
            super(filter);
        }

        @Override
        public TrademarkRegisteredStatusFilter copy() {
            return new TrademarkRegisteredStatusFilter(this);
        }
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter trademarkUUID;

    private StringFilter trademarkNo;

    private IntegerFilter year;

    private StringFilter decreeNo;

    private StringFilter applicantName;

    private StringFilter tradeMarkOwner;

    private StringFilter country;

    private StringFilter nationality;

    private StringFilter address;

    private LocalDateFilter applyDate;

    private StringFilter trademarkEnglish;

    private StringFilter trademarkArabic;

    private StringFilter category;

    private StringFilter imageFileUrl;

    private StringFilter fileUrl;

    private StringFilter extraFileUrl;

    private LocalDateFilter publicationDate;

    private IntegerFilter publicationNo;

    private TrademarkRegisteredStatusFilter trademarkRegisteredStatus;

    private BooleanFilter isHidden;

    private StringFilter notes;

    private Boolean distinct;

    private Boolean isOr = false;

    public TrademarkRegisteredCriteria() {}

    public TrademarkRegisteredCriteria(TrademarkRegisteredCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.trademarkUUID = other.trademarkUUID == null ? null : other.trademarkUUID.copy();
        this.trademarkNo = other.trademarkNo == null ? null : other.trademarkNo.copy();
        this.year = other.year == null ? null : other.year.copy();
        this.decreeNo = other.decreeNo == null ? null : other.decreeNo.copy();
        this.applicantName = other.applicantName == null ? null : other.applicantName.copy();
        this.tradeMarkOwner = other.tradeMarkOwner == null ? null : other.tradeMarkOwner.copy();
        this.country = other.country == null ? null : other.country.copy();
        this.nationality = other.nationality == null ? null : other.nationality.copy();
        this.address = other.address == null ? null : other.address.copy();
        this.applyDate = other.applyDate == null ? null : other.applyDate.copy();
        this.trademarkEnglish = other.trademarkEnglish == null ? null : other.trademarkEnglish.copy();
        this.trademarkArabic = other.trademarkArabic == null ? null : other.trademarkArabic.copy();
        this.category = other.category == null ? null : other.category.copy();
        this.imageFileUrl = other.imageFileUrl == null ? null : other.imageFileUrl.copy();
        this.fileUrl = other.fileUrl == null ? null : other.fileUrl.copy();
        this.extraFileUrl = other.extraFileUrl == null ? null : other.extraFileUrl.copy();
        this.publicationDate = other.publicationDate == null ? null : other.publicationDate.copy();
        this.publicationNo = other.publicationNo == null ? null : other.publicationNo.copy();
        this.trademarkRegisteredStatus = other.trademarkRegisteredStatus == null ? null : other.trademarkRegisteredStatus.copy();
        this.isHidden = other.isHidden == null ? null : other.isHidden.copy();
        this.notes = other.notes == null ? null : other.notes.copy();
        this.distinct = other.distinct;
    }

    @Override
    public TrademarkRegisteredCriteria copy() {
        return new TrademarkRegisteredCriteria(this);
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

    public StringFilter getTrademarkUUID() {
        return trademarkUUID;
    }

    public StringFilter trademarkUUID() {
        if (trademarkUUID == null) {
            trademarkUUID = new StringFilter();
        }
        return trademarkUUID;
    }

    public void setTrademarkUUID(StringFilter trademarkUUID) {
        this.trademarkUUID = trademarkUUID;
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

    public IntegerFilter getYear() {
        return year;
    }

    public IntegerFilter year() {
        if (year == null) {
            year = new IntegerFilter();
        }
        return year;
    }

    public void setYear(IntegerFilter year) {
        this.year = year;
    }

    public StringFilter getDecreeNo() {
        return decreeNo;
    }

    public StringFilter decreeNo() {
        if (decreeNo == null) {
            decreeNo = new StringFilter();
        }
        return decreeNo;
    }

    public void setDecreeNo(StringFilter decreeNo) {
        this.decreeNo = decreeNo;
    }

    public StringFilter getApplicantName() {
        return applicantName;
    }

    public StringFilter applicantName() {
        if (applicantName == null) {
            applicantName = new StringFilter();
        }
        return applicantName;
    }

    public void setApplicantName(StringFilter applicantName) {
        this.applicantName = applicantName;
    }

    public StringFilter getTradeMarkOwner() {
        return tradeMarkOwner;
    }

    public StringFilter tradeMarkOwner() {
        if (tradeMarkOwner == null) {
            tradeMarkOwner = new StringFilter();
        }
        return tradeMarkOwner;
    }

    public void setTradeMarkOwner(StringFilter tradeMarkOwner) {
        this.tradeMarkOwner = tradeMarkOwner;
    }

    public StringFilter getCountry() {
        return country;
    }

    public StringFilter country() {
        if (country == null) {
            country = new StringFilter();
        }
        return country;
    }

    public void setCountry(StringFilter country) {
        this.country = country;
    }

    public StringFilter getNationality() {
        return nationality;
    }

    public StringFilter nationality() {
        if (nationality == null) {
            nationality = new StringFilter();
        }
        return nationality;
    }

    public void setNationality(StringFilter nationality) {
        this.nationality = nationality;
    }

    public StringFilter getAddress() {
        return address;
    }

    public StringFilter address() {
        if (address == null) {
            address = new StringFilter();
        }
        return address;
    }

    public void setAddress(StringFilter address) {
        this.address = address;
    }

    public LocalDateFilter getApplyDate() {
        return applyDate;
    }

    public LocalDateFilter applyDate() {
        if (applyDate == null) {
            applyDate = new LocalDateFilter();
        }
        return applyDate;
    }

    public void setApplyDate(LocalDateFilter applyDate) {
        this.applyDate = applyDate;
    }

    public StringFilter getTrademarkEnglish() {
        return trademarkEnglish;
    }

    public StringFilter trademarkEnglish() {
        if (trademarkEnglish == null) {
            trademarkEnglish = new StringFilter();
        }
        return trademarkEnglish;
    }

    public void setTrademarkEnglish(StringFilter trademarkEnglish) {
        this.trademarkEnglish = trademarkEnglish;
    }

    public StringFilter getTrademarkArabic() {
        return trademarkArabic;
    }

    public StringFilter trademarkArabic() {
        if (trademarkArabic == null) {
            trademarkArabic = new StringFilter();
        }
        return trademarkArabic;
    }

    public void setTrademarkArabic(StringFilter trademarkArabic) {
        this.trademarkArabic = trademarkArabic;
    }

    public StringFilter getCategory() {
        return category;
    }

    public StringFilter category() {
        if (category == null) {
            category = new StringFilter();
        }
        return category;
    }

    public void setCategory(StringFilter category) {
        this.category = category;
    }

    public StringFilter getImageFileUrl() {
        return imageFileUrl;
    }

    public StringFilter imageFileUrl() {
        if (imageFileUrl == null) {
            imageFileUrl = new StringFilter();
        }
        return imageFileUrl;
    }

    public void setImageFileUrl(StringFilter imageFileUrl) {
        this.imageFileUrl = imageFileUrl;
    }

    public StringFilter getFileUrl() {
        return fileUrl;
    }

    public StringFilter fileUrl() {
        if (fileUrl == null) {
            fileUrl = new StringFilter();
        }
        return fileUrl;
    }

    public void setFileUrl(StringFilter fileUrl) {
        this.fileUrl = fileUrl;
    }

    public StringFilter getExtraFileUrl() {
        return extraFileUrl;
    }

    public StringFilter extraFileUrl() {
        if (extraFileUrl == null) {
            extraFileUrl = new StringFilter();
        }
        return extraFileUrl;
    }

    public void setExtraFileUrl(StringFilter extraFileUrl) {
        this.extraFileUrl = extraFileUrl;
    }

    public LocalDateFilter getPublicationDate() {
        return publicationDate;
    }

    public LocalDateFilter publicationDate() {
        if (publicationDate == null) {
            publicationDate = new LocalDateFilter();
        }
        return publicationDate;
    }

    public void setPublicationDate(LocalDateFilter publicationDate) {
        this.publicationDate = publicationDate;
    }

    public IntegerFilter getPublicationNo() {
        return publicationNo;
    }

    public IntegerFilter publicationNo() {
        if (publicationNo == null) {
            publicationNo = new IntegerFilter();
        }
        return publicationNo;
    }

    public void setPublicationNo(IntegerFilter publicationNo) {
        this.publicationNo = publicationNo;
    }

    public TrademarkRegisteredStatusFilter getTrademarkRegisteredStatus() {
        return trademarkRegisteredStatus;
    }

    public TrademarkRegisteredStatusFilter trademarkRegisteredStatus() {
        if (trademarkRegisteredStatus == null) {
            trademarkRegisteredStatus = new TrademarkRegisteredStatusFilter();
        }
        return trademarkRegisteredStatus;
    }

    public void setTrademarkRegisteredStatus(TrademarkRegisteredStatusFilter trademarkRegisteredStatus) {
        this.trademarkRegisteredStatus = trademarkRegisteredStatus;
    }

    public BooleanFilter getIsHidden() {
        return isHidden;
    }

    public BooleanFilter isHidden() {
        if (isHidden == null) {
            isHidden = new BooleanFilter();
        }
        return isHidden;
    }

    public void setIsHidden(BooleanFilter isHidden) {
        this.isHidden = isHidden;
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
        final TrademarkRegisteredCriteria that = (TrademarkRegisteredCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(trademarkUUID, that.trademarkUUID) &&
            Objects.equals(trademarkNo, that.trademarkNo) &&
            Objects.equals(year, that.year) &&
            Objects.equals(decreeNo, that.decreeNo) &&
            Objects.equals(applicantName, that.applicantName) &&
            Objects.equals(tradeMarkOwner, that.tradeMarkOwner) &&
            Objects.equals(country, that.country) &&
            Objects.equals(nationality, that.nationality) &&
            Objects.equals(address, that.address) &&
            Objects.equals(applyDate, that.applyDate) &&
            Objects.equals(trademarkEnglish, that.trademarkEnglish) &&
            Objects.equals(trademarkArabic, that.trademarkArabic) &&
            Objects.equals(category, that.category) &&
            Objects.equals(imageFileUrl, that.imageFileUrl) &&
            Objects.equals(fileUrl, that.fileUrl) &&
            Objects.equals(extraFileUrl, that.extraFileUrl) &&
            Objects.equals(publicationDate, that.publicationDate) &&
            Objects.equals(publicationNo, that.publicationNo) &&
            Objects.equals(trademarkRegisteredStatus, that.trademarkRegisteredStatus) &&
            Objects.equals(isHidden, that.isHidden) &&
            Objects.equals(notes, that.notes) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            trademarkUUID,
            trademarkNo,
            year,
            decreeNo,
            applicantName,
            tradeMarkOwner,
            country,
            nationality,
            address,
            applyDate,
            trademarkEnglish,
            trademarkArabic,
            category,
            imageFileUrl,
            fileUrl,
            extraFileUrl,
            publicationDate,
            publicationNo,
            trademarkRegisteredStatus,
            isHidden,
            notes,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TrademarkRegisteredCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (trademarkUUID != null ? "trademarkUUID=" + trademarkUUID + ", " : "") +
            (trademarkNo != null ? "trademarkNo=" + trademarkNo + ", " : "") +
            (year != null ? "year=" + year + ", " : "") +
            (decreeNo != null ? "decreeNo=" + decreeNo + ", " : "") +
            (applicantName != null ? "applicantName=" + applicantName + ", " : "") +
            (tradeMarkOwner != null ? "tradeMarkOwner=" + tradeMarkOwner + ", " : "") +
            (country != null ? "country=" + country + ", " : "") +
            (nationality != null ? "nationality=" + nationality + ", " : "") +
            (address != null ? "address=" + address + ", " : "") +
            (applyDate != null ? "applyDate=" + applyDate + ", " : "") +
            (trademarkEnglish != null ? "trademarkEnglish=" + trademarkEnglish + ", " : "") +
            (trademarkArabic != null ? "trademarkArabic=" + trademarkArabic + ", " : "") +
            (category != null ? "category=" + category + ", " : "") +
            (imageFileUrl != null ? "imageFileUrl=" + imageFileUrl + ", " : "") +
            (fileUrl != null ? "fileUrl=" + fileUrl + ", " : "") +
            (extraFileUrl != null ? "extraFileUrl=" + extraFileUrl + ", " : "") +
            (publicationDate != null ? "publicationDate=" + publicationDate + ", " : "") +
            (publicationNo != null ? "publicationNo=" + publicationNo + ", " : "") +
            (trademarkRegisteredStatus != null ? "trademarkRegisteredStatus=" + trademarkRegisteredStatus + ", " : "") +
            (isHidden != null ? "isHidden=" + isHidden + ", " : "") +
            (notes != null ? "notes=" + notes + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
