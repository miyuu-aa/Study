package com.example.demo.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.Getter;
import lombok.Setter;

/**
 * すべてのエンティティの基底となる抽象クラスです。
 * 
 * <p>ID管理、排他制御(Version)、エンティティの基本的なライフサイクルに
 * 関わる抽象メソッドを定義しています。</p>
 *
 * <ul>
 *   <li>共通ID採番</li>
 *   <li>楽観的ロック（Version管理）</li>
 *   <li>バリデーション</li>
 *   <li>REST返却時の循環参照防止</li>
 * </ul>
 *
 * @param <T> 自身を型として扱うためのジェネリクス(自己参照型)
 */
@EntityListeners(AuditingEntityListener.class)
@Setter
@Getter
@MappedSuperclass
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public abstract class AbstractStudyEntity<T> {

	/**
	 * 主キー。永続化時に自動採番されます。
	 * APIレスポンス時は読み取りのみ許可されます。
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(accessMode = AccessMode.READ_ONLY)
	protected int id;

	/**
	 * 楽観的ロック管理用バージョン番号。
	 * 更新ごとに自動インクリメントされます。
	 * APIレスポンス時は読み取りのみ許可されます。
	 */
	@Version
	@Schema(accessMode = AccessMode.READ_ONLY)
	protected long version;

	/**
	 * エンティティのバリデーションを実装するための抽象メソッドです。
	 * サブクラスで必要な検証処理を記述してください。
	 */
	public abstract void validate();

	/**
	 * レスポンス前にデータ整形を行う際に使用します。
	 * 必要に応じて関連情報の調整や不要データの除去を実装してください。
	 */
	public abstract void rewrite();

	/**
	 * 削除処理前に関連エンティティの参照を解除するなど、
	 * 整合性維持のための前処理を実装するための抽象メソッドです。
	 */
	public abstract void preRemove();

	/**
	 * 更新前に依存関係を整えるための処理を実装する抽象メソッドです。
	 *
	 * @param entity 更新後の内容を持つエンティティ
	 */
	public abstract void preUpdate(T entity);
}