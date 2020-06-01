package com.rb.fmea.file;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * @version v1.0
 * @ClassName: UploadFile
 * @Description: 文件公用类
 * @Author: yyk
 * @Date: 2020/2/26 18:05
 */

public class UploadFile {

    private final static String PATH="src/main/webapp/";

/**
     * @Author yyk
     * @Description 单个文件上传，realPath为文件上传的路径
     * @Date 2020/2/26 18:10
     * @Param [uploadFile, realPath]
     * @return java.lang.String
     **/

    public static String oneUploadFile(MultipartFile uploadFile,String realPath) throws IOException {
        //生成文件存放的文件夹
        File file=new File(PATH+realPath);
        //如果文件夹不存在，则创建
        if(!file.isDirectory()){
            file.mkdirs();
        }
        //获取文件的原名称
        String oldName=uploadFile.getOriginalFilename();
        //为文件生成新的名称
        String newName=new Date().getTime()+"."+oldName.split("\\.")[1];
        //为文件生成绝对路径
        File absolutePath=new File(file.getAbsoluteFile()+File.separator+newName);
        //文件上传，文件重名会覆盖
        uploadFile.transferTo(absolutePath);
        return newName;
    }
}

