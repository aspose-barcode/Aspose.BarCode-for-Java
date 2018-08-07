package com.aspose.barcode.examples.GenerateBarcode;

import java.io.IOException;

import com.aspose.barcode.EncodeTypes;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.generation.BarCodeGenerator;

public class GenerateBarcodeWithoutRestrictedBarcodeSize {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		String dataDir = Utils.getDataDir(GenerateBarcodeWithRestrictedBarcodeSize.class) + "GenerateBarcode/";
		//ExStart: GenerateBarcodeWithoutRestrictedBarcodeSize
		BarCodeGenerator generator = new BarCodeGenerator(EncodeTypes.QR, "Aspose.BarCode sample");
	    generator.getXDimension().setPixels(3);
	    generator.getMargins().getLeft().setPixels(20);
	    generator.getMargins().getRight().setPixels(20);
	    generator.save(dataDir + "GenerateBarcodeWithoutRestrictedBarcodeSize_out.png");
		//ExEnd:GenerateBarcodeWithoutRestrictedBarcodeSize
	}
}
