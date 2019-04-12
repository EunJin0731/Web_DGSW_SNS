package kr.hs.dgsw.web_dgswsns.Service;

import kr.hs.dgsw.web_dgswsns.Domain.Comment;
import kr.hs.dgsw.web_dgswsns.Domain.User;
import kr.hs.dgsw.web_dgswsns.Repository.CommentRepository;
import kr.hs.dgsw.web_dgswsns.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp1 implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    @PostConstruct
    private void init(){
        User u = this.userRepository.save(new User("abc", "test" ,"1234","dgsw@abc.com", "C:/Users/JEJ/Desktop/웹/jinyoung.jpg"));
        this.commentRepository.save(new Comment(u.getUserid(), "hi 111"));
        this.commentRepository.save(new Comment(u.getUserid(), "hi 222"));
        this.commentRepository.save(new Comment(u.getUserid(), "hi 333"));
    }

    @Override
    public User UserLogin(User user) {
        Optional<User> found = this.userRepository.findByUserid(user.getUserid());
        if(found.isPresent()){
            if(found.get().getPassword().equals(user.getPassword())){
                return found.get();
            }
        }
        return null;
    }
}
