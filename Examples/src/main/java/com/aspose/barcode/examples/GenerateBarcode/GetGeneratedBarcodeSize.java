package com.aspose.barcode.examples.GenerateBarcode;

import com.aspose.barcode.EncodeTypes;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.generation.BarcodeGenerator;

public class GetGeneratedBarcodeSize {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String dataDir = Utils.getDataDir(GetGeneratedBarcodeSize.class) + "GenerateBarcode/";
		// ExStart: GetGeneratedBarcodeSize
		BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.QR, "Aspose.BarCode sample");
		generator.getParameters().getBarcode().getXDimension().setPixels(3);
		generator.getParameters().getBarcode().getQR().setAspectRatio(1.5f);

		System.out.println("Width = " + generator.getParameters().getBarcode().getBarCodeWidth().getPixels());
		System.out.println("Height = " + generator.getParameters().getBarcode().getBarCodeHeight().getPixels());
		// ExEnd: GetGeneratedBarcodeSize
	}

}
