package javax.xml.rpc.handler;

/**
 * public interface LogicalHandler<C extends <code>LogicalMessageContext</code>>
 * extends <code>AbstractHandler</code><C>
 * <p>
 * The javax.xml.rpc.handler.LogicalHandler extends AbstractHandler to provide typesafety for the message context
 * parameter.
 * @version 1.0
 * @author shaas02
 *
 */
public interface LogicalHandler<C extends LogicalMessageContext> extends AbstractHandler {

}
