package com.mood.controller.upload;

import com.mood.entity.upload.UploadRequest;
import com.mood.entity.upload.UploadResponse;
import com.mood.utils.FileUtil;
import com.mood.utils.OssFileContent;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 模块
 *
 * @author chaiwei
 * @time 2018-08-02 18:05
 */
@RestController
@RequestMapping(value = "/api/v1/upload")
public class UploadApi {

    @PostMapping(value = "/image")
    public UploadResponse fileUploads(@RequestParam MultipartFile[] files){
        UploadResponse uploadResponse = new UploadResponse();
        try {
            for(MultipartFile file: files){

                String fileUrl = FileUtil.OSSUpLoad(file.getOriginalFilename(), file.getInputStream(), OssFileContent.GOODSPATH);
                uploadResponse.setUrl(fileUrl);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return uploadResponse;
    }
}
