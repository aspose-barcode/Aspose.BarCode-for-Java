---
title: Creating Two Dimensional (2D) BarCodes in Ruby
type: docs
weight: 50
url: /java/creating-two-dimensional-2d-barcodes-in-ruby/
---

## **Aspose.BarCode - Creating Two Dimensional (2D) Barcodes**
To Create Two Dimensional (2D) Barcodes using **Aspose.Barcode Java for Ruby**, simply invoke **Creating2DBarcode** module. Here you can see example code.

**Ruby Code**

{{< highlight ruby >}}

 data_dir = File.dirname(File.dirname(File.dirname(File.dirname(__FILE__)))) + '/data/'



\# Instantiate barcode object

builder = Rjb::import('com.aspose.barcode.BarCodeBuilder').new

builder.setSymbologyType(Rjb::import('com.aspose.barcode.Symbology').Pdf417)

\# Width of each module

builder.setxDimension(0.6)

\# Height of each module

builder.setyDimension(1.2)

builder.setCodeText("this is some test code text. \n Second line \n third line.")

\# Save the image to your system and set its image format to Jpeg

builder.save(data_dir + "Creating2DBarcode.jpg")

\# Display Status

puts "Created 2D Barcode Successfully."

{{< /highlight >}}
## **Download Running Code**
Download **Creating Two Dimensional (2D) Barcodes (Aspose.BarCode)** from any of the below mentioned social coding sites:

- [GitHub](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/blob/master/Plugins/Aspose_Barcode_Java_for_Ruby/lib/asposebarcodejava/2DBarcode/creating2dbarcode.rb)
