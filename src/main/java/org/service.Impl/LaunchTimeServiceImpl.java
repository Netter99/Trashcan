package org.service.Impl;

import org.dao.ITrashcanDao;
import org.dao.Impl.TrashcanDaoImpl;
import org.service.LaunchTimeService;

public class LaunchTimeServiceImpl implements LaunchTimeService {
    ITrashcanDao trashcanDao = (ITrashcanDao) new TrashcanDaoImpl();
    @Override
    public int getAccountThrowtime(int id) {
        return trashcanDao.getAccountThrowtime(id);
    }

    @Override
    public boolean addAccountThrowtime(int id) {
        return trashcanDao.addAccountThrowtime(id);
    }
}
