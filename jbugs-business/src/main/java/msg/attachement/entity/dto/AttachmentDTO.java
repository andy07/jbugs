package msg.attachement.entity.dto;

/**
 * @author msg systems AG; Silviu-Mihnea Cucuiet.
 * @since 19.1.2
 * day 6/4/2019
 * time 12:58 PM
 */
public class AttachmentDTO {

    private byte[] file;
    private String bugTitle;

    public AttachmentDTO() {
    }

    public byte[] getFile() {
        return file;
    }

    public String getBugTitle() {
        return bugTitle;
    }

    public AttachmentDTO setFile(byte[] file) {
        this.file = file;
        return this;
    }

    public AttachmentDTO setBugTitle(String bugTitle) {
        this.bugTitle = bugTitle;
        return this;
    }
}
