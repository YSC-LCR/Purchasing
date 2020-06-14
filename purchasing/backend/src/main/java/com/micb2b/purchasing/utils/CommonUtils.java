package com.micb2b.purchasing.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CommonUtils {

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

	public static boolean getOS() {
		boolean osFlag = false;
		String os = System.getProperty("os.name");
		// Android, iOS, Linux, Windows ,UNIX,  Mac OS X
		if (os.toUpperCase().startsWith("WINDOW")) {
			osFlag = true;
		}
		return osFlag;
	}

	private static Properties props = new Properties();

	public static String propsLoad(String path, String elementName) {

		String jarPath = System.getProperty("user.dir");

		String r = null;
		try {
			props.load(new FileInputStream(jarPath + "/" + path));
			r = props.getProperty(elementName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return r;
	}

	public static Integer convertStringToInteger(Object o) {
		if (o == null) {
			return 0;
		} else if (o instanceof Integer) {
			return (Integer) o;
		} else if (o instanceof Long) {
			return ((Long) o).intValue();
		} else if (o instanceof String && org.apache.commons.lang3.StringUtils.isBlank(String.valueOf(o))) {
			return 0;
		} else if (o instanceof String) {
			return Integer.valueOf(String.valueOf(o));
		} else {
			return 0;
		}
	}

	public static Long convertStringToLong(Object o) {
		if (o == null) {
			return 0L;
		} else if (o instanceof Long) {
			return (Long) o;
		} else if (o instanceof Integer) {
			return new Long(String.valueOf(o));
		} else if (o instanceof String && org.apache.commons.lang3.StringUtils.isBlank(String.valueOf(o))) {
			return 0L;
		} else if (o instanceof String) {
			return Long.valueOf((String) o);
		} else {
			return 0L;
		}
	}
}
