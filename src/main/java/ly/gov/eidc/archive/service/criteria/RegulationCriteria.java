package ly.gov.eidc.archive.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.BooleanFilter;
import tech.jhipster.service.filter.DoubleFilter;
import tech.jhipster.service.filter.Filter;
import tech.jhipster.service.filter.FloatFilter;
import tech.jhipster.service.filter.IntegerFilter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link ly.gov.eidc.archive.domain.Regulation} entity. This class is used
 * in {@link ly.gov.eidc.archive.web.rest.RegulationResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /regulations?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class RegulationCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter title;

    private StringFilter description;

    private StringFilter type;

    private IntegerFilter year;

    private StringFilter filePdfUrl;

    private StringFilter fileWordUrl;

    private BooleanFilter isActive;

    private Boolean distinct;

    public RegulationCriteria() {}

    public RegulationCriteria(RegulationCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.title = other.title == null ? null : other.title.copy();
        this.description = other.description == null ? null : other.description.copy();
        this.type = other.type == null ? null : other.type.copy();
        this.year = other.year == null ? null : other.year.copy();
        this.filePdfUrl = other.filePdfUrl == null ? null : other.filePdfUrl.copy();
        this.fileWordUrl = other.fileWordUrl == null ? null : other.fileWordUrl.copy();
        this.isActive = other.isActive == null ? null : other.isActive.copy();
        this.distinct = other.distinct;
    }

    @Override
    public RegulationCriteria copy() {
        return new RegulationCriteria(this);
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

    public StringFilter getDescription() {
        return description;
    }

    public StringFilter description() {
        if (description == null) {
            description = new StringFilter();
        }
        return description;
    }

    public void setDescription(StringFilter description) {
        this.description = description;
    }

    public StringFilter getType() {
        return type;
    }

    public StringFilter type() {
        if (type == null) {
            type = new StringFilter();
        }
        return type;
    }

    public void setType(StringFilter type) {
        this.type = type;
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

    public StringFilter getFilePdfUrl() {
        return filePdfUrl;
    }

    public StringFilter filePdfUrl() {
        if (filePdfUrl == null) {
            filePdfUrl = new StringFilter();
        }
        return filePdfUrl;
    }

    public void setFilePdfUrl(StringFilter filePdfUrl) {
        this.filePdfUrl = filePdfUrl;
    }

    public StringFilter getFileWordUrl() {
        return fileWordUrl;
    }

    public StringFilter fileWordUrl() {
        if (fileWordUrl == null) {
            fileWordUrl = new StringFilter();
        }
        return fileWordUrl;
    }

    public void setFileWordUrl(StringFilter fileWordUrl) {
        this.fileWordUrl = fileWordUrl;
    }

    public BooleanFilter getIsActive() {
        return isActive;
    }

    public BooleanFilter isActive() {
        if (isActive == null) {
            isActive = new BooleanFilter();
        }
        return isActive;
    }

    public void setIsActive(BooleanFilter isActive) {
        this.isActive = isActive;
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
        final RegulationCriteria that = (RegulationCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(title, that.title) &&
            Objects.equals(description, that.description) &&
            Objects.equals(type, that.type) &&
            Objects.equals(year, that.year) &&
            Objects.equals(filePdfUrl, that.filePdfUrl) &&
            Objects.equals(fileWordUrl, that.fileWordUrl) &&
            Objects.equals(isActive, that.isActive) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, type, year, filePdfUrl, fileWordUrl, isActive, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RegulationCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (title != null ? "title=" + title + ", " : "") +
            (description != null ? "description=" + description + ", " : "") +
            (type != null ? "type=" + type + ", " : "") +
            (year != null ? "year=" + year + ", " : "") +
            (filePdfUrl != null ? "filePdfUrl=" + filePdfUrl + ", " : "") +
            (fileWordUrl != null ? "fileWordUrl=" + fileWordUrl + ", " : "") +
            (isActive != null ? "isActive=" + isActive + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
