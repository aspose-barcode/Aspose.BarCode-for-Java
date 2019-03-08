package com.aspose.barcode.examples.TwoD_barcodes.utility_features;

import java.awt.Font;
import java.io.IOException;

import com.aspose.barcode.BarCodeBuilder;
import com.aspose.barcode.BarCodeImageFormat;
import com.aspose.barcode.CodeLocation;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.generation.BarCodeGenerator;

public class HidingCodeTextThatIsTooLongToDisplay 
{

	public static void main(String[] args) throws IOException 
        {
		// The path to the resource directory.
		String dataDir = Utils.getDataDir(HidingCodeTextThatIsTooLongToDisplay.class) + "2DBarcode/UtilityFeatures/";
		hideTheBarcodeCodeText(dataDir);
		reduceTheCodeTextFontSize(dataDir);
	}

	public static void hideTheBarcodeCodeText(String dataDir) throws IOException {
		
		BarCodeGenerator generator = new BarCodeGenerator(com.aspose.barcode.EncodeTypes.MACRO_PDF_417);
		generator.setCodeText("The quick brown fox jumps over the lazy dog\n The quick brown fox jumps over the lazy dog\n");
		generator.getCodeTextStyle().setLocation(CodeLocation.BELOW);
		
		generator.save(dataDir + "datamatrix.bmp", BarCodeImageFormat.BMP);
	}
	
	public static void reduceTheCodeTextFontSize(String dataDir) throws IOException {
		
		BarCodeGenerator generator = new BarCodeGenerator(com.aspose.barcode.EncodeTypes.DATA_MATRIX);
		generator.setCodeText("The quick brown fox jumps over the lazy dog\n The quick brown fox jumps over the lazy dog\n");
		generator.getCodeTextStyle().setLocation(CodeLocation.NONE);
		generator.getCodeTextStyle().getFont().setFamilyName("Serif");
		generator.getCodeTextStyle().getFont().setStyle(20);
		
		generator.save(dataDir + "reduceFontSize.bmp", BarCodeImageFormat.BMP);
	}
}
