<?php
namespace Aspose\Barcode\WorkingWithBarcodeRecognition\BasicBarcodeRecognitionFeatures;

use com\aspose\barcoderecognition\BarCodeReadType as BarCodeReadType;
use com\aspose\barcoderecognition\BarCodeReader as BarCodeReader;


class RecognizeMultipleSymbologies{

    public static function run($dataDir=null){

        # initialize barcode reader
        $img = $dataDir . "symbologies.png";
        $barcode_reader_type = new BarCodeReadType();

        $rd = new BarCodeReader($img, $barcode_reader_type->Code39Standard);//Code39Standard  AllSupportedTypes

        # read barcode
        while(java_values($rd->read())){
            # print the code text, if barcode found
            print "CodeText: " . (string)$rd->getCodeText().PHP_EOL;

            # print the symbology type
            print "Type: " . (string)$rd->getReadType().PHP_EOL;
        }
    }
}