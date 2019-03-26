package com.aspose.barcode.examples.technical_articles;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.DecodeType;

public class RecognizePdf417BarcodeWithTurkishCharacters {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// ExStart: RecognizePdf417BarcodeWithTurkishCharacters
		// Load barcode image
		BarCodeReader reader = new BarCodeReader("c:\\temp\\barcode.png", DecodeType.PDF_417);
		 
		// Read barcode
		while(reader.read())
		{
		    // Get byte array and decode
		    byte[] bytes = reader.getCodeBytes();
		    ByteBuffer bytebuf = ByteBuffer.wrap(bytes);
		    System.out.println(Charset.forName("windows-1254").decode(bytebuf).toString());
		}
		reader.close();
		// ExEnd: RecognizePdf417BarcodeWithTurkishCharacters
	}

}
