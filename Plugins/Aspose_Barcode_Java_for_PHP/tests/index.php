<?php

require_once("../../java/Java.inc");

require_once __DIR__ . '/../vendor/autoload.php';

use Aspose\Barcode\WorkingWithBarcode\AdvanceBarcodeFeatures\AustraliaPostBarcode;
use Aspose\Barcode\WorkingWithBarcode\AdvanceBarcodeFeatures\ManageDimension;
use Aspose\Barcode\WorkingWithBarcode\AdvanceBarcodeFeatures\PatchCode;
use Aspose\Barcode\WorkingWithBarcode\AdvanceBarcodeFeatures\SetBarsHeight;
use Aspose\Barcode\WorkingWithBarcode\AdvanceBarcodeFeatures\StartStopSymbol;
use Aspose\Barcode\WorkingWithBarcode\AdvanceBarcodeFeatures\UseChecksumAndSupplementData;
use Aspose\Barcode\WorkingWithBarcode\AdvanceBarcodeFeatures\WideNarrowRatio;

use Aspose\Barcode\WorkingWithBarcode\UtilityFeatures\CodeText;
use Aspose\Barcode\WorkingWithBarcode\UtilityFeatures\ManageCaption;
use Aspose\Barcode\WorkingWithBarcode\UtilityFeatures\SpecifySymbologies;

use Aspose\Barcode\WorkingWith2DBarcodes\Basic2DBarcodeFeatures\Creating2DBarcode;
use Aspose\Barcode\WorkingWith2DBarcodes\Basic2DBarcodeFeatures\CreatingAztecBarcode;
use Aspose\Barcode\WorkingWith2DBarcodes\Basic2DBarcodeFeatures\CreatingDataMatrixBarcode;
use Aspose\Barcode\WorkingWith2DBarcodes\Basic2DBarcodeFeatures\CreatingPdf417Barcode;
use Aspose\Barcode\WorkingWith2DBarcodes\Basic2DBarcodeFeatures\CreatingQRBarcode;


use Aspose\Barcode\WorkingWith2DBarcodes\Utility2DBarcodeFeatures\GenerateMultipleMacroPdf417Barcodes;
use Aspose\Barcode\WorkingWith2DBarcodes\Utility2DBarcodeFeatures\HideCodeText;
use Aspose\Barcode\WorkingWith2DBarcodes\Utility2DBarcodeFeatures\SetAspectRatio;


use Aspose\Barcode\WorkingWithBarcodeImage\BarcodeImageBasicFeatures\GenerateBarcodeWithEmptyBars;
use Aspose\Barcode\WorkingWithBarcodeImage\BarcodeImageBasicFeatures\RotateImage;
use Aspose\Barcode\WorkingWithBarcodeImage\BarcodeImageBasicFeatures\SetBarcodeImageBorders;
use Aspose\Barcode\WorkingWithBarcodeImage\BarcodeImageBasicFeatures\SetBarcodeImageColor;
use Aspose\Barcode\WorkingWithBarcodeImage\BarcodeImageBasicFeatures\SetBarcodeImageMargins;

use Aspose\Barcode\WorkingWithBarcodeImage\BarcodeImageUtilityFeatures\CustomizeBarcodeImageResolution;
use Aspose\Barcode\WorkingWithBarcodeImage\BarcodeImageUtilityFeatures\GenerateBarcodeByCustomImageSize;
use Aspose\Barcode\WorkingWithBarcodeImage\BarcodeImageUtilityFeatures\SaveBarcodeImageToTtreams;
use Aspose\Barcode\WorkingWithBarcodeImage\BarcodeImageUtilityFeatures\SetBarcodeImageUnitSize;

use Aspose\Barcode\WorkingWithBarcodeRecognition\AdvancedBarcodeRecognitionFeatures\GetAllOneDBarcodes;
use Aspose\Barcode\WorkingWithBarcodeRecognition\AdvancedBarcodeRecognitionFeatures\GetBarcodeRecognitionQuality;
use Aspose\Barcode\WorkingWithBarcodeRecognition\AdvancedBarcodeRecognitionFeatures\GetBarcodeRegionInfo;
use Aspose\Barcode\WorkingWithBarcodeRecognition\AdvancedBarcodeRecognitionFeatures\MakingBarcodeRegions;
use Aspose\Barcode\WorkingWithBarcodeRecognition\AdvancedBarcodeRecognitionFeatures\ReadBarcodeFromSpecificRegion;
use Aspose\Barcode\WorkingWithBarcodeRecognition\AdvancedBarcodeRecognitionFeatures\SwitchBarcodeRecognitionModes;

