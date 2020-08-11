---
title: Perl via Inline Java
type: docs
weight: 20
url: /java/perl-via-inline-java/
---

### **Installation and Configuration**
Perl binaries are available from <http://www.perl.com/download.csp>. Please download and install the binaries for your Windows Operating System. The **Inline::Java** module allows you to put Java source code directly "inline" in a Perl script or module. A Java compiler is launched and the Java code is compiled. Then Perl asks the Java classes what public methods have been defined. These classes and methods are available to the Perl program as if they had been written in Perl. See <http://search.cpan.org/dist/Inline-Java/Java.pod> to learn more about Inline::Java.

To install the Inline::Java module:

1. Add theoryx5 ppm repo using the following command:
   C:\Perl\bin> ppm repo add <http://theoryx5.uwinnipeg.ca/ppms/>
1. Install the Inline::Java module:
   C:\Perl\bin> ppm install inline::Java
1. Set path to your JDK:
   C:\Perl\bin> set PERL_INLINE_JAVA_J2SDK=C:\Program Files\Java\jdk1.6.0_03

{{% alert color="primary" %}} 

Download the latest version of Aspose.BarCode for Java, extract files and copy the lib folder with jar files to the root folder of your project.

{{% /alert %}} 
#### **Perl Script to Generate Barcode using Inline Java**
The following code will generate barcode and save the barcode image.

**Perl**

{{< highlight csharp >}}



use Inline Java => <<'END_OF_JAVA_CODE', CLASSPATH=> 'lib/Aspose.BarCode.jar';

import com.aspose.barcode.*;

    class BarCode

    {

        public BarCode()

        {

        }



        public void GenerateBarCode() throws Exception

        {

            // Create an instance of type BarCodeBuilder

            BarCodeBuilder builder = new BarCodeBuilder();

            // Set CodeText

            builder.setCodeText("test-123");

    // Save the barcode image

            builder.save("testperljava.png");

        }

    }

END_OF_JAVA_CODE



my $barcode = new BarCode();

$barcode->GenerateBarCode();

{{< /highlight >}}
