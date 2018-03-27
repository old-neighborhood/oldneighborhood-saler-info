  
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
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONPObject;
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
	public String test1() {
		return "/SalerInfo";
	}
	
	@RequestMapping("/modify")
	@ResponseBody
	public String modify(String callback,HttpServletRequest request) {
		Map<String, String[]> req = request.getParameterMap();
		System.out.println(req.get("id")[0]+" "+
				req.get("name")[0]+" "+
				req.get("password")[0]+" "+
				req.get("tele")[0]+" "+
				req.get("email")[0]+" "+
				req.get("address")[0]+" "+
				req.get("signature")[0]);
		infoService.saveInfo(
				req.get("id")[0],
				req.get("name")[0],
				req.get("password")[0],
				req.get("tele")[0],
				req.get("email")[0],
				req.get("address")[0],
				req.get("signature")[0]
				);
		if(req.get("img")!=null) {
		String imgStr = req.get("img")[0];
		System.out.println("base64:"+imgStr);
		String path = "C:\\pc\\workspace\\oldneighborhood-validate\\src\\main\\resources\\img\\"+req.get("id")[0]+".jpg";
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
		return "success" ;
	}
	
	@RequestMapping("/info")
	@ResponseBody
	public Object getInfo(String callback,String key) {
		Saler saler = infoService.getInfo(key);
		System.out.println(saler.getS_date()+" "+saler.getS_password());
		JSONPObject jsonpObject = new JSONPObject();
		jsonpObject.setFunction(callback);
		jsonpObject.addParameter(saler);
		
		return jsonpObject ;
	}
}
