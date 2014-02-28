package ru.fedichkindenis.entity;

import ru.fedichkindenis.enums.ElFunction;
import ru.fedichkindenis.enums.Operand;
import ru.fedichkindenis.enums.TypeIf;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 28.02.14
 * Time: 21:13
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "function")
public class Function {

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
     * Операнд
     */
    @Column(name = "operand", nullable = true)
    @Enumerated(value = EnumType.ORDINAL)
    private Operand operand;

    /**
     * Ссылка на на следующий шаг функции
     */
    @ManyToOne
    @JoinColumn(name = "next_step", nullable = true)
    private Function nextStep;

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

    public Function getNextStep() {
        return nextStep;
    }

    public void setNextStep(Function nextStep) {
        this.nextStep = nextStep;
    }
}
