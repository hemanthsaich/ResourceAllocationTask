package com.estuate.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.estuate.model.Resource;
import com.estuate.service.ResourceService;


@RestController
@RequestMapping("/api/resources")
public class ResourceController {
    
    @Autowired
    private ResourceService resourceService;
    
    @PostMapping("/search")
    public ResponseEntity<List<Resource>> findResourcesBySkills(@RequestBody Set<String> skills) {
        return ResponseEntity.ok(resourceService.findResourcesBySkills(skills));
    }
    
    @PostMapping("/search/experience")
    public ResponseEntity<List<Resource>> findResourcesBySkillsAndExperience(
            @RequestBody Set<String> skills,
            @RequestParam int maxExperience) {
        return ResponseEntity.ok(resourceService.findResourcesBySkillsAndExperience(skills, maxExperience));
    }
    
    @PostMapping
    public ResponseEntity<Resource> createResource(@RequestBody Resource resource) {
        return ResponseEntity.ok(resourceService.saveResource(resource));
    }
    
    
}
