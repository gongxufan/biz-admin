package gxf.dev.biz.admin.filter;

/**
 * @author gongxufan
 * @date 17-11-30
 **/

import gxf.dev.biz.admin.dto.ResultDto;
import gxf.dev.biz.admin.util.HttpUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 *
 * 1.自定义角色鉴权过滤器(满足其中一个角色则认证通过) 2.扩展异步请求认证提示功能;
 *
 * @author shadow
 *
 */
public class MyPermsAuthorizationFilter extends PermissionsAuthorizationFilter {

    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        Subject subject = getSubject(request, response);
        if (subject.getPrincipal() == null) {
            if (HttpUtils.isAjax(httpRequest)) {
                HttpUtils.sendJsonResponse(httpResponse, ResultDto.timeOut("您尚未登录或登录时间过长,请重新登录!"));
            } else {
                saveRequestAndRedirectToLogin(request, response);
            }
        } else {
            if (HttpUtils.isAjax(httpRequest)) {
                HttpUtils.sendJsonResponse(httpResponse, ResultDto.fail("您没有足够的权限执行该操作"));
            } else {
                String unauthorizedUrl = getUnauthorizedUrl();
                if (StringUtils.hasText(unauthorizedUrl)) {
                    WebUtils.issueRedirect(request, response, unauthorizedUrl);
                } else {
                    WebUtils.toHttp(response).sendError(401);
                }
            }
        }
        return false;
    }

}  