package ly.gov.eidc.archive.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ly.gov.eidc.archive.domain.ViewLog} entity.
 */
public class ViewLogDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    private String actionName;

    private String entityName;

    private String entityId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ViewLogDTO)) {
            return false;
        }

        ViewLogDTO viewLogDTO = (ViewLogDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, viewLogDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ViewLogDTO{" +
            "id=" + getId() +
            ", actionName='" + getActionName() + "'" +
            ", entityName='" + getEntityName() + "'" +
            ", entityId='" + getEntityId() + "'" +
            "}";
    }
}
