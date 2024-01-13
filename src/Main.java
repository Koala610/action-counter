import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import static java.lang.System.lineSeparator;
import static java.lang.System.out;

public class Main {

    private static final ActionStorageRepository actionStorageRepository = new DefaultActionStorageMapRepository();
    private static final ActionCounter actionCounter = new DefaultActionCounter(actionStorageRepository);
    private static final String OUTPUT_TEMPLATE ="Counter: %s";

    public static void main(String[] args) {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out))) {
            actionCounter.call(1);
            actionCounter.call(2);
            actionCounter.call(2);

            showActionCountInTerminal(writer, 4);

            actionCounter.call(300);

            showActionCountInTerminal(writer, 300);
            showActionCountInTerminal(writer, 301);
        } catch (IOException ignored) {
        }
    }

    private static void showActionCountInTerminal(Writer writer, int timestamp) throws IOException {
        writer.write(String.format(OUTPUT_TEMPLATE, actionCounter.getActions(timestamp)));
        writer.write(lineSeparator());
    }
}