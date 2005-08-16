package javax.xml.ws;

/**
 * A Web service endpoint.
 * <p>
 * Endpoints are created using the EndpointFactory  class. An endpoint is always tied to one Binding  and one implementor,
 * both set at endpoint creation time.
 * <p>
 * An endpoint is either in a published or an unpublished state. The publish methods can be used to start publishing an endpoint,
 * at which point it starts accepting incoming requests. Conversely, the stop method can be used to stop accepting incoming
 * requests and take the endpoint down.
 * <p>
 * An Executor may be set on the endpoint in order to gain better control over the threads used to dispatch incoming requests.
 * For instance, thread pooling with certain parameters can be enabled by creating a ThreadPoolExecutor and registering it with
 * the endpoint.
 * <p>
 * Handler chains can be set using the contained Binding.
 * <p>
 * An endpoint may have a list of metadata documents, such as WSDL and XMLSchema documents, bound to it. At publishing
 * time, the JAX-WS implementation will try to reuse as much of that metadata as possible instead of generating new one based on
 *  the annotations present on the implementor.
 *  <p>
 *  @since JAX-WS 2.0
 * @author shaas02
 * @see     EndpointFactory.createEndpoint(java.net.URI, java.lang.Object), Binding, Executor
 */
public interface Endpoint {

	static final java.lang.String WSDL_SERVICE = "javax.xml.ws.wsdl.service";
	
	static final java.lang.String WSDL_PORT = "javax.xml.ws.wsdl.port";
}
