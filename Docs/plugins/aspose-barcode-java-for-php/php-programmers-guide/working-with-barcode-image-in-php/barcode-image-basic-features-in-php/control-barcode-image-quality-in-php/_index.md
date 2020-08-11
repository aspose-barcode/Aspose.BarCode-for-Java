---
title: Control Barcode Image Quality in PHP
type: docs
weight: 20
url: /java/control-barcode-image-quality-in-php/
---

## **Aspose.BarCode - Control Barcode Image Quality**
To Control Barcode Image Quality using **Aspose.Barcode Java for PHP**, simply invoke **SetBarcodeImageQuality** module. Here you can see example code.

**PHPCode**

{{< highlight php >}}

 # Instantiate barcode object

$bb = new BarCodeBuilder();

\# Set the code text of the barcode

$bb->setCodeText("12345678");

\# Set the graphics drawing hint to be Anti Alias

$imageQualityMode=new ImageQualityMode();

$bb->setImageQuality($imageQualityMode->AntiAlias);

\# Set the symbology type to code39

$symbology=new Symbology();

$bb->setSymbologyType($symbology->Code39Extended);

$bb->save($dataDir . "SetBarcodeImageQuality.jpg");

\# Display Status.

print "Set Barcode Image Quality, please check the output file.".PHP_EOL;

{{< /highlight >}}
## **Download Running Code**
Download **Control Barcode Image Quality (Aspose.BarCode)** from any of the below mentioned social coding sites:

- [GitHub](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/blob/master/Plugins/Aspose_Barcode_Java_for_PHP/src/aspose/barcode/WorkingWithBarcodeImage/BarcodeImageBasicFeatures/SetBarcodeImageQuality.php)
- [CodePlex](https://asposebarcodejavaphp.codeplex.com/SourceControl/latest#src/aspose/barcode/WorkingWithBarcodeImage/BarcodeImageBasicFeatures/SetBarcodeImageQuality.php)
