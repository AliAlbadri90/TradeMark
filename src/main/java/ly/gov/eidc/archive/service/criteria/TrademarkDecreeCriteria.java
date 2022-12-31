package ly.gov.eidc.archive.service.criteria;

import java.io.Serializable;
import java.util.Objects;
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
 * Criteria class for the {@link ly.gov.eidc.archive.domain.TrademarkDecree} entity. This class is used
 * in {@link ly.gov.eidc.archive.web.rest.TrademarkDecreeResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /trademark-decrees?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class TrademarkDecreeCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter year;

    private StringFilter decreeNo;

    private BooleanFilter isAccepted;

    private LocalDateFilter decreeDate;

    private StringFilter applicantName;

    private StringFilter tradeMarkOwner;

    private StringFilter country;

    private LocalDateFilter applyDate;

    private StringFilter serialNo;

    private StringFilter trademarkEnglish;

    private StringFilter trademarkArabic;

    private StringFilter category;

    private StringFilter pdfFileUrl;

    private StringFilter extraPdfFileUrl;

    private BooleanFilter isWithdrawal;

    private StringFilter withdrawalDecreeNo;

    private StringFilter notes;

    private Boolean distinct;

    public TrademarkDecreeCriteria() {}

    public TrademarkDecreeCriteria(TrademarkDecreeCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.year = other.year == null ? null : other.year.copy();
        this.decreeNo = other.decreeNo == null ? null : other.decreeNo.copy();
        this.isAccepted = other.isAccepted == null ? null : other.isAccepted.copy();
        this.decreeDate = other.decreeDate == null ? null : other.decreeDate.copy();
        this.applicantName = other.applicantName == null ? null : other.applicantName.copy();
        this.tradeMarkOwner = other.tradeMarkOwner == null ? null : other.tradeMarkOwner.copy();
        this.country = other.country == null ? null : other.country.copy();
        this.applyDate = other.applyDate == null ? null : other.applyDate.copy();
        this.serialNo = other.serialNo == null ? null : other.serialNo.copy();
        this.trademarkEnglish = other.trademarkEnglish == null ? null : other.trademarkEnglish.copy();
        this.trademarkArabic = other.trademarkArabic == null ? null : other.trademarkArabic.copy();
        this.category = other.category == null ? null : other.category.copy();
        this.pdfFileUrl = other.pdfFileUrl == null ? null : other.pdfFileUrl.copy();
        this.extraPdfFileUrl = other.extraPdfFileUrl == null ? null : other.extraPdfFileUrl.copy();
        this.isWithdrawal = other.isWithdrawal == null ? null : other.isWithdrawal.copy();
        this.withdrawalDecreeNo = other.withdrawalDecreeNo == null ? null : other.withdrawalDecreeNo.copy();
        this.notes = other.notes == null ? null : other.notes.copy();
        this.distinct = other.distinct;
    }

    @Override
    public TrademarkDecreeCriteria copy() {
        return new TrademarkDecreeCriteria(this);
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

    public BooleanFilter getIsAccepted() {
        return isAccepted;
    }

    public BooleanFilter isAccepted() {
        if (isAccepted == null) {
            isAccepted = new BooleanFilter();
        }
        return isAccepted;
    }

    public void setIsAccepted(BooleanFilter isAccepted) {
        this.isAccepted = isAccepted;
    }

    public LocalDateFilter getDecreeDate() {
        return decreeDate;
    }

    public LocalDateFilter decreeDate() {
        if (decreeDate == null) {
            decreeDate = new LocalDateFilter();
        }
        return decreeDate;
    }

    public void setDecreeDate(LocalDateFilter decreeDate) {
        this.decreeDate = decreeDate;
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

    public StringFilter getSerialNo() {
        return serialNo;
    }

    public StringFilter serialNo() {
        if (serialNo == null) {
            serialNo = new StringFilter();
        }
        return serialNo;
    }

    public void setSerialNo(StringFilter serialNo) {
        this.serialNo = serialNo;
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

    public StringFilter getExtraPdfFileUrl() {
        return extraPdfFileUrl;
    }

    public StringFilter extraPdfFileUrl() {
        if (extraPdfFileUrl == null) {
            extraPdfFileUrl = new StringFilter();
        }
        return extraPdfFileUrl;
    }

    public void setExtraPdfFileUrl(StringFilter extraPdfFileUrl) {
        this.extraPdfFileUrl = extraPdfFileUrl;
    }

    public BooleanFilter getIsWithdrawal() {
        return isWithdrawal;
    }

    public BooleanFilter isWithdrawal() {
        if (isWithdrawal == null) {
            isWithdrawal = new BooleanFilter();
        }
        return isWithdrawal;
    }

    public void setIsWithdrawal(BooleanFilter isWithdrawal) {
        this.isWithdrawal = isWithdrawal;
    }

    public StringFilter getWithdrawalDecreeNo() {
        return withdrawalDecreeNo;
    }

    public StringFilter withdrawalDecreeNo() {
        if (withdrawalDecreeNo == null) {
            withdrawalDecreeNo = new StringFilter();
        }
        return withdrawalDecreeNo;
    }

    public void setWithdrawalDecreeNo(StringFilter withdrawalDecreeNo) {
        this.withdrawalDecreeNo = withdrawalDecreeNo;
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
        final TrademarkDecreeCriteria that = (TrademarkDecreeCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(year, that.year) &&
            Objects.equals(decreeNo, that.decreeNo) &&
            Objects.equals(isAccepted, that.isAccepted) &&
            Objects.equals(decreeDate, that.decreeDate) &&
            Objects.equals(applicantName, that.applicantName) &&
            Objects.equals(tradeMarkOwner, that.tradeMarkOwner) &&
            Objects.equals(country, that.country) &&
            Objects.equals(applyDate, that.applyDate) &&
            Objects.equals(serialNo, that.serialNo) &&
            Objects.equals(trademarkEnglish, that.trademarkEnglish) &&
            Objects.equals(trademarkArabic, that.trademarkArabic) &&
            Objects.equals(category, that.category) &&
            Objects.equals(pdfFileUrl, that.pdfFileUrl) &&
            Objects.equals(extraPdfFileUrl, that.extraPdfFileUrl) &&
            Objects.equals(isWithdrawal, that.isWithdrawal) &&
            Objects.equals(withdrawalDecreeNo, that.withdrawalDecreeNo) &&
            Objects.equals(notes, that.notes) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            year,
            decreeNo,
            isAccepted,
            decreeDate,
            applicantName,
            tradeMarkOwner,
            country,
            applyDate,
            serialNo,
            trademarkEnglish,
            trademarkArabic,
            category,
            pdfFileUrl,
            extraPdfFileUrl,
            isWithdrawal,
            withdrawalDecreeNo,
            notes,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TrademarkDecreeCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (year != null ? "year=" + year + ", " : "") +
            (decreeNo != null ? "decreeNo=" + decreeNo + ", " : "") +
            (isAccepted != null ? "isAccepted=" + isAccepted + ", " : "") +
            (decreeDate != null ? "decreeDate=" + decreeDate + ", " : "") +
            (applicantName != null ? "applicantName=" + applicantName + ", " : "") +
            (tradeMarkOwner != null ? "tradeMarkOwner=" + tradeMarkOwner + ", " : "") +
            (country != null ? "country=" + country + ", " : "") +
            (applyDate != null ? "applyDate=" + applyDate + ", " : "") +
            (serialNo != null ? "serialNo=" + serialNo + ", " : "") +
            (trademarkEnglish != null ? "trademarkEnglish=" + trademarkEnglish + ", " : "") +
            (trademarkArabic != null ? "trademarkArabic=" + trademarkArabic + ", " : "") +
            (category != null ? "category=" + category + ", " : "") +
            (pdfFileUrl != null ? "pdfFileUrl=" + pdfFileUrl + ", " : "") +
            (extraPdfFileUrl != null ? "extraPdfFileUrl=" + extraPdfFileUrl + ", " : "") +
            (isWithdrawal != null ? "isWithdrawal=" + isWithdrawal + ", " : "") +
            (withdrawalDecreeNo != null ? "withdrawalDecreeNo=" + withdrawalDecreeNo + ", " : "") +
            (notes != null ? "notes=" + notes + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
