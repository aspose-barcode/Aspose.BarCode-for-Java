---
title: Specify Symbologies for BarCodes in Ruby
type: docs
weight: 40
url: /java/specify-symbologies-for-barcodes-in-ruby/
---

## **Aspose.BarCode - Specify Symbologies for Barcodes**
To Specify Symbologies for Barcodes using **Aspose.Barcode Java for Ruby**, simply invoke **SpecifySymbologies** module. Here you can see example code.

**Ruby Code**

{{< highlight ruby >}}

 data_dir = File.dirname(File.dirname(File.dirname(File.dirname(__FILE__)))) + '/data/'



\# Instantiate barcode object

builder = Rjb::import('com.aspose.barcode.BarCodeBuilder').new

\# ============ Code39Standard ===================== 

\# set symbology type

builder.setSymbologyType(Rjb::import('com.aspose.barcode.Symbology').Code39Standard)

\# Save image to disk

builder.save(data_dir + "code39Standard.out.png")

\# ================== QR =========================== 

\# set symbology type

builder.setSymbologyType(Rjb::import('com.aspose.barcode.Symbology').QR)

\# Save image to disk

builder.save(data_dir + "QR.out.png")

\# =============== Code128 ========================= 

\# set symbology type

builder.setSymbologyType(Rjb::import('com.aspose.barcode.Symbology').Code128)

\# Save image to disk

builder.save(data_dir + "code128.out.png")

\# Print message

puts "Barcode image(s) generated successfully."

{{< /highlight >}}
## **Download Running Code**
Download **Specify Symbologies for Barcodes (Aspose.BarCode)** from any of the below mentioned social coding sites:

- [GitHub](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/blob/master/Plugins/Aspose_Barcode_Java_for_Ruby/lib/asposebarcodejava/Barcode/specifysymbologies.rb)
