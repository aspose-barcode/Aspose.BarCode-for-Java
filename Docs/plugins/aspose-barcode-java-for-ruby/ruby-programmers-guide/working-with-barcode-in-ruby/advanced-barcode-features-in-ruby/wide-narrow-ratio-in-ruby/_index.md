---
title: Wide Narrow Ratio in Ruby
type: docs
weight: 70
url: /java/wide-narrow-ratio-in-ruby/
---

## **Aspose.BarCode - Wide Narrow Ratio**
To Wide Narrow Ratio using **Aspose.Barcode Java for Ruby**, simply invoke **WideNarrowRatio** module. Here you can see example code.

**Ruby Code**

{{< highlight ruby >}}

 data_dir = File.dirname(File.dirname(File.dirname(File.dirname(__FILE__)))) + '/data/'



\# Instantiate barcode object

bb = Rjb::import('com.aspose.barcode.BarCodeBuilder').new

\# Set the code text of the barcode

bb.setCodeText("12345678")

\# Set the symbology type to code39

bb.setSymbologyType(Rjb::import('com.aspose.barcode.Symbology').Code39Extended)

\# Set the wide to narrow ratio for the barcode

bb.setWideNarrowRatio(3.0)

\# Save the image to file

bb.save(data_dir + "barcode_ratio_3.jpg")

\# Set the wide to narrow ratio for the barcode

bb.setWideNarrowRatio(5.0)

\# Save the image to file

bb.save(data_dir + "barcode_ratio_5.jpg")

\# Set the wide to narrow ratio for the barcode

bb.setWideNarrowRatio(7.0)

\# Save the image to file

bb.save(data_dir + "barcode_ratio_7.jpg")

\# Set the wide to narrow ratio for the barcode

bb.setWideNarrowRatio(9.0)

\# Save the image to file

bb.save(data_dir + "barcode_ratio_9.jpg")

\# Display Status.

puts "BarCodes with different wide narrow ratios have been created successfully."

{{< /highlight >}}
## **Download Running Code**
Download **Wide Narrow Ratio (Aspose.BarCode)** from any of the below mentioned social coding sites:

- [GitHub](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/blob/master/Plugins/Aspose_Barcode_Java_for_Ruby/lib/asposebarcodejava/Barcode/widenarrowratio.rb)