use Aspose\Barcode\WorkingWithBarcodeRecognition\BasicBarcodeRecognitionFeatures\RecognizeBarcode;
use Aspose\Barcode\WorkingWithBarcodeRecognition\BasicBarcodeRecognitionFeatures\RecognizeMultipleSymbologies;
use Aspose\Barcode\WorkingWithBarcodeRecognition\BasicBarcodeRecognitionFeatures\RecognizeSpecificSymbology;


print "Running Aspose\\Barcode\\WorkingWithBarcode\\AdvanceBarcodeFeatures\\AustraliaPostBarcode::run()" . PHP_EOL;
AustraliaPostBarcode::run(__DIR__ . '/data/WorkingWithBarcode/AdvanceBarcodeFeatures/AustraliaPostBarcode/');

print "Running Aspose\\Barcode\\WorkingWithBarcode\\AdvanceBarcodeFeatures\\ManageDimension::run()" . PHP_EOL;
ManageDimension::run(__DIR__ . '/data/WorkingWithBarcode/AdvanceBarcodeFeatures/ManageDimension/');


print "Running Aspose\\Barcode\\WorkingWithBarcode\\AdvanceBarcodeFeatures\\PatchCode::run()" . PHP_EOL;
PatchCode::run(__DIR__ . '/data/WorkingWithBarcode/AdvanceBarcodeFeatures/PatchCode/');

print "Running Aspose\\Barcode\\WorkingWithBarcode\\AdvanceBarcodeFeatures\\SetBarsHeight::run()" . PHP_EOL;
SetBarsHeight::run(__DIR__ . '/data/WorkingWithBarcode/AdvanceBarcodeFeatures/SetBarsHeight/');

print "Running Aspose\\Barcode\\WorkingWithBarcode\\AdvanceBarcodeFeatures\\StartStopSymbol::run()" . PHP_EOL;
StartStopSymbol::run(__DIR__ . '/data/WorkingWithBarcode/AdvanceBarcodeFeatures/StartStopSymbol/');

print "Running Aspose\\Barcode\\WorkingWithBarcode\\AdvanceBarcodeFeatures\\UseChecksumAndSupplementData::run()" . PHP_EOL;
UseChecksumAndSupplementData::run(__DIR__ . '/data/WorkingWithBarcode/AdvanceBarcodeFeatures/UseChecksumAndSupplementData/');

print "Running Aspose\\Barcode\\WorkingWithBarcode\\AdvanceBarcodeFeatures\\WideNarrowRatio::run()" . PHP_EOL;
WideNarrowRatio::run(__DIR__ . '/data/WorkingWithBarcode/AdvanceBarcodeFeatures/WideNarrowRatio/');

print "Running Aspose\\Barcode\\WorkingWithBarcode\\UtilityFeatures\\CodeText::run()" . PHP_EOL;
CodeText::run(__DIR__ . '/data/WorkingWithBarcode/UtilityFeatures/CodeText/');

print "Running Aspose\\Barcode\\WorkingWithBarcode\\UtilityFeatures\\ManageCaption::run()" . PHP_EOL;
ManageCaption::run(__DIR__ . '/data/WorkingWithBarcode/UtilityFeatures/ManageCaption/');

print "Running Aspose\\Barcode\\WorkingWithBarcode\\UtilityFeatures\\SpecifySymbologies::run()" . PHP_EOL;
SpecifySymbologies::run(__DIR__ . '/data/WorkingWithBarcode/UtilityFeatures/SpecifySymbologies/');


print "Running Aspose\\Barcode\\WorkingWith2DBarcodes\\Basic2DBarcodeFeatures\\Creating2DBarcode::run()" . PHP_EOL;
Creating2DBarcode::run(__DIR__ . '/data/WorkingWith2DBarcodes/Basic2DBarcodeFeatures/Creating2DBarcode/');

print "Running Aspose\\Barcode\\WorkingWith2DBarcodes\\Basic2DBarcodeFeatures\\CreatingAztecBarcode::run()" . PHP_EOL;
CreatingAztecBarcode::run(__DIR__ . '/data/WorkingWith2DBarcodes/Basic2DBarcodeFeatures/CreatingAztecBarcode/');

