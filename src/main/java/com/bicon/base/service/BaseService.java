package com.bicon.base.service;

import java.util.Map;

public interface BaseService {
	/**
	 *
	 * @brief
	 *		基础类扩展：分页查询方法（查询交易不涉及事务）
	 *		输入输出参数均为Map类型，支持复杂的统计及关联查询
	 *
	 * @details
	 * 		1、 根据mybatis SQL配置文件中namespace.sqlId及Map对象paramsMap进行查询
	 *			1.1、 fullSqlId组成： 【命名空间.SQL语句的ID】， 根据fullSqlId参数定位到具体执行的SQL语句
	 *			1.2、 通过预编译方式，将SQL中的传入参数替换成paramsMap中对应同名key的value
	 *
	 *		2、 返回List<Map<String, Object>>对象，返回结果中的键值与查询字段的别名一致
	 *
	 *		3、 查询统一使用<select id="xxx" parameterType="map" resultType="map"></select>标签,结果集类型map
	 *
	 * @author
	 *    - 2018-06-20  王统领  创建初始版本
	 *
	 * @param fullSqlId
	 * @param paramsMap
	 * @return
	 */
	public Object findByPage(String fullSqlId, Map<String, Object> paramsMap);

	/**
	 *
	 * @brief
	 *		基础类扩展：查询全部结果方法（查询交易不涉及事务）
	 *		输入输出参数均为Map类型，支持复杂的统计及关联查询
	 *
	 * @details
	 * 		1、 根据mybatis SQL配置文件中namespace.sqlId及Map对象paramsMap进行查询
	 *			1.1、 fullSqlId组成： 【命名空间.SQL语句的ID】， 根据fullSqlId参数定位到具体执行的SQL语句
	 *			1.2、 通过预编译方式，将SQL中的传入参数替换成paramsMap中对应同名key的value
	 *
	 *		2、 返回List<Map<String, Object>>对象，返回结果中的键值与查询字段的别名一致
	 *
	 *		3、 查询统一使用<select id="xxx" parameterType="map" resultType="map"></select>标签,结果集类型map
	 *
	 * @author
	 *    - 2018-06-20  王统领  创建初始版本
	 *
	 * @param fullSqlId
	 * @param paramsMap
	 * @return
	 */
	public Object findAll(String fullSqlId, Map<String, Object> paramsMap);

	/**
	 *
	 * @brief
	 *		基础类扩展：简单增、删、改方法（涉及事务由spring托管，独立事务业务可直接调用）
	 *		输入参数均为Map类型
	 *
	 * @details
	 * 		1、 根据mybatis SQL配置文件中namespace.sqlId及Map对象paramsMap进行查询
	 *			1.1、 fullSqlId组成： 【命名空间.SQL语句的ID】， 根据fullSqlId参数定位到具体执行的SQL语句
	 *			1.2、 通过预编译方式，将SQL中的传入参数替换成paramsMap中对应同名key的value
	 *
	 *		3、 查询统一使用<update id="yyy" parameterType="map"></update>标签
	 *
	 * @author
	 *    - 2018-06-20  王统领  创建初始版本
	 *
	 * @param fullSqlId
	 * @param paramsMap
	 */
	public void dealOne(String fullSqlId, Map<String, Object> paramsMap);

	public void dealMany(String fullSqlId, Map<String, Object> paramsMap);
}