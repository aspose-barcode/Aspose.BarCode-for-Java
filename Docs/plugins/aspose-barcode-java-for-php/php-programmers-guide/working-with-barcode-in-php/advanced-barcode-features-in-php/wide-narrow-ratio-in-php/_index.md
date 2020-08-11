---
title: Wide Narrow Ratio in PHP
type: docs
weight: 70
url: /java/wide-narrow-ratio-in-php/
---

## **Aspose.BarCode - Wide Narrow Ratio**
To Wide Narrow Ratio using **Aspose.Barcode Java for PHP**, simply invoke **WideNarrowRatio** module. Here you can see example code.

**PHPCode**

{{< highlight php >}}

 # Instantiate barcode object

$bb = new BarCodeBuilder();

\# Set the code text of the barcode

$bb->setCodeText("12345678");

\# Set the symbology type to code39

$symbology=new Symbology();

$bb->setSymbologyType($symbology->Code39Extended);

\# Set the wide to narrow ratio for the barcode

$bb->setWideNarrowRatio(3.0);

\# Save the image to file

$bb->save($dataDir . "barcode_ratio_3.jpg");

\# Set the wide to narrow ratio for the barcode

$bb->setWideNarrowRatio(5.0);

\# Save the image to file

$bb->save($dataDir . "barcode_ratio_5.jpg");

\# Set the wide to narrow ratio for the barcode

$bb->setWideNarrowRatio(7.0);

\# Save the image to file

$bb->save($dataDir . "barcode_ratio_7.jpg");

\# Set the wide to narrow ratio for the barcode

$bb->setWideNarrowRatio(9.0);

\# Save the image to file

$bb->save($dataDir . "barcode_ratio_9.jpg");

\# Display Status.

print "BarCodes with different wide narrow ratios have been created successfully.".PHP_EOL;

{{< /highlight >}}
## **Download Running Code**
Download **Wide Narrow Ratio (Aspose.BarCode)** from any of the below mentioned social coding sites:

- [GitHub](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/blob/master/Plugins/Aspose_Barcode_Java_for_PHP/src/aspose/barcode/WorkingWithBarcode/AdvanceBarcodeFeatures/WideNarrowRatio.php)
- [CodePlex](https://asposebarcodejavaphp.codeplex.com/SourceControl/latest#src/aspose/barcode/WorkingWithBarcode/AdvanceBarcodeFeatures/WideNarrowRatio.php)
