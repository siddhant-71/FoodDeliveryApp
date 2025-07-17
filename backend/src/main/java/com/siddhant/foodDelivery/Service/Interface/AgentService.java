package com.siddhant.foodDelivery.Service.Interface;

import com.siddhant.foodDelivery.DTOs.AgentDTO;
import com.siddhant.foodDelivery.Entities.Agent;

public interface AgentService {
    Agent registerAgent(Agent Agent);
    Agent updateAgent(long AgentID,Agent Agent);
    void deleteAgent(Agent Agent);

    void assignAgentToOrder(long AgentId,long OrderId);
    void unassignAgentFromOrder(long AgentId,long OrderId);
    boolean isAgentAssignedToOrder(long AgentId,long orderId);

    boolean isAgentAvailable(long AgentId);
    //void setAgentAvailability(long AgentId,boolean availability);

    void updateAgentRating(long AgentId,double rating);
    void BlockAgent(long AgentId);
    void unblockAgent(long AgentId);

    AgentDTO EntityToDTO(Agent agent);
    //LCN
    //void updateAgentLocation(long AgentId,double latitude,double longitude);
    //String getAgentLocation(long AgentId);
}
