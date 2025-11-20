# Barcode Generation & Recognition Examples (Java + TestNG)

This repository contains **executable, self-documented tests** that demonstrate how to generate and recognize barcodes with **Aspose.BarCode for Java**.  
Each test focuses on a single concept (size, captions, colors, borders, checksums, text & fonts, etc.) and saves its output to `src/test/resources` for easy inspection.

> Library: [Aspose.BarCode for Java](https://products.aspose.com/barcode/java)

---

## What’s inside

- **Generation topics**
    - *Visual / physical size* (X-Dimension in px/mm + DPI, bar height, auto-size modes, quiet zones).
    - *Captions & human-readable text* (on/off, above/below, padding, fonts, alignment).
    - *Text & Fonts* (font family/size/style, ECI, encoding nuances).
    - *Colors* (bar color, background color, caption color).
    - *Borders* (frame visibility, width, color, dashed styles, ITF-14 bearer bars).
    - *Checksums*
        - **Enforced** (Code 93, Code 128, GS1 Code 128, EAN-8/13/14, UPC-A/E, ISBN, SSCC-18, SCC-14).
        - **Optional** (Code 39, Standard/Interleaved/Matrix 2 of 5, IATA 2 of 5, Codabar, VIN, Deutsche Post, etc.), including examples of toggling checksum on/off and validating with the reader.

- **Recognition topics**
    - Targeted decoding by `DecodeType`, handling **checksum validation modes**.
    - Reading metadata & geometry for debugging.
    - Robustness tips for low-resolution or tight-layout barcodes.

- **Utilities**
    - `ExampleAssist` — filesystem helpers, assertions, image transforms, **expected text vs prefix** matching, **I-2/5 Mod10** calculator, crisp downscaling for tiny codes, etc.

---

## Prerequisites

- **Java 8+** (tested up to current LTS)
- **Gradle**
- Internet access (to resolve dependencies)
- A valid Aspose.BarCode license (optional).  
  If you have one, set it in `LicenseAssist.setupLicense()`; otherwise the examples still run in evaluation mode.

---

## Build & run

### Maven

```bash
mvn -q -DskipTests=false test
```

Run a single class:

```bash
mvn -q -Dtest=CustomizingCaptionsExample test
```

Run a single method:

```bash
mvn -q -Dtest=CustomizingCaptionsExample#ean13_captionBelow_withPointSpace test
```

### Gradle

```bash
./gradlew test
```

With filters:

```bash
./gradlew test --tests "com.aspose.barcode.guide.generation.captions.CustomizingCaptionsExample.*"
```

---

## Output location

All images produced by tests are written under:

```
src/test/resources/<topic>/...
```

Each test asserts that its output file was created (non-empty) and then **recognizes** the generated code to verify type/text.

---

## Key conventions in tests

- **Deterministic rasterization:** explicit image width/height in pixels, X-Dimension in px or mm (with DPI), padding/quiet zones chosen to avoid clipping.
- **Isolation:** each test writes a distinct filename.

---

## `ExampleAssist` highlights

The helper includes a set of utilities used across tests:

- ### Assertions for recognition
    - `assertImageHasBarcodes(path, expectedCount, expectedList)`  
      Reads the image and verifies that the exact number of barcodes is present, then matches **type + text** (or **prefix**) for each expected entry.
    - **`expected(type, text)`** vs **`expectedPrefix(type, prefix)`**
        - Use `expected(...)` when the decoded **text must match exactly** (e.g., `"5901234123457"`).
        - Use `expectedPrefix(...)` when the engine may **prepend/append** standard data (e.g., GS1 AIs like `(01)` or UPC-E/ITF padding):
            - Example: for UPC-E, the engine may expand or display canonicalized text, so matching **prefix** avoids false negatives when the tail differs in a standard-compliant way.

- ### Checksum utilities
    - `expectedI25WithChecksum(payload)` — builds the **expected decoded text** for **Interleaved 2 of 5** with Mod10, adding a leading `0` when the length becomes odd (required by I-2/5).

- ### Image helpers
    - `invertColors(in, out)` — invert RGB, preserve alpha.
    - `downscaleNearest(...)` / `downscaleNearestCrisp(...)` — produce small but crisp barcodes for robustness tests.
    - `addGaussianNoise(...)`, `blur(...)` — degrade images to test recognition stability.

- ### Diagnostics
    - `printResultMetadata(result, prefix)` — dumps result type/text, confidence, region rectangle/quadrangle, and extended params (QR/DataMatrix/PDF417) when available.

---

## Checksums: enforced vs optional

- **Enforced (cannot be disabled)**  
  Code 93, Code 128, GS1 Code 128, EAN-8/13/14 (GTIN-14), UPC-A/E, ISBN, SSCC-18, SCC-14, etc.  
  The tests show that even if you attempt to disable checksum in parameters, the engine will still generate a valid symbol (and recognition will expect it).

- **Optional (can be toggled)**  
  Code 39 (Standard/Full ASCII), Standard/Interleaved/Matrix 2 of 5, IATA 2 of 5, Codabar, VIN, Deutsche Post Ident/Leitcode, OPC, PZN, etc.  
  Tests demonstrate both **ON** and **OFF** cases; some use reader-side `ChecksumValidation` to illustrate decoding behavior.

> Tip: When recognition fails for an optional checksum case, check **X-Dimension**, **bar height**, **quiet zones**, and **reader checksum mode**.

---

## Troubleshooting

- **“Unexpected number of barcodes”**  
  Increase image size, X-Dimension (e.g., 2–3 px), bar height (~80–120 px for 1D), and quiet zones (≥ 10× X-Dim for some symbologies).  
  Ensure `CodeLocation` does not collide with bars.

- **“Expected pair not found (by text)”**  
  Some readers return **normalized** text (e.g., GS1 with `(01)`, UPC-E expansion, or I-2/5 padding).  
  Use `expectedPrefix(...)` when appropriate.

- **Dark backgrounds / borders**  
  If you set a non-white background, ensure bar and border colors keep **sufficient contrast**.

---

## Contributing

- Keep tests **focused**: one concept per test with a clear Javadoc explaining *what it demonstrates* and *what is expected*.
- Follow naming and layout conventions used in current classes.
- Prefer **deterministic** generation (fixed pixel sizes, DPI when using mm/pt units).

---

## License

This repository contains **example code and tests** that rely on Aspose.BarCode for Java.  
Please review Aspose licensing terms for production use of the library.

---

## Support & Documentation

- Product page: <https://products.aspose.com/barcode/java>
- API reference & guides: https://reference.aspose.com/barcode/java/

---

**Happy barcoding!**
