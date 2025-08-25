package domain.in.rjsa.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class fileSaparate {

	public static void main(String[] args) {

		String src = "D:\\Documents\\Desktop\\08-06-2023\\Form16";
		String dest1 = "D:\\Documents\\Desktop\\08-06-2023\\Part_A";	
		String dest2 = "D:\\Documents\\Desktop\\08-06-2023\\Part_B";

		File folder = new File(src);
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
			if (file.getName().contains("PARTB")) {
					File partb = new File(dest2 +"\\"+ file.getName());
					try {
						FileUtils.copyFile(file,partb);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			} else {
				try {
					File parta = new File(dest1 +"\\"+ file.getName());
					FileUtils.copyFile(file,parta);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	}
}
