<?php
namespace Aspose\Barcode\WorkingWith2DBarcodes\Basic2DBarcodeFeatures;

use com\aspose\barcode\BarCodeBuilder as BarCodeBuilder;
use com\aspose\barcode\Symbology as Symbology;

class Creating2DBarcode{

    public static function run($dataDir=null){

        # Instantiate barcode object
        $builder = new BarCodeBuilder();

        $symbology=new Symbology();
        $builder->setSymbologyType($symbology->Pdf417);

        # Width of each module
        $builder->setxDimension(0.6);

        # Height of each module
        $builder->setyDimension(1.2);
        $builder->setCodeText("this is some test code text. \n Second line \n third line.");

        # Save the image to your system and set its image format to Jpeg
        $builder->save($dataDir . "Creating2DBarcode.jpg");

        # Display Status
        print "Created 2D Barcode Successfully.".PHP_EOL;
    }
}