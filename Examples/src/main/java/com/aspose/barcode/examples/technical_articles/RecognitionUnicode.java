package com.aspose.barcode.examples.technical_articles;

//ExStart: RecognitionUnicode
import com.aspose.barcode.*;
import com.aspose.barcode.generation.BarcodeGenerator;
import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarCodeResult;
import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.examples.Utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class RecognitionUnicode {
	public static void main(String[] args) throws IOException {

		String dataDir = Utils.getDataDir(Pdf417BarcodeWithTurkishCharacters.class) + "TechnicalArticles/";

		try {
			License lic = new License();
			lic.setLicense("aspose.barcode.lic");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		String file = dataDir + "pdf417_un.png";
		String scodeText = "منحة";
		System.out.println("codetext: " + scodeText);
		String codeText = getCodeTextFromUnicode(scodeText);
		BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.PDF_417, codeText);
		generator.save(file);

		BarCodeReader reader = new BarCodeReader(file, DecodeType.PDF_417);
		for (BarCodeResult result : reader.readBarCodes()) {
			String rc = result.getCodeText();
			try {
				String s = getUnicodeFromCodeText(rc);
				System.out.println(s);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
	}

	private static String getCodeTextFromUnicode(String s) throws UnsupportedEncodingException {
		byte[] bs = s.getBytes("UTF-8");
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < bs.length; i++) {
			byte b = bs[i];
			if (b >= 0) {
				buf.append((char) b);
			} else {
				buf.append((char) (127 - b));
			}
		}
		return buf.toString();
	}

	private static String getUnicodeFromCodeText(String cs) throws UnsupportedEncodingException {
		byte[] bs = new byte[cs.length()];
		for (int i = 0; i < cs.length(); i++) {
			char c = cs.charAt(i);
			if (c < 128) {
				bs[i] = (byte) c;
			} else {
				bs[i] = (byte) (127 - c);
			}
		}
		return new String(bs, " UTF-8");
	}
}
//ExEnd: RecognitionUnicode