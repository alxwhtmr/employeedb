package com.github.alxwhtmr.employeedb;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created on 31.03.2015.
 */
public class Initializer {
    public static String[] initStringsFromFile(String category) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(Constants.CONFIG_FILE));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (!line.equalsIgnoreCase(category)) {
                line = br.readLine();
            }
            if (line.equalsIgnoreCase(category)) {
                line = br.readLine().trim();
                while (line != null) {
                    if (line.contains("#")) break;
                    sb.append(line);
                    sb.append("\n");
                    line = br.readLine();
                }
            }
            br.close();
            return sb.toString().split("\n");
        } catch (FileNotFoundException e) {
            e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
