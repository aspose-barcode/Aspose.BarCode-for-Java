package com.aspose.barcode.examples.GenerateBarcode;

import java.io.IOException;

import com.aspose.barcode.EncodeTypes;
import com.aspose.barcode.QRErrorLevel;
import com.aspose.barcode.QRVersion;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.generation.BarcodeGenerator;

public class GroupingPropertiesByBarcodeType {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String dataDir = Utils.getDataDir(GroupingPropertiesByBarcodeType.class) + "GenerateBarcode/";

		//ExStart: GroupingPropertiesByBarcodeType
		BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.QR);
	    generator.getParameters().getBarcode().getQR().setQrVersion(QRVersion.VERSION_18);
	    generator.getParameters().getBarcode().getQR().setQrErrorLevel(QRErrorLevel.LEVEL_M);
	    generator.save(dataDir + "GroupingPropertiesByBarcodeType_out.png");
		//ExEnd: GroupingPropertiesByBarcodeType
	}

}
