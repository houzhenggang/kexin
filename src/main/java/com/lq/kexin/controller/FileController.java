package com.lq.kexin.controller;

import com.lq.kexin.entity.File;
import com.lq.kexin.service.FileService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

@Controller
@RequestMapping("/file")
public class FileController {

    @Resource
    FileService fileService;

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String uploadPage() {
        return "/file/upload";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String uploadHTML(String name, MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                System.out.println(name);
//                System.out.println(Arrays.toString(bytes));
                byte[] bytes = file.getBytes();

                File f = new File();
                f.setName(name);
                f.setContent(bytes);
                fileService.saveFile(f);
                return "file/uploadSuccess";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "file/upload";
    }

    @RequestMapping(value = "/download/{fileName:.+}", method = RequestMethod.GET)
    public HttpEntity<byte[]> downloadFile(@PathVariable("fileName") String fileName) {
        File file = fileService.getFile(fileName);
        byte[] documentBody;
        if (file == null) {
            documentBody = new byte[]{};
        } else
            documentBody = file.getContent();

        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("text", "plain"));
        header.set("Content-Disposition",
                "attachment; filename=" + fileName);
        header.setContentLength(documentBody.length);

        return new HttpEntity<byte[]>(documentBody, header);
    }
}
