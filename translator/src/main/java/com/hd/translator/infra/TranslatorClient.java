package com.hd.translator.infra;

import com.hd.translator.vo.ReqTranslatorVO;

public interface TranslatorClient {

	/**
	 * 파파고 번역 요청 매서드
	 */

	String getTranslator(ReqTranslatorVO param) throws Exception;

}
