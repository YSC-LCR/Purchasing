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

import com.micb2b.purchasing.domain.SysParam;
import com.micb2b.purchasing.dto.SysParamDTO;
import com.micb2b.purchasing.repository.SysParamRepository;
import com.micb2b.purchasing.utils.PageUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * @author M.K. Huang
 */
@Slf4j
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class SysParamQueryService {

	@Autowired
	private SysParamRepository sysParamRepository;


	/**
	 * 分頁
	 */
	public Object queryAll(String paramName, Pageable pageable) {
		Page<SysParam> page = sysParamRepository.findAll(new Spec(paramName), pageable);
		Page<SysParamDTO> dtoPage = page.map(sysParam -> this.converterEntityToDTO(sysParam));
		log.info("dtoPage:{}", dtoPage);
		return PageUtil.toPage(dtoPage);
	}

	private SysParamDTO converterEntityToDTO(SysParam entity) {
		SysParamDTO dto = new SysParamDTO();
		dto.setParamId(entity.getParamId());
		dto.setParamName(entity.getParamName());
		dto.setParamDesc(entity.getParamDesc());
		dto.setParamType(entity.getParamType());
		dto.setParamValue(entity.getParamValue());
		dto.setValueType(entity.getValueType());
		dto.setValueSize(entity.getValueSize());
		dto.setCreateBy(entity.getCreateBy());
		dto.setCreateDate(entity.getCreateDate());
		dto.setUpdateBy(entity.getUpdateBy());
		dto.setUpdateDate(entity.getUpdateDate());
		return dto;
	}

	class Spec implements Specification<SysParam> {

		private String paramName;

		public Spec(String paramName) {
			this.paramName = paramName;
		}

		@Override
		public Predicate toPredicate(Root<SysParam> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

			List<Predicate> list = new ArrayList<Predicate>();

			if (!ObjectUtils.isEmpty(paramName)) {
				/**
				 * 相等
				list.add(cb.equal(root.get("paramName").as(String.class), paramName));
				 */
				 /**
                 * 模糊
                 */
                list.add(cb.like(root.get("paramName").as(String.class), "%"+paramName+"%"));
			}

			Predicate[] p = new Predicate[list.size()];
			return cb.and(list.toArray(p));
		}
	}
}
