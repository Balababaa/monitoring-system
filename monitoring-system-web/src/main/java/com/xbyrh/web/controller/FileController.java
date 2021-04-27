package com.xbyrh.web.controller;

import com.xbyrh.common.annotations.NoAuth;
import com.xbyrh.repo.model.support.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * create at 2021/4/23
 *
 * @author chenxinhui
 */
@Slf4j
@RestController
@RequestMapping("/api/file")
public class FileController {

    @NoAuth
    @PostMapping("upload")
    public BaseResponse<String> upload(MultipartFile file) throws Exception {
        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());

        File localFile = new File(file.getOriginalFilename());

        FileOutputStream fileOutputStream = new FileOutputStream(localFile);
        fileOutputStream.write(file.getBytes());
        fileOutputStream.flush();
        fileOutputStream.close();

        return BaseResponse.ok("http://localhost:8080/api/file/download?filename=" + file.getOriginalFilename());
    }

    @NoAuth
    @GetMapping("download")
    public void download(HttpServletResponse response, @RequestParam String filename) throws Exception {

        File file = new File(filename);

        response.reset();
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition", "attachment;filename=" + filename );

        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));) {
            byte[] buff = new byte[1024];
            OutputStream os  = response.getOutputStream();
            int i = 0;
            while ((i = bis.read(buff)) != -1) {
                os.write(buff, 0, i);
                os.flush();
            }
        } catch (IOException e) {
            log.error("下载出错",e);
        }
    }

}
