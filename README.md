# Aspose.BarCode for Java Examples

This repository contains executable, self-documented TestNG examples for
[Aspose.BarCode for Java](https://products.aspose.com/barcode/java).

The examples demonstrate complete development workflows: configuring barcode data,
generating barcode images, recognizing barcodes from different input sources,
examining recognition results, validating decoded data, and working with structured
complex barcode formats.

Each example is designed to accompany the official Aspose.BarCode for Java
documentation and to provide code that can be run, inspected, and adapted for
application development.

---

## Documentation

Use the following documentation sections together with the examples in this
repository:

| Resource | Purpose |
|---|---|
| [Aspose.BarCode for Java documentation](https://docs.aspose.com/barcode/java/) | Main documentation entry point |
| [Getting Started](https://docs.aspose.com/barcode/java/getting-started/) | Installation, licensing, system requirements, and initial setup |
| [Developer Guide](https://docs.aspose.com/barcode/java/developer-guide/) | Detailed generation, recognition, and complex barcode guides |
| [API Reference](https://reference.aspose.com/barcode/java/) | Java classes, methods, properties, and enumerations |
| [Installation](https://docs.aspose.com/barcode/java/installation/) | Installing Aspose.BarCode for Java and configuring dependencies |
| [Evaluation and Licensing](https://docs.aspose.com/barcode/java/licensing/) | Applying a license and understanding evaluation limitations |
| [Release Notes](https://releases.aspose.com/barcode/java/release-notes/) | Changes, fixes, and new features in each release |

---

## Java requirements

Aspose.BarCode for Java supports **Java 8 and later**.

The examples in this repository require **Java 17 or later** to compile and run.
They may use Java APIs introduced after Java 8, including `List.of(...)` and
`Path.of(...)`.

The repository is configured as a **Gradle** project and includes the Gradle
wrapper. A separate local Gradle installation is not required.

---

## Repository structure

The Java examples are organized under:

```text
src/test/java/com/aspose/barcode/guide/
├── quickstart/
├── generation/
├── recognition/
├── complex/
└── common/
```

Sample input files and generated output files are stored under:

```text
src/test/resources/
```

The main example areas are described below.

---

## Quick start examples

Directory:

```text
src/test/java/com/aspose/barcode/guide/quickstart/
```

The quick start examples provide the shortest complete workflows for first-time
users.

### `QuickGenerationExamples`

Demonstrates how to:

- create a `BarcodeGenerator`;
- select an encoding symbology;
- assign barcode text;
- generate a barcode image;
- save the result to a file;
- verify the generated barcode.

Related documentation:

- [Quick Generation Examples](https://docs.aspose.com/barcode/java/developer-guide/quick-start/quick-generation-examples/)
- [Barcode Generation](https://docs.aspose.com/barcode/java/developer-guide/barcode-generation/)

### `QuickRecognitionExamples`

Demonstrates how to:

- create a `BarCodeReader`;
- read a barcode image;
- select one or more `DecodeType` values;
- iterate through `BarCodeResult` objects;
- retrieve recognized text and barcode type.

Related documentation:

- [Quick Recognition Examples](https://docs.aspose.com/barcode/java/developer-guide/quick-start/quick-recognition-examples/)
- [Barcode Recognition](https://docs.aspose.com/barcode/java/developer-guide/barcode-recognition/)

---

## Barcode generation examples

Directory:

```text
src/test/java/com/aspose/barcode/guide/generation/
```

These examples explain how to create barcode images and configure their data,
appearance, dimensions, output format, and validation behavior.

Main documentation section:

- [Barcode Generation](https://docs.aspose.com/barcode/java/developer-guide/barcode-generation/)

### Generation overview

Directory:

```text
generation/overview/
```

Provides introductory generation workflows and demonstrates the main steps used by
the other generation examples:

1. select an `EncodeTypes` value;
2. provide codetext;
3. configure generation parameters;
4. generate or save the barcode image;
5. recognize the result when verification is required.

Related documentation:

- [Barcode Generation](https://docs.aspose.com/barcode/java/developer-guide/barcode-generation/)

### Barcode symbology and codetext

Directory:

```text
generation/symbology_codetext/
```

Example classes include:

- `SetBarcodeSymbologyAndText`
- `SymbologyAndCodeTextExample`

These examples demonstrate how to:

- select a barcode symbology;
- assign and update codetext;
- use symbology-specific text formats;
- generate different barcode types from the same workflow;
- validate the recognized type and text.

Related documentation:

- [Set Barcode Symbology and Text](https://docs.aspose.com/barcode/java/developer-guide/barcode-generation/set-barcode-symbology-and-text/)
- [Linear Barcodes Overview](https://docs.aspose.com/barcode/java/developer-guide/linear-barcodes-overview/)

### Appearance

Directory:

```text
generation/appearance/
```

Example classes include:

- `CustomizeBarcodeAppearance`
- `CustomizeCaptionsAndText`

These examples demonstrate high-level appearance configuration, including:

- foreground and background colors;
- barcode dimensions;
- text visibility;
- captions above and below the barcode;
- caption alignment, padding, and font settings.

Related documentation:

- [Visual Parameters and Layout](https://docs.aspose.com/barcode/java/developer-guide/barcode-generation/visual-parameters-and-layout/)

### Visual parameters and layout

Directory:

```text
generation/visual_parameters/
```

This is the most detailed set of visual-generation examples.

Example classes include:

- `BackgroundsExample`
- `BordersExample`
- `ColorsExample`
- `CustomizingCaptionsExample`
- `CustomizingSizeExample`
- `ImageAndLayoutParametersExample`
- `RotationExample`
- `TextAndFontExample`
- `UseUnitExample`
- `VisualParametersAndLayout`

The examples demonstrate how to configure:

- barcode and image colors;
- image backgrounds;
- borders and border styles;
- barcode width and height;
- padding and quiet zones;
- X-dimension and bar height;
- caption position and alignment;
- human-readable text;
- font family, size, and style;
- image rotation;
- units such as pixels, millimeters, points, and inches;
- image resolution and layout behavior.

Related documentation:

- [Visual Parameters and Layout](https://docs.aspose.com/barcode/java/developer-guide/barcode-generation/visual-parameters-and-layout/)

### Barcode size

Directory:

```text
generation/size/
```

Example class:

- `CustomizeBarcodeSize`

Demonstrates how to control:

- barcode image width and height;
- X-dimension;
- bar height;
- auto-size modes;
- aspect ratio;
- padding and quiet zones;
- resolution-dependent dimensions.

Related documentation:

- [Visual Parameters and Layout](https://docs.aspose.com/barcode/java/developer-guide/barcode-generation/visual-parameters-and-layout/)
- [X-Dimension](https://docs.aspose.com/barcode/java/developer-guide/barcode-generation/xdimension/)

### Unit class

Directory:

```text
generation/unit/
```

Example class:

- `UnitClassExample`

Demonstrates the `Unit` API and conversion between supported measurement units.
These examples are useful when barcode dimensions must be defined in physical
units rather than only in pixels.

Related documentation:

- [Visual Parameters and Layout](https://docs.aspose.com/barcode/java/developer-guide/barcode-generation/visual-parameters-and-layout/)

### X-dimension

Directory:

```text
generation/xdimension/
```

Example class:

- `XDimensionExamples`

Demonstrates how X-dimension affects:

- module and bar width;
- final image size;
- barcode readability;
- output at different resolutions;
- conversion between pixel and physical dimensions.

Related documentation:

- [X-Dimension](https://docs.aspose.com/barcode/java/developer-guide/barcode-generation/xdimension/)

### Checksums

Directory:

```text
generation/checksum/
```

Example classes include:

- `EnforcedChecksumExamples`
- `OptionalChecksumExamples`
- `SetBarcodeChecksum`

These examples demonstrate the difference between symbologies with:

- mandatory checksums that are always generated;
- optional checksums that can be enabled or disabled;
- reader-side checksum validation;
- normalized or expanded decoded text;
- checksum-aware regression tests.

The examples cover common 1D symbologies such as Code 39, Code 93, Code 128,
EAN, UPC, Interleaved 2 of 5, Codabar, and other formats with
symbology-specific checksum rules.

Related documentation:

- [Set Barcode Checksum](https://docs.aspose.com/barcode/java/developer-guide/barcode-generation/set-barcode-checksum/)
- [Barcode Recognition Special Parameters](https://docs.aspose.com/barcode/java/developer-guide/barcode-recognition/special-parameters/)

### Save options

Directory:

```text
generation/save_options/
```

Example classes include:

- `SaveGeneratedBarcodes`
- `SaveGeneratedImageExample`

These examples demonstrate how to:

- save generated barcodes to files;
- save images to output streams;
- select an output image format;
- obtain a generated `BufferedImage`;
- use generated barcode images in memory-based workflows.

Related documentation:

- [Save Generated Barcodes](https://docs.aspose.com/barcode/java/developer-guide/barcode-generation/save-generated-barcodes/)

### Print options

Directory:

```text
generation/print_options/
```

Example classes include:

- `PrintBarcodes`
- `PrintOptionsExample`

These examples demonstrate barcode printing workflows, including:

- rendering a barcode for printing;
- working with printer graphics;
- controlling placement and dimensions;
- preparing barcode images for printed documents and labels.

Related documentation:

- [Printing Barcodes](https://docs.aspose.com/barcode/java/developer-guide/barcode-generation/printing/)

---

## Barcode recognition examples

Directory:

```text
src/test/java/com/aspose/barcode/guide/recognition/
```

These examples demonstrate how to read barcodes, restrict recognition to selected
symbologies, work with image regions and input sources, inspect recognition
metadata, and tune recognition quality and performance.

Main documentation section:

- [Barcode Recognition](https://docs.aspose.com/barcode/java/developer-guide/barcode-recognition/)

### Choose recognition symbology

Directory:

```text
recognition/choose_symbology/
```

Example class:

- `ChooseRecognitionSymbology`

Demonstrates how to:

- recognize a specific barcode type;
- provide several expected `DecodeType` values;
- use all supported types when the symbology is unknown;
- reduce unnecessary recognition work by selecting expected types.

Related documentation:

- [Barcode Recognition](https://docs.aspose.com/barcode/java/developer-guide/barcode-recognition/)
- [Quick Recognition Examples](https://docs.aspose.com/barcode/java/developer-guide/quick-start/quick-recognition-examples/)

### Input sources

Directory:

```text
recognition/input_sources/
```

Example class:

- `InputSourcesExamples`

Demonstrates how to read barcodes from:

- image file paths;
- input streams;
- byte arrays;
- `BufferedImage` objects;
- application-managed image sources.

Related documentation:

- [Recognition Input Sources](https://docs.aspose.com/barcode/java/developer-guide/barcode-recognition/input-sources/)

### Barcode properties

Directory:

```text
recognition/barcode_properties/
```

Example classes include:

- `AnglesExample`
- `ConfigureSymbologyTypeExample`
- `CoordinatesExample`
- `ReadingCodeTextExample`
- `ReadingCodeTextRawExample`
- `ReadingMetadataExample`
- `ResultValidationExample`

These examples demonstrate how to retrieve and validate:

- recognized codetext;
- raw codetext bytes;
- barcode type;
- confidence and reading quality;
- barcode angle;
- bounding rectangle;
- quadrangle coordinates;
- extended recognition parameters;
- result validity and consistency.

Related documentation:

- [Read Barcode Properties](https://docs.aspose.com/barcode/java/developer-guide/barcode-recognition/barcode-properties/)

### Region of interest

Directory:

```text
recognition/roi/
```

Example class:

- `RegionOfInterestExamples`

Demonstrates how to:

- restrict recognition to a selected rectangle;
- scan several regions independently;
- improve performance when the barcode location is known;
- avoid unrelated objects in complex images.

Related documentation:

- [Region of Interest](https://docs.aspose.com/barcode/java/developer-guide/barcode-recognition/region-of-interest/)

### Quality settings

Directory:

```text
recognition/quality_settings/
```

Example class:

- `QualitySettingsExample`

Demonstrates how recognition quality presets and individual settings affect
barcode reading.

Use these examples when balancing:

- recognition speed;
- support for damaged or low-quality images;
- small barcode modules;
- image complexity;
- expected barcode quality.

Related documentation:

- [Recognition Performance and Quality](https://docs.aspose.com/barcode/java/developer-guide/barcode-recognition/performance/)

### Performance and difficult images

Directory:

```text
recognition/performance/
```

Example classes include:

- `DeconvolutionModeExample`
- `HighPerformanceModeExample`
- `MinimalXDimensionExample`
- `MultithreadReadingExample`
- `QualityModeExample`
- `ReadingColorInvertedExample`
- `ReadingDamagedBarcodeExample`
- `ReadingLowResolutionBarcodeExample`
- `RecognitionPresets`

These examples demonstrate how to:

- select recognition presets;
- optimize for speed or quality;
- process several images in parallel;
- recognize low-resolution barcodes;
- recognize damaged or blurred symbols;
- handle inverted barcode colors;
- configure minimal X-dimension;
- use deconvolution-related recognition settings.

Related documentation:

- [Recognition Performance and Quality](https://docs.aspose.com/barcode/java/developer-guide/barcode-recognition/performance/)

### Special recognition parameters

Directory:

```text
recognition/special_parameters/
```

Example classes include:

- `AllowIncorrectBarcodesExample`
- `AustralianPostParametersExample`
- `ChecksumValidationExample`
- `DetectEncodingExample`
- `StripFNCExample`

These examples demonstrate specialized reader behavior, including:

- reading barcodes that do not fully satisfy a symbology specification;
- checksum validation modes;
- automatic text encoding detection;
- handling FNC characters;
- Australian Post decoding parameters.

Related documentation:

- [Special Recognition Parameters](https://docs.aspose.com/barcode/java/developer-guide/barcode-recognition/special-parameters/)

---

## Complex barcode examples

Directory:

```text
src/test/java/com/aspose/barcode/guide/complex/
```

Complex barcodes contain structured business data defined by payment, healthcare,
postal, shipping, or identification standards. These examples demonstrate the full
workflow:

1. populate a typed complex codetext model;
2. generate its carrier barcode;
3. recognize the carrier symbology;
4. decode the recognized text into a typed object;
5. validate individual business fields.

Main documentation section:

- [Complex Barcode](https://docs.aspose.com/barcode/java/developer-guide/complex-barcode/)

### Introduction

Directory:

```text
complex/introduction/
```

Explains the difference between:

- regular barcode generation with `BarcodeGenerator`;
- structured barcode generation with `ComplexBarcodeGenerator`;
- raw recognized text;
- typed decoding through `ComplexCodetextReader`.

Related documentation:

- [Introduction to Complex Barcodes](https://docs.aspose.com/barcode/java/developer-guide/complex-barcode/introduction/)

### Generate complex barcodes

Directory:

```text
complex/generate/
```

Demonstrates how to:

- create typed business data;
- generate the standardized payload;
- configure carrier appearance;
- save complex barcodes to files or streams;
- verify generated output.

Related documentation:

- [Generate Complex Barcodes](https://docs.aspose.com/barcode/java/developer-guide/complex-barcode/generate/)

### Read complex barcodes

Directory:

```text
complex/read/
```

Demonstrates the two-stage recognition workflow:

1. recognize the physical carrier barcode;
2. pass recognized codetext to the matching complex decoder.

The examples also show how to handle unsupported data and `null` decoder results.

Related documentation:

- [Read Complex Barcodes](https://docs.aspose.com/barcode/java/developer-guide/complex-barcode/read/)

### Supported complex barcode types

Directory:

```text
complex/supported_types/
```

The examples are grouped by standard.

| Directory | Structured data demonstrated | Related documentation |
|---|---|---|
| `swiss_qr/` | Swiss QR payment data, creditor and debtor addresses, references, and messages | [Swiss QR Barcodes](https://docs.aspose.com/barcode/java/developer-guide/complex-barcode/supported_types/swiss_qr/) |
| `hibc/` | HIBC LIC product data and HIBC PAS healthcare records | [HIBC Barcodes](https://docs.aspose.com/barcode/java/developer-guide/complex-barcode/supported_types/hibc/) |
| `royal_mail_mailmark/` | Royal Mail Mailmark 1D and Mailmark 2D postal data | [Royal Mail Mailmark Barcodes](https://docs.aspose.com/barcode/java/developer-guide/complex-barcode/supported_types/royal_mail_mailmark/) |
| `maxicode/` | Structured MaxiCode mode 2 and mode 3 data | [MaxiCode Barcodes](https://docs.aspose.com/barcode/java/developer-guide/complex-barcode/supported_types/maxicode/) |
| `usa_driver_id/` | AAMVA USA Driver ID data stored in PDF417 | [USA Driver ID Barcodes](https://docs.aspose.com/barcode/java/developer-guide/complex-barcode/supported_types/usa_driver_id/) |

The parent documentation page is available here:

- [Supported Complex Barcode Types](https://docs.aspose.com/barcode/java/developer-guide/complex-barcode/supported_types/)

### Complete complex barcode workflows

Directory:

```text
complex/examples/
```

Provides complete end-to-end tests that combine:

- business-object creation;
- image generation;
- carrier recognition;
- structured decoding;
- field-by-field validation;
- processing of several complex barcode types.

Related documentation:

- [Complete Complex Barcode Examples](https://docs.aspose.com/barcode/java/developer-guide/complex-barcode/examples/)

### Complex barcode troubleshooting

Directory:

```text
complex/troubleshooting/
```

Demonstrates how to distinguish between:

- image-recognition failures;
- carrier-type mismatches;
- structured-decoding failures;
- missing required fields;
- standard-specific range validation errors.

Related documentation:

- [Troubleshoot Complex Barcodes](https://docs.aspose.com/barcode/java/developer-guide/complex-barcode/troubleshooting/)

---

## Common test utilities

Directory:

```text
src/test/java/com/aspose/barcode/guide/common/
```

The classes in this directory support the examples and are not part of the public
Aspose.BarCode API.

### `ExampleAssist`

Provides shared operations such as:

- creating and checking output paths;
- validating generated files;
- recognizing generated images;
- comparing expected barcode type and text;
- matching exact text or an expected prefix;
- applying image transformations;
- generating degraded images for recognition tests;
- printing recognition diagnostics.

### `Expected` and `CompareMode`

Describe the expected recognition result and define how decoded text is compared,
for example by exact equality or prefix matching.

### `ImageSupplier`

Provides reusable image-loading behavior for tests that process input images in
different forms.

### `LicenseAssist`

Applies an Aspose.BarCode license when one is available.

A license is optional for running the examples. Without a license, the library
works in evaluation mode and evaluation limitations may apply.

---

## Prerequisites

Before running the examples, install or provide:

- **JDK 17 or later**;
- Git;
- internet access for the first dependency resolution;
- an optional Aspose.BarCode license.

Check the active Java version:

```bash
java -version
```

Check the compiler version:

```bash
javac -version
```

Both commands should report Java 17 or later.

---

## Build and run

This repository is configured with Gradle and includes the Gradle wrapper.

### Linux and macOS

Run all tests:

```bash
./gradlew clean test
```

### Windows

Run all tests:

```bat
gradlew.bat clean test
```

### Run one example class

Linux and macOS:

```bash
./gradlew test --tests "com.aspose.barcode.guide.quickstart.QuickGenerationExamples"
```

Windows:

```bat
gradlew.bat test --tests "com.aspose.barcode.guide.quickstart.QuickGenerationExamples"
```

### Run one test method

```bash
./gradlew test --tests "fully.qualified.ClassName.methodName"
```

Replace the package, class, and method names with the example you want to run.

> This repository does not include a Maven `pom.xml`. The documented build commands
> therefore use the Gradle wrapper.

---

## Run examples from an IDE

To use IntelliJ IDEA, Eclipse, or another Java IDE:

1. clone the repository;
2. open or import it as a Gradle project;
3. select JDK 17 or later as the project SDK;
4. allow Gradle to resolve dependencies;
5. run the required TestNG class or method.

The examples are tests, so the IDE must use TestNG when launching them.

---

## Output files and test resources

The examples use the following directory for input resources and generated output:

```text
src/test/resources/
```

Files are organized by topic so that the output of each example can be inspected
after the test completes.

Many generation tests follow this validation pattern:

1. generate a barcode image;
2. verify that the file exists and is not empty;
3. read the generated image with `BarCodeReader`;
4. compare the recognized type and codetext with the expected result.

This makes the examples useful both as documentation and as executable regression
tests.

---

## Test conventions

When adding or modifying examples, follow these conventions:

- keep one primary concept per test;
- use clear class-level and method-level Javadoc;
- use deterministic file names and dimensions;
- store test data under `src/test/resources`;
- verify generated output through recognition where practical;
- validate structured fields for complex barcode examples;
- keep package names lowercase;
- use `ExampleAssist` and `LicenseAssist` where appropriate;
- do not treat helper methods from `common` as public Aspose.BarCode API.

---

## Troubleshooting

### Gradle reports an unsupported Java version

Confirm that Gradle is using JDK 17 or later:

```bash
./gradlew --version
```

The JVM shown in the output must be Java 17 or later.

### Dependencies cannot be resolved

Check internet access, proxy configuration, and access to the configured Maven
repositories. Then retry:

```bash
./gradlew --refresh-dependencies test
```

### A filtered test is not found

Use the fully qualified TestNG class name:

```bash
./gradlew test --tests "com.aspose.barcode.guide.generation.xdimension.XDimensionExamples"
```

Check the package declaration in the source file if the filter still does not
match.

### A generated barcode is not recognized

Check:

- selected `DecodeType`;
- X-dimension;
- bar height;
- image resolution;
- quiet zones and padding;
- foreground/background contrast;
- checksum settings;
- recognition quality settings.

Use the diagnostics in `ExampleAssist` and the related recognition examples when
investigating the result.

### License-related output or evaluation limitations

Configure the license through `LicenseAssist.setupLicense()` using the license
location expected by your local environment. Do not commit private license files
to the repository.

---

## Contributing

When contributing a new example:

1. place it in the package that matches the related documentation topic;
2. create a separate TestNG class when the topic is independent;
3. add detailed Javadoc explaining what the class and every test method demonstrate;
4. use fixed and reproducible test data;
5. verify generated output when possible;
6. add or update the corresponding documentation link in this README;
7. avoid committing private license files or environment-specific paths.

---

## Additional resources

- [Aspose.BarCode for Java documentation](https://docs.aspose.com/barcode/java/)
- [Getting Started](https://docs.aspose.com/barcode/java/getting-started/)
- [Developer Guide](https://docs.aspose.com/barcode/java/developer-guide/)
- [API Reference](https://reference.aspose.com/barcode/java/)
- [Product Page](https://products.aspose.com/barcode/java)
- [Download Aspose.BarCode for Java](https://releases.aspose.com/barcode/java/)
- [Free Support Forum](https://forum.aspose.com/c/barcode)

---

## License

This repository contains example code and tests that use Aspose.BarCode for Java.
Review the Aspose licensing terms before using the library in production.

---

**Happy barcoding!**
