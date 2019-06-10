package com.hzqing.admin.common.utils;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @author hzqing
 * @date 2019-06-10 08:53
 */
public class FileUtil {
    /**
     * 上传文件
     * @param file
     * @param filePath
     * @param fileName
     * @throws Exception
     */
    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath+fileName);
        out.write(file);
        out.flush();
        out.close();
    }
}
