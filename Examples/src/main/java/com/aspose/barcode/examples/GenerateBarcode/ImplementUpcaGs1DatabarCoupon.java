package com.aspose.barcode.examples.GenerateBarcode;

import java.io.IOException;

import com.aspose.barcode.EncodeTypes;
import com.aspose.barcode.QRErrorLevel;
import com.aspose.barcode.QRVersion;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.generation.BarcodeGenerator;

public class ImplementUpcaGs1DatabarCoupon {

	public static void main(String[] args) throws IOException {
		String dataDir = Utils.getDataDir(GroupingPropertiesByBarcodeType.class) + "GenerateBarcode/";

		// ExStart: ImplementUpcaGs1DatabarCoupon
		BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.UPCA_GS_1_DATABAR_COUPON);

		// UPCA part is "514141100906", GS1Databar part is "(8110)001234502239811110555"
		generator.setCodeText("512345678900(8110)001234502239811110555");

		generator.save("UpcaGs1DatabarCoupon.png");
		// ExEnd: ImplementUpcaGs1DatabarCoupon
	}

}
