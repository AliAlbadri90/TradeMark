package ly.gov.eidc.archive.service.dto;

public class DecreeReport {

    private Integer firstDecree;
    private Integer lastDecree;
    private Integer totalCount;
    private Integer missingCount;
    private String missingNumbers;
    private String ministerName;
    private String governmentName;
    private Integer year;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getMissingCount() {
        return missingCount;
    }

    public void setMissingCount(Integer missingCount) {
        this.missingCount = missingCount;
    }

    public String getMinisterName() {
        return ministerName;
    }

    public void setMinisterName(String ministerName) {
        this.ministerName = ministerName;
    }

    public String getGovernmentName() {
        return governmentName;
    }

    public void setGovernmentName(String governmentName) {
        this.governmentName = governmentName;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getMissingNumbers() {
        return missingNumbers;
    }

    public void setMissingNumbers(String missingNumbers) {
        this.missingNumbers = missingNumbers;
    }

    public Integer getFirstDecree() {
        return firstDecree;
    }

    public void setFirstDecree(Integer firstDecree) {
        this.firstDecree = firstDecree;
    }

    public Integer getLastDecree() {
        return lastDecree;
    }

    public void setLastDecree(Integer lastDecree) {
        this.lastDecree = lastDecree;
    }
}
