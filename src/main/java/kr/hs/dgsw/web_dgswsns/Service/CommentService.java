package kr.hs.dgsw.web_dgswsns.Service;

import kr.hs.dgsw.web_dgswsns.Domain.Comment;
import kr.hs.dgsw.web_dgswsns.Protocol.CommentUsernameProtocol;

import java.util.List;

public interface CommentService {

    List<CommentUsernameProtocol> listAllComments();

    CommentUsernameProtocol addComment(Comment comment);

    boolean deleteComment(Long id);

    Comment updateComment(Long id, Comment comment);
}
