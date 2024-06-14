package br.com.kyw.project_kyw.adapters.controllers;

import br.com.kyw.project_kyw.adapters.dtos.base.ProjectBaseDTO;
import br.com.kyw.project_kyw.adapters.dtos.request.TaskRequest;
import br.com.kyw.project_kyw.adapters.dtos.response.TaskResponse;
import br.com.kyw.project_kyw.application.services.TaskService;
import br.com.kyw.project_kyw.core.enums.Status;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
@SecurityRequirement(name = "Bearer ")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskResponse> create(@RequestBody TaskRequest taskRequest){
        var taskresponse = taskService.create(taskRequest, taskRequest.getProjectId());
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(taskresponse.getId())
                .toUri();
        return ResponseEntity.created(location).body(taskresponse);
    }
    @GetMapping("/{taskId}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable UUID taskId){
        return ResponseEntity.ok(taskService.getById(taskId));
    }
    @GetMapping
    public ResponseEntity<Page<TaskResponse>> getAll(Pageable pageable){
        return ResponseEntity.ok(taskService.getAll(pageable));
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskResponse> update(@RequestBody TaskRequest taskRequest, @PathVariable UUID taskId){
        var taskresponse = taskService.update(taskRequest, taskId);
        return ResponseEntity.ok(taskresponse);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> delete(@PathVariable UUID taskId){
        taskService.delete(taskId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/deadline")
    public ResponseEntity<List<TaskResponse>> getTasksByDeadline(){
        var list = taskService.getTaskByDeadline();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/created")
    public ResponseEntity<List<TaskResponse>> getProjectCreatedByUser(@RequestParam("startDate") Instant startDate, @RequestParam("endDate") Instant endDate, Status status) {
        return ResponseEntity.ok(taskService.getTaskCreatedByDate(startDate, endDate, status));
    }

}
