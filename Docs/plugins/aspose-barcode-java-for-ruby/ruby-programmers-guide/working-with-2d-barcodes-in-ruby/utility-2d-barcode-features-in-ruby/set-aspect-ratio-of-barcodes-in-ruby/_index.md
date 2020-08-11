---
title: Set Aspect Ratio of BarCodes in Ruby
type: docs
weight: 30
url: /java/set-aspect-ratio-of-barcodes-in-ruby/
---

## **Aspose.BarCode - Set Aspect Ratio of Barcodes**
To Set Aspect Ratio of Barcodes using **Aspose.Barcode Java for Ruby**, simply invoke **SetAspectRatio** module. Here you can see example code.

**Ruby Code**

{{< highlight ruby >}}

 data_dir = File.dirname(File.dirname(File.dirname(File.dirname(__FILE__)))) + '/data/'



\# Instantiate barcode object

builder = Rjb::import('com.aspose.barcode.BarCodeBuilder').new

builder.setSymbologyType(Rjb::import('com.aspose.barcode.Symbology').Pdf417)

builder.setCodeText("1234567890")

\# Set Aspect Ratio to 3:2 or 1.5

builder.setAspectRatio(1.5)

\# Save the image

builder.save(data_dir + "SetAspectRatio.jpg")

\# Display Status

puts "Set Aspect Ratio Successfully."

{{< /highlight >}}
## **Download Running Code**
Download **Set Aspect Ratio of Barcodes (Aspose.BarCode)** from any of the below mentioned social coding sites:

- [GitHub](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/blob/master/Plugins/Aspose_Barcode_Java_for_Ruby/lib/asposebarcodejava/2DBarcode/setaspectratio.rb)
