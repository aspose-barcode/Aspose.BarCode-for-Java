---
title: Specify Symbologies for Barcodes in PHP
type: docs
weight: 40
url: /java/specify-symbologies-for-barcodes-in-php/
---

## **Aspose.BarCode - Specify Symbologies for Barcodes**
To Specify Symbologies for Barcodes using **Aspose.Barcode Java for PHP**, simply invoke **SpecifySymbologies** module. Here you can see example code.

**PHPCode**

{{< highlight php >}}

 $builder = new BarCodeBuilder();

\# ============ Code39Standard =====================

\# set symbology type

$symbology=new Symbology();

$builder->setSymbologyType($symbology->Code39Standard);

\# Save image to disk

$builder->save($dataDir . "code39Standard.out.png");

\# ================== QR ===========================

\# set symbology type

$builder->setSymbologyType($symbology->QR);

\# Save image to disk

$builder->save($dataDir . "QR.out.png");

\# =============== Code128 =========================

\# set symbology type

$builder->setSymbologyType($symbology->Code128);

\# Save image to disk

$builder->save($dataDir . "code128.out.png");

\# Print message

print "Barcode image(s) generated successfully.".PHP_EOL;

{{< /highlight >}}
## **Download Running Code**
Download **Specify Symbologies for Barcodes (Aspose.BarCode)** from any of the below mentioned social coding sites:

- [GitHub](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/blob/master/Plugins/Aspose_Barcode_Java_for_PHP/src/aspose/barcode/WorkingWithBarcode/UtilityFeatures/SpecifySymbologies.php)
- [CodePlex](https://asposebarcodejavaphp.codeplex.com/SourceControl/latest#src/aspose/barcode/WorkingWithBarcode/UtilityFeatures/SpecifySymbologies.php)
