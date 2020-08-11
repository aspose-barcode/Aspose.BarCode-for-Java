---
title: How to use Aspose.BarCode in TextPad
type: docs
weight: 90
url: /java/how-to-use-aspose-barcode-in-textpad/
---

{{% alert color="primary" %}} 

This article explains how to use [Aspose.BarCode for Java](http://www.aspose.com/api/java/barcode) in TextPad.

{{% /alert %}} 
### **Use Aspose.BarCode in TextPad**
TextPad is an editor for Windows with menu selections for compiling and running Java code. Follow the steps below to use Aspose.BarCode with TextPad:

1. Before setting up TextPad, download and install the current Java SDK from SUN. Then TextPad will configure itself to use that Java SDK.
1. Go to <http://www.textpad.com> on your browser and download.
1. Run the setup.exe file to install.
   1. Start TextPad by selecting its program icon in the Start/Program group (or whichever group the TextPad icon is located in).
1. To compile and run Java programs from TextPad, make adjustments:
   1. Select **Configure**, then **Preferences**.
      The Preference dialog appears.
   1. Click the plus icon (+) next to **Tools**.
   1. Click **Compile Java**.
   1. Then modify the entry in the Parameters text field to:
      -classpath d:\javaprojects $File

|![todo:image_alt_text](http://i.imgur.com/AOQKuAW.jpg)|
| :- |
1. Click **Run Java Application**.
1. Change the **Parameters** text field to:
   -classpath .;d:\javaprojects $BaseName

|![todo:image_alt_text](http://i.imgur.com/L9PMGis.png)|
| :- |
1. Click **OK** and save the setting.
   We are now ready to create a main class.
1. Enter the Java code and save the code.

When you save the class, make sure the file name is the same as the class name. For example, if you are saving the SampleCode class, save it as SampleCode.java. Make sure to match the lower- and uppercases. Java is case-sensitive.


#### **Java**
{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-Src-main-java-com-aspose-barcode-examples-technical_articles-SampleCode-SampleCode.java" >}}




1. Select **Tools**, then **Compile Java**.
   The class is compiled and the bytecode file generated. When SampleCode.java is successfully compiled, the bytecode file SampleCode.class is generated in the folder. Follow the steps just described to set the main class.
1. Run the code: select **Tools**, then **Run Java Application**.

If everything is done right, the program will run and you will see a Sketchpad window:

|![todo:image_alt_text](http://i.imgur.com/jyn4J0Z.png)|
| :- |

