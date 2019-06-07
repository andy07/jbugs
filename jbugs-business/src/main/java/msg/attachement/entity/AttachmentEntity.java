package msg.attachement.entity;

import msg.bug.entity.BugEntity;

import javax.persistence.*;

/**
 * @author msg systems AG; Silviu-Mihnea Cucuiet.
 * @since 19.1.2
 * day 5/21/2019
 * time 7:58 PM
 */

@Entity
@Table(name = "attachments")
public class AttachmentEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "bug_id", nullable = false)
    private BugEntity bug;

    @Lob
    @Column(name = "file", nullable = false)
    private byte[] file;

    public AttachmentEntity() {
    }

    public AttachmentEntity(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public BugEntity getBug() {
        return bug;
    }

    public byte[] getFile() {
        return file;
    }

    public AttachmentEntity setId(long id) {
        this.id = id;
        return this;
    }

    public AttachmentEntity setBug(BugEntity bug) {
        this.bug = bug;
        return this;
    }

    public AttachmentEntity setFile(byte[] file) {
        this.file = file;
        return this;
    }
}
