package domain.in.rjsa.excel;

import java.awt.Image;

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
}

