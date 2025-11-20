package com.example.server.controller;

import com.example.result.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@RestController
@RequestMapping("/api/file")
public class FileController {
    private static final String UPLOAD_DIR = "xxxx";

    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file){
        if(file==null || file.isEmpty()){
            return Result.error("file is empty");
        }
        try{
            String fileName = file.getOriginalFilename();
            String extension = fileName.substring(fileName.lastIndexOf("."));
            String newFileName =  UUID.randomUUID().toString().concat(extension);
            //TODO upload to cloud server
            //////// uploading to local server/////////
            File dir = new File(UPLOAD_DIR);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            File dest = new File(dir, newFileName);

            file.transferTo(dest);

            String url = "/files/" + newFileName;
            ///////////////////////////////////////////
            return Result.success(url);
        }
        catch (Exception e){
            return Result.error("upload failed");
        }

    }
}
