package org.service.Impl;

import org.dao.ITrashcanDao;
import org.dao.Impl.TrashcanDaoImpl;

public class ChangeGoodsService {
    public boolean changeGoods(int id,int score){
        ITrashcanDao trashcanDao = new TrashcanDaoImpl();
        return trashcanDao.changeGoods(id,score);
    }
}
