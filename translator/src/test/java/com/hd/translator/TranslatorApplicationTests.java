package com.hd.translator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hd.translator.infra.PapagoTranslator;
import com.hd.translator.vo.ReqTranslatorVO;

@SpringBootTest
class TranslatorApplicationTests {

	@Autowired
	private PapagoTranslator papagoTranslator;

	@Test
	public void test() throws Exception {
		String source = "ko";
		String target = "en";
		String text = "관문,  관문  관문은   관문이다";
		ReqTranslatorVO param = new ReqTranslatorVO();
		param.setSource(source);
		param.setText(text);
		param.setTarget(target);
		String result = papagoTranslator.getTranslator(param);
	}


	@Test
	public void test2() throws Exception {
		String str = "a  b  c";
		String strr = "a b c";

		String str2 = str.replaceAll("  ", " ");

		System.out.println("str2 = " + str2);
	}

}
