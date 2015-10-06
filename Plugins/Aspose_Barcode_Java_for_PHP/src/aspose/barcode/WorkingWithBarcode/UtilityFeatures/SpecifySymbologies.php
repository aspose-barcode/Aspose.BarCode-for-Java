<?php
namespace Aspose\Barcode\WorkingWithBarcode\UtilityFeatures;

use com\aspose\barcode\BarCodeBuilder as BarCodeBuilder;
use com\aspose\barcode\Symbology as Symbology;

class SpecifySymbologies{

    public static function run($dataDir=null){

        $builder = new BarCodeBuilder();

        # ============ Code39Standard =====================
        # set symbology type
        $symbology=new Symbology();
        $builder->setSymbologyType($symbology->Code39Standard);

        # Save image to disk
        $builder->save($dataDir . "code39Standard.out.png");

        # ================== QR ===========================
        # set symbology type
        $builder->setSymbologyType($symbology->QR);

        # Save image to disk
        $builder->save($dataDir . "QR.out.png");

        # =============== Code128 =========================
        # set symbology type
        $builder->setSymbologyType($symbology->Code128);

        # Save image to disk
        $builder->save($dataDir . "code128.out.png");

        # Print message
        print "Barcode image(s) generated successfully.".PHP_EOL;
    }
}