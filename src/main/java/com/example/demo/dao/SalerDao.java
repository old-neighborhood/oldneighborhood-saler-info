  
    /**    
    * @Title: SalerDao.java  
    * @Package com.example.demo.dao  
    * @Description: TODO(用一句话描述该文件做什么)  
    * @author 彭冲 
    * @date 2018年3月26日  
    * @version V1.0    
    */  
    
package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Saler;

/**  
    * @ClassName: SalerDao  
    * @Description: TODO(这里用一句话描述这个类的作用)  
    * @author 彭冲 
    * @date 2018年3月26日  
    *    
    */

public interface SalerDao extends JpaRepository<Saler, String> {
	
	@Transactional
	@Modifying
	@Query(value="update saler as s set s.s_name=?, s.s_password=?,s.s_tele=?,s.s_email=?,s.s_address=?,s.s_signature=? where s.s_ID=?" ,
			nativeQuery=true)
	void update(String s_name, String s_password, String s_tele, String s_email, String s_address, String s_signature,
			String s_ID);

}
