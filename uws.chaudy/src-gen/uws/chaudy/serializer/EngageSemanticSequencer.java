package uws.chaudy.serializer;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.serializer.acceptor.ISemanticSequenceAcceptor;
import org.eclipse.xtext.serializer.acceptor.SequenceFeeder;
import org.eclipse.xtext.serializer.diagnostic.ISemanticSequencerDiagnosticProvider;
import org.eclipse.xtext.serializer.diagnostic.ISerializationDiagnostic.Acceptor;
import org.eclipse.xtext.serializer.sequencer.AbstractDelegatingSemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.GenericSequencer;
import org.eclipse.xtext.serializer.sequencer.ISemanticNodeProvider.INodesForEObjectProvider;
import org.eclipse.xtext.serializer.sequencer.ISemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService.ValueTransient;
import uws.chaudy.engage.Action;
import uws.chaudy.engage.ActionReaction;
import uws.chaudy.engage.ActionSequence;
import uws.chaudy.engage.ActionSequences;
import uws.chaudy.engage.Age;
import uws.chaudy.engage.Characteristic;
import uws.chaudy.engage.EngagePackage;
import uws.chaudy.engage.EvidenceModel;
import uws.chaudy.engage.Feedback;
import uws.chaudy.engage.FeedbackMessages;
import uws.chaudy.engage.FeedbackModel;
import uws.chaudy.engage.GameDescription;
import uws.chaudy.engage.InactivityLimit;
import uws.chaudy.engage.LearningOutcomes;
import uws.chaudy.engage.Model;
import uws.chaudy.engage.Outcome;
import uws.chaudy.engage.OutcomeValueLimit;
import uws.chaudy.engage.ParamCondition;
import uws.chaudy.engage.Parameter;
import uws.chaudy.engage.Params;
import uws.chaudy.engage.PlayerDescription;
import uws.chaudy.engage.Point;
import uws.chaudy.engage.Points;
import uws.chaudy.engage.PointsCondition;
import uws.chaudy.engage.Reaction;
import uws.chaudy.engage.Reactions;
import uws.chaudy.engage.TimerAction;
import uws.chaudy.engage.TimerActions;
import uws.chaudy.engage.Timing;
import uws.chaudy.engage.Trigger;
import uws.chaudy.engage.TriggerFeedback;
import uws.chaudy.engage.Type;
import uws.chaudy.engage.TypeOutcome;
import uws.chaudy.engage.UpdateScore;
import uws.chaudy.services.EngageGrammarAccess;

@SuppressWarnings("all")
public class EngageSemanticSequencer extends AbstractDelegatingSemanticSequencer {

	@Inject
	private EngageGrammarAccess grammarAccess;
	
