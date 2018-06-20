package com.bicon.base.controller;

import com.bicon.base.service.BaseService;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SuperController {
	private final Logger LOG = Logger.getLogger(SuperController.class);

	private final static String PARAMS_KEY_PAGE_NO = "currentPage";

	private final static String PARAMS_KEY_PAGE_LIMIT = "pageSize";

	private final static String PARAMS_KEY_START = "start";

	private final static int PAGE_LIMIT_INIT_VALUE = 10;

	@Resource(name="BaseServiceImpl")
	protected BaseService service;

	/**
	 *
	 * @brief
	 * 		将request请求封装为Map<String, Object>对象的通用方法
	 * @details
	 * 		特别说明：对分页参数进行了整型转换（仅分页查询时才执行）
	 * @author
	 *    - 2016-10-08  Kingleading  创建初始版本
	 * @param request
	 * @return 返回request请求对应的Map<String, Object>对象
	 */
	protected static Map<String, Object> getParameterMap(HttpServletRequest request) {
		Map<String, String[]> properties = request.getParameterMap();
		Map<String, Object> returnMap = new HashMap<String, Object>();
		Iterator entries = properties.entrySet().iterator();
		Map.Entry entry;
		String name = "";
		String value = null;
		while (entries.hasNext()) {
			entry = (Map.Entry) entries.next();
			name = (String) entry.getKey();
			Object valueObj = entry.getValue();
			if (null == valueObj) {
				value = null;
			} else if (valueObj instanceof String[]) {
				String[] values = (String[]) valueObj;
				for (int i = 0; i < values.length; i++) {
					value = values[i] + ",";
				}
				value = value.substring(0, value.length() - 1);
			} else {
				value = valueObj.toString();
			}
			returnMap.put(name, value);
		}

		// 对request里面的参数进行加工，使空字符串参数转换为null
		Iterator it = returnMap.keySet().iterator();
		while(it.hasNext()){
			Object key = it.next();
			if(returnMap.get(key) == null || "".equals(((String)returnMap.get(key)).trim())){
				returnMap.put((String) key, null);
			}
		}

		// 如果是分页查询，对request里面的参数进行加工，使分页空字符串参数转换为整型数字
		if(request.getRequestURI().contains("/findByPage/")){
			int limit = PAGE_LIMIT_INIT_VALUE;
			if (!StringUtils.isEmpty((String) returnMap.get(PARAMS_KEY_PAGE_LIMIT))) {
				try {
					limit = Integer.parseInt((String) returnMap.get(PARAMS_KEY_PAGE_LIMIT));
					if(limit < 1){
						limit = PAGE_LIMIT_INIT_VALUE;
					}
				} catch (RuntimeException e) {
					limit = PAGE_LIMIT_INIT_VALUE;
				}
			}
			returnMap.put(PARAMS_KEY_PAGE_LIMIT, new Integer(limit));


			int pageNo = 1;
			if (!StringUtils.isEmpty((String) returnMap.get(PARAMS_KEY_PAGE_NO))) {
				try {
					pageNo = Integer.parseInt((String) returnMap.get(PARAMS_KEY_PAGE_NO));
					if(pageNo < 1){
						pageNo = 1;
					}
				} catch (RuntimeException e) {
					pageNo = 1;
				}
			}
			returnMap.put(PARAMS_KEY_PAGE_NO, new Integer(pageNo));
			returnMap.put(PARAMS_KEY_START, new Integer((pageNo - 1) * limit));
		}

		return returnMap;
	}


	/*protected Map<String, Object> getParams(HttpServletRequest request){
		Map<String,Object> paramMap = new HashMap<>();
		Set<String> keys = request.getParameterMap().keySet();
		for(String key:  keys) {
			paramMap.put(key, request.getParameter(key));
		}

		// 如果是分页查询，对request里面的参数进行加工，使分页空字符串参数转换为整型数字
		if(request.getRequestURI().contains("/findByPage/")){
			int limit = PAGE_LIMIT_INIT_VALUE;
			if (!StringUtils.isEmpty((String) paramMap.get(PARAMS_KEY_PAGE_LIMIT))) {
				try {
					limit = Integer.parseInt((String) paramMap.get(PARAMS_KEY_PAGE_LIMIT));
					if(limit < 1){
						limit = PAGE_LIMIT_INIT_VALUE;
					}
				} catch (RuntimeException e) {
					limit = PAGE_LIMIT_INIT_VALUE;
				}
			}
			paramMap.put(PARAMS_KEY_PAGE_LIMIT, new Integer(limit));

			int pageNo = 1;
			if (!StringUtils.isEmpty((String) paramMap.get(PARAMS_KEY_PAGE_NO))) {
				try {
					pageNo = Integer.parseInt((String) paramMap.get(PARAMS_KEY_PAGE_NO));
					if(pageNo < 1){
						pageNo = 1;
					}
				} catch (RuntimeException e) {
					pageNo = 1;
				}
			}
			paramMap.put(PARAMS_KEY_PAGE_NO, new Integer(pageNo));
			paramMap.put(PARAMS_KEY_START, new Integer((pageNo - 1) * limit));
		}
		return paramMap;
	}*/
}