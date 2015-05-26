package com.project.agent;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.MessageTemplate;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.project.metier.Beans;
import com.project.metier.Const;

public class Agent_Interface extends Agent {
	/**
	 * @author ProBook 450g2
	 * 
	 *         Class de l'agent interface permets - La connexion de
	 *         l'utilisateurs (envoi des ids) - Le lancement du GUI
	 */
	private static final long serialVersionUID = 1L;
	private final String TAG = "Agent_Interface";
	private Context context;

	@Override
	protected void setup() {

		/**
		 * Implementation d'un comportement sequentiel !
		 */
		/**
		 * Recupere le contexte de l'application
		 */
		Object[] args = getArguments();
		if (args != null && args.length > 0) {
			if (args[0] instanceof Context) {
				context = (Context) args[0];
			}
		}
		// comportementSequ.addSubBehaviour(new SendIdsBehaviours(this));
		addBehaviour(new SendIdsBehaviours(this));
		addBehaviour(new WaitConfiarmationPlanning(this));
		// comportementSequ.addSubBehaviour(new
		// WaitConfiarmationPlanning(this));
		// addBehaviour(comportementSequ);
	}

	/**
	 * Premier comportement
	 * 
	 * @author ProBook 450g2
	 *
	 */
	public class SendIdsBehaviours extends OneShotBehaviour {
		// private Beans bean;
		private static final long serialVersionUID = 1L;

		public SendIdsBehaviours(Agent a) {
			super(a);
			// bean = new Beans();

		}

		public void action() {
			/**
			 * Recuperation des ids entrees par l'etudiant
			 */
			String userName = Beans.getLogin();
			String mdp = Beans.getMdp();

			/**
			 * Envoi des ids a l'agent Controlleur
			 */
			jade.lang.acl.ACLMessage requestLoginMessage = new jade.lang.acl.ACLMessage(
					jade.lang.acl.ACLMessage.INFORM);
			requestLoginMessage.setConversationId("id");
			requestLoginMessage.setContent(userName + "|" + mdp);
			AID dummyAid = new AID();
			dummyAid.setName("agentScolar@" + Const.ipAdress + ":1099/JADE");
			dummyAid.addAddresses("http://" + Const.ipAdress + ":7778/acc");
			requestLoginMessage.addReceiver(dummyAid);
			send(requestLoginMessage);

		}
	}

	/**
	 * Second comportement parallel
	 * 
	 * @author ProBook 450g2
	 *
	 */
	public class WaitConfiarmationPlanning extends CyclicBehaviour {
		private static final long serialVersionUID = 1L;

		public WaitConfiarmationPlanning(Agent a) {
			super(a);
		}

		/**
		 * L'agent reste à l'ecoute et attend
		 */
		public void action() {
			MessageTemplate model = MessageTemplate.and(MessageTemplate
					.MatchPerformative(jade.lang.acl.ACLMessage.INFORM),
					MessageTemplate.MatchConversationId("resp"));
			jade.lang.acl.ACLMessage reponseLoginMessage = receive(model);
			/**
			 * Si la reponse de l'agent controlleur est positif les donnees sont
			 * envoyees
			 */
			if (reponseLoginMessage != null) {
				String validateConnection = reponseLoginMessage.getContent()
						.toString();
				if (validateConnection.substring(0, 2).equals("ok")) {
					Agent_Interface.this.context.startActivity(new Intent(
							Agent_Interface.this.context,
							com.project.simulaturandroid.Loged.class));
					Beans.setLogin(validateConnection.substring(3,
							validateConnection.length()));
					block();
				} else {
					Toast.makeText(Beans.getContext(), "Erreur dans le login",
							Toast.LENGTH_SHORT).show();
					block();
				}
				/**
				 * Si nan l'agent est blocké
				 */
			} else {

				block();

			}
		}

	}
}
