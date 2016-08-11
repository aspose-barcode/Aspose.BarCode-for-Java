package com.aspose.barcode.examples.TwoD_barcodes.utility_features;

import java.awt.Font;
import java.io.IOException;

import com.aspose.barcode.BarCodeBuilder;
import com.aspose.barcode.BarCodeImageFormat;
import com.aspose.barcode.CodeLocation;
import com.aspose.barcode.Symbology;
import com.aspose.barcode.examples.Utils;

public class HidingCodeTextThatIsTooLongToDisplay {

	public static void main(String[] args) throws IOException {
		// The path to the resource directory.
		String dataDir = Utils.getDataDir(HidingCodeTextThatIsTooLongToDisplay.class) + "2DBarcode/UtilityFeatures/";
		hideTheBarcodeCodeText(dataDir);
		reduceTheCodeTextFontSize(dataDir);
	}

	public static void hideTheBarcodeCodeText(String dataDir) throws IOException {
		
		BarCodeBuilder b = new BarCodeBuilder();
		b.setSymbologyType(Symbology.DataMatrix);
		b.setCodeText("The quick brown fox jumps over the lazy dog\n The quick brown fox jumps over the lazy dog\n");
		b.setCodeLocation(CodeLocation.Below);
		
		b.save(dataDir + "datamatrix.bmp", BarCodeImageFormat.Bmp);
	}
	
	public static void reduceTheCodeTextFontSize(String dataDir) throws IOException {
		
		BarCodeBuilder b = new BarCodeBuilder();
		b.setSymbologyType(Symbology.DataMatrix);
		b.setCodeText("The quick brown fox jumps over the lazy dog\n The quick brown fox jumps over the lazy dog\n");
		b.setCodeLocation(CodeLocation.None);
		b.setCodeTextFont(new Font("Serif", Font.BOLD + Font.ITALIC, 20));
		
		b.save(dataDir + "reduceFontSize.bmp", BarCodeImageFormat.Bmp);
	}
}
