---
title: Printing a BarCode Image in Ruby
type: docs
weight: 30
url: /java/printing-a-barcode-image-in-ruby/
---

## **Aspose.BarCode - Printing a BarCode Image**
To Print a BarCode Image using **Aspose.Barcode Java for Ruby**, simply invoke **PrintingBarcodeImage** module. Here you can see example code.

**Ruby Code**

{{< highlight ruby >}}

 data_dir = File.dirname(File.dirname(File.dirname(File.dirname(__FILE__)))) + '/data/'



\# Instantiate barcode object

symbology = Rjb::import('com.aspose.barcode.Symbology')

builder = Rjb::import('com.aspose.barcode.BarCodeBuilder').new("12345678", symbology.Code128)

\# Set printer name

builder.setPrinterName("ML-1640 Series")

\# Start a print job

builder.print()

{{< /highlight >}}
## **Download Running Code**
Download **Printing a BarCode Image (Aspose.BarCode)** from any of the below mentioned social coding sites:

- [GitHub](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/blob/master/Plugins/Aspose_Barcode_Java_for_Ruby/lib/asposebarcodejava/BarcodeImage/printingbarcodeimage.rb)
