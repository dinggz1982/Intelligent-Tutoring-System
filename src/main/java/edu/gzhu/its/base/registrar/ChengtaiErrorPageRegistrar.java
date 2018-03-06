package edu.gzhu.its.base.registrar;

import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.ErrorPageRegistrar;
import org.springframework.boot.web.servlet.ErrorPageRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * 自定义异常页面
 * <p>Title : ChengtaiErrorPageRegistrar</p>
 * <p>Description : </p>
 * <p>Company : </p>
 * @author 丁国柱
 * @date 2018年1月31日 上午12:37:45
 */
@Component
public class ChengtaiErrorPageRegistrar implements ErrorPageRegistrar {

    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        //具体的错误码错误异常页面
        ErrorPage e404 = new ErrorPage(HttpStatus.NOT_FOUND,"/404.html");
        ErrorPage e500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR,"/500.html");
        ErrorPage e403 = new ErrorPage(HttpStatus.FORBIDDEN,"/403.html");
        
        //指定具体异常的错误定制页面
        ErrorPage argspage = new ErrorPage(IllegalArgumentException.class,"/argsException.html");
        
        registry.addErrorPages(e404,e500,e403,argspage);
    }
}
