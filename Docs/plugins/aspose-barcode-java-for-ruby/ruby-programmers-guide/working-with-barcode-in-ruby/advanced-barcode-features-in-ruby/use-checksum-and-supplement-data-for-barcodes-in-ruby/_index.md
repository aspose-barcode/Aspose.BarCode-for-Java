---
title: Use Checksum and Supplement Data for BarCodes in Ruby
type: docs
weight: 60
url: /java/use-checksum-and-supplement-data-for-barcodes-in-ruby/
---

## **Aspose.BarCode - Use Checksum and Supplement Data for Barcodes**
To Use Checksum and Supplement Data for Barcodes using **Aspose.Barcode Java for Ruby**, simply invoke **UseChecksumAndSupplementData** module. Here you can see example code.

**Ruby Code**

{{< highlight ruby >}}

 data_dir = File.dirname(File.dirname(File.dirname(File.dirname(__FILE__)))) + '/data/'



\# Instantiate barcode object

bb = Rjb::import('com.aspose.barcode.BarCodeBuilder').new

\# Set the Code text for the barcode

bb.setCodeText("1234567")

\# Set the symbology type to Code39

bb.setSymbologyType(Rjb::import('com.aspose.barcode.Symbology').Code39Standard)

\# Make the checksum to be visible on the barcode

bb.setEnableChecksum(Rjb::import('com.aspose.barcode.EnableChecksum').Default)

\# Save the image to disk in JPG format

bb.save(data_dir + "barcode.jpg")

\# Print message

puts "Barcode image generated successfully."

{{< /highlight >}}
## **Download Running Code**
Download **Use Checksum and Supplement Data for Barcodes (Aspose.BarCode)** from any of the below mentioned social coding sites:

- [GitHub](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/blob/master/Plugins/Aspose_Barcode_Java_for_Ruby/lib/asposebarcodejava/Barcode/usechecksumandsupplementdata.rb)
