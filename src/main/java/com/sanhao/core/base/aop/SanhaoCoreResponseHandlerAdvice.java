package com.sanhao.core.base.aop;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.sanhao.core.base.enums.ResultEnum;
import com.sanhao.core.base.vo.ResultVo;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.Arrays;
import java.util.List;

/**
 * 响应结果统一处理
 * @author lixiang
 *
 */
@ControllerAdvice
public class SanhaoCoreResponseHandlerAdvice implements ResponseBodyAdvice<Object> {

	private static final List<Class<? extends Annotation>> annos = Arrays.asList(RequestMapping.class, GetMapping.class,
			PostMapping.class, DeleteMapping.class, PutMapping.class);

	/**
	 * 对所有RestController的接口方法进行拦截
	 * @param returnType
	 * @param converterType
	 * @return
	 */
	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		String className=returnType.getDeclaringClass().getName();
		if (className.indexOf("server.controller.outward") == -1) {
			return false;
		}
		
		AnnotatedElement element = returnType.getAnnotatedElement();
		return annos.stream().anyMatch(anno -> anno.isAnnotation() && element.isAnnotationPresent(anno));
	}

	/**
	 * 在响应结果之前，统一处理响应结果后返回
	 * @param data
	 * @param returnType
	 * @param selectedContentType
	 * @param selectedConverterType
	 * @param request
	 * @param response
	 * @return
	 */
	@Override
	public Object beforeBodyWrite(Object data, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
			ServerHttpResponse response) {

		Object out;
		response.getHeaders().setContentType(MediaType.APPLICATION_JSON);

		if (data instanceof ResultVo) {
			out = data;
		} else {
			ResultVo<Object> vo = new ResultVo<>();
			vo.setCode(ResultEnum.SUCCESS.getCode());
			vo.setMsg(ResultEnum.SUCCESS.getMessage());
			vo.setData(data);
			out = vo;
		}

		return out;
	}
}
