package ly.gov.eidc.archive.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ly.gov.eidc.archive.domain.Minister} entity.
 */
public class MinisterDTO implements Serializable {

    private Long id;

    private String name;

    private String serialNo;

    private String jobTitle;

    private Long decreeCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MinisterDTO)) {
            return false;
        }

        MinisterDTO ministerDTO = (MinisterDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, ministerDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MinisterDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", serialNo='" + getSerialNo() + "'" +
            ", jobTitle='" + getJobTitle() + "'" +
            "}";
    }

    public Long getDecreeCount() {
        return decreeCount;
    }

    public void setDecreeCount(Long decreeCount) {
        this.decreeCount = decreeCount;
    }
}
