/*
package com.bee.springboot.controller.error;


import com.bee.springboot.util.response.CommonResponse;
import com.bee.springboot.util.response.DefaultResponseStatus;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.format.Formatter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.util.StringUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.boot.autoconfigure.web.ErrorController;


*/
/**
 * unify the error response
 * <p>
 * created at 2018/7/12
 *
 * @author
 *//*

@RestController
//不用任何的配置，只要把这个类放在项目中，Spring能扫描到的地方。就可以实现全局异常的回调。
@ControllerAdvice(basePackages = {"com.bee.springboot.controller"})
@RequestMapping("${server.error.path:${error.path:" + ErrorController.ERROR_PATH + "}}")
@Api(tags = "统一错误处理")
public class ErrorController implements ErrorController {
    static Logger LOG = LoggerFactory.getLogger(ErrorController.class);
    static final String ERROR_PATH = "/error";
    static final Map<String, String> ErrorMap = new HashMap<>();
    */
/**
     * 如果原始异常信息中包含特定的key，就转换为对应的message
     *//*

    static final Map<String, String> MessageMap = new HashMap<>();

    static {
        ErrorMap.put("com.alibaba.dubbo.rpc.RpcException", "服务端异常,请稍后重试");
        MessageMap.put(DataIntegrityViolationException.class.getName(), "数据操作异常,请修改后重新操作");
    }

    @InitBinder
    public void intDate(WebDataBinder dataBinder) {
        dataBinder.addCustomFormatter(new Formatter<Date>() {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            DateFormat dayFormat = new SimpleDateFormat("yyyy-MM-dd h:m:s");

            @Override
            public Date parse(String s, Locale locale) throws ParseException {
                if (StringUtils.isEmpty(s) || StringUtils.isEmpty(s.replaceAll("[\'\"]", ""))) {
                    return null;
                }
                try {
                    return dateFormat.parse(s);
                } catch (Exception e) {
                    return dayFormat.parse(s);
                }
            }

            @Override
            public String print(Date date, Locale locale) {
                return dateFormat.format(date);
            }
        });
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResponse handleValidException(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<String> errorMessages = new ArrayList<>();
        if (fieldErrors != null) {
            fieldErrors.forEach(fieldError -> {
                errorMessages.add(fieldError.getDefaultMessage());
            });
        }
        DefaultResponseStatus responseStatus = new DefaultResponseStatus(false, "MethodArgumentNotValidException", StringUtils.collectionToCommaDelimitedString(errorMessages));
        return log(responseStatus, e);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public CommonResponse handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        DefaultResponseStatus responseStatus = new DefaultResponseStatus(false, "MissingServletRequestParameterException", e.getParameterName() + "必须传递");
        return log(responseStatus, e);
    }

    @ExceptionHandler(JpaObjectRetrievalFailureException.class)
    public CommonResponse handleEntityNotFoundException(JpaObjectRetrievalFailureException e) {
        DefaultResponseStatus responseStatus = new DefaultResponseStatus(false, "JpaObjectRetrievalFailureException", "未找到相关数据");
        return log(responseStatus, e);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public CommonResponse handleEntityNotFoundException(DataIntegrityViolationException e) {
        DefaultResponseStatus responseStatus = new DefaultResponseStatus(false, "DataIntegrityViolationException", "输入信息太长");
        return log(responseStatus, e);
    }

    @ExceptionHandler(DataAccessException.class)
    public CommonResponse handleDataAccessException(DataAccessException e) {
        DefaultResponseStatus responseStatus = new DefaultResponseStatus(false, "DataAccessException", "数据操作异常");
        return log(responseStatus, e);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public CommonResponse handleInvalidFormatException(HttpMessageNotReadableException e) {
        DefaultResponseStatus responseStatus = new DefaultResponseStatus(false, "HttpMessageNotReadableException", "输入数据类型不对");
        return log(responseStatus, e);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public CommonResponse handleInvalidFormatException(MethodArgumentTypeMismatchException e) {
        DefaultResponseStatus responseStatus = null;
        if (Integer.class.equals(e.getRequiredType())) {
            responseStatus = new DefaultResponseStatus(false, "MethodArgumentTypeMismatchException", "请输入数字");
        } else {
            responseStatus = new DefaultResponseStatus(false, "MethodArgumentTypeMismatchException", "输入数据不合法,请重新输入");
        }
        return log(responseStatus, e);
    }


    @ExceptionHandler(Exception.class)
    public CommonResponse handleException(Exception e) {
        String message = ErrorMap.get(e.getClass().getName());
        if (message != null) {
            return log((DefaultResponseStatus) ResponseStatus.fail(message), e);
        }
        message = e.getMessage();
        if (message != null) {
            for (String s : MessageMap.keySet()) {
                if (message.contains(s)) {
                    message = MessageMap.get(s);
                    break;
                }
            }
        }
        DefaultResponseStatus status = new DefaultResponseStatus(false, "Exception"
                , message);
        return log(status, e);
    }

    @RequestMapping("")
    public CommonResponse error(HttpServletRequest request) {
        Object code = request.getAttribute("javax.servlet.error.status_code");
        Object message = request.getAttribute("javax.servlet.error.message");
        DefaultResponseStatus status = new DefaultResponseStatus(false, null
                , null);
        if (code != null) {
            status.setCode(code + "");
        }
        if (message != null) {
            String msg = message.toString();
            if (!msg.isEmpty()) {
                status.setMessage(msg);
            }
        }
        return log(status, null);
    }

    private CommonResponse log(DefaultResponseStatus status, Exception e) {
        LOG.error("An Error occurs {}", status, e);
        return CommonResponse.failResponse(status);
    }

    //@Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
*/
