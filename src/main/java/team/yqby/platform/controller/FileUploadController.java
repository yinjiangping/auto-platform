package team.yqby.platform.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import com.google.common.base.Joiner;
import com.huateng.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import team.yqby.platform.config.PublicConfig;
import team.yqby.platform.service.PrepaidCardPayService;

@Slf4j
@RestController
public class FileUploadController {

    private final String filePath = PublicConfig.BILL_FILE_PATH + Joiner.on("_").join(DateUtil.getDateyyyyMMddHHmmss(), "billFile.xlsx");

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public
    @ResponseBody
    String handleFileUpload(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
                stream.write(bytes);
                stream.close();
                return filePath.substring(filePath.lastIndexOf(File.separator) + 1, filePath.length());
            } catch (Exception e) {
                log.error("文件上传失败，错误信息，", e);
            }
        }
        return "";
    }

}
