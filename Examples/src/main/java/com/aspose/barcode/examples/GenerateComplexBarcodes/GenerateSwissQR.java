package com.aspose.barcode.examples.GenerateComplexBarcodes;

import java.awt.image.BufferedImage;
import java.io.IOException;

import com.aspose.barcode.BarCodeImageFormat;
import com.aspose.barcode.complexbarcode.ComplexBarcodeGenerator;
import com.aspose.barcode.complexbarcode.SwissQRCodetext;
import com.aspose.barcode.examples.Utils;

public class GenerateSwissQR {

	public static void main(String[] args) throws IOException {
		// The path to the documents directory.
		String dataDir = Utils.getDataDir(GenerateSwissQR.class) + "GenerateSwissQR/";
		// ExStart:GenerateComplexBarcodes
		// Instantiate barcode object and set CodeText
		SwissQRCodetext swissQRCodetext = new SwissQRCodetext();
		swissQRCodetext.getBill().setAccount("Account");
		swissQRCodetext.getBill().setBillInformation("BillInformation");
		swissQRCodetext.getBill().setCurrency("EUR");

		ComplexBarcodeGenerator generator = new ComplexBarcodeGenerator(swissQRCodetext);
		BufferedImage img = generator.generateBarCodeImage();

		generator.getParameters().getBarcode().getCodeTextParameters().setTwoDDisplayText("test");

		// Save the Barcode image in JPEG format
		generator.save(dataDir + "swissQRCodetext_out.png", BarCodeImageFormat.PNG);
		// ExEnd:GenerateComplexBarcodes
		System.out.println("Barcode saved at " + dataDir);
	}

}
