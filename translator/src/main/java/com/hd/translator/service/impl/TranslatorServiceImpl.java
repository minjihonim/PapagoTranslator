package com.hd.translator.service.impl;

import org.springframework.stereotype.Service;

import com.hd.translator.infra.PapagoTranslator;
import com.hd.translator.service.TranslatorService;
import com.hd.translator.vo.ReqTranslatorVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TranslatorServiceImpl implements TranslatorService {

	private final PapagoTranslator papagoTranslator;

	@Override
	public String getTranslator(ReqTranslatorVO param) throws Exception {

		String resultPapago = papagoTranslator.getTranslator(param);

		return resultPapago;
	}
}
