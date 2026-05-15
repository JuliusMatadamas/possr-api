package com.possr.controllers;

import java.util.concurrent.CompletableFuture;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.possr.constants.AppMessages;
import com.possr.dto.ApiResponseDTO;
import com.possr.dto.WorkRelationDTO;
import com.possr.services.WorkRelationService;
import com.possr.utils.Logging;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/work-relations")
public class WorkRelationController {
    private final Logging logging;
    private final WorkRelationService workRelationService;

    @PostMapping("/v1/create")
    public CompletableFuture<ResponseEntity<ApiResponseDTO>> createWorkRelation(@RequestBody WorkRelationDTO workRelationDTO) {
        logging.logInfo(AppMessages.WORK_RELATION_TO_CREATE, "Calling service to create work relation", AppMessages.UNKNOWN_SOURCE, workRelationDTO);
        return CompletableFuture.supplyAsync(() -> workRelationService.createWorkRelation(workRelationDTO));
    }
}
