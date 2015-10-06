<?php

namespace Aspose\Barcode\WorkingWithBarcode\AdvanceBarcodeFeatures;

use com\aspose\barcode\Symbology as Symbology;
use com\aspose\barcode\BarCodeBuilder as BarCodeBuilder;
use com\aspose\barcode\AustraliaPostFormatControlCode as AustraliaPostFormatControlCode;

class AustraliaPostBarcode{

    public static function run($dataDir=null){

        # Instantiate barcode object
        $symbology = new Symbology();
        $builder = new BarCodeBuilder("1234567890", $symbology->AustraliaPost);

        # Set format control code to standard
        $australiaPostFormatControlCode=new AustraliaPostFormatControlCode();
        $builder->setAustraliaPostFormatControlCode($australiaPostFormatControlCode->Standard);

        # Save the image to your system and set its image format to Jpeg
        $builder->save($dataDir . "AusraliaPost-Barcode.jpg");

        # Display Status
        print "Generated Austrailia Post Barcode Successfully.".PHP_EOL;
    }

}