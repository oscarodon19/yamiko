package ar.edu.ungs.yaf.rules.entities

/**
 * Formula. Entidad del modelo de dominio de Reglas. Representa una condici�n at�mica en la que un campo es sometido a una comparaci�n con un valor.
 * @author ricardo
 *
 */
@SerialVersionUID(70002L)
class Formula(campo:Int, operador:Int, valor:Int) extends Serializable{
  	def getCampo()=campo
  	def getOperador()=operador
  	def getValor()=valor
 	
  	def getStrCampo():String=Constants.NOMENCLADOR(campo)
	  def getStrOperador():String=Constants.OPERADORES(operador)
    def getStrValor():String= {
  		if (Constants.NOMENCLADOR_VALUES.get(campo)==null)
  		  String.valueOf(valor)		
  		else 
  			if (valor>=Constants.NOMENCLADOR_VALUES.get(campo).size)
  				"INVALID VALUE"
  			else
  				return Constants.NOMENCLADOR_VALUES.get(campo).get(valor);
	  }
  	
    def canEqual(a:Formula) = a.isInstanceOf[Formula]
    override def equals(that: Any): Boolean = that match {
      case that: Formula => 
        that.canEqual(this) && this.hashCode == that.hashCode
      case _ => false
    }
    override def hashCode:Int = operador.hashCode() + campo.hashCode() + valor.hashCode()  
	  override def toString = "Formula [" + getStrCampo() + getStrOperador() + getStrValor() +"]"  	
}

/**
 * Rule. Entidad del modelo de dominio de Reglas. Representa una regla. Contiene una lista de f�rmulas que representan las condiciones (conjunciones) y una f�rmula 
 * quer reresenta la predicci�n. Esta �ltima siempre trabaja con el operador "igual". 
 * @author ricardo
 *
 */
@SerialVersionUID(70001L)
class Rule(conditions:List[Formula], prediction:Formula) extends Serializable{
    def getConditions()=conditions
    def getPrediction()=prediction
    
    def canEqual(a:Rule) = a.isInstanceOf[Rule]
    override def equals(that: Any): Boolean = that match {
      case that: Rule => 
        that.canEqual(this) && this.hashCode == that.hashCode
      case _ => false
    }
    override def hashCode:Int = conditions.hashCode() + prediction.hashCode()
	  override def toString = "Rule [ IF (" + conditions.mkString("AND") + ") THEN " + prediction +"]"  	
}