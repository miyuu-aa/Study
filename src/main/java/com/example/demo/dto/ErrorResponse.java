package com.example.demo.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * API エラー応答を表現する DTO クラス。
 * クライアントへ返すエラーメッセージと、
 * 発生時刻（システム日付）を保持する。
 * 
 * 使用例:
 * <pre>
 * return ResponseEntity
 *          .status(HttpStatus.BAD_REQUEST)
 *          .body(new ErrorResponse("Invalid Request", LocalDate.now()));
 * </pre>
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    /** エラーメッセージ */
    private String message;

    /** エラー発生時刻 */
    private LocalDate timestamp;
}