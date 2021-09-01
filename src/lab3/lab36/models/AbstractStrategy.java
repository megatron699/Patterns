package lab3.lab36.models;

import lab3.lab36.interfaces.Strategy;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public abstract class AbstractStrategy implements Strategy {
    @Override
    public void checkAverageStudent(String inputNameFile, String outputNameFile) throws Exception {
        Student student = readFile(inputNameFile);
        student.checkAverage();
        writeFile(outputNameFile, student);
    }

    protected abstract Student readFile(String inputNameFile) throws ParserConfigurationException, IOException, SAXException;

    protected abstract void writeFile(String outputNameFile, Student student) throws ParserConfigurationException, TransformerException;

}
