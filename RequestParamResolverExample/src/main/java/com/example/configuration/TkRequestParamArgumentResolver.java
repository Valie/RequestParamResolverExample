package com.example.configuration;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer;
import com.example.annotation.TkParam;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.annotation.ValueConstants;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.annotation.AbstractNamedValueMethodArgumentResolver;

import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description API参数解析器
 * @Author WeiLi
 * 参考: SpringMVC内置的RequestParamMethodArgumentResolver
 **/
public class TkRequestParamArgumentResolver extends AbstractNamedValueMethodArgumentResolver {

    @Override
    protected NamedValueInfo createNamedValueInfo(MethodParameter parameter) {
        TkParam anno = parameter.getParameterAnnotation(TkParam.class);
        return anno == null ? new NamedValueInfo("", false, ValueConstants.DEFAULT_NONE) :
                new NamedValueInfo(anno.name(), anno.required(), ValueConstants.DEFAULT_NONE);
    }

    @Override
    protected Object resolveName(String key, MethodParameter parameter, NativeWebRequest request) throws Exception {
       //获取参数类型
       Type paramType = parameter.getGenericParameterType();
       if (paramType == null) {
           throw new IllegalArgumentException("undefined parameter type!");
       }

       Object result = request.getParameter(key);
       //判断参数是否是JavaBean对象类型
       if (ParserConfig.global.getDeserializer(paramType) instanceof JavaBeanDeserializer) {
           Map<String, String> map = new HashMap();
           Map<String, String[]> paramMap = request.getParameterMap();
           for (Map.Entry<String, String[]> entry : paramMap.entrySet()) {
               String name = entry.getKey();
               String value = URLDecoder.decode(entry.getValue()[0], "UTF-8");
               map.put(name, value);
           }
           result = JSON.parseObject(JSON.toJSONString(map), paramType);
       }
       return result;
    }

    /**
     * 判断是否支持解析当前参数
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(TkParam.class);
    }

}
