package com.example.json.flow.loader;

import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class FileLoader {

    public static String getJsonString(String filename) throws IOException {
        InputStream resource = new ClassPathResource(
                filename).getInputStream();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(resource))) {
            return reader.lines()
                    .collect(Collectors.joining("\n"));
        }
    }
}
