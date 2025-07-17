package com.siddhant.foodDelivery.Service.Impl;

import com.siddhant.foodDelivery.DTOs.AgentDTO;
import com.siddhant.foodDelivery.Entities.Agent;
import com.siddhant.foodDelivery.Entities.Order;
import com.siddhant.foodDelivery.Enums.OrderStatus;
import com.siddhant.foodDelivery.Repository.AgentRepo;
import com.siddhant.foodDelivery.Repository.OrderRepo;
import com.siddhant.foodDelivery.Service.Interface.AgentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgentServiceImpl implements AgentService {
    @Autowired
    private AgentRepo agentRepo;
    private static final Logger logger = LoggerFactory.getLogger(AgentServiceImpl.class);
    @Autowired
    private OrderRepo orderRepo;

    @Override
    public Agent registerAgent(Agent agent) {
        try{
            agentRepo.save(agent);
            logger.info("Agent registered successfully");
            return agent;
        }
        catch (Exception e){
            logger.error("Agent not registered");
            return null;
        }
    }

    @Override
    public Agent updateAgent(long agentID, Agent agent) {
        Agent oldAgent=agentRepo.findById(agentID).orElseThrow(()->new RuntimeException("Agent not found"));
        if(agent.getEmail()!=null)oldAgent.setEmail(agent.getEmail());
        if(agent.getName()!=null)oldAgent.setName(agent.getName());
        if(agent.getImage()!=null)oldAgent.setImage(agent.getImage());
        if(agent.getPhone()!=null)oldAgent.setPhone(agent.getPhone());
        if(agent.getLastName()!=null)oldAgent.setLastName(agent.getLastName());
        if(agent.getPassword()!=null)oldAgent.setPassword(agent.getPassword());
        logger.info("Agent updated successfully");
        agentRepo.save(oldAgent);
        return oldAgent;
    }

    @Override
    public void deleteAgent(Agent agent) {
        agentRepo.delete(agent);
        logger.info("Agent deleted successfully");
    }

    @Override
    public void assignAgentToOrder(long agentId, long orderId) {
        Agent agent=agentRepo.findById(agentId).orElseThrow(()->new RuntimeException("Agent not found"));
        Order order=orderRepo.findById(orderId).orElseThrow(()->new RuntimeException(("Order not found")));
        if(agent.isAvailability()){
            agent.setAvailability(false);
            agent.getOrdersHistory().add(order);
            order.setAgent(agent);
            order.setOrderStatus(OrderStatus.OUT_FOR_DELIVERY);
            agentRepo.save(agent);
            orderRepo.save(order);
            logger.info("Agent assigned to order successfully");
        }
        else{
            logger.error("Agent is not available");
        }
    }

    @Override
    public void unassignAgentFromOrder(long agentId, long orderId) {
        Agent agent=agentRepo.findById(agentId).orElseThrow(()->new RuntimeException("Agent not found"));
        Order order=orderRepo.findById(orderId).orElseThrow(()->new RuntimeException("Order not found"));
        if(order.getAgent()!=null){
            if(order.getAgent().getId()==agentId){
                agent.getOrdersHistory().remove(order);
                order.setAgent(null);
                agentRepo.save(agent);
                orderRepo.save(order);
                logger.info("Agent unassigned from order successfully");
            }
            else{
                logger.error("Agent already is not assigned to this order");
            }
        }
        else{
            logger.error("No Agent is assigned to this order");
        }
    }

    @Override
    public boolean isAgentAssignedToOrder(long agentId, long orderId) {
        Agent agent=agentRepo.findById(agentId).orElseThrow(()->new RuntimeException("Agent not found"));
        Order order=orderRepo.findById(orderId).orElseThrow(()->new RuntimeException("Order not found"));
        if(order.getAgent()!=null){
            return order.getAgent().getId() == agentId;
        }
        return false;
    }

    @Override
    public boolean isAgentAvailable(long agentId) {
        Agent agent=agentRepo.findById(agentId).orElseThrow(()->new RuntimeException("Agent not found"));
        return agent.isAvailability();
    }

    @Override
    public void updateAgentRating(long agentId, double rating) {
        Agent agent=agentRepo.findById(agentId).orElseThrow(()->new RuntimeException("Agent not found"));
        double old=agent.getRating();
        int raters=agent.getRatingCount();
        agent.setRating(((old*raters)+rating)/(raters+1));
        agent.setRatingCount(raters+1);
        agentRepo.save(agent);
        logger.info("Agent rating updated successfully");
    }


    @Override
    public void BlockAgent(long AgentId) {
        Agent agent=agentRepo.findById(AgentId).orElseThrow(()->new RuntimeException("Agent not found"));
        if(agent.isBlocked())logger.error("Agent is already blocked");
        else{
            agent.setBlocked(true);
            agentRepo.save(agent);
            logger.info("Agent blocked successfully");
        }
    }

    @Override
    public void unblockAgent(long AgentId) {
        Agent agent=agentRepo.findById(AgentId).orElseThrow(()->new RuntimeException("Agent not found"));
        if(!agent.isBlocked())logger.error("Agent is already unblocked");
        else{
            agent.setBlocked(false);
            agentRepo.save(agent);
            logger.info("Agent unblocked successfully");
        }
    }

    public AgentDTO EntityToDTO(Agent agent){
        AgentDTO ans=new AgentDTO();
        ans.setId(agent.getId());
        ans.setName(agent.getName());
        ans.setLastName(agent.getLastName());
        ans.setEmail(agent.getEmail());
        ans.setPhone(agent.getPhone());
        ans.setImage(agent.getImage());
        ans.setRating(agent.getRating());
        ans.setRatingCount(agent.getRatingCount());
        ans.setOrdersHistory(agent.getOrdersHistory());
        return ans;
    }
}
