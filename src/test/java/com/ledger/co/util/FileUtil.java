package com.ledger.co.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ledger.co.controller.LoanController;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileUtil {

    public static List<?> buildMockRequest(String inputFileName) {
        List<Object> mockRequests = new ArrayList<>();
        try {
            ClassLoader classLoader = LoanController.class.getClassLoader();
            URL fileUrl = classLoader.getResource(inputFileName);
            if (fileUrl != null) {
                File inputFile = new File(fileUrl.getFile());
                String inputJson = FileUtils.readFileToString(inputFile, StandardCharsets.UTF_8);
                ObjectMapper mapper = new ObjectMapper();
                mapper.registerModule(new JavaTimeModule());
                mockRequests = Arrays.asList(mapper.readValue(inputJson, Object[].class));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mockRequests;
    }
}
