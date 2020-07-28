package org.hyojeong.stdmgt.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.hyojeong.stdmgt.model.UploadFile;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UploadController {
    
	@ResponseBody
    @RequestMapping(value="upload", method=RequestMethod.POST)
    public String submitUpload(
            @ModelAttribute("uploadFile") UploadFile uploadFile,
            BindingResult result    ) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        System.out.println("enter.. submitUpload");

        // 에러 검사 : 업로드 파일 유무
        MultipartFile file = uploadFile.getFile();
        
        String fileName = file.getOriginalFilename();
        System.out.println(fileName);
        
        try {
            inputStream = file.getInputStream();
            
            File newFile = new File("/Users/dean/Documents/etc/project/HJPA/develop/uploadFile/" + fileName);
            if(newFile.exists()) {
                newFile.createNewFile();
            }
            outputStream = new FileOutputStream(newFile);
            int read = 0;
            byte[] bytes = new byte[1024];
            
            while((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes,0,read);
            }
            
        } catch (Exception e) {
            System.out.println("err : " + e);
        }finally {
            try {
                inputStream.close();
                outputStream.close();
            } catch (Exception e2) {
                // TODO: handle exception
            }
        }
        
        
        return "";
    }
}
