package org.dao.Impl;

public class Main {

    public static void main(String[] args) {
        TrashcanDaoImpl trashcanDao = new TrashcanDaoImpl();
        System.out.println("-----------------------");
        trashcanDao.getMaxId();
        System.out.println("sdas"+trashcanDao.getMaxId());
        System.out.println("*****");
    }

}
