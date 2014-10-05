/**
 */
package uws.chaudy.engage;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link uws.chaudy.engage.Type#getSimpleType <em>Simple Type</em>}</li>
 *   <li>{@link uws.chaudy.engage.Type#getEnumType <em>Enum Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see uws.chaudy.engage.EngagePackage#getType()
 * @model
 * @generated
 */
public interface Type extends EObject
{
  /**
   * Returns the value of the '<em><b>Simple Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Simple Type</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Simple Type</em>' attribute.
   * @see #setSimpleType(String)
   * @see uws.chaudy.engage.EngagePackage#getType_SimpleType()
   * @model
   * @generated
   */
  String getSimpleType();

  /**
   * Sets the value of the '{@link uws.chaudy.engage.Type#getSimpleType <em>Simple Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Simple Type</em>' attribute.
   * @see #getSimpleType()
   * @generated
   */
  void setSimpleType(String value);

  /**
   * Returns the value of the '<em><b>Enum Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Enum Type</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Enum Type</em>' containment reference.
   * @see #setEnumType(uws.chaudy.engage.Enum)
   * @see uws.chaudy.engage.EngagePackage#getType_EnumType()
   * @model containment="true"
   * @generated
   */
  uws.chaudy.engage.Enum getEnumType();

  /**
   * Sets the value of the '{@link uws.chaudy.engage.Type#getEnumType <em>Enum Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Enum Type</em>' containment reference.
   * @see #getEnumType()
   * @generated
   */
  void setEnumType(uws.chaudy.engage.Enum value);

} // Type
