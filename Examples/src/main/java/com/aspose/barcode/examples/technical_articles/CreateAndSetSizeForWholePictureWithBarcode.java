package com.aspose.barcode.examples.technical_articles;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.aspose.barcode.CodeLocation;
import com.aspose.barcode.EncodeTypes;
import com.aspose.barcode.generation.BarCodeGenerator;

public class CreateAndSetSizeForWholePictureWithBarcode {

	public static void main(String[] args) throws IOException {
		// ExStart: CreateAndSetSizeForWholePictureWithBarcode
		// Generate the bar code
		BarCodeGenerator generator = new BarCodeGenerator(EncodeTypes.PDF_417);
		
		// Set the code text
		generator.setCodeText("One thing 2 thing");
		
		// Set the code text location
		generator.getCodeTextStyle().setLocation(CodeLocation.NONE);
		
		// Set margins
		generator.getMargins().getBottom().setPixels(0);
		generator.getMargins().getLeft().setPixels(0);
		generator.getMargins().getRight().setPixels(0);
		generator.getMargins().getTop().setPixels(0);
		
		// Get BufferedImage with exact bar code only
		BufferedImage img = generator.generateBarCodeImage();
		
		// Saving the buffered image
		File outputfile = new File("custombarcode.png");
		ImageIO.write(img, "png", outputfile);
		// ExEnd: CreateAndSetSizeForWholePictureWithBarcode
	}
}
