package com.micb2b.purchasing.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.micb2b.purchasing.aop.Log;
import com.micb2b.purchasing.domain.SysParam;
import com.micb2b.purchasing.repository.SysParamRepository;
import com.micb2b.purchasing.service.SysParamService;


/**
 * @author M.K. Huangs
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class SysparamServiceImpl implements SysParamService {

	@Autowired
	private SysParamRepository sysParamRepository;

	@Override
	@Log("系統參數-新增參數")
	public void create(SysParam sysParam) {
		sysParamRepository.save(sysParam);
	}

	@Override
	public SysParam findById(String paramId) {
		Optional<SysParam> optional = sysParamRepository.findById(paramId);
		return (optional.isPresent()) ? optional.get() : null;
	}

	@Override
	@Log("系統參數-刪除參數")
	@Transactional(rollbackFor = Exception.class)
	public void delete(SysParam sysParam) {
		sysParamRepository.delete(sysParam);
	}

	@Override
	@Log("系統參數-修改參數")
	public void update(SysParam sysParam) {
		sysParamRepository.saveAndFlush(sysParam);
	}

}
