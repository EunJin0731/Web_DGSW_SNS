package kr.hs.dgsw.web_dgswsns.Domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    private String userid;
    private String content;
    private String filelocal;
    private String filename;

    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime modified;

    public Comment(Comment c){
        this.id = c.id;
        this.userid = c.userid;
        this.content = c.content;
        this.filelocal = c.filelocal;
        this.filename = c.filename;
        this.created = c.created;
        this.modified = c.modified;
    }

    public Comment(String userid, String content, String filelocal, String filename, LocalDateTime created, LocalDateTime modified) {
        this.userid = userid;
        this.content = content;
        this.filelocal = filelocal;
        this.filename = filename;
        this.created = created;
        this.modified = modified;
    }

    public Comment(String userid, String content){
        this.userid = userid;
        this.content = content;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFilelocal() {
        return filelocal;
    }

    public void setFilelocal(String filelocal) {
        this.filelocal = filelocal;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }
}
