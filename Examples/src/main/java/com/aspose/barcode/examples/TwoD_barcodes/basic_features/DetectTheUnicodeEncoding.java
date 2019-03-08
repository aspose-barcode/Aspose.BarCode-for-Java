package com.aspose.barcode.examples.TwoD_barcodes.basic_features;

import java.nio.charset.StandardCharsets;

import com.aspose.barcode.BarCodeBuilder;
import com.aspose.barcode.BarCodeImageFormat;
import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.examples.ApplyALicense;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.generation.BarCodeGenerator;

public class DetectTheUnicodeEncoding {

	public static void main(String[] args) throws Exception {
		
		ApplyALicense.applyALicense();
		
		// The path to the resource directory.
		String dataDir = Utils.getDataDir(DetectTheUnicodeEncoding.class) + "2DBarcode/BasicFeatures/";
		String imageFilePath = dataDir + "unicodeEncoding.png";

		BarCodeGenerator generator = new BarCodeGenerator(com.aspose.barcode.EncodeTypes.QR, "Слово");

		generator.getD2().setCodeTextEncoding(StandardCharsets.UTF_8);
		generator.save(imageFilePath, BarCodeImageFormat.PNG);

		BarCodeReader reader = new BarCodeReader(imageFilePath, com.aspose.barcode.barcoderecognition.DecodeType.QR);
		reader.setDetectEncoding(true);

		if (reader.read()) {
			System.out.println(reader.getCodeText()); //"Слово"
		}
	}

}
