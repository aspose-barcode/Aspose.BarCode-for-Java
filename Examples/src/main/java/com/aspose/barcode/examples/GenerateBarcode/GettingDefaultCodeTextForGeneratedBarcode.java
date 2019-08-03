package com.aspose.barcode.examples.GenerateBarcode;

import com.aspose.barcode.EncodeTypes;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.generation.BarcodeGenerator;

public class GettingDefaultCodeTextForGeneratedBarcode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String dataDir = Utils.getDataDir(GettingDefaultCodeTextForGeneratedBarcode.class) + "GenerateBarcode/";
		//ExStart: GettingDefaultCodeTextForGeneratedBarcode
		BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.AUSTRALIAN_POSTE_PARCEL);
		    
		String codetext = generator.getCodeText(); //99712345678901234567890103456

		generator = new BarcodeGenerator(EncodeTypes.EAN_13);
		     
		codetext = generator.getCodeText(); //590123412345
		//ExEnd: GettingDefaultCodeTextForGeneratedBarcode
	}

}
