package com.example.demo.exception;

import com.example.demo.dto.ErrorResponse;

import lombok.Getter;

/**
 * 業務例外 (BusinessException) を表すクラス。
 * 検証違反やドメインルール違反など、
 * 業務上想定された例外発生時に使用する。
 * 
 * ErrorResponse を保持し、
 * コントローラ層などで例外ハンドリングに利用できる。
 */
@Getter
public class BusinessException extends RuntimeException {

    /** クライアントへ返却するエラー情報 */
    private final ErrorResponse response;

    /**
     * BusinessException の新規作成。
     *
     * @param response エラー応答情報
     */
    public BusinessException(ErrorResponse response) {
        super(response.getMessage());
        this.response = response;
    }
}