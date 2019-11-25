package com.micb2b.purchasing.service;

import java.util.Map;

import com.micb2b.purchasing.domain.SysParam;

/**
 * @author M.K. Huang
 */
public interface SysParamService {
	public void delete(SysParam sysParam);
	public void create(SysParam sysParam);
	public void update(SysParam sysParam);
	public SysParam findById(String paramId);

}
