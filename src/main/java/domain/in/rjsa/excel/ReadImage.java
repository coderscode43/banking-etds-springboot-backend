package domain.in.rjsa.excel;

import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

public class ReadImage {

	public Image initializeResource(String fileName) {
		ClassLoader classLoader = getClass().getClassLoader();
		try {
			classLoader.getResource("resources/" + fileName).getPath();
			return ImageIO.read(classLoader.getResourceAsStream("resources/"+ fileName));
		} catch (Exception e) {
//			System.out.println("1");
		}
		try {
			classLoader.getResource(fileName).getPath();
			return ImageIO.read(classLoader.getResourceAsStream(fileName));

		} catch (Exception e) {
//			System.out.println("2");
		}
		return null;
	}
	
	public File initializeResourceString(String fileName) {
		ClassLoader classLoader = getClass().getClassLoader();
		try {
			classLoader.getResource("resources/" + fileName).getPath();
			classLoader.getResourceAsStream("resources/"+ fileName);
			return new File(classLoader.getResource("resources/"+ fileName).getFile());
		} catch (Exception e) {
//			log.info("1");
		}
		try {
			classLoader.getResource(fileName).getPath();
			return new File( classLoader.getResource( fileName).getFile());

		} catch (Exception e) {
//			log.info("2");
		}
		return null;
	}
}

