package com.micb2b.purchasing.service.query;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.micb2b.purchasing.domain.BackendProdCategory;
import com.micb2b.purchasing.domain.BackendProdUnit;
import com.micb2b.purchasing.mapper.BackendProdUnitMapper;
import com.micb2b.purchasing.repository.BackendProdUnitRepository;
import com.micb2b.purchasing.utils.PageUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * @author JayYang
 */

@Service
@Slf4j
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class BackendProdUnitQueryService {

	@Autowired
	private BackendProdUnitRepository backendProdUnitRepository;

	@Autowired
	private BackendProdUnitMapper backendProdUnitMapper;

	/**
	 * 分頁
	 */
//	public Object queryAll(String column,String columnValue,String[] deptIds,Pageable pageable) {
	public Object queryAll(String columnValue,Pageable pageable) {
//		Page<BackendProdCodeView> page = backendProdViewRepository.findAll(new Spec(column,columnValue,deptIds),pageable);
		Page<BackendProdUnit> page = backendProdUnitRepository.findAll(new Spec(columnValue),pageable);
		return PageUtil.toPage(page.map(backendProdUnitMapper::toDto));
	}

	class Spec implements Specification<BackendProdUnit> {

//		private String column;
//		
		private String columnValue;
//		
//		private String[] deptIds;

//		public Spec(String column ,String columnValue,String[] deptIds) {
		public Spec(String columnValue) {
//			this.column = column;
			this.columnValue = columnValue;
//			this.deptIds = deptIds;
		}

		@Override
		public Predicate toPredicate(Root<BackendProdUnit> root, CriteriaQuery<?> criteriaQuery,
				CriteriaBuilder cb) {

			List<Predicate> list = new ArrayList<Predicate>();
			
			list.add(cb.equal(root.get("deleteFlag").as(String.class), "N"));

//			if (!ObjectUtils.isEmpty(deptIds)) {
//				
//				/**
//				 * 相等
//				 */
//				
//				Expression<String> exp = root.get("deptId");
//				list.add(exp.in(deptIds));
//			}
			if ((!ObjectUtils.isEmpty(columnValue))) {
				/**
				 * 模糊
				 */

				list.add(cb.like(root.get("name").as(String.class), "%" + columnValue + "%"));

			}

			Predicate[] p = new Predicate[list.size()];
			return cb.and(list.toArray(p));
		}
	}

}
