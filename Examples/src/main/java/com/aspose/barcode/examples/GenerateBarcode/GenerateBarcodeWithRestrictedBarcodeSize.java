package com.aspose.barcode.examples.GenerateBarcode;

import java.io.IOException;

import com.aspose.barcode.EncodeTypes;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.generation.AutoSizeMode;
import com.aspose.barcode.generation.BarcodeGenerator;

public class GenerateBarcodeWithRestrictedBarcodeSize {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		String dataDir = Utils.getDataDir(GenerateBarcodeWithRestrictedBarcodeSize.class) + "GenerateBarcode/";

		// ExStart: GenerateBarcodeWithRestrictedBarcodeSize
		BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.QR, "Aspose.BarCode sample");

		generator.getParameters().setAutoSizeMode(AutoSizeMode.NEAREST);
		generator.getParameters().getImageWidth().setPixels(200);
		generator.getParameters().getImageHeight().setPixels(200);
		generator.save(dataDir + "GenerateBarcodeWithRestrictedBarcodeSize_out.png");
		// ExEnd: GenerateBarcodeWithRestrictedBarcodeSize
	}
}
