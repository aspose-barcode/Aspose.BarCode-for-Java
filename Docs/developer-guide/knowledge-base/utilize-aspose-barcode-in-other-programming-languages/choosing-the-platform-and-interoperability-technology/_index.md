---
title: Choosing the Platform and Interoperability Technology
type: docs
weight: 10
url: /java/choosing-the-platform-and-interoperability-technology/
---

{{% alert color="primary" %}} 

Aspose.BarCode is a set of barcode generation and recognition components built to allow developers to add barcode functionality to their .NET and Java applications. It is available as two separate products for two different platforms.

- **Aspose.BarCode for .NET**: for .NET applications that run on .NET framework, written in any supported language (C# or VB.NET)
- **Aspose.BarCode for Java**: for Java applications (J2SE and J2EE)

It is possible to use both the .NET and Java versions of Aspose.BarCode with other programming languages depending on the language's support for .NET or Java applications. The following table lists a summary for selecting the .NET or Java version of Aspose.BarCode for each language. 

{{% /alert %}} 

|**Programming Language** |**Aspose.BarCode to Use** |**Interoperability Technology** |
| :- | :- | :- |
|ASP |.NET |COM Interop |
|Delphi |.NET |COM Interop |
|Perl |.NET |COM Interop |
| |Java |Inline::Java |
|PHP |.NET |COM Interop |
| |Java |Java Bridge |
|PowerBuilder |.NET |COM Interop |
|Python |.NET |COM Interop |
| |Java |JPype |
|VBScript |.NET |COM Interop |
|Visual Basic |.NET |COM Interop |
As you can see from the table, for some languages like ASP, Delphi, Visual Basic and PowerBuilder, we can only use Aspose.BarCode for .NET using COM Interop. But other languages like PHP, Perl and Python offers support for both Aspose.BarCode for .NET (using COM Interop) and Java (using Inline::Java, Java Bridge and JType). The following table lists pros and cons for choosing the interoperability technology.

|**Interoperability Technology** |**Pros and Cons** |
| :- | :- |
|Aspose.BarCode for .NET via COM Interop |<p>- Pros: </p><p>&emsp;- New features first appear in Aspose.BarCode for .NET and as a result, it has more features than Aspose.BarCode for Java.</p><p>- Cons: </p><p>&emsp;- Available on Microsoft Windows platforms only.</p><p>&emsp;- Cannot call static methods.</p><p>&emsp;- Hard to call overloaded methods (suffixes added to names).</p><p>&emsp;- Hard to use enumerated values (need to look up and use a constant value).</p><p>&emsp;- Cannot invoke constructors with parameters.</p>|
|Aspose.BarCode for Java via Java Bridge or Inline::Java |<p>- Pros: </p><p>&emsp;- Available on any platform where Java is available.</p><p>&emsp;- Easy to call static methods, constructors with parameters, overloaded methods and use enumerated values.</p><p>- Cons: </p><p>&emsp;- Has less frequent releases and fewer features than Aspose.BarCode for .NET</p>|

