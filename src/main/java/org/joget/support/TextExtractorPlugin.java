package org.joget.support;

import org.joget.plugin.base.DefaultApplicationPlugin;
import org.joget.apps.app.service.AppUtil;
import org.joget.commons.util.FileManager;
import org.joget.commons.util.LogUtil;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.BodyContentHandler;
import org.apache.tika.io.TikaInputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;

public class TextExtractorPlugin extends DefaultApplicationPlugin {

    @Override
    public String getName() {
        return "TextExtractorPlugin";
    }

    @Override
    public String getVersion() {
        return "8.2.0";
    }

    @Override
    public String getDescription() {
        return "Extract text content from various file types supported by Apache Tika";
    }

    public String getLabel() {
        return "Text Extractor Plugin";
    }

    public String getPropertyOptions() {
        return AppUtil.readPluginResource(getClass().getName(), "/properties.json", null, true, null);
    }

    @Override
    public final Object execute(Map props) {
        // TODO: get file uploaded into app resources via properties
        // LogUtil.info("File: ", getPropertyString("file"));

        // WARNING: must upload file manually into wflow/app_tempfile folder with exact file name as below
        String[] filePaths = {
            "example.pdf",
            "example.xlsx",
            "example.docx",
            "example.pptx",
            // WARNING: for images, make sure to install package. example: `apt install tesseract-ocr`
            "example.jpg",
            "example.png",
        };

        for (String path : filePaths) {
            File file = FileManager.getFileByPath(path);

            if (!file.exists()) {
                LogUtil.warn("File not found", path);
                continue;
            }
            
            extractText(file);
        }

        return null;
    }

    private void extractText(File file) {
        LogUtil.info("Attempting to extract text from file", file.getName());

        BodyContentHandler handler = new BodyContentHandler(-1);
        Metadata metadata = new Metadata();
        AutoDetectParser parser = new AutoDetectParser();

        try (InputStream stream = TikaInputStream.get(file)) {
            parser.parse(stream, handler, metadata);
            
            for (String name : metadata.names()) {
                LogUtil.info("Metadata" + name, metadata.get(name));
            }

            LogUtil.info("Contents", handler.toString());
            
        } catch (Exception e) {
            LogUtil.error(getClass().getName(), e, "Failed to extract text from file");
        }
    }
}
