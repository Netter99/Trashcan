package org.dao.Impl;

import org.dao.ITrashcanDao;
import org.entity.Account;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import org.dao.ITrashcanDao;
import org.entity.Location;

import org.entity.AccountInformation;
import org.entity.Location;
import org.util.DBUtil;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TrashcanDaoImpl implements ITrashcanDao {
    /*
     * Dao的所有方法都是无逻辑的，直接执行，不进行判断
     * 在Service层，进行对Dao方法的调用与判断
     */

    /* 疑问：
     *
     * 需不需要所有返回值为true/false的方法，都按照int值作为返回结果，来分三种情况
     * 即：成功、失败、出错
     */
//	@Override//注册
//	public boolean Register(Account account) {
//		ResultSet rs = null;
//		try {
//			String sql = "insert into account values(?,?)";
//			Object[] params = {account.getAname(),account.getPwd()};
//			return DBUtil.executeUpdate(sql, params);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}finally {
//			DBUtil.closeAll(rs, null, null);
//		}
//	}

//	@Override//登陆
//	public int Login(String username,String password) {
//		ResultSet rs = null;
//		int status = 0;
//		try {
//			String sql = "select * from account where aname=? and pwd=?";
//			Object[] params = {username,password};
//			rs = DBUtil.executeQuery(sql, params);
//			if(rs != null){
//				if(rs.next()) {
//					status = 1;
//				}
//			}
//			return status;
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return -1;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return -1;
//		}finally {
//			DBUtil.closeAll(rs, null, null);
//		}
//	}

    private String appid = "wx3dc7c45448433288";
    private String appsecret = "faa5399b82ce6bf43e2f99e58ea6c5b8";

    @Override
    public boolean addOpenId(int id, String openid) {
        boolean success = false;
        try {
            String sql = "insert into user_login(id,openid) values(id=?,openid=?)";
            Object[] params = {id, openid};
            return DBUtil.executeUpdate(sql, params);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.closeAll(null, null, null);
        }
    }

    @Override
    public boolean addAccountNP(int id, String name, String pwd) {
        boolean success = false;
        try {
            String sql = "insert into user_login(username,password) values(username=?,password=?) where id=?";
            Object[] params = {id, name, pwd};
            return DBUtil.executeUpdate(sql, params);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.closeAll(null, null, null);
        }
    }

    @Override
    public boolean changePwd(int id, String pwd) {
        boolean success = false;
        try {
            String sql = "update user_login set password=? where id=?";
            Object[] params = {pwd,id};
            return DBUtil.executeUpdate(sql, params);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.closeAll(null, null, null);
        }
    }

    @Override
    public boolean isOpwdCorrect(int id, String opwd) {
        ResultSet rs = null;
        try {
            String sql = "select count(*) from user_login where id=? and password=?";
            Object[] params = {id,opwd};
            rs = DBUtil.executeQuery(sql, params);
            if (rs != null) {
                return true;
            }else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.closeAll(rs, null, null);
        }
    }

    @Override
    public int getId(String openid) {
        ResultSet rs = null;
        int id = -1;
        try {
            String sql = "select id from user_login where openid=?";
            Object[] params = {openid};
            rs = DBUtil.executeQuery(sql, params);
            if (rs != null) {
                rs.next();
                id = rs.getInt("id");
            }
            return id;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        } finally {
            DBUtil.closeAll(rs, null, null);
        }
    }

    @Override
    public int getMaxId() {
        int id = -1;
        ResultSet rs = null;
        try {
            String sql = "select max(id) id from user_login";
            rs = DBUtil.executeQuery(sql, null);
            if (rs != null) {
                rs.next();
                id = rs.getInt("id");
            }
            System.out.println("id:" + id);
            return id;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        } finally {
            DBUtil.closeAll(rs, null, null);
        }
    }

    @Override
    public String getOpenId(String code) {
        String openid = null;
        OkHttpClient client = new OkHttpClient();
        String url = "https://api.weixin.qq.com/sns/jscode2session";

        Request.Builder reqBuild = new Request.Builder();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();

        urlBuilder.addQueryParameter("appid", appid);
        urlBuilder.addQueryParameter("secret", appsecret);
        urlBuilder.addQueryParameter("js_code", code);
        urlBuilder.addQueryParameter("grant_type", "authorization_code");
        reqBuild.url(urlBuilder.build());
        Request request = reqBuild.build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
            String text = response.body().string();
            JSONObject jsonObject = JSONObject.parseObject(text);
            System.out.println("------" + jsonObject.toJSONString());
            openid = jsonObject.getString("openid");
            System.out.println(openid);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return openid;
    }

    @Override
    public boolean isOpenIdExist(String code) {
        ResultSet rs = null;
        boolean isexist = false;
        try {
            String tempcode = getOpenId(code);
            String sql = "select count(1) from user_login where openid=?";
            Object[] params = {tempcode};
            rs = DBUtil.executeQuery(sql, params);
            if (rs != null) {
                isexist = true;
            }
            return isexist;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.closeAll(rs, null, null);
        }
    }

    @Override//查看用户名是否存在
    public boolean isNameExist(String name) {
        ResultSet rs = null;
        boolean isexist = false;
        try {
            String sql = "select count(1) from user_login where username=?";
            Object[] params = {name};
            rs = DBUtil.executeQuery(sql, params);
            if (rs != null) {
                isexist = true;
            }
            return isexist;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.closeAll(rs, null, null);
        }
    }

//	@Override//返回账户信息
//	public AccountInformation queryAccountByAname(String name) {
//		ResultSet rs = null;
//		AccountInformation ainfo = null;
//		try {
//			String sql = "select * from Information where aname=?";
//			Object[] params = {name};
//			rs = DBUtil.executeQuery(sql, params);
//			if(rs != null){
//				String aname = name;
//				ainfo.setname(name);
//			}
//			return ainfo;
//		}catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}finally {
//			DBUtil.closeAll(rs, null, null);
//		}
//	}
//
//	@Override//设置账户信息
//	public boolean setAccountInformation(AccountInformation info) {
//		boolean success = false;
//		AccountInformation ainfo = null;
//		try {
//			String aname = info.getAname();
//			String sno = info.getsno();
//			String sex =  info.getSex();
//			String school = info.getSchool();
//			String major = info.getMajor();
//			String sclass = info.getSclass();
//			String sql = "insert into Information values(?,?,?,?,?,?)";
//			Object[] params = {aname,sno,sex,school,major,sclass};
//			success = DBUtil.executeUpdate(sql,params);
//			return success;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}finally {
//			DBUtil.closeAll(null, null, null);
//		}
//	}

//    @Override//查询积分
//    public int getAccountCredit(int id) {
//        ResultSet rs = null;
//        int score = 0;
//        try {
//            String sql = "select score from credit where id=?";
//            Object[] params = {id};
//            rs = DBUtil.executeQuery(sql, params);
//            if (rs != null) {
//                if (rs.next()) {
//                    score = rs.getInt("score");
//                }
//            }
//            return score;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return -1;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return -1;
//        } finally {
//            DBUtil.closeAll(rs, null, null);
//        }
//    }
//
//    @Override//增加积分
//    public int addAcoountCredit(String name, int increasement) {
//        int credit = -1;
//        boolean success = false;
//        try {
//            String sql = "update credit set credit=credit+? where name=?";
//            Object[] params = {increasement, name};
//            success = DBUtil.executeUpdate(sql, params);
//            if (success) {
//                credit = getAccountCredit(name) + increasement;
//            }
//            return credit;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return -1;
//        } finally {
//            DBUtil.closeAll(null, null, null);
//        }
//    }

    @Override//查询投放次数
    public int getAccountThrowtime(int id) {
        ResultSet rs = null;
        int times = -1;
        try {
            String sql = "select launch_time from user_score where id=?";
            Object[] parms = {id};
            rs = DBUtil.executeQuery(sql, parms);
            if (rs != null) {
                if (rs.next()) {
                    times = rs.getInt("launch_time");
                }
            }
            return times;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        } finally {
            DBUtil.closeAll(rs, null, null);
        }
    }

    @Override//增加投放次数
    public boolean addAccountThrowtime(int id) {
        boolean success = false;
        try {
            String sql = "update user_score set launch_time=launch_time+1 where id=?";
            Object[] params = {id};
            return DBUtil.executeUpdate(sql, params);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.closeAll(null, null, null);
        }
    }

    @Override//获取坐标
    public List<Location> getAllLocation() {
        ResultSet rs = null;
        List<Location> locations = null;
        try {
            String sql = "select longitude,latitude from can_infor";
            rs = DBUtil.executeQuery(sql, null);
            if (rs != null) {
                while (rs.next()) {
                    String lo = rs.getString("longitude");
                    String la = rs.getString("latitude");
                    Location location = new Location(lo, la);
                    locations.add(location);
                }
            }
            return locations;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.closeAll(rs, null, null);
        }
    }

    @Override//兑换商品
    public boolean changeGoods(int id,int score) {

        try {
            String sql = "update user_score set score=score-? where id=?";
            Object[] params = {score, id};
            return DBUtil.executeUpdate(sql, params);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.closeAll(null, null, null);
        }
    }


}
