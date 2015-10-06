<?php
namespace Aspose\Barcode\WorkingWith2DBarcodes\Basic2DBarcodeFeatures;

use com\aspose\barcode\BarCodeBuilder as BarCodeBuilder;
use com\aspose\barcode\Symbology as Symbology;

class CreatingAztecBarcode{

    public static function run($dataDir=null){

        # Instantiate barcode object
        $builder = new BarCodeBuilder();

        $symbology=new Symbology();
        $builder->setSymbologyType($symbology->Aztec);

        $builder->setCodeText("1234567890");

        # Save the image to your system and set its image format to Jpeg
        $builder->save($dataDir . "CreatingAztecBarcode.jpg");

        # Display Status
        print "Created Aztec Barcode Successfully.".PHP_EOL;
    }
}