---
title: Aspose.BarCode Java For Jython
type: docs
weight: 60
url: /java/aspose-barcode-java-for-jython/
---

## **Introduction**
### **What is Jython?**
Jython is a Java implementation of Python that combines expressive power with clarity. Jython is freely available for both commercial and non-commercial use and is distributed with source code. Jython is complementary to Java and is especially suited for the following tasks:

- **Embedded scripting** - Java programmers can add the Jython libraries to their system to allow end users to write simple or complicated scripts that add functionality to the application.
- **Interactive experimentation** - Jython provides an interactive interpreter that can be used to interact with Java packages or with running Java applications. This allows programmers to experiment and debug any Java system using Jython.
- **Rapid application development** - Python programs are typically 2-10X shorter than the equivalent Java program. This translates directly to increased programmer productivity. The seamless interaction between Python and Java allows developers to freely mix the two languages both during development and in shipping products. 
### **Aspose.BarCode for Java**
Aspose.BarCode for Java is a robust and reliable barcode generation and recognition component, written in Java, it allows developers to quickly and easily add barcode generation and recognition functionality to their Java applications.

Aspose.BarCode for Java supports the Java SE, Java EE and Java ME platforms.
### **Aspose.BarCode Java for Jython**
Project Aspose.BarCode for Jython shows how different tasks can be performed using Aspose.BarCode Java APIs in Jython. This project is aimed to provide useful examples for Jython Developers who want to utilize Aspose.BarCode for Java in their Jython Projects using [Jython](http://www.jython.org/).
## **System Requirements and Supported Platforms**
### **System Requirements**
**Following are the system requirements to use Aspose.BarCode Java for Jython:**

- Java 1.5 or above installed
- Downloaded Aspose.BarCode component
- Jython 2.7.0
### **Supported Platforms**
**Following are the supported platforms:**

- Aspose.BarCode 15.4 and above.
- Java IDE (Eclipse, NetBeans ...)
## **Download Installation and Usage**
### **Downloading**
**Download Examples from social coding websites**

Following releases of running examples are available to download on all of the below mentioned social coding sites:

- [CodePlex](https://asposebarcodejavajython.codeplex.com/releases/view/619260)
- [Github](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/releases/tag/Aspose.BarCode_Java_for_Jython-v1.0)

**Download Aspose.BarCode for Java component**

- [Aspose.BarCode for Java](http://www.aspose.com/community/files/72/java-components/aspose.barcode-for-java/default.aspx)
### **Installing**
- Place downloaded Aspose.BarCode for Java jar file into "lib" directory.
- Replace "your-lib" with the downloaded jar filename in _*init*_.py file.
### **Using**
You can create 2D BarCode using following example code:

{{< highlight java >}}

 from asposebarcode import Settings

from com.aspose.barcode import BarCodeBuilder

from com.aspose.barcode import Symbology

class Creating2DBarCode:

    def __init__(self):

        dataDir = Settings.dataDir + 'WorkingWith2DBarCodes/Basic2DBarCodeFeatures/Creating2DBarCode'

        # Instantiate barcode object

        builder =  BarCodeBuilder()

        symbology= Symbology

        builder.setSymbologyType(symbology.Pdf417)

        # Width of each module

        builder.setxDimension(0.6)

        # Height of each module

        builder.setyDimension(1.2)

        builder.setCodeText("this is some test code text. \n Second line \n third line.")

        # Save the image to your system and set its image format to Jpeg

        builder.save(dataDir + "Creating2DBarCode.jpg")

        # Display Status

        print "Created 2D BarCode Successfully."

if __name__ == '__main__':

    Creating2DBarCode()

{{< /highlight >}}

**This section includes the following topics:**

- [Jython Programmers Guide](/barcode/java/jython-programmers-guide-html/)
  - [Working with 2D Barcodes in Jython](/barcode/java/working-with-2d-barcodes-in-jython-html/)
    - [Basic 2D Barcode Features in Jython](/barcode/java/basic-2d-barcode-features-in-jython-html/)
      - [Creating a DataMatrix Barcode in Jython](/barcode/java/creating-a-datamatrix-barcode-in-jython-html/)
      - [Creating an Aztec Barcode in Jython](/barcode/java/creating-an-aztec-barcode-in-jython-html/)
      - [Creating a Pdf417 Barcode in Jython](/barcode/java/creating-a-pdf417-barcode-in-jython-html/)
      - [Creating a QR Barcode in Jython](/barcode/java/creating-a-qr-barcode-in-jython-html/)
      - [Creating Two Dimensional (2D) Barcodes in Jython](/barcode/java/creating-two-dimensional-282d-29-barcodes-in-jython-html/)
    - [Utility 2D Barcode Features in Jython](/barcode/java/utility-2d-barcode-features-in-jython-html/)
      - [Hiding Code Text that is too Long to Display in Jython](/barcode/java/hiding-code-text-that-is-too-long-to-display-in-jython-html/)
      - [Set Aspect Ratio of Barcodes in Jython](/barcode/java/set-aspect-ratio-of-barcodes-in-jython-html/)
  - [Working with Barcode Image in Jython](/barcode/java/working-with-barcode-image-in-jython-html/)
    - [Barcode Image Basic Features in Jython](/barcode/java/barcode-image-basic-features-in-jython-html/)
      - [Colorize any Part of the Barcode Image in Jython](/barcode/java/colorize-any-part-of-the-barcode-image-in-jython-html/)
      - [Control Barcode Image Quality in Jython](/barcode/java/control-barcode-image-quality-in-jython-html/)
      - [Generate Barcode with Empty Bars in Jython](/barcode/java/generate-barcode-with-empty-bars-in-jython-html/)
      - [Rotate Barcode Image in Jython](/barcode/java/rotate-barcode-image-in-jython-html/)
      - [Set Barcode Image Margins in Jython](/barcode/java/set-barcode-image-margins-in-jython-html/)
      - [Working with Image Borders in Jython](/barcode/java/working-with-image-borders-in-jython-html/)
    - [Barcode Image Utility Features in Jython](/barcode/java/barcode-image-utility-features-in-jython-html/)
      - [Customize Barcode Image Resolution in Jython](/barcode/java/customize-barcode-image-resolution-in-jython-html/)
      - [Generate Barcode by Specifying Custom Image Size in Jython](/barcode/java/generate-barcode-by-specifying-custom-image-size-in-jython-html/)
      - [Save Barcode Image to Streams in Jython](/barcode/java/save-barcode-image-to-streams-in-jython-html/)
      - [Set Size Unit for the Barcode Image in Jython](/barcode/java/set-size-unit-for-the-barcode-image-in-jython-html/)
  - [Working with Barcode in Jython](/barcode/java/working-with-barcode-in-jython-html/)
    - [Advanced Barcode Features in Jython](/barcode/java/advanced-barcode-features-in-jython-html/)
      - [Generate AustraliaPost Barcode with Different Format Control Code Options in Jython](/barcode/java/generate-australiapost-barcode-with-different-format-control-code-options-in-jython-html/)
      - [How to Generate a Patch Code in Jython](/barcode/java/how-to-generate-a-patch-code-in-jython-html/)
      - [Manage X-Dimension and Y-Dimension in Jython](/barcode/java/manage-x-dimension-and-y-dimension-in-jython-html/)
      - [Set Height of the Bars in the Barcode Image in Jython](/barcode/java/set-height-of-the-bars-in-the-barcode-image-in-jython-html/)
      - [Set Start and Stop Symbols of Codabar Barcode in Jython](/barcode/java/set-start-and-stop-symbols-of-codabar-barcode-in-jython-html/)
      - [Use Checksum and Supplement Data for Barcodes in Jython](/barcode/java/use-checksum-and-supplement-data-for-barcodes-in-jython-html/)
      - [Wide Narrow Ratio in Jython](/barcode/java/wide-narrow-ratio-in-jython-html/)
    - [Utility Barcode Features in Jython](/barcode/java/utility-barcode-features-in-jython-html/)
      - [Control the Appearance of Code Text in Jython](/barcode/java/control-the-appearance-of-code-text-in-jython-html/)
      - [Manage the BarCode Caption in Jython](/barcode/java/manage-the-barcode-caption-in-jython-html/)
      - [Set Code Text for Barcode in Jython](/barcode/java/set-code-text-for-barcode-in-jython-html/)
      - [Specify Symbologies for Barcodes in Jython](/barcode/java/specify-symbologies-for-barcodes-in-jython-html/)
  - [Working with Barcode Recognition in Jython](/barcode/java/working-with-barcode-recognition-in-jython-html/)
    - [Advanced Barcode Recognition Features in Jython](/barcode/java/advanced-barcode-recognition-features-in-jython-html/)
      - [Get all Possible 1D Barcodes from an Image in Jython](/barcode/java/get-all-possible-1d-barcodes-from-an-image-in-jython-html/)
      - [Get Barcode Recognition Quality in Percent in Jython](/barcode/java/get-barcode-recognition-quality-in-percent-in-jython-html/)
      - [Getting Barcode Region Information from the Image in Jython](/barcode/java/getting-barcode-region-information-from-the-image-in-jython-html/)
      - [Marking Barcode Regions in an Image in Jython](/barcode/java/marking-barcode-regions-in-an-image-in-jython-html/)
      - [Read Barcode from Specific Region of Image in Jython](/barcode/java/read-barcode-from-specific-region-of-image-in-jython-html/)
      - [Switch Barcode Recognition Modes According to the Requirement in Jython](/barcode/java/switch-barcode-recognition-modes-according-to-the-requirement-in-jython-html/)
    - [Basic Barcode Recognition Features in Jython](/barcode/java/basic-barcode-recognition-features-in-jython-html/)
      - [Introducing Barcode Recognition in Jython](/barcode/java/introducing-barcode-recognition-in-jython-html/)
      - [Recognizing Multiple Symbologies in Single Image in Jython](/barcode/java/recognizing-multiple-symbologies-in-single-image-in-jython-html/)
      - [Recognizing Specific Barcode Symbology in Jython](/barcode/java/recognizing-specific-barcode-symbology-in-jython-html/)
## **Support, Extend and Contribute**
### **Support**
From the very first days of Aspose, we knew that just giving our customers good products would not be enough. We also needed to deliver good service. We are developers ourselves and understand how frustrating it is when a technical issue or a quirk in the software stops you from doing what you need to do. We're here to solve problems, not create them.

This is why we offer free support. Anyone who uses our product, whether they have bought them or are using an evaluation, deserves our full attention and respect.

You can log any issues or suggestions related to Aspose.BarCode Java for Jython using any of the following platforms:

- [CodePlex](https://asposebarcodejavajython.codeplex.com/workitem/list/basic)
- [Github](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/issues)
### **Extend and Contribute**
Aspose.BarCode Java for Jython is open source and its source code is available on the major social coding websites listed below. Developers are encouraged to download the source code and contribute by suggesting or adding new feature or improving the existing ones, so that others could also benefit from it.
### **Source Code**
You can get the latest source code from one of the following locations

- [CodePlex](https://asposebarcodejavajython.codeplex.com/SourceControl/latest)
- [Github](https://github.com/aspose-barcode/Aspose.BarCode-for-Java)
