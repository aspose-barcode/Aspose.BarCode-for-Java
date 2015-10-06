<?php
namespace Aspose\Barcode\WorkingWithBarcodeRecognition\AdvancedBarcodeRecognitionFeatures;

use com\aspose\barcoderecognition\BarCodeReadType as BarCodeReadType;
use com\aspose\barcoderecognition\BarCodeReader as BarCodeReader;

use java\io\FileInputStream as FileInputStream;
use java\awt\Rectangle as Rectangle;

class ReadBarcodeFromSpecificRegion{

    public static function run($dataDir=null)
    {

        # Open the stream. Read only access is enough for Aspose.BarCode to load an image.
        $stream = new FileInputStream($dataDir . "test.png");

        # Create an instance of BarCodeReader class
        # and specify an area to look for the barcode

        $barcode_reader_type = new BarCodeReadType();
        $reader = new BarCodeReader($stream, new Rectangle(0, 0, 10, 50), $barcode_reader_type->Code39Standard);

        # TRead all barcodes in the provided area
        while (java_values($reader->read())){
            # Display the codetext and symbology type of the barcode found
            print "Codetext: " . (string)$reader->getCodeText() + " Symbology: " . (string)$reader->getReadType().PHP_EOL;
        }

        # Close reader
        $reader->close();
    }
}