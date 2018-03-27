  
    /**    
    * @Title: InfoServiceImpl.java  
    * @Package com.example.demo.service.Impl  
    * @Description: TODO(用一句话描述该文件做什么)  
    * @author 彭冲 
    * @date 2018年3月26日  
    * @version V1.0    
    */  
    
package com.example.demo.service.Impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.example.demo.dao.SalerDao;
import com.example.demo.entity.Saler;
import com.example.demo.service.InfoService;

/**  
    * @ClassName: InfoServiceImpl  
    * @Description: TODO(这里用一句话描述这个类的作用)  
    * @author 彭冲 
    * @date 2018年3月26日  
    *    
    */
@Service("InfoService")
public class InfoServiceImpl implements InfoService {

	/* (非 Javadoc)  
	*   
	*   
	* @param key
	* @return  
	* @see com.example.demo.service.InfoService#getInfo(java.lang.String)  
	*/
	@Resource
	private SalerDao salerDao;
	@Override
	public Saler getInfo(String key) {
		Saler saler=salerDao.findOne(key);
		return saler;
	}
	@Override
	public void saveInfo(String s_ID, String s_name, String s_password, String s_tele, String s_email, String s_address,
			String s_signature) {
		salerDao.update(s_name,s_password,s_tele,s_email,s_address,s_signature,s_ID);
	}

}
