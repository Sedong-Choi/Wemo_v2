package com.wemo.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wemo.domain.Calendarbean;

@Repository
public class CalendarDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;

	public int insert(Calendarbean calendarbean){
		return sqlSession.insert("Cal.insert", calendarbean);	
	}

	public int update(Calendarbean calendarbean) {
		return sqlSession.update("Cal.update", calendarbean);
	}

	public int delete(Calendarbean calendarbean) {
		return sqlSession.delete("Cal.delete", calendarbean);
	}

	public int REupdate(Calendarbean calendarbean) {
		return sqlSession.update("Cal.update", calendarbean);
	}

	public int DGupdate(Calendarbean calendarbean) {
		return sqlSession.update("Cal.update", calendarbean);
	}

	public int getListCount(Calendarbean calendarbean) {
		return sqlSession.selectOne("Cal.count", calendarbean);
	}

	public List<Calendarbean> getcalendarList(Calendarbean calendarbean) {
		return sqlSession.selectList("Cal.list", calendarbean);
	}

}
