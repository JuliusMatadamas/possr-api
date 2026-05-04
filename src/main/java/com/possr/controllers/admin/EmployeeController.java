package com.possr.controllers.admin;

import com.possr.utils.Logging;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.possr.dto.ApiResponseDTO;
import com.possr.dto.EmployeeDTO;
import com.possr.services.admin.EmployeeService;

import lombok.RequiredArgsConstructor;

import java.util.concurrent.CompletableFuture;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/employees")
public class EmployeeController {
    private final Logging logging;
    private final EmployeeService employeeService;

    @PostMapping("/v1/create-employee")
    public CompletableFuture<ResponseEntity<ApiResponseDTO>> createEmployee(
            @RequestBody EmployeeDTO employeeDTO) {
        logging.logInfo("EmployeeController.createEmployee", "Creating employee", "EmployeeController", employeeDTO);
        return CompletableFuture.supplyAsync(() -> employeeService.createEmployee(employeeDTO));
    }
}
