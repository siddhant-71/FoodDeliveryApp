package com.siddhant.foodDelivery.Controller;

import com.siddhant.foodDelivery.DTOs.AgentDTO;
import com.siddhant.foodDelivery.Entities.Agent;
import com.siddhant.foodDelivery.Repository.AgentRepo;
import com.siddhant.foodDelivery.Service.Interface.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/agent")
@CrossOrigin(origins = "http://localhost:5137")
public class AgentController {
    @Autowired
    AgentService agentService;
    @Autowired
    AgentRepo agentRepo;

    @GetMapping("/")
    List<AgentDTO> getAllAgents(){
        List<Agent> all=agentRepo.findAll();
        List<AgentDTO>ans=new ArrayList<>();
        for(Agent agent:all)ans.add(agentService.EntityToDTO(agent));
        return ans;
    }
    @GetMapping("/id/{id}")
    AgentDTO getAgentWithId(@PathVariable("id") long id){
        Agent agent=agentRepo.findById(id).orElseThrow(()->new RuntimeException("Agent not found"));
        return agentService.EntityToDTO(agent);
    }
    @GetMapping("/email/{email}")
    AgentDTO getAgentWithEmail(@PathVariable("email") String email){
        Agent agent=agentRepo.findByEmail(email).orElseThrow(()->new RuntimeException("Agent not found"));
        return agentService.EntityToDTO(agent);
    }
    @GetMapping("/rating/{rating}")
    List<AgentDTO> getAgentWithRating(@PathVariable("rating") double rating){
        List<Agent>all=agentRepo.findAllByRatingGreaterThanEqual(rating);
        List<AgentDTO>ans=new ArrayList<>();
        for(Agent agent:all)ans.add(agentService.EntityToDTO(agent));
        return ans;
    }
    @GetMapping("/name/{name}")
    List<AgentDTO> getAgentWithName(@PathVariable("name") String name){
        List<Agent>all=agentRepo.findAllByName(name);
        List<AgentDTO>ans=new ArrayList<>();
        for(Agent agent:all)ans.add(agentService.EntityToDTO(agent));
        return ans;
    }
    @GetMapping("/number/{number}")
    AgentDTO getAgentWithPhone(@PathVariable("number") String number){
        Agent agent=agentRepo.findByPhone(number).orElseThrow(()->new RuntimeException("Agent not found"));
        return agentService.EntityToDTO(agent);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable("id")long id){
        agentRepo.deleteById(id);
    }
    @PostMapping("/")
    void add(@RequestBody Agent agent){
        agentRepo.save(agent);
    }
    @PutMapping("/{id}")
    void update(@PathVariable("id")long id,@RequestBody Agent agent){
        agentService.updateAgent(id,agent);
    }
}
