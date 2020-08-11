---
title: Aspose.BarCode for Android via Java Installation
type: docs
weight: 10
url: /java/aspose-barcode-for-android-via-java-installation/
---

## **System Requirements**
Aspose.BarCode for Android via Java is a platform-independent so it can be used on any platform where the Android Runtime environment is installed and will run on Android systems running Android OS 2.0 or greater. At present, the component has been tested with:

- Android 5.1 v 22
## **Installation**
1. Download the latest version of Aspose.BarCode for Android via Java from [Aspose Artifactory](https://repository.aspose.com/webapp/#/artifacts/browse/tree/General/repo/com/aspose/aspose-barcode/).
1. Unzip the installation package and copy the aspose-barcode-android-20.4.jar file to your Java application.
1. Provide a reference to the aspose-barcode-android-20.4.jar file so the compiler can find it.
## **Install Aspose.BarCode for Android via Java from Maven Repository**
1. Add maven repository into your build.gradle
1. Add 'Aspose.BarCode for Android via Java' JAR as a dependency

{{< highlight java >}}

 // 1. Add maven repository into your build.gradle 

repositories {

    mavenCentral()

    maven { url "http://repository.aspose.com/repo/" }

}



// 2. Add 'Aspose.BarCode for Android via Java' JAR as a dependency

dependencies {

    ...

    ...

    compile (group: 'com.aspose', name: 'aspose-barcode', version: '20.4', classifier: 'android.via.java')

}

{{< /highlight >}}
