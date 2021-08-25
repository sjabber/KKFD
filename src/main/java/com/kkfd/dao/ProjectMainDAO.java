package com.kkfd.dao;

import java.util.List;

import com.kkfd.dto.ProjectMainDTO;
import com.kkfd.exception.FindException;

public interface ProjectMainDAO {
	/**
	 * 
	 * @param main
	 * @return
	 * @throws FindException
	 */
	public List<ProjectMainDTO> mainProjs() throws FindException;
	
	/**
	 * 
	 * @param main
	 * @return
	 * @throws FindException
	 */
	public List<ProjectMainDTO> mainProjs(String id) throws FindException;
}
