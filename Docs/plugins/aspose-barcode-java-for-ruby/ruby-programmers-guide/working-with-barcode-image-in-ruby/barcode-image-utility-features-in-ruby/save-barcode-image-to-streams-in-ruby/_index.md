---
title: Save BarCode Image to Streams in Ruby
type: docs
weight: 40
url: /java/save-barcode-image-to-streams-in-ruby/
---

## **Aspose.BarCode - Save Barcode Image to Streams**
To Save Barcode Image to Streams using **Aspose.Barcode Java for Ruby**, simply invoke **SaveBarcodeImageToTtreams** module. Here you can see example code.

**Ruby Code**

{{< highlight ruby >}}

 data_dir = File.dirname(File.dirname(File.dirname(File.dirname(__FILE__)))) + '/data/'



\# Instantiate barcode object

builder = Rjb::import('com.aspose.barcode.BarCodeBuilder').new

\# Set symbology of the barcode

builder.setSymbologyType(Rjb::import('com.aspose.barcode.Symbology').Code128)

\# Set code text

builder.setCodeText("123456")

\# Initialize ByteArrayOutputStream object

out_stream = Rjb::import('java.io.ByteArrayOutputStream').new

\# Save barcode image

builder.save(out_stream, Rjb::import('com.aspose.barcode.BarCodeImageFormat').Jpeg)

\# Display Status.

puts "Save Barcode Image to Streams, please check the output file."

{{< /highlight >}}
## **Download Running Code**
Download **Save Barcode Image to Streams (Aspose.BarCode)** from any of the below mentioned social coding sites:

- [GitHub](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/blob/master/Plugins/Aspose_Barcode_Java_for_Ruby/lib/asposebarcodejava/BarcodeImage/savebarcodeimagetostreams.rb)