print "Running Aspose\\Barcode\\WorkingWith2DBarcodes\\Basic2DBarcodeFeatures\\CreatingDataMatrixBarcode::run()" . PHP_EOL;
CreatingDataMatrixBarcode::run(__DIR__ . '/data/WorkingWith2DBarcodes/Basic2DBarcodeFeatures/CreatingDataMatrixBarcode/');

print "Running Aspose\\Barcode\\WorkingWith2DBarcodes\\Basic2DBarcodeFeatures\\CreatingPdf417Barcode::run()" . PHP_EOL;
CreatingPdf417Barcode::run(__DIR__ . '/data/WorkingWith2DBarcodes/Basic2DBarcodeFeatures/CreatingPdf417Barcode/');

print "Running Aspose\\Barcode\\WorkingWith2DBarcodes\\Basic2DBarcodeFeatures\\CreatingQRBarcode::run()" . PHP_EOL;
CreatingQRBarcode::run(__DIR__ . '/data/WorkingWith2DBarcodes/Basic2DBarcodeFeatures/CreatingQRBarcode/');


print "Running Aspose\\Barcode\\WorkingWith2DBarcodes\\Utility2DBarcodeFeatures\\GenerateMultipleMacroPdf417Barcodes::run()" . PHP_EOL;
GenerateMultipleMacroPdf417Barcodes::run(__DIR__ . '/data/WorkingWith2DBarcodes/Utility2DBarcodeFeatures/GenerateMultipleMacroPdf417Barcodes/');

print "Running Aspose\\Barcode\\WorkingWith2DBarcodes\\Utility2DBarcodeFeatures\\HideCodeText::run()" . PHP_EOL;
HideCodeText::run(__DIR__ . '/data/WorkingWith2DBarcodes/Utility2DBarcodeFeatures/HideCodeText/');

print "Running Aspose\\Barcode\\WorkingWith2DBarcodes\\Utility2DBarcodeFeatures\\SetAspectRatio::run()" . PHP_EOL;
SetAspectRatio::run(__DIR__ . '/data/WorkingWith2DBarcodes/Utility2DBarcodeFeatures/SetAspectRatio/');

print "Running Aspose\\Barcode\\WorkingWithBarcodeImage\\BarcodeImageBasicFeatures\\GenerateBarcodeWithEmptyBars::run()" . PHP_EOL;
GenerateBarcodeWithEmptyBars::run(__DIR__ . '/data/WorkingWithBarcodeImage/BarcodeImageBasicFeatures/GenerateBarcodeWithEmptyBars/');

print "Running Aspose\\Barcode\\WorkingWithBarcodeImage\\BarcodeImageBasicFeatures\\RotateImage::run()" . PHP_EOL;
RotateImage::run(__DIR__ . '/data/WorkingWithBarcodeImage/BarcodeImageBasicFeatures/RotateImage/');

print "Running Aspose\\Barcode\\WorkingWithBarcodeImage\\BarcodeImageBasicFeatures\\SetBarcodeImageBorders::run()" . PHP_EOL;
SetBarcodeImageBorders::run(__DIR__ . '/data/WorkingWithBarcodeImage/BarcodeImageBasicFeatures/SetBarcodeImageBorders/');

print "Running Aspose\\Barcode\\WorkingWithBarcodeImage\\BarcodeImageBasicFeatures\\SetBarcodeImageColor::run()" . PHP_EOL;
SetBarcodeImageColor::run(__DIR__ . '/data/WorkingWithBarcodeImage/BarcodeImageBasicFeatures/SetBarcodeImageColor/');

print "Running Aspose\\Barcode\\WorkingWithBarcodeImage\\BarcodeImageBasicFeatures\\SetBarcodeImageMargins::run()" . PHP_EOL;
SetBarcodeImageMargins::run(__DIR__ . '/data/WorkingWithBarcodeImage/BarcodeImageBasicFeatures/SetBarcodeImageMargins/');

print "Running Aspose\\Barcode\\WorkingWithBarcodeImage\\BarcodeImageUtilityFeatures\\CustomizeBarcodeImageResolution::run()" . PHP_EOL;
CustomizeBarcodeImageResolution::run(__DIR__ . '/data/WorkingWithBarcodeImage/BarcodeImageUtilityFeatures/CustomizeBarcodeImageResolution/');

print "Running Aspose\\Barcode\\WorkingWithBarcodeImage\\BarcodeImageUtilityFeatures\\GenerateBarcodeByCustomImageSize::run()" . PHP_EOL;
GenerateBarcodeByCustomImageSize::run(__DIR__ . '/data/WorkingWithBarcodeImage/BarcodeImageUtilityFeatures/GenerateBarcodeByCustomImageSize/');

