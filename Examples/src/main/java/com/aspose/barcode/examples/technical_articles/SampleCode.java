package com.aspose.barcode.examples.technical_articles;
//ExStart: SampleCode
import com.aspose.barcode.License;
import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.DecodeType;

public class SampleCode {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		License license = new License();
		license.setLicense("C:\\temp\\Aspose.Total.Product.Family.lic");
		
		BarCodeReader reader = new BarCodeReader("C:\\temp\\MyBarCode.jpg", DecodeType.PDF_417);
		while (reader.read())
		{
			System.out.println("Symbol:" + reader.getCodeTypeName() + " Code :" + reader.getCodeText());
		}
		reader.close();
	}
}
//ExEnd: SampleCode