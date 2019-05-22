package msg.comment;

import msg.bug.entity.BugEntity;

import javax.persistence.*;
import java.sql.Date;

/**
 * Document me.
 *
 * @author msg systems AG; User Name.
 * @since 19.1.2
 */
@Entity
@Table(name = "comments")
public class CommentEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "username", nullable = false)
    private String username;

    @ManyToOne
    @JoinColumn(name = "bug_id", nullable = false)
    private BugEntity bug;

    public CommentEntity() {
    }

    public long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getText() {
        return text;
    }

    public String getUsername() {
        return username;
    }

    public BugEntity getBug() {
        return bug;
    }

    public CommentEntity setId(long id) {
        this.id = id;
        return this;
    }

    public CommentEntity setDate(Date date) {
        this.date = date;
        return this;
    }

    public CommentEntity setText(String text) {
        this.text = text;
        return this;
    }

    public CommentEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    public CommentEntity setBug(BugEntity bug) {
        this.bug = bug;
        return this;
    }
}
