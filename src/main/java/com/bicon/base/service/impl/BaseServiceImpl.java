package com.bicon.base.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.bicon.base.service.BaseService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

@Service("BaseServiceImpl")
public class BaseServiceImpl implements BaseService {
	@Resource
	protected SqlSession sqlSession;

	@Override
	public Object findByPage(String fullSqlId, Map<String, Object> paramsMap) {
		int currentPage = (int)paramsMap.get("currentPage");
		int pageSize = (int)paramsMap.get("pageSize");
		Page<Map> page = PageHelper.startPage(currentPage, pageSize);
		sqlSession.selectList(fullSqlId, paramsMap);
		PageInfo pageInfo = page.toPageInfo();
		return pageInfo;
	}

	@Override
	public Object findAll(String fullSqlId, Map<String, Object> paramsMap) {
		return sqlSession.selectList(fullSqlId, paramsMap);
	}

	@Override
	public void dealOne(String fullSqlId, Map<String, Object> paramsMap) {
		sqlSession.selectList(fullSqlId, paramsMap);
	}

	@Override
	@Transactional
	public void dealMany(String fullSqlId, Map<String, Object> paramsMap) {
		sqlSession.selectList(fullSqlId, paramsMap);
		sqlSession.selectList(fullSqlId, paramsMap);
		int a = 1 / 0;
		sqlSession.selectList(fullSqlId, paramsMap);
		sqlSession.selectList(fullSqlId, paramsMap);
	}
}
