package test.simpleproducerconsumer;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import test.message.SimpleImmutableMessage;

/**
 * 
 * @author Gabriel
 *
 */
public class Consumer {

	CamelContext camelContext = null;

	public Consumer(CamelContext context) {
		camelContext = context;

	}

	public void configure() {
		try {
			configureRoute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void configureRoute() throws Exception {
		camelContext.addRoutes(new RouteBuilder() {
			public void configure() throws Exception {
				from(
						"rabbitmq://localhost:5672/test_ip?queue=task_queue&username=guest&routingKey=test_task&password=guest"
								+ "&autoAck=true&durable=true&exchangeType=direct&autoDelete=false&exchangePattern=InOut")
						.process(new SimpleImmutableMessageProcessor());
			}
		});
	}

	class SimpleImmutableMessageProcessor implements Processor {

		public void process(Exchange exchange) throws Exception {
			exchange.setOut(exchange.getIn());
			Message m = exchange.getOut();
			org.apache.camel.TypeConverter tc = exchange.getContext()
					.getTypeConverter();
			SimpleImmutableMessage incomingMessage = tc.convertTo(
					SimpleImmutableMessage.class, m.getBody());
			System.out.println("received: " + incomingMessage.toString());

		}

	}

}
