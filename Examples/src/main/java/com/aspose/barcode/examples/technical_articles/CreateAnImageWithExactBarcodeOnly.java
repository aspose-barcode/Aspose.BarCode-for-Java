package com.aspose.barcode.examples.technical_articles;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import com.aspose.barcode.EncodeTypes;
import com.aspose.barcode.generation.BarCodeGenerator;

public class CreateAnImageWithExactBarcodeOnly {

	public static void main(String[] args) throws IOException {
		// ExStart: CreateAnImageWithExactBarcodeOnly
		// Generate the barcode
		BarCodeGenerator generator = new BarCodeGenerator(EncodeTypes.CODE_128);
		
		// Set the code text
		generator.getD2().setDisplayText("123456");
		
		// Get bitmap with exact barcode only
		BufferedImage image = generator.generateBarCodeImage();
		
		// Saving the buffered image
		File outputfile = new File("custombarcode.png");
		ImageIO.write(image, "png", outputfile);
		//ExEnd: CreateAnImageWithExactBarcodeOnly
	}
}
