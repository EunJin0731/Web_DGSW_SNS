package kr.hs.dgsw.web_dgswsns.Protocol;

import kr.hs.dgsw.web_dgswsns.Domain.Comment;
import lombok.Data;

@Data
public class CommentUsernameProtocol extends Comment {

    private String username;

    public CommentUsernameProtocol(Comment c, String username) {
        super(c);
        this.username = username;
    }
}
