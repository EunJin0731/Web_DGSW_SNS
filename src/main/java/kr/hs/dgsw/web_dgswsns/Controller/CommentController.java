package kr.hs.dgsw.web_dgswsns.Controller;

import kr.hs.dgsw.web_dgswsns.Domain.Comment;
import kr.hs.dgsw.web_dgswsns.Protocol.CommentUsernameProtocol;
import kr.hs.dgsw.web_dgswsns.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/comment")
    public List<CommentUsernameProtocol> list() {
        return this.commentService.listAllComments();
    }

    @PostMapping("/addcomment")
    public CommentUsernameProtocol add(@RequestBody Comment comment) { return this.commentService.addComment(comment); }

    @PutMapping("/updatecomment/{id}")
    public Comment updateUser(@PathVariable Long id, @RequestBody Comment comment){
        return this.commentService.updateComment(id, comment);
    }

    @DeleteMapping("/deletecomment/{id}")
    public boolean delete(@PathVariable Long id){ return this.commentService.deleteComment(id);}
}