	public void createSequence(EObject context, EObject semanticObject) {
		if(semanticObject.eClass().getEPackage() == EngagePackage.eINSTANCE) switch(semanticObject.eClass().getClassifierID()) {
			case EngagePackage.ACTION:
				if(context == grammarAccess.getActionRule()) {
					sequence_Action(context, (Action) semanticObject); 
					return; 
				}
				else break;
			case EngagePackage.ACTION_REACTION:
				if(context == grammarAccess.getActionReactionRule()) {
					sequence_ActionReaction(context, (ActionReaction) semanticObject); 
					return; 
				}
				else break;
			case EngagePackage.ACTION_SEQUENCE:
				if(context == grammarAccess.getActionSequenceRule()) {
					sequence_ActionSequence(context, (ActionSequence) semanticObject); 
					return; 
				}
				else break;
			case EngagePackage.ACTION_SEQUENCES:
				if(context == grammarAccess.getActionSequencesRule()) {
					sequence_ActionSequences(context, (ActionSequences) semanticObject); 
					return; 
				}
				else break;
			case EngagePackage.AGE:
				if(context == grammarAccess.getAgeRule()) {
					sequence_Age(context, (Age) semanticObject); 
					return; 
				}
				else break;
			case EngagePackage.CHARACTERISTIC:
				if(context == grammarAccess.getCharacteristicRule()) {
					sequence_Characteristic(context, (Characteristic) semanticObject); 
					return; 
				}
				else break;
			case EngagePackage.ENUM:
				if(context == grammarAccess.getEnumRule()) {
					sequence_Enum(context, (uws.chaudy.engage.Enum) semanticObject); 
					return; 
				}
				else break;
			case EngagePackage.EVIDENCE_MODEL:
				if(context == grammarAccess.getEvidenceModelRule()) {
					sequence_EvidenceModel(context, (EvidenceModel) semanticObject); 
					return; 
				}
				else break;
			case EngagePackage.FEEDBACK:
				if(context == grammarAccess.getFeedbackRule()) {
					sequence_Feedback(context, (Feedback) semanticObject); 
					return; 
				}
				else break;
			case EngagePackage.FEEDBACK_MESSAGES:
				if(context == grammarAccess.getFeedbackMessagesRule()) {
					sequence_FeedbackMessages(context, (FeedbackMessages) semanticObject); 
					return; 
				}
				else break;
			case EngagePackage.FEEDBACK_MODEL:
				if(context == grammarAccess.getFeedbackModelRule()) {
					sequence_FeedbackModel(context, (FeedbackModel) semanticObject); 
					return; 
				}
				else break;
			case EngagePackage.GAME_DESCRIPTION:
				if(context == grammarAccess.getGameDescriptionRule()) {
					sequence_GameDescription(context, (GameDescription) semanticObject); 
					return; 
				}
				else break;
			case EngagePackage.INACTIVITY_LIMIT:
				if(context == grammarAccess.getInactivityLimitRule()) {
					sequence_InactivityLimit(context, (InactivityLimit) semanticObject); 
					return; 
				}
				else break;
			case EngagePackage.LEARNING_OUTCOMES:
				if(context == grammarAccess.getLearningOutcomesRule()) {
					sequence_LearningOutcomes(context, (LearningOutcomes) semanticObject); 
					return; 
				}
				else break;
			case EngagePackage.MODEL:
				if(context == grammarAccess.getModelRule()) {
					sequence_Model(context, (Model) semanticObject); 
					return; 
				}
				else break;
			case EngagePackage.OUTCOME:
				if(context == grammarAccess.getOutcomeRule()) {
					sequence_Outcome(context, (Outcome) semanticObject); 
					return; 
				}
				else break;
			case EngagePackage.OUTCOME_VALUE_LIMIT:
				if(context == grammarAccess.getOutcomeValueLimitRule()) {
					sequence_OutcomeValueLimit(context, (OutcomeValueLimit) semanticObject); 
					return; 
				}
				else break;
			case EngagePackage.PARAM_CONDITION:
				if(context == grammarAccess.getParamConditionRule()) {
					sequence_ParamCondition(context, (ParamCondition) semanticObject); 
					return; 
				}
				else break;
			case EngagePackage.PARAMETER:
				if(context == grammarAccess.getParameterRule()) {
					sequence_Parameter(context, (Parameter) semanticObject); 
					return; 
				}
				else break;
			case EngagePackage.PARAMS:
				if(context == grammarAccess.getParamsRule()) {
					sequence_Params(context, (Params) semanticObject); 
					return; 
				}
				else break;
			case EngagePackage.PLAYER_DESCRIPTION:
				if(context == grammarAccess.getPlayerDescriptionRule()) {
					sequence_PlayerDescription(context, (PlayerDescription) semanticObject); 
					return; 
				}
				else break;
			case EngagePackage.POINT:
				if(context == grammarAccess.getPointRule()) {
					sequence_Point(context, (Point) semanticObject); 
					return; 
				}
				else break;
			case EngagePackage.POINTS:
				if(context == grammarAccess.getPointsRule()) {
					sequence_Points(context, (Points) semanticObject); 
					return; 
				}
				else break;
			case EngagePackage.POINTS_CONDITION:
				if(context == grammarAccess.getPointsConditionRule()) {
					sequence_PointsCondition(context, (PointsCondition) semanticObject); 
					return; 
				}
				else break;
			case EngagePackage.REACTION:
				if(context == grammarAccess.getReactionRule()) {
					sequence_Reaction(context, (Reaction) semanticObject); 
					return; 
				}
				else break;
			case EngagePackage.REACTIONS:
				if(context == grammarAccess.getReactionsRule()) {
					sequence_Reactions(context, (Reactions) semanticObject); 
					return; 
				}
				else break;
			case EngagePackage.TIMER_ACTION:
				if(context == grammarAccess.getTimerActionRule()) {
					sequence_TimerAction(context, (TimerAction) semanticObject); 
					return; 
				}
				else break;
			case EngagePackage.TIMER_ACTIONS:
				if(context == grammarAccess.getTimerActionsRule()) {
					sequence_TimerActions(context, (TimerActions) semanticObject); 
					return; 
				}
				else break;
			case EngagePackage.TIMING:
				if(context == grammarAccess.getTimingRule()) {
					sequence_Timing(context, (Timing) semanticObject); 
					return; 
				}
				else break;
			case EngagePackage.TRIGGER:
				if(context == grammarAccess.getTriggerRule()) {
					sequence_Trigger(context, (Trigger) semanticObject); 
					return; 
				}
				else break;
			case EngagePackage.TRIGGER_FEEDBACK:
				if(context == grammarAccess.getTriggerFeedbackRule()) {
					sequence_TriggerFeedback(context, (TriggerFeedback) semanticObject); 
					return; 
				}
				else break;
			case EngagePackage.TYPE:
				if(context == grammarAccess.getTypeRule()) {
					sequence_Type(context, (Type) semanticObject); 
					return; 
				}
				else break;
			case EngagePackage.TYPE_OUTCOME:
				if(context == grammarAccess.getTypeOutcomeRule()) {
					sequence_TypeOutcome(context, (TypeOutcome) semanticObject); 
					return; 
				}
				else break;
			case EngagePackage.UPDATE_SCORE:
				if(context == grammarAccess.getUpdateScoreRule()) {
					sequence_UpdateScore(context, (UpdateScore) semanticObject); 
					return; 
				}
				else break;
			}
		if (errorAcceptor != null) errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
	}
	
