package com.arraytask.service;
import com.arraytask.exception.FileOperationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileReaderService {
    public List<String> readLinesFromFile(String relativePath) throws FileOperationException{
        Path filePath = Paths.get(relativePath);
        BufferedReader reader = null;
        try {
            reader = Files.newBufferedReader(filePath);
            List<String> lines = new ArrayList<>();
            String currentLine;
            while ((currentLine = reader.readLine()) !=null){
                lines.add(currentLine);
            }
            return lines;
        } catch (IOException e){
            throw new FileOperationException("Ошибка чтения файла", e);
        } finally {
            if (reader !=null){
                try {
                    reader.close();
                } catch (IOException e){

                }
            }
        }
    }
}
