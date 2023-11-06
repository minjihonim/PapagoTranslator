package com.hd.translator.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.hd.translator.service.TranslatorService;
import com.hd.translator.vo.ReqTranslatorVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/translator")
@RequiredArgsConstructor
public class TranslatorController {

	private final Logger LOGGER = LoggerFactory.getLogger(TranslatorController.class);

	private final TranslatorService translatorService;

	/**
	 * 번역기 실행 ( 파파고, 구글, 플리토 )
	 * @throws RuntimeException
	 */
	@PostMapping("/getPapago")
	@ResponseBody
	public String getTranslator(@RequestBody ReqTranslatorVO param) throws Exception {

		String result = translatorService.getTranslator(param);

		return result;
	}

	@GetMapping("/test")
	@ResponseBody
	public String test() {
		String a = "abc";

		return a;
	}
}
