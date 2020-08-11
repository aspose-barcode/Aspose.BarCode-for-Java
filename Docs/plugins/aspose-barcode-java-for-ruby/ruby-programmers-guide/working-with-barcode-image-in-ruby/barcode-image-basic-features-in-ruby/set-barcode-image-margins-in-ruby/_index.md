---
title: Set BarCode Image Margins in Ruby
type: docs
weight: 50
url: /java/set-barcode-image-margins-in-ruby/
---

## **Aspose.BarCode - Set Barcode Image Margins**
To Set Barcode Image Margins using **Aspose.Barcode Java for Ruby**, simply invoke **SetBarcodeImageMargins** module. Here you can see example code.

**Ruby Code**

{{< highlight ruby >}}

 data_dir = File.dirname(File.dirname(File.dirname(File.dirname(__FILE__)))) + '/data/'



\# Instantiate barcode object

symbology = Rjb::import('com.aspose.barcode.Symbology')

bb = Rjb::import('com.aspose.barcode.BarCodeBuilder').new("12345678", symbology.Code128)

\# sets the top margin

bb.getMargins().setTop(4)

\# sets the bottom margin

bb.getMargins().setBottom(5)

\# sets the left margin

bb.getMargins().setLeft(2)

\# sets the right margin

bb.getMargins().setRight(3)

bb.save(data_dir + "SetBarcodeImageMargins.jpg")

\# Display Status.

puts "Set Barcode Image Margins, please check the output file."

{{< /highlight >}}
## **Download Running Code**
Download **Set Barcode Image Margins (Aspose.BarCode)** from any of the below mentioned social coding sites:

- [GitHub](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/blob/master/Plugins/Aspose_Barcode_Java_for_Ruby/lib/asposebarcodejava/BarcodeImage/setbarcodeimagemargins.rb)
