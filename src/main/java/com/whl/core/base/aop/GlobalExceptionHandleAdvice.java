package com.whl.core.base.aop;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.whl.core.base.enums.ResultEnum;
import com.whl.core.base.exception.SanHaoCoreException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.whl.core.base.vo.ResultVo;

/**
 * 全局异常处理配置类
 * @author wanghailong
 *
 */
@RestControllerAdvice
public class GlobalExceptionHandleAdvice {

	private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandleAdvice.class);

	/**
	 * 捕获异常信息
	 * @param request
	 * @param e
	 * @return
	 */
	@ExceptionHandler({ Exception.class })
	public <T> ResultVo<T> handler(HttpServletRequest request, Exception e) {
		logger.warn("URL : " + request.getRequestURL().toString());
		logger.warn("HTTP_METHOD : " + request.getMethod());
		logger.warn("IP : " + request.getRemoteAddr());
		logger.warn("请求类型: " + request.getContentType());
		logger.warn("异常类型: {}", e.toString());

		ResultVo<T> resultVo = new ResultVo<>();

		if (e instanceof MethodArgumentNotValidException) {

			BindingResult bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();

			List<FieldError> fieldErrors = bindingResult.getFieldErrors();
			for (FieldError error : fieldErrors) {
				logger.warn("异常实体：{},异常字段: {},异常值: {},异常信息: {}", error.getObjectName(), error.getField(),
						error.getRejectedValue(), error.getDefaultMessage());
				resultVo.setCode(ResultEnum.ERROR_VALID.getCode());
				resultVo.setMsg(error.getDefaultMessage());
				return resultVo;
			}
		} else if (e instanceof SanHaoCoreException) {
			resultVo.setCode(((SanHaoCoreException) e).getCode());
			resultVo.setMsg(e.getMessage());
		} else {
			resultVo.setCode(ResultEnum.FAILED.getCode());
			resultVo.setMsg(ResultEnum.FAILED.getMessage());
		}

		return resultVo;
	}

}
