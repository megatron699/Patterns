package lab3.lab36.models;


import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class StudentHandler extends DefaultHandler {
    private Student student;
    private boolean isAverage = false;

    public Student getStudent() {
        return student;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {

        if (qName.equalsIgnoreCase("student")) {
            student = new Student();
            student.setLastName(attributes.getValue("lastname"));

            return;
        }

        if (qName.equalsIgnoreCase("subject")) {
            Subject subject = new Subject();
            subject.setSubjectName(attributes.getValue("title"));
            try {
                subject.setMark(Integer.parseInt(attributes.getValue("mark")));
            } catch (Exception e) {
                e.printStackTrace();
            }

            student.addSubject(subject);

            return;
        }

        if (qName.equalsIgnoreCase("average")) {
            isAverage = true;
        }
    }

    @Override
    public void characters(char ch[], int start, int length) {
        if (!isAverage) {
            return;
        }

        String average = new String(ch, start, length);
        student.setAverage(Double.parseDouble(average));
        isAverage = false;
    }

}
