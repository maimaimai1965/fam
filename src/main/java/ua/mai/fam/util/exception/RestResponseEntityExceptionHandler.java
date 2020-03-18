package ua.mai.fam.util.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.server.UnsupportedMediaTypeStatusException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;

/**
 * Обработчик ошибок сервиса, заменяющий стандартный обработчик.
 * 
 */
//@Order(Ordered.HIGHEST_PRECEDENCE)
//@Profile({"ac-rest", "ac-data-rest"})
//@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    public final Logger LOG = LoggerFactory.getLogger(this.getClass());

//    @ExceptionHandler(UnsupportedMediaTypeStatusException.class)
//    public final ResponseEntity<Object> handleOtherException(Exception e, WebRequest request) {
//        e = new RestException(RestException.ERROR_CODE_KL_PAY_112, RestException.ERROR_TEXT_KL_PAY_112,
//            ex.getMessage()/*details*/, ex,
//            ua.telesens.plu.util.HttpStatus.FORBIDDEN/*403*/,
//            e.getPluRequest());
//    }

    //  /**
//   * Обработчик ошибок сервиса, которые имеют тип {@link PluException}.<br>
//   * Если в ошибке <i>e</i> не указан статус, то он принимается равным 400 (Bad Request).
//   *
//   * @param e обрабатываемая ошибка типа {@link PluException}.
//   * @param request - объект запроса.
//   * @return resonse-объект, содержащий ошибку <i>e</i>.
//   */
//  @ExceptionHandler(PluWsException.class)
//  public final ResponseEntity<Object> handlePluWsExceptionException(PluWsException e, WebRequest request) {
//      ObjectError k;
//    ErrorDetails errorDetails = new ErrorDetails(e);
//
//    switch (e.getCode()) {
//      case RestException.ERROR_CODE_KL_PAY_100:
//           //Ошибка с кодом ERROR_CODE_KL_PAY_100 еще не логировалась в БД. Записываем ее в БД.
//           //a_err_context = '#OPER=F_DELETE_PAYMENT' + '%' + 'ТЕКСТ ОШИБКИ'
//           SupportService service = getSupportService();
//
//           if (service != null) {
//             PluRequest pluRequest =
//                 (e.getPluRequest() != null) ? e.getPluRequest()
//                                             : getPluRequestFactory().getPluRequest(TicPaymentRestApplication.METHOD_UNDEF);
//             StringBuilder str =
//                 new StringBuilder("#OPER=").append((pluRequest.getMethod()!=null) ? pluRequest.getMethod()
//                                                                                   : TicPaymentRestApplication.METHOD_UNDEF)
//                                            .append("%").append(e.getMessage());
//             try {
//               String newMessage = service.sendEvent(pluRequest, RestException.ERROR_CODE_KL_PAY_100/*err_cd*/,
//                   str.toString()/*err_context*/, "RUS"/*lang*/);
//               //Обновляем сообщение об ошибке.
//               errorDetails.setMessage(newMessage);
//               LOG.error(LogMarker.JOB, newMessage);
//             }
//             catch (Exception ex) {
//               //TODO записать в лог зообщение о том, что произошла ошибка при записи ошибки в БД.
//               LOG.error(LogMarker.JOB, "Error writing error to database: {}", ex.getMessage());
//             }
//           }
//           else {
//             //БД еще недоступна для записи ошибки в БД.
//             LOG.warn(LogMarker.JOB, "Database is not available to write errors. Error: {}", e.getMessage());
//           }
//           break;
//      case RestException.ERROR_CODE_KL_PAY_108:
//      case RestException.ERROR_CODE_KL_PAY_109:
//      case RestException.ERROR_CODE_KL_PAY_105:
//           //Если не найден счет фактура или платеж, то код - 404
//           e.setHttpStatus(ua.telesens.plu.util.HttpStatus.NOT_FOUND);
//           break;
//      case "ERR_SQL":
//           Throwable cause = e.getCause();
//           if (cause instanceof SQLException) {
//             SQLException ex = (SQLException)cause;
//             if (ex.getSQLState().equals("65000")) {
//               //Недоступны объекты схемы T-IC
//               //Ошибка вида: ORA-06550:..identifier 'PG_J_PAYMENT' must be declared
//               //response HTTP status должен быть 403 Forbidden.
//               e = new RestException(RestException.ERROR_CODE_KL_PAY_112, RestException.ERROR_TEXT_KL_PAY_112,
//                                     ex.getMessage()/*details*/, ex,
//                                     ua.telesens.plu.util.HttpStatus.FORBIDDEN/*403*/,
//                                     e.getPluRequest());
//               LOG.error(LogMarker.JOB, " Error: {}", RestException.ERROR_TEXT_KL_PAY_112);
//               errorDetails = new ErrorDetails(e);
//             }
//           }
//           break;
//    }
//    ResponseEntity<Object> responseEntity =  new ResponseEntity<Object>(errorDetails,
//                                //Если httpStatus не задан, то response возвращается со статусом 400, "Bad Request".
//                                (e.getHttpStatus() == null) ? HttpStatus.BAD_REQUEST
//                                                            : HttpStatus.valueOf(e.getHttpStatus().value()));
//
//    return responseEntity;
//  }
//
//
//  public final ResponseEntity<Object> defaultHandleException(Exception e, WebRequest request,
//                                                             ua.telesens.plu.util.HttpStatus pluHttpStatus) {
//    LOG.error(LogMarker.REST, e.getMessage());
//    PluWsException pluWsException =
//        new PluWsException(RestException.ERROR_CODE_KL_PAY_100, e.getMessage(), e, pluHttpStatus);
//    PluRequest pluRequest = PluRequest.getPluRequestFromHttpRequest();
//    pluWsException.setPluRequest(pluRequest);
//    return handlePluWsExceptionException(pluWsException, request);
//  }
//
//
//  @ExceptionHandler(Exception.class)
//  public final ResponseEntity<Object> handleOtherException(Exception e, WebRequest request) {
//    return defaultHandleException(e, request, ua.telesens.plu.util.HttpStatus.BAD_REQUEST);
//  }
//
//  @Override
//  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException e,
//                                                                HttpHeaders headers, HttpStatus status, WebRequest request) {
//    return defaultHandleException(e, request, ua.telesens.plu.util.HttpStatus.BAD_REQUEST);
//  }
//
//  @Override
//  protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e,
//                                                                       HttpHeaders headers, HttpStatus status, WebRequest request) {
//    return defaultHandleException(e, request, ua.telesens.plu.util.HttpStatus.METHOD_NOT_ALLOWED);
//  }
//
//
//  @Override
//  protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException e,
//                                                                   HttpHeaders headers, HttpStatus status, WebRequest request) {
//    return defaultHandleException(e, request, PluWsException.toPluHttpStatus(status));
//  }
//
//  @Override
//  protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException e,
//                                                                    HttpHeaders headers, HttpStatus status, WebRequest request) {
//    return defaultHandleException(e, request, PluWsException.toPluHttpStatus(status));
//  }
//
//  @Override
//  protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException e, HttpHeaders headers,
//                                                             HttpStatus status, WebRequest request) {
//    return defaultHandleException(e, request, PluWsException.toPluHttpStatus(status));
//  }
//
//  @Override
//  protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException e,
//                                                                        HttpHeaders headers, HttpStatus status, WebRequest request) {
//    return defaultHandleException(e, request, PluWsException.toPluHttpStatus(status));
//  }
//
//  @Override
//  protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException e,
//                                                                        HttpHeaders headers, HttpStatus status, WebRequest request) {
//    return defaultHandleException(e, request, PluWsException.toPluHttpStatus(status));
//  }
//
//  @Override
//  protected ResponseEntity<Object> handleConversionNotSupported(ConversionNotSupportedException e, HttpHeaders headers,
//                                                                HttpStatus status, WebRequest request) {
//    return defaultHandleException(e, request, PluWsException.toPluHttpStatus(status));
//  }
//
//  @Override
//  protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException e, HttpHeaders headers, HttpStatus status,
//                                                      WebRequest request) {
//    return defaultHandleException(e, request, PluWsException.toPluHttpStatus(status));
//  }
//
//  @Override
//  protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException e, HttpHeaders headers,
//                                                                HttpStatus status, WebRequest request) {
//    return defaultHandleException(e, request, PluWsException.toPluHttpStatus(status));
//  }
//
//  @Override
//  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers,
//                                                                HttpStatus status, WebRequest request) {
//    return defaultHandleException(e, request, PluWsException.toPluHttpStatus(status));
//  }
//
//  @Override
//  protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException e,
//                                                                   HttpHeaders headers, HttpStatus status, WebRequest request) {
//    return defaultHandleException(e, request, PluWsException.toPluHttpStatus(status));
//  }
//
//  @Override
//  protected ResponseEntity<Object> handleBindException(BindException e, HttpHeaders headers, HttpStatus status,
//                                                       WebRequest request) {
//    return defaultHandleException(e, request, PluWsException.toPluHttpStatus(status));
//  }
//
//
////  @ExceptionHandler(value={NoHandlerFoundException.class})
////  @ResponseStatus(code=HttpStatus.BAD_REQUEST)
////  public String badRequest(Exception e, HttpServletRequest request, HttpServletResponse response) {
////      e.printStackTrace();
////      return "BAD";
////  }
//
//  @Override
//  protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException e, HttpHeaders headers,
//                                                                 HttpStatus status, WebRequest request) {
//    return defaultHandleException(e, request, PluWsException.toPluHttpStatus(status));
//  }
//
//  @Override
//  protected ResponseEntity<Object> handleAsyncRequestTimeoutException(AsyncRequestTimeoutException e,
//                                                                      HttpHeaders headers, HttpStatus status, WebRequest webRequest) {
//    return defaultHandleException(e, null/*request*/, PluWsException.toPluHttpStatus(status));
//  }
//
//  @Override
//  protected ResponseEntity<Object> handleExceptionInternal(Exception e, Object body, HttpHeaders headers,
//                                                           HttpStatus status, WebRequest request) {
//    return defaultHandleException(e, null/*request*/, PluWsException.toPluHttpStatus(status));
//  }

}
