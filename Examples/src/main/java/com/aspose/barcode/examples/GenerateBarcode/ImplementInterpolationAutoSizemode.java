package com.aspose.barcode.examples.GenerateBarcode;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import com.aspose.barcode.EncodeTypes;
import com.aspose.barcode.QRErrorLevel;
import com.aspose.barcode.QRVersion;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.generation.AutoSizeMode;
import com.aspose.barcode.generation.BarCodeGenerator;

import javax.imageio.ImageIO;

public class ImplementInterpolationAutoSizemode {

    public static void main(String[] args) throws IOException {
        String dataDir = Utils.getDataDir(GroupingPropertiesByBarcodeType.class) + "GenerateBarcode/";

        //ExStart: ImplementInterpolationAutoSizemode
        BarCodeGenerator generator = new BarCodeGenerator(EncodeTypes.DATA_MATRIX);
        generator.setAutoSizeMode(AutoSizeMode.Interpolation);
        generator.getBarCodeWidth().setMillimeters(50);
        generator.getBarCodeHeight().setInches(1.3f);

        BufferedImage bitmap = generator.generateBarCodeImage();
        File imageFile = new File("DataMatrix.png");
        ImageIO.write(bitmap, "png", imageFile);
        //ExEnd: ImplementInterpolationAutoSizemode
    }

}
