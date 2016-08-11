package com.aspose.barcode.examples.barcode_image.utility_features;

import javax.print.PrintException;

import com.aspose.barcode.BarCodeBuilder;
import com.aspose.barcode.Symbology;

public class PrintingABarcodeImage {

	public static void main(String[] args) throws PrintException {
		// Create instance of BarCodeBuilder, specify codetext and symbology in the constructor
		BarCodeBuilder builder = new BarCodeBuilder("12345678", Symbology.Code128);
		// Set printer name
		builder.setPrinterName("ML-1640 Series");
		// start a print job
		builder.print();
	}

}
