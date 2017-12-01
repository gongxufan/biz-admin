package gxf.dev.biz.admin.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.util.StringUtil;
import gxf.dev.biz.admin.model.Resources;
import gxf.dev.biz.admin.service.ResourcesService;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author gongxufan
 * @date 17-11-30
 **/
public class HttpUtils {
    public static boolean isAjax(ServletRequest request) {
        String header = ((HttpServletRequest) request).getHeader("X-Requested-With");
        if ("XMLHttpRequest".equalsIgnoreCase(header)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public static void sendJsonResponse(ServletResponse response, Object result){
        try {
            response.getWriter().print(new ObjectMapper().writeValueAsString(result));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化权限
     */
    public static Map<String, String> loadFilterChainDefinitions(ResourcesService resourcesService) {
        // 权限控制map.从数据库获取
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        filterChainDefinitionMap.put("/favicon.ico", "anon");
        filterChainDefinitionMap.put("/ajaxLogin", "anon");
        filterChainDefinitionMap.put("/logout", "anon");
        List<Resources> resourcesList = resourcesService.queryAll();
        for (Resources resources : resourcesList) {

            if (StringUtil.isNotEmpty(resources.getResurl())) {
                String permission = "perms[" + resources.getResurl() + "]";
                filterChainDefinitionMap.put(resources.getResurl(), permission);
            }
        }
        filterChainDefinitionMap.put("/**", "authc");
        return filterChainDefinitionMap;
    }
}
