package com.github.tddiaz.service.csv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Created by tristandiaz on 11/4/17.
 */
@Component
public class CSVFileParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadServiceImpl.class);

    public Iterable<CSVRecord> parse(MultipartFile file) throws IOException {

        Reader reader = null;

        try {
            reader = new InputStreamReader(file.getInputStream());
            return CSVFormat.RFC4180.withFirstRecordAsHeader().parse(reader);

        } catch (IOException e) {
            throw new IOException(e);
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                LOGGER.error("Error closing the File Reader.");
            }
        }
    }
}
