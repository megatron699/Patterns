package lab4.lab42.models;

import lab4.lab42.services.FileSerializableUtils;

public class SerializableDao extends AbstractDao{
    public SerializableDao() {
        fileName = "SerializableDao.txt";
        fileService = new FileSerializableUtils(fileName);
    }
}
