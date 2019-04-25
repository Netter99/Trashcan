package org.service.Impl;

import org.dao.ITrashcanDao;
import org.dao.Impl.TrashcanDaoImpl;
import org.service.LoginService;

public class LoginServiceImpl implements LoginService {
    private ITrashcanDao trashcanDao = new TrashcanDaoImpl();


    @Override
    public boolean addOpenId(String code) {
        String openid = trashcanDao.getOpenId(code);
        if (openid != null) {
            return trashcanDao.addOpenId(openid);
        }
        return false;
    }

    @Override
    public boolean Register(int id, String name, String pwd) {
        if (trashcanDao.isNameExist(name) == false) {
            return trashcanDao.addAccountNP(id, name, pwd);
        }
        return false;
    }

//    @Override
//    public boolean firstLogin(String openid) {
//        if(isOpenIdExist(openid) == false){
//            return true;
//        }else {
//            return false;
//        }
//    }

    @Override
    public boolean isOpenIdExist(String openid) {
        return trashcanDao.isOpenIdExist(openid);
    }

    @Override
    public boolean iUserNameExist(String name) {
        return trashcanDao.isNameExist(name);
    }

    @Override
    public String getOpenId(String code) {
        return trashcanDao.getOpenId(code);
    }

    @Override
    public int getId(String openid) {
        return trashcanDao.getId(openid);
    }

    @Override
    public int getMaxId() {
        return trashcanDao.getMaxId();
    }

    @Override
    public boolean changePwd(int id, String pwd) {
        return trashcanDao.changePwd(id, pwd);
    }

    @Override
    public boolean isOpwdCorrect(int id, String opwd) {
        return trashcanDao.isOpwdCorrect(id, opwd);
    }


}
