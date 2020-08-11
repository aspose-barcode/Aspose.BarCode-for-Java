---
title: Generate AustraliaPost Barcode with Different Format Control Code Options in PHP
type: docs
weight: 10
url: /java/generate-australiapost-barcode-with-different-format-control-code-options-in-php/
---

## **Aspose.BarCode - Generate AustraliaPost Barcode**
To Generate AustraliaPost Barcode using **Aspose.Barcode Java for PHP**, simply invoke **AustraliaPostBarcode** module. Here you can see example code.

**PHPCode**

{{< highlight php >}}

 # Instantiate barcode object

$symbology = new Symbology();

$builder = new BarCodeBuilder("1234567890", $symbology->AustraliaPost);

\# Set format control code to standard

$australiaPostFormatControlCode=new AustraliaPostFormatControlCode();

$builder->setAustraliaPostFormatControlCode($australiaPostFormatControlCode->Standard);

\# Save the image to your system and set its image format to Jpeg

$builder->save($dataDir . "AusraliaPost-Barcode.jpg");

\# Display Status

print "Generated Austrailia Post Barcode Successfully.".PHP_EOL;

{{< /highlight >}}
## **Download Running Code**
Download **Generate AustraliaPost Barcode (Aspose.BarCode)** from any of the below mentioned social coding sites:

- [GitHub](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/blob/master/Plugins/Aspose_Barcode_Java_for_PHP/src/aspose/barcode/WorkingWithBarcode/AdvanceBarcodeFeatures/AustraliaPostBarcode.php)
- [CodePlex](https://asposebarcodejavaphp.codeplex.com/SourceControl/latest#src/aspose/barcode/WorkingWithBarcode/AdvanceBarcodeFeatures/AustraliaPostBarcode.php)
