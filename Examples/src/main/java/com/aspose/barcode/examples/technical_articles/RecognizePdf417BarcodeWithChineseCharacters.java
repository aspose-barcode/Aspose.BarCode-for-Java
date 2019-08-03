package com.aspose.barcode.examples.technical_articles;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.examples.Utils;

public class RecognizePdf417BarcodeWithChineseCharacters {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// ExStart: RecognizePdf417BarcodeWithChineseCharacters
		String dataDir = Utils.getDataDir(RecognizePdf417BarcodeWithChineseCharacters.class) + "TechnicalArticles/";
		// Load barcode image
		BarCodeReader reader = new BarCodeReader(dataDir + "barcode.png", DecodeType.PDF_417);

		// Read barcode
		while (reader.read()) {
			// Get byte array and decode
			byte[] bytes = reader.getCodeBytes();
			ByteBuffer bytebuf = ByteBuffer.wrap(bytes);
			System.out.println(Charset.forName("MS936").decode(bytebuf).toString());
		}
		reader.close();
		// ExEnd: RecognizePdf417BarcodeWithChineseCharacters
	}

}
