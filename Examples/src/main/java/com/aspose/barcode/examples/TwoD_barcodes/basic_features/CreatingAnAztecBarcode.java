package com.aspose.barcode.examples.TwoD_barcodes.basic_features;

import java.io.IOException;

import com.aspose.barcode.BarCodeBuilder;
import com.aspose.barcode.BarCodeImageFormat;
import com.aspose.barcode.Symbology;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.examples.barcode.basic_features.SpecifySymbology;

public class CreatingAnAztecBarcode {

	public static void main(String[] args) throws IOException {
		// The path to the resource directory.
		String dataDir = Utils.getDataDir(SpecifySymbology.class) + "2DBarcode/BasicFeatures/";
		createAnAztecBarcode(dataDir);
		errorCorrection(dataDir);
	}

	public static void createAnAztecBarcode(String dataDir) throws IOException {
		
		BarCodeBuilder b = new BarCodeBuilder();
		b.setSymbologyType(Symbology.Aztec);
		b.setCodeText("1234567890");
		
		b.save(dataDir + "aztec.bmp", BarCodeImageFormat.Bmp);
	}
	
	public static void errorCorrection(String dataDir) throws IOException {
		
		BarCodeBuilder b = new BarCodeBuilder();
		b.setSymbologyType(Symbology.Aztec);
		b.setAztectErrorLevel(50);
		b.setCodeText("1234567890");
		
		b.save(dataDir + "error_correction_aztec.bmp", BarCodeImageFormat.Bmp);
	}

}
