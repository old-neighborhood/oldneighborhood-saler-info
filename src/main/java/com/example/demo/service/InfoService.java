package com.example.demo.service;

import com.example.demo.entity.Saler;

public interface InfoService {
	public Saler getInfo(String key);

	public void saveInfo(
			String s_ID,
			String s_name,
			String s_password,
			String s_tele,
			String s_email,
			String s_address,
			String s_signature
			);
}
