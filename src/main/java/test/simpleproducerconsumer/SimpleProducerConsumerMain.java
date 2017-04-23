package test.simpleproducerconsumer;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

import test.converter.ByteArrayToSimpleImmutableMessageTypeConverter;
import test.converter.SimpleImmutableToByteArrayMessageTypeConverter;
import test.message.SimpleImmutableMessage;

/**
 * Created by Gabriel on 15/04/17.
 */
public class SimpleProducerConsumerMain {

	public static void main(String[] args) throws Exception {
		// starts the default camel context
		final CamelContext camelContext = new DefaultCamelContext();
		camelContext.setTracing(true);
		camelContext.start();

		// add some type converters
		camelContext.getTypeConverterRegistry().addTypeConverter(
				SimpleImmutableMessage.class, byte[].class,
				new ByteArrayToSimpleImmutableMessageTypeConverter());
		camelContext.getTypeConverterRegistry().addTypeConverter(byte[].class,
				SimpleImmutableMessage.class,
				new SimpleImmutableToByteArrayMessageTypeConverter());

		// create the message producer injecting the camel context
		new Thread(new Producer(camelContext)).start();

		// create the message consumer
		new Consumer(camelContext).configure();

	}

}
