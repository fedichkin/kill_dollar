package ru.fedichkindenis.tools;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.fedichkindenis.entity.Game;
import ru.fedichkindenis.entity.Resources;
import ru.fedichkindenis.enums.ElFunction;
import ru.fedichkindenis.entity.Functions;
import ru.fedichkindenis.servlets.GetListGames;

import java.awt.dnd.DropTargetEvent;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: Fedichkin.DY
 * Date: 07.03.14
 * Time: 22:17
 * To change this template use File | Settings | File Templates.
 */
public class FormulaUtils {

    private static final Logger LOG = Logger.getLogger(GetListGames.class);
    //private static final SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

    public static BigDecimal getResultFormula(Long idFunc, Game game,
                                              Date gameDate, Resources resources){

        Stack<BigDecimal> operands = new Stack<BigDecimal>();
        Stack<ElFunction> elements = new Stack<ElFunction>();

        Functions functions = null;
        Long id = idFunc;

        do{
            functions = SessionUtils.getEntityObject(Functions.class, id);
            id = functions.getId();

            if(!functions.getIf()){
                if(functions.getElFunction() != null){
                    ElFunction el = functions.getElFunction();

                    switch (el){
                        case LEFT_BRACKET:
                            elements.push(el);
                            break;
                        case RIGHT_BRACKET:
                            while (elements.lastElement() != ElFunction.LEFT_BRACKET){
                                processOperator(operands, elements.pop());
                            }
                            elements.pop();
                            break;
                        default:
                            while (!elements.empty() && elements.lastElement().getPriority() >= el.getPriority()){
                                processOperator(operands, elements.pop());
                            }
                            elements.push(el);
                    }
                }
                else if(functions.getOperand() != null){
                    Object op = SessionUtils.getValueOperand(functions.getOperand(), game, gameDate, resources);
                    try {
                        operands.push(NumberUtils.parseBigDecimal(op.toString(), "Не число"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else if(functions.getConstOperand() != null){
                    BigDecimal op = functions.getConstOperand();
                    operands.push(op);
                }
                else if(functions.getFuncOperand() != null){
                    operands.push(getResultFormula(functions.getFuncOperand().getId(), game, gameDate, resources));
                }
            }

        }while (functions.getNextStep() != null);

        while (!elements.empty()){
            processOperator(operands, elements.pop());
        }

        return operands.get(0);
    }

    private static void processOperator(Stack<BigDecimal> operands, ElFunction el){

        BigDecimal first = null;
        BigDecimal second = null;
        try {
            first = NumberUtils.parseBigDecimal(operands.pop().toString(), "Не число!");
            second = NumberUtils.parseBigDecimal(operands.pop().toString(), "Не число!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        switch (el){
            case ADDITION:
                operands.push((BigDecimal)NumberUtils.addition(first, second));
                break;
            case SUBTRACTION:
                operands.push((BigDecimal)NumberUtils.subtraction(first, second));
                break;
            case MULTIPLICATION:
                operands.push((BigDecimal)NumberUtils.multiplication(first, second));
                break;
            case DIVISION:
                operands.push((BigDecimal)NumberUtils.division(first, second));
                break;
        }
    }
}
