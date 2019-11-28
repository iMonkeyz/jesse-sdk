package com.imonkeyz.exception;

public interface ErrorCode {

	/**
	 * 获取错误码
	 * @return
	 */
	int getCode();

	/**
	 * 获取错误信息
	 * @return
	 */
	String getDescription();
}