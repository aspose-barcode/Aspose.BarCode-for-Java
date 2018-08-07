package com.aspose.barcode.examples.GenerateBarcode;

import com.aspose.barcode.EncodeTypes;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.generation.BarCodeGenerator;

public class GetGeneratedBarcodeSize {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String dataDir = Utils.getDataDir(GetGeneratedBarcodeSize.class) + "GenerateBarcode/";
		//ExStart: GetGeneratedBarcodeSize
		BarCodeGenerator generator = new BarCodeGenerator(EncodeTypes.QR, "Aspose.BarCode sample");
	    generator.getXDimension().setPixels(3);
	    generator.getD2().setAspectRatio(1.5f);
	    generator.recalculateValues();
	    System.out.println("Width = " + generator.getBarCodeWidth().getPixels());
	    System.out.println("Height = " + generator.getBarCodeWidth().getPixels());
		//ExEnd: GetGeneratedBarcodeSize
	}

}
