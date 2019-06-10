package msg.attachement.entity;


import javax.persistence.*;

/**
 * @author msg systems AG; Silviu-Mihnea Cucuiet.
 * @since 19.1.2
 * day 5/21/2019
 * time 7:58 PM
 */

@Entity
@Table(name = "attachments")
@NamedQueries({
        @NamedQuery(
                name = AttachmentEntity.ATTACHEMENT_FIND_ALL,
                query = "SELECT  A from AttachmentEntity A where A.bugId = :bugId"),
        @NamedQuery(
                name = AttachmentEntity.ATTACHEMENT_DELETE,
                query = "SELECT  A from AttachmentEntity A where A.id = :id")
        }
)
public class AttachmentEntity {

    public static final String ATTACHEMENT_FIND_ALL = "attachment.FindAllForBug";
    public static final String ATTACHEMENT_DELETE = "attachment.Delete";

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "bug_id", nullable = false)
    private long bugId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "type", nullable = false)
    private String type;

    @Lob
    @Column(name = "file", nullable = false)
    private byte[] file;

    public AttachmentEntity() {
    }

    public long getId() {
        return id;
    }

    public long getBugId() {
        return bugId;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public byte[] getFile() {
        return file;
    }

    public AttachmentEntity setId(long id) {
        this.id = id;
        return this;
    }

    public AttachmentEntity setBugId(long bugId) {
        this.bugId = bugId;
        return this;
    }

    public AttachmentEntity setName(String name) {
        this.name = name;
        return this;
    }

    public AttachmentEntity setType(String type) {
        this.type = type;
        return this;
    }

    public AttachmentEntity setFile(byte[] file) {
        this.file = file;
        return this;
    }
}
