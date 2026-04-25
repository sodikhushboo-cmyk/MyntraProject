package com.skillo.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GoogleSheetReader {

    public static Object[][] readFromSheet(String sheetUrl) {

        List<Object[]> data = new ArrayList<>();

        try {
            URL url = new URL(sheetUrl);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;
            boolean isHeader = true;

            while ((line = br.readLine()) != null) {

                if (isHeader) { // skip header
                    isHeader = false;
                    continue;
                }

                String[] values = line.split(",");
                data.add(values);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data.toArray(new Object[0][]);
    }
}