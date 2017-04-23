package test.converter;

import org.apache.camel.Exchange;
import org.apache.camel.support.TypeConverterSupport;

import test.message.SimpleImmutableMessage;

public class SimpleImmutableToByteArrayMessageTypeConverter extends TypeConverterSupport {

	@SuppressWarnings("unchecked")
	public <T> T convertTo(Class<T> type, Exchange exchange, Object value) {
		// converter from value to the MyOrder bean
		SimpleImmutableMessage message = (SimpleImmutableMessage) value;

		return (T) (message.getTimemillis() + ";" + message.getContent()).getBytes();
	}
	

}
