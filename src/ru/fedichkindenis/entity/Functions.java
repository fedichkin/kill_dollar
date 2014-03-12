package ru.fedichkindenis.entity;

import ru.fedichkindenis.enums.ElFunction;
import ru.fedichkindenis.enums.Operand;
import ru.fedichkindenis.enums.TypeIf;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 28.02.14
 * Time: 21:13
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "function")
public class Functions {

    /**
     * Идентификатор
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Является ли функция условием, true - да, false - нет
     */
    @Column(name = "is_if", nullable = false)
    private Boolean isIf;

    /**
     * Если условие, то  определяется тип условия
     */
    @Column(name = "type_if", nullable = true)
    @Enumerated(value = EnumType.ORDINAL)
    private TypeIf typeIf;

    /**
     * Элемент функции
     */
    @Column(name = "el_function", nullable = true)
    @Enumerated(value = EnumType.ORDINAL)
    private ElFunction elFunction;

    /**
     * Операнд (числовой элемент из игры)
     */
    @Column(name = "operand", nullable = true)
    @Enumerated(value = EnumType.ORDINAL)
    private Operand operand;

    /**
     * Операнд (константа, просто число)
     */
    @Column(name = "const_operand", nullable = true)
    private BigDecimal constOperand;

    @ManyToOne
    @JoinColumn(name = "func_operand", nullable = true)
    private Functions funcOperand;

    /**
     * Ссылка на на следующий шаг функции
     */
    @ManyToOne
    @JoinColumn(name = "next_step", nullable = true)
    private Functions nextStep;

    /**********GETTERS AND SETTERS**********/

    public Long getId() {
        return id;
    }

    public Boolean getIf() {
        return isIf;
    }

    public void setIf(Boolean anIf) {
        isIf = anIf;
    }

    public TypeIf getTypeIf() {
        return typeIf;
    }

    public void setTypeIf(TypeIf typeIf) {
        this.typeIf = typeIf;
    }

    public ElFunction getElFunction() {
        return elFunction;
    }

    public void setElFunction(ElFunction elFunction) {
        this.elFunction = elFunction;
    }

    public Operand getOperand() {
        return operand;
    }

    public void setOperand(Operand operand) {
        this.operand = operand;
    }

    public Functions getNextStep() {
        return nextStep;
    }

    public void setNextStep(Functions nextStep) {
        this.nextStep = nextStep;
    }

    public BigDecimal getConstOperand() {
        return constOperand;
    }

    public void setConstOperand(BigDecimal constOperand) {
        this.constOperand = constOperand;
    }

    public Functions getFuncOperand() {
        return funcOperand;
    }

    public void setFuncOperand(Functions funcOperand) {
        this.funcOperand = funcOperand;
    }
}
