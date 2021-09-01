package lab3.lab36.models;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DomStrategy extends AbstractStrategy {
    private Document document;

    @Override
    protected Student readFile(String inputNameFile) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        document = documentBuilder.parse(inputNameFile);

        Element student = (Element) document.getElementsByTagName("student").item(0);
        NodeList subjects = student.getElementsByTagName("subject");

        return new Student(
                student.getAttribute("lastname"),
                IntStream.range(0, subjects.getLength())
                        .mapToObj(el -> {
                            Element element = (Element) subjects.item(el);
                            return new Subject(
                                    element.getAttribute("title"),
                                    Integer.parseInt(element.getAttribute("mark")
                                    )
                            );
                        }).collect(Collectors.toCollection(ArrayList::new)),
                Double.parseDouble(student.getElementsByTagName("average").item(0).getTextContent())
        );
    }

    @Override
    protected void writeFile(String outputNameFile, Student student) throws TransformerException {
        ((Element) document.getElementsByTagName("student").item(0))
                .getElementsByTagName("average").item(0)
                .setTextContent(String.valueOf(student.getAverage()));

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(new DOMSource(document), new StreamResult(outputNameFile));
    }

}
