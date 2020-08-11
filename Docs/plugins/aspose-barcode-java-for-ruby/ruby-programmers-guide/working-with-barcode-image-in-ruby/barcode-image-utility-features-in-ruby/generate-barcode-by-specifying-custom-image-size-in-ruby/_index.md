---
title: Generate BarCode by Specifying Custom Image Size in Ruby
type: docs
weight: 20
url: /java/generate-barcode-by-specifying-custom-image-size-in-ruby/
---

## **Aspose.BarCode - Generate Barcode by Specifying Custom Image Size**
To Generate Barcode by Specifying Custom Image Size using **Aspose.Barcode Java for Ruby**, simply invoke **GenerateBarcodeByCustomImageSize** module. Here you can see example code.

**Ruby Code**

{{< highlight ruby >}}

 data_dir = File.dirname(File.dirname(File.dirname(File.dirname(__FILE__)))) + '/data/'



\# Instantiate barcode object

symbology = Rjb::import('com.aspose.barcode.Symbology')

builder = Rjb::import('com.aspose.barcode.BarCodeBuilder').new("1234567890", symbology.Code39Standard)

\# Set auto size false

builder.setAutoSize(false)

\# Set height

builder.setImageHeight(50)

\# Set width

builder.setImageWidth(120)

builder.save(data_dir + "GenerateBarcodeByCustomImageSize.jpg")

\# Display Status.

puts "Generate Barcode By Custom Image Size, please check the output file."

{{< /highlight >}}
## **Download Running Code**
Download **Generate Barcode by Specifying Custom Image Size (Aspose.BarCode)** from any of the below mentioned social coding sites:

- [GitHub](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/blob/master/Plugins/Aspose_Barcode_Java_for_Ruby/lib/asposebarcodejava/BarcodeImage/generatebarcodebycustomimagesize.rb)
