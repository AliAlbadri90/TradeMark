package ly.gov.eidc.archive.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ly.gov.eidc.archive.domain.DecreeType} entity.
 */
public class DecreeTypeDTO implements Serializable {

    private Long id;

    private String name;

    private String serialNo;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DecreeTypeDTO)) {
            return false;
        }

        DecreeTypeDTO decreeTypeDTO = (DecreeTypeDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, decreeTypeDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DecreeTypeDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", serialNo='" + getSerialNo() + "'" +
            "}";
    }
}
