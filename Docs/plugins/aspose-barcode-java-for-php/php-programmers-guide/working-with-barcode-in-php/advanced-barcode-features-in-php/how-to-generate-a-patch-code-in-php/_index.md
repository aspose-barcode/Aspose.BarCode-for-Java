---
title: How to Generate a Patch Code in PHP
type: docs
weight: 20
url: /java/how-to-generate-a-patch-code-in-php/
---

## **Aspose.BarCode - How to Generate a Patch Code**
To Generate a Patch Code using **Aspose.Barcode Java for Ruby**, simply invoke **PatchCode** module. Here you can see example code.

**Ruby Code**

{{< highlight ruby >}}

 # Instantiate barcode object

$builder = new BarCodeBuilder();

\# Set Symbology type

$symbology=new Symbology();

$builder->setSymbologyType($symbology->PatchCode);

\# Set code text

$builder->setCodeText("Patch T");

\# Save the image to your system and set its image format to Jpeg

$builder->save($dataDir . "PatchCode.jpg");

\# Display Status

print "Generated PatchCode Successfully.".PHP_EOL;

{{< /highlight >}}
## **Download Running Code**
Download **How to Generate a Patch Code (Aspose.BarCode)** from any of the below mentioned social coding sites:

- [GitHub](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/blob/master/Plugins/Aspose_Barcode_Java_for_PHP/src/aspose/barcode/WorkingWithBarcode/AdvanceBarcodeFeatures/PatchCode.php)
- [CodePlex](https://asposebarcodejavaphp.codeplex.com/SourceControl/latest#src/aspose/barcode/WorkingWithBarcode/AdvanceBarcodeFeatures/PatchCode.php)
