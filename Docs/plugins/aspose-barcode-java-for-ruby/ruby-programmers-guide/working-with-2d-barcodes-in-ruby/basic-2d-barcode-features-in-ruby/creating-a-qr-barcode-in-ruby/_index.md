---
title: Creating a QR BarCode in Ruby
type: docs
weight: 40
url: /java/creating-a-qr-barcode-in-ruby/
---

## **Aspose.BarCode - Creating a QR Barcode**
To Create a QR Barcode using **Aspose.Barcode Java for Ruby**, simply invoke **CreatingQRBarcode** module. Here you can see example code.

**Ruby Code**

{{< highlight ruby >}}

 data_dir = File.dirname(File.dirname(File.dirname(File.dirname(__FILE__)))) + '/data/'



\# Instantiate barcode object

builder = Rjb::import('com.aspose.barcode.BarCodeBuilder').new

builder.setSymbologyType(Rjb::import('com.aspose.barcode.Symbology').QR)

builder.setCodeText("1234567890")

\# Hide code text

builder.setCodeLocation(Rjb::import('com.aspose.barcode.CodeLocation').None)

builder.setRotationAngleF(90)

\# Save the image to your system and set its image format to Jpeg

builder.save(data_dir + "CreatingQRBarcode.jpg")

\# Display Status

puts "Created QR Barcode Successfully."

{{< /highlight >}}
## **Download Running Code**
Download **Creating a QR Barcode (Aspose.BarCode)** from any of the below mentioned social coding sites:

- [GitHub](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/blob/master/Plugins/Aspose_Barcode_Java_for_Ruby/lib/asposebarcodejava/2DBarcode/creatingqrbarcode.rb)
