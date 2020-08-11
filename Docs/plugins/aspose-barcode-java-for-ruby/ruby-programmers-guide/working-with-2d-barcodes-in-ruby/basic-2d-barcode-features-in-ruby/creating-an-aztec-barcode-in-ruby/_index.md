---
title: Creating an Aztec BarCode in Ruby
type: docs
weight: 20
url: /java/creating-an-aztec-barcode-in-ruby/
---

## **Aspose.BarCode - Creating an Aztec Barcode**
To Create an Aztec Barcode using **Aspose.Barcode Java for Ruby**, simply invoke **CreatingAztecBarcode** module. Here you can see example code.

**Ruby Code**

{{< highlight ruby >}}

 data_dir = File.dirname(File.dirname(File.dirname(File.dirname(__FILE__)))) + '/data/'



\# Instantiate barcode object

builder = Rjb::import('com.aspose.barcode.BarCodeBuilder').new

builder.setSymbologyType(Rjb::import('com.aspose.barcode.Symbology').Aztec)

builder.setCodeText("1234567890")

\# Save the image to your system and set its image format to Jpeg

builder.save(data_dir + "CreatingAztecBarcode.jpg")

\# Display Status

puts "Created Aztec Barcode Successfully."

{{< /highlight >}}
## **Download Running Code**
Download **Creating an Aztec Barcode (Aspose.BarCode)** from any of the below mentioned social coding sites:

- [GitHub](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/blob/master/Plugins/Aspose_Barcode_Java_for_Ruby/lib/asposebarcodejava/2DBarcode/creatingaztecbarcode.rb)
