/*
 * generated by Xtext
 */
package uws.engage.dsl.validation;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.emf.ecore.EPackage;

public class AbstractAssessValidator extends org.eclipse.xtext.validation.AbstractDeclarativeValidator {

	@Override
	protected List<EPackage> getEPackages() {
	    List<EPackage> result = new ArrayList<EPackage>();
	    result.add(uws.engage.dsl.assess.AssessPackage.eINSTANCE);
		return result;
	}
}
