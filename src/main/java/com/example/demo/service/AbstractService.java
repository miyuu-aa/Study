package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.ErrorResponse;
import com.example.demo.entity.AbstractStudyEntity;
import com.example.demo.exception.BusinessException;
import com.example.demo.repository.AbstractStudyRepository;

/**
 * 汎用的なサービス処理を提供する抽象クラスです。
 * 
 * <p>
 * 共通のCRUD処理やエンティティ整形、例外スローなどのロジックを集約し、
 * 各サービスクラスはこのクラスを継承して拡張することができます。
 * </p>
 *
 * @param <T> 対象エンティティ型 {@link AbstractStudyEntity}
 * @param <R> 対応リポジトリ型 {@link AbstractStudyRepository}
 */
@Transactional
public abstract class AbstractService<T extends AbstractStudyEntity<T>, R extends AbstractStudyRepository<T>> {

	/** DBアクセスを行うリポジトリ */
	protected R repository;

	/**
	 * コンストラクタ
	 *
	 * @param repository 使用するリポジトリ
	 */
	protected AbstractService(R repository) {
		this.repository = repository;
	}

	/**
	 * 全件取得。
	 * 必要に応じてエンティティ内容を整形して返却します。
	 *
	 * @return エンティティ一覧
	 */
	public List<T> findAll() {
		List<T> all = repository.findAll();
		all.forEach(T::rewrite);
		return all;
	}

	/**
	 * ID指定で1件取得。
	 * 存在しない場合は業務例外をスローします。
	 *
	 * @param id 検索対象のID
	 * @return 検索結果のエンティティ
	 * @throws BusinessException 該当データが存在しない場合
	 */
	public T findById(int id) {
		T entity = repository.findById(id)
				.orElseThrow(() -> new BusinessException(
						new ErrorResponse("Data Not Found.", LocalDate.now())));
		entity.rewrite();
		return entity;
	}

	/**
	 * エンティティ追加。
	 * バリデーション後に保存し、返却前に整形処理を行います。
	 *
	 * @param entity 追加するエンティティ
	 * @return 保存されたエンティティ
	 */
	public T add(T entity) {
		entity.validate();
		T add = repository.save(entity);
		add.rewrite();
		return add;
	}

	/**
	 * エンティティ削除。
	 * 削除前に整合性維持のための前処理 {@link AbstractStudyEntity#preRemove()} を実行します。
	 *
	 * @param id 削除対象のID
	 */
	public void deleteById(int id) {
		T existing = findById(id);
		existing.preRemove();
		repository.deleteById(id);
	}

	/**
	 * エンティティ更新。
	 * バリデーション後、既存データに反映し保存します。
	 * その後整形処理を実施してから返却します。
	 *
	 * @param id 更新対象のID
	 * @param entity 更新内容を持つエンティティ
	 * @return 更新後のエンティティ
	 */
	public T update(int id, T entity) {
		entity.validate();
		T existing = findById(id);
		existing.preUpdate(entity);
		T update = repository.save(existing);
		update.rewrite();
		return update;
	}
}