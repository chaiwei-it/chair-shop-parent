package com.mood.entity.upload;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * 用户模块
 * @author fenglu
 * @time 2018-06-08 15:10
 */
@Data
public class UploadRequest{

    private MultipartFile[] files;
}
