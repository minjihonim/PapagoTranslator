package com.hd.translator.infra;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.*;

/**
 * 파파고 번역 연동 API
 */
public interface PapagoApi {

	/**
	 * 파파고 번역 요청 결과 조회 - Test Version
	 */

	@Headers({ "Accept: application/json", "Content-Type:application/x-www-form-urlencoded", "charset: UTF-8"})
	@FormUrlEncoded
	@POST("n2mt")
	Call<JsonObject> getPapagoTranslatorTest(@Header("X-Naver-Client-Id") String clientId,
			@Header("X-Naver-Client-Secret") String clientSecret,
			@Field(value = "source") String source,
			@Field(value = "target") String target,
			@Field(value = "text") String text);


	/**
	 * 파파고 번역 요청 결과 조회 - 실제모델 (Naver Cloud)
	 */
	@Headers({ "Accept: application/json", "Content-Type:application/x-www-form-urlencoded", "charset: UTF-8"})
	@FormUrlEncoded
	@POST("translation")
	Call<JsonObject> getPapagoTranslator(@Header("X-NCP-APIGW-API-KEY-ID") String clientId,
			@Header("X-NCP-APIGW-API-KEY") String clientSecret,
			@Field(value = "source") String source,
			@Field(value = "target") String target,
			@Field(value = "text") String text,
			@Field(value = "glossaryKey") String glossaryKey);
}
