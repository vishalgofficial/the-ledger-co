package com.ledger.co.cmd;

import java.io.*;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class FileUtil {

    public static List<String> getInputStreamFromFile() throws IOException {
        List<String> is;
        try (BufferedReader br = new BufferedReader(new FileReader(new File("src/main/resources/input.txt")))) {
            is = br.lines().collect(toList());
        }
        return is;
    }

    public static List<String> getInputStreamFromFileCmd(File file) throws IOException {
        List<String> is;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            is = br.lines().collect(toList());
        }
        return is;
    }
}
