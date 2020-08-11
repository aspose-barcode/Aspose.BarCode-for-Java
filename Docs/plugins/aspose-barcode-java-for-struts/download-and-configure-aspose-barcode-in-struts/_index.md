---
title: Download and Configure Aspose.BarCode in Struts
type: docs
weight: 10
url: /java/download-and-configure-aspose-barcode-in-struts/
---

## **Downloading Aspose.Barcode Java for Struts 1.3**
You can downloaded the pre-built (binary) .war file from the latest releases hosted on [codeplex](http://asposebarcodeforstruts.codeplex.com/releases).

-OR-

You can download / check out the project source codes from the following locations:

- [CodePlex](https://asposebarcodeforstruts.codeplex.com)
- [Github](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/tree/master/Plugins/Aspose_Barcode_for_Struts)
## **Building Aspose.Barcode Java for Struts 1.3 from Source Codes**
After checking out source codes from any of the above repository, apply the following mvn commands:

{{< highlight java >}}

 $ mvn -U clean package 

{{< /highlight >}}

This will build "Strutsbookapp.war" in the target tolder.

To deploy the .war file just copy it to the running Apache tomcat server webapp directory.
