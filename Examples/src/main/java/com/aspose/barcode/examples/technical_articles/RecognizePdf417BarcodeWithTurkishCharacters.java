package com.aspose.barcode.examples.technical_articles;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarCodeResult;
import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.examples.Utils;

public class RecognizePdf417BarcodeWithTurkishCharacters {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// ExStart: RecognizePdf417BarcodeWithTurkishCharacters
		String dataDir = Utils.getDataDir(RecognizePdf417BarcodeWithTurkishCharacters.class) + "TechnicalArticles/";
		// Load barcode image
		BarCodeReader reader = new BarCodeReader(dataDir + "barcode.png", DecodeType.PDF_417);

		// Read barcode
		for (BarCodeResult result : reader.readBarCodes()) {
			// Get byte array and decode
			byte[] bytes = result.getCodeBytes();
			ByteBuffer bytebuf = ByteBuffer.wrap(bytes);
			System.out.println(Charset.forName("windows-1254").decode(bytebuf).toString());
		}
		// ExEnd: RecognizePdf417BarcodeWithTurkishCharacters
	}

}
