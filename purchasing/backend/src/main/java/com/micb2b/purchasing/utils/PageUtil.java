package com.micb2b.purchasing.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

/**
 * 分頁工具
 * @author jie
 * @date 2018-12-10
 */
public class PageUtil extends cn.hutool.core.util.PageUtil {

    /**
     * List 分頁
     * @param page
     * @param size
     * @param list
     * @return
     */
    public static List toPage(int page, int size , List list) {
        int fromIndex = page * size;
        int toIndex = page * size + size;

        if(fromIndex > list.size()){
            return new ArrayList();
        } else if(toIndex >= list.size()) {
            return list.subList(fromIndex,list.size());
        } else {
            return list.subList(fromIndex,toIndex);
        }
    }

    /**
     * Page 資料處理，預防redis反序列化報錯
     * @param page
     * @return
     */
    public static Map toPage(Page page) {
        Map map = new HashMap();
        map.put("content",page.getContent());
        map.put("totalElements",page.getTotalElements());

        return map;
    }

    /**
     * @param object
     * @param totalElements
     * @return
     */
    public static Map toPage(Object object, Object totalElements) {
        Map map = new HashMap();

        map.put("content",object);
        map.put("totalElements",totalElements);

        return map;
    }
    
    public static Pageable getPageable(int page, int size, String sortable) {
		Pageable pageable = null;
		if(StringUtils.isNotBlank(sortable)) {
			String[] sortArray = sortable.split(",");
			Order order = null;
			List<Order> list = new ArrayList<Order>(); 
			for(int i = 0; i < sortArray.length; i+=2) {
				if("asc".equals(sortArray[i+1])) {
					order = new Order(Direction.ASC, sortArray[i]);
				}else {
					order = new Order(Direction.DESC, sortArray[i]);
				}
				list.add(order);
			}
			pageable = new PageRequest(page, size, new Sort(list));
		}else {
			pageable = new PageRequest(page, size, Sort.unsorted());
		}
		return pageable;
	}

}
