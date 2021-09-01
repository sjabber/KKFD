package com.kkfd.dao;

import com.kkfd.dto.CreatorDTO;
import com.kkfd.exception.AddException;
import com.kkfd.exception.FindException;
import com.kkfd.exception.ModifyException;

public interface CreatorDAO {
	
	public CreatorDTO selectCrById(String loginId) throws FindException;
	
	public void updateCr(CreatorDTO creator) throws ModifyException;
	
	public void insertCr(CreatorDTO creator) throws AddException;
}
