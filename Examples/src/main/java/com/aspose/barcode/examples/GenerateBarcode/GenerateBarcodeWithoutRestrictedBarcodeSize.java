package com.aspose.barcode.examples.GenerateBarcode;

import java.io.IOException;

import com.aspose.barcode.EncodeTypes;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.generation.BarcodeGenerator;

public class GenerateBarcodeWithoutRestrictedBarcodeSize {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		String dataDir = Utils.getDataDir(GenerateBarcodeWithRestrictedBarcodeSize.class) + "GenerateBarcode/";
		//ExStart: GenerateBarcodeWithoutRestrictedBarcodeSize
		BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.QR, "Aspose.BarCode sample");
	    generator.getParameters().getBarcode().getXDimension().setPixels(3);
	    generator.getParameters().getBarcode().getPadding().getLeft().setPixels(20);
	    generator.getParameters().getBarcode().getPadding().getRight().setPixels(20);
	    generator.save(dataDir + "GenerateBarcodeWithoutRestrictedBarcodeSize_out.png");
		//ExEnd: GenerateBarcodeWithoutRestrictedBarcodeSize
	}
}
