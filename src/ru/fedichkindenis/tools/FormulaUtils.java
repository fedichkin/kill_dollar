package ru.fedichkindenis.tools;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.fedichkindenis.enums.ElFunction;
import ru.fedichkindenis.entity.Functions;
import ru.fedichkindenis.servlets.GetListGames;

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
    private static final SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

    public static Object getResultFormula(Long idFunc){

        Stack<Integer> operands = new Stack<Integer>();
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
                    Integer op = (Integer) SessionUtils.getValueOperand(functions.getOperand());
                    operands.push(op);
                }
                else if(functions.getConstOperand() != null){
                    Integer op = functions.getConstOperand().intValue();
                    operands.push(op);
                }
            }

        }while (functions.getNextStep() != null);

        while (!elements.empty()){
            processOperator(operands, elements.pop());
        }

        return operands.get(0);
    }

    private static void processOperator(Stack<Integer> operands, ElFunction el){

        Integer first = operands.pop();
        Integer second = operands.pop();

        switch (el){
            case ADDITION:
                operands.push(first + second);
                break;
            case SUBTRACTION:
                operands.push(first - second);
                break;
            case MULTIPLICATION:
                operands.push(first * second);
                break;
            case DIVISION:
                operands.push(first / second);
                break;
        }
    }
}
