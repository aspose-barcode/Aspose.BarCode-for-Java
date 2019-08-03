package com.aspose.barcode.examples.technical_articles;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.aspose.barcode.generation.CodeLocation;
import com.aspose.barcode.EncodeTypes;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.examples.TwoD_barcodes.basic_features.SetAztecSymbolMode;
import com.aspose.barcode.generation.BarcodeGenerator;

public class CreateAndSetSizeForWholePictureWithBarcode {

	public static void main(String[] args) throws IOException {
		// ExStart: CreateAndSetSizeForWholePictureWithBarcode
		String dataDir = Utils.getDataDir(CreateAndSetSizeForWholePictureWithBarcode.class) + "TechnicalArticles/";
		// Generate the bar code
		BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.PDF_417);
		
		// Set the code text
		generator.setCodeText("One thing 2 thing");
		
		// Set the code text location
		generator.getParameters().getBarcode().getCodeTextParameters().setLocation(CodeLocation.NONE);
		
		// Set margins
		generator.getParameters().getBarcode().getPadding().getBottom().setPixels(0);
		generator.getParameters().getBarcode().getPadding().getLeft().setPixels(0);
		generator.getParameters().getBarcode().getPadding().getRight().setPixels(0);
		generator.getParameters().getBarcode().getPadding().getTop().setPixels(0);
		
		// Get BufferedImage with exact bar code only
		BufferedImage img = generator.generateBarCodeImage();
		
		// Saving the buffered image
		File outputfile = new File(dataDir + "custombarcode.png");
		ImageIO.write(img, "png", outputfile);
		// ExEnd: CreateAndSetSizeForWholePictureWithBarcode
	}
}
