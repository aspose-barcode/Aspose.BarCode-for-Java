---
title: Setting the Environment
type: docs
weight: 10
url: /java/setting-the-environment/
---

Aspose.BarCode for J2ME can run with Java enabled mobile devices with the following platforms:

- CLDC 1.1
- MIDP 2.1

This example uses the Netbeans IDE and Java Micro Edition SDK 3.0 emulator in our example for generating and recognizing the barcodes.

{{% alert color="primary" %}} 

Please make sure that you have Netbeans IDE with J2ME platform installed on your system. If not, you may download it from <http://netbeans.org/>.

{{% /alert %}} 
### **Creating a Project**
1. After setting up Netbeans, create a new project.
1. Select **Java ME** from the categories and **Mobile Application** from the projects list, as shown in the screenshot below:

|![todo:image_alt_text](http://i.imgur.com/mqpReRt.png)|
| :- |
1. Choose **Java Platform Micro Edition SDK 3.0** from the **Emulators** menu.
1. Choose **ClamshellCldcPhone1** from the **Device** menu.
1. Choose **CLDC-1.1** from **Device Configuration** and **MIDP-2.1** from **Device Profile**. These selections are also shown in the below screenshot.

|![todo:image_alt_text](http://i.imgur.com/iYDSOzR.png)|
| :- |
1. Leave all the options as default and click **Finish** to complete the new project wizard.
### **Referencing Aspose.BarCode**
Now, to add barcode generation and recognition capabilities, add a reference to the Aspose.BarCode.J2ME.jar into the project:

1. Right-click on **Project** and click **Properties**.
1. In the left-hand tree view, expand **Build**.
1. Select **Libraries and Resources** and click **Add jar/zip…**.
1. Browse to the Aspose.BarCode.J2ME.jar file and add the reference.
   After adding the jar file, your screen should look like the below one (except path, which may be different in your case).

|![todo:image_alt_text](http://i.imgur.com/FFiAoHf.png)|
| :- |
1. Press **OK** to save the changes.

You can now write code to generate and recognize barcodes in mobile applications (Java compatible).
