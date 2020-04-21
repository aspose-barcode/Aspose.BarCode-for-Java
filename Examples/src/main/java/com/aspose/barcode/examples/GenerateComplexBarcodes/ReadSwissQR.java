package com.aspose.barcode.examples.GenerateComplexBarcodes;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarCodeResult;
import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.complexbarcode.ComplexCodetextReader;
import com.aspose.barcode.complexbarcode.SwissQRCodetext;
import com.aspose.barcode.examples.Utils;

public class ReadSwissQR {

	public static void main(String[] args) {
		// The path to the documents directory.
		String dataDir = Utils.getDataDir(ReadSwissQR.class) + "ReadSwissQR/";
		// ExStart:ReadComplexBarcodes
		BarCodeReader reader = new BarCodeReader(dataDir + "swissQRCodetext_out.png", DecodeType.QR);
		for (BarCodeResult barcodeResult : reader.readBarCodes()) {
			SwissQRCodetext result = ComplexCodetextReader.tryDecodeSwissQR(barcodeResult.getCodeText());
			System.out.println("Account :" + result.getBill().getAccount());
			System.out.println("BillInformation = " + result.getBill().getBillInformation());
			System.out.println("Currency :" + result.getBill().getCurrency());
		}
		// ExEnd:ReadComplexBarcodes
	}
}