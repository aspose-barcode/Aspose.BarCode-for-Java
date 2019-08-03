package com.aspose.barcode.examples.technical_articles;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import com.aspose.barcode.EncodeTypes;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.generation.BarcodeGenerator;

public class Pdf417BarcodeWithChineseCharacters {

	public static void main(String[] args) throws IOException {
		// ExStart: Pdf417BarcodeWithChineseCharacters
		String dataDir = Utils.getDataDir(Pdf417BarcodeWithChineseCharacters.class) + "TechnicalArticles/";
		// Generate the barcode
		BarcodeGenerator gen = new BarcodeGenerator(EncodeTypes.PDF_417);
				 
		// Set the code text
		String codetext = "è¢«æ´ªæ°´å›°ä½�çš„";
		ByteBuffer bytebuffer = Charset.forName("MS936").encode(codetext);
		byte[] bytes = bytebuffer.array();
		String codeText = new String(bytes);
		gen.setCodeText(codeText);
				 
		// Set the display text
		gen.getParameters().getBarcode().getCodeTextParameters().setTwoDDisplayText(codetext);
		gen.save(dataDir + "barcode.png");
		// ExEnd: Pdf417BarcodeWithChineseCharacters
	}
}
