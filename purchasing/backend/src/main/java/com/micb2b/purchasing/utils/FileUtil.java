package com.micb2b.purchasing.utils;

import java.io.File;
import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

/**
 * File工具類，擴展 hutool 工具包
 * @author jay
 * @date 2020-03-31
 */

@Slf4j
@Component
public class FileUtil extends cn.hutool.core.io.FileUtil {

    /**
     * 定義GB的計算常量
     */
    private static final int GB = 1024 * 1024 * 1024;
    /**
     * 定義MB的計算常量
     */
    private static final int MB = 1024 * 1024;
    /**
     * 定義KB的計算常量
     */
    private static final int KB = 1024;

    /**
     * 格式化小數
     */
    private static final DecimalFormat DF = new DecimalFormat("0.00");

	public static String IMG_PATH_LINUX;
	
	public static String IMG_PATH_WIN;

	public static String REPORT_PATH_LINUX;

	public static String REPORT_PATH_WIN;

	@Value("${IMG_PATH_LINUX}")
	public void setImgTmpPathLinux(String pathLinux) {
		IMG_PATH_LINUX = pathLinux;
	}

	@Value("${IMG_PATH_WIN}")
	public void setImgTmpPathWin(String pathWin) {
		IMG_PATH_WIN = pathWin;
	}

	@Value("${REPORT_PATH_LINUX}")
	public void setImgReportPathLinux(String reportPathLinux) {
		REPORT_PATH_LINUX = reportPathLinux;
	}

	@Value("${REPORT_PATH_WIN}")
	public void setImgReportPathWin(String reportPathWin) {
		REPORT_PATH_WIN = reportPathWin;
	}

	public static String saveImgMultipartFile(MultipartFile file) throws Exception {
		// 獲取檔案名
		String fileName = file.getOriginalFilename();
		log.info("上傳的檔案名為：" + fileName);
		// 獲取文件的副檔名
		String suffixName = fileName.substring(fileName.lastIndexOf("."));
		// 檔案上傳後的路徑 https://dayeh.azureedge.net
		String newfileName = StringUtils.getYyyyMMddHHmmssSSS("yyyyMMddHHmmssSSS") + suffixName;
		
		//本機與linux會有路徑錯誤的問題
		//true: windows會帶有C:
		//false: 其他
		String imgTmpPath = (CommonUtils.getOS()?CommonUtils.IMG_PATH_WIN:CommonUtils.IMG_PATH_LINUX);
		
		String fileFullPath = imgTmpPath + newfileName;
		log.info(fileFullPath);

		File dest = new File(fileFullPath);
		// 檢測是否存在目錄
		if (!dest.getParentFile().exists()) {
			dest.getParentFile().mkdirs();
		}
		// 落地
		file.transferTo(dest);
		return newfileName;
	}
	
	public static String saveMultipartFile(MultipartFile file, String prefix) throws Exception {
		// 獲取檔案名
		String fileName = file.getOriginalFilename();
		log.info("上傳的檔案名為：" + fileName);
		// 獲取文件的副檔名
		String suffixName = fileName.substring(fileName.lastIndexOf("."));
		// 檔案上傳後的路徑 https://dayeh.azureedge.net
		String newfileName = prefix + StringUtils.getYyyyMMddHHmmssSSS("yyyyMMddHHmmssSSS") + suffixName;
		
		//本機與linux會有路徑錯誤的問題
		//true: windows會帶有C:
		//false: 其他
		String imgTmpPath = (CommonUtils.getOS()?CommonUtils.IMG_PATH_WIN:CommonUtils.IMG_PATH_LINUX);
		
		String fileFullPath = imgTmpPath + newfileName;
		log.info(fileFullPath);

		File dest = new File(fileFullPath);
		// 檢測是否存在目錄
		if (!dest.getParentFile().exists()) {
			dest.getParentFile().mkdirs();
		}
		// 落地
		file.transferTo(dest);
		return newfileName;
	}
	

    /**
     * 刪除
     * @param files
     */
    public static void deleteFile(File... files) {
        for (File file : files) {
            if (file.exists()) {
                file.delete();
            }
        }
    }

    /**
     * 獲取文件副檔名
     * @param filename
     * @return
     */
    public static String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot >-1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }

    /**
     * Java檔操作 獲取不帶副檔名的檔案名
     * @param filename
     * @return
     */
    public static String getFileNameNoEx(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot >-1) && (dot < (filename.length()))) {
                return filename.substring(0, dot);
            }
        }
        return filename;
    }

    /**
     * 檔大小轉換
     * @param size
     * @return
     */
    public static String getSize(int size){
        String resultSize = "";
        if (size / GB >= 1) {
            //如果當前Byte的值大於等於1GB
            resultSize = DF.format(size / (float) GB) + "GB   ";
        } else if (size / MB >= 1) {
            //如果當前Byte的值大於等於1MB
            resultSize = DF.format(size / (float) MB) + "MB   ";
        } else if (size / KB >= 1) {
            //如果當前Byte的值大於等於1KB
            resultSize = DF.format(size / (float) KB) + "KB   ";
        } else {
            resultSize = size + "B   ";
        }
        return resultSize;
    }
}
