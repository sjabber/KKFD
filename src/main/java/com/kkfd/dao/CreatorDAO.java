package com.kkfd.dao;

import com.kkfd.exception.ModifyException;

public interface CreatorDAO {
	
	public int updateProj(int projNo, String loginId) throws ModifyException;
}
