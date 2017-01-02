package team.yqby.platform.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import com.google.common.base.Joiner;
import com.huateng.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import team.yqby.platform.config.PublicConfig;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
public class FileUploadController {

    private final String filePath = PublicConfig.BILL_FILE_PATH;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public
    @ResponseBody
    String handleFileUpload(HttpServletRequest request) {
        String uploadFileName = "";
        List<MultipartFile> files;
        files = ((MultipartHttpServletRequest) request).getFiles("file");
        //文件不存在则直接抛出错误
        if (files == null || files.size() == 0) {
            log.error("客户端请求文件为空，上传文件失败");
            return uploadFileName;
        }
//        for (int i =0; i< files.size(); ++i) {
        MultipartFile file = files.get(0);
        String name = file.getName();
        if (!file.isEmpty()) {
            try {
                uploadFileName = Joiner.on("_").join(DateUtil.getDateyyyyMMddHHmmss(), (int) ((Math.random() * 9 + 1) * 100000), "billFile.xlsx");
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath + uploadFileName)));
                stream.write(bytes);
                stream.close();
            } catch (Exception e) {
                log.error("You failed to upload {} =>,{} ", name, e.getMessage());
            }
        } else {
            log.error("You failed to upload {} =>, because the file was empty ", name);
        }
//        }
        return uploadFileName;
    }
}
