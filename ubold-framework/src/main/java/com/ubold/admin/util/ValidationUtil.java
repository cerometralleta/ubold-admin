package com.ubold.admin.util;

import com.ubold.admin.constant.StatusCodeConstant;
import com.ubold.admin.response.Response;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * Created by ningzuokun on 2017/11/10.
 */
public class ValidationUtil {
    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public static <T> Response validator(T t) {
        Set<ConstraintViolation<T>> violations = validator.validate(t);
        if (BeanUtils.isNotEmpty(violations)) {
            return Response.FAILURE(StatusCodeConstant.INVALID_ARGS.code,violations.iterator().next().getMessage());
        }
        return Response.SUCCESS();
    }
}
