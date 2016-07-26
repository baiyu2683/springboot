package com.trs.ui;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value ="/crawlpointmonitor")
public class DocumentSearchEntry {

	@RequestMapping(value="/list")
	public String list(){
		return "zhangheng";
	}

	@RequestMapping(value="/delete")
	public List<String> delete(){
		List<String> list = new ArrayList<>();
		list.add("asdfasdf");
		list.add("azcgvzxcvz");
		return list;
	}
}