print "Running Aspose\\Barcode\\WorkingWithBarcodeImage\\BarcodeImageUtilityFeatures\\SaveBarcodeImageToTtreams::run()" . PHP_EOL;
SaveBarcodeImageToTtreams::run(__DIR__ . '/data/WorkingWithBarcodeImage/BarcodeImageUtilityFeatures/SaveBarcodeImageToTtreams/');

print "Running Aspose\\Barcode\\WorkingWithBarcodeImage\\BarcodeImageUtilityFeatures\\SetBarcodeImageUnitSize::run()" . PHP_EOL;
SetBarcodeImageUnitSize::run(__DIR__ . '/data/WorkingWithBarcodeImage/BarcodeImageUtilityFeatures/SetBarcodeImageUnitSize/');

print "Running Aspose\\Barcode\\WorkingWithBarcodeRecognition\\AdvancedBarcodeRecognitionFeatures\\GetAllOneDBarcodes::run()" . PHP_EOL;
GetAllOneDBarcodes::run(__DIR__ . '/data/WorkingWithBarcodeRecognition/AdvancedBarcodeRecognitionFeatures/GetAllOneDBarcodes/');

print "Running Aspose\\Barcode\\WorkingWithBarcodeRecognition\\AdvancedBarcodeRecognitionFeatures\\GetBarcodeRecognitionQuality::run()" . PHP_EOL;
GetBarcodeRecognitionQuality::run(__DIR__ . '/data/WorkingWithBarcodeRecognition/AdvancedBarcodeRecognitionFeatures/GetBarcodeRecognitionQuality/');

print "Running Aspose\\Barcode\\WorkingWithBarcodeRecognition\\AdvancedBarcodeRecognitionFeatures\\GetBarcodeRegionInfo::run()" . PHP_EOL;
GetBarcodeRegionInfo::run(__DIR__ . '/data/WorkingWithBarcodeRecognition/AdvancedBarcodeRecognitionFeatures/GetBarcodeRegionInfo/');

print "Running Aspose\\Barcode\\WorkingWithBarcodeRecognition\\AdvancedBarcodeRecognitionFeatures\\MakingBarcodeRegions::run()" . PHP_EOL;
MakingBarcodeRegions::run(__DIR__ . '/data/WorkingWithBarcodeRecognition/AdvancedBarcodeRecognitionFeatures/MakingBarcodeRegions/');

print "Running Aspose\\Barcode\\WorkingWithBarcodeRecognition\\AdvancedBarcodeRecognitionFeatures\\ReadBarcodeFromSpecificRegion::run()" . PHP_EOL;
ReadBarcodeFromSpecificRegion::run(__DIR__ . '/data/WorkingWithBarcodeRecognition/AdvancedBarcodeRecognitionFeatures/ReadBarcodeFromSpecificRegion/');

print "Running Aspose\\Barcode\\WorkingWithBarcodeRecognition\\AdvancedBarcodeRecognitionFeatures\\SwitchBarcodeRecognitionModes::run()" . PHP_EOL;
SwitchBarcodeRecognitionModes::run(__DIR__ . '/data/WorkingWithBarcodeRecognition/AdvancedBarcodeRecognitionFeatures/SwitchBarcodeRecognitionModes/');

print "Running Aspose\\Barcode\\WorkingWithBarcodeRecognition\\BasicBarcodeRecognitionFeatures\\RecognizeBarcode::run()" . PHP_EOL;
RecognizeBarcode::run(__DIR__ . '/data/WorkingWithBarcodeRecognition/BasicBarcodeRecognitionFeatures/RecognizeBarcode/');

print "Running Aspose\\Barcode\\WorkingWithBarcodeRecognition\\BasicBarcodeRecognitionFeatures\\RecognizeMultipleSymbologies::run()" . PHP_EOL;
RecognizeMultipleSymbologies::run(__DIR__ . '/data/WorkingWithBarcodeRecognition/BasicBarcodeRecognitionFeatures/RecognizeMultipleSymbologies/');

print "Running Aspose\\Barcode\\WorkingWithBarcodeRecognition\\BasicBarcodeRecognitionFeatures\\RecognizeSpecificSymbology::run()" . PHP_EOL;
RecognizeSpecificSymbology::run(__DIR__ . '/data/WorkingWithBarcodeRecognition/BasicBarcodeRecognitionFeatures/RecognizeSpecificSymbology/');
