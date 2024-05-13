package templatecallback;

import java.io.BufferedReader;
import java.io.IOException;

@FunctionalInterface
public interface BufferedReaderCallback {

    int doSomethingWithReader(BufferedReader br) throws IOException;
}
