package com.bicon.base.controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
//@Scope("prototype")
public class BaseController extends SuperController{
	private final Logger LOG = Logger.getLogger(BaseController.class);
	/**
	 * @brief
	 *      通用【分页查询】操作（支持单表及多表关联查询）
	 *      根据指定的sqlId定位mybatis执行sql语句，根据对应查询参数值通过预编译方式完成分页查询操作并返回查询结果的json
	 * @author
	 *    - 2018-06-20  Kingleading  创建初始版本
	 * @param business	        执行sqlMapper文件的第一层命名空间
	 * @param table           执行sqlMapper文件的第二层命名空间--业务表名称
	 * @param sqlId           sqlMapper文件中执行sql的ID
	 * @param req
	 * @return      返回map对象自动转换为json格式
	 */
	@RequestMapping(value="/base/findByPage/{business}/{table}/{sqlId}")
	public Object findByPage(@PathVariable String business, @PathVariable String table, @PathVariable String sqlId, HttpServletRequest req) {
		Map<String, Object> ret = new HashMap<String, Object>();
		String fullSqlId = business + "." + table + "." + sqlId;
		try{
			Map<String, Object> paramsMap = getParameterMap(req);
			Object retObj = service.findByPage(fullSqlId, paramsMap);
			ret.put("success", "00000000");
			ret.put("retMsg", "处理成功");
			ret.put("data", retObj);
			System.out.println("common findByPage: " + fullSqlId + " 处理成功:" + paramsMap);
			LOG.info("common findByPage: " + fullSqlId + " 处理成功:" + paramsMap);
		}catch (Exception e){
			e.printStackTrace();
			LOG.error("common findByPage: " + fullSqlId + " 处理失败", e);
			ret.put("success", "-1");
			ret.put("retMsg", "处理失败");
		}finally {
			return ret;
		}
	}

	/**
	 * @brief
	 *      通用【查询全部】操作（支持单表及多表关联查询）
	 *      根据指定的sqlId定位mybatis执行sql语句，根据对应查询参数值通过预编译方式完成分页查询操作并返回查询结果的json
	 * @author
	 *    - 2018-06-20  Kingleading  创建初始版本
	 * @param business	        执行sqlMapper文件的第一层命名空间
	 * @param table           执行sqlMapper文件的第二层命名空间--业务表名称
	 * @param sqlId           sqlMapper文件中执行sql的ID
	 * @param req
	 * @return      返回map对象自动转换为json格式
	 */
	@RequestMapping(value="/base/findAll/{business}/{table}/{sqlId}")
	public Object findAll(@PathVariable String business, @PathVariable String table, @PathVariable String sqlId, HttpServletRequest req) {
		Map<String, Object> ret = new HashMap<String, Object>();
		String fullSqlId = business + "." + table + "." + sqlId;
		try{
			Map<String, Object> paramsMap = getParameterMap(req);
			Object retObj = service.findAll(fullSqlId, paramsMap);
			ret.put("success", "00000000");
			ret.put("retMsg", "处理成功");
			ret.put("data", retObj);
			System.out.println("common findAll: " + fullSqlId + " 处理成功:" + paramsMap + " data: " + retObj );
			LOG.info("common findAll: " + fullSqlId + " 处理成功:" + paramsMap + " data: " + retObj );
		}catch (Exception e){
			e.printStackTrace();
			LOG.error("findAll: " + fullSqlId + " 处理失败", e);
			ret.put("success", "-1");
			ret.put("retMsg", "处理失败");
		}finally {
			return ret;
		}
	}

	/**
	 * @brief
	 *      通用【单表增、删、改】等独立事务操作方法的action(涉及多步事务的需要自己写Service进行控制)
	 *      根据指定的sqlId定位mybatis执行sql语句，根据对应参数值通过预编译方式进行增删改操作
	 * @author
	 *    - 2016-11-11  Kingleading  创建初始版本
	 * @param business          执行sqlMapper文件的第一层命名空间
	 * @param table             执行sqlMapper文件的第二层命名空间
	 * @param sqlId             sqlMapper文件中执行sql的ID
	 * @param req
	 */
	@RequestMapping(value="/base/deal/{business}/{table}/{sqlId}")
	public Object dealOne(@PathVariable String business, @PathVariable String table, @PathVariable String sqlId, HttpServletRequest req) {
		Map<String, String> ret = new HashMap<String, String>();
		String fullSqlId = business + "." + table + "." + sqlId;
		try{
			Map<String, Object> paramsMap = getParameterMap(req);
			service.dealOne(fullSqlId, paramsMap);
			ret.put("success", "00000000");
			ret.put("retMsg", "处理成功");
			System.out.println("common dealOne: " + fullSqlId + " 处理成功:" + paramsMap);
			LOG.info("common dealOne: " + fullSqlId + " 处理成功:" + paramsMap);
		}catch (Exception e){
			e.printStackTrace();
			LOG.error("dealOne: " + fullSqlId + " 处理失败", e);
			ret.put("success", "-1");
			ret.put("retMsg", "处理失败");
		}finally {
			return ret;
		}
	}
}