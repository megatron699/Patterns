package lab4.lab42.models;

import lab4.lab42.services.FileUtils;

public class TextDao extends AbstractDao {
    public TextDao() {
        fileName = "TextDao.txt";
        fileService = new FileUtils(fileName);
    }
}
