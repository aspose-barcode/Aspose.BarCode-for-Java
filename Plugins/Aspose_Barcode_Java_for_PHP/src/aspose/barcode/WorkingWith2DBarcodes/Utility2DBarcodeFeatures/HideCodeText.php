<?php
namespace Aspose\Barcode\WorkingWith2DBarcodes\Utility2DBarcodeFeatures;

use com\aspose\barcode\BarCodeBuilder as BarCodeBuilder;
use com\aspose\barcode\Symbology as Symbology;
use com\aspose\barcode\CodeLocation as CodeLocation;

use java\awt\Font as Font;
class HideCodeText{

    public static function run($dataDir = null)
    {

        # Instantiate barcode object
        $builder = new BarCodeBuilder();

        $symbology=new Symbology();
        $builder->setSymbologyType($symbology->DataMatrix);

        $builder->setCodeText("The quick brown fox jumps over the lazy dog\n The quick brown fox jumps over the lazy dog\n");

        $codeLocation=new CodeLocation();
        $builder->setCodeLocation($codeLocation->None);

        $font = new Font();

        $builder->setCodeTextFont(new Font("Serif", $font->BOLD . $font->ITALIC, 20));

        # Save the image
        $builder->save($dataDir . "HideCodeText.jpg");

        # Display Status
        print "Hide Code Text Successfully.".PHP_EOL;
    }
}