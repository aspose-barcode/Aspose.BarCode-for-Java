---
title: Download and Configure Aspose.BarCode in Ruby
type: docs
weight: 10
url: /java/download-and-configure-aspose-barcode-in-ruby/
---

## **Download Required Libraries**
Download required libraries mentioned below. These are the required for executing Aspose.Barcode Java for Ruby examples.

- [Aspose.Barcode for Java Component](http://www.aspose.com/community/files/72/java-components/aspose.barcode-for-java/default.aspx)
## **Download Examples from Social Coding Sites**
Following releases of running examples are available to download on below mentioned social coding sites:

**GitHub**

- [Aspose.Barcode Java for Ruby](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/tree/master/Plugins/Aspose_Barcode_Java_for_Ruby)
## **Installing**
It is very simple and easy to install Aspose.Barcode Java for Ruby gem, please follow these simple steps:

1. Run following command. 

{{< highlight java >}}

 $ gem install aspose-barcodejava

{{< /highlight >}}

1. Download required Aspose.Barcode for Java Component from following link.
   <http://www.aspose.com/community/files/72/java-components/aspose.barcode-for-java/default.aspx>
1. Create "jars" folder at root of the Aspose.Barcode Java for Ruby gem and copy downloaded component into it.
## **Using**
Include the required files for working with the RecognizeBarcode example.

{{< highlight java >}}

 require File.dirname(File.dirname(File.dirname(__FILE__))) + '/lib/aspose-barcodejava'

include Asposebarcodejava

include Asposebarcodejava::RecognizeBarcode

initialize_aspose_barcode

{{< /highlight >}}

Let's understand the above code.

1. The first line makes sure that the Aspose.BarCode is loaded and available.
1. Include the files that are required to access the Aspose.BarCode.
1. Initialize the libraries. The aspose JAVA classes are loaded from the path provided in the aspose.yml file
