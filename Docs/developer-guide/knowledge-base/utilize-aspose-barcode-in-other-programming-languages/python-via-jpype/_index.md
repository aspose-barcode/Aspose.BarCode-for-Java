---
title: Python via JPype
type: docs
weight: 30
url: /java/python-via-jpype/
---

#### **Installation and Configuration**
Python binaries are available from [http://www.python.org](http://www.python.org/). You need to download and install it before using Aspose.BarCode.

Python developers need to install JPype to make use of Java from Python. You can download JPype from <http://jpype.sourceforge.net/>

Download the latest version of Aspose.BarCode for Java, extract files and copy the lib folder with JAR files to the root folder of your project. 
#### **Python Code to Generate Barcode via JPype**
The below Python code will use JPype and use Aspose.BarCode for Java class [BarCodeBuilder](/pages/createpage.action?spaceKey=barcodejava&title=com.aspose.barcode.BarCodeBuilder+class&linkCreation=true&fromPageId=13205813) to generate barcode and save the barcode image to disk.

**Python**

{{< highlight csharp >}}

 import jpype

import os.path



jarpath = os.path.join(os.path.abspath("."), "lib")



jpype.startJVM(jpype.getDefaultJVMPath(), "-Djava.ext.dirs=%s" % jarpath)



\# Create an instance of type com.aspose.barcode.BarCodeBuilder 

BarCodeBuilder = jpype.JClass("com.aspose.barcode.BarCodeBuilder")

builder = BarCodeBuilder()

\# Set code text

builder.setCodeText("test-123")



\# Save the barcode image

builder.save("e:\\data\\aspose\\Temp\\testpythonjava.png")

{{< /highlight >}}
