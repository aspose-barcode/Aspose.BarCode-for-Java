<?php
namespace Aspose\Barcode\WorkingWithBarcode\AdvanceBarcodeFeatures;

use com\aspose\barcode\BarCodeBuilder as BarCodeBuilder;
use com\aspose\barcode\Symbology as Symbology;
use com\aspose\barcode\EnableChecksum as EnableChecksum;

class UseChecksumAndSupplementData{

    public static function run($dataDir=null){

        # Instantiate barcode object
        $bb = new BarCodeBuilder();

        # Set the Code text for the barcode
        $bb->setCodeText("1234567");

        # Set the symbology type to Code39
        $symbology=new Symbology();
        $bb->setSymbologyType($symbology->Code39Standard);

        # Make the checksum to be visible on the barcode
        $enableChecksum=new EnableChecksum();
        $bb->setEnableChecksum($enableChecksum->Default);

        # Save the image to disk in JPG format
        $bb->save($dataDir . "barcode.jpg");

        # Print message
        print "Barcode image generated successfully.".PHP_EOL;
    }
}