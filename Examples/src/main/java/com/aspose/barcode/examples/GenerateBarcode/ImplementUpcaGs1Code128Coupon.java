package com.aspose.barcode.examples.GenerateBarcode;

import java.io.IOException;

import com.aspose.barcode.EncodeTypes;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.generation.BarCodeGenerator;

public class ImplementUpcaGs1Code128Coupon {

	public static void main(String[] args) throws IOException {
		String dataDir = Utils.getDataDir(GroupingPropertiesByBarcodeType.class) + "GenerateBarcode/";

        //ExStart: ImplementUpcaGs1Code128Coupon
        BarCodeGenerator generator = new BarCodeGenerator(EncodeTypes.UPCA_GS_1_CODE_128_COUPON);
        
        // UPCA part is "514141100906", GS1Code128 part is "(8102)03"
        generator.setCodeText("514141100906(8102)03");
        
        generator.save(dataDir + "UpcaGs1Code128Coupon.png");
        //ExEnd: ImplementUpcaGs1Code128Coupon

	}

}
