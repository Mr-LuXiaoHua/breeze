package com.breeze.admin.infrastructure.aop;

import com.breeze.admin.infrastructure.utils.IpUtils;
import com.breeze.common.bo.RequestParam;
import com.breeze.common.constants.SymbolConst;
import com.breeze.common.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author 一枕清风
 * @date 2023/3/24
 */
@Slf4j
@Aspect
@Component
public class RequestParamAspect {

    @Pointcut("execution(public * com.breeze.admin.*.controller..*.*(..))")
    public void pointcut() {

    }


    @Around("pointcut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        RequestParam requestParam = assembleRequestParam(pjp);
        log.info("###请求报文: {}", requestParam);

        Object result = pjp.proceed();
        stopWatch.stop();
        log.info("###处理耗时: {} ms", stopWatch.getLastTaskTimeMillis());

        return result;
    }





    private RequestParam assembleRequestParam(ProceedingJoinPoint pjp) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();

        String url = request.getRequestURL().toString();
        String ip = IpUtils.getRemoteHost(request);
        String httpMethod = request.getMethod();
        String classMethod = pjp.getSignature().getDeclaringTypeName() + SymbolConst.DOT + pjp.getSignature().getName();

        RequestParam requestParam = new RequestParam();
        requestParam.setClassMethod(classMethod);
        requestParam.setHttpMethod(httpMethod);
        requestParam.setIp(ip);
        requestParam.setUrl(url);

        Object[] args = pjp.getArgs();
        if (ArrayUtils.isNotEmpty(args)) {
            requestParam.setParam(Arrays.toString(pjp.getArgs()));
        }
        return requestParam;

    }





}
