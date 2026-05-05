package com.possr.controllers.admin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.possr.constants.AppMessages;
import com.possr.dto.ApiResponseDTO;
import com.possr.dto.WorkRelationDTO;
import com.possr.services.admin.WorkRelationService;
import com.possr.utils.Logging;

import lombok.RequiredArgsConstructor;

import java.util.concurrent.CompletableFuture;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/work-relations")
public class WorkRelationController {
    private final WorkRelationService workRelationService;
    private final Logging logging;

    @PostMapping("/v1/create-work-relation")
    public CompletableFuture<ResponseEntity<ApiResponseDTO>> createWorkRelation(
            @RequestBody WorkRelationDTO workRelationDTO) {
        logging.logInfo("WorkRelationController.createWorkRelation", "Creating work relation", AppMessages.UNKNOWN_SOURCE, workRelationDTO);
        return CompletableFuture.supplyAsync(() -> workRelationService.createWorkRelation(workRelationDTO));
    }
}
