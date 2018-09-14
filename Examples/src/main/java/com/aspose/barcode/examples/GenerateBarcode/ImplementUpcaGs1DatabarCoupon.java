package com.aspose.barcode.examples.GenerateBarcode;

import java.io.IOException;

import com.aspose.barcode.EncodeTypes;
import com.aspose.barcode.QRErrorLevel;
import com.aspose.barcode.QRVersion;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.generation.BarCodeGenerator;

public class ImplementUpcaGs1DatabarCoupon {

    public static void main(String[] args) throws IOException {
        String dataDir = Utils.getDataDir(GroupingPropertiesByBarcodeType.class) + "GenerateBarcode/";

        //ExStart: ImplementUpcaGs1DatabarCoupon
        BarCodeGenerator generator = new BarCodeGenerator(EncodeTypes.UPCA_GS_1_DATABAR_COUPON);
        generator.save("UpcaGs1DatabarCoupon.png");
        //ExEnd: ImplementUpcaGs1DatabarCoupon
    }

}
