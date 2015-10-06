<?php
namespace Aspose\Barcode\WorkingWithBarcode\AdvanceBarcodeFeatures;

use com\aspose\barcode\BarCodeBuilder as BarCodeBuilder;
use com\aspose\barcode\Symbology as Symbology;
use com\aspose\barcode\CodabarSymbol as CodabarSymbol;

class StartStopSymbol{

    public static function run($dataDir=null){

        # Create instance of BarCodeBuilder, specify codetext and symbology in the constructor
        $symbology=new Symbology();
        $builder = new BarCodeBuilder("$ 123:50", $symbology->Codabar);

        # Set the codabar start symbol to A
        $codabarSymbol=new CodabarSymbol();
        $builder->setCodabarStartSymbol($codabarSymbol->A);

        # Set the codabar stop symbol to D
        $builder->setCodabarStopSymbol($codabarSymbol->D);

        # Save the image to disk in PNG format
        $builder->save($dataDir . "barcode.out.png");

        # Print message
        print "Barcode image generated successfully.".PHP_EOL;

    }


}