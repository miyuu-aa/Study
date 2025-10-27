package com.example.demo.controller.advice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.dto.ErrorResponse;
import com.example.demo.exception.BusinessException;

import lombok.extern.slf4j.Slf4j;

/**
 * アプリケーション全体の例外をハンドリングするグローバル例外クラス。
 *
 * <p>
 * {@link BusinessException} や Spring 標準例外を捕捉し、
 * 統一された {@link ErrorResponse} 形式でクライアントへ返却します。
 * ログ出力も組み込み、デバッグ時に詳細な情報を取得可能です。
 * </p>
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * 業務例外 (BusinessException) を処理。
     *
     * <p>
     * 発生した例外に保持されている {@link ErrorResponse} をそのまま返却します。
     * HTTPステータスは 400 (Bad Request)。
     * </p>
     *
     * @param e BusinessException
     * @return ErrorResponse クラスの JSON
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBusinessException(BusinessException e) {
        log.warn("BusinessException occurred: {}", e.getResponse().getMessage());
        return e.getResponse();
    }

    /**
     * Spring 内部の例外をカスタマイズして処理。
     *
     * <p>
     * デフォルトでは {@link ResponseEntityExceptionHandler#handleExceptionInternal} を呼び出す。
     * デバッグや開発環境でのログ出力に便利。
     * </p>
     *
     * @param ex 例外
     * @param body レスポンスボディ
     * @param headers HTTPヘッダ
     * @param statusCode HTTPステータスコード
     * @param request Webリクエスト情報
     * @return ResponseEntity オブジェクト
     */
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, Object body, HttpHeaders headers,
            HttpStatusCode statusCode, WebRequest request) {

        if (statusCode == HttpStatus.BAD_REQUEST) {
            log.warn("BAD_REQUEST", ex);
        }
        log.trace("trace log example");
        log.debug("debug log example");
        log.info("info log example");
        log.warn("warn log example");
        log.error("error log example");

        return super.handleExceptionInternal(ex, body, headers, statusCode, request);
    }
}