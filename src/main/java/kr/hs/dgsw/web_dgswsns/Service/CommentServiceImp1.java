package kr.hs.dgsw.web_dgswsns.Service;

import kr.hs.dgsw.web_dgswsns.Domain.Comment;
import kr.hs.dgsw.web_dgswsns.Protocol.CommentUsernameProtocol;
import kr.hs.dgsw.web_dgswsns.Repository.CommentRepository;
import kr.hs.dgsw.web_dgswsns.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImp1 implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<CommentUsernameProtocol> listAllComments() {
        List<Comment> commentList = this.commentRepository.findAll();

        List<CommentUsernameProtocol> cuplist = new ArrayList<>();

        commentList.forEach(comment -> {
            cuplist.add(new CommentUsernameProtocol(comment, this.userRepository.findByUserid(comment.getUserid())
                    .map(u->u.getUsername())
                    .orElse(null)));
        });
        return cuplist;
    }

    @Override
    public CommentUsernameProtocol addComment(Comment comment){
        Comment added = this.commentRepository.save(comment);
        return new CommentUsernameProtocol(added, this.userRepository.findByUserid(added.getUserid())
                .map(u->u.getUsername())
                .orElse(null));
    }

    @Override
    public boolean deleteComment(Long id) {
        try{
            this.commentRepository.deleteById(id);
            return true;
        } catch (Exception ex){
            return false;
        }
    }

    @Override
    public Comment updateComment(Long id, Comment comment) {
        return this.commentRepository.findById(id)
                .map(found -> {
                    found.setId(Optional.ofNullable(comment.getId()).orElse(found.getId()));
                    found.setContent(Optional.ofNullable(comment.getContent()).orElse(found.getContent()));
                    found.setFilelocal(Optional.ofNullable(comment.getFilelocal()).orElse(found.getFilelocal()));
                    found.setFilename(Optional.ofNullable(comment.getFilename()).orElse(found.getFilename()));
                    return this.commentRepository.save(found);
                })
                .orElse(null);
    }
}
