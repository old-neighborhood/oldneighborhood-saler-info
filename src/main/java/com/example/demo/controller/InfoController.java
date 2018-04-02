  
    /**    
    * @Title: InfoController.java  
    * @Package com.example.demo.controller  
    * @Description: TODO(用一句话描述该文件做什么)  
    * @author 彭冲 
    * @date 2018年3月26日  
    * @version V1.0    
    */  
    
package com.example.demo.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.demo.entity.Saler;
import com.example.demo.service.InfoService;

import sun.misc.BASE64Decoder;

/**  
    * @ClassName: InfoController  
    * @Description: TODO(这里用一句话描述这个类的作用)  
    * @author 彭冲 
    * @date 2018年3月26日  
    *    
    */
@SuppressWarnings("restriction")
@Controller
public class InfoController {
	@Resource
	private InfoService infoService;
	
	@RequestMapping("/")
	@ResponseBody
	public String test1() {
		return "/SalerInfo";
	}
	
	@RequestMapping("/modify")
	@ResponseBody
	public String modify(@RequestBody Map<String, String> req) {
		System.out.println(req.get("id")+" "+
				req.get("name")+" "+
				req.get("password")+" "+
				req.get("tele")+" "+
				req.get("email")+" "+
				req.get("address")+" "+
				req.get("signature"));
		infoService.saveInfo(
				req.get("id"),
				req.get("name"),
				req.get("password"),
				req.get("tele"),
				req.get("email"),
				req.get("address"),
				req.get("signature")
				);
		if(req.get("img")!=null&&req.get("img").length()>1000) {
		String imgStr = req.get("img");
		System.out.println("base64:"+imgStr);
		String path = "C:\\pc\\workspace\\oldneighborhood-validate\\src\\main\\resources\\img\\"+req.get("id")+".jpg";
		imgStr = imgStr.replaceAll("data:image/jpeg;base64,", ""); 
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] b = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {
					b[i] += 256;
				}
			}
			System.out.println(path);
			 File imageFile = new File(path);
	            imageFile.createNewFile();
	               if(!imageFile.exists()){
	                   imageFile.createNewFile();
	                }
	                OutputStream imageStream = new FileOutputStream(imageFile);
	                imageStream.write(b);
	                imageStream.flush();
	                imageStream.close();           
			} catch (Exception e) {
			}
		}
		return "{\"result\":\"success\"}" ;
	}
	
	@RequestMapping("/info")
	@ResponseBody
	public Object getInfo(String s_ID) {
		System.out.println(s_ID);
		Saler saler = infoService.getInfo(s_ID);
		System.out.println(saler.getS_date()+" "+saler.getS_password());
		return saler;
	}
}
