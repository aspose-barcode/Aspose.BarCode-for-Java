package com.aspose.barcode.examples.barcode_recognition.advanced_features;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Paint;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarCodeRegion;
import com.aspose.barcode.examples.ApplyALicense;
import com.aspose.barcode.examples.Utils;

public class MarkingBarcodeRegionsInAnImage {

	public static void main(String[] args) throws Exception {
		ApplyALicense.applyALicense();
		
		// The path to the resource directory.
    	String dataDir = Utils.getDataDir(MarkingBarcodeRegionsInAnImage.class) + "BarcodeReader/advanced_features/";
		
		// Create an instance of BarCodeReader class and specify the image and symbology
		BarCodeReader reader = new BarCodeReader(dataDir + "Code39Std.png", com.aspose.barcode.barcoderecognition.DecodeType.CODE_39_STANDARD);

		// Read all the barcodes from the images
		while (reader.read()) {
			// Display the symbology type
			System.out.println("BarCode Type: " + reader.getCodeType());
			// Display the codetext
			System.out.println("BarCode CodeText: " + reader.getCodeText());
			// Get the barcode region
			BarCodeRegion region = reader.getRegion();
			if (region != null) {
				// Initialize an object of type BufferedImage to get the Graphics object
				BufferedImage bufferedImage = ImageIO.read(new File(dataDir + "Code39Std.png"));
				// Initialize graphics object from the image
				Graphics g = bufferedImage.getGraphics();
				// Initialize paint object
				Paint p = new GradientPaint(0, 0, Color.red, 100, 100, Color.pink, true);

				region.drawBarCodeEdges(g, Color.RED);

				// Save the image
				ImageIO.write(bufferedImage, "png", new File(dataDir + "Code39StdOut.png"));
			}
		}
		reader.close();
	}

}
