package test.simpleproducerconsumer;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;

import test.message.SimpleImmutableMessage;

/**
 * Created by Gabriel on 15/04/17.
 */
public class Producer implements Runnable {

	private CamelContext camelContext = null;
	private ProducerTemplate template;

	public Producer(CamelContext context) {
		camelContext = context;
		template = context.createProducerTemplate();
	}

	public void run() {
		try {
			configureRoute();
			produceMessages();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	private void produceMessages() throws Exception {
		while (true) {
			produceMessage();
			Thread.sleep(5);
		}

	}

	private void configureRoute() throws Exception {
		camelContext.addRoutes(new RouteBuilder() {
			public void configure() throws Exception {
				from("direct://input")
				.to("rabbitmq://localhost:5672/test_ip?queue=task_queue&routingKey=test_task"
						+ "&autoAck=true&durable=true&username=guest&password=guest&autoDelete=false&exchangePattern=InOnly");

			}
		});
	}

	private void produceMessage() {
		SimpleImmutableMessage message = new SimpleImmutableMessage(System.currentTimeMillis(), "Some content");
		System.out.println("send: " + message);
		template.sendBody("direct://input",  message);
	}
}
