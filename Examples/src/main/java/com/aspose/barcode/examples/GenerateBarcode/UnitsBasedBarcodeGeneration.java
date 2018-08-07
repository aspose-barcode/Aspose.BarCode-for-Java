package com.aspose.barcode.examples.GenerateBarcode;

import java.io.IOException;

import com.aspose.barcode.EncodeTypes;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.generation.AutoSizeMode;
import com.aspose.barcode.generation.BarCodeGenerator;

public class UnitsBasedBarcodeGeneration {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		String dataDir = Utils.getDataDir(UnitsBasedBarcodeGeneration.class) + "GenerateBarcode/";
		//ExStart: UnitsBasedBarcodeGeneration
		BarCodeGenerator generator = new BarCodeGenerator(EncodeTypes.QR, "Aspose.BarCode sample");
	    generator.setAutoSizeMode (AutoSizeMode.Nearest);
	    generator.getBarCodeWidth().setMillimeters(20);
	    generator.getBarCodeHeight().setMillimeters(20);
	    generator.save(dataDir + "for_display_out.png");

	    generator.setResolution(300);
	    generator.save(dataDir + "for_printer_out.png");
		//ExEnd: UnitsBasedBarcodeGeneration
	}

}
