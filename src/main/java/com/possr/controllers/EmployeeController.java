package com.possr.controllers;

import java.util.concurrent.CompletableFuture;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.possr.constants.AppMessages;
import com.possr.dto.ApiResponseDTO;
import com.possr.dto.EmployeeDTO;
import com.possr.services.EmployeeService;
import com.possr.utils.Logging;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {
    private final Logging logging;
    private final EmployeeService employeeService;

    @PostMapping("/v1/create")
    public CompletableFuture<ResponseEntity<ApiResponseDTO>> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        logging.logInfo(AppMessages.EMPLOYEE_TO_CREATE, "Calling service to create employee", AppMessages.UNKNOWN_SOURCE, employeeDTO);
        return CompletableFuture.supplyAsync(() -> employeeService.createEmployee(employeeDTO));
    }
}
