package com.estuate.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estuate.model.Resource;
import com.estuate.repository.ResourceRepository;

@Service
public class ResourceService {
    
    @Autowired
    private ResourceRepository resourceRepository;
    
    public List<Resource> findResourcesBySkills(Set<String> skills) {
        return resourceRepository.findBySkillsAll(skills, skills.size());
    }
    
    public List<Resource> findResourcesBySkillsAndExperience(Set<String> skills, int maxExperience) {
        return resourceRepository.findBySkillsAndExperience(skills, skills.size(), maxExperience);
    }
    
    public Resource saveResource(Resource resource) {
        return resourceRepository.save(resource);
    }
}
