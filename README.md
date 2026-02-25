# Text Extractor Plugin

A Joget plugin (WIP) for extracting text content from various file types using Apache Tika.

## Overview

The Text Extractor Plugin allows you to extract text and metadata from multiple document formats including PDF, Word documents, Excel spreadsheets, and more. It leverages Apache Tika's robust parsing capabilities to handle diverse file types.

## Features

- **Multi-format support**: Extract text from PDF, DOCX, DOC, XLSX, XLS, and many other file formats
- **Metadata extraction**: Retrieve document metadata during text extraction
- **File parsing**: Uses Apache Tika for automatic file type detection and parsing
- **Logging**: Detailed logging of extraction process and results

## Requirements

- Joget DX 8.2.0 or higher
- Java 11+

## Installation

1. Build the plugin: `mvn clean install`.

2. Upload the generated JAR file into your Joget.

## Usage

1. **Manually** upload below sample files from `text-extractor-plugin/src/main/resources/example-files` folder into Joget server `wflow/app_tempfile` folder:
    - example.pdf
    - example.xlsx
    - example.docx
    - example.pptx
    - example.jpg
    - example.png

2. Create a new **Tool** in **Process Builder**, choose **Text Extractor Plugin** in the **Tools** options.

3. Save changes then **Run Process**.

4. Check the extracted text content and metadata at **System Logs**.

## Way forward

Should use file upload feature instead of manual upload `example_document.pdf`.
Extend plugin for form element.

## License

This software is provided as-is without warranty. No liability is assumed for any damages resulting from the use of this code.