/*
* generated by Xtext
*/
package uws.engage.dsl.parser.antlr;

import com.google.inject.Inject;

import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import uws.engage.dsl.services.AssessGrammarAccess;

public class AssessParser extends org.eclipse.xtext.parser.antlr.AbstractAntlrParser {
	
	@Inject
	private AssessGrammarAccess grammarAccess;
	
	@Override
	protected void setInitialHiddenTokens(XtextTokenStream tokenStream) {
		tokenStream.setInitialHiddenTokens("RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT");
	}
	
	@Override
	protected uws.engage.dsl.parser.antlr.internal.InternalAssessParser createParser(XtextTokenStream stream) {
		return new uws.engage.dsl.parser.antlr.internal.InternalAssessParser(stream, getGrammarAccess());
	}
	
	@Override 
	protected String getDefaultRuleName() {
		return "Model";
	}
	
	public AssessGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}
	
	public void setGrammarAccess(AssessGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
	
}
