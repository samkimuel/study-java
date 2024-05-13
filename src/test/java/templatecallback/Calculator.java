package templatecallback;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.BiFunction;

public class Calculator {

    public int calcSum(String filePath) throws IOException {
        return lineReadTemplate(
                filePath,
                (line, value) -> value + Integer.parseInt(line),
                0
        );
    }

    public int calcMultiply(String filePath) throws IOException {
        return lineReadTemplate(
                filePath,
                (line, value) -> value * Integer.parseInt(line),
                1
        );
    }

    public int fileReadTemplate(String filePath, BufferedReaderCallback callback) throws IOException {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filePath));
            return callback.doSomethingWithReader(br);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public int lineReadTemplate(String filePath, BiFunction<String, Integer, Integer> lineCallback, int initVal) throws IOException {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filePath));
            int res = initVal;
            String line;
            while ((line = br.readLine()) != null) {
                res = lineCallback.apply(line, res);
            }
            return res;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
