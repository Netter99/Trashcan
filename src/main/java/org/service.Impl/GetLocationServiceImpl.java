package org.service.Impl;

import org.dao.ITrashcanDao;
import org.dao.Impl.TrashcanDaoImpl;
import org.entity.Location;

import java.util.List;

public class GetLocationServiceImpl {
    ITrashcanDao trashcanDao = new TrashcanDaoImpl();
    public List<Location> getAllLocation(){
        return trashcanDao.getAllLocation();
    }
}
