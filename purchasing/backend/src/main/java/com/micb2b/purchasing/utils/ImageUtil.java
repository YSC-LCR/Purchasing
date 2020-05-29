package com.micb2b.purchasing.utils;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

@Component
public class ImageUtil {

	// 圖片存取資料夾
	// 將圖片放置Local
	// 抓取圖片
	// base64轉換圖片
	// 圖片轉換base64
	//檔名 : 時間戳記.jpg
	//存檔路徑 : C:/Purchasing/file/img
	
	public static String saveToFileImage(String base64Str) throws IOException {
		System.out.println("123"+base64Str);
		String suffixName = ".jpg";
		String outputFilePath = (CommonUtils.getOS()?CommonUtils.IMG_PATH_WIN:CommonUtils.IMG_PATH_LINUX);
		String fileName = StringUtils.getYyyyMMddHHmmssSSS("yyyyMMddHHmmssSSS") + suffixName;
		Base64StringToFileConversion(outputFilePath,fileName,base64Str);
		return fileName;
	}
	
	public static void Base64StringToFileConversion(String outputFilePath,String fileName,String base64Str) throws IOException {
		File outputFile = new File(outputFilePath+"/"+ fileName);
        // decode the string and write to file
        byte[] decodedBytes = Base64
          .getDecoder()
          .decode(base64Str.replace("\r\n", ""));
        FileUtils.writeByteArrayToFile(outputFile, decodedBytes);
	}

	
	/**
	 * frontend顯示圖片的路徑
	 * @param imgFileName
	 * @return
	 */
	public static String displayFrontendImg(String imgFileName) {
		return  "/static/img/" +imgFileName;
	}
	
	/**
	 * frontend顯示report的路徑
	 * @param reportFileName
	 * @return
	 */
	public static String displayFrontendReport(String reportFileName) {
		return  "/static/img/report/" +reportFileName;
	}

	public static void main(String[] args) throws IOException {
		System.out.println("---start---");
//		String imageFileName = ImageUtil.saveToFileImage(encodedString);
//		System.out.println("result :" +imageFileName);
//		encodedString.
		//將base64前的image/jpeg;base64, 移除
//		String result = encodedString.replace("image/jpeg;base64,", "");
//		System.out.println("result :" +result);
//		System.out.println("---end---");
	}

}
