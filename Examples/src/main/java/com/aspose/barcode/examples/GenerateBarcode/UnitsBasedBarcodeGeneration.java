package com.aspose.barcode.examples.GenerateBarcode;

import java.io.IOException;

import com.aspose.barcode.EncodeTypes;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.generation.AutoSizeMode;
import com.aspose.barcode.generation.BarcodeGenerator;

public class UnitsBasedBarcodeGeneration {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		String dataDir = Utils.getDataDir(UnitsBasedBarcodeGeneration.class) + "GenerateBarcode/";
		// ExStart: UnitsBasedBarcodeGeneration
		BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.QR, "Aspose.BarCode sample");
		generator.getParameters().setAutoSizeMode(AutoSizeMode.NEAREST);
		generator.getParameters().getImageWidth().setMillimeters(20);
		generator.getParameters().getImageHeight().setMillimeters(20);
		generator.save(dataDir + "for_display_out.png");

		generator.getParameters().setResolution(300);
		generator.save(dataDir + "for_printer_out.png");
		// ExEnd: UnitsBasedBarcodeGeneration
	}

}
