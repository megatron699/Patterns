package lab2.lab21;

import java.io.InputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputStreamAdapter extends InputStream{
    private final InputStream inputStream;

    public InputStreamAdapter(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String[] readString() throws IOException {
        List<Integer> listByte = new ArrayList<>();
        int byteElement = inputStream.read();

        while (byteElement != -1) {
            listByte.add(byteElement);
            byteElement = inputStream.read();
        }

        byte[] bytes = new byte[listByte.size()];
        for (int i = 0; i < listByte.size(); i++) {
            bytes[i] = (byte) (int) listByte.get(i);
        }

        return new String(bytes).split(System.lineSeparator());
    }

    @Override
    public int read() throws IOException {
        return inputStream.read();
    }

}
