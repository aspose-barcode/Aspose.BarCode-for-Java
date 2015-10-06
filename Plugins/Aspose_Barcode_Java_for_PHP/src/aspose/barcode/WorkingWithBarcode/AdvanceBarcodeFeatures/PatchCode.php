<?php
namespace Aspose\Barcode\WorkingWithBarcode\AdvanceBarcodeFeatures;

use com\aspose\barcode\BarCodeBuilder as BarCodeBuilder;
use com\aspose\barcode\Symbology as Symbology;

class PatchCode{

    public static function run($dataDir=null){

        # Instantiate barcode object
        $builder = new BarCodeBuilder();

        # Set Symbology type
        $symbology=new Symbology();
        $builder->setSymbologyType($symbology->PatchCode);

        # Set code text
        $builder->setCodeText("Patch T");

        # Save the image to your system and set its image format to Jpeg
        $builder->save($dataDir . "PatchCode.jpg");

        # Display Status
        print "Generated PatchCode Successfully.".PHP_EOL;

    }

}