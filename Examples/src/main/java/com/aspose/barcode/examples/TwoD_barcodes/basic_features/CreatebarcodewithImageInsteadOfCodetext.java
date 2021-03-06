package com.aspose.barcode.examples.TwoD_barcodes.basic_features;

import java.io.IOException;

import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.generation.BarcodeGenerator;

public class CreatebarcodewithImageInsteadOfCodetext {
	public static void main(String[] args) throws IOException {
		// ExStart: CreatebarcodewithImageInsteadOfCodetext
		// The path to the resource directory.
		String dataDir = Utils.getDataDir(SetAztecSymbolMode.class) + "TwoD_barcodes/BasicFeatures/";

		// Create an instance of BarCodeGenerator class
		// Set the barcode text
		// Set the barcode symbology
		BarcodeGenerator generator = new BarcodeGenerator(com.aspose.barcode.EncodeTypes.EAN_13, "123456789012");

		// Generate Barcode image and store it
		java.awt.image.BufferedImage barcode = generator.generateBarCodeImage();

		// Load the logo/other image from disk
		java.awt.image.BufferedImage picture = javax.imageio.ImageIO.read(new java.io.File("path_to_your_picture.png"));

		// Create a new empty image with new Calculated height &amp; width
		java.awt.image.BufferedImage output = new java.awt.image.BufferedImage(
				Math.max(barcode.getWidth(), picture.getWidth()), barcode.getHeight() + picture.getHeight(),
				java.awt.image.BufferedImage.TYPE_INT_ARGB);

		// Get the Graphics object
		java.awt.Graphics g = output.getGraphics();

		// Set the canvas color
		g.setColor(java.awt.Color.WHITE);

		// Draw the primary image (barcode image) on the canvas
		g.drawImage(picture, 0, 0, null);

		// Draw the second image (logo image) on the canvas inside the barcode image
		g.drawImage(barcode, 0, picture.getHeight(), null);

		// Save the final barcode image
		java.io.File imageFile = new java.io.File(dataDir + "output.png");
		javax.imageio.ImageIO.write(output, "PNG", imageFile);
		// ExEnd: CreatebarcodewithImageInsteadOfCodetext
	}
}
