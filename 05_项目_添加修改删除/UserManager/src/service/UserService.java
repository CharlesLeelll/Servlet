package service;

import java.sql.ResultSet;

import dao.DBConnection;

public class UserService {
	DBConnection db=new DBConnection();
	//添加
	public int insert(String[] param) {
		String sql="insert into user(name,password,sex,age,address) values(?,?,?,?,?)";
		db.doSQL(sql,param);
		int i=db.getUpCount();
		return i;
	}
	//修改
	public int update(String[] param) {
		String sql="update user set name=?,password=?,sex=?,age=?,address=? where id=?";
		db.doSQL(sql,param);
		int i=db.getUpCount();
		return i;
	}
	//删除
	public int delete(String[] param) {
		String sql="delete from user where id=?";
		db.doSQL(sql,param);
		int i=db.getUpCount();
		return i;
	}
	//查询
	public void select(String[] param) throws Exception{
		String sql="select * from user";
		db.doSQL(sql,param);
		ResultSet rs=db.getRS();
		if (rs!=null) {//含有集合对象: 空集合 或 含有数据
			rs.last();//将指针移动到末行
			int rowNum=rs.getRow();//获得当前行的行号
			if (rowNum>0) {//至少含有1条数据
				rs.beforeFirst();
				while (rs.next()) {//将指针向下移动一行,若含有数据将返回true,否则返回false
					//根据表字段名,获得当前行的指定字段的值
					System.out.println("id:"+rs.getString("id"));
					System.out.println("name:"+rs.getString("name"));
					System.out.println("sex:"+rs.getString("sex"));
				}
			} else {//空集合
				System.out.println("对比起,没有您要查询的数据.....");
			}
		} else {
			System.out.println("查询异常....");
		}
	}
}
