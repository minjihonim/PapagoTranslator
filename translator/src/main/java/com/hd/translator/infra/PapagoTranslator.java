package com.hd.translator.infra;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.hd.translator.vo.ReqTranslatorVO;

import lombok.RequiredArgsConstructor;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Component
@RequiredArgsConstructor
public class PapagoTranslator implements TranslatorClient {

	private final Logger LOGGER = LoggerFactory.getLogger(PapagoTranslator.class);

	private static final int MAX_IDLE_CONN = 10;

	private static final int KEEEP_ALIVE = 5;

	private static final int CONNECT_TO = 5;

	private static final int READ_TO = 10;

	private static final String test_BASE_URL = "https://openapi.naver.com/v1/papago/";
	private static final String BASE_URL = "https://naveropenapi.apigw.ntruss.com/nmt/v1/";

	// Test-version
//	private static final String clientId = "S0QZyjNjOWxp302OBVpC";
	private static final String clientId = "";

	// Test-version
//	private static final String clientSecret = "XuG1Hn0PJ6";
	private static final String clientSecret = "";

	private static final String glossaryKey = "53da3b8d-2e0a-4893-a0da-45b6bad67a25";

	private PapagoApi papagoApi;

	@PostConstruct
	protected void createApiClient() {

		HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor((msg) -> {
			LOGGER.info(msg);
		});
		loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

		ConnectionPool pool = new ConnectionPool(MAX_IDLE_CONN, KEEEP_ALIVE, TimeUnit.MINUTES);
		OkHttpClient client = new OkHttpClient.Builder().addInterceptor(loggingInterceptor) // 인터셉터
				.connectionPool(pool) // 커넥션풀
				.readTimeout(READ_TO, TimeUnit.SECONDS).connectTimeout(CONNECT_TO, TimeUnit.SECONDS).build();

		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(BASE_URL)
				.addConverterFactory(GsonConverterFactory.create())
				.client(client)
				.build();

		papagoApi = retrofit.create(PapagoApi.class);
	}

	/**
	 * 파파고 번역 요청
	 * @param param
	 * @return String
	 * @throws Exception
	 */
	@Override
	public String getTranslator(ReqTranslatorVO param) throws Exception {

		String convertMessage = param.getText().replaceAll("  ", " ");
		Response<JsonObject> response = papagoApi.getPapagoTranslator(clientId, clientSecret, param.getSource(),
				param.getTarget(), convertMessage, glossaryKey).execute();

		JsonObject jsonObject = response.body();

		JsonElement translatedText = jsonObject.get("message")
				.getAsJsonObject().get("result")
				.getAsJsonObject().get("translatedText");

		if (response.isSuccessful()) {
			return String.valueOf(translatedText);
		}

		throw new RuntimeException();
	}
}
