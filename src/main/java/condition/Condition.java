package condition;

import java.util.function.Consumer;
import java.util.function.Predicate;

/***
 * https://stackoverflow.com/questions/49857232/use-java-lambda-instead-of-if-else/49857571#49857571
 */
public class Condition<E> {

    private final Predicate<E> condition;
    private final E operand;

    private Boolean result;

    private Condition(E operand, Predicate<E> condition) {
        this.condition = condition;
        this.operand = operand;
    }

    public static <E> Condition<E> of(E element, Predicate<E> condition) {
        return new Condition<>(element, condition);
    }

    public Condition<E> ifTrue(Consumer<E> consumer) {
        if (result == null)
            result = condition.test(operand);

        if (result)
            consumer.accept(operand);

        return this;
    }

    public Condition<E> ifFalse(Consumer<E> consumer) {
        if (result == null)
            result = condition.test(operand);

        if (!result)
            consumer.accept(operand);

        return this;
    }

    public E getOperand() {
        return operand;
    }
}
