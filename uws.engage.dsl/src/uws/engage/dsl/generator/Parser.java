/*
 * generated by Xtext
 */
package uws.engage.dsl.generator;
import uws.engage.dsl.generator.ParseResult;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.util.CancelIndicator;
import org.eclipse.xtext.validation.CheckMode;
import org.eclipse.xtext.validation.IResourceValidator;

import uws.engage.dsl.assess.Model;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;

import uws.engage.dsl.assess.Action;
import uws.engage.dsl.assess.ActionReaction;
import uws.engage.dsl.assess.BadgeModel;
import uws.engage.dsl.assess.Characteristic;
import uws.engage.dsl.assess.EvidenceModel;
import uws.engage.dsl.assess.Feedback;
import uws.engage.dsl.assess.FeedbackMessages;
import uws.engage.dsl.assess.FeedbackModel;
import uws.engage.dsl.assess.GameDescription;
import uws.engage.dsl.assess.GenericTrigger;
import uws.engage.dsl.assess.LearningOutcomes;
import uws.engage.dsl.assess.Outcome;
import uws.engage.dsl.assess.OutcomesPoints;
import uws.engage.dsl.assess.Parameter;
import uws.engage.dsl.assess.Params;
import uws.engage.dsl.assess.PlayerDescription;
import uws.engage.dsl.assess.Point;
import uws.engage.dsl.assess.Points;
import uws.engage.dsl.assess.Reaction;
import uws.engage.dsl.assess.TimerAction;
import uws.engage.dsl.assess.TimerActions;
import uws.engage.dsl.assess.Timing;
import uws.engage.dsl.assess.Trigger;
import uws.engage.dsl.assess.TriggerFeedback;

import org.json.simple.JSONObject;

public class Parser {
	
	public static ParseResult parse(String input) throws IOException {

		Injector injector = new uws.engage.dsl.AssessStandaloneSetup().createInjectorAndDoEMFRegistration();
		Parser parser= injector.getInstance(Parser.class);
		return parser.run(input);
	}	
	
	public JSONObject getJSONfromDSL(String input) throws IOException {

		Injector injector = new uws.engage.dsl.AssessStandaloneSetup().createInjectorAndDoEMFRegistration();
		Parser parser= injector.getInstance(Parser.class);
		return DSLtoJSON(parser.run(input).model);
	}
		
	@Inject 
	private Provider<ResourceSet> resourceSetProvider;
	
