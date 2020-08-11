---
title: Render Barcode to Servlet
type: docs
weight: 40
url: /java/render-barcode-to-servlet/
---

{{% alert color="primary" %}} 

Aspose.BarCode for Java provides graphics interfaces to render barcode images in various situations, including AWT graphics, web response streams, printers, and images. This article illustrates how to render barcodes to servlet pages.

{{% /alert %}} 

The following servlet sample shows how to render barcodes:


#### **Java**
{{< gist "aspose-com-gists" "9dea2dd38be50330a824dd05da062a97" "Examples-src-main-java-com-aspose-barcode-examples-barcode_image-rendering_features-RenderBarcodeToServlet-RenderBarcodeToServlet.java" >}}



To build and run this servlet, we need servlet-api.jar as well as Aspose.BarCode.Jar. The Servlet-api.jar is provided by your web server, if not, copy the one provided in the Aspose.BarCode.zip.

Edit web.xml to point to your servlet:

**XML**

{{< highlight csharp >}}

 <?xml version="1.0" encoding="UTF-8"?>

        <web-app version="2.4"

        xmlns="http://java.sun.com/xml/ns/j2ee"

        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"

        xsi:schemaLocation="http://java.sun.com/xml/ns/j2ee

        http://java.sun.com/xml/ns/j2ee/web-app_2_4.xsd">

  <servlet>

    <description>desc</description>

    <display-name>name</display-name>

    <servlet-name>ServletSample</servlet-name>

    <servlet-class>ServletSample</servlet-class>

  </servlet>

  <servlet-mapping>

    <servlet-name>ServletSample</servlet-name>

    <url-pattern>/servlet/ServletSample</url-pattern>

  </servlet-mapping>

</web-app>

{{< /highlight >}}

Packup and deploy. This sample will be run on a Tomcat server.

|![todo:image_alt_text](http://i.imgur.com/4DXZc24.png)|
| :- |
|**Figure: Deploy**|
Here's a runtime snapshot powered by Tomcat server:

|![todo:image_alt_text](http://i.imgur.com/yEx6ee0.png)|
| :- |
|**Figure: Servlet sample**|

