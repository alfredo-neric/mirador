package com.sistema.restaurant.mirador.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.restaurant.mirador.business.domain.TcStatus;
import com.sistema.restaurant.mirador.dao.TcStatusDAO;
import com.sistema.restaurant.mirador.service.TcStatusService;

@Service(value = "tcStatusService")
public class TcStatusServiceImpl implements TcStatusService {
	@Autowired
	private TcStatusDAO tcStatusDAO;

	public TcStatusDAO getTcStatusDAO() {
		return tcStatusDAO;
	}

	public void setTcStatusDAO(TcStatusDAO tcStatusDAO) {
		this.tcStatusDAO = tcStatusDAO;
	}

	@Override
	public List<TcStatus> save(TcStatus tcStatus) {
		return this.tcStatusDAO.save(tcStatus);
	}

	@Override
	public List<TcStatus> update(TcStatus tcStatus) {
		return this.tcStatusDAO.update(tcStatus);
	}

	@Override
	public List<TcStatus> delete(TcStatus tcStatus) {
		return this.tcStatusDAO.delete(tcStatus);
	}

	@Override
	public TcStatus add() {
		return this.tcStatusDAO.add();
	}

	@Override
	public List<TcStatus> orderBy() {
		return this.tcStatusDAO.orderBy();
	}

	@Override
	public Boolean validateExist(TcStatus tcStatus) {
		return this.tcStatusDAO.validateExist(tcStatus);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sistema.restaurant.mirador.service.TcStatusService#activeRowEdit(int)
	 */
	@Override
	public void activeRowEdit(int index) {
		this.tcStatusDAO.activeRowEdit(index);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sistema.restaurant.mirador.service.TcStatusService#getPage(java.util.
	 * List)
	 */
	@Override
	public Integer getPage(List<TcStatus> list) {
		return this.tcStatusDAO.getPage(list);
	}

	@Override
	public List<TcStatus> findStatus(String describe) {
		
		return this.tcStatusDAO.findStatus(describe);
	}

}
