package msg.attachement.entity.dto;

/**
 * @author msg systems AG; Silviu-Mihnea Cucuiet.
 * @since 19.1.2
 * day 6/4/2019
 * time 12:58 PM
 */
public class AttachmentDTO {

    private String file;
    private String name;
    private String type;
    private long bugId;

    public AttachmentDTO() {
    }

    public String getFile() {
        return file;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public long getBugId() {
        return bugId;
    }

    public AttachmentDTO setFile(String file) {
        this.file = file;
        return this;
    }

    public AttachmentDTO setName(String name) {
        this.name = name;
        return this;
    }

    public AttachmentDTO setType(String type) {
        this.type = type;
        return this;
    }

    public AttachmentDTO setBugId(long bugId) {
        this.bugId = bugId;
        return this;
    }
}
