package service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.DBConnection;

public class UserService {
	DBConnection db=new DBConnection();
	//添加
	public int insert(String[] param) {
		String sql="insert into user(name,password,sex,age,address) values(?,?,?,?,?)";
		db.doSQL(sql,param);
		int i=db.getUpCount();
		db.getClose();
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
	public List<Map<String,String>> select(String sql,String[] param) throws Exception{
		db.doSQL(sql,param);
		ResultSet rs=db.getRS();
		List<Map<String,String>> list=null;
		if (rs!=null) {//含有集合对象: 空集合 或 含有数据
			rs.last();//将指针移动到末行
			int rowNum=rs.getRow();//获得当前行的行号
			if (rowNum>0) {//至少含有1条数据
				list=new ArrayList<Map<String,String>>();//创建大容器
				rs.beforeFirst();
				
				while (rs.next()) {//将指针向下移动一行,若含有数据将返回true,否则返回false
					//根据表字段名,获得当前行的指定字段的值
					Map<String,String> map=new HashMap<String,String>();//创建小容器: 当前行的数据
					map.put("id", rs.getString("id"));
					map.put("name", rs.getString("name"));
					map.put("sex", rs.getString("sex"));
					map.put("age", rs.getString("age"));
					map.put("password", rs.getString("password"));
					map.put("address", rs.getString("address"));
					list.add(map);
				}
				
			} 
			
		} else {
			System.out.println("查询异常....");
		}
		return list;
	}
}
