package com.aspose.barcode.examples.GenerateBarcode;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import com.aspose.barcode.CodeLocation;
import com.aspose.barcode.EncodeTypes;
import com.aspose.barcode.QRErrorLevel;
import com.aspose.barcode.QRVersion;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.generation.AutoSizeMode;
import com.aspose.barcode.generation.BarCodeGenerator;

import javax.imageio.ImageIO;

public class GenerateCode16KBarCode {

    public static void main(String[] args) throws IOException {
        String dataDir = Utils.getDataDir(GroupingPropertiesByBarcodeType.class) + "GenerateBarcode/";

        //ExStart: GenerateCode16KBarCode
        BarCodeGenerator generator = new BarCodeGenerator(EncodeTypes.CODE_16_K);
        generator.setAutoSizeMode(AutoSizeMode.NEAREST);
        generator.getBarCodeWidth().setPixels(100);
        generator.getCodeTextStyle().setLocation(CodeLocation.NONE);
        generator.save("Code16K_Nearest.png");
        //ExEnd: GenerateCode16KBarCode
    }

}
