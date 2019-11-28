package com.imonkeyz.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Data
@EqualsAndHashCode(callSuper=false)
public class BizException extends RuntimeException {

	@AllArgsConstructor
	@Getter
	public enum BizErrorCodeEnum implements ErrorCode {

		UNSPECIFIED(500, "系統錯誤，請稍後再試");

		private int code;
		private String description;

		@Override
		public int getCode() {
			return code;
		}

		@Override
		public String getDescription() {
			return description;
		}
	}

	/**
	 * 错误码
	 */
	private final ErrorCode errorCode;

	/**
	 * 无参默认构造UNSPECIFIED
	 */
	public BizException() {
		super(BizErrorCodeEnum.UNSPECIFIED.getDescription());
		this.errorCode = BizErrorCodeEnum.UNSPECIFIED;
	}

	/**
	 * 指定错误码构造通用异常
	 *
	 * @param errorCode 错误码
	 */
	public BizException(final ErrorCode errorCode) {
		super(errorCode.getDescription());
		this.errorCode = errorCode;
	}

	/**
	 * 指定详细描述构造通用异常
	 *
	 * @param detailedMessage 详细描述
	 */
	public BizException(final String detailedMessage) {
		super(detailedMessage);
		this.errorCode = BizErrorCodeEnum.UNSPECIFIED;
	}

	/**
	 * 指定错误源构造通用异常
	 *
	 * @param t 错误源
	 */
	public BizException(final Throwable t) {
		super(t);
		this.errorCode = BizErrorCodeEnum.UNSPECIFIED;
	}

	/**
	 * 构造通用异常
	 *
	 * @param errorCode       错误码
	 * @param detailedMessage 详细描述
	 */
	public BizException(final ErrorCode errorCode, final String detailedMessage) {
		super(detailedMessage);
		this.errorCode = errorCode;
	}

	/**
	 * 构造通用异常
	 *
	 * @param errorCode       错误码
	 * @param detailedMessage 详细描述
	 */
	public BizException(final ErrorCode errorCode, final Object detailedMessage) {
		super(String.valueOf(detailedMessage));
		this.errorCode = errorCode;
	}

	/**
	 * 构造通用异常
	 *
	 * @param errorCode 错误码
	 * @param t         错误源
	 */
	public BizException(final ErrorCode errorCode, final Throwable t) {
		super(errorCode.getDescription(), t);
		this.errorCode = errorCode;
	}

	/**
	 * 构造通用异常
	 *
	 * @param detailedMessage 详细描述
	 * @param t               错误源
	 */
	public BizException(final String detailedMessage, final Throwable t) {
		super(detailedMessage, t);
		this.errorCode = BizErrorCodeEnum.UNSPECIFIED;
	}

	/**
	 * 构造通用异常
	 *
	 * @param errorCode       错误码
	 * @param detailedMessage 详细描述
	 * @param t               错误源
	 */
	public BizException(final ErrorCode errorCode, final String detailedMessage, final Throwable t) {
		super(detailedMessage, t);
		this.errorCode = errorCode;
	}

	/**
	 * 构造通用异常
	 *
	 * @param errorCode       错误码
	 * @param detailedMessage 详细描述
	 * @param t               错误源
	 */
	public BizException(final ErrorCode errorCode, final Object detailedMessage, final Throwable t) {
		super(String.valueOf(detailedMessage), t);
		this.errorCode = errorCode;
	}
}
