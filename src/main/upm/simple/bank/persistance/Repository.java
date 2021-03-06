package main.upm.simple.bank.persistance;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * @author akazmierczak
 * @create 14.11.2021
 */
public interface Repository<T> {

    T save(T o) throws Exception;

    String getAsALine(T o);

    T findById(Object o) throws IOException;
    List<T> findAll() throws IOException;
    void deleteById(Object o) throws IOException;

    default void replaceLines(String newLine, String toReplace, String fileName) throws Exception {
        try (BufferedReader bufferedReader = Files.newBufferedReader(Path.of(fileName))) {
            StringBuilder inputBuffer = new StringBuilder();
            String currentLine;

            while ((currentLine = bufferedReader.readLine()) != null) {

                if (toReplace.equals(currentLine)) {
                    currentLine = newLine;
                }

                inputBuffer.append(currentLine);
                inputBuffer.append('\n');
            }
            bufferedReader.close();

            // write the new string with the replaced line OVER the same file
            FileOutputStream fileOut = new FileOutputStream(fileName);
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();

        }
    }

    default void removeLine(String toRemove, String fileName) {
        try (BufferedReader bufferedReader = Files.newBufferedReader(Path.of(fileName))) {
            StringBuilder inputBuffer = new StringBuilder();
            String currentLine;

            while ((currentLine = bufferedReader.readLine()) != null) {

                if (!currentLine.trim().equals(toRemove)) {
                    inputBuffer.append(currentLine);
                    inputBuffer.append('\n');
                }
            }

            bufferedReader.close();

            // write the new string with the replaced line OVER the same file
            FileOutputStream fileOut = new FileOutputStream(fileName);
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();

        } catch (Exception e) {
            System.out.println("Problem reading file.");
        }
    }
}
