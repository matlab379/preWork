package com.cjx.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cjx.utils.DatabaseHelper;

public class WordInfoModel {

	private String word;
	private int maxTf;
	private int df;
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public int getMaxTf() {
		return maxTf;
	}
	public void setMaxTf(int maxTf) {
		this.maxTf = maxTf;
	}
	public int getDf() {
		return df;
	}
	public void setDf(int df) {
		this.df = df;
	}
	
	/**
	 * 根据结果集读取this并返回
	 * 
	 * @param rs
	 * @return
	 */
	public WordInfoModel read(ResultSet rs)
	{
		try {
			
			setWord(rs.getString("WORD"));
			setMaxTf(rs.getInt("MAX_TF"));
			setDf(rs.getInt("DF"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return this;
	}
	
	/**
	 * 将此记录写入数据库
	 * 
	 * @param con
	 */
	public void write(Connection con)
	{
		try {
			
			Statement sta = con.createStatement();
			String sql = "INSERT INTO t_word_info (WORD,MAX_TF,DF) VALUES (" +
					"'" + getWord() +
					"'," + getMaxTf() + 
					"," + getDf() +
					")";
			sta.execute(sql);
			sta.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void write()
	{
		try {
			Connection con = DatabaseHelper.getConnection();
			write(con);
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
