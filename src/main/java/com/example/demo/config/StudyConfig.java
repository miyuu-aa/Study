package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate6.Hibernate6Module;

/**
 * アプリケーション全体の共通設定を行うクラスです。
 *
 * <p>特に JSON シリアライズ時に Hibernate の遅延読み込みや
 * 循環参照が原因で発生する問題を回避するために
 * {@link Hibernate6Module} を ObjectMapper に登録しています。</p>
 */
@Configuration
public class StudyConfig {

    /**
     * JSON シリアライズ設定をカスタマイズした {@link ObjectMapper} を生成します。
     *
     * <p>
     * 遅延ロードプロキシの扱いなど Hibernate 特有のデータ形式を
     * 適切にシリアライズできるよう設定しています。
     * </p>
     *
     * @param jackson2ObjectMapperBuilder Spring 提供の ObjectMapper ビルダー
     * @return カスタム設定が追加された ObjectMapper
     */
    @Bean
    ObjectMapper objectMapper(Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder) {
        return jackson2ObjectMapperBuilder.build()
                .registerModule(
                        // Hibernate6用モジュールを有効化
                        new Hibernate6Module().configure(
                                Hibernate6Module.Feature.USE_TRANSIENT_ANNOTATION,
                                false // @Transient を持つフィールドの扱いを変更
                        )
                );
    }
}