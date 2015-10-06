<?php
namespace Aspose\Barcode\WorkingWithBarcodeRecognition\AdvancedBarcodeRecognitionFeatures;

use com\aspose\barcoderecognition\BarCodeReadType as BarCodeReadType;
use com\aspose\barcoderecognition\BarCodeReader as BarCodeReader;

class GetAllOneDBarcodes{

    public static function run($dataDir=null){

        $img = $dataDir . "barcode.jpg";

        # initialize barcode reader
        $barcode_reader_type = new BarCodeReadType();
        $reader = new BarCodeReader($img, $barcode_reader_type->Code39Standard);

        # Call read method
        $reader->read();

        # Now get all possible barcodes
        $barcodes = $reader->getAllPossibleBarCodes();

        $i = 0;
        while(java_values($i < strlen($barcodes))) {

        # Display code text, symbology, detected angle, recognition percentage of the barcode
        print "Code Text: " . (string)$barcodes[$i]->getCodetext() . " Symbology: " . (string)$barcodes[$i]->getBarCodeReadType() . " Recognition percentage: " . (string)$barcodes[$i]->getAngle().PHP_EOL;

        # Display x and y coordinates of barcode detected
        $point = $barcodes[$i]->getRegion()->getPoints();

        print "Top left coordinates: X = " . (string)$point[0]->getX() . ", Y = " . (string)$point[0]->getY().PHP_EOL;
        print "Bottom left coordinates: X = " . (string)$point[1]->getX() . ", Y = " . (string)$point[1]->getY().PHP_EOL;
        print "Bottom right coordinates: X = " . (string)$point[2]->getX() . ", Y = " . (string)$point[2]->getY().PHP_EOL;
        print "Top right coordinates: X = " . (string)$point[3]->getX(). ", Y = " . (string)$point[3]->getY().PHP_EOL;
        break;
        }
        # Close reader
        $reader->close();
    }

}