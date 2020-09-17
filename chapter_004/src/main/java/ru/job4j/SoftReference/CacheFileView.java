package ru.job4j.SoftReference;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class CacheFileView {

    public static void main(String[] args) throws IOException {
        Cache cache = new Cache(Sources.getFileSource());
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filePath = "chapter_004\\cachedFiles\\";
        while (true) {
            System.out.print("Enter file name or type \"exit\": ");
            String fileName = reader.readLine();
            if ("exit".equals(fileName)) {
                reader.close();
                break;
            }
            List<String> content = cache.getCachedFile(filePath + fileName);
            content.forEach(System.out::println);
        }
    }
}
