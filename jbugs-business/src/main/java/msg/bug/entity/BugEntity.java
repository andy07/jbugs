package msg.bug.entity;

import msg.attachement.entity.AttachmentEntity;
import msg.comment.CommentEntity;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author msg systems AG; Silviu-Mihnea Cucuiet.
 * @since 19.1.2
 * day 5/21/2019
 * time 12:16 AM
 */

@Entity
@Table(name = "bugs")
public class BugEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "version", nullable = false)
    private String version;

    @Column(name = "targetDate", nullable = false)
    private Date targetDate;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "fixed_version", nullable = false)
    private String fixedVersion;

    @Column(name = "severity", nullable = false)
    private String severity;

    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @Column(name = "assigned_to", nullable = false)
    private String assignedTo;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "bug")
    private Set<AttachmentEntity> attachments = new HashSet<>();

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "bug")
    private Set<CommentEntity> comments = new HashSet<>();


    public BugEntity() {
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getVersion() {
        return version;
    }

    public Date getTargetDate() {
        return targetDate;
    }

    public String getStatus() {
        return status;
    }

    public String getFixedVersion() {
        return fixedVersion;
    }

    public String getSeverity() {
        return severity;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public Set<AttachmentEntity> getAttachments() {
        return attachments;
    }

    public Set<CommentEntity> getComments() {
        return comments;
    }

    public BugEntity setId(long id) {
        this.id = id;
        return this;
    }

    public BugEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public BugEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public BugEntity setVersion(String version) {
        this.version = version;
        return this;
    }


    public BugEntity setTargetDate(Date targetDate) {
        this.targetDate = targetDate;
        return this;
    }

    public BugEntity setStatus(String status) {
        this.status = status;
        return this;
    }

    public BugEntity setFixedVersion(String fixedVersion) {
        this.fixedVersion = fixedVersion;
        return this;
    }

    public BugEntity setSeverity(String severity) {
        this.severity = severity;
        return this;
    }

    public BugEntity setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public BugEntity setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
        return this;
    }

    public BugEntity setAttachments(Set<AttachmentEntity> attachments) {
        this.attachments = attachments;
        return this;
    }

    public BugEntity setComments(Set<CommentEntity> comments) {
        this.comments = comments;
        return this;
    }
}
