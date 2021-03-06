/**
 */
package uws.engage.dsl.assess;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Timer Action</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link uws.engage.dsl.assess.TimerAction#getName <em>Name</em>}</li>
 *   <li>{@link uws.engage.dsl.assess.TimerAction#getTimings <em>Timings</em>}</li>
 * </ul>
 * </p>
 *
 * @see uws.engage.dsl.assess.AssessPackage#getTimerAction()
 * @model
 * @generated
 */
public interface TimerAction extends EObject
{
  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see uws.engage.dsl.assess.AssessPackage#getTimerAction_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link uws.engage.dsl.assess.TimerAction#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Timings</b></em>' containment reference list.
   * The list contents are of type {@link uws.engage.dsl.assess.Timing}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Timings</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Timings</em>' containment reference list.
   * @see uws.engage.dsl.assess.AssessPackage#getTimerAction_Timings()
   * @model containment="true"
   * @generated
   */
  EList<Timing> getTimings();

} // TimerAction
