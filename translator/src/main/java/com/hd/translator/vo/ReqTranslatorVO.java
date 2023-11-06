package com.hd.translator.vo;

import lombok.Data;

@Data
public class ReqTranslatorVO {

	/** 원본 언어 */
	private String source;
	/** 번역할 언어 */
	private String target;
	/** 번역 내용 */
	private String text;
}
