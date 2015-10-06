<?php
namespace Aspose\Barcode\WorkingWithBarcodeRecognition\AdvancedBarcodeRecognitionFeatures;

use com\aspose\barcoderecognition\BarCodeReadType as BarCodeReadType;
use com\aspose\barcoderecognition\BarCodeReader as BarCodeReader;

use javax\imageio\ImageIO as ImageIO;
use java\io\File as File;
use java\awt\Color as Color;
use java\awt\GradientPaint;


class MakingBarcodeRegions{

    public static function run($dataDir=null){

        # initialize barcode reader
        $img = $dataDir . "barcode.jpg";
        $barcode_reader_type = new BarCodeReadType();
        $reader = new BarCodeReader($img, $barcode_reader_type->Code39Standard);

        # Try to recognize all possible barcodes in the image
        while (java_values($reader->read())){
            # Display the symbology type
            print "BarCode Type: " . (string)$reader->getReadType().PHP_EOL;
            # Display the codetext
            print "BarCode CodeText: " . (string)$reader->getCodeText().PHP_EOL;
            # Get the barcode region
            $region = $reader->getRegion();

            if ($region != null){
            # Initialize an object of type BufferedImage to get the Graphics object
                $imageIO=new ImageIO();
                //                $file=new File();
                $bufferedImage = $imageIO -> read(new File($img));

                # Initialize graphics object from the image
                $g = $bufferedImage -> getGraphics();

                $color = new Color();
                # Initialize paint object
                $p = new GradientPaint(0, 0, $color->red, 100, 100, $color->pink, true);

                $region->drawBarCodeEdges($g, $color->RED);

                # Save the image

                $imageIO->write($bufferedImage, "png", new File($dataDir . "Code39StdOut.png"));
            }
        }
        # Close reader
        $reader->close();
    }

}