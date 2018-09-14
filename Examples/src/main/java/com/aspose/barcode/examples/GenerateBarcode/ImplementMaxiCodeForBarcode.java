package com.aspose.barcode.examples.GenerateBarcode;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import com.aspose.barcode.*;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.generation.AutoSizeMode;
import com.aspose.barcode.generation.BarCodeGenerator;

import javax.imageio.ImageIO;

public class ImplementMaxiCodeForBarcode {

    public static void main(String[] args) throws IOException {
        String dataDir = Utils.getDataDir(GroupingPropertiesByBarcodeType.class) + "GenerateBarcode/";

        //ExStart: ImplementMaxiCodeForBarcode
        BarCodeGenerator generator = new BarCodeGenerator(EncodeTypes.MAXI_CODE);
        generator.setMaxiCodeEncodeMode(5);
        BufferedImage bitmap = generator.generateBarCodeImage();
        File imageFile = new File("MaxiCodeEncodeMode.png");
        ImageIO.write(bitmap, "png", imageFile);
        //ExEnd: ImplementMaxiCodeForBarcode
    }

}
