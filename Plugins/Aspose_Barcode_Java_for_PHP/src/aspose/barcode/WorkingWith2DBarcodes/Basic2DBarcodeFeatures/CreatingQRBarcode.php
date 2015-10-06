<?php
namespace Aspose\Barcode\WorkingWith2DBarcodes\Basic2DBarcodeFeatures;

use com\aspose\barcode\BarCodeBuilder as BarCodeBuilder;
use com\aspose\barcode\Symbology as Symbology;
use com\aspose\barcode\CodeLocation as CodeLocation;
class CreatingQRBarcode{

    public static function run($dataDir=null){

        # Instantiate barcode object
        $builder = new BarCodeBuilder();

        $symbology=new Symbology();
        $builder->setSymbologyType($symbology->QR);

        $builder->setCodeText("1234567890");

        # Hide code text
        $codeLocation=new CodeLocation();
        $builder->setCodeLocation($codeLocation->None);

        $builder->setRotationAngleF(90);

        # Save the image to your system and set its image format to Jpeg
        $builder->save($dataDir . "CreatingQRBarcode.jpg");

        # Display Status
        print "Created QR Barcode Successfully.".PHP_EOL;

    }


}