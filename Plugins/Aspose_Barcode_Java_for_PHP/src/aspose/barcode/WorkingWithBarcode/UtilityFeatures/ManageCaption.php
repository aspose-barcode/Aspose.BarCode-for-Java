<?php
namespace Aspose\Barcode\WorkingWithBarcode\UtilityFeatures;

use com\aspose\barcode\BarCodeBuilder as BarCodeBuilder;
use com\aspose\barcode\Symbology as Symbology;
use com\aspose\barcode\Caption as Caption;
use com\aspose\barcode\Alignment as Alignment;
use com\aspose\barcode\BarCodeImageFormat as BarCodeImageFormat;

class ManageCaption{

    public static function run($dataDir=null){

        # Instantiate barcode object
        $bb = new BarCodeBuilder();

        # Set up code text (data to be encoded)
        $bb->setCodeText("1234567");

        # Set the symbology type to Code128
        $symbology=new Symbology();
        $bb->setSymbologyType($symbology->Code128);

        $caption = new Caption();
        $alignment=new Alignment();
        $caption->setText("Captions");
        $caption->setTextAlign($alignment->Middle);

        $bb->setCaptionAbove($caption);
        $bb->setCaptionBelow($caption);

        $bb->getCaptionAbove()->setTextAlign($alignment->Left);
        $bb->getCaptionAbove()->setText("Aspose");
        $bb->getCaptionAbove()->setVisible(true);
        #bb.getCaptionAbove().setFont(new java.awt.Font("Pristina", java.awt.Font.PLAIN, 14));
        #bb.getCaptionAbove().setForeColor(java.awt.Color.RED);

        $bb->getCaptionBelow()->setTextAlign($alignment->Right);
        $bb->getCaptionBelow()->setText("Aspose.BarCode Caption Below");
        $bb->getCaptionBelow()->setVisible(true);
        #bb.getCaptionBelow().setFont(new java.awt.Font("Pristina", Font.PLAIN, 14));
        #bb.getCaptionBelow().setForeColor(java.awt.Color.RED);

        # Save the image to your system and set its image format to Jpeg
        $barCodeImageFormat=new BarCodeImageFormat();
        $bb->save($dataDir . "barcode.jpg", $barCodeImageFormat->Jpeg);

        # Display Status
        print "Barcode with Captions saved successfully.".PHP_EOL;
    }
}