---
title: Creating a Pdf417 BarCode in Ruby
type: docs
weight: 30
url: /java/creating-a-pdf417-barcode-in-ruby/
---

## **Aspose.BarCode - Creating a Pdf417 Barcode**
To Create a Pdf417 Barcode using **Aspose.Barcode Java for Ruby**, simply invoke **CreatingPdf417Barcode** module. Here you can see example code.

**Ruby Code**

{{< highlight ruby >}}

 data_dir = File.dirname(File.dirname(File.dirname(File.dirname(__FILE__)))) + '/data/'



\# Instantiate barcode object

builder = Rjb::import('com.aspose.barcode.BarCodeBuilder').new

builder.setSymbologyType(Rjb::import('com.aspose.barcode.Symbology').Pdf417)

builder.setCodeText("12345")

\# Save the image to your system and set its image format to Jpeg

builder.save(data_dir + "CreatingPdf417Barcode.jpg")

\# Display Status

puts "Created Pdf417 Barcode Successfully."

{{< /highlight >}}
## **Download Running Code**
Download **Creating a Pdf417 Barcode (Aspose.BarCode)** from any of the below mentioned social coding sites:

- [GitHub](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/blob/master/Plugins/Aspose_Barcode_Java_for_Ruby/lib/asposebarcodejava/2DBarcode/creatingpdf417barcode.rb)