	@Inject
	private IResourceValidator validator;
	
	
	public ParseResult run(String input) throws IOException {
		ParseResult p = new ParseResult();
		
		ResourceSet set = resourceSetProvider.get();
		Resource resource = set.createResource(URI.createURI("file:/example.assess"));	
		InputStream in = new ByteArrayInputStream(input.getBytes());
		resource.load(in, set.getLoadOptions());
		p.model = (Model) resource.getContents().get(0);
		p.issues = validator.validate(resource, CheckMode.ALL, CancelIndicator.NullImpl);
		return p;
		
	}	

	
	@SuppressWarnings("unchecked")
	private JSONObject DSLtoJSON(Model model)
	{
		JSONObject json = new JSONObject();
		
		// ##################### SERIOUS GAME ######################	
		GameDescription sg = model.getGame();
		JSONObject sgJson =  new JSONObject();

		sgJson.put("name", sg.getName());
		sgJson.put("idDeveloper", sg.getDev());
		if (sg.getDesc() != null) { sgJson.put("description", sg.getDesc()); }
		if (sg.getAgeRange() != null) { 
			sgJson.put("ageMin", new Integer(sg.getAgeRange().getAgeMin()));
			sgJson.put("ageMax", new Integer(sg.getAgeRange().getAgeMax()));
		}
		if (sg.getLang() != null) { sgJson.put("lang", sg.getLang()); }
		if (sg.getCountry() != null) { sgJson.put("country", sg.getCountry()); }
		if (sg.getGenre() != null) { sgJson.put("genre", sg.getGenre()); }
		if (sg.getSubject() != null) { sgJson.put("subject", sg.getSubject()); }
		if (sg.getPublic() != null) { sgJson.put("public", sg.getPublic()); }
		
		json.put("seriousGame", sgJson);
		
		// ##################### PLAYER ######################	
		
		PlayerDescription player = model.getPlayer();
		ArrayList<JSONObject> playerJson = new ArrayList<JSONObject>();
		if (player != null)
		{
			
			for (Characteristic c : player.getCharacteristics()) {
				JSONObject characteristicJson = new JSONObject();
	
				characteristicJson.put("name", c.getName());
				
				String type;
				if (c.getType().getSimpleType() != null)
				{
					type = c.getType().getSimpleType() ;
				}
				else
				{
					type = "Enum (" + c.getType().getEnumType().getEnumValues().get(0);
					for (int i = 1 ; i < c.getType().getEnumType().getEnumValues().size() ; i++)
					{
						type += ", " + c.getType().getEnumType().getEnumValues().get(i);
					}
					type += ")";
				}
				
				characteristicJson.put("type", type);
							
				playerJson.add(characteristicJson);
			}	
		}
		json.put("player", playerJson);

		// ##################### LEARNING OUTCOME ######################
		
		LearningOutcomes los = model.getLearningOutcomes();
		JSONObject losJSON = new JSONObject();
		
		for (Outcome o : los.getOutcomes())
		{
			JSONObject outcomeJson = new JSONObject();
			
			if (o.getDesc() != null) { outcomeJson.put("desc", o.getDesc()); }
			if ((Integer)o.getValue() != null) { outcomeJson.put("value", o.getValue()); }
			if (o.getType() != null) { outcomeJson.put("type", o.getType()); }
			
			ArrayList<JSONObject> feedback = new ArrayList<JSONObject>();
			
			if (model.getFeedbackModel() != null)
			{
				for (Trigger t : model.getFeedbackModel().getTriggers())
				{
					if (t.getPerf() != null && t.getPerf().getOutcome() == o)
					{
						JSONObject f = new JSONObject();
						
						f.put("limit", new Integer(t.getPerf().getLimit()));
						f.put("sign", t.getPerf().getSign());
						ArrayList<JSONObject> feedbackToTrigger = new ArrayList<JSONObject>();
						
						for (TriggerFeedback reaction : t.getPerf().getTriggerReactions())
						{
							JSONObject feedbackDesc = new JSONObject();
							feedbackDesc.put("name", reaction.getFeedback().getName());
							feedbackDesc.put("immediate", reaction.isImmediate());
							feedbackToTrigger.add(feedbackDesc);
						}
						
						f.put("feedback", feedbackToTrigger);
						
						feedback.add(f);
					}
				}
			}
			
			outcomeJson.put("feedbackTriggered", feedback);
			
			
			losJSON.put(o.getName(), outcomeJson);
		}
		
		json.put("learningOutcomes", losJSON);
		
		// ##################### FEEDBACK ######################	
		
		FeedbackMessages fdbck = model.getFeedbackMessages();
		
		if (fdbck != null)
		{
			JSONObject fdbckJson = new JSONObject();
			
			for (Feedback f : fdbck.getFeedbackMsgs())
			{
				JSONObject fJson = new JSONObject();
				
				fJson.put("message", f.getMessage());
				if (f.getImage() != null) { fJson.put("image", f.getImage()); }
				if (f.getType() != null) { fJson.put("type", f.getType()); }
				if (f.isWin()) { fJson.put("final", "win"); }
				if (f.isLose()) { fJson.put("final", "lose"); }
				if (f.isEnd()) { fJson.put("final", "end"); }
				
				fdbckJson.put(f.getName(), fJson);
			}
			
			json.put("feedback", fdbckJson);
		}
		
		// ##################### EVIDENCE MODEL ######################
		
		EvidenceModel actions = model.getEvidenceModel();
		JSONObject actionsJson = new JSONObject();
		
		for (Action a : actions.getActions())
		{
			// prepare the new JSON object
			String actionName = a.getName();
			JSONObject assessment = new JSONObject();
			
			// get the action description
			if (a.getDesc() != null) { assessment.put("desc", a.getDesc()); }
			
			// get the action parameters
			JSONObject paramsJson = new JSONObject();
			JSONObject paramsJsonLogOnly = new JSONObject();
			for (Parameter p : a.getParams())
			{
				String type;
				if (p.getType().getSimpleType() != null)
				{
					type = p.getType().getSimpleType() ;
				}
				else
				{
					type = "Enum (" + p.getType().getEnumType().getEnumValues().get(0);
					for (int i = 1 ; i < p.getType().getEnumType().getEnumValues().size() ; i++)
					{
						type += ", " + p.getType().getEnumType().getEnumValues().get(i);
					}
					type += ")";
				}
				if (p.isLogOnly())
				{
					paramsJsonLogOnly.put(p.getName(), type);
				}
				else
				{
					paramsJson.put(p.getName(), type);
				}
			}
			assessment.put("params", paramsJson);
			if (!paramsJsonLogOnly.isEmpty()) {assessment.put("paramsLogOnly", paramsJsonLogOnly);}
			
			// get the reactions
			ArrayList<JSONObject> reactions = new ArrayList<JSONObject>();
			
			for (Points assess : a.getPoints())
			{
				JSONObject assessJson = new JSONObject();				
				if (assess.isOthers())
				{
					assessJson.put("else", "true");
				}
				else
				{
					ArrayList<JSONObject> valuesJson = new ArrayList<JSONObject>();
					for (Params p : assess.getParams())
					{			
						JSONObject paramValuesJson = new JSONObject();
						for (int i = 0 ; i < p.getValues().size() ; i++)
						{
							String typeValue = (a.getParams().get(i).getType().getSimpleType() != null)? a.getParams().get(i).getType().getSimpleType() : "Enum";
							if (typeValue.equals("Int"))
							{
								paramValuesJson.put(a.getParams().get(i).getName(), 
										new Integer(p.getValues().get(i)));
							}
							else if (typeValue.equals("Float"))
							{
								paramValuesJson.put(a.getParams().get(i).getName(), 
										new Float(p.getValues().get(i)));
							}
							else if (typeValue.equals("Bool"))
							{
								paramValuesJson.put(a.getParams().get(i).getName(), 
										new Boolean(p.getValues().get(i)));
							}
							else
							{
								paramValuesJson.put(a.getParams().get(i).getName(), 
										p.getValues().get(i));
							}							
						}
						valuesJson.add(paramValuesJson);
					}
					assessJson.put("values", valuesJson);
				}
				
				ArrayList<JSONObject> marksJson = new ArrayList<JSONObject>();
				for (OutcomesPoints outcomePoints : assess.getOutcomesPoints())
				{				
					JSONObject markJson = new JSONObject();
					if (outcomePoints.getOutcome() != null)
					{
						markJson.put("learningOutcome", outcomePoints.getOutcome().getName());
					}
					else
					{
						markJson.put("learningOutcome", model.getLearningOutcomes().getOutcomes().get(0).getName());					
					}
					if (outcomePoints.getPts() != null)
					{
						if (outcomePoints.getPts().isNegative())					
						{
							markJson.put("mark", new Integer(outcomePoints.getPts().getValue()*-1));
						}
						else
						{
							markJson.put("mark", new Integer(outcomePoints.getPts().getValue()));
						}
					}
					else if (outcomePoints.getVar() != null)
					{
						markJson.put("markVar", outcomePoints.getVar().getName());					
					}
					if (outcomePoints.isResetValue())
					{
						markJson.put("reset", new Boolean(true));
					}
					marksJson.add(markJson);
				}
				assessJson.put("marks", marksJson);
				
				
				
				if (a.getReactions() != null)
				{
					ArrayList<JSONObject> feedbackJson = new ArrayList<JSONObject>();
					
					for (Reaction r : a.getReactions().getReaction())
					{
						if (r.getPointsC() != null && r.getPointsC().getKeyWd().equals("any"))
						{
							Boolean negativePoints = false;
							Boolean positivePoints = false;
							for (OutcomesPoints op : assess.getOutcomesPoints())
							{
								if (op.getPts().isNegative()) { negativePoints = true; }
								else { positivePoints = true; }
							}
							
							if (r.getPointsC().getSign() != null && 
									((r.getPointsC().getSign().equals("+") && positivePoints)
									 || (r.getPointsC().getSign().equals("-") && negativePoints)))
							{
								JSONObject f = new JSONObject();
								f.put("name", r.getFeedback().getName());
								f.put("immediate", r.isImmediate());
								feedbackJson.add(f);
							}
							else if (r.getPointsC().getValue() != null)
							{
								Point ptForFeedback = r.getPointsC().getValue();
								int ptForFeedbackInt = (ptForFeedback.isNegative())? ptForFeedback.getValue() * -1 : ptForFeedback.getValue();
								
								for (OutcomesPoints op : assess.getOutcomesPoints())
								{
									int ptToTest = (op.getPts().isNegative())? op.getPts().getValue() * -1 : op.getPts().getValue();
									if ( ptForFeedbackInt == ptToTest )
									{
										JSONObject f = new JSONObject();
										f.put("name", r.getFeedback().getName());
										f.put("immediate", r.isImmediate());
										feedbackJson.add(f);	
										break;
									}
								}
							}
						}
					}					
					assessJson.put("feedback", feedbackJson);
				}
				reactions.add(assessJson);
			}
			assessment.put("reactions", reactions);
			
			actionsJson.put(actionName, assessment);
		}
		
		json.put("evidenceModel", actionsJson);
		
		// ##################### INACTIVITY FEEDBACK ######################
		
		FeedbackModel feedbackModel = model.getFeedbackModel();
		
		
		if (feedbackModel != null)
		{
			ArrayList<JSONObject> inactivityJson = new ArrayList<JSONObject>();
		
			for (Trigger i : feedbackModel.getTriggers())
			{
				if (i.getInactivity() != null)
				{
					JSONObject inactivity = new JSONObject();

					inactivity.put("limit", new Integer(i.getInactivity().getLimit()));
					inactivity.put("sign", i.getInactivity().getSign());
					ArrayList<String> feedbackToTrigger = new ArrayList<String>();
					ArrayList<JSONObject> marks = new ArrayList<JSONObject>();
					
					for (ActionReaction reaction : i.getInactivity().getTriggerReactions())
					{
						if (reaction.getTriggerFeedback() != null)
						{
							feedbackToTrigger.add(reaction.getTriggerFeedback().getFeedback().getName());
						}
						if (reaction.getUpdateScore() != null)
						{
							JSONObject mark = new JSONObject();
							Point point = reaction.getUpdateScore().getPts();
							int score = (point.isNegative())? point.getValue() * -1 : point.getValue();
							mark.put("mark", score);
							mark.put("learningOutcome", reaction.getUpdateScore().getOutcome().getName());
							
							marks.add(mark);
						}
					}
					if (feedbackToTrigger.size() > 0)
						inactivity.put("feedback", feedbackToTrigger);
					if (marks.size() > 0)
						inactivity.put("mark", marks);
					
					inactivityJson.add(inactivity);
				}
			}
			json.put("inactivityFeedback", inactivityJson);
		}

		// ##################### BADGE MODEL ACTIONS ######################
		
		BadgeModel badgeModel = model.getBadgeModel();
				
		if (badgeModel != null)
		{
			ArrayList<JSONObject> badgeJson = new ArrayList<JSONObject>();
		
			for (GenericTrigger b : badgeModel.getGenericTriggers())
			{
				if (b.getSimple() != null)
				{
					JSONObject simple = new JSONObject();

					int limit = (b.getSimple().isNegativeLimit())? b.getSimple().getLimit() * 1 : b.getSimple().getLimit();
					simple.put("limit", new Integer(limit));
					simple.put("sign", b.getSimple().getSign());
					simple.put("function", b.getSimple().getFunction());
					
					ArrayList<String> feedbackToTrigger = new ArrayList<String>();
					
					for (TriggerFeedback reaction : b.getSimple().getTriggerReactions())
					{
						feedbackToTrigger.add(reaction.getFeedback().getName());						
					}
					simple.put("feedback", feedbackToTrigger);
					
					badgeJson.add(simple);
				}
				else if (b.getLo() != null)
				{
					JSONObject lo = new JSONObject();

					int limit = (b.getLo().isNegativeLimit())? b.getLo().getLimit() * 1 : b.getLo().getLimit();
					lo.put("limit", new Integer(limit));
					lo.put("sign", b.getLo().getSign());
					lo.put("function", b.getLo().getFunction());
					lo.put("outcome", b.getLo().getOutcome().getName());
					
					ArrayList<String> feedbackToTrigger = new ArrayList<String>();
					
					for (TriggerFeedback reaction : b.getLo().getTriggerReactions())
					{
						feedbackToTrigger.add(reaction.getFeedback().getName());
						
					}
					lo.put("feedback", feedbackToTrigger);
					
					badgeJson.add(lo);
				}
			}
			json.put("badgeModel", badgeJson);
		}
		
		
		// ##################### TIMER ACTIONS ######################
		
		TimerActions timerActions = model.getEvidenceModel().getTimerActions();
		
		if (timerActions != null)
		{
			JSONObject actionTimers = new JSONObject();
			
			for (TimerAction t : timerActions.getTimerActions())
			{
				//JSONObject timer = new JSONObject();				
				ArrayList<JSONObject> timers = new ArrayList<JSONObject>();
				
				for (Timing timing : t.getTimings())
				{
					if (timing.isAfter())
					{
						JSONObject timer = new JSONObject();
						timer.put("limit", timing.getLimit());
						
						ArrayList<JSONObject> marks = new ArrayList<JSONObject>();
						ArrayList<String> feedback = new ArrayList<String>();
						
						for (ActionReaction reaction : timing.getTimingReactions()) {
							if (reaction.getTriggerFeedback() != null)
							{
								feedback.add(reaction.getTriggerFeedback().getFeedback().getName());
							}
							if (reaction.getUpdateScore() != null)
							{
								JSONObject mark = new JSONObject();
								Point point = reaction.getUpdateScore().getPts();
								int score = (point.isNegative())? point.getValue() * -1 : point.getValue();
								mark.put("mark", score);
								mark.put("learningOutcome", reaction.getUpdateScore().getOutcome().getName());
								
								marks.add(mark);
							}
						}
						timer.put("marks", marks);
						timer.put("feedback", feedback);
						
						timers.add(timer);
					}
				}
				actionTimers.put(t.getName(), timers);
			}
			
			json.put("timerActions", actionTimers);
		}
		
		return json;
	}
	
}
