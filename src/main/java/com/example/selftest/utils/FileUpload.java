package com.example.selftest.utils;

import com.example.selftest.dto.OpResultDTO;
import com.example.selftest.mapper.UserMapper;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.example.selftest.entity.RubbishEntity;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

@RestController
@RequestMapping(value = "/api/upload")
public class FileUpload {

    @Value("${spring.upload.folder}")
    private String uploadFolder;
    private final UserMapper userMapper;

    private final Logger logger = LoggerFactory.getLogger(FileUpload.class);
    public FileUpload(UserMapper userMapper){
        this.userMapper = userMapper;
    }
    /**
     * @Description: 图片上传，采用Base64方式
     * @Author: SN
     * @Date: 2019/04/01 18:15
     */
    @ResponseBody
    @RequestMapping(value = "/image", method = RequestMethod.POST)
    public OpResultDTO saveBase64(@RequestParam(value = "imgBase64") String imgBase64,@RequestParam(value = "rubbishId")Integer rubbishId) {
        System.out.println(rubbishId);
        OpResultDTO opResult = new OpResultDTO();
        StringBuffer fileName = new StringBuffer();
        StringBuffer directoryName = new StringBuffer();
        StringBuffer pathName = new StringBuffer();
        StringBuffer imgPrefix = new StringBuffer();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        fileName.append(df.format(new Date()));
        //图片文件在服务器上的保存路径，需要与Nginx的反向代理整合考虑
        //System.out.println(ClassUtils.getDefaultClassLoader().getResource("").getPath());
        pathName.append(uploadFolder);
        try {
            if (imgBase64.indexOf("data:image/png;") != -1) {
                imgPrefix.append("data:image/png;base64,");
                fileName.append(".png");
            } else if (imgBase64.indexOf("data:image/jpeg;") != -1) {
                imgPrefix.append("data:image/jpeg;base64,");
                fileName.append(".jpeg");
            } else if (imgBase64.indexOf("data:image/jpg;") != -1) {
                imgPrefix.append("data:image/jpg;base64,");
                fileName.append(".jpg");
            } else if (imgBase64.indexOf("data:image/bmp;") != -1) {
                imgPrefix.append("data:image/bmp;base64,");
                fileName.append(".bmp");
            }
            imgBase64 = imgBase64.replace(imgPrefix.toString(), "");
            df = new SimpleDateFormat("yyyyMMdd");
            directoryName.append(df.format(new Date()) + "/");
            System.out.println(directoryName);
            System.out.println(pathName.toString() + directoryName.toString() + fileName);
            File file = new File(pathName.toString() + directoryName.toString() + fileName.toString());
            RubbishEntity rubbishEntity = new RubbishEntity();
            rubbishEntity.setRubbishImage(directoryName.toString() + fileName.toString());
            rubbishEntity.setRubbishId(rubbishId);
            userMapper.edit2(rubbishEntity);
            byte[] fileBytes = Base64.getDecoder().decode(imgBase64);
            FileUtils.writeByteArrayToFile(file, fileBytes);
            opResult.setMsgResult("success");
            //图片的返回路径需要根据自己的需求自行设置，注意应是相对路径
            opResult.setObjResult("../../file/" + directoryName.toString() + fileName.toString());
        } catch (IOException e) {
            logger.error(e.toString());
            opResult.setMsgResult("error");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return opResult;
    }

    /**
     * @Description: 文件上传，采用FormData方式，注意参数接收方式
     * @Author: SN
     * @Date: 2019/04/01 18:15
     */
    @ResponseBody
    @RequestMapping(value = "/accessory", method = RequestMethod.POST)
    public OpResultDTO saveAccessory(@RequestParam("asyData") MultipartFile[] asyData) {
        OpResultDTO opResult = new OpResultDTO();
        StringBuffer fileName = new StringBuffer();
        StringBuffer directoryName = new StringBuffer();
        StringBuffer pathName = new StringBuffer();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        pathName.append(uploadFolder);
        try {
            for (MultipartFile file : asyData) {
                String OriginalName = file.getOriginalFilename();
                String suffixName = OriginalName.substring(OriginalName.lastIndexOf("."));
                fileName.append(df.format(new Date()) + suffixName);
                df = new SimpleDateFormat("yyyyMMdd");
                directoryName.append(df.format(new Date()) + "/");
                File desFile = new File(pathName.toString() + directoryName.toString() + fileName.toString());
                System.out.println(desFile.getParentFile());
                if (!desFile.exists()) {
                    desFile.getParentFile().mkdirs();
                }
                file.transferTo(desFile);
            }
            opResult.setMsgResult("success");
            opResult.setObjResult("../../file/" + directoryName.toString() + fileName.toString());
        } catch (IOException e) {
            logger.error(e.toString());
            opResult.setMsgResult("error");
        }
        return opResult;
    }
}
