package com.project.metier;

/**
 * 
 * Class Beans contien different getter et setter
 * 
 *  @author ProBook 450g2
 */

import java.io.Serializable;
import java.util.ArrayList;

import com.project.agent.Agent_Gestion;
import com.project.agent.Agent_Interface;

@SuppressWarnings("serial")
public class Beans implements Serializable, jade.util.leap.Serializable {

	private static ArrayList<String> planningTab = new ArrayList();
	private static ArrayList<String> planningTabUpdated = new ArrayList();
	private static Agent_Gestion agentGestion;
	private static Agent_Interface agentInterface;

	public static Agent_Interface getAgentInterface() {
		return agentInterface;
	}

	public static void setAgentInterface(Agent_Interface agentInterface) {
		Beans.agentInterface = agentInterface;
	}

	public static Agent_Gestion getAgentGestion() {
		return agentGestion;
	}

	public static void setAgentGestion(Agent_Gestion agentGestion) {
		Beans.agentGestion = agentGestion;
	}

	public ArrayList<String> getPlanningTabUpdated() {
		return planningTabUpdated;
	}

	public void setPlanningTabUpdated(ArrayList<String> planningTabUpdated) {
		Beans.planningTabUpdated.clear();
		Beans.planningTabUpdated = planningTabUpdated;
	}

	private static String login = null;
	private static String mdp = null;

	public static String getLogin() {
		return login;
	}

	public static void setLogin(String login) {
		Beans.login = login;
	}

	public static String getMdp() {
		return mdp;
	}

	public static void setMdp(String mdp) {
		Beans.mdp = mdp;
	}

	public Beans() {
	}

	public ArrayList<String> getPlanningTab() {
		return planningTab;
	}

	public void setPlanningTab(ArrayList<String> planningTab) {
		this.planningTab.clear();
		this.planningTab = planningTab;
	}
}