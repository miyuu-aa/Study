package com.example.demo.controller.advice;

import java.lang.reflect.Type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 受信リクエストのボディをログに記録するためのアドバイスクラス。
 *
 * <p>
 * {@link RestControllerAdvice} と {@link RequestBodyAdviceAdapter} を利用し、
 * すべての {@code @RequestBody} に対してログ出力を行う。
 * </p>
 *
 * <p>
 * 例：JSON形式のリクエストボディを {@link org.slf4j.Logger} を通して出力。
 * </p>
 */
@RestControllerAdvice
public class RequestBodyAdvice extends RequestBodyAdviceAdapter {

	private static final Logger logger = LoggerFactory.getLogger(RequestBodyAdvice.class);

	@Autowired
	private ObjectMapper objectMapper;

	/**
	 * すべてのリクエストボディをサポートする。
	 *
	 * @param methodParameter メソッドパラメータ
	 * @param targetType 対象の型
	 * @param converterType 使用される HttpMessageConverter のクラス
	 * @return 常に {@code true} を返す（すべてのリクエストボディに対応）
	 */
	@Override
	public boolean supports(MethodParameter methodParameter, Type targetType,
			Class<? extends HttpMessageConverter<?>> converterType) {
		return true;
	}

	/**
	 * リクエストボディ読み取り後に呼ばれる。
	 *
	 * <p>
	 * ObjectMapper を利用してボディを JSON 文字列に変換し、
	 * ログに出力する。変換失敗時は警告ログを出力。
	 * </p>
	 *
	 * @param body リクエストボディのオブジェクト
	 * @param inputMessage HTTP入力メッセージ
	 * @param parameter メソッドパラメータ
	 * @param targetType 対象型
	 * @param converterType 使用される HttpMessageConverter のクラス
	 * @return オリジナルの body をそのまま返す
	 */
	@Override
	public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
			Class<? extends HttpMessageConverter<?>> converterType) {
		try {
			String json = objectMapper.writeValueAsString(body);
			logger.info("RequestBody:{}", json);
		} catch (Exception e) {
			logger.warn("Failed to log request body", e);
		}
		return body;
	}

}