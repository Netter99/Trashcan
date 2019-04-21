package org.service.Impl;

import java.util.List;

import org.dao.ITrashcanDao;
import org.dao.Impl.TrashcanDaoImpl;
import org.entity.Account;
import org.entity.Location;
import org.service.ITrashcanService;

public class TrashcanServiceImpl implements ITrashcanService{
	ITrashcanDao trashcanDao = new TrashcanDaoImpl();

	@Override
	public boolean Register(Account account) {
//		if(trashcanDao.isExist(account.getAname())) {
//			return false;
//		}else {
////			return trashcanDao.Register(account);
//			return true;
//		}
        return  false;
	}

	@Override
	public int Login(Account account) {
//		return trashcanDao.Login(account);
		return 1;
	}

	@Override
	public int getAccountCredit(Account account) {
		return 0;
	}

	@Override
	public void addAcoountCredit(String name,int increasement) {
		trashcanDao.addAcoountCredit(name, increasement);
	}

	@Override
	public List<Location> queryAllLocation() {
		return trashcanDao.queryAllLocation();
	}

	@Override
	public int getId(String openid) {
		return 0;
	}


}
