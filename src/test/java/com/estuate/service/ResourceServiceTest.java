package com.estuate.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.estuate.model.Resource;
import com.estuate.repository.ResourceRepository;

public class ResourceServiceTest {
    
    @Mock
    private ResourceRepository resourceRepository;
    
    @InjectMocks
    private ResourceService resourceService;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void testFindResourcesBySkills() {
    	Set<String> skills = new HashSet<>(Arrays.asList("Java", "Spring"));
        Resource resource = new Resource();
        resource.setResourceName("Test User");
        resource.setSkills(skills);
        
        when(resourceRepository.findBySkillsAll(skills, skills.size()))
            .thenReturn(Arrays.asList(resource));
            
        List<Resource> result = resourceService.findResourcesBySkills(skills);
        
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test User", result.get(0).getResourceName());
        verify(resourceRepository).findBySkillsAll(skills, skills.size());
    }
}