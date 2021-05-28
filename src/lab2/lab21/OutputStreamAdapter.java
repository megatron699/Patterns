package lab2.lab21;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

public class OutputStreamAdapter extends OutputStream {
    private final OutputStream outputStream;

    public OutputStreamAdapter(OutputStream outputStream){
        this.outputStream = outputStream;
    }

    public void write(String[] strings) throws IOException {
        Arrays.stream(strings).forEach(
                str -> {
                    try {
                        write((str + System.lineSeparator()).getBytes());
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                }
        );

        this.outputStream.flush();
    }


    @Override
    public void write(int b) throws IOException {
        outputStream.write(b);
    }
}
