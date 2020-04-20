/**
 * 
 */
package com.bookstore.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.bookstore.entity.Category;

public class CategoryDAO extends JpaDAO<Category> implements GenericDAO<Category> {

	public CategoryDAO(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

	public Category create(Category category) {
		return super.create(category);
	}

	public Category update(Category category) {
		return super.update(category);
	}

	public Category get(Object id) {
		return super.find(Category.class, id);
	}

	public void delete(Object id) {
		super.delete(Category.class, id);
		
	}

	public List<Category> listAll() {
		return super.findWithNamedQuery("Category.findAll");
		
	}

	public long count() {
		return super.countWithNamedQuery("Category.countAll");
	}

}
