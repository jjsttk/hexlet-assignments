package exercise;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.CompletableFuture;
import java.nio.file.Path;

class App {

    // BEGIN
    public static CompletableFuture<String> unionFiles(String firstSourceStringPath,
                                                       String secondSourceStringPath,
                                                       String resultFileStringPath) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                var firstFilePath = Path.of(firstSourceStringPath).toAbsolutePath();
                var secondFilePath = Path.of(secondSourceStringPath).toAbsolutePath();
                var resultFilePath = Path.of(resultFileStringPath).toAbsolutePath();

                Files.deleteIfExists(resultFilePath);

                Files.write(resultFilePath, Files.readAllBytes(firstFilePath));
                Files.write(resultFilePath, Files.readAllBytes(secondFilePath), StandardOpenOption.APPEND);

                return String.join("\n", Files.readAllLines(resultFilePath));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).exceptionally(ex -> {
            System.out.println("Ошибка: " + ex.getMessage());
            return "Ошибка при обработке файлов";
        });
    }

    // END

    public static void main(String[] args) throws Exception {
        // BEGIN
        String result = unionFiles("src/main/resources/file1.txt",
                "src/main/resources/file2.txt",
                "src/main/resources/result.txt").join();
        System.out.println("Результат объединения файлов:\n" + result);
        // END
    }
}