	/**
	 * Constraint:
	 *     (updateScore=UpdateScore | triggerFeedback=TriggerFeedback)
	 */
	protected void sequence_ActionReaction(EObject context, ActionReaction semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (action1=ID (before?='before' | after?='after') action2=ID sequenceReactions+=ActionReaction+)
	 */
	protected void sequence_ActionSequence(EObject context, ActionSequence semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     actionSequences+=ActionSequence
	 */
	protected void sequence_ActionSequences(EObject context, ActionSequences semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (name=ID params+=Parameter params+=Parameter* points+=Points+ reactions=Reactions?)
	 */
	protected void sequence_Action(EObject context, Action semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (ageMin=INT ageMax=INT)
	 */
	protected void sequence_Age(EObject context, Age semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, EngagePackage.Literals.AGE__AGE_MIN) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, EngagePackage.Literals.AGE__AGE_MIN));
			if(transientValues.isValueTransient(semanticObject, EngagePackage.Literals.AGE__AGE_MAX) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, EngagePackage.Literals.AGE__AGE_MAX));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getAgeAccess().getAgeMinINTTerminalRuleCall_0_0(), semanticObject.getAgeMin());
		feeder.accept(grammarAccess.getAgeAccess().getAgeMaxINTTerminalRuleCall_2_0(), semanticObject.getAgeMax());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (name=ID type=Type)
	 */
	protected void sequence_Characteristic(EObject context, Characteristic semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, EngagePackage.Literals.CHARACTERISTIC__NAME) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, EngagePackage.Literals.CHARACTERISTIC__NAME));
			if(transientValues.isValueTransient(semanticObject, EngagePackage.Literals.CHARACTERISTIC__TYPE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, EngagePackage.Literals.CHARACTERISTIC__TYPE));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getCharacteristicAccess().getNameIDTerminalRuleCall_0_0(), semanticObject.getName());
		feeder.accept(grammarAccess.getCharacteristicAccess().getTypeTypeParserRuleCall_2_0(), semanticObject.getType());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (enumValues+=STRING enumValues+=STRING*)
	 */
	protected void sequence_Enum(EObject context, uws.chaudy.engage.Enum semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (actions+=Action+ timerActions=TimerActions? actionSequence=ActionSequences?)
	 */
	protected void sequence_EvidenceModel(EObject context, EvidenceModel semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     feedbackMsgs+=Feedback+
	 */
	protected void sequence_FeedbackMessages(EObject context, FeedbackMessages semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     triggers+=Trigger+
	 */
	protected void sequence_FeedbackModel(EObject context, FeedbackModel semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (name=ID message=STRING type=TypeFeedback? final?='final'?)
	 */
	protected void sequence_Feedback(EObject context, Feedback semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         name=STRING 
	 *         dev=INT 
	 *         desc=STRING? 
	 *         ageRange=Age? 
	 *         lang=ID? 
	 *         country=ID? 
	 *         genre=STRING? 
	 *         subject=STRING? 
	 *         public=BOOL?
	 *     )
	 */
	protected void sequence_GameDescription(EObject context, GameDescription semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (sign=CompSign limit=INT triggerReactions+=ActionReaction+)
	 */
	protected void sequence_InactivityLimit(EObject context, InactivityLimit semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     outcomes+=Outcome+
	 */
	protected void sequence_LearningOutcomes(EObject context, LearningOutcomes semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         game=GameDescription 
	 *         player=PlayerDescription? 
	 *         learningOutcomes=LearningOutcomes 
	 *         feedbackMessages=FeedbackMessages? 
	 *         evidenceModel=EvidenceModel 
	 *         feedbackModel=FeedbackModel?
	 *     )
	 */
	protected void sequence_Model(EObject context, Model semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (outcome=[Outcome|ID] sign=CompSign negativeLimit?='-'? limit=INT triggerReactions+=TriggerFeedback+)
	 */
	protected void sequence_OutcomeValueLimit(EObject context, OutcomeValueLimit semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (name=ID desc=STRING? value=INT? type=TypeOutcome?)
	 */
	protected void sequence_Outcome(EObject context, Outcome semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     params=[Params|ID]
	 */
	protected void sequence_ParamCondition(EObject context, ParamCondition semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, EngagePackage.Literals.PARAM_CONDITION__PARAMS) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, EngagePackage.Literals.PARAM_CONDITION__PARAMS));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getParamConditionAccess().getParamsParamsIDTerminalRuleCall_0_1(), semanticObject.getParams());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (type=Type name=ID)
	 */
	protected void sequence_Parameter(EObject context, Parameter semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, EngagePackage.Literals.PARAMETER__TYPE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, EngagePackage.Literals.PARAMETER__TYPE));
			if(transientValues.isValueTransient(semanticObject, EngagePackage.Literals.PARAMETER__NAME) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, EngagePackage.Literals.PARAMETER__NAME));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getParameterAccess().getTypeTypeParserRuleCall_0_0(), semanticObject.getType());
		feeder.accept(grammarAccess.getParameterAccess().getNameIDTerminalRuleCall_1_0(), semanticObject.getName());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (name=ID? values+=Param values+=Param)
	 */
	protected void sequence_Params(EObject context, Params semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     characteristics+=Characteristic+
	 */
	protected void sequence_PlayerDescription(EObject context, PlayerDescription semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (negative?='-'? value=INT)
	 */
	protected void sequence_Point(EObject context, Point semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (keyWd=PointsKeyWd (sign=Sign | value=Point))
	 */
	protected void sequence_PointsCondition(EObject context, PointsCondition semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (outcome=[Outcome|ID]? pts=Point (others?='others' | params+=Params+))
	 */
	protected void sequence_Points(EObject context, Points semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (paramsC=ParamCondition | (pointsC=PointsCondition feedback=[Feedback|ID]))
	 */
	protected void sequence_Reaction(EObject context, Reaction semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     reaction+=Reaction+
	 */
	protected void sequence_Reactions(EObject context, Reactions semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (name=ID timings+=Timing+)
	 */
	protected void sequence_TimerAction(EObject context, TimerAction semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     timerActions+=TimerAction+
	 */
	protected void sequence_TimerActions(EObject context, TimerActions semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     ((before?='within' | after?='after') limit=INT timingReactions+=ActionReaction+)
	 */
	protected void sequence_Timing(EObject context, Timing semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     feedback=[Feedback|ID]
	 */
	protected void sequence_TriggerFeedback(EObject context, TriggerFeedback semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, EngagePackage.Literals.TRIGGER_FEEDBACK__FEEDBACK) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, EngagePackage.Literals.TRIGGER_FEEDBACK__FEEDBACK));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getTriggerFeedbackAccess().getFeedbackFeedbackIDTerminalRuleCall_1_0_1(), semanticObject.getFeedback());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (perf=OutcomeValueLimit | inactivity=InactivityLimit)
	 */
	protected void sequence_Trigger(EObject context, Trigger semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (knowledge?='knowledge' | skill?='skill' | competence?='competence')
	 */
	protected void sequence_TypeOutcome(EObject context, TypeOutcome semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         simpleType='Int' | 
	 *         simpleType='Float' | 
	 *         simpleType='String' | 
	 *         simpleType='Bool' | 
	 *         simpleType='Char' | 
	 *         simpleType='Text' | 
	 *         enumType=Enum
	 *     )
	 */
	protected void sequence_Type(EObject context, Type semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (outcome=[Outcome|ID] pts=Point)
	 */
	protected void sequence_UpdateScore(EObject context, UpdateScore semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, EngagePackage.Literals.UPDATE_SCORE__OUTCOME) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, EngagePackage.Literals.UPDATE_SCORE__OUTCOME));
			if(transientValues.isValueTransient(semanticObject, EngagePackage.Literals.UPDATE_SCORE__PTS) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, EngagePackage.Literals.UPDATE_SCORE__PTS));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getUpdateScoreAccess().getOutcomeOutcomeIDTerminalRuleCall_0_0_1(), semanticObject.getOutcome());
		feeder.accept(grammarAccess.getUpdateScoreAccess().getPtsPointParserRuleCall_2_0(), semanticObject.getPts());
		feeder.finish();
	}
}
