package cn.bananice.basic.controller;

import cn.bananice.basic.util.AjaxResult;
import cn.bananice.basic.util.FastdfsUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/fastDfs")
public class FastDfsController {

    //上传
    @PostMapping("/upload")
    public AjaxResult upload(@RequestPart("file") MultipartFile file) {

        try {
            String filename = file.getOriginalFilename();
            String ext = filename.substring(filename.lastIndexOf("." )+1);

            byte[] bytes = file.getBytes();
            String path = FastdfsUtil.upload(bytes, ext);

            return AjaxResult.getAjaxResult().setResultObj(path);
        } catch (IOException e) {
            e.printStackTrace();
            return AjaxResult.getAjaxResult().setSuccess(false).setMessage("文件上传失败");
        }
    }

    //删除
    @DeleteMapping  //localhost:8080/fastDfs?path=/group1/M00/03/66/CgAIC2O1WtSARAUWAAE8bwh2HuU90.jpeg
    public AjaxResult delete(@RequestParam("path") String path) {

        try {
            String pathTmp = path.substring(1);
            int index = pathTmp.indexOf("/");

            String group = pathTmp.substring(0, index);
            String remotePath = pathTmp.substring(index+1);

            FastdfsUtil.delete(group,remotePath);
            return AjaxResult.getAjaxResult();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getAjaxResult().setSuccess(false).setMessage("删除失败！"+e.getMessage());
        }
    }
}
