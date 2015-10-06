<?php
namespace Aspose\Barcode\WorkingWithBarcodeRecognition\AdvancedBarcodeRecognitionFeatures;

use com\aspose\barcoderecognition\BarCodeReadType as BarCodeReadType;
use com\aspose\barcoderecognition\BarCodeReader as BarCodeReader;

class GetBarcodeRecognitionQuality{

    public static function run($dataDir=null)
    {

        $img = $dataDir . "barcode.jpg";

        # initialize barcode reader
        $barcode_reader_type = new BarCodeReadType();
        $reader = new BarCodeReader($img, $barcode_reader_type->Code39Standard);

        # Call read method
        while (java_values($reader->read())){
            print "Barcode CodeText: " . (string)$reader->getCodeText(). " Barcode Type: ".(string)$reader->getReadType().PHP_EOL;
            $percent = $reader->getRecognitionQuality();
            print "Barcode Quality Percentage: ".(string)$percent.PHP_EOL;
        }

        # Close reader
        $reader->close();
    }
}