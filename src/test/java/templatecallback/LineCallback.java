package templatecallback;

@FunctionalInterface
public interface LineCallback {

    int doSomethingWithLine(String line, int value);
}
