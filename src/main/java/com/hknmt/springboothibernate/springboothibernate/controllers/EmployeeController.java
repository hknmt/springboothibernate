package com.hknmt.springboothibernate.springboothibernate.controllers;

import com.hknmt.springboothibernate.springboothibernate.dao.EmployeeDAO;
import com.hknmt.springboothibernate.springboothibernate.entities.Employee;
import com.hknmt.springboothibernate.springboothibernate.exception.ExceptionResponse;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;

@RestController
public class EmployeeController extends ResponseEntityExceptionHandler {

    @Autowired
    private EmployeeDAO data;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public String wellcome(){
        return "Wellcome to RESTful example";
    }

    @RequestMapping(value = "/Employee/{empID}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> getEmployee(@PathVariable(value = "empID", required = true)int empID){
        Employee result = data.getEmployee(empID);

        if (result == null) {
            ExceptionResponse error = new ExceptionResponse("error", "Không có nhân viên này!");
            return new ResponseEntity<ExceptionResponse>(error, HttpStatus.NOT_FOUND);
        }
        else
            return new ResponseEntity<Employee>(result, HttpStatus.OK);
    }

    @ExceptionHandler(value = NumberFormatException.class)
    @RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ExceptionResponse> handleNumberformatException(NumberFormatException ex){

        ExceptionResponse error = new ExceptionResponse("error", "Không có nhân viên này!");
        return new ResponseEntity<ExceptionResponse>(error, HttpStatus.BAD_REQUEST);
    }

    /*@ExceptionHandler(value = Exception.class)
    @RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ExceptionResponse> handleException(Exception ex){
        ExceptionResponse error = new ExceptionResponse("error", ex.getMessage());
        return new ResponseEntity<ExceptionResponse>(error, HttpStatus.BAD_REQUEST);
    }*/
}
