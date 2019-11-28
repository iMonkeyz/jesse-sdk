package com.imonkeyz.vo.converter;

import javax.validation.constraints.NotNull;

public abstract class VOConverter<SOURCE, TARGET> {

	/**
	 * Convert Entity to VO
	 * @param src Entity
	 * @param tar VO
	 * @return
	 */
	public TARGET convert(SOURCE src, TARGET tar) {
		validation(src, tar);
		populator(src, tar);
		return tar;
	}

	/**
	 * Convert Entity to VO
	 * @param src Entity
	 * @return VO
	 */
	public TARGET convert(SOURCE src) {
		return convert(src, createVO());
	}


	/**
	 * Parse VO to Entity
	 * @param src
	 * @param tar
	 * @return
	 */
	public SOURCE parse(TARGET src, SOURCE tar) {
		validation(src, tar);
		parser(src, tar);
		return tar;
	}

	/**
	 * Parse VO to Entity
	 * @param src
	 */
	public SOURCE parse(TARGET src) {
		return parse(src, createEntity());
	}

	/**
	 * Check both Entity And VO must not be null
	 * @param src Entity or VO
	 * @param tar Entity or VO
	 */
	private void validation(Object src, Object tar) {
		if (null == src) {
			throw new RuntimeException("Convert error, Source Object must not be Null !");
		}
		if (null == tar) {
			throw new RuntimeException("Convert error, Target Object must not be Null !");
		}
	}

	/**
	 * Create a new VO Target
	 * @return
	 */
	protected abstract TARGET createVO();

	/**
	 * VO Populator
	 * @param src Entity
	 * @param tar VO
	 */
	protected abstract void populator(@NotNull SOURCE src, @NotNull TARGET tar);

	/**
	 * Create a new Entity Source
	 * @return
	 */
	protected abstract SOURCE createEntity();

	/**
	 * Entity Parser
	 * @param src VO
	 * @param tar Entity
	 */
	protected abstract void parser(@NotNull TARGET src, @NotNull SOURCE tar);

}
