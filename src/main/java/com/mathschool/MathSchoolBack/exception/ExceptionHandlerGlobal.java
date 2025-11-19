package com.mathschool.MathSchoolBack.exception;

import com.google.api.gax.rpc.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.annotations.NotFound;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerGlobal {

    @ExceptionHandler(NotFoundException.class)
    public String handleNotFoundException(NotFoundException e, HttpServletRequest request, Model model) {
        return "error/404";
    }
  /*
   * It is recommended to have a general exception handler to catch all unhandled exceptions.
   * This can be done by uncommenting the following method.
   * It is also recommended to add more specific exception handlers for common exceptions,
   * such as IllegalArgumentException and DataIntegrityViolationException.
   */
  /* @ExceptionHandler(Exception.class)
    public String handleGeneralException(Exception e,HttpServletRequest request, Model model) {
        System.out.println(e.getMessage());
        return "error/500";
    }*/
}
