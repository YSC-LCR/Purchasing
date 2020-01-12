package com.micb2b.purchasing.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.micb2b.purchasing.domain.BackendProdUnitInfo;
import com.micb2b.purchasing.dto.BackendProductUnitInfoDTO;
import com.micb2b.purchasing.mapper.BackendProdUnitInfoMapper;
import com.micb2b.purchasing.repository.BackendProdUnitInfoRepository;
import com.micb2b.purchasing.service.BackendProdUnitInfoService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class BackendProdUnitInfoServiceImpl implements BackendProdUnitInfoService {

	@Autowired
	private BackendProdUnitInfoRepository backendProdUnitInfoRepository;
	
	@Autowired
	private BackendProdUnitInfoMapper backendProdUnitInfoMapper;

	@Override
	public List<BackendProductUnitInfoDTO> findAllByUnit(Long unitId) {
		
		return backendProdUnitInfoMapper.toDto(backendProdUnitInfoRepository.findAllByUnit(unitId));
	}

	@Override
	public void create(BackendProdUnitInfo prodUnitInfo) {
		backendProdUnitInfoRepository.save(prodUnitInfo);
	}

	@Override
	public void update(BackendProdUnitInfo prodUnitInfo) {
		backendProdUnitInfoRepository.save(prodUnitInfo);
	}

	@Override
	public void delete(Long unitInfoId) {
		backendProdUnitInfoRepository.deleteById(unitInfoId);
	}
	
}
