package com.jack.common;

import com.jack.exception.ParamException;
import com.jack.exception.PermissionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常类
 */
@Slf4j
public class SpringExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        String url = request.getRequestURL().toString();
        ModelAndView model;
        String defaultMsg = "System error：";

        //要求项目中所有请求json数据，都是用json结尾
        if (url.endsWith(".json")) {
            //判断是否是自己的异常
            if (ex instanceof PermissionException || ex instanceof ParamException) {
                JsonData fail = JsonData.fail(ex.getMessage());
                model = new ModelAndView("jsonView", fail.toMap());
            } else {
                //其他异常
                log.error("unknow json exception, url:"+url, ex);
                JsonData fail = JsonData.fail(defaultMsg + ex.getMessage());
                model = new ModelAndView("jsonView", fail.toMap());
            }
            //page页面
        } else if (url.endsWith(".page")){
            log.error("unknow page exception, url:"+url, ex);
            JsonData fail = JsonData.fail(defaultMsg + ex.getMessage());
            model = new ModelAndView("exception", fail.toMap());
        } else {
            log.error("unknow exception, url:"+url, ex);
            JsonData fail = JsonData.fail(defaultMsg + ex.getMessage());
            model = new ModelAndView("jsonView", fail.toMap());
        }
        return model;
    }
}
