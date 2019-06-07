package msg.bug.entity.dto;

import java.sql.Date;
import java.util.Optional;

/**
 * Document me.
 *
 * @author msg systems AG; User Name.
 * @since 19.1.2
 */
public class BugDTO {

    private long id;

    private String title;

    private String version;

    private Date targetDate;

    private String status;

    private String fixedVersion;

    private String severity;

    private String assignedTo;

    private Optional<String> description = Optional.empty();

    private Optional<String> createdBy = Optional.empty();


    public BugDTO() {
    }

    public String getTitle() {
        return title;
    }

    public BugDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getVersion() {
        return version;
    }

    public BugDTO setVersion(String version) {
        this.version = version;
        return this;
    }

    public String getDescription() {
        return description.orElse("");
    }

    public BugDTO setDescription(String description) {
        this.description = Optional.of(description);
        return this;
    }

    public String getCreatedBy() {
        return createdBy.orElse("");
    }

    public BugDTO setCreatedBy(String createdBy) {
        this.createdBy = Optional.of(createdBy);
        return this;
    }

    public Date getTargetDate() {
        return targetDate;
    }

    public BugDTO setTargetDate(Date targetDate) {
        this.targetDate = targetDate;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public BugDTO setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getFixedVersion() {
        return fixedVersion;
    }

    public BugDTO setFixedVersion(String fixedVersion) {
        this.fixedVersion = fixedVersion;
        return this;
    }

    public String getSeverity() {
        return severity;
    }

    public BugDTO setSeverity(String severity) {
        this.severity = severity;
        return this;
    }


    public String getAssignedTo() {
        return assignedTo;
    }

    public BugDTO setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
        return this;
    }

    public long getId() {
        return id;
    }

    public BugDTO setId(long id) {
        this.id = id;
        return this;
    }

}
