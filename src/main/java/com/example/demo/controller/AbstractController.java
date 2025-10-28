package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.demo.entity.AbstractStudyEntity;
import com.example.demo.repository.AbstractStudyRepository;
import com.example.demo.service.AbstractService;

/**
 * 汎用的なCRUD操作を提供する抽象コントローラーです。
 * 
 * <p>共通のREST APIエンドポイントを定義し、
 * 各エンティティのコントローラーはこのクラスを継承することで
 * 重複実装を避けることができます。</p>
 *
 * @param <T> 対象となるエンティティ。{@link AbstractStudyEntity} を継承している必要があります。
 * @param <R> 利用するリポジトリ。{@link AbstractStudyRepository} を継承している必要があります。
 * @param <S> 利用するサービス。{@link AbstractService} を継承している必要があります。
 */
public abstract class AbstractController<T extends AbstractStudyEntity<T>, R extends AbstractStudyRepository<T>, S extends AbstractService<T, R>> {

	/** CRUD処理を担当するサービス */
	protected S service;

	/**
	 * コンストラクタ
	 * 
	 * @param service サービスクラスのインスタンス
	 */
	protected AbstractController(S service) {
		this.service = service;
	}

	/**
	 * 全件取得
	 *
	 * @return エンティティのリスト
	 */
	@GetMapping
	public List<T> findAll() {
		return service.findAll();
	}

	/**
	 * ID指定で1件取得
	 *
	 * @param id 取得対象のID
	 * @return 該当するエンティティ
	 */
	@GetMapping("{id}")
	public T findById(@PathVariable int id) {
		return service.findById(id);
	}

	/**
	 * エンティティの登録
	 *
	 * @param entity リクエストボディで渡されるエンティティ
	 * @return 登録後のエンティティ
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public T add(@RequestBody T entity) {
		return service.add(entity);
	}

	/**
	 * ID指定でエンティティを削除
	 *
	 * @param id 削除対象のID
	 */
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable int id) {
		service.deleteById(id);
	}

	/**
	 * ID指定でエンティティを更新
	 *
	 * @param id 更新対象のID
	 * @param entity 更新内容
	 * @return 更新後のエンティティ
	 */
	@PutMapping("{id}")
	public T update(@PathVariable int id, @RequestBody T entity) {
		return service.update(id, entity);
	}
}