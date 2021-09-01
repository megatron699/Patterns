package lab3.lab36.models;

public class Subject {
    private String subjectName;
    private int mark;

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) throws Exception {
        if (mark > 5 || mark < 1) {
            throw new Exception("Оценка меньше 1 или больше 5");
        }

        this.mark = mark;
    }

    public Subject() {
    }

    public Subject(String title, int mark) {
        this.subjectName = title;
        this.mark = mark;
    }

}
