package com.transport.transport.controller;


import com.transport.transport.model.Path;
import com.transport.transport.service.PathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/paths")
public class PathController {

    @Autowired
    private PathService pathService;

    @PutMapping("/{path_id}")
    public ResponseEntity<String> updatePath(
            @PathVariable Long path_id,
            @RequestBody Path path) {
        Path updatedPath = pathService.updatePath(path_id, path);
        if (updatedPath != null) {
            return new ResponseEntity<>("ok", HttpStatus.OK);
        }
        return new ResponseEntity<>("Path not found", HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Path> createPath(@RequestBody Path path){
        Path savePath = pathService.savePath(path);
        return new ResponseEntity<>(savePath, HttpStatus.CREATED);
    }


    @GetMapping("/{source_id}/{destination_id}")
    public ResponseEntity<?> getOptimalPath(
            @PathVariable Long source_id,
            @PathVariable Long destination_id) {
        Map<String, Object> result = pathService.findOptimalPath(source_id, destination_id);
        return ResponseEntity.ok(result);
    }


    @GetMapping
    public ResponseEntity<Iterable> getPaths(){
        return new ResponseEntity<>(pathService.getAllPaths(), HttpStatus.FOUND);
    }
    @DeleteMapping("/{path_id}")
    public ResponseEntity<String> deletePath(@PathVariable Long path_id) {
        boolean deleted = pathService.deletePath(path_id);
        if (deleted) {
            return new ResponseEntity<>("ok", HttpStatus.OK);
        }
        return new ResponseEntity<>("Path not found", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/path/{pathId}")
    public ResponseEntity<Path> getPathById(@PathVariable Long pathId) {
        return new ResponseEntity<>(pathService.getPath(pathId), HttpStatus.OK);
    }
}
