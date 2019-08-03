package com.aspose.barcode.examples.GenerateBarcode;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import com.aspose.barcode.EncodeTypes;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.generation.AutoSizeMode;
import com.aspose.barcode.generation.BarcodeGenerator;

import javax.imageio.ImageIO;

public class ImplementInterpolationAutoSizemode {

    public static void main(String[] args) throws IOException {
        String dataDir = Utils.getDataDir(GroupingPropertiesByBarcodeType.class) + "GenerateBarcode/";

        //ExStart: ImplementInterpolationAutoSizemode
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.DATA_MATRIX);
        generator.getParameters().getBarcode().setAutoSizeMode(AutoSizeMode.INTERPOLATION);
        generator.getParameters().getBarcode().getBarCodeWidth().setMillimeters(50);
        generator.getParameters().getBarcode().getBarCodeHeight().setInches(1.3f);

        BufferedImage bitmap = generator.generateBarCodeImage();
        File imageFile = new File(dataDir + "DataMatrix.png");
        ImageIO.write(bitmap, "png", imageFile);
        //ExEnd: ImplementInterpolationAutoSizemode
    }

}
