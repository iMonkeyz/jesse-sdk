package com.imonkeyz.vo;

import com.imonkeyz.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class HResult {

	private int code;
	private String status;
	private String msg;
	private Object data;

	private HResult(Status status) {
		this.code = status.code;
		this.status = status.status;
	}

	private HResult(Status status, String msg) {
		this.code = status.code;
		this.status = status.status;
		this.msg = msg;
	}

	public static HResult success() {
		return new HResult(Status.Success);
	}

	public static HResult success(String msg) {
		return new HResult(Status.Success, msg);
	}

	public static HResult success(String msg, Object data) {
		return success(msg).data(data);
	}

	public static HResult success(int code, String msg, Object data) {
		return success(msg, data).code(code);
	}

	public static HResult failure() {
		return new HResult(Status.Failure);
	}

	public static HResult failure(ErrorCode ec) {
		return failure(ec.getDescription()).code(ec.getCode());
	}

	public static HResult failure(String msg) {
		return new HResult(Status.Failure, msg);
	}

	public static HResult failure(String msg, Object data) {
		return failure(msg).data(data);
	}

	public static HResult failure(int code, String msg, Object data) {
		return failure(msg, data).code(code);
	}

	public HResult code(int code) {
		this.code = code;
		return this;
	}

	public HResult msg(String msg) {
		this.msg = msg;
		return this;
	}

	public HResult data(Object data) {
		this.data = data;
		return this;
	}

	@AllArgsConstructor
	@Getter
	public enum Status {
		Success(0, "success"),
		Failure(99, "failure");

		int code;
		String status;
	}

}
