package com.aspose.barcode.examples.technical_articles;

//ExStart: SampleCode
import com.aspose.barcode.License;
import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.examples.Utils;

public class SampleCode {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String dataDir = Utils.getDataDir(SampleCode.class) + "TechnicalArticles/";
		
		BarCodeReader reader = new BarCodeReader(dataDir + "MyBarCode.jpg", DecodeType.PDF_417);
		while (reader.read()) {
			System.out.println("Symbol:" + reader.getCodeTypeName() + " Code :" + reader.getCodeText());
		}
		reader.close();
	}
}
//ExEnd: SampleCode