package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import com.example.demo.entity.AbstractStudyEntity;

/**
 * すべてのリポジトリの基底インターフェースです。
 *
 * <p>
 * 共通のID型(Integer)を持つエンティティを対象に、
 * 基本的なCRUD操作と動的検索(Specification)を提供します。
 * </p>
 *
 * <p>
 * このインターフェース自体は Spring による Bean 化対象ではなく、
 * 具体的な Entity ごとのリポジトリが継承して使用します。
 * </p>
 *
 * @param <T> 対象エンティティ型 {@link AbstractStudyEntity}
 */
@NoRepositoryBean
public interface AbstractStudyRepository<T extends AbstractStudyEntity<T>>
        extends JpaRepository<T, Integer>, JpaSpecificationExecutor<T> {
}