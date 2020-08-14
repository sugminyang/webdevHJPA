package org.hyojeong.stdmgt.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DownloadController {
    @RequestMapping("download")
    @ResponseBody
    public byte[] downProcess(HttpServletResponse response,
        @RequestParam String filename) throws IOException{
    	System.out.println("downProcess..." + filename);
//        File file = new File("/Users/dean/Documents/etc/project/HJPA/develop/uploadFile/" + filename);
        File file = new File("/data/" + filename);
        byte[] bytes = FileCopyUtils.copyToByteArray(file);
        
        String fn = new String(file.getName().getBytes(), "utf-8");
        
        response.setHeader("Content-Disposition",
                "attachment;filename=\"" + fn + "\"");
        response.setContentLength(bytes.length);
        return bytes;
    }
}

