<?php
namespace Aspose\Barcode\WorkingWith2DBarcodes\Utility2DBarcodeFeatures;

use com\aspose\barcode\BarCodeBuilder as BarCodeBuilder;
use com\aspose\barcode\Symbology as Symbology;

class SetAspectRatio{

    public static function run($dataDir=null){

        # Instantiate barcode object
        $builder = new BarCodeBuilder();

        $symbology=new Symbology();
        $builder->setSymbologyType($symbology->Pdf417);

        $builder->setCodeText("1234567890");

        # Set Aspect Ratio to 3:2 or 1.5
        $builder->setAspectRatio(1.5);

        # Save the image
        $builder->save($dataDir . "SetAspectRatio.jpg");

        # Display Status
        print "Set Aspect Ratio Successfully.".PHP_EOL;
    }

}