package com.example.demo.controller.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMappingJacksonResponseBodyAdvice;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 返却されるレスポンスボディをログ出力するためのアドバイスクラス。
 *
 * <p>
 * {@link RestControllerAdvice} と {@link AbstractMappingJacksonResponseBodyAdvice} を利用し、
 * すべてのレスポンスボディを JSON 形式でログに記録する。
 * </p>
 *
 * <p>
 * 例：API の戻り値オブジェクトを {@link ObjectMapper} を通じて JSON に変換して出力。
 * 開発時のデバッグや監査ログに有効。
 * </p>
 */
@RestControllerAdvice
public class ResponseBodyAdvice extends AbstractMappingJacksonResponseBodyAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ResponseBodyAdvice.class);

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * レスポンスボディを書き込む前に呼ばれる。
     *
     * <p>
     * JSON にシリアライズしてログ出力する。
     * エラーが発生した場合は警告ログを残す。
     * </p>
     *
     * @param bodyContainer レスポンスボディを格納する {@link MappingJacksonValue}
     * @param contentType コンテンツタイプ
     * @param returnType コントローラの戻り値型
     * @param request HTTPリクエスト情報
     * @param response HTTPレスポンス情報
     */
    @Override
    protected void beforeBodyWriteInternal(MappingJacksonValue bodyContainer, MediaType contentType,
            MethodParameter returnType, ServerHttpRequest request, ServerHttpResponse response) {

        System.out.println("*****Rstart*****");

        Object body = bodyContainer.getValue();

        try {
            String json = objectMapper.writeValueAsString(body);
            logger.info("ResponseBody:{}", json);
        } catch (Exception e) {
            logger.warn("Failed to log response body", e);
        }

        System.out.println("*****Rend*****");
    }

}