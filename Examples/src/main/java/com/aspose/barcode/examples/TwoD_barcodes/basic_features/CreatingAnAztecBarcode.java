package com.aspose.barcode.examples.TwoD_barcodes.basic_features;

import java.io.IOException;

import com.aspose.barcode.BarCodeBuilder;
import com.aspose.barcode.BarCodeImageFormat;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.generation.BarCodeGenerator;

public class CreatingAnAztecBarcode {

	public static void main(String[] args) throws IOException {
		// The path to the resource directory.
		String dataDir = Utils.getDataDir(CreatingAnAztecBarcode.class) + "2DBarcode/BasicFeatures/";
		createAnAztecBarcode(dataDir);
		errorCorrection(dataDir);
	}

	public static void createAnAztecBarcode(String dataDir) throws IOException {
		//ExStart: createAnAztecBarcode
		BarCodeGenerator generator = new BarCodeGenerator(com.aspose.barcode.EncodeTypes.AZTEC, "1234567890");
		
		generator.save(dataDir + "aztec.bmp", BarCodeImageFormat.BMP);
		//ExEnd: createAnAztecBarcode
	}
	
	public static void errorCorrection(String dataDir) throws IOException {
		//ExStart: errorCorrection
		BarCodeGenerator generator = new BarCodeGenerator(com.aspose.barcode.EncodeTypes.AZTEC, "1234567890");
		generator.getAztec().setErrorLevel(50);

		generator.save(dataDir + "error_correction_aztec.bmp", BarCodeImageFormat.BMP);
		//ExEnd: errorCorrection
	}

}
