---
title: Generate Multiple MacroPdf417 BarCodes for Large or Multiple Code Text Values in Ruby
type: docs
weight: 10
url: /java/generate-multiple-macropdf417-barcodes-for-large-or-multiple-code-text-values-in-ruby/
---

## **Aspose.BarCode - Generate Multiple MacroPdf417 Barcodes for Large or Multiple Code Text Values**
To Generate Multiple MacroPdf417 Barcodes for Large or Multiple Code Text Values using **Aspose.Barcode Java for Ruby**, simply invoke **GenerateMultipleMacroPdf417Barcodes** module. Here you can see example code.

**Ruby Code**

{{< highlight ruby >}}

 data_dir = File.dirname(File.dirname(File.dirname(File.dirname(__FILE__)))) + '/data/'



\# Instantiate barcode object

builder = Rjb::import('com.aspose.barcode.BarCodeBuilder').new

builder.setSymbologyType(Rjb::import('com.aspose.barcode.Symbology').MacroPdf417)

\# Create array for storing multiple barcodes

i = 1

size = 4

list_code_text = ["code-1", "code-2", "code-3", "code-last"]

str_file_id = 1

\# Check the listbox for getting codetext and generating the barcodes

for i in 1..4

    builder.setCodeText(list_code_text[i - 1])

    # fileID should be same for all the generated barcodes

    builder.setMacroPdf417FileID(str_file_id)



    # Assign segmentID in increasing order (1,2,3,....)

    builder.setMacroPdf417SegmentID(i)



    # Save the barcode (fileid_segmentid.png)

    builder.save(data_dir + "#{i}.png")

end

\# Display Status

puts "Saved Images Successfully."

{{< /highlight >}}
## **Download Running Code**
Download **Generate Multiple MacroPdf417 Barcodes for Large or Multiple Code Text Values (Aspose.BarCode)** from any of the below mentioned social coding sites:

- [GitHub](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/blob/master/Plugins/Aspose_Barcode_Java_for_Ruby/lib/asposebarcodejava/2DBarcode/generatemultiplemacropdf417barcodes.rb)
