package com.aspose.barcode.examples.barcode_recognition.basic_features;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.examples.ApplyALicense;
import com.aspose.barcode.examples.Utils;

public class SupportedImageFormats {

	public static void main(String[] args) throws Exception {
		// For complete examples and data files, please go to
		// https://github.com/aspose-barcode/Aspose.BarCode-for-Java
		ApplyALicense.applyALicense();

		// ExStart: SupportedImageFormats
		// The path to the resource directory.
		String dataDir = Utils.getDataDir(RecognizingMultipleSymbologiesInSingleImage.class)
				+ "BarcodeReader/basic_features/";
		BufferedImage img = ImageIO.read(new File(dataDir + "MultipleBarcodes.png"));

		// Initialize barcode reader
		BarCodeReader rd = new BarCodeReader(img, com.aspose.barcode.barcoderecognition.DecodeType.CODE_39_EXTENDED);
		while (rd.read()) {
			// Print the code text, if barcode found
			System.out.println("CodeText: " + rd.getCodeText().toString());
		}
		// ExEnd: SupportedImageFormats
	}

}
