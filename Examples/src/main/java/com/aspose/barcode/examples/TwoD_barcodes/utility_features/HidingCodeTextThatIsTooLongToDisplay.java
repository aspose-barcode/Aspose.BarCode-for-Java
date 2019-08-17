package com.aspose.barcode.examples.TwoD_barcodes.utility_features;

import java.awt.Font;
import java.io.IOException;

import com.aspose.barcode.BarCodeImageFormat;
import com.aspose.barcode.generation.CodeLocation;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.generation.BarcodeGenerator;

public class HidingCodeTextThatIsTooLongToDisplay {

	public static void main(String[] args) throws IOException {
		// The path to the resource directory.
		String dataDir = Utils.getDataDir(HidingCodeTextThatIsTooLongToDisplay.class) + "2DBarcode/UtilityFeatures/";
		hideTheBarcodeCodeText(dataDir);
		reduceTheCodeTextFontSize(dataDir);
	}

	public static void hideTheBarcodeCodeText(String dataDir) throws IOException {
		// ExStart: hideTheBarcodeCodeText
		BarcodeGenerator generator = new BarcodeGenerator(com.aspose.barcode.EncodeTypes.MACRO_PDF_417);
		generator.setCodeText(
				"The quick brown fox jumps over the lazy dog\n The quick brown fox jumps over the lazy dog\n");
		generator.getParameters().getBarcode().getCodeTextParameters().setLocation(CodeLocation.BELOW);

		generator.save(dataDir + "datamatrix.bmp", BarCodeImageFormat.BMP);
		// ExEnd: hideTheBarcodeCodeText
	}

	public static void reduceTheCodeTextFontSize(String dataDir) throws IOException {
		// ExStart: reduceTheCodeTextFontSize
		BarcodeGenerator generator = new BarcodeGenerator(com.aspose.barcode.EncodeTypes.DATA_MATRIX);
		generator.setCodeText(
				"The quick brown fox jumps over the lazy dog\n The quick brown fox jumps over the lazy dog\n");
		generator.getParameters().getBarcode().getCodeTextParameters().setLocation(CodeLocation.NONE);
		generator.getParameters().getBarcode().getCodeTextParameters().getFont().setFamilyName("Serif");
		generator.getParameters().getBarcode().getCodeTextParameters().getFont().setStyle(20);

		generator.save(dataDir + "reduceFontSize.bmp", BarCodeImageFormat.BMP);
		// ExEnd: reduceTheCodeTextFontSize
	}
}
