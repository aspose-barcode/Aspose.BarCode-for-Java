---
title: Save Barcode Image to Streams in PHP
type: docs
weight: 30
url: /java/save-barcode-image-to-streams-in-php/
---

## **Aspose.BarCode - Save Barcode Image to Streams**
To Save Barcode Image to Streams using **Aspose.Barcode Java for PHP**, simply invoke **SaveBarcodeImageToTtreams** module. Here you can see example code.

**PHPCode**

{{< highlight php >}}

 # Instantiate barcode object

$builder = new BarCodeBuilder();

\# Set symbology of the barcode

$symbology=new Symbology();

$builder->setSymbologyType($symbology->Code128);

\# Set code text

$builder->setCodeText("123456");

\# Initialize ByteArrayOutputStream object

$out_stream = new ByteArrayOutputStream();

\# Save barcode image

$barCodeImageFormat=new BarCodeImageFormat();

$builder->save($out_stream, $barCodeImageFormat->Jpeg);

\# Display Status.

print "Save Barcode Image to Streams, please check the output file.".PHP_EOL;

{{< /highlight >}}
## **Download Running Code**
Download **Save Barcode Image to Streams (Aspose.BarCode)** from any of the below mentioned social coding sites:

- [GitHub](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/blob/master/Plugins/Aspose_Barcode_Java_for_PHP/src/aspose/barcode/WorkingWithBarcodeImage/BarcodeImageUtilityFeatures/SaveBarcodeImageToTtreams.php)
- [CodePlex](https://asposebarcodejavaphp.codeplex.com/SourceControl/latest#src/aspose/barcode/WorkingWithBarcodeImage/BarcodeImageUtilityFeatures/SaveBarcodeImageToTtreams.php)
