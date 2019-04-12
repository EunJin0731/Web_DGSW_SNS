package kr.hs.dgsw.web_dgswsns.Controller;

import kr.hs.dgsw.web_dgswsns.Domain.Comment;
import kr.hs.dgsw.web_dgswsns.Domain.User;
import kr.hs.dgsw.web_dgswsns.Protocol.AttachmentProtocol;
import kr.hs.dgsw.web_dgswsns.Repository.CommentRepository;
import kr.hs.dgsw.web_dgswsns.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

@RestController
public class AttachmentController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CommentRepository commentRepository;

    @PostMapping("/attachment")
    public AttachmentProtocol upload(@RequestPart MultipartFile srcfile) {
        String destFilename
                = "C:/Users/JEJ/IdeaProjects/web_01_0326/upload/"
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd/"))
                + UUID.randomUUID().toString() + "_"
                + srcfile.getOriginalFilename();
        try {
            File destFile = new File(destFilename);
            destFile.getParentFile().mkdirs();
            srcfile.transferTo(destFile);
            return new AttachmentProtocol(destFilename, srcfile.getOriginalFilename());
        } catch (Exception ex) {
            return null;
        }
    }

    @GetMapping("/download/{type}/{id}")
    public void download(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id, @PathVariable String type){
        try {
            String filepath;
            String filename;
            if(type.equals("user")) {
                Optional<User> found = this.userRepository.findById(id);
                filepath = found.get().getFilelocal();
                filename = found.get().getFilename();
            }else{
                Optional<Comment> found = this.commentRepository.findById(id);
                filepath = found.get().getFilelocal();
                filename = found.get().getFilename();
            }
            File file = new File(filepath);
            if (file.exists() == false) return;

            String mimeType = URLConnection.guessContentTypeFromName(file.getName());
            if (mimeType == null) mimeType = "application/octet-stream";

            response.setContentType(mimeType);
            response.setHeader("Content-Disposition", "inline; filename=\"" + filename + "\"");
            response.setContentLength((int) file.length());

            InputStream is = new BufferedInputStream(new FileInputStream(file));
            FileCopyUtils.copy(is, response.getOutputStream());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
