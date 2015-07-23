package com.lq.kexin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;

@Controller
@RequestMapping("/file")
public class FileController {

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String uploadPage() {
        return "/file/upload";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String uploadHTML(String name, MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                System.out.println(name);
                byte[] bytes = file.getBytes();
                System.out.println(Arrays.toString(bytes));
                // store the bytes somewhere
                return "file/uploadSuccess";
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return "file/upload";
    }
}
