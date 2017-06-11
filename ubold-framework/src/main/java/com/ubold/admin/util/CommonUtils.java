package com.ubold.admin.util;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import org.springframework.util.CollectionUtils;

import com.sophia.response.Response;
import com.sophia.web.constant.StatusCodeConstant;

/**
 * 工具
 * @author zkning
 */
public class CommonUtils {
	
	/**
	 * jsr303 校验
	 * @param bean
	 * @return
	 */
	public static <T> Response<?> verify(T bean) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		javax.validation.Validator validator = factory.getValidator();
		Set<ConstraintViolation<T>> violations = validator.validate(bean);
		StringBuilder msgBuffer = new StringBuilder();
		if (!CollectionUtils.isEmpty(violations)) {
			for (ConstraintViolation<T> violation : violations) {
				msgBuffer.append(violation.getPropertyPath().toString() + ":" + violation.getMessage() + "; ");
				break;
			}
			return Response.FAILURE(StatusCodeConstant.INVALID_ARGS.code,msgBuffer.toString());
		} else {
			return Response.SUCCESS();
		}
	}
}
