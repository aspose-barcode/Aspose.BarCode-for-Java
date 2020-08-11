---
title: Hiding Code Text that is too Long to Display in PHP
type: docs
weight: 20
url: /java/hiding-code-text-that-is-too-long-to-display-in-php/
---

## **Aspose.BarCode - Hiding Code Text that is too Long to Display**
To Hide Code Text that is too Long to Display using **Aspose.Barcode Java for PHP**, simply invoke **HideCodeText** module. Here you can see example code.

**PHPCode**

{{< highlight php >}}

 # Instantiate barcode object

$builder = new BarCodeBuilder();

$symbology=new Symbology();

$builder->setSymbologyType($symbology->DataMatrix);

$builder->setCodeText("The quick brown fox jumps over the lazy dog\n The quick brown fox jumps over the lazy dog\n");

$codeLocation=new CodeLocation();

$builder->setCodeLocation($codeLocation->None);

$font = new Font();

$builder->setCodeTextFont(new Font("Serif", $font->BOLD . $font->ITALIC, 20));

\# Save the image

$builder->save($dataDir . "HideCodeText.jpg");

\# Display Status

print "Hide Code Text Successfully.".PHP_EOL;

{{< /highlight >}}
## **Download Running Code**
Download **Hiding Code Text that is too Long to Display (Aspose.BarCode)** from any of the below mentioned social coding sites:

- [GitHub](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/blob/master/Plugins/Aspose_Barcode_Java_for_PHP/src/aspose/barcode/WorkingWith2DBarcodes/Utility2DBarcodeFeatures/HideCodeText.php)
- [CodePlex](https://asposebarcodejavaphp.codeplex.com/SourceControl/latest#src/aspose/barcode/WorkingWith2DBarcodes/Utility2DBarcodeFeatures/HideCodeText.php)
