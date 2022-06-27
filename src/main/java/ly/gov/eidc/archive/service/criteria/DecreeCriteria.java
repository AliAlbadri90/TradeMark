package ly.gov.eidc.archive.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import ly.gov.eidc.archive.domain.enumeration.DecreeStatus;
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
 * Criteria class for the {@link ly.gov.eidc.archive.domain.Decree} entity. This class is used
 * in {@link ly.gov.eidc.archive.web.rest.DecreeResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /decrees?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class DecreeCriteria implements Serializable, Criteria {

    /**
     * Class for filtering DecreeStatus
     */
    public static class DecreeStatusFilter extends Filter<DecreeStatus> {

        public DecreeStatusFilter() {}

        public DecreeStatusFilter(DecreeStatusFilter filter) {
            super(filter);
        }

        @Override
        public DecreeStatusFilter copy() {
            return new DecreeStatusFilter(this);
        }
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter documentNo;

    private StringFilter decreeNo;

    private StringFilter title;

    private StringFilter details;

    private StringFilter keywords;

    private IntegerFilter pages;

    private LocalDateFilter decreeDate;

    private IntegerFilter year;

    private StringFilter notes;

    private StringFilter pdfFileUrl;

    private StringFilter wordFileUrl;

    private StringFilter extraPdfFileUrl;

    private DecreeStatusFilter decreeStatus;

    private StringFilter remarks;

    private BooleanFilter isHidden;

    private StringFilter hideNotes;

    private LocalDateFilter hideEndDate;

    private LongFilter decreeTypeId;

    private LongFilter decreeCategoryId;

    private LongFilter ministerId;

    private LongFilter governmentId;

    private Boolean distinct;

    public DecreeCriteria() {}

    public DecreeCriteria(DecreeCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.documentNo = other.documentNo == null ? null : other.documentNo.copy();
        this.decreeNo = other.decreeNo == null ? null : other.decreeNo.copy();
        this.title = other.title == null ? null : other.title.copy();
        this.details = other.details == null ? null : other.details.copy();
        this.keywords = other.keywords == null ? null : other.keywords.copy();
        this.pages = other.pages == null ? null : other.pages.copy();
        this.decreeDate = other.decreeDate == null ? null : other.decreeDate.copy();
        this.year = other.year == null ? null : other.year.copy();
        this.notes = other.notes == null ? null : other.notes.copy();
        this.pdfFileUrl = other.pdfFileUrl == null ? null : other.pdfFileUrl.copy();
        this.wordFileUrl = other.wordFileUrl == null ? null : other.wordFileUrl.copy();
        this.extraPdfFileUrl = other.extraPdfFileUrl == null ? null : other.extraPdfFileUrl.copy();
        this.decreeStatus = other.decreeStatus == null ? null : other.decreeStatus.copy();
        this.remarks = other.remarks == null ? null : other.remarks.copy();
        this.isHidden = other.isHidden == null ? null : other.isHidden.copy();
        this.hideNotes = other.hideNotes == null ? null : other.hideNotes.copy();
        this.hideEndDate = other.hideEndDate == null ? null : other.hideEndDate.copy();
        this.decreeTypeId = other.decreeTypeId == null ? null : other.decreeTypeId.copy();
        this.decreeCategoryId = other.decreeCategoryId == null ? null : other.decreeCategoryId.copy();
        this.ministerId = other.ministerId == null ? null : other.ministerId.copy();
        this.governmentId = other.governmentId == null ? null : other.governmentId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public DecreeCriteria copy() {
        return new DecreeCriteria(this);
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

    public StringFilter getDocumentNo() {
        return documentNo;
    }

    public StringFilter documentNo() {
        if (documentNo == null) {
            documentNo = new StringFilter();
        }
        return documentNo;
    }

    public void setDocumentNo(StringFilter documentNo) {
        this.documentNo = documentNo;
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

    public StringFilter getTitle() {
        return title;
    }

    public StringFilter title() {
        if (title == null) {
            title = new StringFilter();
        }
        return title;
    }

    public void setTitle(StringFilter title) {
        this.title = title;
    }

    public StringFilter getDetails() {
        return details;
    }

    public StringFilter details() {
        if (details == null) {
            details = new StringFilter();
        }
        return details;
    }

    public void setDetails(StringFilter details) {
        this.details = details;
    }

    public StringFilter getKeywords() {
        return keywords;
    }

    public StringFilter keywords() {
        if (keywords == null) {
            keywords = new StringFilter();
        }
        return keywords;
    }

    public void setKeywords(StringFilter keywords) {
        this.keywords = keywords;
    }

    public IntegerFilter getPages() {
        return pages;
    }

    public IntegerFilter pages() {
        if (pages == null) {
            pages = new IntegerFilter();
        }
        return pages;
    }

    public void setPages(IntegerFilter pages) {
        this.pages = pages;
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

    public StringFilter getWordFileUrl() {
        return wordFileUrl;
    }

    public StringFilter wordFileUrl() {
        if (wordFileUrl == null) {
            wordFileUrl = new StringFilter();
        }
        return wordFileUrl;
    }

    public void setWordFileUrl(StringFilter wordFileUrl) {
        this.wordFileUrl = wordFileUrl;
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

    public DecreeStatusFilter getDecreeStatus() {
        return decreeStatus;
    }

    public DecreeStatusFilter decreeStatus() {
        if (decreeStatus == null) {
            decreeStatus = new DecreeStatusFilter();
        }
        return decreeStatus;
    }

    public void setDecreeStatus(DecreeStatusFilter decreeStatus) {
        this.decreeStatus = decreeStatus;
    }

    public StringFilter getRemarks() {
        return remarks;
    }

    public StringFilter remarks() {
        if (remarks == null) {
            remarks = new StringFilter();
        }
        return remarks;
    }

    public void setRemarks(StringFilter remarks) {
        this.remarks = remarks;
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

    public StringFilter getHideNotes() {
        return hideNotes;
    }

    public StringFilter hideNotes() {
        if (hideNotes == null) {
            hideNotes = new StringFilter();
        }
        return hideNotes;
    }

    public void setHideNotes(StringFilter hideNotes) {
        this.hideNotes = hideNotes;
    }

    public LocalDateFilter getHideEndDate() {
        return hideEndDate;
    }

    public LocalDateFilter hideEndDate() {
        if (hideEndDate == null) {
            hideEndDate = new LocalDateFilter();
        }
        return hideEndDate;
    }

    public void setHideEndDate(LocalDateFilter hideEndDate) {
        this.hideEndDate = hideEndDate;
    }

    public LongFilter getDecreeTypeId() {
        return decreeTypeId;
    }

    public LongFilter decreeTypeId() {
        if (decreeTypeId == null) {
            decreeTypeId = new LongFilter();
        }
        return decreeTypeId;
    }

    public void setDecreeTypeId(LongFilter decreeTypeId) {
        this.decreeTypeId = decreeTypeId;
    }

    public LongFilter getDecreeCategoryId() {
        return decreeCategoryId;
    }

    public LongFilter decreeCategoryId() {
        if (decreeCategoryId == null) {
            decreeCategoryId = new LongFilter();
        }
        return decreeCategoryId;
    }

    public void setDecreeCategoryId(LongFilter decreeCategoryId) {
        this.decreeCategoryId = decreeCategoryId;
    }

    public LongFilter getMinisterId() {
        return ministerId;
    }

    public LongFilter ministerId() {
        if (ministerId == null) {
            ministerId = new LongFilter();
        }
        return ministerId;
    }

    public void setMinisterId(LongFilter ministerId) {
        this.ministerId = ministerId;
    }

    public LongFilter getGovernmentId() {
        return governmentId;
    }

    public LongFilter governmentId() {
        if (governmentId == null) {
            governmentId = new LongFilter();
        }
        return governmentId;
    }

    public void setGovernmentId(LongFilter governmentId) {
        this.governmentId = governmentId;
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
        final DecreeCriteria that = (DecreeCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(documentNo, that.documentNo) &&
            Objects.equals(decreeNo, that.decreeNo) &&
            Objects.equals(title, that.title) &&
            Objects.equals(details, that.details) &&
            Objects.equals(keywords, that.keywords) &&
            Objects.equals(pages, that.pages) &&
            Objects.equals(decreeDate, that.decreeDate) &&
            Objects.equals(year, that.year) &&
            Objects.equals(notes, that.notes) &&
            Objects.equals(pdfFileUrl, that.pdfFileUrl) &&
            Objects.equals(wordFileUrl, that.wordFileUrl) &&
            Objects.equals(extraPdfFileUrl, that.extraPdfFileUrl) &&
            Objects.equals(decreeStatus, that.decreeStatus) &&
            Objects.equals(remarks, that.remarks) &&
            Objects.equals(isHidden, that.isHidden) &&
            Objects.equals(hideNotes, that.hideNotes) &&
            Objects.equals(hideEndDate, that.hideEndDate) &&
            Objects.equals(decreeTypeId, that.decreeTypeId) &&
            Objects.equals(decreeCategoryId, that.decreeCategoryId) &&
            Objects.equals(ministerId, that.ministerId) &&
            Objects.equals(governmentId, that.governmentId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            documentNo,
            decreeNo,
            title,
            details,
            keywords,
            pages,
            decreeDate,
            year,
            notes,
            pdfFileUrl,
            wordFileUrl,
            extraPdfFileUrl,
            decreeStatus,
            remarks,
            isHidden,
            hideNotes,
            hideEndDate,
            decreeTypeId,
            decreeCategoryId,
            ministerId,
            governmentId,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DecreeCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (documentNo != null ? "documentNo=" + documentNo + ", " : "") +
            (decreeNo != null ? "decreeNo=" + decreeNo + ", " : "") +
            (title != null ? "title=" + title + ", " : "") +
            (details != null ? "details=" + details + ", " : "") +
            (keywords != null ? "keywords=" + keywords + ", " : "") +
            (pages != null ? "pages=" + pages + ", " : "") +
            (decreeDate != null ? "decreeDate=" + decreeDate + ", " : "") +
            (year != null ? "year=" + year + ", " : "") +
            (notes != null ? "notes=" + notes + ", " : "") +
            (pdfFileUrl != null ? "pdfFileUrl=" + pdfFileUrl + ", " : "") +
            (wordFileUrl != null ? "wordFileUrl=" + wordFileUrl + ", " : "") +
            (extraPdfFileUrl != null ? "extraPdfFileUrl=" + extraPdfFileUrl + ", " : "") +
            (decreeStatus != null ? "decreeStatus=" + decreeStatus + ", " : "") +
            (remarks != null ? "remarks=" + remarks + ", " : "") +
            (isHidden != null ? "isHidden=" + isHidden + ", " : "") +
            (hideNotes != null ? "hideNotes=" + hideNotes + ", " : "") +
            (hideEndDate != null ? "hideEndDate=" + hideEndDate + ", " : "") +
            (decreeTypeId != null ? "decreeTypeId=" + decreeTypeId + ", " : "") +
            (decreeCategoryId != null ? "decreeCategoryId=" + decreeCategoryId + ", " : "") +
            (ministerId != null ? "ministerId=" + ministerId + ", " : "") +
            (governmentId != null ? "governmentId=" + governmentId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
